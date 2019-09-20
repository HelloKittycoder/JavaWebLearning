package com.kittycoder.part01.ch03_syncandasync;

/**
 * Created by shucheng on 2019-9-16 下午 21:48
 * 对象锁的同步和异步问题
 */
public class SyncAsyncTest {

    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** synchronized **/
    public void method2() {
        System.out.println(Thread.currentThread().getName());
    }

    public synchronized void method3() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        SyncAsyncTest sa = new SyncAsyncTest();

        /**
         * 分析：
         * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象的非synchronized修饰的方法
         * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
         *
         * 下面的例子：可能的打印结果
         * t1
         * t2（等待4秒打印下一行）
         * t3
         *
         * 可能的代码执行顺序：（t1和t2的打印几乎是同时的，可以看作非常接近；
         * 但是t1会阻塞t3的打印，因为t1在sleep时，已经获得了对象锁，t1会阻塞同步方法的执行）
         * ----------|--t1--|--t2--|--t3--|
         *    sout   |  1   |  1.5 |  3   |
         *    sleep  |  2   |      |      |
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sa.method1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sa.method2();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                sa.method3();
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
