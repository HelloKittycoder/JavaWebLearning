package com.bjsxt.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-5-9 下午 16:06
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("********************************前置start*******************************");
        System.out.println("切点方法对象：" + method + "，方法名：" + method.getName());
        if (args != null && args.length > 0) {
            System.out.println("切点方法参数：" + args);
        } else {
            System.out.println("切点方法没有参数");
        }
        System.out.println("切点拦截的调用对象：" + target);
        System.out.println("执行前置通知");
        System.out.println("********************************前置end*******************************");
    }
}
