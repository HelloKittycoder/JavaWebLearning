### 复杂参数
1. 请求参数中包含多个同名参数的获取方式  
1.1 复选框传递的参数就是多个同名参数  
```java
@RequestMapping("demo")
public String demo(String name, int age, @RequestParam("hobbies") List<String> abc) {
    System.out.println("执行了demo4==" + name + "  " + age + "  " + abc);
    return "main.jsp";
}
```
2. 请求参数中对象.属性格式  
2.1 jsp中代码  
```html
<input type="text" name="peo.name"/>
<input type="text" name="peo.age"/>
```
5.2 新建一个类  
对象名和参数中点前面名称对应
```java
public class Demo {
    private People peo;
}
```
5.3 控制器  
```java
@RequestMapping("demo2")
public String demo2(Demo demo) {
    System.out.println("执行了demo2==" + demo);
    return "main.jsp";
}
```
6. 在请求参数中传递集合对象类型参数  
6.1 jsp中代码  
```html
<input type="text" name="peoList[0].name"/>
<input type="text" name="peoList[0].age"/>
<input type="text" name="peoList[1].name"/>
<input type="text" name="peoList[1].age"/>
```
6.2 新建类  
```java
public class Demo {
    private List<People> peoList;
}
```
6.3 控制器
```java
@RequestMapping("demo3")
public String demo3(Demo demo) {
    System.out.println("执行了demo3==" + demo);
    return "main.jsp";
}
```

### restful传值方式
1. 可以简化jsp中参数编写格式  
2. 在jsp中设定特定的格式  
```html
<a href="demo5/123/abc">跳转2</a>
```
3. 在控制器中  
3.1 在@RequestMapping中一定要和请求格式对应  
3.2 {名称}中名称自定义  
3.3 @PathVariable 获取@RequestMapping中内容，默认按照方法参数名称去寻找
```java
@RequestMapping("demo5/{age1}/{name}")
public String demo5(@PathVariable String name, @PathVariable("age1") int age) {
    System.out.println("执行了demo4==" + name + "  " + age);
    return "/main.jsp";
}
```