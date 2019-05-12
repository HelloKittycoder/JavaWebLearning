package com.bjsxt.service.impl;

import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by shucheng on 2019-5-9 下午 13:47
 */
public class UsersServiceImpl implements UsersService {

    private UsersMapper usersMapper;

    @Override
    public Users login(Users users) {
        return usersMapper.selByUsersPwd(users);
    }

    @Override
    public int insert(Users users) {
        int rows = usersMapper.insertUsers(users);
        System.out.println(1 / 0);
        return rows;
    }

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
}
