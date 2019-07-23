package com.kittycoder.test1.po;

import java.util.Date;

/**
 * Created by shucheng on 2019-7-22 下午 22:43
 */
public class Student_Nsf {

    private Integer id;
    private String name;
    private Date birthday; // 假设存的时间是精确到秒的

    public Student_Nsf() {
    }

    public Student_Nsf(Integer id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
