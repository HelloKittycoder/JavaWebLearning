package com.bjsxt.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2019-10-6 上午 10:11
 */
public class Sender {

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
        MessageProducer producer = session.createProducer(null);

        // 第六步：我们需要通过MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode）
        // producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // 第七步：最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），并用MessageProducer的send方法发送数据，同理客户端使用receive方法接收数据。最后不要忘记关闭Connection连接。
        for (int i = 0; i < 100; i++) {
            TextMessage msg = session.createTextMessage("我是消息内容" + i);
            // 第一个参数 目标地址
            // 第二个参数 具体的数据信息
            // 第三个参数 传送数据的模式
            // 第四个参数 优先级
            // 第五个参数 消息的过期时间
            producer.send(destination, msg);
            // System.out.println(msg.getText());
            TimeUnit.SECONDS.sleep(1);
        }

        /*for (int i = 0; i < 5; i++) {
            MapMessage map = session.createMapMessage();
            map.setString("name", "张三");
            map.setString("age", "20");
            map.setString("address", "北京");
            producer.send(destination, map, DeliveryMode.NON_PERSISTENT, 0, 1000L*1000);
        }*/

        if (connection != null) {
            connection.close();
        }
    }
}
