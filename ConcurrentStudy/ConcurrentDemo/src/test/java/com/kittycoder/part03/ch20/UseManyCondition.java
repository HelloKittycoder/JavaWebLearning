package com.kittycoder.part03.ch20;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shucheng on 2019-9-25 下午 18:08
 * ReentrantLock使用多个Condition
 * 经常使用的synchronized关键字其实就相当于只有一个Condition：也就是说对于多个线程获得的lock，
 * 其中一个线程调用notify只能通知剩下所有的线程；而ReentrantLock可以对要通知的线程先使用Condition分组，
 * 到时指定要通知哪一组的线程
 *
 * 打印结果：
 * 当前线程：t1进入方法m1等待...
 * 当前线程：t2进入方法m2等待...
 * 当前线程：t3进入方法m3等待...
 * 当前线程：t4唤醒...
 * 当前线程：t1进入方法m1继续...
 * 当前线程：t2进入方法m2继续...
 * 当前线程：t5唤醒...
 * 当前线程：t3进入方法m3继续...
 *
 * 可能的代码执行顺序：
 * 1.t1,t2至t5线程依次被启动
 * 2.t1,t2（都属于c1）,t3（属于c2）同时获得lock，打印“xxx进入方法等待”，并调用await方法，处于等待状态
 * 3.t4线程执行，打印“t4唤醒...”，调用signalAll，唤醒c1这一组的所有线程（也就是t1,t2），同时打印“xxx进入方法继续”
 * 4.t4线程执行，打印“t5唤醒...”，调用signal，唤醒c2对应的线程（也就是t3），同时打印“xxx进入方法继续”
 */
public class UseManyCondition {

    private ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void m1() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法m1等待...");
            c1.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法m1继续...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法m2等待...");
            c1.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法m2继续...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m3() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法m3等待...");
            c2.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法m3继续...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m4() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "唤醒...");
            c1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m5() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "唤醒...");
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseManyCondition umc = new UseManyCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m2();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m3();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m4();
            }
        }, "t4");
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m5();
            }
        }, "t5");

        t1.start(); // c1
        t2.start(); // c1
        t3.start(); // c2

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start(); // c1
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t5.start(); // c2
    }
}
