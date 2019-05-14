### @ResponseBody
1. 在方法上只有@RequestMapping时，无论方法返回值时什么，都会认为需要跳转  
2. 在方法上添加@ResponseBody（恒不跳转）  
2.1 如果返回值满足key-value形式（对象或map）  
（1）就会把响应头设置为application/json;charset=utf-8  
（2）把转换后的内容以输出流的形式响应给客户端  
2.2 如果返回值不满足key-value，例如返回值为String  
（1）把响应头设置为text/html  
（2）把方法返回值以流的形式直接输出  
（3）如果返回值包含中文，出现中文乱码  
produces表示响应头中Content-Type的取值
```java
@RequestMapping(value = "demo3", produces = "text/html;charset=utf-8")
@ResponseBody
public String demo3() {
    People p = new People();
    p.setAge(11);
    p.setName("张三");
    System.out.println("执行了demo3");
    return "中文";
}
```
