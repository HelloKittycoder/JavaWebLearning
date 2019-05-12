package com.bjsxt.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by Administrator on 2019/5/12
 */
public class DemoController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("执行了springmvc控制器");
        // 跳转页面（转发）和传值
        ModelAndView mav = new ModelAndView("main");
        // 如果没有配置自定义视图解析器，就要写成：
        // ModelAndView mav = new ModelAndView("/WEB-INF/page/main.jsp");
        return mav;
    }
}
