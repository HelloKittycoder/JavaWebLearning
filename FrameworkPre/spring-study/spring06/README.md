### AOP
1. 中文名称：面向切面编程  
2. 英文名称：Aspect Oriented Programming  
3. 正常程序执行流程都是纵向执行流程  
3.1 面向切面编程，在原有纵向执行流程中添加横切面  
3.2 不需要修改原有程序代码  
（1）体现出程序的高扩展性  
（2）原有功能相当于释放了部分逻辑，让职责更加明确  
4. 面向切面编程是什么？  
在程序原有纵向执行流程中，针对某一个或某一些方法添加通知，形成横切面的过程就叫做面向切面编程  
5. 常用概念  
5.1 原有功能：切点 pointcut  
5.2 前置通知：在切点之前执行的功能。before advice  
5.3 后置通知：在切点之后执行的功能。after advice  
5.4 如果切点执行过程中出现异常，会触发异常通知。throws advice  
5.5 所有功能总称叫做切面  
5.6 织入：把切面嵌入原有功能的过程叫做织入  
6. spring提供了两种AOP实现方式  
6.1 Schema-based  
（1）每个通知都需要实现接口或类  
（2）是在&lt;aop:config&gt;中配置  
6.2 AspectJ  
（1）每个通知不需要实现接口或类  
（2）是在&lt;aop:config&gt;的子标签&lt;aop:aspect&gt;中配置  

### Schema-based实现步骤
1. 导入jar（spring版本：4.1.6）  
aopalliance.jar  
aspectjweaver.jar  
commons-logging-1.1.3.jar  
spring-aop.jar  
spring-aspects.jar  
spring-beans.jar  
spring-context.jar  
spring-core.jar  
spring-expression.jar  
spring-tx.jar  
2. 新建通知类  
2.1 新建前置通知类  
```java
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行前置通知，拦截的方法名称为==========" + method.getName());
    }
}
```
2.2 新建后置通知类  
```java
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行后置通知，拦截的方法名称为==========" + method.getName());
    }
}
```
3. 在spring配置文件中添加配置  
3.1 引入aop命名空间  
3.2 配置通知类的&lt;bean&gt;  
3.3 配置切面
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置通知类对象，需要在切面中引入 -->
    <bean id="mybefore" class="com.bjsxt.advice.MyBeforeAdvice"></bean>
    <bean id="myafter" class="com.bjsxt.advice.MyAfterAdvice"></bean>

    <!-- 配置切面 -->
    <aop:config>
        <!-- 配置切点 -->
        <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.Demo.demo2(String,int))"/>
        <aop:pointcut id="mypoint2" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1())"/>

        <!-- 通知 -->
        <aop:advisor advice-ref="mybefore" pointcut-ref="mypoint"/>
        <aop:advisor advice-ref="myafter" pointcut-ref="mypoint2"/>
    </aop:config>

    <!-- 配置Demo类，测试使用 -->
    <bean id="demo" class="com.bjsxt.interceptMethod.Demo"></bean>
</beans>
```
4. 编写测试代码  
```java
public class DemoTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test() {
        Demo demo = ac.getBean("demo", Demo.class);
        demo.demo1();
        demo.demo2("111", 11);
        demo.demo3();
    }
}
```
5. 运行结果  
```text
demo1
执行后置通知，拦截的方法名称为==========demo1
执行前置通知，拦截的方法名称为==========demo2
demo2
demo3
```