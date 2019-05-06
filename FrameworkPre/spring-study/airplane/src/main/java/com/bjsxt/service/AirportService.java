package com.bjsxt.service;

import com.bjsxt.pojo.Airport;

import java.util.List;

/**
 * Created by shucheng on 2019-5-5 下午 23:34
 */
public interface AirportService {

    /**
     * 显示所有起飞机场
     * @return
     */
    List<Airport> showTakePort();

    /**
     * 显示所有降落机场
     * @return
     */
    List<Airport> showLandPort();
}
