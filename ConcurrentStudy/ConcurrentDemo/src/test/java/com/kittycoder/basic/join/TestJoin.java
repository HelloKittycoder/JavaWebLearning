package com.kittycoder.basic.join;

/**
 * Created by shucheng on 2019-11-6 上午 8:57
 * 未使用join的时候
 * main线程不会等待threadA，main线程会先于threadA结束
 *
 * threadA暂停时，不会影响main线程的打印，main线程的printNum依然在执行
 *
 * 参考链接：https://blog.csdn.net/weixin_42868638/article/details/88398254
 */
public class TestJoin {

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

        printNum();
        /* System.out.println("MainThread join before");
        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("MainThread finished");
    }
}
