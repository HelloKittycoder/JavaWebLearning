package com.kittycoder.part01.ch05_otherconcepts;

/**
 * Created by shucheng on 2019-9-17 上午 11:01
 * synchronized的重入（不同实例方法之间）
 */
public class SyncDubbo1 {

    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " method1...");
        method2();
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " method2...");
        method3();
    }

    public synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + " method3...");
    }

    /**
     * 分析：
     * 创建t1、t2两个线程
     *
     * 打印结果为：可能的打印结果
     * t2 method1...
     * t2 method2...
     * t2 method3...
     * t1 method1...
     * t1 method2...
     * t1 method3...
     *
     * t2线程先获得对象锁，进入method1方法，该方法是同步的，内部方法调用的其他方法method2还是同步的（method2不加synchronized也是一样的效果），
     * 该对象锁会被一直占用，直到method1方法执行完；
     * t1线程获得对象锁，进入method1方法，之后类似
     *
     * @param args
     */
    public static void main(String[] args) {
        SyncDubbo1 sd = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
