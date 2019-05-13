package com.bjsxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {
    // ModelAndView mav = new ModelAndView("/WEB-INF/page/main.jsp");

    @RequestMapping("demo")
    public String demo() {
        System.out.println("执行了springmvc控制器");
        return "/WEB-INF/page/main.jsp";
    }

    @RequestMapping("demo2")
    public String demo2() {
        System.out.println("demo2");
        return "main.jsp";
    }
}
