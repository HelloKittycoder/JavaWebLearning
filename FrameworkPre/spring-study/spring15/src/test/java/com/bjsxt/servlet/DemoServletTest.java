package com.bjsxt.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Create by Administrator on 2019/5/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration
public class DemoServletTest {

    // @Mock
    private HttpServletRequest request;
    // @Mock
    private HttpServletResponse response;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext servletContext;
    private DemoServlet instance;

    // private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
        instance = new DemoServlet();
        when(WebApplicationContextUtils.getWebApplicationContext(servletContext))
                .thenReturn(wac);
    }

    // 如何用mockito模拟tomcat的多线程请求，区分出每个不同的请求（问题暂未解决，目前还是直接运行tomcat）
    // 未解决部分：下面单元测试中发起了两次请求，但是spring中的teacher2（范围是request）每次得到的结果是相同的，正常情况下应该是不同的
    // 参考：
    // https://stackoverflow.com/questions/50006609/mockito-and-multiple-http-in-spring-boot-test
    @Test
    public void service() throws Exception {
        instance.init(servletConfig);
        given(servletConfig.getServletContext()).willReturn(servletContext);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        instance.service(request, response);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        instance.service(request, response);
    }
}