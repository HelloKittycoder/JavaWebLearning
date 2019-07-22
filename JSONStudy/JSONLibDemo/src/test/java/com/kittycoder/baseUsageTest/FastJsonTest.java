package com.kittycoder.baseUsageTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.kittycoder.baseUsageTest.po.Student;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by shucheng on 2019/3/6 下午 21:35.
 * FastJson单元测试
 */
public class FastJsonTest {

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
        jsonObject.put("sex", "男");
        System.out.println(jsonObject);

        // 根据key返回，打印:3
        System.out.println(jsonObject.get("sex"));

        // 判断输出对象的类型
        boolean isArray = isArray(jsonObject);
        boolean isEmpty = jsonObject.isEmpty();

        // 打印:4
        System.out.println("是否数组:" + isArray
                + "是否空:" + isEmpty);

        // 把JSONArray增加到JSONObject中
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "root");
        jsonArray.add(1, "scott");
        // 开始增加
        jsonObject.put("student", jsonArray);
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
        jsonArray.add("test");

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
        JSONObject jsonObject = (JSONObject) JSON.toJSON(stu1);
        System.out.println(jsonObject);

        // json字符串转JavaBean
        String jsonData = "{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"}";
        Student stu2 = JSON.parseObject(jsonData, Student.class);
        System.out.println(stu2);
    }

    // 04 List与json字符串互转
    @Test
    public void listJsonTest() {
        // List转json字符串
        List list = new ArrayList();
        list.add(new Student("1", "张三", 10));
        list.add(new Student("2", "李四", 20));
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        System.out.println(jsonArray);

        // json字符串转List
        List list1 = new ArrayList();
        String jsonData = "[{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}]";
        List<Student> studentList = JSON.parseObject(jsonData, new TypeReference<List<Student>>(){});
        System.out.println(studentList);
    }

    // 05 JSONArray与数组互转
    @Test
    public void arrayJsonArrayTest() {
        // Java数组转JSONArray
        boolean[] boolArray = new boolean[]{true, false, true};
        String jsonStr = JSON.toJSONString(boolArray);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
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
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        System.out.println(jsonArray);

        // JSONArray转List
        List<Student> studentList = JSON.parseObject(jsonArray.toJSONString(), new TypeReference<List<Student>>(){});
        System.out.println(studentList);
    }

    // 06 JSONArray与List互转2
    @Test
    public void test() throws Exception {
        String jsonStr = "[{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}]";

        // JSONArray转List
        // 方法一：通过TypeReference来传递泛型
        TypeReference tr = new TypeReference<List<Student>>(){};
        Type type = tr.getType();
        List<Student> studentList = JSON.parseObject(jsonStr, type);
        System.out.println(studentList);

        // 方法二：手动构建泛型类对应的Type（该方式更为灵活）
        Type type2 = new ParameterizedTypeImpl(new Type[]{Student.class}, null, List.class);
        /*List<Student> list = new ArrayList<Student>();
        String jsonStr2 = JSON.toJSONString(list);*/
        List<Student> studentList2 = JSON.parseObject(jsonStr, type2);
        System.out.println(studentList2);
    }

    // 07 Map与json字符串互转
    @Test
    public void mapJsonTest() {
        // Map转json字符串
        Map map = new HashMap();
        map.put(1, new Student("1", "张三", 10));
        map.put(2, new Student("2", "李四", 20));
        JSONObject jsonMap = (JSONObject) JSON.toJSON(map);
        System.out.println(jsonMap);

        // json字符串转Map
        String jsonData = "{\"1\":{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},\"2\":{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}}";
        Map map1 = JSON.parseObject(jsonData, Map.class);
        Set set = map1.keySet(); // 获取map中的所有键
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            Student stu = JSON.parseObject(map1.get(key).toString(), Student.class);
            System.out.println(key + " " + stu);
        }
    }

    // 07 Map与json字符串互转2
    @Test
    public void mapJsonTest2() {

        // 直接使用TypeReference
        String jsonData = "{\"1\":{\"sname\":\"张三\",\"sage\":10,\"sid\":\"1\"},\"2\":{\"sname\":\"李四\",\"sage\":20,\"sid\":\"2\"}}";
        Map<Integer, Student> map = JSON.parseObject(jsonData, new TypeReference<Map<Integer, Student>>(){});
        System.out.println(map);

        // 使用对应的Type
        TypeReference tr = new TypeReference<Map<Integer, Student>>(){};
        Type type2 = tr.getType();
        Map<Integer, Student> map2 = JSON.parseObject(jsonData, type2);
        System.out.println(map2);

        // 手动构建泛型类（Map<Integer,Student>）对应的Type
        Type type3 = new ParameterizedTypeImpl(new Type[]{Integer.class, Student.class}, null, Map.class);
        Map<Integer, Student> map3 = JSON.parseObject(jsonData, type3);
        System.out.println(map3);
    }

    // 判断是否为json数组
    public boolean isArray(Object object) {
        return object instanceof JSONArray;
    }
}
