package com.bjsxt.service;

import com.bjsxt.pojo.Flower;

import java.util.List;

/**
 * Create by Administrator on 2019/3/16
 */
public interface FlowerService {

    List<Flower> show();

    int add(Flower flower);
}
