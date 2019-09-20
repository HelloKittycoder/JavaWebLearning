package com.kittycoder.part01.ch06_synchronizedusage;

/**
 * Created by shucheng on 2019-9-18 下午 22:04
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 */
public class StringLock {

    // 说明：synchronized锁 "字符串常量" 和 new String("字符串常量") 的区别
    // 假设t1先获得锁
    // "字符串常量"：t1一直占用锁，t2没有机会获得锁
    // new String("字符串常量")：每次生成的是新的对象，这段代码每次生成的都是一个新的对象，锁这个的话跟没锁没有区别

    public void method() {
        // new String("字符串常量")
        synchronized ("字符串常量") {
            try {
                while (true) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        StringLock stringLock = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
