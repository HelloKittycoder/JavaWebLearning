package com.kittycoder.ch08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by shucheng on 2019-9-18 下午 17:05
 * 线程之间的通信（使用CountDownLatch可以解决notify发通知时不释放锁的问题）
 */
public class ListAdd3 {

    private volatile static List list = new ArrayList();

    public void add() {
        list.add("bjsxt");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        ListAdd3 list2 = new ListAdd3();

        // 1 实例化出来一个lock
        // 当使用wait和notify的时候，一定要配合着synchronized关键字去使用
        // Object lock = new Object();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        list2.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        Thread.sleep(500);
                        if (list2.size() == 5) {
                            System.out.println("已经发出通知..");
                            countDownLatch.countDown();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (list2.size() != 5) {
                    try {
                        // System.out.println("t2进入...");
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                throw new RuntimeException();
            }
        }, "t2");

        t2.start();
        t1.start();
    }
}
