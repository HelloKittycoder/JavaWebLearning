package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import net.sf.excelutils.ExcelUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2018/4/24.
 * ExcelUtils模板导出示例
 * 参考：http://excelutils.sourceforge.net/
 */
public class ExcelReportTest {

    public static void main(String[] args) {
        try {
            // 准备数据
            List<Student> studentList = new ArrayList<Student>();
            Student student = new Student();
            student.setSid("101");
            student.setSname("张三");
            student.setSphone("13101230123");
            student.setSaddress("北京");
            studentList.add(student);

            student = new Student();
            student.setSid("102");
            student.setSname("李四");
            student.setSphone("15101230123");
            student.setSaddress("上海");
            studentList.add(student);

            student = new Student();
            student.setSid("103");
            student.setSname("王五");
            student.setSphone("17101230123");
            student.setSaddress("广州");
            studentList.add(student);

            student = new Student();
            student.setSid("104");
            student.setSname("赵六");
            student.setSphone("18101230123");
            student.setSaddress("深圳");
            studentList.add(student);

            ExcelUtils.addValue("studentList", studentList);

            String projectPath = System.getProperty("user.dir");
            String templatePath = projectPath + "\\template\\studentInfo.xls"; // 模版文件路径
            File outputFile = new File("导出学生信息表.xls");
            ExcelUtils.export(templatePath, new FileOutputStream(outputFile));
            System.out.println("文件导出成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
