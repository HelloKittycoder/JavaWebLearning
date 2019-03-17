log4j  
1.是由apache推出的开源日志处理的类库  
2.为什么需要日志：  
（1）在项目中编写System.out.println()，输出到控制台；当项目发布到tomcat后，没有控制台  
（在命令行界面才能看见），不容易观察一些输出结果  
（2）log4作用：不仅能把内容输出到控制台，还能把内容输出到文件中，便于观察结果  
3.使用步骤：  
（1）导入log4j-xxx.jar  
（2）在src下新建log4j.properties（路径和名称不允许改变），这个是在eclipse下；  
如果用idea，就在resources下创建properties文件  
4.log4j日志级别
（1）fatal（致命错误）>error（错误）>warn（警告）>info（普通信息）>debug（调试信息）
（2）在log4j.properties的第一行中控制整体日志输出级别  
log4j.rootCategory=INFO, CONSOLE  
5.log4j输出目的地
（1）在第一行中控制输出目的地  
CONSOLE, LOGFILE（这个表示向控制台和文件中输出）  
6.pattern中常用的几个表达式  
参考链接：https://blog.csdn.net/hello_word2/article/details/79295344
（1）%C 包名+类名  
（2）%d{YYYY-MM-dd HH:mm:ss} 时间  
（3）%L 行号  
（4）%m 信息  
（5）%n 换行
