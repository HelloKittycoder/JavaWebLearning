package com.bjsxt.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-5-9 下午 16:27
 */
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("********************************后置start*******************************");
        System.out.println("切点方法返回值：" + returnValue);
        System.out.println("切点方法对象：" + method + "，方法名：" + method.getName());
        if (args != null && args.length > 0) {
            System.out.println("切点方法参数：" + args);
        } else {
            System.out.println("切点方法没有参数");
        }
        System.out.println("切点拦截的调用对象：" + target);
        System.out.println("执行后置通知");
        System.out.println("********************************后置end*******************************");
    }
}
