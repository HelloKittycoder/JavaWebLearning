package com.kittycoder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shucheng on 2020/1/30 17:49
 */
@Controller
public class HelloController {

    @Value("${person.last-name}")
    private String name;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello" + name;
    }
}
