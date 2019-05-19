package com.bjsxt.mapper;

import com.bjsxt.pojo.Users;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by shucheng on 2019-5-19 下午 20:15
 */
public interface UsersMapper {
    @Insert("insert into users values(default,#{username},#{password},#{photo})")
    int insUsers(Users users);
}
