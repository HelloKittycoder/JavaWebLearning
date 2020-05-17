package com.kittycoder;

import com.kittycoder.bean.Employee;
import com.kittycoder.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by shucheng on 2020/1/31 21:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot09CacheApplicationTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 操作k-v都是字符串的

    @Autowired
    private RedisTemplate redisTemplate; // 操作k-v都是对象的

    @Autowired
    private RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void test() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

    /**
     * Redis常见的五大数据类型：
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     */
    @Test
    public void test01() {
        // 字符串
        /*String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);*/

        // 列表
        /*stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");*/

        List<String> list = stringRedisTemplate.opsForList().range("mylist", 0 , -1);
        System.out.println(list);
    }

    // 测试保存对象
    @Test
    public void test02() {
        Employee emp = employeeMapper.getEmpById(1);
        // 默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        // redisTemplate.opsForValue().set("emp-01", emp);
        // 1.将数据以json的方式保存
        // （1）自己将对象转为json
        // （2）redisTemplate默认的序列化规则：改变默认的序列化规则
        empRedisTemplate.opsForValue().set("emp-01", emp);
        Employee employee = empRedisTemplate.opsForValue().get("emp-01");
        System.out.println(employee);
    }
}
