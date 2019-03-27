package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Create by Administrator on 2019/3/27
 */
public class StudentMapperTest extends BaseTest {

    @Test
    public void testSelectAll() {
        SqlSession session = factory.openSession();
        List<Student> list = session.selectList("com.bjsxt.mapper.StudentMapper.selectAll");
        for (Student s : list) {
            System.out.println(s);
        }
        session.close();
    }
}
