<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form action="servlet/UploadFileServlet" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file"> <br>
		<input type="file" name="file"> <br>
		<input type="file" name="file"> <br>
		<input type="text"><br>
		<input type="text"><br>
		<input type="text"><br>
		
	<!-- 	<input type="file" name="file"> <input type="submit"value="上传文件" ><br>
		<input type="file" name="file"> <input type="submit"value="上传文件" > -->
		<input type="submit"value="上传文件" >
	</form>
	上传进度：
	<label id="fileUploadProcess"></label>
	<iframe name="iframeUpload" src="" width="350" height="35"
		frameborder=0 SCROLLING="no" style="display:NONE"> </iframe>
</body>

<script type="text/javascript">

/* 	//定时器对象  
	var uploadProcessTimer = null;

	$(function() {
		$(':button').click(function(){
    var formData = new FormData($('form')[0]);
    $.ajax({
        url: 'http://localhost:8080/downloadProgress/servlet/UploadFileServlet',  //server script to process data
        type: 'POST',
        xhr: function() {  // custom xhr
            myXhr = $.ajaxSettings.xhr();
            if(myXhr.file){ // check if upload property exists
                myXhr.file.addEventListener('progress',progressHandlingFunction, false); // for handling the progress of the upload
            }
            return myXhr;
        },
        //Ajax事件
//        beforeSend: beforeSendHandler,
//        success: completeHandler,
//        error: errorHandler,
        // Form数据
        data: formData,
        //Options to tell JQuery not to process data or worry about content-type
        cache: false,
        contentType: false,
        processData: false
    });
}); */
/* 	//绑定定时器开始操作到提交按钮  
		$('input[type=button]').click(function() {
			//启动上传进度查询定时器  
			uploadProcessTimer = window.setInterval(getFileUploadProcess, 20);
		});
	}); */
/* 	//获取文件上传进度  
	function getFileUploadProcess() {
		$.get('http://localhost:8080/downloadProgress/servlet/GetProgressServlet', function(data) {
			$('#fileUploadProcess').html(data);
		});
	} */
/* 	    //上传完成后，由iframe返回脚本自动调用  
    function fileUploadCallBack(res) {  
        //清除定时器  
        if(uploadProcessTimer) {  
            window.clearInterval(uploadProcessTimer);  
        }  
        var message = res['message'];  
        var code = res['code'];  
        if(code != 200) {  
            $('#fileUploadProcess').html('0%');  
        }  
        alert(message);  
    }   */ 
</script>
</html>
