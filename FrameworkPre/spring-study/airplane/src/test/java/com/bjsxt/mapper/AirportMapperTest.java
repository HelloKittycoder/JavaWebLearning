package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Airport;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AirportMapperTest extends BaseTest {

    @Test
    public void selTakePort() {
        List<Airport> list = session.selectList("com.bjsxt.mapper.AirportMapper.selTakePort");
        for (Airport airport : list) {
            System.out.println(airport);
        }
    }

    @Test
    public void selLandPort() {
        List<Airport> list = session.selectList("com.bjsxt.mapper.AirportMapper.selLandPort");
        for (Airport airport : list) {
            System.out.println(airport);
        }
    }
}