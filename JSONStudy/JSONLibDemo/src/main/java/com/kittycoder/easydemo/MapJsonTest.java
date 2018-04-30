package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by shucheng on 2018/4/30.
 * 05 List与json字符串互转
 */
public class MapJsonTest {
    public static void main(String[] args) {
        // Map转json字符串
        Map map = new HashMap();
        map.put(1, new Student("1", "张三", 10));
        map.put(2, new Student("2", "李四", 20));
        JSONObject jsonMap = JSONObject.fromObject(map);
        System.out.println(jsonMap);

        // json字符串转Map
        String jsonData = "{\"2\":{\"sid\":\"2\",\"sage\":20,\"sname\":\"李四\"},\"1\":{\"sid\":\"1\",\"sage\":10,\"sname\":\"张三\"}}";
        Map map1 = JSONObject.fromObject(jsonData);
        Set set = map1.keySet(); // 获取map中的所有键
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            JSONObject jsonObject = JSONObject.fromObject(map1.get(key));
            Student stu = (Student) JSONObject.toBean(jsonObject, Student.class);
            System.out.println(key + " " + stu);
        }
    }
}
