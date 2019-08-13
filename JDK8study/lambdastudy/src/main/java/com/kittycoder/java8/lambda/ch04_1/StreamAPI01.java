package com.kittycoder.java8.lambda.ch04_1;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by shucheng on 2019-8-6 上午 9:00
 */
public class StreamAPI01 {

    // 生成流 test1-test6
    @Test
    public void test1() {
        String[] arr = {"a", "b", "1", "2"};
        Stream<String> stream = Stream.of(arr);
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("a", "b", "1", "2");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        Set<String> set = new HashSet<>(Arrays.asList("1", "2", "3"));
        Stream<String> stream2 = set.stream();
        stream2.forEach(System.out::println);
    }

    @Test
    public void test3() {
        Stream<Integer> stream = Stream.generate(() -> 1); // 如果不截取（如：limit）的话，就会无限循环
        stream.limit(10).forEach(System.out::println);
    }

    @Test
    public void test4() {
        // iterate会把每次循环的结果当成下一次的输入
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1); // 如果不截取（如：limit）的话，就会无限循环
        stream.limit(10).forEach(System.out::println);
    }

    @Test
    public void test5() {
        String str = "abcd";
        IntStream stream = str.chars();

        // stream.forEach(x -> System.out.println(x));
        stream.forEach(System.out::println);
    }

    @Test
    public void test6() throws Exception {
        String projectDir = System.getProperty("user.dir");
        Files.lines(Paths.get(projectDir + "/src/test/java/com/kittycoder/java8/lambda/AppTest.java"))
            .forEach(System.out::println);
    }

    // test7 终止操作（E）；
    @Test
    public void test7() {
        // 打印偶数
        /*Arrays.asList(1,2,3,4,5).stream().filter(x -> {
            //System.out.println("过滤操作");
            return x % 2 == 0;
        }).forEach(System.out::println);*/

        // 计算 sum，max，min等
        /*Arrays.asList(1,2,3,4,5).stream().filter(x -> x%2 == 0).forEach(System.out::println);
        int sum = Arrays.asList(1,2,3,4,5,6).stream().filter(x -> x%2 == 0).mapToInt(x -> x).sum();
        System.out.println(sum);

        int max = Arrays.asList(1,2,3,4,5,6).stream().max((a, b) -> a - b).get();
        System.out.println(max);

        int min = Arrays.asList(1,2,3,4,5,6).stream().min((a, b) -> a - b).get();
        System.out.println(min);

        // 匹配 findAny，findFirst等
        Optional<Integer> op = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 2 == 0).findAny();
        System.out.println(op.get());

        op = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 2 == 0)
                .sorted((a, b) -> b - a).findFirst(); // 倒序排列
        System.out.println(op.get());*/

        /*Arrays.asList(11, 3, 8, 5, 10).stream().sorted()
                .forEach(System.out::println); // 升序排列

        Arrays.asList(11, 3, 8, 5, 10).stream().sorted((a, b) -> b - a)
                .forEach(System.out::println); // 倒序排列

        Arrays.asList("cn", "admin", "net", "io").stream().sorted((a, b) -> a.length() - b.length())
                .forEach(System.out::println); // 按字符串长度排序*/

        // 收集器 collect
        // 把1-50之内的偶数找出来，放到一个list里
        // Stream.iterate(1, x -> x + 1).limit(50).forEach(System.out::println);
        // Stream.iterate(1, x -> x + 1).limit(50).filter(x -> x % 2 == 0).forEach(System.out::println);
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(list);
    }

    // 中间操作（M） filter，sorted等
    @Test
    public void test8() {
        // 使用distinct去重
        /*Arrays.asList(1, 3, 4, 2, 2, 5, 1).stream().distinct().forEach(System.out::println);
        // 使用Set去重
        Set<Integer> set = Arrays.asList(1, 3, 4, 2, 2, 5, 1).stream().collect(Collectors.toSet());*/
        // skip，limit可以用作分页
        /*List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).sorted((a, b) -> b - a).skip(10).limit(10)
                .collect(Collectors.toList()); // 倒序循环（前50个倒序，忽略前10个，再取前10个）；这个例子中，如果先limit(10)再skip(10)，结果为[]，需要注意顺序
        System.out.println(list);*/
        // 转换 map/flatMap
        // 把逗号分隔的数字字符串分割成字符串数组，然后转换成整数数组，最后求和
        /*String str = "11,22,33,44,55";

        int sum = Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum();
        System.out.println(sum);

        sum = Stream.of(str.split(",")).map(x -> Integer.valueOf(x)).mapToInt(x -> x).sum();
        System.out.println(sum);

        sum = Stream.of(str.split(",")).map(Integer::valueOf).mapToInt(x -> x).sum();
        System.out.println(sum);*/

        // 把str转换为User
        /*String str2 = "tomcat,nginx,apache,jetty";
        Stream.of(str2.split(",")).map(x -> new User(x)).forEach(System.out::println);
        Stream.of(str2.split(",")).map(User::new).forEach(System.out::println);
        Stream.of(str2.split(",")).map(x -> Person.build(x)).forEach(System.out::println);
        Stream.of(str2.split(",")).map(Person::build).forEach(System.out::println);*/

        // Stream.of(str2.split(",")).peek(System.out::println).forEach(System.out::println);

        // 中途将结果取出来看
        String str = "11,22,33,44,55";
        int sum = Stream.of(str.split(",")).peek(System.out::println).mapToInt(Integer::valueOf).sum();
        System.out.println(sum);
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Person {
    private String name;

    public static Person build(String name) {
        Person p = new Person();
        p.setName(name);
        return p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}