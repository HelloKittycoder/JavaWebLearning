package com.kittycoder.baseUsageTest.po;

/**
 * Created by shucheng on 2018/4/18.
 */
public class Student {

    private String sid; // 学生id
    private String sname; // 学生姓名
    private int sage; // 学生年龄

    public Student() {
    }

    public Student(String sid, String sname, int sage) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                '}';
    }
}
