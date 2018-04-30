package com.kittycoder.easydemo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by shucheng on 2018/4/30.
 * 02 JSONArray
 */
public class JsonArrayTest {
    public static void main(String[] args) {
        // 创建JSONArray
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "root");
        jsonArray.add(1, "scott");
        jsonArray.element("test");

        // 打印1
        System.out.println(jsonArray);

        // 根据下标返回，打印:2
        System.out.println(jsonArray.get(0));

        // 根据下标设置新值，打印:3
        jsonArray.set(0, "laozhang");
        System.out.println(jsonArray);

        // 把JSONObject放入JSONArray中
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "toor");
        jsonObject.put("password", "123");

        // 开始增加，打印:4
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);

        // 遍历，打印:5
        for(int i = 0; i < jsonArray.size(); i++) {
            System.out.print(jsonArray.get(i) + "\t");
        }
    }
}
