### 如何给Bean的属性赋值（注入）
1. 通过构造方法设置值  
2. 设置注入（通过set方法）  
2.1 如果属性是基本数据类型或String等简单类型  
```xml
<bean id="peo" class="com.bjsxt.pojo.People">
    <property name="id" value="222"></property>
    <property name="name" value="李四"></property>
</bean>
```
等效于
```xml
<bean id="peo" class="com.bjsxt.pojo.People">
    <property name="id">
        <value>222</value>
    </property>
    <property name="name">
        <value>李四</value>
    </property>
</bean>
```
2.2 如果是Set<?>
```xml
<property name="set">
    <set>
        <value>1</value>
        <value>2</value>
        <value>3</value>
        <value>4</value>
    </set>
</property>
```
2.3 如果是List<?>
```xml
<property name="list">
    <list>
        <value>1</value>
        <value>2</value>
        <value>3</value>
        <value>4</value>
    </list>
</property>
```
说明：如果list中就只有一个值
```xml
<property name="list" value="1">
</property>
```
2.4 如果属性是数组（如果数组中就一个值，可以直接通过value属性赋值）
```xml
<property name="strs">
    <array>
        <value>1</value>
        <value>2</value>
        <value>3</value>
        <value>4</value>
    </array>
</property>
```
2.5 如果属性是map
```xml
<property name="map">
    <map>
        <entry key="a" value="11"/>
        <entry key="b" value="22"/>
    </map>
</property>
```
2.6 如果属性是Properties类型
```xml
<property name="demoProps">
    <props>
        <prop key="aProp">aaa</prop>
        <prop key="bProp">bbb</prop>
    </props>
</property>
```