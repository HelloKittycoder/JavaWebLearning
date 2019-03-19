package com.bjsxt.service.impl;

import com.bjsxt.pojo.Flower;
import com.bjsxt.pojo.PageInfo;
import com.bjsxt.service.FlowerService;
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
 * 在数据访问层处理异常和在控制器中处理异常，service中只抛出异常
 */
public class FlowerServiceImpl implements FlowerService {
    @Override
    public PageInfo show(int pageNum, int pageSize) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        // 前面是工厂 实例化工厂对象时使用的是 构建者设计模式 名称标志：后面有Builder
        // 构建者设计模式意义：简化对象实例化过程
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        // 如果不使用构建者模式的话，上面的代码要写成
        /*XMLConfigBuilder parser = new XMLConfigBuilder(is);
        SqlSessionFactory factory = new DefaultSqlSessionFactory(parser.parse());*/

        SqlSession session = factory.openSession();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);

        // 查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageStart", pageInfo.getPageStart());
        paramMap.put("pageSize", pageSize);

        // 获取该页数据
        List<Flower> list = session.selectList("a.b.pageList", paramMap);
        pageInfo.setDataList(list);
        // 记录的总条数
        int count = session.selectOne("a.b.pageCount");
        pageInfo.setTotal(count);
        session.close();
        return pageInfo;
    }
}
