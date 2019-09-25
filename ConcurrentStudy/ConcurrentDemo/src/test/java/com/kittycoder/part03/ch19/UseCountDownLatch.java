package com.kittycoder.part03.ch19;

import java.util.concurrent.CountDownLatch;

/**
 * Created by shucheng on 2019-9-25 上午 11:32
 * CountDownLatch（线程计数器，等待其他线程完成，再执行主线程）使用demo
 *
 * 打印结果：
 * 进入线程t1，等待其他线程处理完成...
 * t3线程进行初始化操作...
 * t2线程进行初始化操作...
 * t2线程初始化完毕，通知t1线程继续...
 * t3线程初始化完毕，通知t1线程继续
 * t1线程继续执行...
 *
 * 可能的代码执行流程：
 * 1.创建3个线程，然后依次启动
 * 2.打印“进入t1”，遇到await，代码跳转到其他线程执行代码
 * 3.t3分配到cpu资源，打印“t3线程初始化”，执行睡眠4s（说明：sleep确实不会释放锁，t2和t3不存在锁竞争关系）
 * 3.t2分配到cpu资源，打印“t2线程初始化”，执行睡眠3s，然后打印“t2线程初始化完毕”，countDown执行减1操作（此时值为1）
 * 4.t3分配到cpu资源，打印“t3线程初始化完毕”，countDown执行减1操作（此时值为0）
 * 5.值为0时，立即回到await所在的线程，执行await之后的代码，打印“t1线程继续执行...”
 */
public class UseCountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDown = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入线程t1，等待其他线程处理完成...");
                    countDown.await();
                    System.out.println("t1线程继续执行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2线程进行初始化操作...");
                    Thread.sleep(3000);
                    System.out.println("t2线程初始化完毕，通知t1线程继续...");
                    countDown.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3线程进行初始化操作...");
                    Thread.sleep(4000);
                    System.out.println("t3线程初始化完毕，通知t1线程继续");
                    countDown.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
