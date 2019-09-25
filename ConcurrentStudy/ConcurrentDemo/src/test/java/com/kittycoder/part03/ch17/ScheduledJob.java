package com.kittycoder.part03.ch17;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2019-9-25 上午 11:16
 */
public class ScheduledJob {

    public static void main(String[] args) {
        Temp command = new Temp();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // initialDelay：5（初始化任务的时间为5s）
        // delay：1（每隔1s执行一次任务）
        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);
    }
}

class Temp extends Thread {
    @Override
    public void run() {
        System.out.println("run");
    }
}
