package com.bjsxt.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * Created by shucheng on 2019-5-9 下午 20:40
 */
public class MyThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("执行异常通知-Schema-based方式，" + ex.getMessage());
    }

    /*public void afterThrowing(Method m, Object[] args, Object target, Exception ex) {
        System.out.println("执行异常通知");
    }*/
}
