package com.bjsxt.advice;

/**
 * Created by shucheng on 2019-5-9 下午 19:40
 */
public class MyThrowAdvice {
    public void myexception(Exception e) {
        System.out.println("执行异常通知，异常message：" + e.getMessage());
    }
}
