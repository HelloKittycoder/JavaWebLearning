package com.kittycoder.datadisplay.action;

import com.kittycoder.datadisplay.po.SelectData;
import com.kittycoder.datadisplay.po.Student;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2018/4/18.
 * 用于模拟页面返回List的例子
 */
public class ListAction {

    private List<Student> studentList;
    private List<SelectData> provinceList;
    private String dataType;

    public String testList() {
        try {
            findStudentList(); // 返回学生list（遍历table）
            findProvinceList(); // 返回省份list（遍历下拉列表）
            if("json".equals(dataType)) {
                return "jsonData";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    /**
     * 返回List<Student>
     * @return
     */
    public void findStudentList() {
        if(studentList == null) {
            studentList = new ArrayList<Student>();
        }
        Student student = new Student();
        student.setSid(1L);
        student.setSname("张三");
        student.setSbirthday("1991-01-01");
        student.setSage("10");
        studentList.add(student);

        student = new Student();
        student.setSid(2L);
        student.setSname("李四");
        student.setSbirthday("1992-02-02");
        student.setSage("20");
        studentList.add(student);

        student = new Student();
        student.setSid(3L);
        student.setSname("王五");
        student.setSbirthday("1993-03-03");
        student.setSage("30");
        studentList.add(student);
    }

    /**
     * 返回学生所在的省份（下拉列表键值对）
     * @return
     */
    public void findProvinceList() {
        if (provinceList == null) {
            provinceList = new ArrayList<SelectData>();
        }
        SelectData selectData = new SelectData();
        selectData.setKey("1");
        selectData.setValue("北京");
        provinceList.add(selectData);

        selectData = new SelectData();
        selectData.setKey("2");
        selectData.setValue("上海");
        provinceList.add(selectData);

        selectData = new SelectData();
        selectData.setKey("3");
        selectData.setValue("广州");
        provinceList.add(selectData);

        selectData = new SelectData();
        selectData.setKey("4");
        selectData.setValue("深圳");
        provinceList.add(selectData);

        selectData = new SelectData();
        selectData.setKey("5");
        selectData.setValue("珠海");
        provinceList.add(selectData);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<SelectData> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<SelectData> provinceList) {
        this.provinceList = provinceList;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
