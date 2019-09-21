package com.kittycoder;

import com.kittycoder.entity.User;
import com.kittycoder.mapper.UserMapper;
import com.kittycoder.session.DefaultSqlSessionFactory;
import com.kittycoder.session.SqlSession;
import com.kittycoder.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * Created by shucheng on 2019-9-21 上午 10:09
 */
public class MybatisTest {

    @Test
    public void testSelectAllUsers() {
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAllUsers();
        System.out.println(users);
    }

    @Test
    public void testSelectUserById() {
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserById("1");
        System.out.println(user);
    }
}
