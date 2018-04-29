package com.kittycoder.easydemo;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by shucheng on 2018/4/19.
 * 09 根据excel内容生成插入sql
 */
public class ReadsheetToSql {

    static XSSFRow row;
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                new File("excel_template/studentData.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        StringBuffer sql = new StringBuffer();
        // 获取第0个sheet
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        // 创建sheet的行迭代器
        Iterator<Row> rowIterator = spreadsheet.iterator();
        // 只要行迭代器中有数据，则获取行中的数据
        int i = 0;
        while (rowIterator.hasNext()) {
            // 从行迭代器中获取行对象
            row = (XSSFRow) rowIterator.next();
            // 从行对象中获取单元格迭代器
            Iterator<Cell> cellIterator = row.cellIterator();
            // 只要单元格迭代器中有数据，则获取单元格中的数据
            if(i != 0) {
                sql.append("insert into student (name, age, sex, regtime) values (");
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    // 根据不同的单元格类型，得到不同类型的数值
                    int cellType = cell.getCellType();
                    switch (cellType) {
                        case Cell.CELL_TYPE_NUMERIC: // 数值型
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                // 参考   https://blog.csdn.net/juice_panda/article/details/52213588?locationNum=4
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                System.out.print(sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) + "\t\t");
                                sql.append("'"+sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()))+"',");
                            } else {
                                /*System.out.print(cell.getNumericCellValue() + "\t\t");
                                sql.append("'"+cell.getNumericCellValue()+"',");*/

                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                System.out.print(cell.getStringCellValue() + "\t\t");
                                sql.append("'"+cell.getStringCellValue()+"',");
                            }

                            break;
                        case Cell.CELL_TYPE_STRING: // 字符串型
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            sql.append("'"+cell.getStringCellValue()+"',");
                            break;
                    }
                }
                // 获取系统自带的换行符
                // 参考 https://blog.csdn.net/qq_27222801/article/details/53744670
                String separator = System.getProperty("line.separator");
                // 删除StringBuffer最后一个字符
                // 参考https://www.cnblogs.com/shaozhiheng/p/3661714.html
                sql.deleteCharAt(sql.length()-1);
                sql.append(");" + separator);
                System.out.println();
            }
            i++;
        }
        System.out.println(sql.toString());
        fis.close();
        // 将语句的执行结果拼接，存入文本文件中
        File file = new File("readsheetToSql.txt");
        FileOutputStream out = new FileOutputStream(file);
        out.write(sql.toString().getBytes("utf-8"));
        out.flush();
        out.close();
    }
}
