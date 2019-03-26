package com.bjsxt;

/**
 * Create by Administrator on 2019/3/25
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        final ThreadLocal<String> threadLocal = new ThreadLocal<>();
        System.out.println(threadLocal + "=====外部线程变量");
        threadLocal.set("测试");
        new Thread() {
            public void run() {
                System.out.println(threadLocal + "=====外部线程变量");
                String result = threadLocal.get();
                System.out.println("结果：" + result);
            }
        }.start();
    }
}
