package com.kittycoder.action;

import com.kittycoder.po.Student;
import com.kittycoder.util.Dao;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by shucheng on 2018/4/25.
 */
public class StudentAction {
    @Autowired
    private Dao dao;
    private Student student;
    private List<Student> studentList;

    public String findAll() {
        try {
            studentList = dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String findStudentById() {
        try {
            student = dao.findById(student.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String insertStudent() {
        try {
            student.setRegtime(new Date());
            dao.insertStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String updateStudent() {
        try {
            dao.updateStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String deleteStudent() {
        try {
            dao.deleteStudent(student.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
