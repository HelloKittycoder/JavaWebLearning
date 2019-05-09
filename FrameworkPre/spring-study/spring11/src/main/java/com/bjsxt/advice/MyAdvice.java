package com.bjsxt.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by shucheng on 2019-5-9 下午 21:43
 */
public class MyAdvice {
    public void mybefore() {
        System.out.println("前置");
    }
    public void myafter() {
        System.out.println("后置1");
    }
    public void myaftering() {
        System.out.println("后置2");
    }
    public void mythrow() {
        System.out.println("异常");
    }
    public Object myaround(ProceedingJoinPoint p) throws Throwable {
        System.out.println("执行环绕-start");
        Object result = p.proceed();
        System.out.println("执行环绕-end");
        return result;
    }
}
