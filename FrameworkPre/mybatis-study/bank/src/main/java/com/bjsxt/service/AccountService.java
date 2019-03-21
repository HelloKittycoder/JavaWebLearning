package com.bjsxt.service;

import com.bjsxt.pojo.Account;

import java.io.IOException;

/**
 * Create by Administrator on 2019/3/20
 */
public interface AccountService {

    /**
     * 账号和密码比匹配状态码
     */
    int ACCOUNT_PASSWORD_NOT_MATCH = 1;

    /**
     * 余额不足
     */
    int ACCOUNT_BALANCE_NOT_ENOUGH = 2;

    /**
     * 账户和姓名不匹配
     */
    int ACCOUNT_NAME_NOT_MATCH = 3;

    /**
     * 转账失败
     */
    int ERROR = 4;

    /**
     * 转账成功
     */
    int SUCCESS = 5;
    /**
     * 转账
     * @param accIn 收款账号
     * @param accOut 付款账号
     * @return
     */
    int transfer(Account accIn, Account accOut) throws IOException;
}
