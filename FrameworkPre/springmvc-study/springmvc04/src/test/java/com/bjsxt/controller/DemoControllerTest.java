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
                .param("age", "11")
                .param("hobbies", "学习")
                .param("hobbies", "写代码")
                .param("hobbies", "看视频");
        mockMvc.perform(request);
    }

    @Test
    public void demo2() throws Exception {

        RequestBuilder request = null;

        request = post("/demo2")
                .param("peo.name", "张三")
                .param("peo.age", "11");
        mockMvc.perform(request);
    }

    @Test
    public void demo3() throws Exception {

        RequestBuilder request = null;

        request = post("/demo3")
                .param("peoList[0].name", "张三")
                .param("peoList[0].age", "11")
                .param("peoList[1].name", "李四")
                .param("peoList[1].age", "22");
        mockMvc.perform(request);
    }

    @Test
    public void demo4() throws Exception {

        RequestBuilder request = null;

        request = get("/demo4?name=abc&age=123");
        mockMvc.perform(request);
    }

    @Test
    public void demo5() throws Exception {

        RequestBuilder request = null;

        request = get("/demo5/123/abc");
        mockMvc.perform(request);
    }

    @Test
    public void page() throws Exception {

        RequestBuilder request = null;

        request = post("/page")
                .param("name", "张三")
                .param("age", "11");
        mockMvc.perform(request);
    }
}