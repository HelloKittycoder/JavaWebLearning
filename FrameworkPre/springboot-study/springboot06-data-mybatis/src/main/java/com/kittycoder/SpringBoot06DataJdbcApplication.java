package com.kittycoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by shucheng on 2020/1/30 17:46
 * @SpringBootApplication 来标注一个主程序类，说明这是一个SpringBoot应用
 */
@MapperScan(value = "com.kittycoder.mapper")
@SpringBootApplication
public class SpringBoot06DataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06DataJdbcApplication.class, args);
    }
}
