package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        request = post("/demo");
        mockMvc.perform(request)
            .andDo(print()) // 打印响应数据
            .andExpect(content().contentType("application/json;charset=utf-8"))
            .andExpect(content().json("{\"name\":\"张三\",\"age\":11}")); // 验证json数据
    }

    @Test
    public void demo2() throws Exception {

        RequestBuilder request = null;

        request = post("/demo2");
        mockMvc.perform(request)
             .andDo(print()) // 打印响应数据
             .andExpect(content().contentType("application/json;charset=UTF-8"))
             .andExpect(content().json("{\"name\":\"张三\",\"age\":11}")); // 验证json数据
    }

    @Test
    public void demo3() throws Exception {

        RequestBuilder request = null;

        request = post("/demo3");
        mockMvc.perform(request)
             .andDo(print()) // 打印响应数据
             .andExpect(content().contentType("text/html;charset=utf-8"));
    }
}