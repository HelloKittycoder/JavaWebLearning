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
<table border="1">
    <tr>
        <td>资料名称</td>
        <td>下载次数</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="file">
        <tr>
            <td>${file.name}</td>
            <td>${file.count}</td>
            <td><a href="downloadFile?id=${file.id}&name=${file.name}">下载</a></td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
    $("a").click(function () {
        // parent() 父标签
        // prev() 前一个兄弟标签
        // jquery中规范，对象名以$开头
        var $td = $(this).parent().prev();
        // html()返回字符串
        $td.html(parseInt($td.html())+1);
    });
</script>
</body>
</html>