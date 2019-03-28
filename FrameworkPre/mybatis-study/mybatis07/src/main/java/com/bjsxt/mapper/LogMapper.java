package com.bjsxt.mapper;

import com.bjsxt.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create by Administrator on 2019/3/21
 */
public interface LogMapper {

    List<Log> testIf(@Param("accIn") String accIn, @Param("accOut") String accOut);

    List<Log> testWhere(@Param("accIn") String accIn, @Param("accOut") String accOut);

    List<Log> testChoose(@Param("accIn") String accIn, @Param("accOut") String accOut);

    int testSet(Log log);

    List<Log> testTrim1(Log log);
    int testTrim2(Log log);

    List<Log> testBind(Log log);

    List<Log> testForEach1(List<Integer> list);
    int testForEach2(List<Integer> list);
    int testForEach3(int accOut);

    List<Log> testInclude();

    List<Log> testMapParams(@Param("params") Map<String, String> params);
}
