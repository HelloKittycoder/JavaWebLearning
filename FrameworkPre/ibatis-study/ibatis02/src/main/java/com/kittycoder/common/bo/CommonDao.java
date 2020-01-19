package com.kittycoder.common.bo;

import java.util.List;

/**
 * Created by shucheng on 2020/1/19 21:53
 */
public interface CommonDao {

    /**
     * 查询列表
     */
    <T> List<T> queryForList(String sqlId, Object obj);

    /**
     * 新增
     */
    Integer insert(String sqlId, Object obj);

    /**
     * 修改
     */
    int update(String sqlId, Object obj);

    /**
     * 删除
     */
    int delete(String sqlId, Object obj);
}
