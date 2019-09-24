package com.kittycoder.part02.ch14;

/**
 * Created by shucheng on 2019-9-24 上午 10:26
 */
public class TestFuture {

    public static void main(String[] args) {

        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功！");
        System.out.println("做其他的事情...");

        String result = data.getRequest();
        System.out.println(result);

        /* 两个线程之间的执行互不影响，一个线程内部的等待，对别的线程不会造成影响
        // （t1和t2之间没有锁竞争，t1和FutureClient中new的Thread存在锁竞争，因为这两个都用了futureData对象）
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("======================启动t1线程=====================");

                FutureClient fc = new FutureClient();
                Data data = fc.request("请求参数");
                System.out.println("请求发送成功！");
                System.out.println("做其他的事情...");

                String result = data.getRequest();
                System.out.println(result);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("======================启动t2线程=====================");
                for (int i = 0; i < 100; i++) {
                    System.out.println("************t2线程正在执行" + i + "************");
                }
            }
        }, "t2");

        t1.start();
        t2.start();*/
    }
}
