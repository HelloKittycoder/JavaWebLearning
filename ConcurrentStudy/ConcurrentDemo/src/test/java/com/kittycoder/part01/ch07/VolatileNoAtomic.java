package com.kittycoder.part01.ch07;

/**
 * Created by shucheng on 2019-9-17 下午 22:12
 * volatile关键字不具备synchronized关键字的原子性（同步）
 * 如果使用int count，对于打印出来的结果不太理解（为什么最终结果达不到1000？）
 *
 * https://www.cnblogs.com/zhengbin/p/5653051.html
 */
public class VolatileNoAtomic extends Thread {

    private static volatile int count;
    // private static AtomicInteger count = new AtomicInteger(0);

    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
            count++;
            // count.incrementAndGet();
        }
        System.out.println(count);
    }

    public void run() {
        addCount();
    }

    public static void main(String[] args) {

        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }
}
