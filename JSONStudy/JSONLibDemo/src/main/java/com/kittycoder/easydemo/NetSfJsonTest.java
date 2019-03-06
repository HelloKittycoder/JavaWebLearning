package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Test;

import java.util.*;

/**
 * Created by shucheng on 2019/3/6.
 * NetSfJson单元测试
 */
public class NetSfJsonTest {

    // 01 创建json对象
    @Test
    public void jsonObjectTest() {
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

    // 02 创建json数组
    @Test
    public void jsonArrayTest() {
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

    // 03 JavaBean与json字符串互转
    @Test
    public void beanJsonTest() {
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

    // 04 List与json字符串互转
    @Test
    public void listJsonTest() {
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

    // 05 JSONArray与数组互转
    @Test
    public void arrayJsonArrayTest() {
        // Java数组转JSONArray
        boolean[] boolArray = new boolean[]{true, false, true};
        JSONArray jsonArray = JSONArray.fromObject(boolArray);
        System.out.println(jsonArray);

        // JSONArray转Java数组
        Object[] obj = jsonArray.toArray();
        for (Object o : obj) {
            System.out.print(o + " ");
        }
    }

    // 06 JSONArray与List互转
    @Test
    public void listJsonArrayTest() {
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

    // 07 Map与json字符串互转
    @Test
    public void mapJsonTest() {
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
