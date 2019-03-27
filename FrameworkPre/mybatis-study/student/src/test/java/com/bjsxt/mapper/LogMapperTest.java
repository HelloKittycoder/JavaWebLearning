package com.bjsxt.mapper;

/**
 * Create by Administrator on 2019/3/26
 */

import com.bjsxt.BaseTest;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class LogMapperTest extends BaseTest {

    @Test
    public void testSelectAll() {
        SqlSession session = factory.openSession();
        session.selectList("com.bjsxt.mapper.LogMapper.selectAll");
        session.close();
        SqlSession session1 = factory.openSession();
        session1.selectList("com.bjsxt.mapper.LogMapper.selectAll");
        session1.close();
    }
}
