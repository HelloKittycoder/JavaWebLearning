<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
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
    <title>主页</title>
</head>
<body>
request:${requestScope.req}<br/>
session:${sessionScope.session}<br/>
sessionParam:${sessionScope.sessionParam}<br/>
application:${applicationScope.application}
<hr/>
map:${requestScope.map}<br/>
mapPeoName:${requestScope.mapPeo.name}<br/>
mapPeoAge:${requestScope.mapPeo.age}
<hr/>
model:${requestScope.model}<br/>
modelPeoName:${requestScope.modelPeo.name}<br/>
modelPeoAge:${requestScope.modelPeo.age}
<hr/>
mav:${requestScope.mav}<br/>
mavPeoName:${requestScope.mavPeo.name}<br/>
mavPeoAge:${requestScope.mavPeo.age}
</body>
</html>
