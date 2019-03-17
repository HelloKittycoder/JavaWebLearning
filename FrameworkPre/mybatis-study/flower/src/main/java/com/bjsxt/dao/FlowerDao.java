package com.bjsxt.dao;

import com.bjsxt.pojo.Flower;

import java.util.List;

/**
 * Create by Administrator on 2019/3/16
 */
public interface FlowerDao {

    List<Flower> selectAll();

    int insertFlower(Flower flower);
}
