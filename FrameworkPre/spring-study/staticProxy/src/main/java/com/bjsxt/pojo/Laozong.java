package com.bjsxt.pojo;

/**
 * Created by shucheng on 2019-5-10 上午 11:17
 * 老总-真实对象
 */
public class Laozong implements Gongneng {

    private String name;

    public Laozong() {
    }

    public Laozong(String name) {
        this.name = name;
    }

    @Override
    public void zhidingxiaomubiao() {
        System.out.println("制定小目标");
    }

    @Override
    public void chifan() {
        System.out.println("老总吃饭");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
