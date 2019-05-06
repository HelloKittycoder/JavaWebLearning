package com.bjsxt.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.BDDMockito.given;

/**
 * 测试ShowTakeServlet（写法1）
 * 参考链接：https://zhizizhishou0104.iteye.com/blog/1983728
 * https://blog.csdn.net/wsh596823919/article/details/80747833
 */
public class ShowTakeServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher dispatcher;
    private ShowTakeServlet instance;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        instance = new ShowTakeServlet();
    }

    @Test
    public void service() throws Exception {
        instance.init(servletConfig);
        given(servletConfig.getServletContext()).willReturn(servletContext);
        given(request.getRequestDispatcher("showland")).willReturn(dispatcher);
        PrintWriter pw = new PrintWriter(System.out, true);
        given(response.getWriter()).willReturn(pw);
        instance.service(request, response);
        pw.flush();
        Assert.assertNotNull(response);
    }
}