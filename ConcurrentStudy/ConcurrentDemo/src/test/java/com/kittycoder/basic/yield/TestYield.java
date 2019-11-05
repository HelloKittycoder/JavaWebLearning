package com.kittycoder.basic.yield;

/**
 * Created by shucheng on 2019-11-4 下午 22:20
 * Thread#yield，将当前线程暂停，让系统线程重新调度
 *
 * 很明显，这个时候yield没有起到任何作用（虽然让当前线程暂停由系统重新调度，但是yield方法不会释放monitor）
 * monitor：同步监视器
 *
 * t1一旦在执行printNum方法，此时t1就拿到了该方法的锁，中途执行yield和sleep不会释放锁，只有等方法执行完
 * 才会释放锁；t2接着有机会执行printNum方法
 *
 * printNum上面有synchronized，此时就相当于排队执行，t1执行完printNum方法，t2接着执行printNum方法
 */
public class TestYield {

    public synchronized void printNum() {
        for (int i = 0; i < 50; i++) {
            if ((i % 2 == 0) && "t1".equals(Thread.currentThread().getName())) {
                System.out.println("***********i=" + i +"中途暂停***********");
                Thread.yield();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "====" + i);
        }
    }

    public static void main(String[] args) {

        TestYield t = new TestYield();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.printNum();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.printNum();
            }
        }, "t2");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
