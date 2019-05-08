package com.bjsxt;

import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2019-5-6 下午 23:49
 * 测试给对象属性赋值
 */
public class SetBeanPropertyTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    // 构造方法
    @Test
    public void test1() {
        /*People people = ac.getBean("peo", People.class);
        System.out.println(people);*/
        DefaultSqlSessionFactory factoryBean = ac.getBean("factory", DefaultSqlSessionFactory.class);
        System.out.println(factoryBean);
    }
}
