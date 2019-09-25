package com.kittycoder.part03.ch20;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shucheng on 2019-9-25 下午 16:46
 * lock.getHoldCount：查询当前线程保持此锁定的个数，即调用lock()方法的次数
 * https://blog.csdn.net/javabird945/article/details/80567606
 *
 * 在方法中多次获得lock
 */
public class TestHoldCount {

    // 重入锁
    private ReentrantLock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            // System.out.println("m1加锁");
            System.out.println("进入m1方法，holdCount数为：" + lock.getHoldCount());

            // 调用m2方法
            m2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // System.out.println("m1解锁");
            lock.unlock();
            // System.out.println("退出m1方法，holdCount数为：" + lock.getHoldCount());
        }
    }

    public void m2() {
        try {
            lock.lock();
            // System.out.println("m2加锁");
            System.out.println("进入m2方法，holdCount数为：" + lock.getHoldCount());
            m3();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // System.out.println("m2解锁");
            lock.unlock();
            // System.out.println("退出m2方法，holdCount数为：" + lock.getHoldCount());
        }
    }

    public void m3() {
        try {
            lock.lock();
            // System.out.println("m3加锁");
            System.out.println("进入m3方法，holdCount数为：" + lock.getHoldCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // System.out.println("m3解锁");
            lock.unlock();
            // System.out.println("退出m3方法，holdCount数为：" + lock.getHoldCount());
        }
    }

    public static void main(String[] args) {
        TestHoldCount thc = new TestHoldCount();
        thc.m1();
    }
}
