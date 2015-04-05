#include <linkage.h>
#include <string.h>
#include <printk.h>

#ifdef __ARCH_X86__
#include <stdio.h>
#else
#include <ring_buffer.h>
#endif

#include <uart.h>

extern struct ring_buffer *kernel_ring_buffer;
extern struct ring_buffer *user_ring_buffer;

typedef char * va_list;
#define _INTSIZEOF(n)   ((sizeof(n)+sizeof(int)-1)&~(sizeof(int) - 1) )
#define va_start(ap,v) ( ap = (va_list)&v + _INTSIZEOF(v) )
#define va_arg(ap,t) ( *(t *)((ap += _INTSIZEOF(t)) - _INTSIZEOF(t)) )
#define va_end(ap)    ( ap = (va_list)0 )

static char print_buf[1024];

#define FORMAT_TYPE_MASK	0xff00
#define FORMAT_TYPE_SIGN_BIT	0x0100
#define FORMAT_TYPE_NONE	0x000
#define FORMAT_TYPE_CHAR	0x100
#define FORMAT_TYPE_UCHAR	0x200
#define FORMAT_TYPE_SHORT	0x300
#define FORMAT_TYPE_USHORT	0x400
#define FORMAT_TYPE_INT		0x500
#define FORMAT_TYPE_UINT	0x600
#define FORMAT_TYPE_LONG	0x700
#define FORMAT_TYPE_ULONG	0x800
#define FORMAT_TYPE_STR		0xd00
#define FORMAT_TYPE_PTR		0x900
#define FORMAT_TYPE_SIZE_T	0xb00

#define FORMAT_TYPE(x)	((x)&FORMAT_TYPE_MASK)
#define SET_FORMAT_TYPE(x,t)	do{\
(x)&=~FORMAT_TYPE_MASK;(x)|=(t);\
}while(0)
#define FORMAT_SIGNED(x)	((x)&FORMAT_TYPE_SIGN_BIT)

#define FORMAT_FLAG_MASK	0xffff0000
#define FORMAT_FLAG_SPACE	0x10000
#define FORMAT_FLAG_ZEROPAD	0x20000
#define FORMAT_FLAG_LEFT	0x40000
#define	FORMAT_FLAG_WIDTH	0x100000

#define FORMAT_FLAG(x)	((x)&FORMAT_FLAG_MASK)
#define SET_FORMAT_FLAG(x,f)	((x)|=(f))

#define FORMAT_BASE_MASK	0xff
#define FORMAT_BASE_O		0x08
#define FORMAT_BASE_X		0x10
#define FORMAT_BASE_D		0x0a
#define FORMAT_BASE_B		0x02

#define FORMAT_BASE(x)	(FORMAT_BASE_MASK&(x))
#define SET_FORMAT_BASE(x,t)	do{(x)&=~FORMAT_BASE_MASK;(x)|=(t);}while(0)

#define do_div(n,base) ({ \
	int __res; \
	__res = ((unsigned int) n) % (unsigned int) base; \
	n = ((unsigned int) n) / (unsigned int) base; \
	__res; })


void * _debug_output_io = (void *)0xef005020; /* This initial value is the physical address, meaningless. */
 
#ifdef __ARCH_X86__
static void __put_char_u(char *p,int num){
  while(*p&&num--)
    printf("%c", *p++);
}
static void __put_char_k(char *p,int num){
  while(*p&&num--)
    printf("%c", *p++);
}
#else
extern int ring_buffer_enabled;
extern struct ring_buffer *user_ring_buffer;
static void __put_char_u(char *p,int num){
	while(*p&&num--){
	  if (ring_buffer_enabled)
		ring_buffer_put_char(user_ring_buffer, *p++);
	  else
		*(volatile unsigned int *)_debug_output_io=*p++;
	  /*	  *(volatile unsigned int *)0xef005020=*p++; */
	};
}
static void __put_char_k(char *p,int num){
	while(*p&&num--){
	  if (ring_buffer_enabled)
		ring_buffer_put_char(kernel_ring_buffer, *p++);
	  else
		*(volatile unsigned int *)_debug_output_io=*p++;
	  /*	  *(volatile unsigned int *)0xef005020=*p++; */
	};
}

#endif

char *number(char *str, int num,int base,unsigned int flags){
const char *digits="0123456789abcdef";
char numbers[68];

	int i=0;
	int sign=0;
	
	if(FORMAT_SIGNED(flags)&&(signed int)num<0){
		sign=1;
		num=~num+1;
	}
	
	do{
		numbers[i++]=digits[do_div(num,base)];
	}while(num!=0);
	
	if(FORMAT_BASE(flags)==FORMAT_BASE_O){
		numbers[i++]='0';
	}else if(FORMAT_BASE(flags)==FORMAT_BASE_X){
		numbers[i++]='x';
		numbers[i++]='0';
	}else if(FORMAT_BASE(flags)==FORMAT_BASE_B){
		numbers[i++]='b';
		numbers[i++]='0';
	}
	if(sign)
		numbers[i++]='-';

	while (i-- > 0)
			*str++ = numbers[i];

	return str;
}

int format_decode(const char *fmt,unsigned int *flags){
	const char *start = fmt;

	*flags &= ~FORMAT_TYPE_MASK;
	*flags |= FORMAT_TYPE_NONE;
	for (; *fmt ; ++fmt) {
		if (*fmt == '%')
		break;
	}

	if (fmt != start || !*fmt)
		return fmt - start;

	do{
		fmt++;
		switch(*fmt){
			case 'l':
				SET_FORMAT_FLAG(*flags,FORMAT_FLAG_WIDTH);
				break;
			default:
				break;
		}
	}while(0);
	
	SET_FORMAT_BASE(*flags,FORMAT_BASE_D);
	switch (*fmt) {
		case 'c':
			SET_FORMAT_TYPE(*flags,FORMAT_TYPE_CHAR);
			break;

		case 's':
			SET_FORMAT_TYPE(*flags,FORMAT_TYPE_STR);
			break;

		case 'o':
			SET_FORMAT_BASE(*flags,FORMAT_BASE_O);
			SET_FORMAT_TYPE(*flags,FORMAT_TYPE_UINT);
			break;

		case 'x':
		case 'X':
			SET_FORMAT_BASE(*flags,FORMAT_BASE_X);
			SET_FORMAT_TYPE(*flags,FORMAT_TYPE_UINT);
			break;

		case 'd':
		case 'i':
			SET_FORMAT_TYPE(*flags,FORMAT_TYPE_INT);
			SET_FORMAT_BASE(*flags,FORMAT_BASE_D);
			break;
		case 'u':
			SET_FORMAT_TYPE(*flags,FORMAT_TYPE_UINT);
			SET_FORMAT_BASE(*flags,FORMAT_BASE_D);
			break;

	default:
		break;
	}
	return ++fmt-start;
}

#ifndef __ARCH_X86__
int vsnprintf(char *buf, int size, const char *fmt, va_list args){
	int num;
	char *str, *end, c,*s;
	int read;
	unsigned int spec=0;

	str = buf;
	end = buf + size;

	if (end < buf) {
		end = ((void *)-1);
		size = end - buf;
	}

	while (*fmt) {
		const char *old_fmt = fmt;

		read = format_decode(fmt, &spec);
		fmt += read;

		if((FORMAT_TYPE(spec))==FORMAT_TYPE_NONE){
			int copy = read;
			if (str < end) {
				if (copy > end - str)
					copy = end - str;
					memcpy(str, old_fmt, copy);
			}
			str += read;

		}else if(spec&FORMAT_FLAG_WIDTH){
			//do nothing
		}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_CHAR){
			c = (unsigned char) va_arg(args, int);
			if (str < end)
				*str = c;
			++str;
		}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_STR){
			s = (char *) va_arg(args, char *);
			while(str<end&&*s!='\0'){
				*str++=*s++;
			}
		}else{
			if(FORMAT_TYPE(spec)==FORMAT_TYPE_INT){
				num = va_arg(args, int);
			}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_ULONG){
				num = va_arg(args, unsigned long);
			}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_LONG){
				num = va_arg(args, long);
			}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_SIZE_T){
				num = va_arg(args, int);
			}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_USHORT){
				num = (unsigned short) va_arg(args, int);
			}else if(FORMAT_TYPE(spec)==FORMAT_TYPE_SHORT){
				num = (short) va_arg(args, int);
			}else{
				num = va_arg(args, unsigned int);
			}
				str=number(str,num,spec&FORMAT_BASE_MASK,spec);
		}
	}
	if (size > 0) {
		if (str < end)
			*str = '\0';
		else
			end[-1] = '\0';
	}
	return str-buf;
}
#endif

static int printk_disabled_flag = 0;
void printk_disable() {
  printk_disabled_flag = 1;
}

void printk_enable() {
  printk_disabled_flag = 0;
}

int log_type_ini = 1;
int log_type_fs = 0;
int log_type_mm = 0;
int log_type_proc = 0;
int log_type_irq = 0;

int log_level_err = 1;
int log_level_inf = 1;
int log_level_wrn = 1;
int log_level_0 = 0;
int log_level_1 = 0;
int log_level_2 = 0;
int log_level_3 = 0;
int log_level_4 = 0;
int log_level_5 = 0;
int log_level_6 = 0;
int log_level_7 = 0;
int log_level_8 = 0;
int log_level_9 = 0;

void printk(int ss, int level, const char *fmt, ...)
{
	va_list args;
	unsigned int i;
	char *leading_ss, *leading_lvl;

	if (printk_disabled_flag)
	  return;

	switch (ss) {
	case PR_SS_INI:
	  if (0 == log_type_ini)
		return;
	  leading_ss = "INI] ";
	  break;
	case PR_SS_FS:
	  if (0 == log_type_fs)
		return;
	  leading_ss = "FS] ";
	  break;
	case PR_SS_MM:
	  if (0 == log_type_mm)
		return;
	  leading_ss = "MM] ";
	  break;
	case PR_SS_PROC:
	  if (0 == log_type_proc)
		return;
	  leading_ss = "PROC] ";
	  break;
	case PR_SS_IRQ:
	  if (0 == log_type_irq)
		return;
	  leading_ss = "IRQ] ";
	  break;
	default:
	  return;
	}

	switch (level) {
	case PR_LVL_INF:
	  if (0 == log_level_inf)
		return;
	  leading_lvl = "[INFO-";
	  break;
	case PR_LVL_ERR:
	  if (0 == log_level_err)
		return;
	  leading_lvl = "[ERR-";
	  break;
	case PR_LVL_WRN:
	  if (0 == log_level_wrn)
		return;
	  leading_lvl = "[WRN-";
	  break;
	case PR_LVL_DBG0:
	  if (0 == log_level_0)
		return;
	  leading_lvl = "[DBG0-";
	  break;
	case PR_LVL_DBG1:
	  if (0 == log_level_1)
		return;
	  leading_lvl = "[DBG1-";
	  break;
	case PR_LVL_DBG2:
	  if (0 == log_level_2)
		return;
	  leading_lvl = "[DBG2-";
	  break;
	case PR_LVL_DBG3:
	  if (0 == log_level_3)
		return;
	  leading_lvl = "[DBG3-";
	  break;
	case PR_LVL_DBG4:
	  if (0 == log_level_4)
		return;
	  leading_lvl = "[DBG4-";
	  break;
	case PR_LVL_DBG5:
	  if (0 == log_level_5)
		return;
	  leading_lvl = "[DBG5-";
	  break;
	case PR_LVL_DBG6:
	  if (0 == log_level_6)
		return;
	  leading_lvl = "[DBG6-";
	  break;
	case PR_LVL_DBG7:
	  if (0 == log_level_7)
		return;
	  leading_lvl = "[DBG7-";
	  break;
	case PR_LVL_DBG8:
	  if (0 == log_level_8)
		return;
	  leading_lvl = "[DBG8-";
	  break;
	case PR_LVL_DBG9:
	  if (0 == log_level_9)
		return;
	  leading_lvl = "[DBG9-";
	  break;
	default:
	  return;
	}


	va_start (args, fmt);
	i = vsnprintf (print_buf, sizeof(print_buf),fmt, args);
	va_end (args);

	__put_char_k (leading_lvl, 8);
	__put_char_k (leading_ss, 8);
	__put_char_k (print_buf,i);

}

/* output to user screen directly*/
void printu(const char *fmt, ...)
{
  va_list args;
  unsigned int i;
  
  va_start (args, fmt);
  i = vsnprintf (print_buf, sizeof(print_buf),fmt, args);
  va_end (args);
  
  __put_char_u (print_buf,i);

#ifndef __ARCH_X86__
  uart0_tx_start();
#endif
}

/* print raw string to screen directly */
void prints(char *string, int length) {
  __put_char_u (string, length);
#ifndef __ARCH_X86__
  uart0_tx_start();
#endif
}


void print_memory_byte (unsigned long start, unsigned long end) {
  int row=0, i, j, tmp;
  unsigned char c;
  static unsigned char hex_sym[] = "0123456789abcdef";
  char lineContent[60] = {0};
  int lineIndex = 0;
  int length = 0;
  start = start & 0xfffffff0;
  end = (end + 0xf) & 0xfffffff0;
  length = end - start;
  
  printk(PR_SS_MM, PR_LVL_DBG9, "%s(): start = %x, end = %x, length = %x\n", __func__, start, end, length);
  if (start > end)
	return;
  printk(PR_SS_MM, PR_LVL_DBG9, "          00 11 22 33 44 55 66 77 88 99 aa bb cc dd ee ff \n");
  for (i=0; i < length; i++) {
	if (0 == i%16) {
	  // print line number
	  if (0 != lineContent[0]) {
		lineContent[lineIndex++] = '\n';		
		printk(PR_SS_MM, PR_LVL_DBG9, lineContent);
	  }
	  memset(lineContent, 0, 60);
	  lineIndex = 0;
	  tmp = start + (row << 4);
	  for (j = 0; j < 8; j++) {
		lineContent[lineIndex++] = hex_sym[((tmp & 0xf0000000) >> 28)];
		tmp = tmp << 4;
	  }
	  lineContent[lineIndex++] = ':';
	  lineContent[lineIndex++] = ' ';
	  row++;
	}
	c = ((unsigned char *)start)[i];
	// print first digit
	lineContent[lineIndex++] = hex_sym[(c&0x0f0) >> 4];
	lineContent[lineIndex++] = hex_sym[(c&0x0f)];
	lineContent[lineIndex++] = ' ';
  }
  if (0 != lineContent[0]) {
	lineContent[lineIndex++] = '\n';
	printk(PR_SS_MM, PR_LVL_DBG9, lineContent);
  }

}

/*
void debug(const char *fmt, ...)
{
	va_list args;
	unsigned int i;

	va_start (args, fmt);
	i = vsnprintf (print_buf, sizeof(print_buf),fmt, args);
	va_end (args);

	__put_char (print_buf,i);
}
*/

void test_printk(void){
	char *p="this is %s test";
	char c='H';
	int d=-256;
	int k=0;

	printk(0, 0, "test string :::	%s\ntest char ::: %c\ntest digit ::: %d\ntest X ::: %x\ntest unsigned ::: %u\ntest zero ::: %d\n",p,c,d,d,d,k);
}

