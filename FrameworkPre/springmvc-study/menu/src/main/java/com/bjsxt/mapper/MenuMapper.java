package com.bjsxt.mapper;

import com.bjsxt.pojo.Menu;

import java.util.List;

/**
 * Created by shucheng on 2019-5-15 下午 21:54
 */
public interface MenuMapper {

    List<Menu> selByPid(int pid);
}
