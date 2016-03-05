<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发表新帖</title>
</head>
	<%@include file="../common/bbs/cssandscript.jsp" %>
  <body>
   <%@include file="../common/top.jsp" %>
<div class="allpage">
    <%@include file="../common/bbs/userinfo.jsp" %>
    <%@include file="../common/bbs/nav.jsp" %>
    <%@include file="../common/bbs/pagenav.jsp" %>
<div class="asearch">
	<ul>
		<span class="asearchrigeht"><li><input type="text"
				class="seachma" value="请输入搜索内容" />
		</li>
			<li><input type="submit" name="searchsubmit" class="sousuo"
				value="搜索"></input>
		</li> </span>
		<li><img
			src="${pageContext.request.contextPath }/images/bbs/icon11.png"
			id="zhuye" />
		</li>
		<li id="zhexian"><a href="#">所有版面</a>
		</li>
		<li><a href="#">精彩活动</a>
		</li>
		<li id="zhexian2"><a href="#">发布新帖</a>
		</li>
	</ul>
</div>
    <%@include file="../common/bbs/metteredit.jsp" %>
    <%@include file="../common/end.jsp" %>
</div>
  </body>
</html>
