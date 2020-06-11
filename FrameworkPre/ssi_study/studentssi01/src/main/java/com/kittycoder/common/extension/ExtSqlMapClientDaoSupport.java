package com.kittycoder.common.extension;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.lang.reflect.Field;

/**
 * Created by shucheng on 2018/6/11.
 */
public class ExtSqlMapClientDaoSupport extends SqlMapClientDaoSupport {

    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public void initialize() {
        // System.out.println("此时属性都已经设置好了：" + sqlExecutor + getSqlMapClient());

        SqlMapClientImpl sqlMapClient = ((SqlMapClientImpl)getSqlMapClientTemplate().getSqlMapClient());
        SqlMapExecutorDelegate delegate = sqlMapClient.getDelegate();
        // 通过反射把自定义的sqlExecutor设置进去
        try {
            Field sqlExecutorField = SqlMapExecutorDelegate.class.getDeclaredField("sqlExecutor");
            sqlExecutorField.setAccessible(true);
            sqlExecutorField.set(delegate, sqlExecutor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
