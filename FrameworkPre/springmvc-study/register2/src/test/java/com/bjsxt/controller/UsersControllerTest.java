package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsersControllerTest extends BaseTest {

    // 参看https://github.com/HelloKittycoder/JavaWebLearning/blob/master/FrameworkPre/springmvc-study/springmvc10/src/test/java/com/bjsxt/controller/DemoControllerTest.java
    @Test
    public void register() throws Exception {

        // 创建一个mockFile
        MockMultipartFile mfile = new MockMultipartFile("file",
                "aa.txt", "text/plain", "aa".getBytes());

        String responseText = mockMvc.perform(fileUpload("/register")
                .file(mfile)
                .param("username", "王五")
                .param("password", "123456"))
                .andExpect(redirectedUrl("/show")) // 重定向至/show
                .andDo(print()) // 打印出请求和响应的内容
                .andReturn().getResponse().getContentAsString(); // 将响应的数据转换为字符串

        System.out.println(responseText);
    }
}