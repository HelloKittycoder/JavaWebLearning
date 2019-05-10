package com.bjsxt.interceptMethod;

/**
 * Created by shucheng on 2019-5-9 下午 15:28
 */
public class Demo {

    public void demo1(String name,int age) throws Exception {
        // int i = 1/0;
        System.out.println("demo1" + name + "  " + age);
    }

    public void demo1(String name1) {
        System.out.println("一个参数demo1");
    }
}
