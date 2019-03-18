package com.bjsxt.mapper;

import com.bjsxt.pojo.Flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Administrator on 2019/3/17
 */
public class Test {

    public static void main(String[] args) throws IOException {
        // 使用工厂设计模式
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // 只传一个参数
        /*Flower flower = session.selectOne("a.b.selectById", 2);
        System.out.println(flower);*/

        // 如果希望传递多个参数，可以使用实体类或map
        // 传入一个实体类
        /*Flower param = new Flower();
        param.setId(2);
        Flower flower = session.selectOne("a.b.selectById1", param);
        System.out.println(flower);*/

        // 传入一个Map参数
        /*Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "百日草");
        Flower flower = session.selectOne("a.b.selectById2", map);
        System.out.println(flower);*/

        // 分页查询
        Map<String, Object> map = new HashMap<>();
        // 显示几个
        int pageSize = 2;
        // 第几页
        int pageNumber = 2;
        map.put("pageStart", (pageNumber-1)*pageSize);
        map.put("pageSize", pageSize);
        List<Flower> flowerList = session.selectList("a.b.page", map);
        System.out.println(flowerList);
        session.close();
    }
}
