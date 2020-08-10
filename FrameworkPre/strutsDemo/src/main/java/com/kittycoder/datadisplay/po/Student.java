package com.kittycoder.datadisplay.po;

/**
 * Created by shucheng on 2018/4/18.
 */
public class Student {

    private Long sid; // 学生id
    private String sname; // 学生姓名
    private String sbirthday; // 学生生日
    private String sage; // 学生年龄

    public Student() {}

    public Student(Long sid, String sname, String sbirthday, String sage) {
        this.sid = sid;
        this.sname = sname;
        this.sbirthday = sbirthday;
        this.sage = sage;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSage() {
        return sage;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sbirthday='" + sbirthday + '\'' +
                ", sage='" + sage + '\'' +
                '}';
    }
}
