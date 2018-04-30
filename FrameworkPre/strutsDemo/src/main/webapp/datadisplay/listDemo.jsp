<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: shucheng
  Date: 2018/4/18
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>遍历list的demo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/table.css"/>
</head>
<body>
<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
<h3>获取List的每个对象（method1）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
        <th>学生id</th>
        <th>学生姓名</th>
        <th>学生生日</th>
        <th>学生年龄</th>
    </thead>
    <tbody>
        <s:iterator value="studentList" var="student" status="status">
            <tr <s:if test="#status.count%2==0">bgcolor="#F2F2F2" </s:if>>
                <td>${student.sid}</td>
                <td>${student.sname}</td>
                <td>${student.sbirthday}</td>
                <td>${student.sage}</td>
            </tr>
        </s:iterator>
    </tbody>
</table>

<h3>获取List的每个对象（method2）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
    <th>学生id</th>
    <th>学生姓名</th>
    <th>学生生日</th>
    <th>学生年龄</th>
    </thead>
    <tbody>
    <s:iterator value="studentList" status="status">
        <tr <s:if test="#status.count%2==0">bgcolor="#F2F2F2" </s:if>>
            <td>${studentList[status.index].sid}</td>
            <td>${studentList[status.index].sname}</td>
            <td>${studentList[status.index].sbirthday}</td>
            <td>${studentList[status.index].sage}</td>
        </tr>
    </s:iterator>
    </tbody>
</table>

<h3>获取下拉列表项（method1）</h3>
<s:select list="provinceList" listKey="key" listValue="value" emptyOption="true"></s:select>

<h3>获取下拉列表项（method2）</h3>
<select id="provinceList"></select>

<script src="http://libs.baidu.com/jquery/1.8.3/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            url:path+"/datadisplay/testList.action",
            type:"post",
            data:{dataType: "json"},
            success:function (responseText) {
                var provinceList = responseText.provinceList;
                var options = "<option value></option>";
                $.each(provinceList, function (k, v) {
                    options += "<option value='" + v.key + "'>" + v.value + "</option>";
                });
                $("#provinceList").append(options);
            }
        });
    });
</script>
</body>
</html>
