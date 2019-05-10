### 使用注解（基于AspectJ）
1. spring不会自动取去寻找注解，必须告诉spring哪些包下的类中可能有注解  
1.1 引入xmlns:context
```xml
<!-- 如果有多个包，则将包路径用逗号分隔开 com.bjsxt.advice,com.bjsxt.test -->
<context:component-scan base-package="com.bjsxt.advice"></context:component-scan>
```
2. @Component  
2.1 相当于&lt;bean/&gt;  
2.2 如果没有参数，把类名首字母变小写，相当于&lt;bean id=""/&gt;  
2.3 @Component("自定义名称")
3. 实现步骤：
3.1 在spring配置文件中设置注解在哪些包中
```xml
<!-- 如果有多个包，则将包路径用逗号分隔开 com.bjsxt.advice,com.bjsxt.test -->
<context:component-scan base-package="com.bjsxt.advice,com.bjsxt.interceptMethod"></context:component-scan>
```
3.2 在Demo类中添加@Component  
 在方法上添加@Pointcut("")定义切点
```java
@Component
public class Demo {

    @Pointcut("execution(* com.bjsxt.interceptMethod.Demo.demo1())")
    public void demo1() throws Exception {
        // int i = 1/0;
        System.out.println("demo1");
    }
}
```
3.3 在通知类中配置  
（1）@Component类被spring管理  
（2）@Aspect相当于&lt;aop:aspect/&gt;表示通知方法在当前类中
```java
@Component
@Aspect
public class MyAdvice {
    @Before("com.bjsxt.interceptMethod.Demo.demo1()")
    public void mybefore() {
        System.out.println("前置");
    }

    // 切点方法正常执行时，通知才会执行
    @AfterReturning("com.bjsxt.interceptMethod.Demo.demo1()")
    public void myaftering() {
        System.out.println("后置1");
    }

    // 不管切点是否正常执行，通知都会执行
    @After("com.bjsxt.interceptMethod.Demo.demo1()")
    public void myafter() {
        System.out.println("后置2");
    }

    @AfterThrowing("com.bjsxt.interceptMethod.Demo.demo1()")
    public void mythrow() {
        System.out.println("异常");
    }

    @Around("com.bjsxt.interceptMethod.Demo.demo1()")
    public Object myaround(ProceedingJoinPoint p) throws Throwable {
        System.out.println("环绕-start");
        Object result = p.proceed();
        System.out.println("环绕-end");
        return result;
    }
}
```
