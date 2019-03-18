package com.bjsxt.mapper;

import com.bjsxt.pojo.Flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Create by Administrator on 2019/3/17
 */
public class Test {

    public static void main(String[] args) throws IOException {
        // 使用工厂设计模式
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        /*XMLConfigBuilder parser = new XMLConfigBuilder(is);
        SqlSessionFactory factory = new DefaultSqlSessionFactory(parser.parse());*/
        SqlSession session = factory.openSession();

        // 1.查询集合
        // 写法一：不用创建FlowerMapper
        List<Flower> flowerList = session.selectList("a.b.selectAll");
        /* 写法二：需要创建FlowerMapper
        FlowerMapper flowerMapper = session.getMapper(FlowerMapper.class);
        List<Flower> flowerList = flowerMapper.selectAll();*/

        /*for (Flower flower : flowerList) {
            System.out.println(flower);
        }*/

        // 2.查询单个对象
        /*int count = session.selectOne("a.b.selectById");
        System.out.println(count);*/
        Flower flower = session.selectOne("a.b.selectById");
        // System.out.println(flower);

        // 3.返回map
        /*Map<Object, Object> map = session.selectMap("a.b.c", "name");
        System.out.println(map);*/

        // 调用a.j类中的查询语句
        flowerList = session.selectList("a.j.selectAll");
        flower = session.selectOne("a.j.selectById");
        session.close();
    }
}
