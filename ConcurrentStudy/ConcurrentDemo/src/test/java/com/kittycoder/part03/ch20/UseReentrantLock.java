package com.kittycoder.part03.ch20;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shucheng on 2019-9-25 下午 15:56
 * ReentrantLock使用demo
 * 可以结合https://www.cnblogs.com/takumicx/p/9338983.html来看
 *
 * 对ReentrantLock的说明：
 * A.ReentrantLock和synchronized的相同点
 * 1.ReentrantLock和synchronized都是独占锁，只允许线程互斥地访问临界区。但是实现上两者不同：synchronized加锁解锁过程是隐式的，
 * 用户不用手动操作，优点是操作简单，但显得不够灵活；ReentrantLock需要手动加锁和解锁，且解锁的操作尽量放在finally代码块中，
 * 保证线程正确释放锁
 * 2.ReentrantLock和synchronized都是可重入的。synchronized因为可重入因此可以放在被递归执行的方法上，且不用担心线程最后能否
 * 正确释放锁；而ReentrantLock在重入时要确保重复获得锁的次数必须和重复释放锁的次数一样，否则可能导致其他线程无法获得该锁
 *
 */
public class UseReentrantLock {

    private ReentrantLock lock = new ReentrantLock();

    public void method1() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入method1...");
            Thread.sleep(1000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出method1...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入method2...");
            Thread.sleep(2000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出method2...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseReentrantLock ur = new UseReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ur.method1();
                ur.method2();
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println(ur.lock.getQueueLength());
    }
}
