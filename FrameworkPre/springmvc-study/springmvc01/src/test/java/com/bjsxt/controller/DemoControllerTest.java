package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Create by Administrator on 2019/5/12
 * 测试DemoController
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest extends BaseTest {

    @Test
    public void handleRequest() throws Exception {
        String responseString = mockMvc.perform(get("/demo"))
            .andExpect(status().isOk()) // 返回的状态是200
            .andDo(print()) // 打印出请求和响应的内容
            .andReturn().getResponse().getContentAsString(); // 将响应的数据转换为字符串
        System.out.println(responseString);
    }
}