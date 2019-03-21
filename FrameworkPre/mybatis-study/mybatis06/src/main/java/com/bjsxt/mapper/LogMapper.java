package com.bjsxt.mapper;

import com.bjsxt.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by Administrator on 2019/3/21
 */
public interface LogMapper {

    List<Log> selAll();

    /**
     * mybatis把参数转换为map了，其中@Param("key")参数内容就是map的value
     * @param accIn
     * @param accOut
     * @return
     */
    List<Log> selByAccInAccOut(@Param("accIn") String accIn, @Param("accOut") String accOut);
}
