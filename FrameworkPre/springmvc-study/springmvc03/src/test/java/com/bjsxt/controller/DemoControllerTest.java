package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Create by Administrator on 2019/5/12
 * 测试DemoController
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest extends BaseTest {

    @Test
    public void demo() throws Exception {
        RequestBuilder request = null;

        request = post("/demo")
                .param("name", "张三")
                .param("age", "11");
        mockMvc.perform(request);
    }

    @Test
    public void demo2() throws Exception {

        RequestBuilder request = null;

        request = post("/demo2")
                .param("name", "张三")
                .param("age", "11");
        mockMvc.perform(request);
    }

    @Test
    public void demo3() throws Exception {

        RequestBuilder request = null;

        request = post("/demo3")
                .param("name", "张三")
                .param("age", "11");
        // 判断request中的属性“demo123”是否为"测试"
        // 参考连接：https://blog.trifork.com/2012/12/11/properly-testing-spring-mvc-controllers/
        mockMvc.perform(request)
            .andExpect(request().attribute("demo123", "测试"));
    }

    @Test
    public void page() throws Exception {

        RequestBuilder request = null;

        request = post("/page")
                .param("name1", "张三")
                .param("age1", "11");
        mockMvc.perform(request);
    }

    @Test
    public void page2() throws Exception {

        RequestBuilder request = null;

        request = post("/page2");
        mockMvc.perform(request);
    }

    @Test
    public void page3() throws Exception {

        RequestBuilder request = null;

        request = post("/page3")
            .param("age", "11");
        mockMvc.perform(request)
            .andExpect(status().isBadRequest()) // 判断请求状态是否为400
            .andDo(print()); // 打印请求及响应信息
    }

    @Test
    public void page4() throws Exception {

        RequestBuilder request = null;

        request = post("/page4");
        mockMvc.perform(request);
    }
}