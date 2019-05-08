### 使用Spring简化MyBatis
1. 导入mybatis所有jar和spring基本包（beans、context、core、el），spring-jdbc，  
spring-tx，spring-sop，mybatis-spring等  
2. 编写spring配置文件applicationContext.xml  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 数据源封装类
        数据源：获取数据库连接
        spring-jdbc.jar中
     -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <!-- 创建SqlSessionFactory对象 -->
    <bean id="factory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 数据库连接信息来源于dataSource -->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 扫描器：相当于mybatis.xml中mappers下的package标签 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 要扫描哪个包 -->
        <property name="basePackage" value="com.bjsxt.mapper"/>
        <!-- 和factory产生关系 -->
        <property name="sqlSessionFactory" ref="factory"/>
    </bean>

    <!-- 由spring管理service实现类（要想属性被注入，该属性所在的类要被spring管理） -->
    <bean id="airportService" class="com.bjsxt.service.impl.AirportServiceImpl">
        <property name="airportMapper" ref="airportMapper"/>
    </bean>
</beans>
```
3. 编写代码  
3.1 正常编写pojo  
3.2 编写mapper包下时必须使用接口绑定方案或注解方法（必须有接口）  
3.3 正常编写Service接口和Service实现类  
说明： 需要在Service实现类中声明Mapper接口对象，并生成get/set方法
3.4 spring无法管理servlet