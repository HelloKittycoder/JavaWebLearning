package com.kittycoder.ch10;

/**
 * Created by shucheng on 2019-9-18 下午 22:24
 * ThreadLocal使用demo
 *
 * 对threadLocal设置的变量，只对当前线程可见，别的线程取不出来
 */
public class ConnThreadLocal {

    public static ThreadLocal<String> th = new ThreadLocal<String>();

    public void setTh(String value) {
        th.set(value);
    }

    public void getTh() {
        System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
    }

    public static void main(String[] args) throws InterruptedException {

        final ConnThreadLocal ct = new ConnThreadLocal(); // 加final是为了让ct只能被初始化一次
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ct.setTh("张三");
                ct.getTh();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ct.getTh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        // Thread.State（线程的多个状态），一个线程不能多次start
        // https://blog.csdn.net/zl1zl2zl3/article/details/80776112
    }
}
