package com.bjsxt.mapper;

import com.bjsxt.pojo.Airport;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by shucheng on 2019-5-7 下午 21:31
 */
public interface AirportMapper {

    @Select("select * from airport")
    List<Airport> selAll();
}
