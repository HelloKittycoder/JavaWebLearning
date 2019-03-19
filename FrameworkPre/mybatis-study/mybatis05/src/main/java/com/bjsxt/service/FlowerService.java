package com.bjsxt.service;

import com.bjsxt.pojo.Flower;
import com.bjsxt.pojo.PageInfo;

import java.io.IOException;
import java.util.List;

/**
 * Create by Administrator on 2019/3/17
 */
public interface FlowerService {

    // 查询分页
    PageInfo show(int pageNum, int pageSize) throws IOException;
}
