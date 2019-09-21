package com.kittycoder.util;

import com.kittycoder.entity.User;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2019-9-21 下午 21:59
 */
public class ReflectUtil {

    private static Map<Class, Map<String, Class>> classFieldInfoMap = new HashMap<>();

    // 将每一列的属性值设置到实体类的相应字段中
    public static <T> void setValueToEntity(Class<T> clazz, List<T> list, ResultSet rs) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Class> fieldInfoMap = getFieldInfo(clazz);
        for (Map.Entry<String, Class> entry : fieldInfoMap.entrySet()) {
            String fieldName = entry.getKey();
            Class fieldType = entry.getValue();
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                if (fieldType == Long.class) {
                    field.set(t, rs.getLong(fieldName));
                } else if (fieldType == String.class) {
                    field.set(t, rs.getString(fieldName));
                } else if (fieldType == int.class || fieldType == Integer.class) {
                    field.set(t, rs.getInt(fieldName));
                } else {
                    field.set(t, rs.getObject(fieldName));
                }
            } catch (Exception e) {
                // 如果从不存在的列取值，会报错，暂不处理；后续可以优化下
                // e.printStackTrace();
            }
        }
        list.add(t);
    }

    public static Map<String, Class> getFieldInfo(Class<?> clazz) {
        Map<String, Class> fieldInfoMap = classFieldInfoMap.get(clazz);
        if (fieldInfoMap == null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            fieldInfoMap = new HashMap<>();
            for (Field f : declaredFields) {
                fieldInfoMap.put(f.getName(), f.getType());
            }
            classFieldInfoMap.put(clazz, fieldInfoMap);
        }
        return fieldInfoMap;
    }

    public static void main(String[] args) {
        Map<String, Class> fieldInfoMap = getFieldInfo(User.class);
        for (Map.Entry<String, Class> entry : fieldInfoMap.entrySet()) {
            System.out.println(entry.getKey() + "=====" + entry.getValue());
        }
    }
}
