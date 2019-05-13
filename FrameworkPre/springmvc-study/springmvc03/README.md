### 字符编码过滤器
```xml
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
### 传参
1. 把内容写到方法（HandlerMethod）参数中，SpringMVC只要有这个内容，  
就会把内容注入进去  
2. 基本数据类型参数  
2.1 默认保证参数名称和请求中传递的参数名相同  
```java
@RequestMapping("demo")
public String demo(String name, int age) {
    System.out.println("执行了demo==" + name + "  " + age);
    return "main.jsp";
}
```
2.2 如果请求参数名和方法参数名不对应，可以使用@RequestParam()重新对应上  
```java
@RequestMapping("page")
public String page(@RequestParam("name1") String name, @RequestParam("age1") int age) {
    System.out.println("执行了page==" + name + "  " + age);
    return "main.jsp";
}
```
2.3 如果方法参数是基本数据类型（不是封装类），可以通过@RequestParam设置默认值  
防止没有参数时500  
```java
@RequestMapping("page2")
public String page2(@RequestParam(defaultValue = "2") int pageSize, @RequestParam(defaultValue = "1") int pageNumber) {
    System.out.println("执行了page2==" + pageSize + "  " + pageNumber);
    return "main.jsp";
}
```
2.4 如果强制要求必须有某个参数  
```java
@RequestMapping("page3")
public String page3(@RequestParam() String name, int age) {
    System.out.println("执行了page3==" + name + "  " + age);
    return "main.jsp";
}
```
3. HandlerMethod中参数是对象类型  
3.1 请求参数名和对象中属性名对应（get/set方法）
```java
@RequestMapping("demo2")
public String demo2(People peo) {
    System.out.println("执行了demo2==" + peo);
    return "main.jsp";
}
```