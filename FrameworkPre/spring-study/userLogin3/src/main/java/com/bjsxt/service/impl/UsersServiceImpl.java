package com.bjsxt.service.impl;

import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by shucheng on 2019-5-9 下午 13:47
 */
public class UsersServiceImpl implements UsersService {

    @Value("${my.demo}")
    private String test;
    @Value("${my.demo1}")
    private int test1;

    private UsersMapper usersMapper;

    @Override
    public Users login(Users users) {
        System.out.println("输出：" + test + "  " + test1);
        return usersMapper.selByUsersPwd(users);
    }

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
}
