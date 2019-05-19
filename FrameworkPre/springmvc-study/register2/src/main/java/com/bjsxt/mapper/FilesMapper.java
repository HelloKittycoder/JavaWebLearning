package com.bjsxt.mapper;

import com.bjsxt.pojo.Files;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by shucheng on 2019-5-19 下午 23:00
 */
public interface FilesMapper {
    @Select("select * from files")
    List<Files> selAll();

    @Update("update files set count=count+1 where id=#{0}")
    int updCountById(int id);
}
