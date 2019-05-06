package com.bjsxt.mapper;

import com.bjsxt.pojo.Airport;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by shucheng on 2019-5-5 下午 23:31
 */
public interface AirportMapper {

    // 查询所有的起飞机场
    @Select("select * from airport where id in (select distinct takeid from airplane)")
    List<Airport> selTakePort();

    // 查询所有的降落机场
    @Select("select * from airport where id in (select distinct landid from airplane)")
    List<Airport> selLandPort();
}
