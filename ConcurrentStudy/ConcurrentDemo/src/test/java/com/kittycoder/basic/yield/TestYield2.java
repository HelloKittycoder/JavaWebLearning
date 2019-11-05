package com.kittycoder.basic.yield;

/**
 * Created by shucheng on 2019-11-5 下午 20:08
 *
 * printNum上面没有加synchronized，这就是并发执行（t1和t2轮流获得cpu时间片，交替执行printNum）
 *
 * 以下代码可能的打印结果为：（一定会出现的是在t1线程i为偶数时，会让t1暂停；让系统线程重新调度，至于调度后谁能获得执行机会，
 * 这个说不准，但是一定是和t1相同级别或者比t1级别更高的线程，也有可能t1继续得到执行机会）
 *
 * 解释：
 * 线程t1在i为偶数时，暂停t1的执行，让系统线程重新调度
 * t2的优先级为最高，t1的优先级为最低
 * 这意味着，t1调用yield暂停后，虽然有可能是t1接着执行，也有可能是t2接着执行，但是t2有更多的执行机会
 */
public class TestYield2 {

    public void printNum() {
        for (int i = 0; i < 50; i++) {
            if ((i % 2 == 0) && "t1".equals(Thread.currentThread().getName())) {
                System.out.println("***********i=" + i +"中途暂停***********");
                Thread.yield();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "====" + i);
        }
    }

    public static void main(String[] args) {
        TestYield2 t = new TestYield2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.printNum();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.printNum();
            }
        }, "t2");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
