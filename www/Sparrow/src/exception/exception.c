#include <type.h>
#include <memory.h>
#include <exception.h>
#include <string.h>
#include <printk.h>


void exception_disable(void) {
  asm("cpsid i	@ __cli" : : : "memory", "cc");
}

void exception_enable(void) {
  asm("cpsie i	@ __sti" : : : "memory", "cc");
}

void exception_init() {
	unsigned long vectors = EXCEPTION_BASE;
	extern char __vectors_start[], __vectors_end[];
	extern char __stubs_start[], __stubs_end[];

	printk(PR_SS_IRQ, PR_LVL_INF, "exception_init()\n");
	printk(PR_SS_IRQ, PR_LVL_INF, "vectors = %x\n", vectors);
	printk(PR_SS_IRQ, PR_LVL_INF, "__vectors_start = %x, __vectors_end = %x\n", __vectors_start, __vectors_end);
	printk(PR_SS_IRQ, PR_LVL_INF, "__stubs_start = %x, __stubs_end = %x\n", __stubs_start, __stubs_end);

	/*
	 * Copy the vectors, stubs and kuser helpers (in entry-armv.S)
	 * into the vector page, mapped at 0xffff0000, and ensure these
	 * are visible to the instruction stream.
	 */
	memcpy((void *)vectors, __vectors_start, __vectors_end - __vectors_start);
	memcpy((void *)vectors + 0x200, __stubs_start, __stubs_end - __stubs_start);

	printk(PR_SS_IRQ, PR_LVL_INF, "%s: finish\n", __func__);
}

