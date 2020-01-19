package com.kittycoder.common.bo;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2020/1/19 21:54
 */
public class CommonDaoImpl implements CommonDao {

    public static final SqlMapClient sqlMap;

    static {
        try {
            String resource = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error----" + e);
        }
    }

    public static SqlMapClient getSqlMapInstance() {
        return sqlMap;
    }

    @Override
    public <T> List<T> queryForList(String sqlId, Object obj) {
        List<T> list = new ArrayList<>();
        try {
            list = sqlMap.queryForList(sqlId, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer insert(String sqlId, Object obj) {
        Integer addNums = 0;
        try {
            addNums = (Integer) sqlMap.insert(sqlId, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addNums;
    }

    @Override
    public int update(String sqlId, Object obj) {
        int updateNums = 0;
        try {
            updateNums = sqlMap.update(sqlId, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateNums;
    }

    @Override
    public int delete(String sqlId, Object obj) {
        int deleteNums = 0;
        try {
            deleteNums = sqlMap.delete(sqlId, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteNums;
    }
}
