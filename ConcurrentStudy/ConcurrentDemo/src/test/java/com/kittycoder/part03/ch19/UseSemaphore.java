package com.kittycoder.part03.ch19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by shucheng on 2019-9-25 下午 15:27
 * Semaphore使用demo
 * 说明：用来控制并发线程数（Semaphore含义为“信号灯”）
 *
 * 代码说明：
 * 下面模拟20个客户端访问（实际需要20个线程来实现同时访问），但因为资源限制，
 * 最多只能5个线程同时运行（通过Semaphore的构造方法输入参数来控制）
 */
public class UseSemaphore {

    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            int NO = index;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing:" + NO);
                        // 模拟实际业务逻辑
                        Thread.sleep((long) (Math.random() * 10000));
                        semp.release();
                        // System.out.println(semp.getQueueLength()); // 获取等待许可的线程个数
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println(semp.getQueueLength());

        // 退出线程池
        exec.shutdown();
    }
}
