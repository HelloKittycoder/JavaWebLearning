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
        <th>花卉编号</th>
        <th>花卉名称</th>
        <th>价格（元）</th>
        <th>原产地</th>
    </tr>
    <c:forEach items="${pageInfo.dataList}" var="flower">
        <tr>
            <td>${flower.id}</td>
            <td>${flower.name}</td>
            <td>${flower.price}</td>
            <td>${flower.production}</td>
        </tr>
    </c:forEach>
</table>
<%--pageNum===${pageInfo.pageNum}
pageCount===${pageInfo.pageCount}--%>
<%--<a href="showPage?pageNum=${pageInfo.pageNum - 1}&pageSize=2" onclick="<c:if test="${pageInfo.pageNum <= 1}">javascript:return false;</c:if>">上一页</a>
<a href="showPage?pageNum=${pageInfo.pageNum + 1}&pageSize=2" onclick="<c:if test="${pageInfo.pageNum >= pageInfo.pageCount}">javascript:return false;</c:if>">下一页</a>--%>
<a href="showPage?pageNum=${pageInfo.pageNum - 1}&pageSize=2" <c:if test="${pageInfo.pageNum <= 1}">onclick="javascript:return false;"</c:if>>上一页</a>
<a href="showPage?pageNum=${pageInfo.pageNum + 1}&pageSize=2" <c:if test="${pageInfo.pageNum >= pageInfo.pageCount}">onclick="javascript:return false;"</c:if>>下一页</a>
</body>
</html>