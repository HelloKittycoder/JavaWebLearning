package com.kittycoder.exception;

/**
 * Created by shucheng on 2020/2/6 23:13
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在");
    }
}
