<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: shucheng
  Date: 2020/8/9
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ognl的使用</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/table.css"/>
    <style type="text/css">
        #dataBody tr:nth-of-type(even) {
            background-color: #F2F2F2;
        }
    </style>
</head>
<body>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
        <tr>
            <th>表达式</th>
            <th>值</th>
        </tr>
    </thead>
    <tbody id="dataBody">
        <!-- #号的使用
        参考链接：https://www.cnblogs.com/sharpest/p/6030265.html
         -->
        <tr>
            <td>#session.mystudent.sage</td>
            <td><s:property value="#session.mystudent.sage"/></td>
        </tr>
        <tr>
            <td>studentList.{?#this.sid==1}</td>
            <td><s:property value="studentList.{?#this.sid==1}"/></td>
        </tr>
        <tr>
            <td>studentList[1]</td>
            <td><s:property value="studentList[1]"/></td>
        </tr>
        <tr>
            <td>mapList[0]</td>
            <td>
                <%-- 说明：或者写成 mapList[0]['startNodeName'] --%>
                <s:property value="mapList[0].startNodeName"/>
            </td>
        </tr>
        <!-- 调用普通方法
         参考链接：https://www.jb51.net/article/124732.htm
         -->
        <tr>
            <td>student.getSbirthday()</td>
            <td><s:property value="student.getSbirthday()"/></td>
        </tr>
        <!-- 调用后台的静态方法 -->
        <tr>
            <td>System.getProperty('file.encoding')</td>
            <td><s:property value="@java.lang.System@getProperty('file.encoding')"/></td>
        </tr>
        <tr>
            <td>Math.max(2,3)</td>
            <td>
                <%-- 说明：下面的方法等同于@java.lang.Math@max(2,3) --%>
                <s:property value="@@max(2,3)"/>
            </td>
        </tr>
    </tbody>
</table>
</body>
</html>
