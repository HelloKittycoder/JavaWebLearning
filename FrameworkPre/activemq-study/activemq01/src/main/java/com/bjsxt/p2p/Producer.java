package com.bjsxt.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by shucheng on 2019-10-7 下午 18:23
 */
public class Producer {

    // 单例模式

    // 1 连接工厂
    private ConnectionFactory connectionFactory;
    // 2 连接对象
    private Connection connection;
    // 3 Session对象
    private Session session;
    // 4 生产者
    private MessageProducer messageProducer;

    public Producer() {
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://localhost:61616");
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.messageProducer = this.session.createProducer(null);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
        return this.session;
    }

    public void send1(/*String queueName, Message message*/) {
        try {
            Destination destination = this.session.createQueue("first");
            MapMessage msg1 = this.session.createMapMessage();
            msg1.setString("name", "张三");
            msg1.setString("age", "23");
            msg1.setStringProperty("color", "blue");
            msg1.setIntProperty("sal", 2200);
            MapMessage msg2 = this.session.createMapMessage();
            msg2.setString("name", "李四");
            msg2.setString("age", "26");
            msg2.setStringProperty("color", "red");
            msg2.setInt("sal", 1300);
            MapMessage msg3 = this.session.createMapMessage();
            msg3.setString("name", "王五");
            msg3.setString("age", "28");
            msg3.setStringProperty("color", "green");
            msg3.setIntProperty("sal", 1500);
            MapMessage msg4 = this.session.createMapMessage();
            msg4.setString("name", "赵六");
            msg4.setString("age", "30");
            msg4.setStringProperty("color", "blue");
            msg4.setIntProperty("sal", 1800);

            /*MapMessage msg1 = this.session.createMapMessage();
            msg1.setString("name", "张三");
            msg1.setString("age", "23");
            msg1.setStringProperty("color", "blue");
            msg1.setIntProperty("sal", 2200);
            int id = 1;
            msg1.setInt("id", id);
            String receiver = id%2 == 0 ? "A" : "B";
            msg1.setStringProperty("receiver", receiver);
            MapMessage msg2 = this.session.createMapMessage();
            msg2.setString("name", "李四");
            msg2.setString("age", "26");
            msg2.setStringProperty("color", "red");
            msg2.setInt("sal", 1300);
            id = 2;
            msg2.setInt("id", id);
            receiver = id%2 == 0 ? "A" : "B";
            msg2.setStringProperty("receiver", receiver);
            MapMessage msg3 = this.session.createMapMessage();
            msg3.setString("name", "王五");
            msg3.setString("age", "28");
            msg3.setStringProperty("color", "green");
            msg3.setIntProperty("sal", 1500);
            id = 3;
            msg3.setInt("id", id);
            receiver = id%2 == 0 ? "A" : "B";
            msg3.setStringProperty("receiver", receiver);
            MapMessage msg4 = this.session.createMapMessage();
            msg4.setString("name", "赵六");
            msg4.setString("age", "30");
            msg4.setStringProperty("color", "blue");
            msg4.setIntProperty("sal", 1800);
            id = 4;
            msg4.setInt("id", id);
            receiver = id%2 == 0 ? "A" : "B";
            msg4.setStringProperty("receiver", receiver);*/

            this.messageProducer.send(destination, msg1, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
            this.messageProducer.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 3, 1000*60*10L);
            this.messageProducer.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 6, 1000*60*10L);
            this.messageProducer.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send2(/*String queueName, Message message*/) {
        try {
            Destination destination = this.session.createQueue("first");
            TextMessage msg = this.session.createTextMessage("我是一个字符串内容");
            this.messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        p.send1();
        // p.send2();
    }
}
