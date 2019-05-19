<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>主页</title>
</head>
<body>
<form action="uploadFile" enctype="multipart/form-data" method="post">
    姓名：<input type="text" name="name"/><br/>
    文件：<input type="file" name="file"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
