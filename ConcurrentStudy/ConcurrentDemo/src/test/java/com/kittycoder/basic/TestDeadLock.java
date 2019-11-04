package com.kittycoder.basic;

/**
 * Created by shucheng on 2019-11-4 下午 21:44
 * 测试死锁
 */
class A {
    synchronized void foo(B b) {
        System.out.println("当前线程名：" + Thread.currentThread().getName() + " 调用A实例的foo方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.last();
    }

    synchronized void last() {
        System.out.println("进入了A.last");
    }
}

class B {
    synchronized void bar(A a) {
        System.out.println("当前线程名：" + Thread.currentThread().getName() + " 调用B实例的bar方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.last();
    }

    synchronized void last() {
        System.out.println("进入了B.last");
    }
}

// 创建两个子线程来调用
/*public class TestDeadLock {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.foo(b);
            }
        }, "t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.bar(a);
            }
        }, "t2").start();
    }
}*/

// 先启动子线程，然后再调用主线程
public class TestDeadLock implements Runnable {
    A a = new A();
    B b = new B();

    public static void main(String[] args) {
        TestDeadLock td = new TestDeadLock();
        new Thread(td).start();
        td.init();
    }

    public void init() {
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入了主线程以后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("子线程");
        b.bar(a);
        System.out.println("进入了子线程以后");
    }
}