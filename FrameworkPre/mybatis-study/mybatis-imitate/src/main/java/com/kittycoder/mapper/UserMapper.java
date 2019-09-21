package com.kittycoder.mapper;

import com.kittycoder.entity.User;

import java.util.List;

/**
 * Created by shucheng on 2019-9-21 下午 15:56
 */
public interface UserMapper {

    List<User> selectAllUsers();

    User selectUserById(String userId);
}
