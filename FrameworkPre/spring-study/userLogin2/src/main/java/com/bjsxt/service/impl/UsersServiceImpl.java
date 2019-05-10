package com.bjsxt.service.impl;

import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;

/**
 * Created by shucheng on 2019-5-9 下午 13:47
 */
public class UsersServiceImpl implements UsersService {

    private UsersMapper usersMapper;

    @Override
    public Users login(Users users) {
        return usersMapper.selByUsersPwd(users);
    }

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
}
