package com.bjsxt.service;

import com.bjsxt.pojo.Flower;
import com.sun.scenario.effect.Flood;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Create by Administrator on 2019/3/19
 */
public class ServiceTestTest {

    private SqlSession session;

    @Before
    public void setUp() throws Exception {
        // 使用工厂设计模式
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        session.close();
    }

    // 查询分页
    @Test
    public void selectPage() {
        // 第几页
        int pageNum = 1;
        // 每页几条
        int pageSize = 2;
        Map<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageNum - 1)*pageSize);
        map.put("pageSize", pageSize);
        List<Flower> flowerList = session.selectList("a.b.page", map);
        System.out.println(flowerList);
    }

    // 查询所有
    @Test
    public void selectById() {
        Flower flower = session.selectOne("a.b.selectById", 1);
        System.out.println(flower);
    }

    // 新增
    @Test
    public void insert() {
        Flower flower = new Flower();

        // 插入第一条数据
        try {
            flower.setName("name1");
            session.insert("a.b.insertFlower", flower);
        } catch (Exception e) {
            session.rollback();
        }

        // 插入第二条数据（这里会出现字段数据太长的问题）
        try {
            flower.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            session.insert("a.b.insertFlower", flower);
        } catch (Exception e) {
            session.rollback();
        }

        session.commit();
        System.out.println("提交成功");
    }
}