package com.kittycoder.student.bo;

import com.kittycoder.student.pojo.Student;
import org.junit.Test;

/**
 * Created by shucheng on 2020/1/19 22:15
 */
public class StudentBoTest {

    private StudentBo studentBo = new StudentBo();

    @Test
    public void testQueryAllStudent() {
        System.out.println(studentBo.queryAllStudent());
    }

    @Test
    public void testInsertStudent() {
        Student s = new Student();
        s.setName("李四");
        s.setAge(20);
        studentBo.insertStudent(s);
    }

    @Test
    public void testUpdateStudent() {
        Student s = new Student(2, "王五", 30);
        studentBo.updateStudent(s);
    }

    @Test
    public void testDeleteStudent() {
        studentBo.deleteStudentById(2);
    }
}
