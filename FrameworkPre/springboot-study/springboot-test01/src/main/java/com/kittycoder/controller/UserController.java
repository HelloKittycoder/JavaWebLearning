package com.kittycoder.controller;

import com.kittycoder.po.User;
import com.kittycoder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shucheng on 2020/3/27 22:18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/queryAllUserList")
    public List<User> queryAllUserList() {
        return userService.queryAllUserList();
    }

    @GetMapping("/queryUserByAttr")
    public List<User> queryUserByAttr(User user) {
        return userService.queryUserByAttr(user);
    }

    @GetMapping("/queryUserByAttr2")
    public List<User> queryUserByAttr2(Integer id, String name, Integer age) {
        return userService.queryUserByAttr2(id, name, age);
    }
}
