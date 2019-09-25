package com.kittycoder.part03.ch19;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shucheng on 2019-9-25 下午 13:51
 * CyclicBarrier使用demo
 * CyclicBarrier作用：让所有线程都等带完成后才会继续下一步行动（如何体现Cyclic含义可以参看，https://www.jianshu.com/p/333fd8faa56e）
 *
 * 下面的例子：等所有运动员都准备OK后，开始赛跑
 * 打印结果：
 * lisi 准备OK.
 * zhangsan 准备OK.
 * wangwu 准备OK.
 * wangwu Go!!
 * lisi Go!!
 * zhangsan Go!!
 *
 * 代码可能的执行流程：
 * 1.主线程创建一个带有3个参与者的CyclicBarrier，
 * 2.创建一个有3个线程的线程池，对应3个参与者（这些参与者都持有CyclicBarrier引用）
 * 3.提交3个线程到线程池去执行
 * 4.这3个线程是并发执行的，lisi准备OK，告诉barrier我已经准备好了（调用await方法），然后lisi被阻塞；
 * zhangsan准备OK，告诉barrier我已经准备好了（调用await方法），然后zhangsan被阻塞；
 * wangwu准备Ok，告诉barrier我已经准备好了，然后wangwu被阻塞
 * 5.当barrier的await方法被调用3次后，说明所有参与者都已经准备好了，此时3个线程都被释放，并发执行后面的代码
 * 打印“xxx Go!!”
 */
public class UseCyclicBarrier {

    static class Runner implements Runnable {
        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random()).nextInt(5));
                System.out.println(name + " 准备OK.");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " Go!!");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3); // 3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Thread(new Runner(barrier, "zhangsan")));
        executor.submit(new Thread(new Runner(barrier, "lisi")));
        executor.submit(new Thread(new Runner(barrier, "wangwu")));

        executor.shutdown();
    }
}
