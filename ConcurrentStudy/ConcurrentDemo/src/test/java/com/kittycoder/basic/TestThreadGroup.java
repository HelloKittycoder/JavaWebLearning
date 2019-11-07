package com.kittycoder.basic;

/**
 * Created by shucheng on 2019-11-7 下午 22:21
 * 测试线程组
 */
public class TestThreadGroup extends Thread {

    public TestThreadGroup(String name) {
        super(name);
    }

    public TestThreadGroup(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // getThreadGroup().getName()：线程组名
            // getName()：线程名
            System.out.println(getThreadGroup().getName() + "*****" + getName() + "*****" + i);
        }
    }

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程所在的线程组：" + mainGroup);
        System.out.println("主线程是否为后台线程组：" + mainGroup.isDaemon());

        TestThreadGroup m1 = new TestThreadGroup(mainGroup, "测试1");
        m1.start();
        new TestThreadGroup("测试2").start();

        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否为后台线程组：" + tg.isDaemon());
        TestThreadGroup tg1 = new TestThreadGroup(tg, "tg组的线程甲");
        tg1.start();
        new TestThreadGroup(tg, "tg组的线程乙").start();
    }
}
