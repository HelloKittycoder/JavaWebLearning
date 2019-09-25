package com.kittycoder.part03.ch20;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shucheng on 2019-9-25 下午 17:03
 * ReentrantLock的Condition
 * https://www.cnblogs.com/xiaoxi/p/7651360.html
 *
 * 打印结果：
 * 当前线程：t1进入等待状态...
 * 当前线程：t1释放锁...
 * 当前线程：t2进入...
 * 当前线程：t2发出唤醒...
 * 当前线程：t2释放锁...
 * 当前线程：t1继续执行...
 * 当前线程：t1释放锁...
 *
 * 可能的代码执行顺序：
 * 1.t1获得锁，打印“当前线程：t1进入等待状态...”
 * 2.等待3s，打印“当前线程：t1释放锁...”
 * 3.遇到condition.await，t1释放锁，t2获得锁
 * 4.打印“当前线程：t2进入...”
 * 5.等待3s，打印“当前线程：t2发出唤醒...”
 * 6.condition.signal，t2释放锁，t1获得锁；在t2释放锁之前，执行finally代码块，
 * 打印“当前线程：t2释放锁...”
 * 7.继续执行method1中的await之后的代码，打印“当前线程：t1继续执行...”
 * 8.执行finally代码块，打印“当前线程：t1释放锁...”
 */
public class UseCondition {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method1() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待状态...");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁...");
            condition.await(); // Object wait
            System.out.println("当前线程：" + Thread.currentThread().getName() + "继续执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁...");
        }
    }

    public void method2() {

        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒...");
            condition.signal(); // Object notify
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁...");
        }
    }

    public static void main(String[] args) {

        final UseCondition uc = new UseCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                uc.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                uc.method2();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
