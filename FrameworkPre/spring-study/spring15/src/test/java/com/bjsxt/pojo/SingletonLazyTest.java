package com.bjsxt.pojo;

import org.junit.Test;

/**
 * Create by Administrator on 2019/5/11
 */
public class SingletonLazyTest {

    @Test
    public void getInstance() {
        SingletonLazy singletonLazy1 = SingletonLazy.getInstance();
        SingletonLazy singletonLazy2 = SingletonLazy.getInstance();
        System.out.println(singletonLazy1 == singletonLazy2);
    }
}
