package com.kittycoder;

import com.kittycoder.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2020/1/31 21:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerUserApplicationTest {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        userService.hello();
    }
}
