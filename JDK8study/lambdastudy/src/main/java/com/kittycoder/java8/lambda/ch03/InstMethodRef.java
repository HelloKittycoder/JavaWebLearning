package com.kittycoder.java8.lambda.ch03;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by shucheng on 2019-8-4 下午 21:23
 * 实例方法引用
 * 如果函数式接口的实现恰好可以通过调用一个实例的实例方法来实现，那么就可以使用实例方法引用
 *
 * 语法：
 * inst::instMethod
 */
public class InstMethodRef extends BaseInstMethodRef {

    public String getStr() {
        return "hello";
    }

    public void setStr(String str) {
        System.out.println("设置str为：" + str);
    }

    @Override
    public String toUpperCase(String str) {
        System.out.println("son toUpperCase");
        return str.toUpperCase();
    }

    public void callThisMethod(String str) {
        Function<String, String> f = this::toUpperCase;
        System.out.println(f.apply(str));

        Function<String, String> f2 = super::toUpperCase;
        System.out.println(f2.apply(str));
    }

    // 无参实例方法
    @Test
    public void test1() {
        Supplier<String> s = () -> new InstMethodRef().getStr();
        Supplier<String> s1 = () -> { return new InstMethodRef().getStr(); };
        Supplier<String> s2 = new InstMethodRef()::getStr;
        System.out.println(s2.get());
    }

    // 有参实例方法
    @Test
    public void test2() {
        Consumer<String> c = (str) -> new InstMethodRef().setStr(str);
        Consumer<String> c1 = (str) -> { new InstMethodRef().setStr(str); };
        Consumer<String> c2 = new InstMethodRef()::setStr;
        c2.accept("c2");
    }

    // 类的实例方法之间相互调用（this，super）
    @Test
    public void test3() {
        new InstMethodRef().callThisMethod("admin");
    }
}
