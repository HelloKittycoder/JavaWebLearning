package com.kittycoder.service;

import com.kittycoder.bean.Employee;
import com.kittycoder.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by shucheng on 2020/2/13 21:03
 */
@CacheConfig(cacheNames = "emp") // 抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中获取，不用调用方法
     * 原理：
     * 1. 自动配置类：org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
     * 2. 缓存的配置类：
     * org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     * 3. 哪个配置类默认生效：SimpleCacheConfiguration
     * 4. 给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     * 5. 可以获取和创建ConcurrentMapCache类型的缓存组件；它的作用是将数据保存在ConcurrentMap中
     *
     * 几个属性：
     * cacheNames/value：指定缓存组件的名字；将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存
     *
     * key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值
     * key = "#root.methodName+'['+#id+']'"       getEmp[2]
     *
     * keyGenerator：key的生成器；可以自己指定key的生成器和组件id
     * key/keyGenerator：二选一使用
     *
     * cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     *
     * condition：指定符合条件的情况下才缓存
     * condition = "#a0>1"
     *
     * unless：否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存
     *
     * sync：是否使用同步模式
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}/*, keyGenerator = "myKeyGenerator", condition = "#a0>1",
            unless = "#a0==2"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存
     * 运行时机：
     * 1. 先调用目标方法
     * 2. 将目标方法的结果缓存起来
     *
     * 注：缓存默认用的key是传入的参数，这里就是employee；
     * 但这和查询放入的缓存用的key不是同一个，所以要改成下面的，
     * key = "#employee.id"或key = "#result.id"
     * 和查询里缓存用的key保持一致
     *
     * org.springframework.cache.interceptor.CacheAspectSupport#generateKey(org.springframework.cache.interceptor.CacheAspectSupport.CacheOperationContext, java.lang.Object)
     * org.springframework.cache.concurrent.ConcurrentMapCache#put(java.lang.Object, java.lang.Object)
     *
     * @param employee
     * @return
     */
    @CachePut(/*value = "emp", */key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 这里默认用的就是 key = "#id"
     *
     * allEntries默认是false，是否清除这个缓存中所有的数据
     *
     * beforeInvocation=false：缓存的清除是否在方法之前执行
     * 默认代表缓存清除操作是在方法执行之后执行；如果出现异常缓存就不会清除
     *
     * beforeInvocation=true：代表清除缓存操作是在方法运行之前执行，
     * 无论方法有无出现异常，缓存都会被清除
     * @param id
     */
    @CacheEvict(value = "emp", beforeInvocation = true)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        // employeeMapper.deleteEmpById(id);
        int i = 1 / 0;
    }

    // @Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
               @Cacheable(/*value = "emp", */key = "#lastName")
            },
            put = {
               @CachePut(/*value = "emp", */key = "#result.id"),
               @CachePut(/*value = "emp", */key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
