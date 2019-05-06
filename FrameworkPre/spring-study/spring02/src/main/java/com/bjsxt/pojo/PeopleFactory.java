package com.bjsxt.pojo;

/**
 * Created by shucheng on 2019-5-6 下午 22:28
 */
public class PeopleFactory {

    // 实例工厂
    public People newInstance() {
        return new People(1, "实例工厂测试");
    }

    // 静态工厂
    public static People newInstanceStatic() {
        return new People(1, "静态工厂测试");
    }
}
