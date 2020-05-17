package com.kittycoder.studentMgmt.service.impl;

import com.kittycoder.studentMgmt.po.Student;
import com.kittycoder.studentMgmt.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by shucheng on 2018/4/27.
 * 运行这个单元测试时，
 * 先到mysql数据库中手动执行下init.sql，设置好初始状态，
 * 然后从上到下依次执行
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
/*@ContextConfiguration(locations = {"classpath:applicationContext.xml",
        "classpath:applicationContext-service.xml"})*/
public class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void findAll() throws Exception {
        Student student = new Student();
        List<Student> studentList = studentService.findAll(student);
        System.out.println(studentList);
    }

    @Test
    public void findById() throws  Exception {
        Student student = studentService.findById(1);
        System.out.println(student);
    }

    @Test
    public void insertStudent() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date =dateFormat.parse("1993-01-01");
        Student student = new Student(0, "王五", 30, "男", date);
        studentService.insertStudent(student);
        System.out.println("数据插入成功");
    }

    @Test
    public void  updateStudent() throws Exception {
        Student student = new Student(3, "王五3", 33, "男", new Date(System.currentTimeMillis()));
        studentService.updateStudent(student);
        System.out.println("数据修改成功");
    }

    @Test
    public void deleteStudent() throws Exception {
        studentService.deleteStudent(3);
        System.out.println("数据删除成功");
    }
}