package com.bjsxt.mapper;

import com.bjsxt.pojo.Users;
import org.apache.ibatis.annotations.Select;

/**
 * Created by shucheng on 2019-5-9 下午 13:44
 */
public interface UsersMapper {

    Users selByUsersPwd(Users users);
}
