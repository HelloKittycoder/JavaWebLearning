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

        request = post("/demo");
        mockMvc.perform(request)
            .andExpect(forwardedUrl("demo2")) // 最终请求的路径为demo2
            .andExpect(handler().methodName("demo")); // controller方法名称为demo
    }

    @Test
    public void demo2() throws Exception {

        RequestBuilder request = null;

        request = post("/demo2");
        mockMvc.perform(request)
            .andExpect(forwardedUrl("/main.jsp")) // 最终请求的路径为/main.jsp
            .andExpect(handler().methodName("demo2")); // controller方法名称为demo2
    }
}