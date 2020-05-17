package com.kittycoder.controller;

import com.kittycoder.autoconfigure.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shucheng on 2020/1/30 17:49
 */
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        String result = helloService.sayHello("xixi");
        System.out.println(result);
        return "Hello World!";
    }
}
