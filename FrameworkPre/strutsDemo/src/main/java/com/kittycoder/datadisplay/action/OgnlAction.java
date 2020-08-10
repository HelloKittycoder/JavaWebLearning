package com.kittycoder.datadisplay.action;

import com.kittycoder.datadisplay.po.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2020/8/9 23:34
 */
public class OgnlAction {

    private Student student;
    private List<Student> studentList;
    private List<Map<String, String>> mapList;

    static {
        putSessionAttrIfAbsent("mystudent", new Student(101L, "测试", "1990-01-01", "22"));
    }

    public String testOgnl() {
        generateStudent();

        ListAction listAction = new ListAction();
        listAction.findStudentList();
        studentList = listAction.getStudentList();

        MapAction mapAction = new MapAction();
        mapAction.testMap();
        mapList = mapAction.getMapList();

        ActionContext.getContext().getSession().put("mystudent", new Student(33L, "测试", "1990-01-01", "22"));

        return ActionSupport.SUCCESS;
    }

    public static void putSessionAttrIfAbsent(String key, Object value) {
        Map<String, Object> sessionMap = ActionContext.getContext().getSession();
        if (!sessionMap.containsKey(key)) {
            sessionMap.put(key, value);
        }
    }

    private void generateStudent() {
        if(student == null) {
            student = new Student();
        }
        student.setSid(1L);
        student.setSname("张三");
        student.setSbirthday("1994-09-13");
        student.setSage("18");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }
}
