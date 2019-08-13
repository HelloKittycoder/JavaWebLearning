package com.kittycoder.java8.lambda.ch03;

import org.junit.Test;

import java.io.Closeable;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by shucheng on 2019-8-4 下午 20:07
 * 方法的引用01
 */
public class MethodRef01 {

    @Test
    public void test1() {
        // 无参无返回值
        Runnable r = () -> {};
        Closeable c = () -> {};

        // 有参无返回值
        Consumer<String> c2 = (a) -> {};
        Consumer<String> c3 = a -> {};
        Consumer<String> c4 = (String a) -> {};

        // 无参有返回值
        Supplier<String> s1 = () -> "hello";
        Supplier<String> s2 = () -> {
            // 具体业务逻辑
            return "hello";
        };

        // 有参有返回值
        Function<String, Integer> f1 = (str) -> Integer.valueOf(str);
        Function<String, Integer> f2 = (String str) -> Integer.valueOf(str);
        Function<String, Integer> f3 = (String str) -> {
            // 具体业务逻辑
            return Integer.valueOf(str);
        };
    }

    @Test
    public void test2() {
        Function<String, String> fn = str -> str.toUpperCase();
        System.out.println(fn.apply("admin"));

        Consumer<String> c = arg -> { System.out.println(arg); };
        c.accept("hello");

        Function<String, String> fn1 = String::toUpperCase;
        System.out.println(fn1.apply("admin"));

        Consumer<String> c1 = System.out::println;
        c1.accept("hello");
    }
}
