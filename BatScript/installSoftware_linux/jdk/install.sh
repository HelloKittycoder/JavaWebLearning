#!/bin/bash

# 安装jdk1.8
echo "Starting to install jdk1.8 ..."
echo "=============Decompressing install package=============="
mkdir -p /usr/local/java && tar -zxf jdk-8u161-linux-x64.tar.gz -C /usr/local/java
echo "=============Generating Environment Variables=============="
sudo sh -c "cat envar/javaEnvar.txt >> /etc/profile"
source /etc/profile
echo "=============Creating Soft Link=============="
cd /usr/bin
ln -s -f $JAVA_HOME/bin/java
ln -s -f $JAVA_HOME/bin/javac
echo "Complete install jdk1.8!"
