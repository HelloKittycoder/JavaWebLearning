package com.kittycoder.ch11;

/**
 * Created by shucheng on 2019-9-19 下午 20:04
 * 双重校验锁
 *
 * https://blog.csdn.net/tian31233/article/details/79664649
 * 网上很多文章中说加volatile可以避免指令重排序，对此还不太理解，因为我目前还没发现如果不加volatile时候运行代码会报什么错误
 */
public class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton ds;

    public static DoubleCheckSingleton getDs() {
        if (ds == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (ds == null) {
                    ds = new DoubleCheckSingleton();
                }
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleCheckSingleton.getDs().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleCheckSingleton.getDs().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleCheckSingleton.getDs().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        /*for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // System.out.println(Thread.currentThread().getName() + "====" + DoubleCheckSingleton.getDs().hashCode());
                    DoubleCheckSingleton.getDs().hashCode();
                }
            }, "t"+i).start();
        }*/
    }
}
