package com.bjsxt.service.impl;

import com.bjsxt.mapper.AirplaneMapper;
import com.bjsxt.pojo.Airplane;
import com.bjsxt.service.AirplaneService;
import com.bjsxt.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by shucheng on 2019-5-6 上午 9:49
 */
public class AirplaneServiceImpl implements AirplaneService {

    @Override
    public List<Airplane> show(int takeid, int landid) {
        SqlSession session = MyBatisUtil.getSession();
        AirplaneMapper airplaneMapper = session.getMapper(AirplaneMapper.class);
        return airplaneMapper.selByTakeidLandid(takeid, landid);
    }
}
