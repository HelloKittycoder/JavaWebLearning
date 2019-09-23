package com.kittycoder.part02.ch13;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by shucheng on 2019-9-23 上午 9:25
 * ConcurrentLinkedQueue，ArrayBlockingQueue，LinkedBlockingQueue，SynchronousQueue
 */
public class UseQueue {

    public static void main(String[] args) throws Exception {

        // 高性能无阻塞无界队列：ConcurrentLinkedQueue
        /*
        ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.add("e");

        System.out.println(q.poll()); // a 从头部取出元素，并从队列里删除
        System.out.println(q.size()); // 4
        System.out.println(q.peek()); // b
        System.out.println(q.size()); // 4
        */

        // 阻塞队列中add、offer、put三者的区别（https://blog.csdn.net/samxie0816/article/details/47174951）
        // add 将指定元素插入此队列尾部，插入成功返回true；如果没有可用空间，则抛出IllegalStateException
        // offer 将指定元素插入此队列尾部，插入成功返回true；如果此队列已满，则返回false
        // put 将指定元素插入此队列尾部，插入成功无返回；如果此队列已满，则等待空间变得可用
        // 有界阻塞队列
        /* ArrayBlockingQueue<String> array = new ArrayBlockingQueue<>(5);
        array.put("a");
        array.put("b");
        array.add("c");
        array.add("d");
        array.add("e");
        array.add("f");
        // System.out.println(array.offer("a", 3, TimeUnit.SECONDS));
        */

        /**
        // 无界阻塞队列
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.offer("e");
        q.offer("f");
        // System.out.println(q.size());

//        for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//            String string = (String) iterator.next();
//            System.out.println(string);
//        }

        List<String> list = new ArrayList<>();
        System.out.println(q.drainTo(list, 3)); // 将队列中的值全部移除，并发设置到给定的集合中（https://www.jianshu.com/p/c228c7a6f326）
        System.out.println(list.size());
        for (String string : list) {
            System.out.println(string);
        }
        */

        // 无缓冲的队列
        SynchronousQueue<String> q = new SynchronousQueue<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(q.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                q.add("asdasd");
            }
        });
        t2.start();
    }
}
