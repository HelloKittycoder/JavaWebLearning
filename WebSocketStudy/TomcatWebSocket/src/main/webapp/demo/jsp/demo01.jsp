<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #messageDiv {
            width: 400px;
            height: 250px;
            border: 1px solid;
        }
        input.disabled {
            background-color: #999;
        }
    </style>
</head>
<body>
<input type="hidden" id="ctx" value="${pageContext.request.contextPath}"/>
<h3>Demo01 websocket简单交互</h3>
<input id="connect" type="button" value="建立连接"/> <input id="disconnect" class="disabled" type="button" value="断开连接"/><br/>
<div id="messageDiv">
    <input id="sendMessage" class="disabled" type="button" value="发送消息"/><br/>
    日志：
    <textarea id="logArea" rows="13" cols="40" readonly style="resize: none"></textarea>
    <input id="clearLog" type="button" value="清除日志"/>
</div>
<!-- 前端库在线引用地址：https://www.bootcdn.cn/ -->
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/demo/script/demo01.js"></script>
</body>
</html>
