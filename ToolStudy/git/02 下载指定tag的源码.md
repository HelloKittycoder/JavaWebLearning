### 下载指定tag的源码
概念说明：tag相当于一个指定时点的代码快照，是无法进行修改的  
实际操作应该是在远程建立一个对应tag的分支，然后在该分支上进行开发

例如：我现在要下载camunda-bpm-platform（https://github.com/camunda/camunda-bpm-platform.git）tag为7.10.0的源码  
操作步骤为：  
1. 先将camunda官方的bpm-platform的源码fork到自己的仓库中，现在我的地址是https://github.com/HelloKittycoder/camunda-bpm-platform.git  
2. 在本地新建一个文件夹camunda-bpm-platform，用来存放git仓库代码  
3. 进入camunda-bpm-platform文件夹，执行命令  
（1）git init #初始化git仓库  
（2）git remote add origin https://github.com/HelloKittycoder/camunda-bpm-platform.git #添加远程仓库  
（3）git fetch #从远程仓库获取必要信息，并不会真正把源码下载下来（fetch和pull的区别：https://www.cnblogs.com/qiu-Ann/p/7902855.html）  
这个时候会遇到一个问题：filename too long，解决方法为：（参考链接：https://blog.csdn.net/u012262450/article/details/82982452）  
git config –global core.longpaths true  #关于是否要用global，可以参考https://blog.csdn.net/u010999809/article/details/89377911  
（4）git checkout 7.10.0 #获取tag名称为7.10.0的源码以及版本信息  
如果该tag无法checkout下来，则需要先执行git fetch --tags，然后再checkout该tag（参考https://www.jianshu.com/p/683da9248322）  
（5）git tag #查看远程仓库中的所有tag  
（6）A.git branch v7.10.0 7.10.0 #在本地仓库创建一个和tag名称相同的分支  
说明：branch后面第一个参数是版本名称，第二个参数是tag名称，如果打了引号也会算在名称里  
B.git checkout v7.10.0 #切换到本地新创建的分支  
C.git push origin v7.10.0 #把本地创建的分支提交到远程仓库  
（参考链接：https://blog.csdn.net/linjcai/article/details/80887335）  


有关分支的相关操作补充：  
参考链接： https://blog.csdn.net/DinnerHowe/article/details/79082769  
git branch -a #查看本地的所有分支，命令行中同时还会显示当前仓库所处的分支（会标*号显示）  
[拉取指定分支的代码](https://blog.csdn.net/zuofanxiu/article/details/83308500)  
[更改分支名称](https://www.cnblogs.com/wangzhichao/p/git.html)  

其他参考链接：  
https://blog.csdn.net/ligang2585116/article/details/83035205  
https://blog.csdn.net/ligang2585116/article/details/46468709  
https://www.cnblogs.com/thammer/p/7291918.html
