<%--
  Created by IntelliJ IDEA.
  User: shucheng
  Date: 2018/4/25
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/table.css"/>
</head>
<body>
<table cellpadding="0" cellspacing="0" class="result-table">
    <a href="${pageContext.request.contextPath}/studentMgmt/insertStudent.jsp">新增</a>
    <thead>
        <th>ID</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>注册时间</th>
        <th>操作</th>
    </thead>
    <tbody>
        <c:forEach items="${studentList}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.age}</td>
                <td>${s.sex}</td>
                <td>${s.regtime}</td>
                <td><a href="${pageContext.request.contextPath}/studentMgmt/findStudentById.action?student.id=${s.id}">修改</a>
                    <a href="${pageContext.request.contextPath}/studentMgmt/deleteStudent.action?student.id=${s.id}">删除</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
