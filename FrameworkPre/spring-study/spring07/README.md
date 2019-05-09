### 通配符使用和前置、后置通知的参数含义
1. 通配符使用
1.1 *通配符，匹配任意方法名，任意类名，任意一级包名  
1.2 如果希望匹配任意方法参数 (..)  
切点表达式举例：  
```text
execution(* com.bjsxt.interceptMethod.Demo.demo1())
拦截com.bjsxt.interceptMethod.Demo类的demo1()方法

execution(* com.bjsxt.interceptMethod.Demo.demo2(String,int))
拦截com.bjsxt.interceptMethod.Demo类的demo2(String,int)方法

execution(* com.bjsxt.interceptMethod.Demo.*())
拦截com.bjsxt.interceptMethod.Demo类的所有无参方法

execution(* com.bjsxt.interceptMethod.Demo.*(..))
拦截com.bjsxt.interceptMethod.Demo类的任意参数数量（有参+无参）的方法

execution(* com.bjsxt.interceptMethod.*.*(..))
拦截com.bjsxt.interceptMethod包下的任意类的任意参数数量（有参+无参）的方法

execution(* com.bjsxt.*.service.impl.*.*(..))
拦截com.bjsxt包下的任意模块的service.impl包下的任意类的任意参数数量（有参+无参）的方法
```
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
        <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.*.*(..))"/>

        <!-- 通知 -->
        <aop:advisor advice-ref="mybefore" pointcut-ref="mypoint"/>
        <aop:advisor advice-ref="myafter" pointcut-ref="mypoint"/>
    </aop:config>

    <!-- 配置Demo类，测试使用 -->
    <bean id="demo" class="com.bjsxt.interceptMethod.Demo"></bean>
    <bean id="demo1" class="com.bjsxt.interceptMethod.Demo1"></bean>
</beans>
```

2. 前置、后置通知参数含义  
2.1 前置通知参数含义  
（1）method：切点方法对象 Method对象  
（2）args：切点方法参数  
（3）target：切点拦截的调用对象（是原始对象，而非代理对象）
```java
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("********************************前置start*******************************");
        System.out.println("切点方法对象：" + method + "，方法名：" + method.getName());
        if (args != null && args.length > 0) {
            System.out.println("切点方法参数：" + args);
        } else {
            System.out.println("切点方法没有参数");
        }
        System.out.println("切点拦截的调用对象：" + target);
        System.out.println("执行前置通知");
        System.out.println("********************************前置end*******************************");
    }
}
```
2.2 后置通知参数含义  
（1）returnValue：切点方法的返回值  
（2）method：切点方法对象 Method对象  
（3）args：切点方法参数  
（4）target：切点拦截的调用对象（是原始对象，而非代理对象）
```java
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("********************************后置start*******************************");
        System.out.println("切点方法返回值：" + returnValue);
        System.out.println("切点方法对象：" + method + "，方法名：" + method.getName());
        if (args != null && args.length > 0) {
            System.out.println("切点方法参数：" + args);
        } else {
            System.out.println("切点方法没有参数");
        }
        System.out.println("切点拦截的调用对象：" + target);
        System.out.println("执行后置通知");
        System.out.println("********************************后置end*******************************");
    }
}
```