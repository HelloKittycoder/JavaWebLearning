package com.bjsxt.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by shucheng on 2019-5-9 下午 21:43
 */
@Component
@Aspect
public class MyAdvice {
    @Before("com.bjsxt.interceptMethod.Demo.demo1()")
    public void mybefore() {
        System.out.println("前置");
    }

    // 切点方法正常执行时，通知才会执行
    @AfterReturning("com.bjsxt.interceptMethod.Demo.demo1()")
    public void myaftering() {
        System.out.println("后置1");
    }

    // 不管切点是否正常执行，通知都会执行
    @After("com.bjsxt.interceptMethod.Demo.demo1()")
    public void myafter() {
        System.out.println("后置2");
    }

    @AfterThrowing("com.bjsxt.interceptMethod.Demo.demo1()")
    public void mythrow() {
        System.out.println("异常");
    }

    @Around("com.bjsxt.interceptMethod.Demo.demo1()")
    public Object myaround(ProceedingJoinPoint p) throws Throwable {
        System.out.println("环绕-start");
        Object result = p.proceed();
        System.out.println("环绕-end");
        return result;
    }
}
