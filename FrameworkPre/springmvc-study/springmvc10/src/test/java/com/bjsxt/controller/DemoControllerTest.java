package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Create by Administrator on 2019/5/12
 * 测试DemoController
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest extends BaseTest {

    // 参考链接：https://tobato.iteye.com/blog/2315174
    @Test
    public void uploadFile() throws Exception {

        // 获取一个文件
        String filePath = getWebappPath() + "/files/a.txt";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(filePath);

        MockMultipartFile mfile = new MockMultipartFile("file", file.getName(),
                "text/plain", fis);

        String responseString = mockMvc.perform(fileUpload("/uploadFile")
            .file(mfile)
            .param("name", "aa"))
            .andExpect(status().isOk()) // 返回的状态是200
            .andDo(print()) // 打印出请求和响应的内容
            .andReturn().getResponse().getContentAsString(); // 将响应的数据转换为字符串
        System.out.println(responseString);
    }
}