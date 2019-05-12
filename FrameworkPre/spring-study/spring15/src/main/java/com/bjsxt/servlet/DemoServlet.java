package com.bjsxt.servlet;

import com.bjsxt.pojo.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/5/12
 */
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        Teacher t1 = ac.getBean("teacher1", Teacher.class);
        Teacher t2 = ac.getBean("teacher2", Teacher.class);
        Teacher t3 = ac.getBean("teacher3", Teacher.class);
        System.out.println("单例：===" + t1);
        System.out.println("多例：===" + t2);
        System.out.println("每次请求都不一样：===" + t3);
    }
}
