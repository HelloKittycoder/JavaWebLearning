package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class MenuMapperTest extends BaseTest {

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void selByPid() {
        List<Menu> list = menuMapper.selByPid(0);
        System.out.println(list);
    }
}