package com.kittycoder.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by shucheng on 2019-8-29 上午 9:03
 * https://www.cnblogs.com/bailuobo/p/4867408.html
 */
public class TestRedis {

    private Jedis j;

    @Before
    public void setUp() {
        j = new Jedis("192.168.21.161", 6379);
    }

    @After
    public void tearDown() {
        j.close();
    }

    // 测试连通性
    @Test
    public void testConnection() {
        System.out.println(j.ping());
        System.out.println(j);
    }

    // 测试key命令
    @Test
    public void testKeys() {
        // 获取所有的key
        Set<String> allkeys = j.keys("*");
        System.out.println(allkeys);
        // 判断指定名称的key是否存在
        System.out.println(j.exists("name"));
        System.out.println(j.exists("age"));
        // 获取指定名称的key对应的value
        System.out.println(j.get("name"));
        System.out.println(j.get("age"));
    }

    // 测试String类型相关的命令
    @Test
    public void testString() {
        System.out.println("=====================String=====================");
    }
}
