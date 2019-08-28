### jdk1.8安装shell脚本 
安装包jdk-8u161-linux-x64.tar.gz去官网（https://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html）下载 
懒得注册账号的，可以用这个下载账号（https://www.cnblogs.com/xiaostudy/p/9940167.html），感谢xiaostudy 

使用说明： 
1. 安装包和install.sh放在一个目录下 
2. 比如：install.sh放在/home/software下，先赋予执行权限（chmod +x install.sh），直接在该目录执行./install.sh 
3. 执行完成后需要手动执行下source /etc/profile才能让新设置的环境变量在当前shell环境中生效  
（因为shell脚本里写的source /etc/profile，只能让环境变量影响执行脚本新开的shell环境，一旦脚本执行完就失效了）  
4. 执行卸载操作 先赋予执行权限（chmod +x install.sh），然后执行./uninstall.sh  
（说明：此时手动执行source /etc/profile后，JAVA_HOME变量还是能看到，目前还未找到原因；除非你用xshell重新连一次或者重启linux）
