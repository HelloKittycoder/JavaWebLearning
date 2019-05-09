package com.bjsxt.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-5-9 下午 16:06
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行前置通知，拦截的方法名称为==========" + method.getName());
    }
}
