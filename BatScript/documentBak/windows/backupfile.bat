@echo off
rem ---------------------------------------------------------------------------
rem window�ļ��б���
rem ---------------------------------------------------------------------------
rem ÿ����Ҫ�޸�original_BaseDir��original_DirName��backupFile_BaseDir

echo "================Start Back================"
rem 1.��Ҫ���ݵ��ļ���
set "original_BaseDir=D:\var\invest\InvestUploadDoc"
rem 2.�ļ�������
set "original_DirName=InvestUploadDoc"
rem 3.�����ļ����Ŀ¼
set "backupFile_BaseDir=D:\mybackup"
rem 4.�Ƿ�ɾ��30��ǰ�ľɱ����ļ���Ĭ������Ϊ���ǡ���Y��
set is_del_old=Y


rem ��������ļ��в����ڣ��򴴽�֮
if not exist "%backupFile_BaseDir%" md "%backupFile_BaseDir%"
rem ����ʱ���
set timevar=%time:~0,2%
if /i %timevar% LSS 10 (
	set timevar=0%time:~1,1%
)
set var=%date:~0,4%%date:~5,2%%date:~8,2%%timevar%%time:~3,2%%time:~6,2%

rem ���ݽű�����Ŀ¼
set "myAppPath=%cd%"
rem zip.exe����Ҫ�ͱ��ݽű���ͬһ���ļ��У�����Ŀ¼
rem set "zipApp=%myAppPath%\zip.exe"
set "zipApp=%myAppPath%\7z"

cd /d "%original_BaseDir%"&&cd ..
rem "%zipApp%" -r "%backupFile_BaseDir%\file_%var%.zip" "%original_DirName%\*"
call "%zipApp%" a "%backupFile_BaseDir%\file_%var%.zip" "%original_DirName%\*"

if /i "%is_del_old%"=="Y" (
	echo "====delete over 30 days back===="
	forfiles /p %backupFile_BaseDir%  /s /m  *.zip /d -30 /c "cmd /c del @path"
)
echo "================Start End================"