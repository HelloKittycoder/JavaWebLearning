package com.kittycoder.easydemo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by shucheng on 2018/4/30.
 * 01 JSONObject
 */
public class JsonObjectTest {

    public static void main(String[] args) {
        // 创建JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "root");
        jsonObject.put("password", "123456");
        // 打印:1
        System.out.println(jsonObject);

        // 增加属性，打印:2
        jsonObject.element("sex", "男");
        System.out.println(jsonObject);

        // 根据key返回，打印:3
        System.out.println(jsonObject.get("sex"));

        // 判断输出对象的类型
        boolean isArray = jsonObject.isArray();
        boolean isEmpty = jsonObject.isEmpty();
        boolean isNullObject = jsonObject.isNullObject();

        // 打印:4
        System.out.println("是否数组:" + isArray
                + "是否空:" + isEmpty);

        // 把JSONArray增加到JSONObject中
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "root");
        jsonArray.add(1, "scott");
        // 开始增加
        jsonObject.element("student", jsonArray);
        // 打印:5
        System.out.println(jsonObject);
    }
}
