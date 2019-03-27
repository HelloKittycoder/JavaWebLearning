package com.bjsxt.mapper;

/**
 * Create by Administrator on 2019/3/26
 */

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TeacherMapperTest extends BaseTest {

    @Test
    public void testSelectAll() {
        SqlSession session = factory.openSession();
        List<Teacher> list = session.selectList("com.bjsxt.mapper.TeacherMapper.selectAll");
        for (Teacher t : list) {
            System.out.println(t);
        }
        session.close();
    }
}
