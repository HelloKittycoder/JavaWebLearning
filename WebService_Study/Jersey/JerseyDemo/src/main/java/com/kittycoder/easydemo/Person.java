package com.kittycoder.easydemo;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by shucheng on 2018/5/19.
 */
@XmlRootElement(name = "person")
public class Person {

    @FormParam(value = "name")
    private String name;

    @FormParam(value = "id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
