<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link href="<%=path%>/js/uploadify/3.2.1/uploadify.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="<%=path%>/js/uploadify/3.2.1/jquery.uploadify.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#uploadify").uploadify({
                "swf" : "<%=path%>/js/uploadify/3.2.1/uploadify.swf",
                "uploader" : "<%=path%>/uploadify/uploadFile.action",
                "fileObjName": "uploadify",
                "folder" : "UploadFile",
                'auto': true,
                'multi': false,
                'method': 'get'
            });
        });
    </script>
</head>
<body>
<form action="">
    <input type="file" name="uploadify" id="uploadify"/>
    <a href="javascript:$('#uploadify').uploadify('cancel');">取消上传</a>
</form>


<%-- <s:form action="<%=path%>/uploadify/uploadFile.action" method="post" enctype="multipart/form-data">
<s:file name="uploadify" label="Select File" id="upload" ></s:file>
<s:submit value="Upload"></s:submit>
</s:form> --%>
</body>
</html>