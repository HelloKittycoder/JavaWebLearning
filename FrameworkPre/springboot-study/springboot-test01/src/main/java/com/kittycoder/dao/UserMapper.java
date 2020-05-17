package com.kittycoder.dao;

import com.kittycoder.po.User;

import java.util.List;

/**
 * Created by shucheng on 2020/3/27 22:14
 */
public interface UserMapper {

    List<User> queryAllUserList();

    List<User> queryUserByAttr(User user);

    List<User> queryUserByAttr(Integer id, String name, Integer age);
}
