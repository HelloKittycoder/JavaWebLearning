package com.kittycoder.controller;

import com.kittycoder.entity.User;
import com.kittycoder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shucheng on 2020/2/12 19:29
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // http://localhost:8080/user/1
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    // http://localhost:8080/user?lastName=zhangsan&email=aa
    @GetMapping("/user")
    public User insertUser(User user) {
        User save = userRepository.save(user);
        return save;
    }
}
