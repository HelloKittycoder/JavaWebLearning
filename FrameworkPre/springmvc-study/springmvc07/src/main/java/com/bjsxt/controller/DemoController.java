package com.bjsxt.controller;

import com.bjsxt.pojo.People;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    // 在servlet中的写法
    @RequestMapping("demo")
    public void demo(HttpServletResponse resp) throws Exception {
        People p = new People();
        p.setAge(11);
        p.setName("张三");
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(p);

        resp.setContentType("application/json;charset=utf-8"); // 设置响应头，解决中文乱码
        PrintWriter out = resp.getWriter();
        out.print(result);
        out.flush();
        out.close();

        System.out.println("执行了demo");
    }

    @RequestMapping("demo2")
    @ResponseBody // 1. 将People转换为json 2.将响应头设置为application/json
    public People demo2() {
        People p = new People();
        p.setAge(11);
        p.setName("张三");
        System.out.println("执行了demo2");
        return p;
    }

    // produces 设置响应头中Content-Type的取值
    @RequestMapping(value = "demo3", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String demo3() {
        People p = new People();
        p.setAge(11);
        p.setName("张三");
        System.out.println("执行了demo3");
        return "中文";
    }
}
