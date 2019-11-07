package com.kittycoder.basic.daemon;

/**
 * Created by shucheng on 2019-11-7 下午 22:00
 * 测试后台线程，比较后台线程和前台线程的区别
 *
 * 代码说明：
 * 主线程是前台线程，t也是前台线程，t_daemon是后台线程
 * 前台线程（主线程，t线程）里的代码都会完全执行完；
 * 只要前台线程代码已经执行完了（打印“主线程开始”、“主线程结束”，再进行10次循环打印），就算后台线程的代码（100次循环打印）没执行完，
 * JVM还是会主动退出，后台进程也被结束掉了
 *
 * 可以尝试把t_daemon.setDaemon(true);这行注释掉（这个时候t_daemon成为普通线程，100次循环打印会全都执行完），看下有什么区别
 */
public class TestDaemonThread {

    public static void main(String[] args) {

        System.out.println("主线程开始");

        Thread t_daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("我是后台线程" + i);
                }
            }
        });
        t_daemon.setDaemon(true);
        t_daemon.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "====" + i);
                }
            }
        });
        t.start();

        System.out.println("主线程结束");
    }
}
