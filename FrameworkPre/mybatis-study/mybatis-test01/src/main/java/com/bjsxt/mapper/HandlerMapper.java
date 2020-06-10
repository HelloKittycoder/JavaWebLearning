package com.bjsxt.mapper;

import com.bjsxt.pojo.HandlerInfo;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

/**
 * Created by shucheng on 2020/6/5 22:34
 */
public interface HandlerMapper {

    List<HandlerInfo> queryHandlerInfoList();

    void insertHandlerInfo(HandlerInfo handlerInfo);

    void updateHandlerInfo(HandlerInfo handlerInfo);

    void selectIdNameMap(ResultHandler resultHandler);
}
