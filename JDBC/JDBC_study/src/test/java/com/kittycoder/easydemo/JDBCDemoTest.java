package com.kittycoder.easydemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shucheng on 2018/4/30.
 */
public class JDBCDemoTest {
    @Test
    public void queryAll() throws Exception {
        JDBCDemo.queryAll();
    }

    @Test
    public void insert() throws Exception {
        JDBCDemo.insert();
    }

    @Test
    public void update() throws Exception {
        JDBCDemo.update();
    }

    @Test
    public void delete() throws Exception {
        JDBCDemo.delete();
    }
}