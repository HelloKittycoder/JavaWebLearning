package com.kittycoder.basic.sleep;

/**
 * Created by shucheng on 2019-11-4 下午 21:24
 * 测试Thread#sleep方法，sleep会暂停当前线程的运行，但是不会释放锁
 */
public class TestSleep {

    public void a1() throws Exception {
        Thread.sleep(3000);
        System.out.println("a1====" + Thread.currentThread().getName());
    }

    public void a2() throws Exception {
        Thread.sleep(1000);
        System.out.println("a2====" + Thread.currentThread().getName());
    }

    public void a3() throws Exception {
        System.out.println("a3====" + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        TestSleep t = new TestSleep();
        t.a1(); // 这里内部调用了Thread.sleep，导致main方法暂停了2秒，后面的代码无法执行
        System.out.println("2秒过去了...");
        // 线程1和线程2各自没有关系，他们内部调用Thread.sleep对另一方没有影响
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.a2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.a3();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
    }
}
