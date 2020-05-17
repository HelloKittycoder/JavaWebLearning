package com.kittycoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by shucheng on 2020/1/30 17:46
 * @SpringBootApplication 来标注一个主程序类，说明这是一个SpringBoot应用
 */
@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
