package com.bjsxt.service.impl;

import com.bjsxt.BaseSpring;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by Administrator on 2019/5/12
 */
public class UsersServiceImplTest extends BaseSpring {

    @Autowired
    private UsersService usersService;
    @Test
    public void insert() {
        Users users = new Users();
        users.setUsername("王五");
        users.setPassword("333");
        try {
            usersService.insert(users);
            System.out.println("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常，回滚");
        }
    }
}