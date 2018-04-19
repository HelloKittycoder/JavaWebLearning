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
    <link href="<%=path%>/js/uploadify/2.1.0/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="<%=path%>/js/uploadify/2.1.0/swfobject.js"></script>
    <script type="text/javascript" src="<%=path%>/js/uploadify/2.1.0/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#uploadify").uploadify({
                "uploader" : "<%=path%>/js/uploadify/2.1.0/uploadify.swf",
                "script" : "<%=path%>/uploadify/uploadFile.action",
                "cancelImg" : "<%=path%>/js/uploadify/2.1.0/cancel.png",
                "fileDataName": "uploadify",
                "folder" : "UploadFile",
                'auto': true,
                'multi': false,
                'method': 'get',
                'onSelect': function(e, queueId, fileObj) {
                    if(fileObj.size == 0){
                        alert("不能上传文件大小为0KB的文件,请删除选择!");
                        $("#uploadify").uploadifySettings('auto', false);
                    }else{
                        if(fileObj.type.toUpperCase()=='.EXE') {
                            alert("不能上传EXE类型的文件,请删除选择!");
                            $("#uploadify").uploadifySettings('auto',false);
                        }else{
                            $("#uploadify").uploadifySettings('auto',true);
                        }
                    }
                },
                'onComplete': function (event, queueID, fileObj, response, data) {
                    if(response==""){
                        $.prompt("建立项目文件夹发生错误，可能文件夹名称有特殊字符");
                    }else{
                        try{
                            var formId=$("#uploadify").closest("form").attr("id");
                            console.info(fileObj.name);
                            console.info(fileObj.name);
                            console.info(response);
                            /* $("#fileLastNameDiv").html('<span id='+fileObj.name+'><img src="../../../source/images/atchm.gif"/>'+fileObj.name+'<a class="Cmp_Ctrl" onclick="delUploadFile(&quot;'+fileObj.name+'&quot;);">删除</a><br></span>');
                             $("#"+formId+" *[id*='document.documentName']").val(fileObj.name);
                             $("#"+formId+" *[id='document.filePhysicsName']").val(fileObj.name);
                             $("#"+formId+" *[id='document.filePhysicsPath']").val(response); */
                        }catch(e){
                            promptMessage(e);
                        }
                    }
                }
            });
        });
    </script>
</head>
<body>
<form action="">
    <input type="file" name="uploadify" id="uploadify" />
    <a href="javascript:$('#uploadify').uploadifyClearQueue()" style="text-decoration:none">取消上传</a>
</form>
</body>
</html>