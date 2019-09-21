package com.kittycoder.session;

/**
 * Created by shucheng on 2019-9-20 下午 23:35
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
