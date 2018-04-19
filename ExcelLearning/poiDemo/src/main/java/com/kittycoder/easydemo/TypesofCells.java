package com.kittycoder.easydemo;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by shucheng on 2018/4/19.
 * 05 在电子表格中创建不同类型的单元格
 */
public class TypesofCells {

    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("单元格类型"); // 创建sheet（名为"单元格类型"）

        XSSFRow row = spreadsheet.createRow(2); // 创建第3行
        row.createCell(0).setCellValue("单元格类型"); // 给第3行的A列设置值
        row.createCell(1).setCellValue("单元格的值");// 给第3行的B列设置值

        row = spreadsheet.createRow(3); // 创建第4行
        row.createCell(0).setCellValue("set cell type BLANK"); // 给第4行的A列设置值
        row.createCell(1); // 创建一个空的单元格（第4行B列）

        row = spreadsheet.createRow(4); // 创建第5行
        row.createCell(0).setCellValue("set cell type BOOLEAN"); // 给第5行的A列设置值
        row.createCell(1).setCellValue(true); // 给第5行的B列设置值为true（布尔类型值）

        row = spreadsheet.createRow(5); // 创建第6行
        row.createCell(0).setCellValue("set cell type ERROR"); // 给第6行的A列设置值
        row.createCell(1).setCellValue(XSSFCell.CELL_TYPE_ERROR); // 给第6行的B列设置值（一个枚举值，为int类型）

        row = spreadsheet.createRow(6); // 创建第7行
        row.createCell(0).setCellValue("set cell type numeric"); // 给第7行的A列设置值
        row.createCell(1).setCellValue(new Date()); // 给第7行的B列设置值（date类型）

        row = spreadsheet.createRow(7); // 创建第8行
        row.createCell(0).setCellValue("set cell type numeric"); // 给第8行的A列设置值
        row.createCell(1).setCellValue(20); // 给第8行的B列设置值（数值类型）

        row = spreadsheet.createRow(8); // 创建第9行
        row.createCell(0).setCellValue("set cell type string"); // 给第9行的A列设置值
        row.createCell(1).setCellValue("A String"); // 给第9行的B列设置值（字符串类型）

        // 将数据通过文件流写入硬盘
        FileOutputStream out = new FileOutputStream(new File("typesofcells.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("文件写入成功！");
    }
}
