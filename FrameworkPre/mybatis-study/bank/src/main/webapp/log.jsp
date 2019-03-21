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
            <th>序号</th>
            <th>付款账号</th>
            <th>收款账号</th>
            <th>金额</th>
        </tr>
        <c:forEach items="${pageInfo.list}" var="log">
            <tr>
                <td>${log.id}</td>
                <td>${log.accIn}</td>
                <td>${log.accOut}</td>
                <td>${log.money}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="showLogList?pageSize=${pageInfo.pageSize}&pageNum=${pageInfo.pageNum-1}" <c:if test="${pageInfo.pageNum<=1}">onclick="javascript:return false;"</c:if>>上一页</a>
    <a href="showLogList?pageSize=${pageInfo.pageSize}&pageNum=${pageInfo.pageNum+1}" <c:if test="${pageInfo.pageNum>=pageInfo.total}">onclick="javascript:return false;"</c:if>>下一页</a>
</body>
</html>