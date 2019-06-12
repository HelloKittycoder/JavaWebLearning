<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-6-12
  Time: 下午 21:05
  To change this template use File | Settings | File Templates.
  https://blog.csdn.net/u010999809/article/details/91630313
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件下载demo</title>
</head>
<body>
    <input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
    <h3>无跳转文件下载</h3>
    <input type="button" value="下载" id="downloadBtn" onclick="downloadFile('测试下载.txt')"/>
    <iframe style="display:none;" name="hiddenIframe" id="hiddenIframe"></iframe>
    <script src="http://libs.baidu.com/jquery/1.8.3/jquery.js"></script>
    <script type="text/javascript">
        function downloadFile(resourceName) {
            var url = $("#path").val() + "/datadisplay/testDownload.action?resourceName=" + resourceName;
            downloadButtonClicked(url);
        }
        function downloadButtonClicked(url) {
            /* 方法一：在firefox和ie下没有效果
            参考链接：https://stackoverflow.com/questions/1066452/easiest-way-to-open-a-download-window-without-navigating-away-from-the-page
            var elem = document.createElement("a");
            elem.href = url;
            elem.target = "hiddenIframe";
            elem.click();*/
            // 方法二：这里需要加上encodeURI，因为ie不会对中文自动转码
            $("#hiddenIframe").attr("src", encodeURI(url));
        }
    </script>
</body>
</html>
