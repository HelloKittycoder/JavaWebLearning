package com.kittycoder.part03.ch18;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

/**
 * Created by shucheng on 2019-9-24 下午 23:17
 * 使用ThreadPoolExecutor自定义拒绝策略
 * jdk提供的策略有：默认策略，DiscardOldestPolicy等
 */
public class UseThreadPoolExecutor1 {

    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于等于corePoolSize，则会将任务加入队列
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式
         *
         * 以下面这种自定义线程池为例：
         * corePoolSize 1
         * maximumPoolSize 2
         * keepAliveTime 60
         * TimeUnit.SECONDS
         * 使用ArrayBlockingQueue
         *
         * 1.使用默认拒绝策略：
         * 打印结果：
         * run taskId = 5
         * Exception--Task 6
         * run taskId = 1
         * run taskId = 2
         * run taskId = 3
         * run taskId = 4
         *
         * 说明：
         * 一开始线程数为0，小于corePoolSize（1），则创建一个线程来执行任务（任务1）
         * 此时corePoolSize为1，任务2会被加到队列里
         * 此后任务3，任务4都会被加到队列中（这时队列已经满了）
         * 任务5这时要加进来，corePoolSize已经被占掉了，队列也已经满了，然后发现线程数还没有大于maximumPoolSize（2），再创建一个线程来处理这个任务
         * 这时还有任务6，但是这时没有地方放了，只能采用默认的拒绝策略，直接抛出异常
         *
         * 2.使用DiscardOldestPolicy策略（丢弃最旧的数据）
         * 打印结果：
         * run taskId = 1
         * run taskId = 5
         * run taskId = 3
         * run taskId = 4
         * run taskId = 6
         *
         * 说明：
         * 一开始线程数为0，小于corePoolSize（1），则创建一个线程来执行任务（任务1）
         * 此时corePoolSize为1，任务2会被加到队列里
         * 此后任务3，任务4都会被加到队列中（这时队列已经满了）
         * 任务5这时要加进来，corePoolSize已经被占掉了，队列也已经满了，然后发现线程数还没有大于maximumPoolSize（2），再创建一个线程来处理这个任务
         * 这时还有任务6，但是这时没有地方放了，只能采用DiscardOldestPolicy策略，把最早放到队列里的2给删掉了（所以最终打印的结果中没有2）
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, // coreSize
                2, // MaxSize
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3) // 指定一种队列（有界队列）
                // new LinkedBlockingQueue<>()
                // ,new MyRejected()
                // ,new DiscardOldestPolicy()
        );

        MyTask mt1 = new MyTask(1, "任务1");
        MyTask mt2 = new MyTask(2, "任务2");
        MyTask mt3 = new MyTask(3, "任务3");
        MyTask mt4 = new MyTask(4, "任务4");
        MyTask mt5 = new MyTask(5, "任务5");
        MyTask mt6 = new MyTask(6, "任务6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        pool.shutdown();
    }
}
