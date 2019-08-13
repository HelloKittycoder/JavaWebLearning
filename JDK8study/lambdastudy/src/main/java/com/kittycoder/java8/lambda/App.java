package com.kittycoder.java8.lambda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        // Date是可变对象
        Date d = new Date();
        System.out.println(d);
        d.setYear(d.getYear() + 1);
        System.out.println(d);

        // jdk1.8的日期api
        // LocalDate时不可变对象
        LocalDate day = LocalDate.now();
        System.out.println(day);
        LocalDate day2 = day.plusDays(1);
        System.out.println(day);
        System.out.println(day2);

        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

        // BigDecimal是不可变对象
        BigDecimal a = new BigDecimal("100");
        System.out.println(a);
        BigDecimal b = a.add(new BigDecimal("200"));
        System.out.println(a);
        System.out.println(b);

        // String是不可变对象
        String str = new String("aaaa");
        System.out.println(str);
        String str1 = str.concat("bbb");
        System.out.println(str);
        System.out.println(str1);
    }
}
