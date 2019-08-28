#!/bin/bash

# 卸载jdk1.8
echo "Starting to uninstall jdk1.8 ..."
echo "=============Deleting install directory=============="
rm -rf /usr/local/java
echo "=============Deleting Environment Variables=============="
sudo sed -i '/#java environment variable start/,/#java environment variable end/d' /etc/profile
echo "=============Deleting Soft Link=============="
rm -rf /usr/bin/java
rm -rf /usr/bin/javac
echo "Complete uninstall jdk1.8!"
