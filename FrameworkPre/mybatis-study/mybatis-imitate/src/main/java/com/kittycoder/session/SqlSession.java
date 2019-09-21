package com.kittycoder.session;

import java.util.List;

/**
 * Created by shucheng on 2019-9-20 下午 23:36
 */
public interface SqlSession {

    <T> T selectOne(String statement, Object paramater);

    <E> List<E> selectList(String statement, Object paramater);

    <T> T getMapper(Class<T> type);
}
