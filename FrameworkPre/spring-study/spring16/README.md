### 声明式事务
1. 编程式事务  
1.1 由程序员编写事务控制代码  
1.2 OpenSessionInView 编程式事务  
2. 声明式事务  
2.1 事务控制代码已经由spring写好，程序员只需要声明出那些方法  
需要进行事务控制和如何进行事务控制  
3. 声明式事务都是针对于ServiceImpl类下方法的  
4. 事务管理器是基于通知（advice）的  
5. 在spring配置文件中配置声明式事务  
```xml
<!-- 声明式事务配置 -->
<!-- 数据源封装类
   数据源：获取数据库连接
   spring-jdbc.jar中
-->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<!-- 通知类在spring-jdbc.jar中 -->
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 配置需要进行事务控制的方法 -->
<tx:advice id="txAdvice" transaction-manager="txManager">
    <tx:attributes>
        <tx:method name="ins*"/>
        <tx:method name="upd*"/>
        <tx:method name="del*"/>
        <tx:method name="*"/>
    </tx:attributes>
</tx:advice>

<aop:config>
    <!-- 切点范围设置大一些 -->
    <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.service.impl.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="mypoint"/>
</aop:config>
```

### 声明式事务中属性解释
1. name="" 哪些方法需要有事务控制  
支持*通配符  
2. readonly="boolean" 是否为只读事务  
2.1 如果true，告诉数据库此事务为只读事务。数据库优化，会对  
性能有一定提升，所以只要是查询的方法，建议使用此选项  
2.2 如果为false（默认值），事务就是需要做提交或回滚的事务。建议新增、删除、修改时用  
3. propagation 控制事务传播行为  
3.1 当一个具有事务控制的方法被另一个有事务控制的方法调用后，需要如何管理事务
（新建事务？在原有事务中执行？把事务挂起？报异常？）  
3.2 REQUIRED（默认值）：如果当前有事务，就在事务中执行；如果当前没有事务，就新建一个事务  
3.3 SUPPORTS：如果当前有事务，就在事务中执行；如果当前没有事务，就在非事务状态下执行  
3.4 MANDATORY：必须在事务内执行。如果当前有事务，就在事务中执行；如果没有事务，报错  
3.5 REQUIRES_NEW：必须在事务中执行。如果当前没有事务，就新建一个事务；如果当前有事务，  
把当前事务挂起  
3.6 NOT_SUPPORTED：必须在非事务状态下执行。如果当前没有事务，正常执行；如果当前有事务，  
把当前事务挂起  
3.7 NEVER：必须在非事务状态下执行。如果当前没有事务，正常执行；如果当前有事务，报错  
3.8 NESTED：必须在事务状态下执行。如果没有事务，则新建一个事务；如果当前有事务，创建一个嵌套事务
4. isolation="" 事务隔离级别  
4.1 在多线程或并发访问下下如何保证访问到的数据是具有完整性的  
4.2 脏读：  
一个事务（A）读取到另一个事务（B）中未提交的数据，另一个事务中数据可能进行了改变。  
此时A事务读取的数据可能和数据库中数据是不一致的，此时认为是脏数据。读取脏数据的过程  
叫做脏读  
4.3 不可重复读：  
（1） 主要针对的是某行数据（或行中某列）  
（2） 主要针对的是修改操作  
（3） 两次读取在同一个事务内
（3） 当事务A第一次读取数据后，事务B对事务A读取的数据进行修改，事务A中再次读取的数据和  
和之前读取的数据不一致。该过程称为不可重复读  
4.4 幻读：  
（1） 主要针对的操作是新增或删除  
（2） 两次事务的结果  
（3） 事务A按照特定条件查询出结果，事务B新增了一条符合条件的数据。事务A中查询的数据和数据库  
中的数据不一致，事务A好像出现了幻觉，这种情况称为幻读  
4.5 DEFAULT：默认值，有底层数据库自动判断应该使用什么隔离级别  
4.6 READ_UNCOMMITED：可以读取未提交数据，可能出现脏读、不可重复读、幻读（效率最高）  
4.7 READ_COMMITED：只能读取其他事务已提交数据。可以防止脏读，可能出现不可重复读和幻读  
4.8 REPEATABLE_READ：读取的数据被添加锁，防止其他事务修改此数据。可以防止不可重复读，脏读，  
可能出现幻读  
4.9 SERIALIZABLE：排队操作，对整个表添加锁。一个事务在操作数据时，另一个事务等待事务  
操作完成后才能操作这个表  
（1）最安全的 （2）效率最低的
