package com.bjsxt.mapper;

import com.bjsxt.pojo.Airplane;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shucheng on 2019-5-6 上午 9:25
 */
public interface AirplaneMapper {

    List<Airplane> selByTakeidLandid(@Param("takeid") int takeid, @Param("landid") int landid);
}
