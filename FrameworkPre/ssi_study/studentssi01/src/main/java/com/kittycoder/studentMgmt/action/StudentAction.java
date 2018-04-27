package com.kittycoder.studentMgmt.action;

import com.kittycoder.studentMgmt.po.Student;
import com.kittycoder.studentMgmt.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by shucheng on 2018/4/27.
 */
public class StudentAction {

    @Autowired
    private StudentService studentService;
    private Student student;
    private List<Student> studentList;

    /**
     * 查询学生List
     * @return
     */
    public String findAll() {
        try {
            studentList = studentService.findAll(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    /**
     * 根据id查询学生
     * @return
     */
    public String findStudentById() {
        try {
            student = studentService.findById(student.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    /**
     * 插入学生
     * @return
     */
    public String insertStudent() {
        try {
            student.setRegtime(new Date());
            studentService.insertStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    /**
     * 修改学生
     * @return
     */
    public String updateStudent() {
        try {
            studentService.updateStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    /**
     * 删除学生
     * @return
     */
    public String deleteStudent() {
        try {
            studentService.deleteStudent(student.getId());
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
