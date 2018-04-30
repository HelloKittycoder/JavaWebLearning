package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shucheng on 2018/4/30.
 * 03 JavaBean与json字符串互转
 */
public class BeanJsonTest {
    public static void main(String[] args) {
        // JavaBean对象转json字符串
        Student stu1 = new Student("1", "张三", 10);
        JSONObject jsonObject = JSONObject.fromObject(stu1);
        System.out.println(jsonObject);

        // json字符串转JavaBean
        String jsonData = "{\"sid\":\"1\",\"sage\":10,\"sname\":\"张三\"}";
        JSONObject jsonObject1 = JSONObject.fromObject(jsonData);
        Student stu2 = (Student) JSONObject.toBean(jsonObject1, Student.class);
        System.out.println(stu2);
    }
}
