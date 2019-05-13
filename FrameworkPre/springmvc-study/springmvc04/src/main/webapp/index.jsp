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
<form action="page" method="post">
     传参给对象
    <input type="text" name="name"/>
    <input type="text" name="age"/>
    <%-- 传参给list
    <input type="checkbox" name="hobbies" value="学习"/>
    <input type="checkbox" name="hobbies" value="写代码"/>
    <input type="checkbox" name="hobbies" value="看视频"/>
    <input type="checkbox" name="hobbies" value="看笔记"/>--%>
    <%-- 对象中嵌套对象
    <input type="text" name="peo.name"/>
    <input type="text" name="peo.age"/>--%>
    <!-- 对象中嵌套list -->
    <%--<input type="text" name="peoList[0].name"/>
    <input type="text" name="peoList[0].age"/>
    <input type="text" name="peoList[1].name"/>
    <input type="text" name="peoList[1].age"/>--%>
    <a href="demo4?name=abc&age=123">跳转1</a>
    <a href="demo5/123/abc">跳转2</a>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
