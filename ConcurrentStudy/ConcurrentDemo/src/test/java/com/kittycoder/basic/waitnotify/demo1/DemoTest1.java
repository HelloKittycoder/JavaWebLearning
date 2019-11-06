package com.kittycoder.basic.waitnotify.demo1;

/**
 * Created by shucheng on 2019-11-6 下午 23:08
 * 使用wait/notify简单模拟下两个线程对一个账户同时存钱、取钱操作
 *
 * 代码逻辑：
 * 账户里没钱flag=false，账户里有钱flag=true
 * 1. flag=false时，无法进行取钱操作，只能做存钱操作
 * 2. flag=true时，无法进行存钱操作，只能做取钱操作
 * 3. 只能存1次取1次，然后取1次存1次；不能连续两次存，不能连续两次取
 */
public class DemoTest1 {

    public static void main(String[] args) {
        Account account = new Account("123456", 0);
        new DrawThread("甲", account).start();
        new DepositThread("乙", account).start();
    }
}
