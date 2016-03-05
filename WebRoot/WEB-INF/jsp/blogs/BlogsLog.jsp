<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional.dtd//EN">
<html>
<head>
<title>我的博客</title>
<link rel="stylesheet" href="<%=path%>/css/blogs/log.css"
	rel="stylesheet" type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/css/blogs/photo.css"
	rel="stylesheet" type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/css/blogs/blogs2.css"
	rel="stylesheet" type="text/css"></link>
</head>
<body>
<!-- 网页top -->
	<jsp:include page="top.jsp"/>
	<div class="blogs_main">
		<!-- 网页heard -->
		<jsp:include page="heard.jsp"/>
	<!-- 日志列表 -->
		<div class="log">
    		<div class="log1">
                <div class="log2">
                <span><a class="sendlog"></a><a href="<%= path%>/blogs/log!togoAdd.action?user.id=${user.id}">发表新日志</a></span>
                <h5>日志</h5>
                </div>
             	<div class="logmetter">
                	<ul>
                	<s:iterator var="l" value="#request.list">
                    	<li><span> ${l.date}<a>赞: </a>(${l.praise})&nbsp;</span><img src="<%= path%>/images/blogs/log.png" class="logimg"><a href="<%= path%>/blogs/log!myLog.action?blogsLog.id=${l.id}&user.id=${user.id}">${l.log_title}</a> </li>
                	</s:iterator>
                    </ul>
                </div>
            </div>
    </div>
	</div>
	<div class="fenye">
        <ul class="nav">
        <li id="shouye"><a href="<%=path %>/blogs/log!getFenYe.action?pageNum=${pageNum-1}">&#171;上页</a></li>
        <li id="gettosecond"><a href="<%=path %>/blogs/log!getFenYe.action?pageNum=${pageNum +1}">&nbsp;&nbsp; 下页&raquo;&nbsp;　</a></li>
        </ul>
	</div>
	  <!-- 网页底部 -->
	<jsp:include page="../common/end.jsp"/>
</body>
</html>