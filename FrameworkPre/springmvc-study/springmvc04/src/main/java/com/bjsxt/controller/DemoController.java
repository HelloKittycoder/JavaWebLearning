package com.bjsxt.controller;

import com.bjsxt.pojo.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    @RequestMapping("demo")
    public String demo(String name, int age, @RequestParam("hobbies") List<String> abc) {
        System.out.println("执行了demo==" + name + "  " + age + "  " + abc);
        return "main.jsp";
    }

    @RequestMapping("demo2")
    public String demo2(Demo demo) {
        System.out.println("执行了demo2==" + demo);
        return "main.jsp";
    }

    @RequestMapping("demo3")
    public String demo3(Demo demo) {
        System.out.println("执行了demo3==" + demo);
        return "main.jsp";
    }

    @RequestMapping("demo4")
    public String demo4(String name, int age) {
        System.out.println("执行了demo4==" + name + "  " + age);
        return "main.jsp";
    }

    @RequestMapping("demo5/{age1}/{name}")
    public String demo5(@PathVariable String name, @PathVariable("age1") int age) {
        System.out.println("执行了demo4==" + name + "  " + age);
        return "/main.jsp";
    }

    // 前端给map传参数，和demo方法是同样的道理
    @RequestMapping("page")
    public String page(@RequestParam Map<String, String> map) {
        System.out.println("执行了page==" + map);
        return "main.jsp";
    }
}
