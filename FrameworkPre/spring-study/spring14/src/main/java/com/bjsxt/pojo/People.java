package com.bjsxt.pojo;

/**
 * Created by shucheng on 2019-5-10 下午 20:28
 */
public class People {

    private Teacher teacher;

    public People() {
    }

    public People(Teacher teacher2) {
        this.teacher = teacher2;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "People{" +
                "teacher=" + teacher +
                '}';
    }
}
