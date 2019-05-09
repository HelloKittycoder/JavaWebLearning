### 配置异常通知的步骤（Schema-based方式）
1. 新建一个类实现ThrowsAdvice接口  
1.1 必须自己写方法，且必须叫afterThrowing    
1.2 有两种参数方式（必须是1个或4个）  
写哪个都可以，如果两个都写，只会执行最后一个方法
1.3 异常类型要与切点报的异常类型一致
```java
public class MyThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("执行异常通知-Schema-based方式，" + ex.getMessage());
    }

    /*public void afterThrowing(Method m, Object[] args, Object target, Exception ex) {
        System.out.println("执行异常通知");
    }*/
}
```
2. 在ApplicationContext.xml中配置  
```xml
<bean id="mythrow" class="com.bjsxt.advice.MyThrowAdvice"></bean>
<aop:config>
    <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1())"/>
    <aop:advisor advice-ref="mythrow" pointcut-ref="mypoint"/>
    <!--<aop:aspect ref="mythrow">
        <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1())"/>
        <aop:after-throwing method="afterThrowing" throwing="ex" pointcut-ref="mypoint"/>
    </aop:aspect>-->
</aop:config>
<bean id="demo" class="com.bjsxt.interceptMethod.Demo"></bean>
```


1. 只有当切点报异常才能触发异常通知  
2. 在spring中只有AspectJ方式提供了异常通知的办法  
如果希望通过schema-bae实现，需要按照特定的要求自己编写方法
3. 实现步骤：  
3.1 新建类，在类中写任意名称的方法
```java
public class MyThrowAdvice {
    public void myexception(Exception e) {
        System.out.println("执行异常通知，异常message：" + e.getMessage());
    }
}
```
3.2 在spring配置文件中进行配置  
（1）&lt;aop:aspect&gt;的ref属性表示：方法在哪个类中  
（2）&lt;aop:xxxx/&gt;表示什么通知  
（3）method：当触发这个通知时，调用哪个方法  
（4）throwing：异常对象名，必须和通知中方法参数名相同（可以不在通知中声明异常对象）
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
        
    <bean id="myThrow" class="com.bjsxt.advice.MyThrowAdvice"></bean>
    <aop:config>
        <aop:aspect ref="myThrow">
            <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1())"/>
            <aop:after-throwing method="myexception" pointcut-ref="mypoint" throwing="e"/>
        </aop:aspect>
    </aop:config>
    <bean id="demo" class="com.bjsxt.interceptMethod.Demo"></bean>
</beans>
```
