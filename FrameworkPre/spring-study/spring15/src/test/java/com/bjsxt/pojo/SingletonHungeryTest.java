package com.bjsxt.pojo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create by Administrator on 2019/5/11
 */
public class SingletonHungeryTest {

    @Test
    public void getInstance() {
        SingletonHungery singletonHungery1 = SingletonHungery.getInstance();
        SingletonHungery singletonHungery2 = SingletonHungery.getInstance();
        System.out.println(singletonHungery1 == singletonHungery2);
    }
}