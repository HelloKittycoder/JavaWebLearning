package com.kittycoder.java8.lambda.ch04_2;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by shucheng on 2019-8-8 上午 8:34
 * 并行流（parallel）与串行流（sequential）
 */
public class StreamAPI02 {

    @Test
    public void test1() {
        // 设置线程数量
        // 也可以通过设置启动参数 -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
        // 如果不加parallel，默认使用的是串行流（sequential）
        Optional<Integer> max = Stream.iterate(1, x -> x + 1).limit(200).parallel()
                .peek(x -> {
                    System.out.println(Thread.currentThread().getName());
                }).max(Integer::compareTo);
        System.out.println(max);
    }
}
