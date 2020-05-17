package com.kittycoder.service.impl;

import com.kittycoder.dao.UserMapper;
import com.kittycoder.po.User;
import com.kittycoder.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shucheng on 2020/3/27 22:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUserList() {
        return userMapper.queryAllUserList();
    }

    @Override
    public List<User> queryUserByAttr(User user) {
        return userMapper.queryUserByAttr(user);
    }

    @Override
    public List<User> queryUserByAttr2(Integer id, String name, Integer age) {
        return userMapper.queryUserByAttr(id, name, age);
    }
}
