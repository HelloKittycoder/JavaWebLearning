package com.kittycoder.basic.waitnotify.demo1;

/**
 * Created by shucheng on 2019-11-6 下午 23:01
 * 存钱线程
 */
public class DepositThread extends Thread {

    private Account account;

    public DepositThread(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            account.deposit(i, 100);
        }
    }
}
