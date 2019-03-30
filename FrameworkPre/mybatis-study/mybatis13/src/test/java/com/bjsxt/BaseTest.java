
package com.bjsxt;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;

/**
 * Create by Administrator on 2019/3/21
 */
public class BaseTest {

    protected SqlSessionFactory factory;
    protected SqlSession session;
    // 初始化操作
    @Before
    public void setUp() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
    }

    // 测试运行完以后的操作
    @After
    public void tearDown() throws Exception {
        session.close();
    }
}