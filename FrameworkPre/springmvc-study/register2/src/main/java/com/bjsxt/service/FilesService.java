package com.bjsxt.service;

import com.bjsxt.pojo.Files;
import com.bjsxt.pojo.Users;

import java.util.List;

/**
 * Created by shucheng on 2019-5-19 下午 23:01
 */
public interface FilesService {
    /**
     * 显示全部
     * @return
     */
    List<Files> show();

    /**
     * 根据id修改资料下载次数
     * @param id
     * @return
     */
    int updCount(int id, Users users, String name);
}
