### Spring中加载properties文件
1. 在src下新建xxx.properties文件  
2. 在spring配置文件中先引入xmlns:context，在下面添加  
如果需要加载多个配置文件，需要使用逗号分隔，  
写成 classpath:db.properties,classpath:db1.properties
```xml
<context:property-placeholder location="classpath:db.properties"/>
```
3. 添加了属性文件加载，并且在&lt;beans&gt;中开启自动注入需要注意的地方：  
3.1 SqlSessionFactoryBean的id不能叫做sqlSessionFactory  
3.2 修改如下：
把原来通过ref引用替换成value赋值。自动注入只能影响ref，不会影响value赋值
```xml
<!-- 创建SqlSessionFactory对象 -->
<bean id="factory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <!-- 设置要起别名的类所在的包路径 -->
    <property name="typeAliasesPackage" value="com.bjsxt.pojo"/>
</bean>

<!-- 扫描器：相当于mybatis.xml中mappers下的package标签 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <!--<property name="sqlSessionFactory" ref="factory"/>-->
    <property name="sqlSessionFactoryBeanName" value="factory"/>
    <!-- 要扫描哪个包 -->
    <property name="basePackage" value="com.bjsxt.mapper"/>
</bean>
```
4. 在被Spring管理的类中通过@Value("${key}")取出properties的内容  
4.1 添加注解扫描  
```xml
<context:component-scan base-package="com.bjsxt.service.impl"/>
```
4.2 在类中添加  
（1）key和变量名可以不相同  
（2）变量类型任意，只要保证key对应的value能转换成这个类型就可以
```java
@Value("${my.demo}")
private String test;
```