package com.kittycoder.basic.sleep;

/**
 * Created by shucheng on 2019-11-4 下午 21:24
 */
public class TestSleep2 {

    public void a1() throws Exception {
        Thread.sleep(3000);
        System.out.println("a1====" + Thread.currentThread().getName());
    }

    public synchronized void a2() throws Exception {
        Thread.sleep(1000);
        System.out.println("a2====" + Thread.currentThread().getName());
    }

    public synchronized void a3() throws Exception {
        System.out.println("a3====" + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        TestSleep2 t = new TestSleep2();
        t.a1();
        // 不同的synchronized方法之间不存在锁竞争
        // a2内部sleep()不会影响a3执行
        // 多个线程调用a2，先抢到锁的线程完全执行完a2方法，后面才能抢到锁并执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.a2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.a3();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
    }
}
