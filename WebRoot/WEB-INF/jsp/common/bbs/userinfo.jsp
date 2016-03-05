<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 用户信息模块	 -->
<div class="mattertop">
	<div style="float:left; margin-top:5px;">
		<img src="${pageContext.request.contextPath }/images/bbs/logo2.png" />
	</div>
	<div class="top">
		<span><img
			src="${pageContext.request.contextPath }/images/bbs/touxiang.png" width="48px;" height="48px;"/>
		</span>
		<div class="top1">
			<ul>
				<li><img
					src="${pageContext.request.contextPath }/images/bbs/icon10.png"/>
					${landinguser.power==0?"--游客--":landinguser.nickname}
					</li>
				<br/>
				<li></li>
				<li>提醒 |短消息 |设置 |我的中心 |好友 | 任务</li>
			</ul>
		</div>
	</div>
</div>