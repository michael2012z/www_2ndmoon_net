#include <type.h>
#include <linkage.h>
#include <printk.h>

long sys_print(char *string, int length) {
  printk(PR_SS_IRQ, PR_LVL_DBG6, "%s: string = %x, length = %x\n", __func__, string, length);
  if ((NULL != string)&&(0 != length))
	prints(string, length);
  else
	printk(PR_SS_IRQ, PR_LVL_DBG6, "%s: invalid string from user space\n", __func__);	

  return 0;
}

long sys_inputc() {
  printk(PR_SS_IRQ, PR_LVL_ERR, "%s: not implemented\n", __func__);
  return 0 ;
}
