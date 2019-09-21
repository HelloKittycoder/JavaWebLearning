package com.kittycoder.binding;

import com.kittycoder.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by shucheng on 2019-9-21 下午 16:00
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        String statement = method.getDeclaringClass().getName() + "." + method.getName();

        // 检查返回类型是否为Collection的子类
        if (Collection.class.isAssignableFrom(returnType)) {
            return sqlSession.selectList(statement, args == null ? null : args[0]);
        } else {
            return sqlSession.selectOne(statement, args == null ? null : args[0]);
        }
    }
}
