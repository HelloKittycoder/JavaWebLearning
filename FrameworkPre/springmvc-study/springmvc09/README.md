### 文件下载
1. 访问资源时响应头如果没有设置Content-Disposition，浏览器默认按照inline值进行处理  
inline能显示就显示，不能显示就下载  
2. 只需要修改响应头中Content-Disposition="attachment;filename=文件名"  
2.1 attachment 下载，以附件形式下载  
2.2 filename=值 就是下载时显示的下载文件名  
3. 实现步骤  
3.1 导入apache的jar包（commons-io）  
3.2 在jsp中添加超链接，设置要下载的文件  
```html
<a href="downloadFile?fileName=show.rar">下载</a>
```
3.3 编写控制器方法
```java
// 返回为void，表示不跳转
@RequestMapping("downloadFile")
public void downloadFile(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {

    // resp.setContentType();和写resp.setHeader("Content-Type",属性值)是等效的
    // 设置响应头（attachment 无论何时，都进行下载）
    resp.setHeader("Content-Disposition", "attachment;filename="+fileName);
    // 浏览器中的默认响应头，inline（能显示则显示，不能显示则下载）
    // resp.setHeader("Content-Disposition", "inline;filename="+fileName);

    // 获取某个资源的完整路径
    String path = req.getServletContext().getRealPath("files");
    File file = new File(path, fileName);
    // 将文件转换为二进制流
    byte[] bytes = FileUtils.readFileToByteArray(file);

    // 字符流
    // PrintWriter out = resp.getWriter();
    // 字节流
    ServletOutputStream os = resp.getOutputStream();
    os.write(bytes);
    os.flush();
    os.close();
}
```
