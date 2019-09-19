package com.kittycoder.ch12;

import java.util.*;

/**
 * Created by shucheng on 2019-9-19 下午 22:03
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 *
 * ConcurrentModificationException异常（参考：https://www.cnblogs.com/snowater/p/8024776.html）
 */
public class Tickets {

    public static void main(String[] args) {
        // 初始化火车票池并添加火车票：避免线程同步可采用Vector替代

        final Vector<String> tickets = new Vector<String>();
        // 另外可以用Collections.synchronizedxxx生成带有同步特性的容器
        // Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

        for (int i = 1; i <= 1000; i++) {
            tickets.add("火车票" + i);
        }

        /* 这段会报ConcurrentModificationException异常
        for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            tickets.remove(20);
        }*/

        for (int i = 1; i <= 10; i++) {
            new Thread("线程" + i) {
                @Override
                public void run() {
                    while (true) {
                        if (tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}
