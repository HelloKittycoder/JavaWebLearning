package com.bjsxt.service.impl;

import com.bjsxt.mapper.AirportMapper;
import com.bjsxt.pojo.Airport;
import com.bjsxt.service.AirportService;

import java.util.List;

/**
 * Created by shucheng on 2019-5-7 下午 21:40
 */
public class AirportServiceImpl implements AirportService {

    private AirportMapper airportMapper;

    @Override
    public List<Airport> show() {
        return airportMapper.selAll();
    }

    public AirportMapper getAirportMapper() {
        return airportMapper;
    }

    public void setAirportMapper(AirportMapper airportMapper) {
        this.airportMapper = airportMapper;
    }
}
