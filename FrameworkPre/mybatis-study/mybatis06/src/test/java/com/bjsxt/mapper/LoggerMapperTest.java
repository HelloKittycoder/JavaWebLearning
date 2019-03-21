package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Log;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Administrator on 2019/3/21
 */
public class LoggerMapperTest extends BaseTest {

    private LogMapper logMapper;

    @Before
    public void init() throws Exception {
        logMapper = session.getMapper(LogMapper.class);
    }

    @Test
    public void selAll() {
        /**
         * 接口，为什么能实例化？
         *
         * 需要给接口一个实例化对象
         *
         * 使用的是jdk的动态代理设计模式，即面向接口的代理设计模式（必须有接口）
         */
        List<Log> list = logMapper.selAll();
        for (Log log : list) {
            System.out.println(log);
        }
    }

    @Test
    public void selByAccInAccOut() {
        List<Log> list = logMapper.selByAccInAccOut("3", "1");
        for (Log log : list) {
            System.out.println(log);
        }
    }
}