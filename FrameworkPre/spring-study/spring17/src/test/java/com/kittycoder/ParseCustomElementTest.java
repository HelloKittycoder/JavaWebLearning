package com.kittycoder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2019-6-18 下午 23:09
 * 自定义标签解析
 */
public class ParseCustomElementTest {

    private ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void test() {
        User user = (User) context.getBean("user");
        System.out.println(user.getUserName() + "----" + user.getEmail());
    }
}
