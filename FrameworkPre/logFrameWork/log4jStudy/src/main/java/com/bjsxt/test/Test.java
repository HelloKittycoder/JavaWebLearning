package com.bjsxt.test;

import org.apache.log4j.Logger;

/**
 * Create by Administrator on 2019/3/17
 */
public class Test {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Test.class);
        logger.debug("这是一个调试信息");
        logger.info("普通信息");

        try {
            int in = 5/0;
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage());
        }
        System.out.println("结束");
    }
}
