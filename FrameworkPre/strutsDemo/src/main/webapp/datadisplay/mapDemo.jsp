<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: shucheng
  Date: 2018/4/16
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>遍历Map数据的demo</title>
    <style type="text/css">
        table thead th {
            border:1px solid black;
        }
        table tbody td {
            border: 1px solid black;
        }
        .result-table {
            border:1px solid black;
            text-align: center;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h3>遍历stringHashMap（无序的HashMap）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
        <th>map的key</th>
        <th>map的value</th>
        <th>遍历的索引值</th>
        <th>遍历的所在行</th>
        <th>所在行数的奇偶性</th>
    </thead>
    <s:iterator value="pageResult.stringHashMap" var="stringMap" status="status">
        <tr <s:if test="#status.count%2==0">bgcolor="#F2F2F2" </s:if>>
            <td><s:property value="key"/></td>
            <td><s:property value="value"/></td>
            <td><s:property value="#status.index"/></td>
            <td><s:property value="#status.count"/></td>
            <td><s:if test="#status.count%2==0">偶数</s:if><s:else>奇数</s:else></td>
        </tr>
    </s:iterator>
</table>
<h3>遍历stringLinkedHashMap（有序的LinkedHashMap）</h3>
<table cellpadding="0" cellspacing="0" class="result-table">
    <thead>
    <th>map的key</th>
    <th>map的value</th>
    <th>遍历的索引值</th>
    <th>遍历的所在行</th>
    <th>所在行数的奇偶性</th>
    </thead>
    <s:iterator value="pageResult.stringLinkedHashMap" var="stringMap" status="status">
        <tr <s:if test="%{#status.count%2==0}">bgcolor="#F2F2F2" </s:if>>
            <td>${key}</td>
            <td>${value}</td>
            <td>${status.index}</td>
            <td>${status.count}</td>
            <td><s:if test="%{#status.count%2==0}">偶数</s:if><s:else>奇数</s:else></td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
