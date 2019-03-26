package com.bjsxt.mapper;

import com.bjsxt.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by Administrator on 2019/3/21
 */
public interface LogMapper {

    int insertLog(Log log);
}
