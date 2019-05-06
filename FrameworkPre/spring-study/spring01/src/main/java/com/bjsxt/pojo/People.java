package com.bjsxt.pojo;

/**
 * Created by shucheng on 2019-5-6 下午 21:19
 */
public class People {
    private int id;
    private String name;

    public People() {
        System.out.println("执行无参构造方法");
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

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
