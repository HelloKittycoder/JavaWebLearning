package com.bjsxt.mapper;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.pojo.Student;

import java.util.List;

/**
 * Create by Administrator on 2019/3/26
 */
public interface StudentMapper {

    List<Student> selectPageList(PageInfo pi);

    long selectPageCount(PageInfo pi);
}
