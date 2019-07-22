package com.kittycoder.util;

import com.kittycoder.baseUsageTest.po.Student;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-7-22 下午 21:43
 */
public class ListToJSONTest {

    @Test
    public void test() {
        List<Student> studentList = new ArrayList<Student>();
        Student student = new Student("1", "张三", 10);
        studentList.add(student);
        student = new Student("2", "李四", 20);
        studentList.add(student);
        student = new Student("3", "王五", 30);
        studentList.add(student);
        student = new Student("4", "赵六", 40);
        studentList.add(student);
        JSONObject jsonObject = ListToJSON.parseListToJSON2("studentList", studentList);
        System.out.println(jsonObject);
    }
}
