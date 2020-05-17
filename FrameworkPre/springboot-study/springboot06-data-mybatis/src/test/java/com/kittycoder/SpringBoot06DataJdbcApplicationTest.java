package com.kittycoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by shucheng on 2020/1/31 21:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06DataJdbcApplicationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws Exception {
        System.out.println("context========" + context);
        System.out.println("dataSource========" + dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println("连接用的类========" + connection.getClass());
        connection.close();
    }
}
