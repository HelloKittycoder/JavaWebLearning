package com.bjsxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    @RequestMapping("demo")
    public String demo() {
        System.out.println("执行了demo");
        return "forward:demo2";
    }

    @RequestMapping("demo2")
    public String demo2() {
        System.out.println("执行了demo2");
        return "main";
    }
}
