package com.bjsxt.interceptMethod;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test() {
        Demo demo = ac.getBean("demo", Demo.class);
        try {
            demo.demo1();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}