package com.kittycoder.easydemo;

import com.kittycoder.util.DbUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shucheng on 2018/4/30.
 */
public class JDBCDemo01Test {

    @Before
    public void setUp() throws Exception {
        DbUtil.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/guan?useUnicode=true&characterEncoding=UTF8",
                "root", "123456");
        DbUtil.updateBatchByFile("src/main/resources/sql/init.sql");
        DbUtil.close();
    }

    @Test
    public void queryAll() throws Exception {
        JDBCDemo01.queryAll();
    }

    @Test
    public void insert() throws Exception {
        JDBCDemo01.insert();
        JDBCDemo01.queryAll();
    }

    @Test
    public void update() throws Exception {
        JDBCDemo01.insert();
        JDBCDemo01.update();
        JDBCDemo01.queryAll();
    }

    @Test
    public void delete() throws Exception {
        JDBCDemo01.insert();
        JDBCDemo01.update();
        JDBCDemo01.delete();
        JDBCDemo01.queryAll();
    }

}