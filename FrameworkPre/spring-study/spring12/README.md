### AspectJ方式在通知中获取切点参数
1. 新建类，不用实现  
1.1 类中方法名任意  
```java
public class MyAdvice {
    public void mybefore(String name, int age) {
        System.out.println("前置" + name + "  " + age);
    }
    public void mybefore1(String name1) {
        System.out.println("前置：" + name1);
    }
    public void myafter() {
        System.out.println("后置1");
    }
    public void myaftering() {
        System.out.println("后置2");
    }
    public void mythrow() {
        System.out.println("异常");
    }
    public Object myaround(ProceedingJoinPoint p) throws Throwable {
        System.out.println("执行环绕-start");
        Object result = p.proceed();
        System.out.println("执行环绕-end");
        return result;
    }
}
```
1.2 在spring配置文件中添加配置  
（1） &lt;aop:after/&gt; 后置通知，是否出现异常都执行  
（2） &lt;aop:after-returning/&gt; 后置通知 只有当切点正确执行时，才会执行  
（3） &lt;aop:after/&gt;、&lt;aop:after-returning/&gt;和&lt;aop:after-throwing/&gt; 执行顺序和配置顺序有关  
（4） a. execution()括号不能括上args  
b. 中间使用and不能使用&&，由spring把and解析成&&  
c. args(名称) 名称是自定义的，顺序和demo1(参数,参数)对应  
d. &lt;aop:before/&gt; arg-names="名称" 名称来源于expression=""中的args()  
注意：① args()有几个参数，arg-names里面必须有几个参数  
② arg-names="" 里面名称必须和通知方法参数名对应
```xml
<bean id="myadvice" class="com.bjsxt.advice.MyAdvice"></bean>
<aop:config>
    <aop:aspect ref="myadvice">
        <aop:pointcut id="mypoint" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1(String,int)) and args(name,age)"/>
        <aop:pointcut id="mypoint1" expression="execution(* com.bjsxt.interceptMethod.Demo.demo1(String)) and args(name1)"/>
        <aop:before method="mybefore" pointcut-ref="mypoint" arg-names="name,age"/>
        <aop:before method="mybefore1" pointcut-ref="mypoint1" arg-names="name1"/>
        <!--<aop:after method="myafter" pointcut-ref="mypoint"/>
        <aop:after-returning method="myaftering" pointcut-ref="mypoint"/>
        <aop:after-throwing method="mythrow" pointcut-ref="mypoint"/>
        <aop:around method="myaround" pointcut-ref="mypoint"/>-->
    </aop:aspect>
</aop:config>
<bean id="demo" class="com.bjsxt.interceptMethod.Demo"></bean>
```