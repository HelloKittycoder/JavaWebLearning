package com.kittycoder.part01.ch05_otherconcepts;

/**
 * Created by shucheng on 2019-9-17 下午 19:40
 * synchronized异常
 */
public class SyncException {

    private int i = 0;
    public synchronized void operation() {
        while (true) {
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " ,i = " + i);
                if (i == 20) {
                    // Integer.parseInt("a");
                    throw new RuntimeException();
                }
                if (i == 30) {
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SyncException se = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
