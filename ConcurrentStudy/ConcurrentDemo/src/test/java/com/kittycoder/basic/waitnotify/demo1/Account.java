package com.kittycoder.basic.waitnotify.demo1;

/**
 * Created by shucheng on 2019-11-6 下午 22:51
 */
public class Account {

    private String accounttNo; // 账号
    private double balance; // 账户余额
    private boolean flag = false; // 标识账户下是否有存款，false表示无存款

    public Account() {
    }

    public Account(String accounttNo, double balance) {
        this.accounttNo = accounttNo;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    // 取钱
    public synchronized void draw(int time, double drawAmount) {
        if (!flag) {
            try {
                System.out.println(Thread.currentThread().getName() + "取钱被阻塞");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 以下代码也可以不用挪到else里，把notifyAll改成notify也行
            System.out.println(Thread.currentThread().getName() + "第" + time + "次取钱 " + drawAmount);
            balance -= drawAmount;
            flag = false;
            notifyAll();
            System.out.println("余额 " + balance);
        }
    }

    // 存钱
    public synchronized void deposit(int time, double depositAmount) {
        if (flag) {
            try {
                System.out.println(Thread.currentThread().getName() + "存钱被阻塞");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 以下代码也可以不用挪到else里，把notifyAll改成notify也行
            System.out.println(Thread.currentThread().getName() + "第" + time + "次存钱 " + depositAmount);
            balance += depositAmount;
            flag = true;
            notifyAll();
            System.out.println("余额 " + balance);
        }
    }
}
