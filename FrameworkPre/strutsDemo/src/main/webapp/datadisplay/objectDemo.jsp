<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: shucheng
  Date: 2018/4/18
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>遍历对象的demo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/table.css"/>
</head>
<body>
<h3>获取Student对象的属性（方法一）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
        <th>学生id</th>
        <th>学生姓名</th>
        <th>学生生日</th>
        <th>学生年龄</th>
    </thead>
    <tbody>
        <td>${student.sid}</td>
        <td>${student.sname}</td>
        <td>${student.sbirthday}</td>
        <td>${student.sage}</td>
    </tbody>
</table>
<h3>获取Student对象的属性（方法二）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
    <th>学生id</th>
    <th>学生姓名</th>
    <th>学生生日</th>
    <th>学生年龄</th>
    </thead>
    <tbody>
    <td><s:property value="student.sid"/></td>
    <td><s:property value="student.sname"/></td>
    <td><s:property value="student.sbirthday"/></td>
    <td><s:property value="student.sage"/></td>
    </tbody>
</table>
</body>
</html>
