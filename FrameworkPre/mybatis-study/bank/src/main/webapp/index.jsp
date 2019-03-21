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
<form action="transfer" method="post">
    转账账户：<input type="text" name="accOutAccNo"/><br/>
    密码：<input type="password" name="accOutPassword"/><br/>
    金额：<input type="text" name="accOutBalance"/><br/>
    收款账号：<input type="text" name="accInAccNo"/><br/>
    收款姓名：<input type="text" name="accInName"/><br/>
    <input type="submit" value="转账"/>
</form>
</body>
</html>