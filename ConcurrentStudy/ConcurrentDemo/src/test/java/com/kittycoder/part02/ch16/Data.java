package com.kittycoder.part02.ch16;

/**
 * Created by shucheng on 2019-9-24 下午 15:22
 * 如果用final修饰class，表示这个类不能被继承
 */
public final class Data {

    private String id;
    private String name;

    public Data(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
