### DI
1. 中文名称：依赖注入  
2. 英文名称：Dependency Injection  
3. DI是什么？  
3.1 DI和IoC是一样的  
3.2 当一个类（A）中需要依赖另一个类（B）对象时，把B赋值给A的过程就叫做依赖注入
4. 代码体现
```xml
<bean id="peo" class="com.bjsxt.pojo.People">
    <property name="desk" ref="desk"/>
</bean>

<bean id="desk" class="com.bjsxt.pojo.Desk">
    <property name="id" value="101"/>
    <property name="price" value="100.11"/>
</bean>
```