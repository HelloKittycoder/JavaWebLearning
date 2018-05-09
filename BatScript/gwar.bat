rem @echo off
rem author:luoshucheng
rem 写法一：将webapps下的项目拖至该文件上即可在该项目旁边生成war包
rem 如student文件夹
rem 写法二：gwar "D:\otherSoftware\tomcatServer\apache-tomcat-7.0.78A\webapps\student"
rem gwar "D:\Tomcat\tomcat 7.0.70\webapps\student"

cd /d %~dp1&&jar -cvf %~nx1.war -C %~nx1\ .&&exit



rem 之前考虑过的另外一种生成方法（这种写法对带空格的路径不支持）
rem cd /d %~dp1&&jar -cvf %~nx1.war -C "%~dp1"%~nx1\ .