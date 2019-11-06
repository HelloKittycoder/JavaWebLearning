package com.kittycoder.basic.join;

/**
 * Created by shucheng on 2019-11-6 上午 9:14
 * 使用join的时候
 * main线程会等待threadA，main线程会等待threadA执行完之后结束掉
 *
 * threadA暂停时，会影响main线程printNum方法的执行
 */
public class TestJoin2 {

    public static void printNum() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "====" + i);
        }
    }

    public static void main(String[] args) {
        System.out.println("MainThread run start.");

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadA run start.");
                try {
                    System.out.println("threadA暂停1秒 start...");
                    Thread.sleep(1000);
                    System.out.println("threadA暂停1秒 end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadA run finished.");
            }
        });
        threadA.start();

        System.out.println("MainThread join before");
        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printNum();
        System.out.println("MainThread finished");
    }
}
