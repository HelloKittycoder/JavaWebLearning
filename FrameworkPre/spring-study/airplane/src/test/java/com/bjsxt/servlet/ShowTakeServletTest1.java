package com.bjsxt.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 测试ShowTakeServlet（写法2）
 * 参考链接：https://stackoverflow.com/questions/5434419/how-to-test-my-servlet-using-junit
 */
public class ShowTakeServletTest1 {

    @Test
    public void service() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("showland")).thenReturn(dispatcher);

        new ShowTakeServlet().service(request, response);
    }
}