package com.kittycoder.easydemo;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by shucheng on 2018/4/19.
 * 06 单元格样式
 */
public class CellStyle {

    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("cellstyle");

        XSSFRow row = spreadsheet.createRow(1); // 创建第2行
        row.setHeight((short) 800);

        XSSFCell cell = (XSSFCell) row.createCell((short)1); // 创建第2行B列
        cell.setCellValue("test of merging"); // 测试单元格合并
        spreadsheet.addMergedRegion(new CellRangeAddress(
                1, // 起始行
                1, // 结束行
                1, // 起始列
                4 // 结束列
        )); // 从2行2列至2行5列（即2B至2）的区域做合并操作

        // 设置行高
        row = spreadsheet.createRow(5); // 创建第6行
        cell = row.createCell(0); // 创建第6行A列
        row.setHeight((short)800); // 设置行高为800

        // 单元格对齐（top left alignment）
        XSSFCellStyle style1 = workbook.createCellStyle();
        spreadsheet.setColumnWidth(0, 8000); // 将电子表格A列的宽度设置为8000
        style1.setAlignment(XSSFCellStyle.ALIGN_LEFT); // 水平居左
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP); // 垂直居上

        row = spreadsheet.createRow(6); // 创建第7行
        cell = row.createCell(1); // 创建第7行B列
        cell.setCellValue("Top Left");
        cell.setCellStyle(style1);
        row.setHeight((short) 800); // 设置第7行的行高为800

        // 设置单元格内容居中的样式
        XSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 设置单元格水平居中
        style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 设置单元格垂直居中
        row = spreadsheet.createRow(7); // 创建第8行
        cell = (XSSFCell)row.createCell(2); // 创建第8行C列
        cell.setCellValue("Center Aligned"); // 在单元格中放入字符串“Center Aligned”
        cell.setCellStyle(style2); // 给单元格应用样式style2
        row.setHeight((short)800); // 设置第8行的行高为800

        // 设置单元格内容底部居右
        XSSFCellStyle style3 = workbook.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style3.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
        row = spreadsheet.createRow(8); // 创建第9行
        cell = row.createCell(3); // 创建第9行D列
        cell.setCellValue("Bottom Right");
        cell.setCellStyle(style3);
        spreadsheet.setColumnWidth(3, 8000); // 将电子表格D列的宽度设置为8000
        row.setHeight((short)1000);

        // 设置单元格内容水平垂直均为两端对齐
        XSSFCellStyle style4 = workbook.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
        style4.setAlignment(XSSFCellStyle.VERTICAL_JUSTIFY);
        row = spreadsheet.createRow(9); // 创建第10行
        cell = row.createCell(3); // 创建第10行D列
        cell.setCellValue("Justified Alignment");
        cell.setCellStyle(style4);
        row.setHeight((short) 1000);

        // 设置单元格的边框线颜色
        row = spreadsheet.createRow(10); // 创建第11行
        row.setHeight((short) 800); // 设置行高为800
        cell = row.createCell(1); // 创建第11行B列
        cell.setCellValue("BORDER");
        XSSFCellStyle style5 = workbook.createCellStyle();
        style5.setBorderBottom(XSSFCellStyle.BORDER_THICK); // 设置底边框的线宽
        style5.setBottomBorderColor(IndexedColors.BLUE.getIndex()); // 设置底边框的颜色
        style5.setBorderLeft(XSSFCellStyle.BORDER_DOUBLE); // 设置左边框为双线
        style5.setLeftBorderColor(IndexedColors.GREEN.getIndex()); // 设置左边框的颜色
        style5.setBorderRight(XSSFCellStyle.BORDER_HAIR); // 设置右边框为点线（dot border）
        style5.setRightBorderColor(IndexedColors.RED.getIndex()); // 设置右边框的颜色
        style5.setBorderTop(XSSFCellStyle.BIG_SPOTS); // 设置上边框为粗点线（large spots）
        style5.setTopBorderColor(IndexedColors.CORAL.getIndex()); // 设置上边框的颜色
        cell.setCellStyle(style5);

        // 设置单元格的填充色
        row = spreadsheet.createRow(11); // 创建第12行
        cell = row.createCell(1); // 创建第12行A列
        cell.setCellValue("FILL BACKGROUND/FILL PATTERN");
        XSSFCellStyle style6 = workbook.createCellStyle();
        style6.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index); // 设置填充背景色（柠檬雪纺色）
        style6.setFillPattern(XSSFCellStyle.LESS_DOTS); // 设置填充形式为稀疏点
        style6.setAlignment(XSSFCellStyle.ALIGN_FILL); // 填充后水平对齐
        spreadsheet.setColumnWidth(1, 8000); // 设置B列的列宽为8000
        cell.setCellStyle(style6);

        // 设置单元格的前景色
        row = spreadsheet.createRow(12); // 创建第13行
        cell = row.createCell((short)1); // 创建13行B列
        cell.setCellValue("FILL FOREGROUND/FILL PATTERN");
        XSSFCellStyle style7 = workbook.createCellStyle();
        style7.setFillForegroundColor(HSSFColor.BLUE.index); //　设置填充前景色（蓝色）
        style7.setFillPattern(XSSFCellStyle.LESS_DOTS); // 设置填充形式为稀疏点
        style7.setAlignment(XSSFCellStyle.ALIGN_FILL); // 填充后水平对齐
        cell.setCellStyle(style7);

        // 将数据通过文件流写入硬盘
        FileOutputStream out = new FileOutputStream(new File("cellstyle.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("文件写入成功！");

    }
}
