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

        People peo1 = ac.getBean("people", People.class);
        System.out.println(peo1);
        People peo2 = ac.getBean("people2", People.class);
        System.out.println(peo2);
        System.out.println(peo1==peo2);
    }
}
