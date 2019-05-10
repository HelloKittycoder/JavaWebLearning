package com.bjsxt.jdk_dynamic.pojo;

import java.lang.reflect.Proxy;

/**
 * Created by shucheng on 2019-5-10 下午 14:15
 */
public class Women {
    public static void main(String[] args) {
        // jvm只会创建一个classLoader
        // System.out.println(Women.class.getClassLoader() == Laozong.class.getClassLoader());
        Mishu mishu = new Mishu(); // 代理类
        // 第一个参数：反射时使用的类加载器
        // 第二个参数：Proxy需要实现什么接口
        // 第三个参数：通过接口对象调用方法时，需要调用哪个类的invoke方法
        Gongneng gongneng = (Gongneng) Proxy.newProxyInstance(Women.class.getClassLoader(), // 类加载器
                new Class[]{Gongneng.class}, // 代理类实现的接口
                mishu); // 代理类
        gongneng.chifan();
    }
}
