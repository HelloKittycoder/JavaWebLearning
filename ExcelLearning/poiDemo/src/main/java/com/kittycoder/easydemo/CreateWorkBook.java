package com.kittycoder.easydemo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by shucheng on 2018/4/19.
 * 01 创建Excel文件
 */
public class CreateWorkBook {
    public static void main(String[] args) {
        // 创建空白工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            // 根据文件名生成指定文件流
            FileOutputStream out = new FileOutputStream(
                    new File("createworkbook.xlsx"));
            // 对文件进行写操作，在硬盘上生成文件
            workbook.write(out);
            System.out.println("文件写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
