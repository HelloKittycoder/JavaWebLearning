### 环绕通知（Schema-based方式）
1. 把前置通知和后置通知都写到一个通知中，就组成了环绕通知  
2. 实现步骤：  
2.1 新建一个类实现MethodInterceptor
```java
public class MyAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕-前置");
        Object result = invocation.proceed(); // 放行，调用切点方式
        System.out.println("环绕-后置");
        return result;
    }
}
```
2.2 配置applicationContext.xml
```xml
<!-- 环绕通知 -->
<bean id="myaround" class="com.bjsxt.advice.MyAroundAdvice"></bean>
<aop:config>
    <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1())"/>
    <aop:advisor advice-ref="myaround" pointcut-ref="mypoint"/>
</aop:config>
<bean id="demo" class="com.bjsxt.interceptMethod.Demo"></bean>
```