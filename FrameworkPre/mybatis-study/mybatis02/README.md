### 添加mybatis日志支持
1. 在mybatis全局配置文件中通过&lt;settings&gt;标签控制mybatis全局开关
（1）必须保证有log4j-xxx.jar
（2）在src下有log4j.properties
在mybatis.xml中开启log4j
```xml
   <settings>
      <setting name="logImpl" value="LOG4J"></setting>
   </settings>
```
2. log4j中可以输出制定内容的日志（控制某个局部内容的日志级别）
（1）命名级别（包级别）：&ltmapper&gt;namespace属性中除了最后一个类名
例如：namespace="com.bjsxt.mapper.FlowerMapper"，其中包级别为com.bjsxt.mapper,需要在log4j.properties中填写
log4j.logger.com.bjsxt.mapper=DEBUG
（2）类级别
namespace属性值相当于namespace类名
（3）方法级别
使用namespace属性值+标签id属性值
