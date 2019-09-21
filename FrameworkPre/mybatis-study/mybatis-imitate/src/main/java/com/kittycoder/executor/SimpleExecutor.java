package com.kittycoder.executor;

import com.kittycoder.mapping.MapStatement;
import com.kittycoder.session.Configuration;
import com.kittycoder.util.ReflectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-9-21 下午 16:24
 */
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(MapStatement ms, Object parameter) {
        List<E> ret = new ArrayList<>();
        Class<E> clazz = null;
        try {
            clazz = (Class<E>) Class.forName(ms.getResultType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 加载驱动
            Class.forName(configuration.getDriverClass());
            // 建立连接
            conn = DriverManager.getConnection(configuration.getJdbcUrl(),
                    configuration.getUsername(), configuration.getPassword());

            // 创建sql语句
            ps = conn.prepareStatement(ms.getSqlStatement());
            parameterized(ps, parameter);
            // 执行查询，返回结果集
            rs = ps.executeQuery();

            // 放到list中
            while (rs.next()) {
                /*E e = clazz.newInstance();
                Field idField = clazz.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(e, rs.getLong("id"));

                Field nameField = clazz.getDeclaredField("name");
                nameField.setAccessible(true);
                nameField.set(e, rs.getString("name"));

                Field ageField = clazz.getDeclaredField("age");
                ageField.setAccessible(true);
                ageField.set(e, rs.getInt("age"));

                ret.add(e);*/
                ReflectUtil.setValueToEntity(clazz, ret, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    // 暂时只支持设置单个参数，后续可以优化，改成能设置多个参数的
    private void parameterized(PreparedStatement ps, Object parameter) throws Exception {
        if (parameter instanceof Long) {
            ps.setLong(1, (Long) parameter);
        } else if (parameter instanceof String) {
            ps.setString(1, (String) parameter);
        } else if (parameter instanceof Integer) {
            ps.setInt(1, (Integer) parameter);
        } else if (parameter instanceof Object) {
            ps.setObject(1, parameter);
        }
    }
}
