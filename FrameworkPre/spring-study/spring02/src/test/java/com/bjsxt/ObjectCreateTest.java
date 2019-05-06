package com.bjsxt;

import com.bjsxt.pojo.People;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2019-5-6 下午 22:52
 * 测试通过工厂设计模式来创建对象
 */
public class ObjectCreateTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    // 构造方法
    @Test
    public void test1() {
        People people = ac.getBean("peo", People.class);
        System.out.println(people);
    }

    // 实例工厂
    @Test
    public void test2() {
        /**
         * 实例工厂测试代码
         * 具体如何实例化工厂（单例还是多例）和本知识点没有关系
         */
        /*PeopleFactory factory = new PeopleFactory();
        People people = factory.newInstance();
        System.out.println(people);*/
        People people = ac.getBean("peo1", People.class);
        System.out.println(people);
    }

    // 静态工厂
    @Test
    public void test3() {
        /*People people = PeopleFactory.newInstanceStatic();
        System.out.println(people);*/
        People people = ac.getBean("peo2", People.class);
        System.out.println(people);
    }
}
