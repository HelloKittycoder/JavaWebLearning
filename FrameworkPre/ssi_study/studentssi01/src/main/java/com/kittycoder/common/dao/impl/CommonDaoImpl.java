package com.kittycoder.common.dao.impl;

import com.kittycoder.common.dao.CommonDao;
import com.kittycoder.common.extension.ExtSqlMapClientDaoSupport;

import java.util.List;

/**
 * Created by shucheng on 2018/4/26.
 */
public class CommonDaoImpl extends ExtSqlMapClientDaoSupport implements CommonDao {

    @Override
    public List getAttributeList(Object obj, String action) {
        return getSqlMapClientTemplate().queryForList(action, obj);
    }

    @Override
    public Object getAttributeByPoJo(Object obj, String action) {
        return getSqlMapClientTemplate().queryForObject(action, obj);
    }

    @Override
    public void updateAttributeByPoJo(Object obj, String action) {
        getSqlMapClientTemplate().update(action, obj);
    }

    @Override
    public void insertObject(Object obj, String action) {
        getSqlMapClientTemplate().insert(action, obj);
    }

    @Override
    public void deleteObject(Object obj, String action) {
        getSqlMapClientTemplate().delete(action, obj);
    }
}
