package com.kittycoder.datadisplay.action;

import com.kittycoder.datadisplay.po.PageResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

/**
 * Created by shucheng on 2018/4/16.
 * 用于模拟页面返回map的例子
 */
public class MapAction {

    private PageResult pageResult;
    private List<Map<String, String>> mapList;

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

            // 生成List<Map<String, String>>
            if(mapList == null) {
                mapList = new ArrayList<>();
            }
            Map<String, String> map = new HashMap<>();
            map.put("startNodeName", "开始");
            map.put("endNodeName", "项目负责人");
            map.put("sequenceId", "101");
            mapList.add(map);

            map = new HashMap<>();
            map.put("startNodeName", "项目负责人");
            map.put("endNodeName", "部门负责人");
            map.put("sequenceId", "102");
            mapList.add(map);

            map = new HashMap<>();
            map.put("startNodeName", "部门负责人");
            map.put("endNodeName", "总经理");
            map.put("sequenceId", "103");
            mapList.add(map);
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

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }
}
