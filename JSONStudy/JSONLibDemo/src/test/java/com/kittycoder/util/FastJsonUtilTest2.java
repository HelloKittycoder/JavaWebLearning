package com.kittycoder.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.kittycoder.po.Project;
import com.kittycoder.po.ResponseEntity;
import com.kittycoder.po.WindPage;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试复杂泛型的转换
 * Created by shucheng on 2020/9/11 21:54
 */
public class FastJsonUtilTest2 {


    /**
     * 数据类型
     * ResponseEntity
     *  -code
     *  -message
     *  -WindPage<Project>
     *      -pageIndex
     *      -pageNum
     *      -List<Project> list
     */
    @Test
    public void test1() {
        String resultJson = generateTestData();
        ResponseEntity<WindPage<Project>> r1 = JSON.parseObject(resultJson, new ResponseEntity<WindPage<Project>>() {}.getClass().getGenericSuperclass());
        System.out.println(r1);

        ResponseEntity<WindPage<Project>> r2 = JSON.parseObject(resultJson, new TypeReference<ResponseEntity<WindPage<Project>>>(){});
        System.out.println(r2);

        ResponseEntity<WindPage<Project>> r3 = JSON.parseObject(resultJson, generateGenericType());
        System.out.println(r3);
    }

    /**
     * 数据类型
     * ResponseEntity
     *  -code
     *  -message
     *  -Project
     */
    @Test
    public void test2() {
        String resultJson = generateTestData2();
        ResponseEntity<Project> r1 = JSON.parseObject(resultJson, new ResponseEntity<Project>() {}.getClass().getGenericSuperclass());
        System.out.println(r1);

        ResponseEntity<Project> r2 = JSON.parseObject(resultJson, new TypeReference<ResponseEntity<Project>>(){});
        System.out.println(r2);

        ParameterizedTypeImpl pt = new ParameterizedTypeImpl(new Type[] {Project.class}, null, ResponseEntity.class);
        ResponseEntity<Project> r3 = JSON.parseObject(resultJson, pt);
        System.out.println(r3);
    }

    public static ParameterizedTypeImpl generateGenericType() {
        // WindPage<Project>
        ParameterizedTypeImpl o = new ParameterizedTypeImpl(new Type[] {Project.class}, null, WindPage.class);
        // ResponseEntity<WindPage<Project>>
        ParameterizedTypeImpl pt = new ParameterizedTypeImpl(new Type[] {o}, null, ResponseEntity.class);
        return pt;
    }

    private String generateTestData() {
        ResponseEntity re = new ResponseEntity();
        re.setCode("10001");
        re.setMessage("正常返回");
        WindPage windPage = new WindPage();
        windPage.setPageIndex(111);
        windPage.setPageNum(222);

        List<Project> list = new ArrayList<Project>();
        Project project = new Project();
        project.setProjectId("p101");
        project.setProjectName("项目全称");
        project.setProjectShortName("项目简称");
        list.add(project);

        project = new Project();
        project.setProjectId("p102");
        project.setProjectName("项目全称2");
        project.setProjectShortName("项目简称2");
        list.add(project);

        project = new Project();
        project.setProjectId("p103");
        project.setProjectName("项目全称3");
        project.setProjectShortName("项目简称3");
        list.add(project);
        windPage.setList(list);

        re.setT(windPage);
        return JSON.toJSONString(re);
    }

    private String generateTestData2() {
        ResponseEntity re = new ResponseEntity();
        re.setCode("10001");
        re.setMessage("正常返回");
        WindPage windPage = new WindPage();
        windPage.setPageIndex(111);
        windPage.setPageNum(222);

        Project project = new Project();
        project.setProjectId("p101");
        project.setProjectName("项目全称");
        project.setProjectShortName("项目简称");

        re.setT(project);
        return JSON.toJSONString(re);
    }
}
