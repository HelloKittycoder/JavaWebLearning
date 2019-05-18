package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

/**
 * Create by Administrator on 2019/5/12
 * 测试DemoController
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest extends BaseTest {

    @Test
    public void demo1() throws Exception {
        RequestBuilder request = null;

        request = post("/demo1");
        mockMvc.perform(request)
            .andDo(print()) // 打印响应数据
             // request作用域
            .andExpect(request().attribute("req", "req的值"))
             // session作用域
            .andExpect(request().sessionAttribute("session", "session的值"))
            .andExpect(request().sessionAttribute("sessionParam", "sessionParam的值"))
             // application作用域
            .andExpect(requestExt().applicationAttribute("application", "application的值"));
    }

    @Test
    public void demo2() throws Exception {

        RequestBuilder request = null;

        request = post("/demo2");
        mockMvc.perform(request)
             .andDo(print()) // 打印响应数据
             .andExpect(request().attribute("map", "map的值"))
             .andExpect(requestExt().hasAttribute("mapPeo"));
    }

    @Test
    public void demo3() throws Exception {

        RequestBuilder request = null;

        request = post("/demo3");
        mockMvc.perform(request)
             .andDo(print()) // 打印响应数据
             .andExpect(request().attribute("model", "model的值"))
             .andExpect(requestExt().hasAttribute("modelPeo"));
    }

    @Test
    public void demo4() throws Exception {

        RequestBuilder request = null;

        request = post("/demo4");
        mockMvc.perform(request)
             .andDo(print()) // 打印响应数据
             .andExpect(request().attribute("mav", "mav的值"))
             .andExpect(requestExt().hasAttribute("mavPeo"));
    }
}