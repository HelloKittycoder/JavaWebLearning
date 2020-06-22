package com.kittycoder.interceptor;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by shucheng on 2020/3/28 15:22
 * Mybatis Sql打印拦截器
 * 参考链接：https://www.liangzl.com/get-article-detail-34117.html
 */
@Component
@Intercepts({@Signature(
            type = Executor.class,
            method = "update",
            args = {MappedStatement.class, Object.class}),
        @Signature(
            type = Executor.class,
            method = "query",
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
            type = Executor.class,
            method = "query",
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class SqlPrintInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(SqlPrintInterceptor.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = null;
        if (invocation.getArgs().length > 1) {
            parameterObject = invocation.getArgs()[1];
        }
        String statementId = ms.getId();
        BoundSql boundSql = ms.getBoundSql(parameterObject); // 获取关联的sql
        String sql = assembleSql(boundSql, parameterObject, ms.getConfiguration());
        // sql信息打印
        Logger log = printSqlInfo(statementId, sql);

        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        long timing = end - start;
        // sql执行时间（ms）
        log.info(statementId + " elasped " + timing + " ms");
        return result;
    }

    // sql信息打印
    private static Logger printSqlInfo(String sqlId, String sql) {
        // 获取sql是哪个service执行的
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String requestURI = null, serviceMethodName = null;
        boolean condition2;

        int i = 0;
        while (i < stacks.length) {
            condition2 = MapperProxy.class.getName().equals(stacks[i].getClassName());

            if (condition2) {
                serviceMethodName = stacks[i + 2].getClassName() + "." + stacks[i + 2].getMethodName()
                        + "(" + stacks[i + 2].getLineNumber() +  ")";
            }
            i++;
        }

        if (RequestContextHolder.getRequestAttributes() != null) {
            requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
        }
        String info = requestURI
                + "********" +serviceMethodName
                + "******sqlId-" + sqlId + "**************\n"
                + sql;

        // System.out.println(stacks);
        Logger log;
        if (serviceMethodName == null) {
            log = logger;
        } else {
            log = LoggerFactory.getLogger(serviceMethodName);
        }

        // controller--service--sqlId
        log.info(info);
        return log;
    }

    // 拼接sql
    private String assembleSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (parameterMappings != null) {
            int i = 0;
            while (i < parameterMappings.size()) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        // 使用mybatis自带的反射工具获取属性值，和我们手动获取parameterObject的propertyName的Field本质上是一样的
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    sql = replacePlaceholder(sql, value);
                }
                i++;
            }
        }
        return sql;
    }

    // 替换占位符
    private String replacePlaceholder(String sql, Object propertyValue) {
        String result;
        if (propertyValue != null) {
            if (propertyValue instanceof String) {
                result = "'" + propertyValue + "'";
            } else if (propertyValue instanceof Date) {
                result = "'" + DATE_FORMAT.format(propertyValue) + "'";
            } else {
                result = propertyValue.toString();
            }
        } else {
            result = "null";
        }
        // 为什么使用quoteReplacement？ https://www.cnblogs.com/jingmoxukong/p/6026474.html
        return sql.replaceFirst("\\?", Matcher.quoteReplacement(result));
    }
}
