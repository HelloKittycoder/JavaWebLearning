package com.kittycoder.easydemo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by shucheng on 2018/4/19.
 * 02 检查Excel文件是否已经存在
 */
public class OpenWorkBook {
    public static void main(String[] args) throws Exception {
        File file = new File("openworkbook.xlsx");
        FileInputStream fip = new FileInputStream(file);
        // 获取工作簿实例
        XSSFWorkbook workbook = new XSSFWorkbook(fip);
        if (file.isFile() && file.exists()) {
            System.out.println("文件打开成功！");
        } else {
            System.out.println("文件打开失败！");
        }
    }
}
