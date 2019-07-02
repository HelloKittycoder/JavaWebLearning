package com.kittycoder.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kittycoder.po.Student;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2019-5-18 上午 0:29
 * https://blog.csdn.net/u010999809/article/details/90301626
 */
public class FastJsonUtilTest {

    /**
     * 不使用convertToBean时的常规写法
     */
    @Test
    public void test1() {
        String listJsonData1 = "[\"张三\",\"李四\",\"王五\"]";
        List<String> list1 = JSON.parseObject(listJsonData1, new TypeReference<List<String>>(){});
        System.out.println(list1);

        String listJsonData2 = "[{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}]";
        List<Student> list2 = JSON.parseObject(listJsonData2, new TypeReference<List<Student>>(){});
        System.out.println(list2);

        // 转换成Map<Integer, Student>
        String mapJsonData = "{\"1\":{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},\"2\":{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}}";
        Map<Integer, Student> map = JSON.parseObject(mapJsonData, new TypeReference<Map<Integer, Student>>(){});
        System.out.println(map);
    }

    /**
     * 测试convertToBean工具方法
     */
    @Test
    public void convertToBean() {
        // 转换成List<String>
        String listJsonData1 = "[\"张三\",\"李四\",\"王五\"]";
        List<String> list1 = FastJsonUtil.convertToBean(listJsonData1, new Type[]{String.class}, List.class);
        System.out.println(list1);

        // 转换成List<String>
        String listJsonData2 = "[{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}]";
        List<Student> list2 = FastJsonUtil.convertToBean(listJsonData2, new Type[]{Student.class}, List.class);
        System.out.println(list2);

        // 转换成Map<Integer, Student>
        String mapJsonData = "{\"1\":{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},\"2\":{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}}";
        Map<Integer, Student> map = FastJsonUtil.convertToBean(mapJsonData, new Type[]{Integer.class, Student.class}, Map.class);
        System.out.println(map);
    }

    @Test
    public void test2() {
        // 转换成Map<Integer, Student>
        // 写法一：
        String mapJsonData = "{\"1\":{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},\"2\":{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}}";
        Map<Integer, Student> map = FastJsonUtil.convertToBean(mapJsonData, new Type[]{Integer.class, Student.class}, Map.class);
        System.out.println(map);

        // 写法二：（可以稍微作下封装）
        Map<Integer, Student> map2 = JSON.parseObject(mapJsonData, new HashMap<Integer, Student>(){}.getClass().getGenericSuperclass());
        System.out.println(map2);
    }

    // 泛型擦除
    @Test
    public void test3() {
        List<String> list1 = new ArrayList<String>();
        List<Integer> list2 = new ArrayList<Integer>();
        System.out.println(list1.getClass() == list2.getClass());
    }

    // 泛型未被擦除
    // 【泛型不会被擦除的情形之一】如果一个类实例（list1）是声明的泛型类（ArrayList<String>）的子类时，
    // 那么这个类实例（list1）的泛型不会被擦除
    @Test
    public void test4() {
        List<String> list1 = new ArrayList<String>(){};
        List<Integer> list2 = new ArrayList<Integer>(){};
        // 判断两个子类的类型是否相同（false）
        System.out.println(list1.getClass() == list2.getClass());
        // 判断list1和list2是否为List的子类或子接口（都为true）
        System.out.println(List.class.isAssignableFrom(list1.getClass()));
        System.out.println(List.class.isAssignableFrom(list2.getClass()));

        // 获取父类声明的类型（java.util.ArrayList<java.lang.String>和java.util.ArrayList<java.lang.Integer>）
        System.out.println(list1.getClass().getGenericSuperclass());
        System.out.println(list2.getClass().getGenericSuperclass());

        // 获取声明的类型参数（class java.lang.String和class java.lang.Integer）
        ParameterizedType type1 = (ParameterizedType) list1.getClass().getGenericSuperclass();
        ParameterizedType type2 = (ParameterizedType) list2.getClass().getGenericSuperclass();
        System.out.println(type1.getActualTypeArguments()[0]);
        System.out.println(type2.getActualTypeArguments()[0]);
    }
}
