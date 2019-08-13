package com.kittycoder.java8.lambda.optionalstudy;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by shucheng on 2019-8-9 下午 13:46
 * https://blog.csdn.net/zknxx/article/details/78586799
 */
public class OptionalStudy01 {

    // of
    @Test
    public void test1() {
        Optional<String> ofOptional = Optional.of("张三");
        // System.out.println(ofOptional.get());
        Optional<String> nullOptional = Optional.of(null);
        // System.out.println(nullOptional);
    }

    // ofNullable
    @Test
    public void test2() {
        Optional<String> nullOptional = Optional.ofNullable(null);
        System.out.println(nullOptional);

        Optional<String> lisi = Optional.ofNullable("lisi");
        System.out.println(lisi);
    }

    // empty
    @Test
    public void test3() {
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
    }

    // get
    @Test
    public void test4() {
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.get());

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.get());
    }

    // orElse
    @Test
    public void test5() {
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.orElse("zhangsan"));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElse("李四"));
    }

    // orElseGet
    @Test
    public void test6() {
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.orElseGet(() -> "zhangsan"));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElseGet(() -> "orElseGet"));
    }

    // orElseThrow
    @Test
    public void test7() {
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.orElseThrow(CustomException::new));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElseThrow(CustomException::new));
    }

    private static class CustomException extends RuntimeException {

        public CustomException() {
            super("自定义异常");
        }

        public CustomException(String message) {
            super(message);
        }
    }

    // filter
    @Test
    public void test8() {
        Optional<String> stringOptional = Optional.of("zhangsan");
        System.out.println(stringOptional.filter(e -> e.length() > 5).orElse("王五"));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.filter(e -> e.length() > 5).orElse("lisi"));
    }

    // map
    @Test
    public void test9() {
        Optional<String> stringOptional = Optional.of("zhangsan");
        System.out.println(stringOptional.map(e -> e.toUpperCase()).orElse("失败"));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.map(e -> e.toUpperCase()).orElse("失败"));
    }

    // flatMap
    // 说明：map和flatMap的区别在于flatMap中mapper返回值必须是Optional，
    // map方法的mapper函数返回值可以是任何类型T
    @Test
    public void test10() {
        Optional<String> stringOptional = Optional.of("zhangsan");
        System.out.println(stringOptional.flatMap(e -> Optional.of("lisi")).orElse("失败"));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.flatMap(e -> Optional.empty()).orElse("失败"));
    }

    @Test
    public void test11() {
        Optional<String> stringOptional = Optional.of("zhangsan");
        stringOptional.ifPresent(e -> System.out.println("我被处理了..." + e));
    }
}
