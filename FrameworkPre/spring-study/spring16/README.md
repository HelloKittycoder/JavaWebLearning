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