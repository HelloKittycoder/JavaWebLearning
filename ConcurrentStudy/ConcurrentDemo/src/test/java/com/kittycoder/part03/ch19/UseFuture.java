package com.kittycoder.part03.ch19;

import java.util.concurrent.*;

/**
 * Created by shucheng on 2019-9-25 下午 14:20
 * Future使用demo
 * https://blog.csdn.net/u010365819/article/details/80761332
 * https://www.cnblogs.com/cz123/p/7693064.html
 *
 * 说明：
 * FutureTask的get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
 *
 * 打印结果：总共用时1001ms
 * 可以看出，比较耗时的操作是在主线程（1000ms）；UseFuture的call方法执行只要500ms
 * 启动了两个线程来调用UseFuture的call方法，两次异步操作总共只花了500ms
 * 因为主线程和两次异步操作几乎是同时开始的，所以总共用时大概在1000ms左右
 *
 * 对以下代码执行流程的说明：
 * 1.创建2个FutureTask，构造参数是实现了Callable接口（可以接到返回值）的UseFuture类
 * 2.创建一个带有2个线程的线程池，并把前面的2个FutureTask提交给2个线程分别去执行；
 * 同时主线程开始处理耗时的业务逻辑
 * 3.主线程业务逻辑处理完毕后，调用get方法获取FutureTask中的数据；
 * 这两个异步线程执行完FutureTask总共花费了500ms，主线程业务逻辑需要1000ms。
 * 虽然get()方法会有阻塞，但是这是在没有拿到结果的情况下，此时主线程业务执行完以后，异步线程都已经拿到结果了；
 * 所以get()方法相当于阻塞，直接就可以取到数据
 * 4.调用shutdown关闭线程池中的线程
 */
public class UseFuture implements Callable<String> {

    private String para;

    public UseFuture(String para) {
        this.para = para;
    }

    /**
     * 这里是真实的业务逻辑，其执行可能很慢
     */
    @Override
    public String call() throws Exception {
        // 模拟执行耗时
        Thread.sleep(500);
        String result = this.para + "处理完成";
        return result;
    }

    // 主控制函数
    public static void main(String[] args) throws Exception {
        String queryStr = "query";
        // 构造FutureTask，并且传入真正进行业务逻辑处理的类，该类一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<>(new UseFuture(queryStr));
        FutureTask<String> future2 = new FutureTask<>(new UseFuture(queryStr));

        // 创建一个固定线程的线程池且线程数为2
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 这里提交任务future，则开启线程执行RealData的call()
        // submit和execute的区别：1.submit可以传入实现Callable接口的实例对象
        // 2.submit方法有返回值
        long startTime = System.currentTimeMillis();
        Future f1 = executor.submit(future); // 单独启动一个线程去执行
        Future f2 = executor.submit(future2);
        System.out.println("请求完毕");

        try {
            // 这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
            System.out.println("处理实际的业务逻辑...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 调用获取数据方法，如果call()方法没有执行完成，则依然会进行等待
        System.out.println("数据：" + future.get());
        System.out.println("数据：" + future2.get());
        System.out.println("总共用时：" + (System.currentTimeMillis() - startTime) + "ms");

        executor.shutdown();
    }
}
