package com.bjsxt.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by shucheng on 2019-5-9 下午 21:23
 */
public class MyAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕-前置");
        Object result = invocation.proceed(); // 放行，调用切点方式
        System.out.println("环绕-后置");
        return result;
    }
}
