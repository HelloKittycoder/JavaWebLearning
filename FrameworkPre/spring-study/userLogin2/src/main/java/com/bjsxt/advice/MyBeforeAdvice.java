package com.bjsxt.advice;

import com.bjsxt.pojo.Users;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by shucheng on 2019-5-10 下午 16:46
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        Users users = (Users) args[0];
        Logger logger = Logger.getLogger(MyBeforeAdvice.class);
        logger.info(users.getUsername() + "在" + new Date().toLocaleString() + "进行登录");
    }
}
