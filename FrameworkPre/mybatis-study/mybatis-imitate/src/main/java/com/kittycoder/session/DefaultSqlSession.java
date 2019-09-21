package com.kittycoder.session;

import com.kittycoder.binding.MapperProxy;
import com.kittycoder.executor.Executor;
import com.kittycoder.executor.SimpleExecutor;
import com.kittycoder.mapping.MapStatement;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by shucheng on 2019-9-21 上午 10:04
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String statement, Object paramater) {
        List<T> list = selectList(statement, paramater);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("selectOne只能返回一条查询结果");
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object paramater) {
        MapStatement mapStatement = configuration.getMapStatements().get(statement);
        return executor.query(mapStatement, paramater);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{type}, new MapperProxy(this));
    }
}
