package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Log;
import com.bjsxt.pojo.LogSearch;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Create by Administrator on 2019/3/21
 */
public class LoggerMapperTest extends BaseTest {

    private LogMapper logMapper;
    private String accOut; // 付款账号
    private String accIn; // 收款账号

    @Before
    public void init() throws Exception {
        logMapper = session.getMapper(LogMapper.class);
    }

    // 获取用户在控制台的输入参数
    public void receiveUserParams() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入付款账号：");
        accOut = input.nextLine();
        System.out.println("请输入收款账号：");
        accIn = input.nextLine();
    }

    // 测试if标签
    @Test
    public void testIf() {
        receiveUserParams();
        List<Log> list = logMapper.testIf(accIn, accOut);
        for (Log log : list) {
            System.out.println(log);
        }
    }

    // 测试where标签
    @Test
    public void testWhere() {
        receiveUserParams();
        List<Log> list = logMapper.testWhere(accIn, accOut);
        for (Log log : list) {
            System.out.println(log);
        }
    }

    // 测试choose，when，otherwise标签
    @Test
    public void testChoose() {
        receiveUserParams();
        List<Log> list = logMapper.testChoose(accIn, accOut);
        for (Log log : list) {
            System.out.println(log);
        }
    }

    // 测试set标签
    @Test
    public void testSet() {
        receiveUserParams();
        Log log = new Log();
        log.setId(1);
        log.setAccOut(accOut);
        log.setAccIn(accIn);
        int index = logMapper.testSet(log);
        System.out.println(index);
    }

    // 测试trim标签
    @Test
    public void testTrim1() {
        logMapper.testTrim1(null);
    }

    @Test
    public void testTrim2() {
        logMapper.testTrim2(new Log());
        session.commit();
    }

    // 测试bind标签
    @Test
    public void testBind() {
        Log log = new Log();
        log.setAccIn("3");
        List<Log> list = logMapper.testBind(log);
        for (Log lg : list) {
            System.out.println(lg);
        }
    }

    // 测试foreach标签
    @Test
    public void testForEach1() {
        List<Integer> list = Arrays.asList(new Integer[]{1,2,3});
        List<Log> logList = logMapper.testForEach1(list);
        System.out.println(logList);
    }

    // 批量插入和单条插入对比（testForEach2和testForEach3）
    @Test
    public void testForEach2() {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            list.add(i);
        }
        logMapper.testForEach2(list);
        session.commit();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000.0 + "s"); // 2.931s
    }

    @Test
    public void testForEach3() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            logMapper.testForEach3(i);
        }
        session.commit();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000.0 + "s"); // 23.658s
    }

    @Test
    public void testInclude() {
        logMapper.testInclude();
    }

    // 测试foreach语句读取map
    // https://blog.csdn.net/qioutiandeyun/article/details/77188973
    @Test
    public void testMapParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("accIn", "3");
        params.put("accOut", "1");
        List<Log> list = logMapper.testMapParams(params);
        for (Log lg : list) {
            System.out.println(lg);
        }
    }

    @Test
    public void testMapParams2() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("accIn", "3");
        params.put("accOut", "1");
        LogSearch logSearch = new LogSearch();
        logSearch.setParams(params);
        logSearch.setMoney(100.0);
        List<Log> list = logMapper.testMapParams2(logSearch);
        for (Log lg : list) {
            System.out.println(lg);
        }
    }

    // 测试返回Map数据
    @Test
    public void testReturnMap() {
        Map<String, Object> map = session.selectMap("com.bjsxt.mapper.LogMapper.testReturnMap", "id");
        System.out.println(map);
    }

    // 测试返回boolean（数字>=1时，直接转成true；数字=0时，直接转成false）
    @Test
    public void testReturnBoolean() {
        boolean b = logMapper.testReturnBoolean();
        System.out.println(b);
        boolean o = session.selectOne("com.bjsxt.mapper.LogMapper.testReturnBoolean");
        System.out.println(o);
    }

    @Test
    public void testExecuteSql() {
        Map<String, String> params = new HashMap<>();
        params.put("sql", "select * from log where accin = #{accIn} and accout = #{accOut}");
        // 这种情况有4条数据
        params.put("accIn", "3");
        params.put("accOut", "1");
        /*// 这种情况有2条数据
        params.put("accIn", "3");
        params.put("accOut", "2");*/
        List<Map<String, Object>> result = logMapper.executeSql(params);
        System.out.println(result);
    }

    @Test
    public void testExecuteSqlTwo() {
        Map<String, String> params = new HashMap<>();
        params.put("sql", "select * from log where accin = #{accIn} and accout = #{accOut}");
        // 这种情况有4条数据
        params.put("accIn", "3");
        params.put("accOut", "1");
        /*// 这种情况有2条数据
        params.put("accIn", "3");
        params.put("accOut", "2");*/
        List<Log> result = logMapper.executeSqlTwo(params);
        System.out.println(result);
    }
}
