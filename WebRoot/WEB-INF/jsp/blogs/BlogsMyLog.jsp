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
<base href="<%=basePath%>">

<title>我的日志</title>
<link rel="stylesheet"	href="${pageContext.request.contextPath }/css/blogs/blogs2.css"	type="text/css"></link>
</head>
<body>
	<!-- 网页top -->
	<jsp:include page="top.jsp" />
	<div class="blogs_main">
		<!-- 网页heard -->
		<jsp:include page="heard.jsp" />
				<div class="log">
    		<div class="log1">
                <div class="log2">
                <span><a class="sendlog"></a><a href="<%= path%>/blogs/log!togoAdd.action">发表新日志</a></span>
                <h5>日志</h5>
                </div>
             	<div class="logmetterin">
                  <ul>
                  <s:iterator var="l" value="#request.blogsLog">
                    <li class="logli1"><span><a class="blogsauthor"></a>&nbsp;<a>Crazymango</a></span><span>${l.date } (2/3)&nbsp;|&nbsp;</span><h3>${l.log_title }</h3> 
                        </li>
                    <li class="logli2">
                      ${l.log}
                        </li>
                        </s:iterator>
                        <li class="logli4">
                    	<div class="logli1_1">
                        <h5>评论</h5>
                        </div>
                    	</li>
                    	
                        <li class="logli3">
                           <table width="965" border="0" >
                           <s:if test="%{clist !=null}">
                           <s:iterator var="l" value="#request.clist" >
                               <tr>
                                  <td width="70" valign="top" style="text-align:center;">
                                  <img class="person_leavewordimg" src="<%=path%>/images/blogs/touxiang.png" width="48" height="48" />
                                  </td>
                                  <td width="688">
                                        <div class="person_leaveword5"><span><a>编辑</a>|<a>删除</a></span><a href="#">${l.user_id}</a> 2012-08-12 12:10</div>
                                        <div class="person_leaveword6">
                                      ${l.comment}
                                        </div>                  
                                 </td>
                              </tr>
                             </s:iterator>
				</s:if>
				<s:if test="%{clist =null}">
				<div class="otherlog">
				还没有沙发速抢
				</div>
				</s:if>
                        </table>
                    </li>
                    <li class="logli5">
                   		<div class="send">
				<s:iterator var="l" value="#request.blogsLog" >
					<form action="<%=path%>/blogs/comment!addLog.action?blogsLog.id=${l.id}&user.id=${user.id}" method="post">
						<textarea id="content1"  class="logpinglun" name="blogsComment.comment"></textarea>
						<input type="hidden" name="blogsComment.user_id" value="1">
						<s:iterator var="l" value="#request.blogsLog">
						<input type="hidden" name="blogsComment.table_id" value="${l.id }">
						</s:iterator>
						<input type="submit"  class="logpinglun" value="发表" />
					</form>
				</s:iterator>
				</div>
                    </li>
                  </ul>
                
                </div>
            </div>
				</div>
				
				
				
				
				
				
				
				
				
	</div>
	<!-- 网页底部 -->
	<jsp:include page="../common/end.jsp" />
</body>
</html>
