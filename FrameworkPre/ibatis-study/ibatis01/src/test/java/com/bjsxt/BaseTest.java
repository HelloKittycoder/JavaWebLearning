package com.bjsxt;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.Reader;

/**
 * Create by Administrator on 2019/3/21
 * 参考文档：https://ibatis.apache.org/docs/java/pdf/iBATIS-SqlMaps-2_cn.pdf
 */
public class BaseTest {

    protected SqlMapClient sqlMapClient;
    // protected SqlSession session;
    // 初始化操作
    @Before
    public void setUp() throws Exception {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);

        /*InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();*/
    }

    // 测试运行完以后的操作
    @After
    public void tearDown() throws Exception {
        // session.close();
    }
}
