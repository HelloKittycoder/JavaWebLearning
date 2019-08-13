package com.kittycoder.java8.lambda.ch02;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by shucheng on 2019-8-4 下午 18:31
 * Lambda表达式的使用
 */
public class LambdaExpression {

    // 无参无返回值
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1");
            }
        };
        r1.run();

        Runnable r2 = () -> {System.out.println("r2");};
        r2.run();

        Runnable r3 = () -> System.out.println("r3");
        r3.run();
    }

    // 无参有返回值
    @Test
    public void test2() throws Exception {
        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "c1";
            }
        };
        System.out.println(c1.call());

        Callable<String> c2 = () -> { return "c2"; };
        System.out.println(c2.call());

        Callable<String> c3 = () -> "c3";
        System.out.println(c3.call());
    }

    // 有参无返回值
    @Test
    public void test3() {
        UserMapper u1 = new UserMapper() {
            @Override
            public void insert(User user) {
                System.out.println("u1");
            }
        };
        u1.insert(new User());

        UserMapper u2 = (User user) -> { System.out.println("u2");};
        u2.insert(new User());

        UserMapper u3 = user -> System.out.println("u3");
        u3.insert(new User());
    }

    // 有参有返回值
    @Test
    public void test4() {
        OrderMapper o1 = new OrderMapper() {
            @Override
            public int insert(Order order) {
                return 1;
            }
        };
        System.out.println(o1.insert(new Order()));

        OrderMapper o2 = (Order order) -> { return 2; };
        System.out.println(o2.insert(new Order()));

        OrderMapper o3 = (order) -> { return 3; };
        System.out.println(o3.insert(new Order()));

        OrderMapper o4 = (Order order) -> 4;
        System.out.println(o4.insert(new Order()));

        OrderMapper o5 = order -> 5;
        System.out.println(o5.insert(new Order()));
    }

    // 计算1+2+3+...+n
    @Test
    public void test5() {
        Function<Integer, Integer> f1 = a -> {
            int sum = 0;
            for (int i = 1; i <= a; i++) {
                sum += i;
            }
            return sum;
        };
        System.out.println(f1.apply(10));
    }

    @Test
    public void test6() {
        // 抽象方法无返回值，执行的方法可以有返回值，也可以没有返回值；
        // 但是不能直接指向一个值
        Runnable r1 = () -> get();
        Runnable r2 = () -> exec();
        // Runnable r3 = () -> 100; // 等同与return 100;接口要求无返回值，而表达式有返回值

        // 抽象方法有返回值，执行的方法必须有返回值，且类型要保持一致
        Foo f1 = () -> get();
        // Foo f2 = () -> find(); // 接口要求返回int，返回类型不一致
        // Foo f3 = () -> exec(); // 接口要求有返回值，exec没有返回值
        Foo f4 = () -> 100;
        Foo f5 = () -> true ? 1 : -1;
    }

    @Test
    public void test7() {
        BiFunction<String, String, Integer> bf = (a, b) -> a.length() + b.length();
        System.out.println(bf.apply("java", "se"));

        Function<String, Integer> f = a -> a.length();
        System.out.println(f.apply("javaee"));
    }

    public static int get() {
        return 1;
    }

    public static String find() {
        return "";
    }

    public static void exec() {
    }
}

interface UserMapper {
    void insert(User user);
}

class User {}

interface OrderMapper {
    int insert(Order order);
}

class Order {}

interface Foo {
    int get();
}