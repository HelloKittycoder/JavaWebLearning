package com.kittycoder.common.extension;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.RequestScope;
import com.kittycoder.common.dao.impl.CommonDaoImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by shucheng on 2018/6/11.
 * https://www.evget.com/article/2010/10/28/15430.html
 */
public class SqlExecutorExt extends SqlExecutor {

    private static Logger logger = Logger.getLogger(SqlExecutorExt.class);

    @Override
    public int executeUpdate(RequestScope request, Connection conn, String sql, Object[] parameters) throws SQLException {
        printSqlInfo(request.getStatement().getId(), sql, parameters);
        return super.executeUpdate(request, conn, sql, parameters);
    }

    @Override
    public void executeQuery(RequestScope request, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
        Logger logger = printSqlInfo(request.getStatement().getId(), sql, parameters);
        long start = System.currentTimeMillis();
        super.executeQuery(request, conn, sql, parameters, skipResults, maxResults, callback);
        long end = System.currentTimeMillis();
        long time = end - start;
        // sql执行时间（ms）
        logger.debug(request.getStatement().getId() + " elasped " + time + "ms");
    }

    // 替换参数后的sql
    public static String generateActualSql(String boundSql, Object[] params) {
        String sql = boundSql.replaceAll("[\\s]+", " ");
        if (params.length == 0) {
            return sql;
        }

        try {
            String[] strArr = sql.split("\\?");
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < strArr.length; i++) {
                sb.append(strArr[i]);

                if (i < strArr.length - 1) {
                    if (params[i] instanceof String) {
                        sb.append("'").append(params[i]).append("'");
                    } else {
                        sb.append(params[i]);
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    // sql信息打印
    public static org.apache.log4j.Logger printSqlInfo(String sqlId, String sql, Object[] parameters) {
        // 获取sql是哪个service执行的
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String actionMethodName = null, serviceMethodName = null;
        boolean condition1, condition2;

        int i = 0;
        while (i < stacks.length) {
            condition1 = ServletActionContext.getContext() != null && ServletActionContext.getContext().get("action") != null
                    && ServletActionContext.getContext().get("action").getClass().getName().equals(stacks[i].getClassName());

            condition2 = CommonDaoImpl.class.getName().equals(stacks[i].getClassName());

            if (condition1) {
                actionMethodName = "[" + ServletActionContext.getContext().getName() + "]"+ stacks[i].getClassName() + "." + stacks[i].getMethodName()
                        + "(" + stacks[i].getLineNumber() +  ")";
            } else if (condition2) {
                serviceMethodName = stacks[i + 1].getClassName() + "." + stacks[i + 1].getMethodName()
                        + "(" + stacks[i + 1].getLineNumber() +  ")";
            }
            if (StringUtils.isNotBlank(actionMethodName) && StringUtils.isNotBlank(serviceMethodName)) {
                break;
            } else {
                i++;
            }
        }

        String info = actionMethodName
                + "********" +serviceMethodName
                + "******sqlId-" + sqlId + "**************\n"
                + generateActualSql(sql, parameters);

        org.apache.log4j.Logger log;
        if (serviceMethodName == null) {
            log = logger;
        } else {
            log = Logger.getLogger(serviceMethodName);
        }

        // action--service--sqlId
        log.error(info);
        return log;
    }
}
