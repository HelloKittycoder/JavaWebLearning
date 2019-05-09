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
