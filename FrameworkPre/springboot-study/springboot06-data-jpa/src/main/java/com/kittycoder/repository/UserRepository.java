package com.kittycoder.repository;

import com.kittycoder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shucheng on 2020/2/12 19:21
 * 继承JpaRepository来完成对数据库的操作
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
