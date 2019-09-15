package com.kittycoder.ch01_threadsafe;

/**
 * Created by shucheng on 2019-9-15 下午 20:45
 * 线程安全测试
 *
 * 线程安全概念：当多个线程访问某一个类（对象或方法时），这个对象试中都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的
 * synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为“互斥区”或“临界区”
 */
public class ThreadSafetyTest extends Thread {

    private int count = 5;

    // synchronized加锁
    public void run() {
        count--;
        System.err.println(this.currentThread().getName() + " count=" + count);
    }

    public static void main(String[] args) {
        /**
         * 分析：当多个线程访问ThreadSafetyTest的run方法时，以排队的形式进行处理（这里排队是按照CPU分配的先后顺序定的），
         *      一个线程想要执行synchronized修饰的方法里的代码：
         *      1. 尝试获得锁
         *      2. 如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程就会不断的尝试获得这把锁，知道拿到位置，
         *      而且是多个线程同时去竞争这把锁（也即是会有锁竞争的问题）
         */
        ThreadSafetyTest ts = new ThreadSafetyTest();
        Thread t1 = new Thread(ts, "t1");
        Thread t2 = new Thread(ts, "t2");
        Thread t3 = new Thread(ts, "t3");
        Thread t4 = new Thread(ts, "t4");
        Thread t5 = new Thread(ts, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
