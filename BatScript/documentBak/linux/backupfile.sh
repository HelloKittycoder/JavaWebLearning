#!/bin/sh
# ---------------------------------------------------------------------------
# linux文件夹备份
# ---------------------------------------------------------------------------
#每次需要修改original_BaseDir和backBaseDir


#1.需要备份的目录
original_BaseDir="/root/demo/InvestUploadDoc/"
#2.文件夹名称
original_DirName="InvestUploadDoc"
#3.备份文件存放目录
backupFile_BaseDir="/root/demo/backup/"
#4.是否删除30天前的旧备份文件，默认设置为“是”（Y）
is_del_old="Y"

#如果备份文件存放目录不存在，则创建之
if [ ! -d "$backupFile_BaseDir" ]; then
  mkdir -p "$backupFile_BaseDir"
fi

#压缩并删除原文件
cd "$original_BaseDir"&&cd ..
tar -zcvf "$backupFile_BaseDir"file_$(date +%Y%m%d%H%M%S).tar.gz $original_DirName/*

#删除文件夹下超过30天的文件
if [ "$is_del_old" == "Y" ]; then
	find "$backupFile_BaseDir" -mtime +30 -name "*.sql" -exec rm -rf {} \;
fi