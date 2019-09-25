package com.kittycoder.part02.ch18;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by shucheng on 2019-9-24 下午 23:21
 */
public class MyRejected implements RejectedExecutionHandler {

    public MyRejected() {}

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义处理... 当前被拒绝任务为：" + r.toString());
    }
}
