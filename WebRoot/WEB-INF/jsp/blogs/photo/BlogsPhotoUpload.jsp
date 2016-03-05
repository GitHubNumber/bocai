<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional.dtd//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传照片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/photo.css" type="text/css"></link>
  </head>
  
  <body>
    <!-- 网页top -->
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="blogs_main">
		<!-- 网页heard -->
		<jsp:include page="../heard.jsp"></jsp:include>
		
		
		</div>
		<DIV style="width: 980px;height: 350px;margin: 0px auto;">
		<div style="width: 150px;background-color: #D8E9EC; height: 350px; float:left;"></div>
		<div class="UploadPhoto">
		
     <form action="<%=path %>/blogs/upload.action" method="post" enctype="multipart/form-data">
  	<input type="hidden" name="blogsphotogroup.id" value="${blogsphotogroup.id}" >
     <div class="center_box">
     <div style="background-color: #cccccc;width: 380px;height: 30px;text-align: left;"><h3>上传照片</h3></div>
 <ul>
  	<li>	<h4>文件描述:</h4><input type="text" name="blogsPhotoUpload.info" size="30" height="30px"></li>
  	<li>	<h4>文 &nbsp;&nbsp;件:</h4><input type="file" name="blogsPhotoUpload.upload" size="20"><br></li>
  	<li>	<input type="submit" value="  提   交  "></li>
  	</ul>
  	
  	</div>
  	</form>

  	</div>
  	<div style="width: 150px; float: left;background-color: #D8E9EC; height: 350px; "></div>
  	
  	</DIV>
  	<jsp:include page="../../common/end.jsp"></jsp:include>
  </body>
</html>