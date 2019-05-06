package com.bjsxt.service.impl;

import com.bjsxt.mapper.AirportMapper;
import com.bjsxt.pojo.Airport;
import com.bjsxt.service.AirportService;
import com.bjsxt.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by shucheng on 2019-5-5 下午 23:35
 */
public class AirportServiceImpl implements AirportService {
    @Override
    public List<Airport> showTakePort() {
        SqlSession session = MyBatisUtil.getSession();
        AirportMapper airportMapper = session.getMapper(AirportMapper.class);
        return airportMapper.selTakePort();
    }

    @Override
    public List<Airport> showLandPort() {
        SqlSession session = MyBatisUtil.getSession();
        AirportMapper airportMapper = session.getMapper(AirportMapper.class);
        return airportMapper.selLandPort();
    }
}
