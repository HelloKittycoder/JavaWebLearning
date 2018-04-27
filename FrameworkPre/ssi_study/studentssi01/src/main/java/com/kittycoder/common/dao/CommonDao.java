package com.kittycoder.common.dao;

import java.util.List;

/**
 * Created by shucheng on 2018/4/26.
 */
public interface CommonDao {

    /**
     * 根据对象属性查询List数据
     * @param obj
     * @param action
     * @return
     */
    List getAttributeList(Object obj, String action);

    /**
     * 根据条件查询单个对象
     * @param obj
     * @param action
     * @return
     */
    Object getAttributeByPoJo(Object obj, String action);

    /**
     * 插入、修改、删除实体对象
     * @param obj
     * @param action
     */
    void updateAttributeByPoJo(Object obj, String action);

    /**
     * 插入实体对象
     * @param obj
     */
    void insertObject(Object obj, String action);

    /**
     * 删除实体对象
     * @param obj
     * @param action
     */
    void deleteObject(Object obj, String action);
}
