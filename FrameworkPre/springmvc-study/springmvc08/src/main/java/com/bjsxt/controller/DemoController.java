package com.bjsxt.controller;

import com.bjsxt.pojo.People;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    @RequestMapping("demo1")
    public String demo1(HttpServletRequest abc, HttpSession sessionParam) {
        // request作用域
        abc.setAttribute("req", "req的值");

        // session作用域
        HttpSession session = abc.getSession();
        session.setAttribute("session", "session的值");
        sessionParam.setAttribute("sessionParam", "sessionParam的值");

        // application作用域
        ServletContext application = abc.getServletContext();
        application.setAttribute("application", "application的值");

        // 说明：不能通过直接在参数上添加 ServletContext app 来进行注入，spring出于安全考虑
        // 只能通过req.getServletContext()来获取，然后向里面设置值
        // app.setAttribute("app", "app的值");
        return "/index.jsp";
    }

    @RequestMapping("demo2")
    public String demo2(Map<String, Object> map) {
        System.out.println(map.getClass());
        map.put("map", "map的值");
        People peo = new People("张三", 11);
        map.put("mapPeo", peo);
        return "/index.jsp";
    }

    @RequestMapping("demo3")
    public String demo3(Model model) {
        model.addAttribute("model", "model的值");
        People peo = new People("张三", 11);
        model.addAttribute("modelPeo", peo);
        return "/index.jsp";
    }

    @RequestMapping("demo4")
    public ModelAndView demo4() {
        // 参数是要跳转的视图
        ModelAndView mav = new ModelAndView("/index.jsp");
        mav.addObject("mav", "mav的值");
        People peo = new People("张三", 11);
        mav.addObject("mavPeo", peo);
        return mav;
    }

    /*// 在servlet中的写法
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
    }*/
}
