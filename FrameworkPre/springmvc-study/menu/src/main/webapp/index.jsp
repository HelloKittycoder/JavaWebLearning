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
<input type="hidden" id="basePath" value="<%=basePath%>"/>
<button id="loadMenu">加载菜单</button>
<hr/>
<div id="content">
    <%--<ul>
        <li>菜单A
            <ul>
                <li>菜单A1</li>
                <li>菜单A2</li>
            </ul>
        </li>
        <li>菜单B
            <ul>
                <li>菜单B1</li>
                <li>菜单B2</li>
            </ul>
        </li>
    </ul>--%>
</div>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
    $(function() {
        App.loadMenuContent();
    });
</script>
</body>
</html>