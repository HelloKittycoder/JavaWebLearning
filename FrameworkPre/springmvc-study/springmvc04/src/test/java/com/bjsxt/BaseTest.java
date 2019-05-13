package com.bjsxt;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Create by Administrator on 2019/5/12
 */
@ContextConfiguration(locations = {"classpath:springmvc.xml"})
@WebAppConfiguration
public class BaseTest {

    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
    }

    // 访问controller
    public void accessController(String mappingName) throws Exception {
        String responseString = mockMvc.perform(get(mappingName))
            .andExpect(status().isOk()) // 返回的状态是200
            .andDo(print()) // 打印出请求和响应的内容
            .andReturn().getResponse().getContentAsString(); // 将响应的数据转换为字符串
        System.out.println(responseString);
    }
}
