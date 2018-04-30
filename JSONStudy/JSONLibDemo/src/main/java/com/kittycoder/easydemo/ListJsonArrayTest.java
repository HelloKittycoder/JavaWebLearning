package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shucheng on 2018/4/30.
 * 06 JSONArray与List互转
 */
public class ListJsonArrayTest {
    public static void main(String[] args) {
        // List转JSONArray
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("1", "张三", 10));
        list.add(new Student("2", "李四", 20));
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray);

        // JSONArray转List
        List<Student> list1 = JSONArray.toList(jsonArray, new Student(), new JsonConfig());
        Iterator<Student> ite = list1.iterator();
        while (ite.hasNext()) {
            Student stu = ite.next();
            System.out.println(stu);
        }
    }
}
