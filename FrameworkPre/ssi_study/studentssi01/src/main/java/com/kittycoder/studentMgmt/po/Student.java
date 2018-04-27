package com.kittycoder.studentMgmt.po;

import java.util.Date;

/**
 * Created by shucheng on 2018/4/25.
 * 学生PO
 */
public class Student {

    private int id;
    private String name;
    private int age;
    private String sex;
    private Date regtime;

    public Student() {
    }

    public Student(int id, String name, int age, String sex, Date regtime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.regtime = regtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", regtime=" + regtime +
                '}';
    }
}
