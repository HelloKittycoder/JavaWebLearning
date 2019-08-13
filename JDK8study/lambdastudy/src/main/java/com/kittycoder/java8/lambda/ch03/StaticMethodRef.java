package com.kittycoder.java8.lambda.ch03;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by shucheng on 2019-8-4 下午 20:33
 * 静态方法引用
 * 如果函数式接口的实现恰好可以通过调用一个静态方法来实现，那么就可以使用静态方法引用
 *
 * 语法：
 * 类名::staticMethod
 */
public class StaticMethodRef {

    public static String get() {
        return "hello";
    }

    public static void con(Integer size) {
        System.out.println("size为" + size);
    }

    public static int getStringLength(String a, String b) {
        return a.length() + b.length();
    }

    // 无参静态方法
    @Test
    public void test1() {
        // 无输入有输出
        Supplier<String> s = () -> StaticMethodRef.get();
        System.out.println(s.get() + " s");

        Supplier<String> s1 = StaticMethodRef::get;
        System.out.println(s1.get() + " s1");
    }

    // 带参静态方法
    @Test
    public void test2() {
        // 有输入没输出
        Consumer<Integer> c1 = (size) -> {
            System.out.println("size为" + size);
        };
        Consumer<Integer> c2 = (size) -> StaticMethodRef.con(size);
        Consumer<Integer> c3 = StaticMethodRef::con;

        // 有输入有输出
        BiFunction<String, String, Integer> bf1 = (a, b) -> a.length() + b.length();
        BiFunction<String, String, Integer> bf2 = (a, b) -> StaticMethodRef.getStringLength(a, b);
        BiFunction<String, String, Integer> bf3 = StaticMethodRef::getStringLength;
    }
}
