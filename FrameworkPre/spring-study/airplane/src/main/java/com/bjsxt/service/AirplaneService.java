package com.bjsxt.service;

import com.bjsxt.pojo.Airplane;

import java.util.List;

/**
 * Created by shucheng on 2019-5-6 上午 9:49
 */
public interface AirplaneService {

    List<Airplane> show(int takeid, int landid);
}
