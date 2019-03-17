package com.bjsxt.service;

import com.bjsxt.pojo.Flower;

import java.io.IOException;
import java.util.List;

/**
 * Create by Administrator on 2019/3/17
 */
public interface FlowerService {

    // 查询全部
    List<Flower> show() throws IOException;
}
