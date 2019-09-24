package com.kittycoder.part02.ch13;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by shucheng on 2019-9-24 上午 8:57
 */
public class UsePriorityBlockingQueue {

    public static void main(String[] args) throws Exception {

        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id为3");

        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");

        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id为1");

        Task t4 = new Task();
        t4.setId(2);
        t4.setName("id为2");

        // return this.id > task.id ? 1 : 0;
        q.add(t1); // 3
        q.add(t2); // 4
        q.add(t3); // 1
        q.add(t4); // 2

        // 1 3 4
        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
    }
}
