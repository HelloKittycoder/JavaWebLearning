package com.bjsxt.controller;

import com.bjsxt.pojo.Menu;
import com.bjsxt.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shucheng on 2019-5-15 下午 22:12
 */
@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    // 查询所有菜单
    @RequestMapping("show")
    @ResponseBody
    public List<Menu> show() {
        return menuService.show();
    }
}
