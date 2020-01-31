package com.kittycoder;

import com.kittycoder.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2020/1/31 16:43
 * 可以在测试期间很方便的使用容器的自动注入功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTest {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService() {
        System.out.println(ioc.containsBean("helloService02"));
    }

    @Test
    public void test() {
        System.out.println(person);
    }
}
