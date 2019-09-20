package com.kittycoder.part01.ch04_dirtyread;

/**
 * Created by shucheng on 2019-9-16 下午 22:52
 * 脏读
 *
 * 业务整体需要使用完整的synchronized，保持业务的原子性
 */
public class DirtyReadTest {

    private String username = "bjsxt";
    private String password = "123";

    public synchronized void setValue(String username, String password) {
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;

        System.out.println("setValue最终结果：username=" + username + ",password=" + password);
    }

    public void getValue() {
        System.out.println("getValue方法得到：username=" + this.username + ",password=" + this.password);
    }

    /**
     * 分析：
     * dr是在主线程，t1是一个线程
     * Thread.sleep(2000)可以看作正在执行一个非常耗时的操作
     *
     * 打印结果为：
     * getValue方法得到：username=z3,password=123（等待2秒打印下一行）
     * setValue最终结果：username=z3,password=456
     *
     * 可能的代码执行顺序：（t1和t2的打印几乎是同时的，可以看作非常接近）
     * ----------|--dr--|   ----------|--t2--|
     *  username |  1   |       sout  |  1.5
     *    sleep  |  2   |
     *  password |  3   |
     *    sout   |  4   |
     *
     * 这个例子其实正确的做法是：如果setValue加了synchronized，那么getValue也要加synchronized
     */
    public static void main(String[] args) throws Exception {
        DirtyReadTest dr = new DirtyReadTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);

        dr.getValue();
    }
}
