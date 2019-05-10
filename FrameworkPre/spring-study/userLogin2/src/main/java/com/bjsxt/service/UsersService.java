package com.bjsxt.service;

import com.bjsxt.pojo.Users;

/**
 * Created by shucheng on 2019-5-9 下午 13:46
 */
public interface UsersService {

    /**
     * 登录
     * @param users
     * @return
     */
    Users login(Users users);
}
