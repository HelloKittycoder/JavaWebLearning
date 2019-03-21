package com.bjsxt.service.impl;

import com.bjsxt.pojo.Log;
import com.bjsxt.pojo.PageInfo;
import com.bjsxt.service.LogService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Administrator on 2019/3/21
 */
public class LogServiceImpl implements LogService {
    @Override
    public PageInfo showPage(int pageSize, int pageNum) throws IOException {
        // 加载xml，获取session
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // 传参数
        Map<String, Object> param = new HashMap<>();
        param.put("pageStart", pageSize*(pageNum-1));
        param.put("pageSize", pageSize);
        List<Log> list = session.selectList("com.bjsxt.mapper.logMapper.selPageList", param);
        Integer count = session.selectOne("com.bjsxt.mapper.logMapper.selPageCount");

        // 构建分页对象
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);

        pageInfo.setTotal(count%pageSize==0 ? count/pageSize : count/pageSize+1);
        pageInfo.setList(list);
        return pageInfo;
    }
}
