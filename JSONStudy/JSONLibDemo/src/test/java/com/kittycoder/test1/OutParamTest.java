package com.kittycoder.test1;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kittycoder.test1.po.Student_Fj;
import com.kittycoder.test1.po.Student_Jks;
import com.kittycoder.test1.po.Student_Nsf;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by shucheng on 2019-7-22 下午 23:16
 */
public class OutParamTest {

    private List list;

    // https://blog.csdn.net/qq_41570658/article/details/89383110
    @Test
    public void fjTest() throws Exception {
        list = generateList(Student_Fj.class);

        String jsonStr = JSONObject.toJSONString(list);
        System.out.println(jsonStr);
    }

    // https://blessht.iteye.com/blog/2018901
    @Test
    public void nsfTest() throws Exception {
        list = generateList(Student_Nsf.class);

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray.toString());
    }

    // https://www.cnblogs.com/wangshen31/p/8961691.html
    @Test
    public void jksTest() throws Exception {
        list = generateList(Student_Jks.class);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);
        System.out.println(jsonStr);
    }

    // 生成测试用的List
    private <T> List<T> generateList(final Class<T> clazz) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Constructor<T> ctr = clazz.getDeclaredConstructor(Integer.class, String.class, Date.class);
        return new ArrayList<T>(){{
            add(ctr.newInstance(101, "张三", sdf.parse("1994-03-01 06:20:00")));
            add(ctr.newInstance(102, "李四", sdf.parse("1995-06-01 06:20:00")));
            add(ctr.newInstance(103, "王五", sdf.parse("1996-09-01 06:20:00")));
        }};
    }
}

class JsonDateValueProcessor implements JsonValueProcessor {

    private String format = "yyyy-MM-dd HH:mm:ss";

    public JsonDateValueProcessor() {
    }

    public JsonDateValueProcessor(String format) {
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    private Object process(Object value) {
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }
}
