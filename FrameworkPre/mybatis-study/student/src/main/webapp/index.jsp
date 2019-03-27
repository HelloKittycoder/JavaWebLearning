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
    <input type="radio" value="2" name="pageSize"/>2
    <input type="radio" value="3" name="pageSize"/>3
    <input type="radio" value="4" name="pageSize"/>4<br/>
    学生姓名：<input type="text" name="sname"/>&nbsp;&nbsp;老师姓名：<input type="text" name="tname"/><br/>
    <button>查询</button><br/>
    <table border="1">
        <tr>
            <td>学生编号</td>
            <td>学生姓名</td>
            <td>年龄</td>
            <td>任课老师</td>
        </tr>
        <c:forEach items="${pageInfo.list}" var="stu">
            <tr>
                <td>${stu.id}</td>
                <td>${stu.name}</td>
                <td>${stu.age}</td>
                <td>${stu.teacher.name}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="" class="page_a">上一页</a><a href="" class="page_a">下一页</a>

    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript">
        $(function () {
            var pageSize = "${pageInfo.pageSize}";
            var pageNumber = "${pageInfo.pageNumber}";
            var tname = "${pageInfo.tname}";
            var sname = "${pageInfo.sname}";
            var total = "${pageInfo.total}";

            // i表示循环下标，n表示迭代变量 n=数组[i] n是dom对象
            // dom对象转换成jquery对象 $(dom对象)
            // 把jquery对象转换成dom对象 jquery对象[0] jquery对象.get(0)
            $.each($(":radio"), function (i, n) {
                if ($(n).val()==pageSize) {
                    $(n).attr("checked", "checked");
                }
            });

            // 对输入框设置值
            $(":text[name='tname']").val(tname);
            $(":text[name='sname']").val(sname);

            // 查询按钮点击事件
            $("button").click(function () {
                chageUrl(pageSize, 1);
            });

            // 单选按钮点击事件
            $(":radio").click(function () {
                chageUrl($(this).val(), 1);
            });

            // 点击上一页
            $(".page_a:eq(0)").click(function () {
                chageUrl(pageSize, pageNumber <= 1 ? pageNumber : parseInt(pageNumber) - 1);
                return false;
            });

            // 点击下一页
            $(".page_a:eq(1)").click(function () {
                chageUrl(pageSize, pageNumber >= total ? total : parseInt(pageNumber) + 1);
                return false;
            });
        })

        function chageUrl(pageSize, pageNumber) {
            location.href = "show?pageSize="+pageSize+"&pageNumber="+pageNumber
                +"&sname="+$(":text[name='sname']").val()+"&tname="+$(":text[name='tname']").val();
        }
    </script>
</body>
</html>