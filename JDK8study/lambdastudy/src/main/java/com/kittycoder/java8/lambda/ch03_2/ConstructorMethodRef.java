package com.kittycoder.java8.lambda.ch03_2;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by shucheng on 2019-8-5 下午 20:45
 * 构造方法引用
 * 如果函数式接口的实现恰好可以通过调用一个类的构造方法来实现，那么就可以使用构造方法引用
 *
 * 语法
 * 类名::new
 */
public class ConstructorMethodRef {

    @Test
    public void test1() {
        Supplier<Person> s1 = () -> new Person();
        Supplier<Person> s2 = Person::new;
        System.out.println(s2.get());

        Supplier<List> s3 = ArrayList::new;
        System.out.println(s3.get());
        Supplier<Thread> s4 = Thread::new;
        Supplier<Set> s5 = HashSet::new;
        Supplier<Map> s6 = HashMap::new;
        Supplier<String> s7 = String::new;
    }

    @Test
    public void test2() {
        Supplier<Account> s1 = () -> new Account();
        Supplier<Account> s2 = Account::new;
        s2.get();

        Consumer<Integer> c1 = num -> new Account(num);
        Consumer<Integer> c2 = Account::new;
        c2.accept(11);

        Function<Integer, Account> f1 = (num) -> new Account(num);
        Function<Integer, Account> f2 = Account::new;
        f2.apply(2);

        Function<String, Account> f3 = (name) -> new Account(name);
        Function<String, Account> f4 = Account::new;
        f4.apply("4");
    }
}

class Account {

    public Account() {
        System.out.println("Account");
    }

    public Account(int num) {
        System.out.println("Account(num)");
    }

    public Account(String name) {
        System.out.println("Account(name)");
    }
}

class Person {
    public Person() {
        System.out.println("new Person()");
    }
}
