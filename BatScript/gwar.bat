rem @echo off
rem author:luoshucheng
rem д��һ����webapps�µ���Ŀ�������ļ��ϼ����ڸ���Ŀ�Ա�����war��
rem ��student�ļ���
rem д������gwar "D:\otherSoftware\tomcatServer\apache-tomcat-7.0.78A\webapps\student"
rem gwar "D:\Tomcat\tomcat 7.0.70\webapps\student"

cd /d %~dp1&&jar -cvf %~nx1.war -C %~nx1\ .&&exit



rem ֮ǰ���ǹ�������һ�����ɷ���������д���Դ��ո��·����֧�֣�
rem cd /d %~dp1&&jar -cvf %~nx1.war -C "%~dp1"%~nx1\ .