package com.bjsxt.advice;

import com.bjsxt.pojo.Users;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-5-10 下午 16:56
 */
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        Logger logger = Logger.getLogger(MyAfterAdvice.class);
        Users users = (Users) args[0];
        if (returnValue != null) {
            logger.info(users.getUsername() + "登录成功！");
        } else {
            logger.info(users.getUsername() + "登录失败！");
        }
    }
}
