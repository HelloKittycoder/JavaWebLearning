### 动态代理
1. 为了解决静态代理需要频繁编写代理功能的问题  
2. 分类  
2.1 JDK提供的  
2.2 cglib动态代理

### JDK动态代理
1. 和cglib动态代理对比  
1.1 优点：jdk自带，不需要额外导入jar  
1.2 缺点：  
（1）真实对象必须实现接口  
（2）利用反射机制，效率不高  
2. 使用JDK动态代理时可能出现java.lang.ClassCastException异常
出现原因：希望把接口对象转换为具体真实对象

## cglib动态代理
1. cglib优点：  
1.1 基于字节码，生成真实对象的子类  
运行效率高于JDK动态代理  
1.2 不需要实现接口  
2. cglib缺点：非JDK功能，需要额外导入jar
3. 使用spring-aop时，只要出现Proxy和真实对象转换异常，需要添加
```xml
<aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>
```
3.1 设置为true，使用cglib  
3.2 设置为false，使用jdk（默认值）