package com.kittycoder.java8.lambda.introduction;

/**
 * Created by shucheng on 2019-8-4 下午 17:45
 * 什么是函数式接口？
 *
 * 这里的delete方法是抽象方法，满足函数式接口的要求
 * @FunctionalInterface用来标记函数式接口
 *
 * JDK1.8之前的一些函数式接口
 * @see java.lang.Runnable
 * @see java.util.concurrent.Callable
 * @see java.util.Comparator
 *
 * JDK1.8提供的函数式接口（在java.util.function包下）
 * @see java.util.function.Supplier 输出
 * @see java.util.function.Consumer 输入
 * @see java.util.function.BiConsumer 两个输入
 * @see java.util.function.Function 输入，输出（一般输入输出类型不同）
 * @see java.util.function.UnaryOperator 输入，输出（一般输入输出类型相同）
 * @see java.util.function.BiFunction 两个输入，一个输出（一般输入输出类型不同）
 * @see java.util.function.BinaryOperator 两个输入，一个输出（一般输入输出类型相同）
 *
 */
@FunctionalInterface
public interface UserMapper {

    int delete();

    public int hashCode();

    default int insert() {
        return 1;
    }

    static int update() {
        return 1;
    }
}
