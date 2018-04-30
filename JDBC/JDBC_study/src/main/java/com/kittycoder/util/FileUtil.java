package com.kittycoder.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by shucheng on 2018/4/30.
 */
public class FileUtil {

    /**
     * 以行为单位读取文件，一次读取一整行
     * 参考：https://blog.csdn.net/zs520ct/article/details/72231674
     * @param fileName
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("====以行为单位读取文件，一次读取一整行====");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件测试
     * @param args
     */
    public static void main(String[] args) {
        readFileByLines("src/main/resources/sql/init.sql");
    }
}
