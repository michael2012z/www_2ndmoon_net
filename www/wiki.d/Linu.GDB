version=pmwiki-2.2.6 ordered=1 urlencoded=1
agent=Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.224 Safari/534.10
author=
charset=UTF-8
csum=
ctime=1292854491
host=112.2.252.147
name=Linu.GDB
rev=3
targets=
text=!!!!准备arm-linux-gdb，用于远程调试：%0a%0a# gdb源码下载：http://ftp.gnu.org/gnu/gdb/%0a%0a# 编译arm-linux-gdb\\%0a    指定交叉编译工具链的位置\\%0a    export PATH=$PATH:/usr/local/arm/3.4.1/bin\\%0a    开始配置和编译\\%0a    $cd gdb-6.6\\%0a    $./configure --target=arm-linux --prefix=/usr/local/arm/gdb -v \\%0a   $make%0a%0a# 编译gdbserver \\%0a    $cd gdb/gdbserver \\%0a    $./configure --target=arm-linux --host=arm-linux \\%0a      target含义同前,host指明编译生成的gdbserver运行在arm-linux上,前者没有设定host的原因是 \\%0a      arm-linux-gdb是在pc linux上运行的,就像arm-linux-gcc (很好理解的:)) \\%0a    $make CC=/usr/local/arm/3.4.1/bin/arm-linux-gcc %0a%0a----%0a%0a!!!!使用示例：%0a%0a# gdbserver是在目标板上运行的程序：\\%0a    $ gdbserver 192.168.1.200:7777 test%0a%0a# arm-linux-gdb是在主机上运行的程序：\\%0a    $ arm-linux-gdb test \\%0a    ... \\%0a    target remote 192.168.1.168:7777%0a%0a!!!!开始调试！
time=1292854627
author:1292854627=
diff:1292854627:1292854582:=5a6%0a> %0a6a8%0a> %0a8a11%0a> %0a
host:1292854627=112.2.252.147
author:1292854582=
diff:1292854582:1292854491:=16c16%0a%3c # 编译gdbserver \\%0a---%0a> # 编译gdbserver%0a21,22c21,22%0a%3c     $make CC=/usr/local/arm/3.4.1/bin/arm-linux-gcc %0a%3c %0a---%0a>     $make CC=/usr/local/arm/3.4.1/bin/arm-linux-gcc \\%0a> %0a35c35%0a%3c !!!!开始调试！%0a\ No newline at end of file%0a---%0a> 开始调试！%0a\ No newline at end of file%0a
host:1292854582=112.2.252.147
author:1292854491=
diff:1292854491:1292854491:=1,35d0%0a%3c !!!!准备arm-linux-gdb，用于远程调试：%0a%3c %0a%3c # gdb源码下载：http://ftp.gnu.org/gnu/gdb/%0a%3c %0a%3c # 编译arm-linux-gdb\\%0a%3c %0a%3c     指定交叉编译工具链的位置\\%0a%3c %0a%3c     export PATH=$PATH:/usr/local/arm/3.4.1/bin\\%0a%3c     开始配置和编译\\%0a%3c %0a%3c     $cd gdb-6.6\\%0a%3c     $./configure --target=arm-linux --prefix=/usr/local/arm/gdb -v \\%0a%3c    $make%0a%3c %0a%3c # 编译gdbserver%0a%3c     $cd gdb/gdbserver \\%0a%3c     $./configure --target=arm-linux --host=arm-linux \\%0a%3c       target含义同前,host指明编译生成的gdbserver运行在arm-linux上,前者没有设定host的原因是 \\%0a%3c       arm-linux-gdb是在pc linux上运行的,就像arm-linux-gcc (很好理解的:)) \\%0a%3c     $make CC=/usr/local/arm/3.4.1/bin/arm-linux-gcc \\%0a%3c %0a%3c ----%0a%3c %0a%3c !!!!使用示例：%0a%3c %0a%3c # gdbserver是在目标板上运行的程序：\\%0a%3c     $ gdbserver 192.168.1.200:7777 test%0a%3c %0a%3c # arm-linux-gdb是在主机上运行的程序：\\%0a%3c     $ arm-linux-gdb test \\%0a%3c     ... \\%0a%3c     target remote 192.168.1.168:7777%0a%3c %0a%3c 开始调试！%0a\ No newline at end of file%0a
host:1292854491=112.2.252.147
