<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Insert title here</title>
</head>
<body>
<form action="register" method="post" enctype="multipart/form-data">
    用户名：<input id="username" type="text" name="username"/><span></span><br/>
    密码：<input id="password" type="password" name="password"/><span></span><br/>
    确认密码：<input id="repassword" type="password" name="repassword"/><span></span><br/>
    头像：<input id="imageFile" type="file" name="file"/><br/>
    <input id="submitBtn" type="submit" value="注册"/>
</form>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/register.js"></script>
</body>
</html>