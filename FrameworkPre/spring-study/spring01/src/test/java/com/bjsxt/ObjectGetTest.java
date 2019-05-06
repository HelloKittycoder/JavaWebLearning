package com.bjsxt;

import com.bjsxt.pojo.People;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2019-5-6 下午 21:23
 * 测试对象获取
 */
public class ObjectGetTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    // 获取容器中的People对象
    @Test
    public void getPeopleClass() {
        People p = ac.getBean("peo", People.class);
        System.out.println(p);
        /*People p = (People) ac.getBean("peo");
        System.out.println(p);*/
    }

    // 获取容器中所有被管理的bean的名称
    @Test
    public void getBeanNames() {
        String[] names = ac.getBeanDefinitionNames();
        for (String str : names) {
            System.out.println(str);
        }
    }
}
