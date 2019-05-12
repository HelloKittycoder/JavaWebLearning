package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/5/12
 * front设计模式，前端设计模式（用/）
 *
 * /* 会拦截所有请求，所有情况都会找这个servlet来处理
 * / 除了*.jsp会真的去找jsp文件外，剩下的都找这个servlet来处理
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = req.getParameter("control");
        System.out.println(result);
        if ("demo1".equals(result)) {
            demo1(req, resp);
        }
        if ("demo2".equals(result)) {
            demo2();
        }
        if ("demo3".equals(result)) {
            demo3();
        }
        if ("demo4".equals(result)) {
            demo4();
        }
        System.out.println("执行控制器");
    }

    public void demo1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo1被调用");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    public void demo2() {
        System.out.println("demo2被调用");
    }
    public void demo3() {
        System.out.println("demo3被调用");
    }
    public void demo4() {
        System.out.println("demo4被调用");
    }
}
