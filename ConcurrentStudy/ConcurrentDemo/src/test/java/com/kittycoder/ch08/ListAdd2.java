package com.kittycoder.ch08;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-9-18 下午 17:05
 * 线程之间的通信（使用wait、notify方法）
 *
 * wait释放锁，sleep、notify不释放锁
 */
public class ListAdd2 {

    private volatile static List list = new ArrayList();

    public void add() {
        list.add("bjsxt");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        ListAdd2 list2 = new ListAdd2();

        // 1 实例化出来一个lock
        // 当使用wait和notify的时候，一定要配合着synchronized关键字去使用
        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            list2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                            Thread.sleep(500);
                            if (list2.size() == 5) {
                                System.out.println("已经发出通知..");
                                lock.notify(); // 通知其他线程，同时不释放锁；
                                // 如果释放锁的话，t2就会打印“收到通知线程停止..”，但是t2没有打印，说明notify确实通知了，但是并没有释放锁
                            }
                        }
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (list2.size() != 5) {
                        try {
                            System.out.println("t2进入...");
                            Thread.sleep(3000); // 使当前线程睡眠，不释放锁
                            lock.wait(); // 使当前线程处于等待状态，并且释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                }
            }
        }, "t2");

        t2.start();
        t1.start();
    }
}
