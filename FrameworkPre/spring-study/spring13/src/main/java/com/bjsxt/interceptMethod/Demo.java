package com.bjsxt.interceptMethod;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by shucheng on 2019-5-9 下午 15:28
 */
@Component
public class Demo {

    @Pointcut("execution(* com.bjsxt.interceptMethod.Demo.demo1())")
    public void demo1() throws Exception {
        // int i = 1/0;
        System.out.println("demo1");
    }
}
