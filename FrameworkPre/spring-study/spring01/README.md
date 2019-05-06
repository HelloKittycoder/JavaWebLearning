### SpringIoC  
1. 中文名称：控制反转  
2. 英文名称：Inversion of Control  
3. IoC是什么？  
1）IoC完成的事情：原先由程序员主动通过new实例化对象的事情，转交给Spring负责  
2）控制指的是：控制类的对象  
3）反转指的是：转交给Spring负责  
4）IoC最大的作用：解耦  
程序员不需要管理对象，解除了对象管理和程序员之间的耦合

### Spring环境搭建
1. 导入jar  
2. 在src下新建applicationContext.xml  
1）文件名称和路径自定义  
2）applicationContext.xml配置的信息最终存储到了ApplicationContext容器中  
3）spring配置文件是基于schema的  
a.schema文件扩展名.xsd  
b.把schema理解成DTD的升级版（因为比DTD具备更好的扩展性）  
c.每次引入一个xsd文件是一个namespace  
4）配置文件中只需要引入基本schema  
a.通过<bean/>创建对象  
b.默认配置文件被加载时创建对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- id表示获取到对象标识
     class表示创建哪个类的对象 -->
    <bean id="peo" class="com.bjsxt.pojo.People"/>
</beans>
```

3. 编写测试方法  
3.1 getBean("<bean>标签id值",返回值类型)；如果没有第二个参数，默认是Object  
3.2 getBeanDefinitionNames()，Spring容器中目前管理的所有的对象
```java
public class ObjectGetTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    // 获取容器中的People对象
    @Test
    public void getPeopleClass() {
        People p = ac.getBean("peo", People.class);
        System.out.println(p);
        /*People p = (People) ac.getBean("peo");
        System.out.println(p);*/
    }

    // 获取容器中所有被管理的bean的名称
    @Test
    public void getBeanNames() {
        String[] names = ac.getBeanDefinitionNames();
        for (String str : names) {
            System.out.println(str);
        }
    }
}
```
