### 文件上传
1. 基于apache的commons-fileupload.jar完成文件上传  
2. MultipartResolver作用：  
2.1 把客户端上传的文件流转换成MultipartFile封装类  
2.2 通过MultipartFile封装类获取到文件流  
3. 表单数据类型分类  
3.1 在&lt;form&gt;的enctype属性中控制表单类型  
3.2 默认值application/x-www-form-urlencoded（普通表单数据，传递少量文字信息）  
3.3 text/plain（大文字量时使用的类型。邮件、论文）  
3.4 multipart/form-data（表单中包含二进制文件内容）  
4. 实现步骤：  
4.1 导入springmv包和apache文件处理包（commons-fileupload.jar和commons-io.jar）  
4.2 编写JSP页面  
这里用的是post。因为get最多传2KB，post最多传2GB；另外，get传的是字符流，post传的是字节流
```html
<form action="uploadFile" enctype="multipart/form-data" method="post">
    姓名：<input type="text" name="name"/><br/>
    文件：<input type="file" name="file"/><br/>
    <input type="submit" value="提交"/>
</form>
```
4.3 配置springmvc.xml  
```xml
<!-- MultipartResolver解析器 -->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 设置最大不超过50bytes（字节） -->
    <property name="maxUploadSize" value="50"/>
</bean>
<!-- 异常解析器 -->
<bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <props>
            <prop key="org.springframework.web.multipart.MaxUploadSizeExceededException">/error.jsp</prop>
        </props>
    </property>
</bean>
```
4.4 编写控制器类
MultipartFile对象名必须和&lt;input type="file"/&gt;的name属性值相同
```java
// 返回为void，表示不跳转
@RequestMapping("uploadFile")
public String uploadFile(MultipartFile file, String name) throws IOException {
    // 用来测试在做文件上传时，除了接收文件流以外，能否再接收普通参数
    System.out.println("name:" + name);

    // 为避免上传相同文件名的文件出现被覆盖的情形，使用uuid对文件重新命名
    String fileName = file.getOriginalFilename();
    String suffix = fileName.substring(fileName.lastIndexOf(".")); // 如果需要上传特定类型的文件，可以用suffix再做判断
    String uuid = UUID.randomUUID().toString();

    // 将前端传来的二进制流存放到指定路径下
    FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:/"+uuid+suffix));
    return "/index.jsp";
}
```