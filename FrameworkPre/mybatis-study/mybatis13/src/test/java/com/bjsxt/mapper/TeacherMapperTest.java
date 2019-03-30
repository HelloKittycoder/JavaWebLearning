package com.bjsxt.mapper;

/**
 * Create by Administrator on 2019/3/26
 * 使用AutoMapping结合别名实现关联单个对象（说明：无法做到关联集合）
 */

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Student;
import com.bjsxt.pojo.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TeacherMapperTest extends BaseTest {

    private TeacherMapper teacherMapper;

    @Before
    public void init() {
        teacherMapper = session.getMapper(TeacherMapper.class);
    }

    @Test
    public void testSelectAll() {
        List<Teacher> list = teacherMapper.selectAll();;
        for (Teacher t : list) {
            System.out.println(t);
        }
    }

    @Test
    public void testInsertTeacher() {
        Teacher t = new Teacher();
        t.setName("王五");
        int index = teacherMapper.insertTeacher(t);
        if (index != 0) {
            System.out.println("新增成功");
        }
        session.commit();
    }

    @Test
    public void testUpdateTeacher() {
        Teacher t = new Teacher();
        t.setId(4);
        t.setName("赵六");
        int index = teacherMapper.updateTeacher(t);
        if (index != 0) {
            System.out.println("修改成功");
        }
        session.commit();
    }

    @Test
    public void testDeleteById() {
        int index = teacherMapper.deleteById(4);
        if (index != 0) {
            System.out.println("删除成功");
        }
        session.commit();
    }

    @Test
    public void testSelectTeacher() {
        List<Teacher> list = teacherMapper.selectTeacher();
        for (Teacher t : list) {
            System.out.println(t);
        }
    }
}
