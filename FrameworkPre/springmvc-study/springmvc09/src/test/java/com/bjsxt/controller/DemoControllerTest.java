package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create by Administrator on 2019/5/12
 * 测试DemoController
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest extends BaseTest {

    @Test
    public void downloadFile() throws Exception {
        matchDownloadHeader("/downloadFile",
                new String[]{"a.txt", "show.rar"});
    }
}