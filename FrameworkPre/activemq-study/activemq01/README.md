### ActiveMQ学习-HelloWorld
首先写一个简单的Hello World示例，感受下ActiveMQ。我们需要实现接受者和发送者两部分代码的编写  
Sender/Receiver：  
第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码以及要连接的地址，均使用默认即可，默认端口为“tcp://localhost:61616”  
第二步：通过ConnectionFactory工厂对象创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的  
第三步：通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数配置1为是否启用事务，参数配置2为签收模式，一般我们设置自动签收  
第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式中，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic  
第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者），MessageProducer/MessageConsumer  
第六步：我们需要通过MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode）  
第七步：最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），并用MessageProducer的send方法发送数据，同理客户端使用receive方法接收数据。最后不要忘记关闭Connection连接。  
（这步另外说明下：生产者是短连接，用完就关掉connection；消费者是长连接，不需要关掉connection）

### ActiveMQ学习-P2P（点对点消费）
见p2p包（主要关注下Consumer.java中session.createConsume可以对消息做筛选，另外可以对消息进行监听onMessage）

### ActiveMQ学习-publish/subscribe（发布/订阅）
见pb包

### ActiveMQ学习-简单模拟下负载均衡
见action包