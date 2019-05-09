package com.bjsxt.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-5-9 下午 16:27
 */
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行后置通知，拦截的方法名称为==========" + method.getName());
    }
}
