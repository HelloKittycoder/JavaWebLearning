package com.bjsxt.controller;

import com.bjsxt.pojo.People;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    @RequestMapping("demo")
    public String demo(String name, int age) {
        System.out.println("执行了demo==" + name + "  " + age);
        return "main.jsp";
    }

    @RequestMapping("demo2")
    public String demo2(People peo) {
        System.out.println("执行了demo2==" + peo);
        return "main.jsp";
    }

    @RequestMapping("demo3")
    public String demo3(People peo, String name, int age, HttpServletRequest req) {
        System.out.println("执行了demo3==" + peo);
        req.setAttribute("demo123", "测试");
        return "main.jsp";
    }

    // 解决前后端传参不一致，导致参数无法接收到的问题（参数前没有任何注解时，引用数据类型不传默认是null，基本数据类型不传会直接报错）
    @RequestMapping("page")
    public String page(@RequestParam("name1") String name, @RequestParam("age1") int age) {
        System.out.println("执行了page==" + name + "  " + age);
        return "main.jsp";
    }

    // 解决参数为基本数据类型，不传参数时无法转换为null抛异常的问题
    @RequestMapping("page2")
    public String page2(@RequestParam(defaultValue = "2") int pageSize, @RequestParam(defaultValue = "1") int pageNumber) {
        System.out.println("执行了page2==" + pageSize + "  " + pageNumber);
        return "main.jsp";
    }

    // 要求前端必须传参数，不然直接报错
    // 说明：@RequestParam(required = false)对基本数据类型无效，写与不写都是要传的；所以RequestParam的required属性其实就是给引用数据类型配的
    // 不配置@RequestParam时，不传引用数据类型时，对应参数为null；不传基本数据类型，直接报错，因为无法转换为null
    // 配置@RequestParam时，如果设置成false，与该参数前不写注解是等效的，都表示不是必传参数；如果设置成true，说明必须传参，不然响应400
    @RequestMapping("page3")
    public String page3(@RequestParam() String name, int age) {
        System.out.println("执行了page3==" + name + "  " + age);
        return "main.jsp";
    }

    // 这样写虽然是强制传递，但是因为后面有默认值，所以不传值也不会出现400
    @RequestMapping("page4")
    public String page4(@RequestParam(required = true, defaultValue = "测试") String name) {
        System.out.println("执行了page4==" + name);
        return "main.jsp";
    }
}
