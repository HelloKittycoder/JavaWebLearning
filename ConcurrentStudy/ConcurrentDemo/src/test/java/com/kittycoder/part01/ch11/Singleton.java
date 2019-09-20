package com.kittycoder.part01.ch11;

/**
 * Created by shucheng on 2019-9-19 下午 21:02
 * 静态内部类
 */
public class Singleton {

    private static class InnerSingleton {
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerSingleton.singleton;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getInstance().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getInstance().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getInstance().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
