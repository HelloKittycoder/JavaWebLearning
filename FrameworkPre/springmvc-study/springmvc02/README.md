### SpringMVC注解方式环境搭建
1. 导入jar包  
2. 在web.xml中配置前端控制器DispatcherServlet  
如果不配置&lt;init-param&gt;，默认配置文件要求为/WEB-INF/&lt;servlet-name&gt;-servlet.xml  
如下面这种情况不配置init-param，则要求配置文件在/WEB-INF/中，且名称为springmvc123-servlet.xml
```xml
<servlet>
    <servlet-name>springmvc123</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 修改配置文件路径和名称 -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <!-- 自启动 -->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springmvc123</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```
3. 在src下新建springmvc.xml  
引入xmlns:mvc命名空间
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 扫描注解 -->
    <context:component-scan base-package="com.bjsxt.controller"></context:component-scan>
    <!-- 注解驱动（相当于配了下面两个标签） -->
    <!-- HandlerMapping
     org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping -->
    <!-- HandlerAdapter
    org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter -->
    <mvc:annotation-driven></mvc:annotation-driven>
    <!-- 静态资源 -->
    <!-- mapping浏览器的url地址，location后台根据前端传来的url转换成实际要访问的地址
    mapping中的*表示该文件夹下的文件，**表示该文件夹下任意层级下的文件
     举例：springmvc是项目名
     http://localhost:8080/springmvc/js/jquery-1.11.0.js 访问的是项目根路径下的js/jquery-1.11.0.js
     http://localhost:8080/springmvc/abc/jquery-1.11.0.js 访问的是项目根路径下的WEB-INF/js/jquery-1.11.0.js
     -->
    <mvc:resources location="/js/" mapping="/js/**"></mvc:resources>
    <mvc:resources location="/css/" mapping="/css/**"></mvc:resources>
    <mvc:resources location="/WEB-INF/js/" mapping="/abc/**"></mvc:resources>
    <mvc:resources location="/images/" mapping="/images/**"></mvc:resources>
</beans>
```
4. 编写控制器类  
```java
@Controller
public class DemoController {
    // ModelAndView mav = new ModelAndView("/WEB-INF/page/main.jsp");

    @RequestMapping("demo")
    public String demo() {
        System.out.println("执行了springmvc控制器");
        return "/WEB-INF/page/main.jsp";
    }

    @RequestMapping("demo2")
    public String demo2() {
        System.out.println("demo2");
        return "main.jsp";
    }
}

```