package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2018/4/30.
 * 04 List与json字符串互转
 */
public class ListJsonTest {
    public static void main(String[] args) {
        // List转json字符串
        List list = new ArrayList();
        list.add(new Student("1", "张三", 10));
        list.add(new Student("2", "李四", 20));
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray);

        // json字符串转List
        List list1 = new ArrayList();
        String jsonData = "[{\"sid\":\"1\",\"sage\":10,\"sname\":\"张三\"},{\"sid\":\"2\",\"sage\":20,\"sname\":\"李四\"}]";
        JSONArray jsonArray1 = JSONArray.fromObject(jsonData);
        for (int i = 0; i < jsonArray1.size(); i++) {
            JSONObject jsonObject = jsonArray1.getJSONObject(i);
            Student stu2 = (Student) JSONObject.toBean(jsonObject, Student.class);
            list1.add(stu2);
        }
        System.out.println(list1);
    }
}
