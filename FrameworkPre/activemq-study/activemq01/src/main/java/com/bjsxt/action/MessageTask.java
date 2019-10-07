package com.bjsxt.action;

import javax.jms.MapMessage;

/**
 * Created by shucheng on 2019-10-7 下午 21:51
 */
public class MessageTask implements Runnable {

    private MapMessage message;

    public MessageTask(MapMessage message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("当前线程：" + Thread.currentThread().getName()
                    + "处理任务：" + this.message.getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
