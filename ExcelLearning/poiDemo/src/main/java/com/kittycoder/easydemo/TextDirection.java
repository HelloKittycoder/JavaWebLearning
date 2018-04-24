package com.kittycoder.easydemo;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by shucheng on 2018/4/24.
 * 08 文字方向
 */
public class TextDirection {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Text direction"); // 给workbook创建一个名为“Text direction”的sheet页

        // 0度旋转角
        XSSFCellStyle myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 0);
        XSSFRow row = spreadsheet.createRow(2); // 创建第3行
        XSSFCell cell = row.createCell(1); // 创建第3行B列（第2列）
        cell.setCellValue("0D angle");
        cell.setCellStyle(myStyle);

        // 30度旋转角
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 30);
        cell = row.createCell(3); // 创建第3行第4列
        cell.setCellValue("30D angle");
        cell.setCellStyle(myStyle);

        // 90度旋转角
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 90);
        cell = row.createCell(5); // 创建第3行第6列
        cell.setCellValue("90D angle");
        cell.setCellStyle(myStyle);

        // 120度旋转角
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 120);
        cell = row.createCell(7); // 创建第3行第8列
        cell.setCellValue("120D angle");
        cell.setCellStyle(myStyle);

        // 270度旋转角
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 270);
        cell = row.createCell(9); // 创建第3行第10列
        cell.setCellValue("270D angle");
        cell.setCellStyle(myStyle);

        // 360度旋转角
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 360);
        cell = row.createCell(11); // 创建第3行第12列
        cell.setCellValue("360D angle");
        cell.setCellStyle(myStyle);

        FileOutputStream out = new FileOutputStream(new File("textdirection.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("文件写入成功！");
    }
}
