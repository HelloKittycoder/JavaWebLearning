package com.kittycoder.ch02_multithreadlock;

/**
 * Created by shucheng on 2019-9-15 下午 23:09
 *
 * 关键字加载实例方法上取得的锁是对象锁，而不是把一段代码（方法）当作锁
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock）
 *
 * 在静态方法上加synchronized关键字，表示锁定.class类，类级别的锁（独占.class类）
 *
 * https://blog.csdn.net/wxgxgp/article/details/79931653
 * https://blog.csdn.net/xiao__gui/article/details/8188833
 *
 * 解释下不同情况下代码的结果：
 * 首先t1->m1，t2->m2是两个不同线程（t1，t2）去访问各自的实例对象（m1，m2），也就是两个通道，
 * 两者之间没有关系
 *
 * （下面打印顺序不一定严格是这个顺序，只是可能会出现的顺序）
 * A. printNum没有被static修饰：（这个时候synchronized没有起到作用，此时是同时设值的）
 * 1. num没有被static修饰：打印结果为
 * tag b, set num over!200
 * tag a, set num over!100
 * tag b,num=200（等了1秒后，打印下一行）
 * tag a,num=100
 * 因为num是跟着各自的实例走的，所以打印的是不同的num
 *
 * 2. num被static修饰：打印结果为
 * tag b, set num over!200
 * tag a, set num over!200
 * tag b,num=200（等了1秒后，打印下一行）
 * tag a,num=200
 * 因为num是静态属性，只有1份，又由于最后走的b，被设置成200，
 * 所以最终打印的是200
 *
 * B. printNum被static修饰：（这个synchronized起到作用了，一个线程在设值时，另一个线程只能等待）
 * 1. num没有被static修饰（代码编译不通过，静态方法无法访问实例属性；因为执行静态方法的时候，实例属性不一定存在）
 *
 * 2. num被static修饰：打印结果为
 * tag a, set num over!100（等了1秒后，打印下一行）
 * tag a,num=100
 * tag b, set num over!200
 * tag b,num=200
 * 此时a获得类锁，将num设置成100，代码执行完成后释放锁；然后b获得类锁，将num设置成200
 */
public class MultiThreadTest {

    private int num = 0;

    /** static **/
    public synchronized void printNum(String tag) {
        try {
            if (tag.equals("a")) {
                num = 100;
                System.out.println("tag a, set num over!" + num);
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over!" + num);
            }
            System.out.println("tag " + tag + ",num=" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 注意观察run方法输出顺序
    public static void main(String[] args) {

        // 两个不同的对象
        MultiThreadTest m1 = new MultiThreadTest();
        MultiThreadTest m2 = new MultiThreadTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();
    }
}