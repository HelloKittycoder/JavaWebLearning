package com.bjsxt.pojo;

import java.util.*;

/**
 * Created by shucheng on 2019-5-6 下午 21:19
 */
public class People {
    private int id;
    private String name;
    private Set<String> set;
    private List<String> list;
    private String[] strs;
    private Map<String, String> map;
    private Properties demoProps;

    private Desk desk;

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", set=" + set +
                ", list=" + list +
                ", strs=" + Arrays.toString(strs) +
                ", map=" + map +
                ", demoProps=" + demoProps +
                ", desk=" + desk +
                '}';
    }

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
        System.out.println("执行setId方法");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("执行setName方法");
        this.name = name;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getDemoProps() {
        return demoProps;
    }

    public void setDemoProps(Properties demoProps) {
        this.demoProps = demoProps;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}
