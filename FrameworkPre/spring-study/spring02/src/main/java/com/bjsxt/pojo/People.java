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

    public People(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("执行有参构造方法");
    }


    public People(Integer id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("执行有参构造方法Integer");
    }

    public People(String name, int id) {
        this.id = id;
        this.name = name;
        System.out.println("执行有参构造方法1111");
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
