package com.bjsxt.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by shucheng on 2019-10-7 下午 18:22
 */
public class Consumer {

    public static final String SELECTOR_0 = "age > 25";

    public static final String SELECTOR_1 = "color = 'blue'";
    // "JMSType = 'car' AND weight > 2500"

    public static final String SELECTOR_2 = "color = 'blue' AND sal > 2000";

    public static final String SELECTOR_3 = "receiver = 'A'";

    // public static final String SELECTOR_4 = "receiver = 'B'";

    // 1 连接工厂
    private ConnectionFactory connectionFactory;
    // 2 连接对象
    private Connection connection;
    // 3 Session对象
    private Session session;
    // 4 消费者
    private MessageConsumer messageConsumer;
    // 5 目标地址
    private Destination destination;

    public Consumer() {
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://localhost:61616"
            );
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.destination = this.session.createQueue("first");
            // 创建消费者的时候发生了变化
            this.messageConsumer = this.session.createConsumer(this.destination, SELECTOR_2);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receiver() {
        try {
            this.messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            try {
                if (message instanceof TextMessage) {}
                if (message instanceof MapMessage) {
                    MapMessage ret = (MapMessage) message;
                    System.out.println(ret.toString());
                    System.out.println(ret.getString("name"));
                    System.out.println(ret.getString("age"));
                }
                // ...
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Consumer c = new Consumer();
        c.receiver();
    }
}
