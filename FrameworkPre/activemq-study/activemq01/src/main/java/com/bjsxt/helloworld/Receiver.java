package com.bjsxt.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by shucheng on 2019-10-6 上午 10:26
 */
public class Receiver {

    public static void main(String[] args) throws Exception {

        // 第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码以及要连接的地址，均使用默认即可，默认端口为“tcp://localhost:61616”
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616");

        // 第二步：通过ConnectionFactory工厂对象创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 第三步：通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数配置1为是否启用事务，参数配置2为签收模式，一般我们设置自动签收
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        // 第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式中，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic
        Destination destination = session.createQueue("first");

        // 第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者），MessageProducer/MessageConsumer
        MessageConsumer consumer = session.createConsumer(destination);

        while (true) {
            // TextMessage msg = (TextMessage) consumer.receive();
            // if (msg == null) break;
            // System.out.println("收到的内容：" + msg.getText());
            TextMessage msg = (TextMessage) consumer.receive();
            // System.out.println(msg.getStringProperty("name"));
            System.out.println("消费数据：" + msg.getText());
        }
    }
}
