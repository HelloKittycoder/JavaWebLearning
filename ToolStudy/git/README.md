git学习  
一、准备工作
1.工具下载：windows下操作git，可下载git for windows  

二、命令学习  
（参考链接：https://www.cnblogs.com/justuntil/p/8966383.html）  
1.初始化一个本地仓库 init
git init  
这时会在当前目录下生成.git的隐藏文件夹

2.创建文件夹，添加文件  
放入需要提交的东西。这步操作，不管是在命令行还是在可视化界面下操作都无所谓  

3.标记哪些文件是需要提交的 add,rm
注意：空文件夹是不会被提交上去的，里面必须要有文件才能把该文件夹提交上去  
比如：我在当前文件夹下放了mybatis01文件夹，里面是我的项目相关的文件，我要把  
该文件夹以及子文件夹下的所有内容提交上去 git add mybatis01  
假如我本地仓库有个文件夹弄错了，需要把它删掉 git rm mybatis01

4.查看当前仓库状态 status
git status 该命令会显示当前目录下的一些变动，比如哪些文件夹放到了本地仓库目录  
下，但是没有标记提交；哪些文件被删除了，但是没有标记提交

5.提交到本地仓库 commit
git commit -m 'some comments'  
如果创建git文件夹时提供的信息不完善，这时会提示"Please tell me who you are"  
这时需要填下邮箱，用户名（比如：在git上注册了aaa@qq.com，用户名为zhangsan）  
执行命令：
git config --global user.email "aaa@qq.com"  
git config --global user.name "zhangsan"  
参考链接：https://blog.csdn.net/qq_26540999/article/details/53445589  

6.将代码从本地仓库推到远程仓库的master分支上
提交前可以先查看下本地仓库所处的分支（git branch）  
git push origin master  
如果第一次安装使用，会要你输入用户名和密码（就是你在github注册时填写的邮箱和  
密码），正确录入后就推送成功了
