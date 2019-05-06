### Spring创建对象的三种方式
1. 通过构造方法创建  
1.1 无参构造创建：默认情况  
1.2 有参构造创建：需要明确配置  
（1） 需要在类中提供有参构造方法  
（2） 在applicationContext.xml中设置调用哪个构造方法创建对象  
如果设定的条件匹配多个构造方法，则将会执行最后一个匹配的构造方法  
index：参数的索引（从0开始）  
name：参数名  
type：类型（区分开关键字和封装类，如：int和Integer）
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- id表示获取到对象标识
     class表示创建哪个类的对象 -->
    <bean id="peo" class="com.bjsxt.pojo.People">
        <!-- ref引用另一个bean   value基本数据类型或String等 -->
        <constructor-arg index="0" name="id" type="int" value="123"></constructor-arg>
        <constructor-arg index="1" name="name" type="java.lang.String" value="张三"></constructor-arg>
    </bean>
</beans>
```
2. 实例工厂  
2.1 工厂设计模式：帮助创建类对象。一个工厂可以生产多个对象  
2.2 实例工厂：需要先创建工厂，才能生产对象  
2.3 实现步骤：  
1）必须要有一个实例工厂
```java
public class PeopleFactory {

    public People newInstance() {
        return new People(1, "实例工厂测试");
    }
}
```
2）在applicationContext.xml中配置工厂对象和需要创建的对象  
```xml
<bean id="factory" class="com.bjsxt.pojo.PeopleFactory"></bean>
<bean id="peo1" factory-bean="factory" factory-method="newInstance"></bean>
```
3. 静态工厂  
3.1 不需要创建工厂，可以快速创建对象
3.2 实现步骤：
1）编写一个静态工厂（在方法上添加static）
```java
public class PeopleFactory {

    public static People newInstanceStatic() {
        return new People(1, "实例工厂测试");
    }
}
```
2）在applicationContext.xml中  
```xml
<bean id="peo2" class="com.bjsxt.pojo.PeopleFactory" factory-method="newInstanceStatic"></bean>
````