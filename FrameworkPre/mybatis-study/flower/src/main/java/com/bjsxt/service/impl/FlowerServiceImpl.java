package com.bjsxt.service.impl;

import com.bjsxt.dao.FlowerDao;
import com.bjsxt.dao.impl.FlowerDaoImpl;
import com.bjsxt.pojo.Flower;
import com.bjsxt.service.FlowerService;

import java.util.List;

/**
 * Create by Administrator on 2019/3/16
 */
public class FlowerServiceImpl implements FlowerService {

    private FlowerDao flowerDao = new FlowerDaoImpl();

    @Override
    public List<Flower> show() {
        return flowerDao.selectAll();
    }

    @Override
    public int add(Flower flower) {
        return flowerDao.insertFlower(flower);
    }
}
