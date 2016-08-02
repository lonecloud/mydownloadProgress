<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form action="uploadAction.action" method="post"
		enctype="multipart/form-data">
		<input type="file" name="upload" class="upload" /><span></span><br>
		<input type="file" name="upload" class="upload" /><span></span><br>
		<input type="file" name="upload" class="upload" /><span></span><br>
		<%-- 	<s:submit value="点击" onclick="formSubmit()"></s:submit> --%>
		<input type="button" onClick="myAjax()" value="异步上传">
		<!-- <button>点击</button> -->
		<span></span>
	</form>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
	var uploadProcessTimer = null;
	function myAjax() {
		uploadProcessTimer = window.setInterval("getFileUploadProcess()", 10);//每隔100毫秒执行callback  
		var inputFiles = document.querySelectorAll(".upload");//每个input框
		var files = [];
		for (var i = 0; i < inputFiles.length; i++) {
			files.push(inputFiles[i].files[0]);//将每个files装进去

		}
		console.log(files);

		var fd = new FormData();
		for (var i = 0; i < files.length; i++) {
			//一个文件 对应一个FormData对象 实现ajax 多文件异步 带进度条同时上传

			var file = files[i];
			if (file == null) {
				continue;
			}
			fd.append("upload", file);//input框name属性，file->file对象
		}
		ajaxUpload(fd);
	}
	function ajaxUpload(fd) {
		var url = "${pageContext.request.contextPath}/uploadAction.action";

		var xhr = new XMLHttpRequest();//获取对象
		xhr.open('POST', url, true);//
		xhr.send(fd);
		/*     xhr.onload = function (e)//回调函数
		 {
		 if (this.status == 200) {
		
		 }
		 } */
	};
	function getFileUploadProcess() {
		$.ajax({
			type : "GET",
			url : "fileProgressAction.action",
			dataType : "json",
			cache : false,
			success : function(jsonObject) {
				var isComplete = true;
				for (var i = 0; i < jsonObject.length; i++) {
					if (jsonObject[i].precent != "100%") {
						isComplete = false;
						break;
					}

				}
				var spans = document.querySelectorAll("span");
				for (var i = 0; i < jsonObject.length; i++) {
					spans[i].innerHTML = "已上传：" + jsonObject[i].precent+"速度"+jsonObject[i].speed;
				}
				if (isComplete) {
					window.clearInterval(uploadProcessTimer);
					$.ajax({
						type : "GET",
						url : "deleteSessionAction.action",
						cache : false,
						success:function(){
							alert("上传成功");
						}
					});

				}
			}
		});
	}
</script>
</html>
