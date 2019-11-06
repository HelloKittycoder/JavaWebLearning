package com.kittycoder.basic.waitnotify.demo1;

/**
 * Created by shucheng on 2019-11-6 下午 23:01
 * 取钱线程
 */
public class DrawThread extends Thread {

    private Account account;

    public DrawThread(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            account.draw(i, 100);
        }
    }
}
