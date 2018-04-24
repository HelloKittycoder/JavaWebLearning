package com.kittycoder.easydemo;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by shucheng on 2018/4/24.
 * 07 字体和字体样式
 */
public class FontStyle {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Fontstyle"); // 给workbook创建一个名为“Fontstyle”的sheet页

        XSSFFont font = workbook.createFont(); // 创建字体
        font.setFontHeightInPoints((short) 30); // 设置字体高度为30个像素点
        font.setFontName("IMPACT"); // 设置字体为IMPACT
        font.setItalic(true); // 设置字体为斜体
        font.setColor(HSSFColor.BRIGHT_GREEN.index);
        // 将设置好的字体放到style中
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        XSSFRow row = spreadsheet.createRow(2); // 创建第3行
        XSSFCell cell = row.createCell(1); // 创建第3行B列
        cell.setCellValue("Font Style"); // 给单元格设置一个字符串“Font Style”
        cell.setCellStyle(style);

        FileOutputStream out = new FileOutputStream(new File("fontstyle.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("文件写入成功！");
    }
}
