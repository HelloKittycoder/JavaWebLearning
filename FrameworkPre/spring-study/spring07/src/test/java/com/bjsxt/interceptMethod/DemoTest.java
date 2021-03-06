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
        demo.demo1();
        demo.demo2("111", 11);
        demo.demo3();
        demo.demo4("demo4测试");

        Demo1 demo1 = ac.getBean("demo1", Demo1.class);
        demo1.demo11();
    }
}