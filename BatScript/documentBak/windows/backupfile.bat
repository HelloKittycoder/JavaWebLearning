@echo off
rem ---------------------------------------------------------------------------
rem window文件夹备份
rem ---------------------------------------------------------------------------
rem 每次需要修改original_BaseDir、original_DirName和backupFile_BaseDir

echo "================Start Back================"
rem 1.需要备份的文件夹
set "original_BaseDir=D:\var\invest\InvestUploadDoc"
rem 2.文件夹名称
set "original_DirName=InvestUploadDoc"
rem 3.备份文件存放目录
set "backupFile_BaseDir=D:\mybackup"
rem 4.是否删除30天前的旧备份文件，默认设置为“是”（Y）
set is_del_old=Y


rem 如果备份文件夹不存在，则创建之
if not exist "%backupFile_BaseDir%" md "%backupFile_BaseDir%"
rem 生成时间戳
set timevar=%time:~0,2%
if /i %timevar% LSS 10 (
	set timevar=0%time:~1,1%
)
set var=%date:~0,4%%date:~5,2%%date:~8,2%%timevar%%time:~3,2%%time:~6,2%

rem 备份脚本所在目录
set "myAppPath=%cd%"
rem zip.exe（需要和备份脚本在同一个文件夹）所在目录
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