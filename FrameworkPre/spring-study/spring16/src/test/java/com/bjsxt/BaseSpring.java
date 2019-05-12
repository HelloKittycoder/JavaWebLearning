package com.bjsxt;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Create by Administrator on 2019/5/12
 * 参考链接：https://www.cnblogs.com/jiaoyiping/p/4251759.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration
public class BaseSpring {

    @Autowired
    private WebApplicationContext applicationContext;

    /*protected static ApplicationContext applicationContext;
    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }*/
}
