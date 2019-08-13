package com.kittycoder.java8.lambda.ch03_2;

import org.junit.Test;

import java.io.Closeable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by shucheng on 2019-8-4 下午 22:03
 * 对象方法引用：
 * 抽象方法的第一个参数类型刚好是实例方法的类型，抽象方法剩余的参数恰好可以当作实例方法的参数。如果函数式接口的实现能由上面说的实例方法调用来实现的话，那么就可以使用对象方法引用
 *
 * 语法：
 * 类名::instMethod
 */
public class ObjectMethodRef {

    // 抽象方法没有输入参数，不能使用对象方法引用
    public void notValid() {
        Runnable r = () -> {};
        Closeable c = () -> {};
        Supplier<String> s = () -> "";
    }

    @Test
    public void test1() {
        Consumer<Too> c1 = (Too too) -> new Too().foo();
        c1.accept(new Too());

        // c2和c3的写法是等价的
        Consumer<Too> c2 = (Too too) -> too.foo();
        c2.accept(new Too());

        Consumer<Too> c3 = Too::foo;
        c3.accept(new Too());

        Consumer<Too> c4 = (Too too) -> new Too2().foo();
        // 上面这种情况无法改写成对象方法的形式
        // Consumer<Too> c5 = Too2::foo;
    }

    @Test
    public void test2() {

        BiConsumer<Too2, String> c1 = (too2, str) -> too2.foo2(str);

        BiConsumer<Too2, String> c2 = Too2::foo2;
        c2.accept(new Too2(), "testStr");

        BiFunction<Prod, String, Integer> bf1 = (p, s) -> p.fun(s);

        BiFunction<Prod, String, Integer> bf2 = Prod::fun;
        System.out.println(bf2.apply(new Prod(), "hehe"));
    }

    @Test
    public void test3() {
        Execute ex1 = (p, name, size) -> p.run(name, size);
        Execute ex2 = Prod::run;
        ex2.run(new Prod(), "11", "22");
    }

    @Test
    public void test4() {
        ThrFunction<Prod2, String, String, Integer> t1 = (p, s1, s2) -> p.run(s1, s2);

        ThrFunction<Prod2, String, String, Integer> t2 = Prod2::run;
        System.out.println(t2.apply(new Prod2(), "a1", "a2"));
    }
}

interface ThrFunction<T, U, V, R> {

    R apply(T t, U u, V v);
}

class Prod2 {
    Integer run(String name, String size) {
        System.out.println("name===" + name + "*****size===" + size);
        return 2;
    }
}

interface Execute {

    void run(Prod p, String name, String size);
}

class Prod {

    public void run(String name, String size) {}

    public Integer fun(String s) {
        System.out.println("输入参数为：" + s);
        return 1;
    }
}

class Too {
    public void foo() {
        System.out.println("invoke");
    }
}

class Too2 {
    public void foo() {
        System.out.println("invoke");
    }

    public void foo2(String str) {
        System.out.println("Too2.foo2========" + str);
    }
}