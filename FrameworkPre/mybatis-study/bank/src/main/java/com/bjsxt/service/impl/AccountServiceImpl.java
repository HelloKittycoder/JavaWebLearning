package com.bjsxt.service.impl;

import com.bjsxt.pojo.Account;
import com.bjsxt.pojo.Log;
import com.bjsxt.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by Administrator on 2019/3/20
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public int transfer(Account accIn, Account accOut) throws IOException {
        // 获取SessionFactory
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // 先判断账号和密码是否匹配（付款人）
        Account accOutSelect = session.selectOne("com.bjsxt.mapper.accountMapper.selByAccnoPwd", accOut);
        if (accOutSelect != null) {
            if (accOutSelect.getBalance() >= accOut.getBalance()) {
                // 判断账号和密码是否匹配（收款人）
                Account accInSelect = session.selectOne("com.bjsxt.mapper.accountMapper.selByAccnoName", accIn);
                if (accInSelect != null) {
                    // 收款方金额增加
                    accIn.setBalance(accOut.getBalance());
                    // 付款方金额减少
                    accOut.setBalance(-accOut.getBalance());
                    int index = session.update("com.bjsxt.mapper.accountMapper.updBalanceByAccno", accOut);
                    index += session.update("com.bjsxt.mapper.accountMapper.updBalanceByAccno", accIn);
                    if (index == 2) {
                        // 日志表记录
                        Log log = new Log();
                        log.setAccIn(accIn.getAccNo());
                        log.setAccOut(accOut.getAccNo());
                        log.setMoney(accIn.getBalance());
                        session.insert("com.bjsxt.mapper.logMapper.insLog", log);
                        // 日志文件记录
                        Logger logger = Logger.getLogger(AccountServiceImpl.class);
                        DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
                        logger.info(log.getAccOut()+"给"+log.getAccIn()+"在"+sdf.format(new Date())+"转了"+log.getMoney());
                        session.commit();
                        session.close();
                        return SUCCESS;
                    } else {
                        session.rollback();
                        session.close();
                        return ERROR;
                    }
                } else {
                    // 余额不足
                    return ACCOUNT_NAME_NOT_MATCH;
                }
            } else {
                // 账户和姓名不匹配
                return ACCOUNT_BALANCE_NOT_ENOUGH;
            }
        } else {
            // 账号和密码不匹配
            return ACCOUNT_PASSWORD_NOT_MATCH;
        }
    }
}
