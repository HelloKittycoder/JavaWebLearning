package com.kittycoder.datadisplay.action;

import com.kittycoder.datadisplay.po.Student;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by shucheng on 2018/4/18.
 * 用于模拟页面返回object的例子
 */
public class ObjectAction {

    private Student student;

    public String testObject() {
        try {
            if(student == null) {
                student = new Student();
            }
            student.setSid(1L);
            student.setSname("张三");
            student.setSbirthday("1994-09-13");
            student.setSage("18");
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
}
