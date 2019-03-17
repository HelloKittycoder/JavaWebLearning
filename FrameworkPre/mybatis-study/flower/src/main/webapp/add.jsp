<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript">
        //页面加载完成后执行
        //相当于: window.onload=function(){}   $(document).ready(function(){});
    </script>
</head>
<body>
<!--  post:
		字节流
		2GB
		更安全
		相对效率低
	get:
		字符流
		2KB
 -->
<form action="insert" method="post">
    <table border="1" align="center">
        <tr>
            <td colspan="2" style="text-align: center;">花卉信息</td>
        </tr>
        <tr>
            <td>花卉名称：</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>花卉价格：</td>
            <td><input type="text" name="price"/></td>
        </tr>
        <tr>
            <td>原产地：</td>
            <td><input type="text" name="production"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="提交"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>