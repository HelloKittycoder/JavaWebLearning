package com.kittycoder.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2019-9-26 上午 9:15
 */
public class HandlerExecutorPool {

    private ExecutorService executor;

    public HandlerExecutorPool(int maxPoolSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable task) {
        this.executor.execute(task);
    }
}
