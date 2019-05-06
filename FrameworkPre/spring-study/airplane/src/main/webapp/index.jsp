<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<form action="showtake">
    起飞机场：
    <select name="takeid">
        <option value="0">请选择</option>
        <c:forEach items="${takeport}" var="t">
            <option value="${t.id}">${t.portName}</option>
        </c:forEach>
    </select>
    降落机场：
    <select name="landid">
        <option value="0">请选择</option>
        <c:forEach items="${landport}" var="t">
            <option value="${t.id}">${t.portName}</option>
        </c:forEach>
    </select>
    <input type="submit" value="查询"/>
</form>
<table border="1">
    <tr>
        <td>飞机编号</td>
        <td>起飞机场</td>
        <td>起飞城市</td>
        <td>降落机场</td>
        <td>降落城市</td>
        <td>航行时间</td>
        <td>票价（元）</td>
    </tr>
    <c:forEach items="${airplaneList}" var="a">
        <tr>
            <td>${a.airNo}</td>
            <td>${a.takePort.portName}</td>
            <td>${a.takePort.cityName}</td>
            <td>${a.landPort.portName}</td>
            <td>${a.landPort.cityName}</td>
            <td>
                <c:if test="${a.time/60>1}">
                    <fmt:formatNumber value="${a.time/60}" pattern="0"></fmt:formatNumber>小时
                </c:if>
                <c:if test="${a.time%60>0}">
                    ${a.time%60}分钟
                </c:if>
            </td>
            <td>${a.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>