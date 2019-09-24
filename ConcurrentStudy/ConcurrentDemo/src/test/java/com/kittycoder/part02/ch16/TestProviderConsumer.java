package com.kittycoder.part02.ch16;

import java.util.concurrent.*;

/**
 * Created by shucheng on 2019-9-24 下午 15:35
 */
public class TestProviderConsumer {

    public static void main(String[] args) {
        // 内存缓冲区（LinkedBlockingQueue本身是一个无界队列）
        BlockingQueue<Data> queue = new LinkedBlockingQueue<>(10);
        // 生产者
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);
        // 消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        // 创建线程池运行，这是一个缓存的线程池，可以创建无穷大的线程，没有任务的时候不创建线程。空闲线程存活时间为60s（默认值）
        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(p1);
        cachePool.execute(p2);
        cachePool.execute(p3);
        cachePool.execute(c1);
        cachePool.execute(c2);
        cachePool.execute(c3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p1.stop();
        p2.stop();
        p3.stop();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        cachePool.shutdown();
//        cachePool.shutdownNow();
    }
}
