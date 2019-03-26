package com.bjsxt.service.impl;

import com.bjsxt.mapper.LogMapper;
import com.bjsxt.pojo.Log;
import com.bjsxt.service.LogService;
import com.bjsxt.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Create by Administrator on 2019/3/25
 */
public class LogServiceImpl implements LogService {
    @Override
    public int insertLog(Log log) {
        SqlSession session = MyBatisUtil.getSession();
        LogMapper mapper = session.getMapper(LogMapper.class);
        return mapper.insertLog(log);
    }
}
