package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsersMapperTest extends BaseTest {

    @Resource
    private UsersMapper usersMapper;

    @Test
    public void insUsers() {
        Users users = new Users();
        users.setUsername("王五");
        users.setPassword("123456");
        users.setPhoto("hehehe");
        int index = usersMapper.insUsers(users);
        System.out.println(index > 0 ? "新增成功" : "新增失败");
    }
}