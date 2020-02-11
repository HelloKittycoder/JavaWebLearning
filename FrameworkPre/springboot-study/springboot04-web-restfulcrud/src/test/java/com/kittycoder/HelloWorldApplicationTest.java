package com.kittycoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2020/1/31 21:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTest {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ApplicationContext context;

    @Test
    public void test() {
        logger.trace("trace日志...");
        logger.debug("debug日志...");
        logger.info("info日志...");
        logger.warn("warn日志...");
        logger.error("error日志...");
    }
}
