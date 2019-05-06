package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Airplane;
import com.bjsxt.pojo.Airport;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AirplaneMapperTest extends BaseTest {

    @Test
    public void selByTakeidLandid1() {
        AirplaneMapper airplaneMapper = session.getMapper(AirplaneMapper.class);
        List<Airplane> list = airplaneMapper.selByTakeidLandid(1, 3);
        for (Airplane airplane : list) {
            System.out.println(airplane);
        }
    }
}