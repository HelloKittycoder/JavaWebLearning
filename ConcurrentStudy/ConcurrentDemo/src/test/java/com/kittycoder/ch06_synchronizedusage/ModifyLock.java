package com.kittycoder.ch06_synchronizedusage;

/**
 * Created by shucheng on 2019-9-18 下午 21:28
 * 同一对象属性的修改不会影响锁的情况
 */
public class ModifyLock {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public synchronized void changeAttribute(String name, int age) {
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
            this.setName(name);
            this.setAge(age);
            Thread.sleep(2000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ModifyLock modifyLock = new ModifyLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttribute("张三", 20);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttribute("李四", 21);
            }
        }, "t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
