<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional.dtd//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的照片</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/addLog.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/log.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/photo.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/myphoto.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/blogs2.css" type="text/css"></link>
  </head>
  <body>
    <!-- 网页top -->
	<jsp:include page="../top.jsp"/>
		
		
	<div class="blogs_main">
		<!-- 网页heard -->
		<jsp:include page="../heard.jsp"></jsp:include>
		<div class="log2 log1 margintop">
                <h5>我的照片</h5>
         </div>
		<div class="p_all">
		<div class="p_left"><a href="<%=path%>/blogs/photo!selectMyPhoto.action?blogsphoto.id=${blogsphoto.id}&flag=-1&user.id=${user.id}"></a></div>
		<div class="p_center">
   <img alt="" src="<%= path%>/images/blogs/userphoto/${blogsphoto.photo }" >
		</div>
		<div class="p_right">
		<a href="<%=path%>/blogs/photo!selectMyPhoto.action?blogsphoto.id=${blogsphoto.id}&flag=1&user.id=${user.id}"></a>
		</div>
   <div class="p_info">
   <ul>
   <li>相片名称:${blogsphoto.photo }<br/></li>
   <li>相片介绍:${blogsphoto.photoInfo }<br/></li>
   <li>相片上传时间:${blogsphoto.date }<br/></li>
   </ul>
   <form action="<%=path %>/blogs/comment!addPhoto.action?blogsphoto.id=${blogsphoto.id}&user.id=${user.id}" method="post" class="myfrom">
   评论:<br>
   <textarea rows="5" cols="25" name="blogsComment.comment"></textarea><br>
   <input type="hidden" value="${blogsphoto.id}" name="blogsComment.table_id">
   <input type="submit" class="logpinglun" value="评论">
   </form>
   <div class="message">
   <img alt="" src="<%=path%>/images/blogs/pinglun.png"> 评论内容:
   <br/>
   	<s:iterator var="l" value="clist">
   	<div class="comentAll">
   		<div class="left"><img alt="" src="<%= path %>/images/blogs/log/touxiang.png" width="40px" height="40px"></div>
   		<div class="left">
   		<span class="userid">用户名:${l.user_id }</span>
   		<br/><br/>
   		<span class="comment">评论:${l.comment }</span>
   		</div>
   	</div>
   	</s:iterator>
   </div>
   </div>
   </div>
		</div>
  <jsp:include page="../../common/end.jsp"></jsp:include>
  </body>
</html>
