package com.bjsxt.service;

import com.bjsxt.pojo.PageInfo;

import java.io.IOException;

/**
 * Create by Administrator on 2019/3/21
 */
public interface LogService {

    PageInfo showPage(int pageSize, int pageNum) throws IOException;
}
