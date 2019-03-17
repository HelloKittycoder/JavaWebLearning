package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Administrator on 2019/3/16
 * / 代表所有未定义的url-pattern全都走这个处理方法
 * /* 代表所有定义和未定义的url-pattern全都走这个处理方法
 */
// @WebServlet("/")
public class DemoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("执行");
    }
}
