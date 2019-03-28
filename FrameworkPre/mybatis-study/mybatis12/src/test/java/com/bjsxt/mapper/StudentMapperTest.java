package com.bjsxt.mapper;

/**
 * Create by Administrator on 2019/3/26
 * 使用AutoMapping结合别名实现关联单个对象（说明：无法做到关联集合）
 */

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

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
