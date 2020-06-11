package com.kittycoder.common.extension;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.RequestScope;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by shucheng on 2018/6/11.
 */
public class SqlExecutorExt extends SqlExecutor {

    @Override
    public int executeUpdate(RequestScope request, Connection conn, String sql, Object[] parameters) throws SQLException {
        System.out.println("进入SqlExecutorExt实现方法");
        return super.executeUpdate(request, conn, sql, parameters);
    }

    @Override
    public void executeQuery(RequestScope request, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
        System.out.println("SqlExecutorExt查询前");
        super.executeQuery(request, conn, sql, parameters, skipResults, maxResults, callback);
        System.out.println("SqlExecutorExt查询后");
    }
}
