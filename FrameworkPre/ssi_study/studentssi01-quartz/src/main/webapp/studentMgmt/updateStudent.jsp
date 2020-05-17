<%--
  Created by IntelliJ IDEA.
  User: shucheng
  Date: 2018/4/25
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生修改</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/table.css"/>
</head>
<body>
<form id="studentForm" method="post" action="${pageContext.request.contextPath}/studentMgmt/updateStudent.action">
    <input type="hidden" name="student.id" value="${student.id}"/>
    <table cellpadding="0" cellspacing="0" class="result-table">
        <tr>
            <td>姓名</td>
            <td><input type="text" name="student.name" value="${student.name}"/></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="text" name="student.age" value="${student.age}"/></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input type="text" name="student.sex" value="${student.sex}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
