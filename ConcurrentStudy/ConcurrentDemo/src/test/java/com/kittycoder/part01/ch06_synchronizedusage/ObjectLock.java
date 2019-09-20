package com.kittycoder.part01.ch06_synchronizedusage;

/**
 * Created by shucheng on 2019-9-18 下午 21:35
 * 使用synchronized代码块加锁，比较灵活（可以锁的范围）
 */
public class ObjectLock {

    // 说明：以下3个方法之间不存在互斥关系

    // 对象锁和类锁不是同一个东西，一个是类的实例的锁，一个是类的Class对象的锁；
    // 也就是说，1个线程访问静态synchronized时，允许另一个线程访问对象的实例synchronized方法；反过来也是成立的。
    // 所以，method1和method2不存在互斥关系

    // method1和method3也不存在互斥关系，因为两个方法锁的不是同一个东西；
    // 一个锁的是ObjectLock的实例，一个锁的是lock对象
    public void method1() {
        synchronized (this) { // 对象锁
            try {
                System.out.println("do method1..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() { // 类锁
        synchronized (ObjectLock.class) {
            try {
                System.out.println("do method2..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();
    public void method3() { // 任何对象锁
        synchronized (lock) {
            try {
                System.out.println("do method3..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ObjectLock objLock = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method2();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method3();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
