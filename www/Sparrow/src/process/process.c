#include <type.h>
#include <list.h>
#include "pid.h"
#include <process.h>
#include <string.h>
#include <printk.h>
#include "elf.h"
#include <exception.h>

extern unsigned long kernel_pgd;
extern struct task_struct *current_task;
extern struct sched_class *scheduler;
struct task_struct *init_kernel_task = NULL;

static struct task_struct *create_launch_kernel_task(char *name) {
  int pid;
  struct task_struct *task = NULL;
  pid = allocate_pid();
  if (pid < 0)
	return NULL;
  
  task = (struct task_struct *)kmalloc(sizeof(struct task_struct));

  if (NULL == task)
	return NULL;

  task->priority = 1;
  task->elf_file_name = (char *)kmalloc(strlen(name) + 1);
  memcpy(task->elf_file_name, name, strlen(name) + 1);
  task->sched_en.state = PROCESS_STATE_RUNNING;
  task->sched_en.blocked_pid = -1;
  task->sched_en.blocking_pid = -1;
  task->stack = (void *)&init_thread_union;

  task->pid = pid;

  printk(PR_SS_PROC, PR_LVL_DBG3, "%s process %d stack = %x\n", __func__, pid, task->stack);

  task->mm.pgd = kernel_pgd;

  enqueue_task(task, sched_enqueue_flag_new);

  return task;
}

void initialize_process() {
  initialize_pid();
  schedule_initialize();
  create_launch_kernel_task("*init");
  init_kernel_task = scheduler->pick_next_task();
  current_task = init_kernel_task;
  process_cleaner_init();
}


void arm_start_kernel_thread(void) __asm__("arm_start_kernel_thread");

int create_kernel_thread(int (*fn)(void *), char *name) {
  int pid;
  struct task_struct *task = NULL;
  struct pt_regs *regs = NULL;
  struct thread_info *thread = NULL;

  pid = allocate_pid();
  if (pid < 0)
	return pid;
  
  /* make task_struct */
  task = (struct task_struct *)kmalloc(sizeof(struct task_struct));

  if (NULL == task)
	return -1;

  task->priority = 1;
  task->elf_file_name = (char *)kmalloc(strlen(name) + 1);
  memcpy(task->elf_file_name, name, strlen(name) + 1);
  task->sched_en.state = PROCESS_STATE_READY;
  task->sched_en.blocked_pid = -1;
  task->sched_en.blocking_pid = -1;
  task->stack = (void *)kmalloc(PAGE_SIZE*2);

  task->pid = pid;

  printk(PR_SS_PROC, PR_LVL_DBG3, "%s process %d stack = %x\n", __func__, pid, task->stack);

  INIT_LIST_HEAD(&(task->mm.mmap.list));
  task->mm.pgd = (unsigned long)kmalloc(PAGE_SIZE * 4);
  memcpy((void *)task->mm.pgd, (void *)kernel_pgd, PAGE_SIZE * 4);
  printk(PR_SS_MM, PR_LVL_DBG7, "%s: pid = %x, mm.pgd = %x\n", __func__, pid, task->mm.pgd);
  printk(PR_SS_MM, PR_LVL_DBG7, "%s: *mm.pgd = %x\n", __func__, *((unsigned long *)task->mm.pgd));

  INIT_LIST_HEAD(&(task->sched_en.queue_entry));

  /* populate initial content of stack */
  regs = task_pt_regs(task);
  arm_create_kernel_thread(fn, NULL, regs);
  regs->ARM_r0 = 0;
  regs->ARM_sp = 0; /* this is user-space sp, don't need to set it */
	
  thread = task_thread_info(task);
  thread->task = task;
  thread->cpu_domain = arm_calc_kernel_domain();
  thread->tp_value = 0;
  thread->cpu_context.sp = (unsigned long)regs;
  thread->cpu_context.pc = (unsigned long)arm_start_kernel_thread;

  enqueue_task(task, sched_enqueue_flag_new);

  return pid;
}

int create_user_thread(int (*fn)(char *), char *elf_file_name, char **parameters) {
  int pid;
  struct task_struct *task = NULL;
  struct pt_regs *regs = NULL;
  struct thread_info *thread = NULL;
  int i = 0;

  pid = allocate_pid();
  if (pid < 0)
	return pid;
  
  /* make task_struct */
  task = (struct task_struct *)kmalloc(sizeof(struct task_struct));

  if (NULL == task)
	return -1;

  task->priority = 4;
  task->elf_file_name = NULL;
  task->sched_en.state = PROCESS_STATE_READY;
  task->sched_en.blocked_pid = -1;
  task->sched_en.blocking_pid = -1;
  task->stack = (void *)kmalloc(PAGE_SIZE*2);

  task->pid = pid;

  printk(PR_SS_PROC, PR_LVL_DBG3, "%s process %d stack = %x\n", __func__, pid, task->stack);

  INIT_LIST_HEAD(&(task->mm.mmap.list));
  task->mm.pgd = (unsigned long)kmalloc(PAGE_SIZE * 4);
  memcpy((void *)task->mm.pgd, (void *)kernel_pgd, PAGE_SIZE * 4);
  printk(PR_SS_MM, PR_LVL_DBG7, "%s: pid = %x, mm.pgd = %x\n", __func__, pid, task->mm.pgd);
  printk(PR_SS_MM, PR_LVL_DBG7, "%s: *mm.pgd = %x\n", __func__, *((unsigned long *)task->mm.pgd));

  task->elf_file_name = (char *)kmalloc(strlen(elf_file_name) + 1);
  memcpy(task->elf_file_name, elf_file_name, strlen(elf_file_name) + 1);
  for(i = 0; i < 4; i++)
	if (parameters[i]) {
	  task->parameters[i] = (char *)kmalloc(strlen(parameters[i]) + 1);
	  memcpy(task->parameters[i], parameters[i], strlen(parameters[i]) + 1);	  
	}
	  

  INIT_LIST_HEAD(&(task->sched_en.queue_entry));

  /* populate initial content of stack */
  regs = task_pt_regs(task);
  arm_create_user_thread(fn, task->elf_file_name, regs);
  regs->ARM_r0 = 0;
  regs->ARM_sp = 0; /* this is user-space sp, don't need to set it */
	
  thread = task_thread_info(task);
  thread->task = task;
  thread->cpu_domain = arm_calc_kernel_domain();
  thread->tp_value = 0;
  thread->cpu_context.sp = (unsigned long)regs;
  thread->cpu_context.pc = (unsigned long)arm_start_kernel_thread;

  enqueue_task(task, sched_enqueue_flag_new);

  return pid;
}

int execute_binary(struct task_struct *task, struct file *filep) {
  printk(PR_SS_PROC, PR_LVL_DBG3, "%s: current process: %d\n", __func__, task->pid);
  return load_elf_binary(filep, &(task->regs), &(task->mm));
}

void destroy_process(struct task_struct *task) {
  free_pid(task->pid);
  kfree(task);
}

void run_kernel_process(char *init_filename)
{
  arm_kernel_execve(init_filename, NULL, NULL);
}


/* process cleaner */
static struct list_head* zombie_queue=NULL;

void destroy_user_thread(struct task_struct *task) {
    list_add_tail(&task->sched_en.queue_entry, zombie_queue);
}

void process_cleaner_init() {
  zombie_queue = kmalloc(sizeof(struct list_head));
  INIT_LIST_HEAD(zombie_queue);
}

int process_cleaner(void *unused) {
  while(1) {
	struct list_head *first, *head;
	struct sched_entity *en;
	struct task_struct* task;
	while(!list_empty(zombie_queue)) {
	  int i;
	  struct vm_area_struct *vma;

	  first = zombie_queue->next;
	  list_del(first);
	  en = container_of(first, struct sched_entity, queue_entry);
	  task = container_of(en, struct task_struct, sched_en);
	  /* delete task data */
	  free_pid(task->pid);
	  if (task->stack)
		kfree(task->stack);
	  if (task->elf_file_name)
		kfree(task->elf_file_name);
	  for (i = 0; i < 4; i++)
		if (task->parameters[i])
		  kfree(task->parameters[i]);

	  /* free user space memory and vma */
	  head = &(task->mm.mmap.list);
	  while(!list_empty(head)) {
		first = head->next;
		list_del(first);
		vma = container_of(first, struct vm_area_struct, list);
		if (0 != vma->memory)
		  kfree((void *)vma->memory);
		kfree(vma);
	  }

	  /* free task struct */
	  kfree(task);
	}
	exception_disable();
	schedule();
	exception_enable();
  }
  return -1;
}
