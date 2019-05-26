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
        <tr>
            <th>学生id</th>
            <th>学生姓名</th>
            <th>学生生日</th>
            <th>学生年龄</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${student.sid}</td>
            <td>${student.sname}</td>
            <td>${student.sbirthday}</td>
            <td>${student.sage}</td>
        </tr>
    </tbody>
</table>
<h3>获取Student对象的属性（方法二）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
        <tr>
            <th>学生id</th>
            <th>学生姓名</th>
            <th>学生生日</th>
            <th>学生年龄</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><s:property value="student.sid"/></td>
            <td><s:property value="student.sname"/></td>
            <td><s:property value="student.sbirthday"/></td>
            <td><s:property value="student.sage"/></td>
        </tr>
    </tbody>
</table>

<!-- 参考链接：https://struts.apache.org/tag-developers/iterator-tag.html -->
<h3>简单遍历（不依赖其他pojo），例如从2到5</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
        <tr>
            <th>序号</th>
            <th>遍历的数字</th>
        </tr>
    </thead>
    <tbody>
        <s:iterator begin="2" end="5" status="status">
            <tr>
                <td>
                    ${status.count}
                    <%-- 或者写成 <s:property value="#status.count"/>--%>
                </td>
                <td>
                    ${top}
                </td>
            </tr>
        </s:iterator>
    </tbody>
</table>
</body>
</html>
