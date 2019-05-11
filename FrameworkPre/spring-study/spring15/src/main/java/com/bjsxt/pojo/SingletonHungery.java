package com.bjsxt.pojo;

/**
 * Create by Administrator on 2019/5/11
 * 饿汉式单例
 */
public class SingletonHungery {
    // 在类加载时进行实例化
    private static SingletonHungery singletonHungery = new SingletonHungery();
    private SingletonHungery() {}
    public static SingletonHungery getInstance() {
        return singletonHungery;
    }
}
