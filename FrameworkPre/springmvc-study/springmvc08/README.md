### JSP九大内置对象和四大作用域
1. 九大内置对象  
######
| 名称        | 类型   |  含义  |  获取方式  |
| --------   | :-----:  | :----:  | :----:  |
| request      | HttpServletRequest   |   封装所有请求信息     |   方法参数     |
| response        |   HttpServletResponse   |   封装所有响应信息   |   方法参数   |
| session        |    HttpSession    |  封装所有会话信息  |  req.getSession()  |
| application        |    ServletContext    |  所有信息  |  getServletContext();<br/>req.getServletContext();  |
| out        |    PrintWriter    |  输出对象  |  resp.getWriter();  |
| exception        |    Exception    |  异常对象  |  resp.getWriter();  |
| page        |    Object    |  当前页面对象  |  resp.getWriter();  |
| pageContext        |    PageContext    |  获取其他对象  |  resp.getWriter();  |
| config        |    ServletConfig    |  配置信息  |  resp.getWriter();  |

2. 四大作用域  
2.1 page  
在当前页面不会被重新实例化  
2.2 request  
在一次请求中是同一个对象，下次请求会重新实例化一个request对象  
2.3 session  
（1）一次会话  
（2）只要客户端Cookie传递的Jsessionid不变，Session不会重新实例化（不超过默认时间时）  
（3）实际有效时间  
a. 浏览器关闭。Cookie失效  
b. 默认时间。在时间范围内无任何交互。tomcat中的web.xml中配置  
```xml
<session>
    <session-timeout>30</session-timeout>
</session>
```
2.4 application  
只有在tomcat启动项目时才实例化，关闭tomcat时销毁application  

### SpringMVC作用域传值的几种方式
1. 使用原生Servlet  
在HandlerMethod参数中添加作用域对象
```java
@RequestMapping("demo1")
public String demo1(HttpServletRequest abc, HttpSession sessionParam) {
    // request作用域
    abc.setAttribute("req", "req的值");

    // session作用域
    HttpSession session = abc.getSession();
    session.setAttribute("session", "session的值");
    sessionParam.setAttribute("sessionParam", "sessionParam的值");

    // application作用域
    ServletContext application = abc.getServletContext();
    application.setAttribute("application", "application的值");

    // 说明：不能通过直接在参数上添加 ServletContext app 来进行注入，spring出于安全考虑
    // 只能通过req.getServletContext()来获取，然后向里面设置值
    // app.setAttribute("app", "app的值");
    return "/index.jsp";
}
```
2. 如果想快速取到作用域的内容，（在4.3.1的版本里面有SessionAttribute，RequestAttribute注解）  
本次用的版本是4.1.6，所以不对这个作具体介绍
3. 使用Map集合  
3.1 把map中的内容放在request作用域中  
3.2 spring会对map集合通过BindingAwareModelMap进行实例化  
```java
@RequestMapping("demo2")
public String demo2(Map<String, Object> map) {
    System.out.println(map.getClass());
    map.put("map", "map的值");
    return "/index.jsp";
}
```
4. 使用SpringMVC中Model接口  
4.1 把内容最终放入request作用域中
```java
@RequestMapping("demo3")
public String demo3(Model model) {
    model.addAttribute("model", "model的值");
    return "/index.jsp";
}
```
5. 使用SpringMVC中ModelAndView类  
```java
@RequestMapping("demo4")
public ModelAndView demo4() {
    // 参数是要跳转的视图
    ModelAndView mav = new ModelAndView("/index.jsp");
    mav.addObject("mav", "mav的值");
    return mav;
}
```