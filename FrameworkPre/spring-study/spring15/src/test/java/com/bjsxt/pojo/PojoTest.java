package com.bjsxt.pojo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2019-5-10 下午 20:30
 */
public class PojoTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test() {
        /*String[] names = ac.getBeanDefinitionNames();
        System.out.println(Arrays.toString(names));*/

        Teacher t1 = ac.getBean("teacher1", Teacher.class);
        Teacher t2 = ac.getBean("teacher1", Teacher.class);
        System.out.println(t1==t2);
    }
}
