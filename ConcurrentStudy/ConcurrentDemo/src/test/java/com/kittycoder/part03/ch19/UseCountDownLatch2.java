package com.kittycoder.part03.ch19;

import java.util.concurrent.CountDownLatch;

/**
 * Created by shucheng on 2019-9-25 下午 13:19
 * 网上找的例子（https://www.cnblogs.com/cuglkb/p/8572239.html）
 * 模拟两个运动员（线程t1，t2）赛跑，主线程main是裁判
 *
 * 打印结果：
 * the race begin
 * t2 arrived!
 * t1 arrived!
 * the race end
 *
 * 代码说明：
 * 运动员在起跑线处于等待状态（2个线程创建好以后，等待裁判发出开始指令“the race begin”，也就是调用begin的count方法）
 * 每个运动员听到“开始”指令（持有一个begin引用）会奔跑，
 * 到达终点后会撞到结束的线（打印“xxx arrived!”），
 * 裁判得知所有运动员到达终点后，会宣布比赛结束the race end（因为所有运动员持有一个end引用）
 *
 * 可能的代码执行流程：
 * 1. 主线程创建两个线程t1、t2，并启动（此时t1、t2都被阻塞，因为t1和t2都调用了begin.await()方法），然后打印“the race begin”
 * 2. 主线程执行begin.countDown()，此时begin的值为0；主线程回到await所在的线程（t1、t2），执行await之后的代码
 * 3. t1和t2之间不存在锁竞争，两者打印先后会有差异，此时依次打印“t2 arrived!”、“t1 arrived!”；之后执行了end.await()
 * 4. t1和t2再分别执行countDown()方法，最终end的值为0，回到主线程的end.await()，执行其后的语句，打印“the race end”
 */
public class UseCountDownLatch2 {

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        for (int i = 1; i <= 2; i++) {
            Thread thread = new Thread(new Player(begin, end), "t" + i);
            thread.start();
        }

        try {
            System.out.println("the race begin");
            begin.countDown();
            end.await();
            System.out.println("the race end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 选手
 */
class Player implements Runnable {

    private CountDownLatch begin;
    private CountDownLatch end;

    Player(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            begin.await();
            System.out.println(Thread.currentThread().getName() + " arrived!");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
