package com.kittycoder.part01.ch07;

/**
 * Created by shucheng on 2019-9-17 下午 23:17
 * volatile关键字：主线程和子线程的变量共享（更改一个变量后，其他所有线程都能感知到）
 *
 * 下面的例子需要给isRunning加上volatile关键字才能看到效果
 *
 * 说明：
 * 线程在获得锁，然后释放锁以后，会把变量的状态更新到主存中
 * volatile是解决线程在获得锁和释放锁之间的时间节点，将变量的状态更新到主存中的问题
 */
public class RunThread extends Thread {

    /*volatile*/
    private boolean isRunning = true;
    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("进入run方法...");
        while (isRunning == true) {
            // ...
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        Thread.sleep(1000);
        rt.setRunning(false);
        System.out.println("isRunning的值已经被设置成了false");
    }
}
