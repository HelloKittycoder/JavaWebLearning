### 视图解析器
1. SpringMVC会提供默认视图解析器  
2. 程序员自定义视图解析器  
```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```
3. 如果希望不执行自定义视图解析器，需要在方法返回值前面添加forward:或redirect:  
```java
@RequestMapping("demo")
public String demo() {
    System.out.println("执行了demo");
    return "forward:demo2";
}

@RequestMapping("demo2")
public String demo2() {
    System.out.println("执行了demo2");
    return "main";
}
```