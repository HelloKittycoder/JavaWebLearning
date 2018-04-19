package com.kittycoder.easydemo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * Created by shucheng on 2018/4/19.
 * 04 从电子表格中读取数据
 */
public class Readsheet {

    static XSSFRow row;
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                new File("WriteSheet.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // 获取第0个sheet
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        // 创建sheet的行迭代器
        Iterator<Row> rowIterator = spreadsheet.iterator();
        // 只要行迭代器中有数据，则获取行中的数据
        while (rowIterator.hasNext()) {
            // 从行迭代器中获取行对象
            row = (XSSFRow) rowIterator.next();
            // 从行对象中获取单元格迭代器
            Iterator<Cell> cellIterator = row.cellIterator();
            // 只要单元格迭代器中有数据，则获取单元格中的数据
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                // 根据不同的单元格类型，得到不同类型的数值
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC: // 数值型
                        System.out.print(
                           cell.getNumericCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING: // 字符串型
                        System.out.print(
                                cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            System.out.println();
        }
        fis.close();
    }
}
