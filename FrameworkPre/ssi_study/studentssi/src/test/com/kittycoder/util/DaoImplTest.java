package com.kittycoder.util;

import com.kittycoder.po.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by shucheng on 2018/4/25.
 */
public class DaoImplTest {

    private Dao dao;

    @Before
    public void setUp() throws Exception {
        // 读取配置文件
        String conf = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        // 获取容器中的bean组件对象
        dao = (Dao) ac.getBean("daoImpl");
    }

    @Test
    public void findAll() throws Exception {
        List<Student> studentList = dao.findAll();
        System.out.println(studentList);
    }

    @Test
    public void findById() throws Exception {
        Student student = dao.findById(1);
        System.out.println(student);
    }

    @Test
    public void insertStudent() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2016-10-20");
        Student student = new Student(0, "张三", 10, "男", date);
        dao.insertStudent(student);
        System.out.println("数据插入成功");
    }

    @Test
    public void updateStudent() throws Exception {
        Student student = new Student(1, "张三", 10, "男", new Date(System.currentTimeMillis()));
        dao.updateStudent(student);
        System.out.println("数据更新成功");
    }

    @Test
    public void deleteStudent() throws Exception {
        dao.deleteStudent(1);
        System.out.println("数据删除成功");
    }
}
