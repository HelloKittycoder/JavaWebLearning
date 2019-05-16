package com.kittycoder.easydemo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kittycoder.po.Student;
import org.junit.Test;

import java.util.*;

/**
 * Created by shucheng on 2019-5-16 下午 16:23
 * Jackson单元测试
 */
public class JacksonTest {

    private static ObjectMapper mapper = new ObjectMapper();

    // 01 创建json对象
    @Test
    public void jsonObjectTest() {
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("username", "root");
        jsonObject.put("password", "123456");
        // 打印:1
        System.out.println(jsonObject.toString());

        // 增加属性，打印:2
        jsonObject.put("sex", "男");
        System.out.println(jsonObject);

        // 根据key返回，打印:3
        System.out.println(jsonObject.get("sex"));

        // 判断输出对象的类型
        boolean isArray = jsonObject.isArray();
        boolean isNullObject = jsonObject.isNull();

        // 打印:4
        System.out.println("是否数组:" + isArray
                + "是否空:" + isNullObject);

        // 把JSONArray增加到JSONObject中
        ArrayNode jsonArray = mapper.createArrayNode();
        jsonArray.insert(0, "root");
        jsonArray.insert(1, "scott");
        // 开始增加
        jsonObject.set("student", jsonArray);
        // 打印:5
        System.out.println(jsonObject);
    }

    // 02 创建json数组
    @Test
    public void jsonArrayTest() {
        // 创建JSONArray
        ArrayNode jsonArray = mapper.createArrayNode();
        jsonArray.insert(0, "root");
        jsonArray.insert(1, "scott");
        jsonArray.add("test");

        // 打印1
        System.out.println(jsonArray);

        // 根据下标返回，打印:2
        System.out.println(jsonArray.get(0));

        // 根据下标设置新值，打印:3
        jsonArray.remove(0);
        jsonArray.insert(0, "laozhang");
        System.out.println(jsonArray);

        // 把JSONObject放入JSONArray中
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("username", "toor");
        jsonObject.put("password", "123");

        // 开始增加，打印:4
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);

        // 遍历，打印:5
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.print(jsonArray.get(i) + "\t");
        }
    }

    // 03 JavaBean与json字符串互转
    @Test
    public void beanJsonTest() throws Exception {
        // JavaBean对象转json字符串
        Student stu1 = new Student("1", "张三", 10);
        System.out.println(mapper.writeValueAsString(stu1));

        // json字符串转JavaBean
        String jsonData = "{\"sid\":\"1\",\"sname\":\"张三\",\"sage\":10}";
        Student stu2 = mapper.readValue(jsonData, Student.class);
        System.out.println(stu2);
    }

    // 04 List与json字符串互转
    @Test
    public void listJsonTest() throws Exception {
        // List转json字符串
        List list = new ArrayList();
        list.add(new Student("1", "张三", 10));
        list.add(new Student("2", "李四", 20));
        System.out.println(mapper.writeValueAsString(list));

        // json字符串转List
        List<Student> list1 = new ArrayList();
        String jsonData = "[{\"sid\":\"1\",\"sname\":\"张三\",\"sage\":10},{\"sid\":\"2\",\"sname\":\"李四\",\"sage\":20}]";
        list1 = convertJsonStrToList(jsonData, Student.class);
        System.out.println(list1);
    }

    // 05 JSONArray与数组互转
    @Test
    public void arrayJsonArrayTest() throws Exception {
        // Java数组转JSONArray
        boolean[] boolArray = new boolean[]{true, false, true};
        String jsonStr = mapper.writeValueAsString(boolArray);
        System.out.println(jsonStr);

        // JSONArray转Java数组
        boolean[] obj = mapper.readValue(jsonStr, new boolean[]{}.getClass());
        System.out.println(obj);
    }

    // 06 JSONArray与List互转
    @Test
    public void listJsonArrayTest() throws Exception {
        // List转JSONArray
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("1", "张三", 10));
        list.add(new Student("2", "李四", 20));
        String jsonStr = mapper.writeValueAsString(list);
        System.out.println(jsonStr);

        // JSONArray转List
        List<Student> list1 = convertJsonStrToList(jsonStr, Student.class);
        Iterator<Student> ite = list1.iterator();
        while (ite.hasNext()) {
            Student stu = ite.next();
            System.out.println(stu);
        }
    }

    // 07 Map与json字符串互转
    @Test
    public void mapJsonTest() throws Exception {
        // Map转json字符串
        Map map = new HashMap();
        map.put(1, new Student("1", "张三", 10));
        map.put(2, new Student("2", "李四", 20));
        System.out.println(mapper.writeValueAsString(map));

        // json字符串转Map
        String jsonData = "{\"1\":{\"sid\":\"1\",\"sname\":\"张三\",\"sage\":10},\"2\":{\"sid\":\"2\",\"sname\":\"李四\",\"sage\":20}}";
        Map<String, Student> map1 = mapper.readValue(jsonData, getCollectionType(Map.class, String.class, Student.class));
        Set<String> set = map1.keySet(); // 获取map中的所有键
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            Student stu = map1.get(key);
            System.out.println(key + " " + stu);
        }
    }

    /**
     * jackson将json字符串转换成List
     * 参考链接：https://www.jianshu.com/p/aa4e1d60be5b
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> convertJsonStrToList(String jsonStr, Class<T> clazz) throws Exception {
        JavaType javaType = getCollectionType(List.class, clazz);;
        return mapper.readValue(jsonStr, javaType);
    }

    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
