package com.kittycoder.action;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.kittycoder.po.PageResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by shucheng on 2018/4/16.
 * 用于模拟页面返回map的例子
 */
public class MapAction {

    private PageResult pageResult;

    public String testMap() {
        try {
            if(pageResult == null) {
                pageResult = new PageResult();
            }
            // 生成HashMap
            HashMap<String, String> stringHashMap = new HashMap<String, String>();
            stringHashMap.put("one","张三1");
            stringHashMap.put("two","李四");
            stringHashMap.put("three","王五");
            stringHashMap.put("four","赵六");
            stringHashMap.put("five","钱七");
            stringHashMap.put("six","郑八");
            pageResult.setStringHashMap(stringHashMap);

            // 生成LinkedMap
            LinkedHashMap<String, String> stringLinkedHashMap = new LinkedHashMap<String, String>();
            stringLinkedHashMap.put("one","张三");
            stringLinkedHashMap.put("two","李四");
            stringLinkedHashMap.put("three","王五");
            stringLinkedHashMap.put("four","赵六");
            stringLinkedHashMap.put("five","钱七");
            stringLinkedHashMap.put("six","郑八");
            pageResult.setStringLinkedHashMap(stringLinkedHashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }
}
