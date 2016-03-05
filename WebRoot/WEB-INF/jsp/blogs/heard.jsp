<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<div class="blogs_header">
	<div class="blogs_headtop">
		<li>${user.nickname }的个人空间</li>
		<li class="blogs_li1"><a>www.mango.com</a>[<a href="#">收藏</a>][<a
			href="#">分享</a>]</li>
	</div>
	<div class="blogs_nav">
		<a href="${pageContext.request.contextPath }/blogs/log?user.id=${user.id}">主页</a><a href="${pageContext.request.contextPath }/blogs/log!getFenYe.action?user.id=${user.id}">日志</a><a href="${pageContext.request.contextPath }/blogs/photogroup!getFenYe.action?user.id=${user.id}">相册</a><a
			href="${pageContext.request.contextPath }/blogs/messages!getPaging.action?user.id=${user.id}">留言板</a><a href="${pageContext.request.contextPath }/blogs/mood!getFenYe.action?user.id=${user.id}">心情</a><a href="#">个人资料</a>
	</div>
</div>