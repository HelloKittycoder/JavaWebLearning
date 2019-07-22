package com.kittycoder.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2018/5/3.
 *
 * 将List转换为JSON数据，其中json中的key为List中实体对象的key，
 * value为List中的对象中属性在对应的key的汇总
 *
 * 转换前：
 * List中存放3个student
 * Student("1", "张三", 10)
 * Student("2", "李四", 20)
 * Student("3", "王五", 30)
 * Student("4", "赵六", 40)
 *
 * 转换后：
 * {"studentList.sname":["张三","李四","王五","赵六"],
 * "studentList.sage":["10","20","30","40"],
 * "studentList.sid":["1","2","3","4"]}
 */
public class ListToJSON {

    public static <T> JSONObject parseListToJSON(String variableName, List<T> list) {
        JSONObject jsonObject = new JSONObject();
        if(list == null || list.size() == 0){
            return jsonObject;
        }

        Class clazz = list.get(0).getClass(); // 获取list中存放的对象类型
        Field[] fields = clazz.getDeclaredFields(); // 获取list中存放对象所包含的属性
        String prefix = "".equals(variableName)||variableName == null ? "" : variableName+".";

        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (Field field : fields) {
            resultMap.put(prefix + field.getName(), new ArrayList<String>());
        }

        // 组装一个Map
        /*
        构建出的map示例
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sid", new String[]{"1","2","3","4"});
        map.put("sname", new String[]{"张三","李四","王五","赵六"});
        map.put("sage", new String[]{"10","20","30","40"});
        */
        try {
            for (T t:list) {
                for (Field field :fields) {
                    String fieldName = field.getName();
                    ArrayList<String> mapValue = (ArrayList<String>) resultMap.get(prefix + fieldName);
                    field.setAccessible(true);
                    mapValue.add(field.get(t) + "");
                    resultMap.put(prefix + fieldName, mapValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonObject = JSONObject.fromObject(resultMap);
        return jsonObject;
    }

    public static <T> JSONObject parseListToJSON2(String variableName, List<T> list) {
        JSONObject jsonObject = new JSONObject();
        if(list == null || list.size() == 0){
            return jsonObject;
        }

        Class clazz = list.get(0).getClass(); // 获取list中存放的对象类型
        Field[] fields = clazz.getDeclaredFields(); // 获取list中存放对象所包含的属性
        String prefix = "".equals(variableName)||variableName == null ? "" : variableName+".";
        List<String> tempList = null;
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                tempList = new ArrayList<String>();
                for (T t:list) {
                    field.setAccessible(true);
                    tempList.add(field.get(t) + "");
                }
                jsonObject.put(prefix + fieldName, JSONArray.fromObject(tempList));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
