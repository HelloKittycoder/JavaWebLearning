package com.kittycoder.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.Type;

/**
 * Created by shucheng on 2019-5-17 下午 22:21
 */
public class FastJsonUtil {

    /**
     * 把json字符串转换成带泛型的javaBean
     * @param jsonStr json字符串
     * @param actualArguments 类型参数
     * @param rawType 声明泛型的类
     * @param <T>
     * @return
     */
    public static <T> T convertToBean(String jsonStr, Type[] actualArguments, Type rawType) {
        Type type = new ParameterizedTypeImpl(actualArguments, null, rawType);
        return JSON.parseObject(jsonStr, type);
    }
}
