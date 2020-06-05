package com.bjsxt.mapper;

import com.bjsxt.pojo.HandlerInfo;

import java.util.List;

/**
 * Created by shucheng on 2020/6/5 22:34
 */
public interface HandlerMapper {

    List<HandlerInfo> queryHandlerInfoList();

    void insertHandlerInfo(HandlerInfo handlerInfo);

    void updateHandlerInfo(HandlerInfo handlerInfo);
}
