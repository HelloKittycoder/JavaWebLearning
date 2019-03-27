package com.bjsxt.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Create by Administrator on 2019/3/26
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession的方法
     * @return
     */
    public static SqlSession getSession() {
        SqlSession session = tl.get();
        if (session == null) {
            tl.set(factory.openSession());
        }
        return tl.get();
    }

    /**
     * 关闭SqlSession
     */
    public static void closeSession() {
        SqlSession session = tl.get();
        if (session != null) {
            session.close();
        }
        tl.set(null);
    }
}
