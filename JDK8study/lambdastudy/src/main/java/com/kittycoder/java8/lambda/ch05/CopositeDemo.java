package com.kittycoder.java8.lambda.ch05;

import com.kittycoder.java8.lambda.ch05.po.Book;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shucheng on 2019-8-8 下午 19:59
 * 综合实例
 */
public class CopositeDemo {

    /**
     * index.do?itemId=1&userId=10000&type=20&token=11111&key=index
     */
    @Test
    public void test1() {
        String queryString = "itemId=1&userId=10000&type=20&token=11111&key=index";
        Map<String, String> params = Stream.of(queryString.split("&")).map(str -> str.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(params);
    }

    @Test
    public void test2() {
        // List<Book> -> List<Integer>
        List<Integer> ids = books().stream().map(book -> book.getId()).collect(Collectors.toList());
        System.out.println(ids);

        ids = books().stream().map(Book::getId).collect(Collectors.toList());
        System.out.println(ids);

        String str = books().stream().map(book -> book.getId()+"").collect(Collectors.joining(","));
        System.out.println(str);

        str = books().stream().map(book -> book.getId()+"").collect(Collectors.joining(",","(",")"));
        System.out.println(str);

        str = books().stream().map(book -> "'"+book.getId()+"'").collect(Collectors.joining(",","(",")"));
        System.out.println(str);
    }

    @Test
    public void test3() {
        // List<Book> -> List<String>
        List<String> types = books().stream().map(book -> book.getType()).collect(Collectors.toList());
        System.out.println(types);

        types = books().stream().map(book -> book.getType()).distinct().collect(Collectors.toList());
        System.out.println(types);

        Set<String> set = books().stream().map(book -> book.getType()).distinct().collect(Collectors.toSet());
        System.out.println(set);
    }

    // 排序
    @Test
    public void test4() {}

    private List<Book> books() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "tomcat", 50d, "服务器", LocalDate.parse("2014-05-17")));
        books.add(new Book(2, "jetty", 60d, "服务器", LocalDate.parse("2015-12-01")));
        books.add(new Book(3, "nginx", 65d, "服务器", LocalDate.parse("2016-10-17")));
        books.add(new Book(4, "java", 66d, "编程语言", LocalDate.parse("2011-04-09")));
        books.add(new Book(5, "ruby", 80d, "编程语言", LocalDate.parse("2013-08-09")));
        books.add(new Book(6, "php", 40d, "编程语言", LocalDate.parse("2014-08-06")));
        books.add(new Book(7, "html", 440d, "编程语言", LocalDate.parse("2011-01-06")));
        books.add(new Book(8, "oracle", 150d, "数据库", LocalDate.parse("2013-08-09")));
        books.add(new Book(9, "mysql", 66d, "数据库", LocalDate.parse("2015-04-06")));
        books.add(new Book(10, "ssh", 70d, "数据库", LocalDate.parse("2016-12-04")));
        books.add(new Book(11, "设计模式", 81d, "其他", LocalDate.parse("2017-04-06")));
        books.add(new Book(12, "重构", 62d, "其他", LocalDate.parse("2012-04-09")));
        books.add(new Book(13, "敏捷开发", 72d, "其他", LocalDate.parse("2016-09-07")));
        books.add(new Book(14, "从技术到管理", 42d, "其他", LocalDate.parse("2016-02-19")));
        books.add(new Book(15, "算法导论", 58d, "其他", LocalDate.parse("2010-05-08")));
        return books;
    }
}