<%@page import="com.mango.blogs.bean.BlogsComment"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>留言</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/mood/mood.css"
	type="text/css"></link>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/blogs2.css"
	type="text/css"></link>
<script charset="utf-8"
	src="${pageContext.request.contextPath }/js/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/blogs/date.js"
	charset="gbk"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.js"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="content1"]', {
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'bold', 'italic',
					'underline', 'forecolor', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'emoticons' ]
		});
	});
	$(function() {
		$('.commAdd').click(function() {
		$('.myform').css("display","none");
		$(this).prev().css("display","block");
		});
	});
</script>
</head>
<body onLoad="startclock(); timerONE=window.setTimeout">
	<jsp:include page="top.jsp" />
	<div class="blogs_main">
		<jsp:include page="heard.jsp" />
		<div class="log2 log1 margintop">
                <h5>留言板</h5>
         </div>
		<div class="mood_box">
				
				<!-- 留言 -->
				<div class="logmetterin">
                  <ul>
                        <li class="logli3">
                           <table width="965" border="0" >
                           	<s:iterator var="l" value="#request.mlist" >
                               <tr>
                                  <td width="70" valign="top" style="text-align:center;">
                                  <img class="person_leavewordimg" src="<%=path%>/images/blogs/touxiang.png" width="48" height="48" />
                                  </td>
                                  <td width="688">
                                        <div class="person_leaveword5"><span><a>编辑</a>|<a>删除</a></span><a href="#">CrazyMango-->${l.message_user_id}</a> 2012-08-12 12:10</div>
                                        <div class="person_leaveword6">
                                       ${l.message}
                                        </div>
                                        <div >
                                        <c:forEach items="${l.blogsComment }" var="c">
                                        <div class="pinglun">
                                        <div class="pinglunuser">
                                         <img class="person_leavewordimg" src="<%=path%>/images/blogs/touxiang.png" width="48" height="48" />
                                        </div>
                                        <div class="pinglunComm">
                                        <ul>
                                        	<li>CrazyMango-->${c.user_id } 2012-08-12 12:10</li>
                                        	<li class="fontcolor">${c.comment }</li>
                                        </ul>
                                        </div>
                                        </div>
                                        	
                                        </c:forEach>
                                        </div>                  
	                                 <div class="comm">
							<form class="myform" action="<%=path %>/blogs/comment!addMessage.action" method="post">
							<input type="hidden" name="blogsComment.table_id" value="${l.id }">
								<textarea rows="2" cols="30" name="blogsComment.comment"></textarea>
								<input type="submit" class="logpinglun" value="提交">
							</form>
						<div class="commAdd">点击回复:</div>
							</div>
                                 </td>
                              </tr>
                              </s:iterator>
                        </table>
                    </li>
                  </ul>
                </div>
				
				
				
				
				
				
				<div class="fenye">
					总页数${maxPageNum } <a
						href="<%=path %>/blogs/messages!getPaging.action?pageNum=${pageNum-1}">&lt;&lt;上一页</a>
					<a
						href="/Mango_BBS/blogs/messages!getPaging.action?pageNum=${pageNum +1}">下一页&gt;&gt;</a>
				</div>
				
				
				<!-- 发表留言 -->
				<div class="blogs_publish_mood">
					<form action="<%=path%>/blogs/messages!addMessages.action" method="post">
						<textarea id="content1" class="content" name="blogsMessage.message"></textarea>
						<input type="hidden" name="blogsMessage.message_user_id" value="1">
						<input type="submit" class="btnimg" value="发表" />
					</form>
				</div>
		</div>
		</div>
		<div style="width: 980px; margin: 0px;padding: 0px;">
		<jsp:include page="../common/end.jsp"/>
		</div>
	
</body>
</html>
