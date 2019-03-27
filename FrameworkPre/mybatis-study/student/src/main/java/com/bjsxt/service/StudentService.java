package com.bjsxt.service;

import com.bjsxt.pojo.PageInfo;

/**
 * Create by Administrator on 2019/3/26
 */
public interface StudentService {

    PageInfo showPage(String sname, String tname, String pageSize, String pageNumber);
}
