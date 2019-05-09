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
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>
<body>
${error}
<form action="login" method="post">
    用户名：<input type="text" name="username"/><br/>
    密码：<input type="password" name="password"/><br/>
    验证码：<input type="text" name="code" size="1"/><img id="validcodeImg" src="validcode" width="80" height="40"/><a id="newValidCode" href="">看不清</a><br/>
    <input type="submit" value="登录"/><input type="reset" value="重置"/>
</form>
<%--<img src="images/a.png"/>--%>
<%--<img src="demo"/>--%>
<script type="text/javascript">
    $(function () {
        $("#newValidCode").click(function () {
            // 浏览器带有缓存功能，不会多次请求相同数据
            $("#validcodeImg").attr("src", "validcode?a="+Math.random());
            return false;
        });
    });
</script>
</body>
</html>