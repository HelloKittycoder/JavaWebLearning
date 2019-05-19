package com.bjsxt.service.impl;

import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shucheng on 2019-5-19 下午 20:18
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    @Override
    public int insRegister(Users users) {
        return usersMapper.insUsers(users);
    }
}
