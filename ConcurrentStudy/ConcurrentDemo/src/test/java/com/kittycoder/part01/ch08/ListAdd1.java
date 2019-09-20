package com.kittycoder.part01.ch08;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-9-18 下午 16:48
 * 线程之间的通信（使用volatile关键字）
 *
 * 说明：启动t1、t2线程后，t2线程检测到t1线程更改list1的size后变成5以后，t2线程自动终止;
 * 同时t2线程的异常会被t1线程捕获，t1线程继续执行循环，直到循环终止运行
 */
public class ListAdd1 {

    /**
     * 这里需要给list加volatile关键字，不然t2线程无法即时感知到t1线程修改list后的size
     */
    private volatile static List list = new ArrayList();

    public void add() {
        list.add("bjsxt");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        ListAdd1 list1 = new ListAdd1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        list1.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (list1.size() == 5) {
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
