package com.kittycoder.part01.ch06_synchronizedusage;

/**
 * Created by shucheng on 2019-9-18 下午 20:57
 * 锁对象的改变问题
 *
 * 下面在synchronized代码块里把lock对象给改变了，导致t1线程获得的锁没有起到作用
 * Thread在sleep时，t2线程也进到synchronized代码块里了
 */
public class ChangeLock {

    private String lock = "lock";

    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
