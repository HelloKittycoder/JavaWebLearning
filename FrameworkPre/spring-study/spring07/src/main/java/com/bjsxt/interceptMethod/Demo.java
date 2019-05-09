package com.bjsxt.interceptMethod;

/**
 * Created by shucheng on 2019-5-9 下午 15:28
 */
public class Demo {

    public void demo1() {
        System.out.println("demo1");
    }

    public void demo2(String name, int age) {
        System.out.println("demo2");
    }

    public void demo3() {
        System.out.println("demo3");
    }

    public String demo4(String name) {
        System.out.println("demo4");
        return "demo4的返回值";
    }
}
