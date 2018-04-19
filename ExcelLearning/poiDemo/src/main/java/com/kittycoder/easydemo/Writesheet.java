package com.kittycoder.easydemo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by shucheng on 2018/4/19.
 * 03 将数据写入电子表格
 */
public class Writesheet {
    public static void main(String[] args) throws Exception {
        // 创建空白工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建空sheet
        XSSFSheet spreadsheet = workbook.createSheet("学生信息表");
        // 创建行对象
        XSSFRow row;
        // 准备需要写入的数据
        Map<String, Object[]> studentInfo = new TreeMap<String, Object[]>();
        studentInfo.put("1", new Object[]{
                "学生id", "学生姓名", "学生住址"});
        studentInfo.put("2", new Object[]{
                "s01", "张三", "北京"});
        studentInfo.put("3", new Object[]{
                "s02", "李四", "上海"});
        studentInfo.put("4", new Object[]{
                "s03", "王五", "广州"});
        studentInfo.put("5", new Object[]{
                "s04", "赵六", "深圳"});
        studentInfo.put("6", new Object[]{
                "s05", "钱七", "珠海"});
        // 迭代每个数据，并写入sheet中
        Set<String> keyid = studentInfo.keySet(); // map中的每个键
        int rowid = 0; // 行的初始计数是从0开始
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++); // map中每个对应的键值对创建一个行
            Object[] objectArr = studentInfo.get(key); // 通过map中的键获得需要插入的数据
            int cellid = 0; // 单元格的初始计数是从0开始
            for (Object obj : objectArr) { // 给每个单元格插入具体内容
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
        // 将workbook通过文件流操作写入硬盘
        FileOutputStream out = new FileOutputStream(
                new File("WriteSheet.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("文件写入成功！");
    }
}
