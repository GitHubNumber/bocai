<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 分页模块 -->
<div class="fenye">
        <ul class="nav">
        <li id="shouye"><a href="${pageContext.request.contextPath }/bbs/index!topicTitle.action">&#171;返回首页</a></li>
        
        <li id="gettosecond"><a href="#">&raquo;</a></li>
        </ul>
        <span id="fatie">
        	<c:if test="${landinguser.power!=0}">
	        <a href="${pageContext.request.contextPath }/bbs/get_sendmetter"> <img src="${pageContext.request.contextPath }/images/bbs/fatie.png"/></a>
	        </c:if>
	        <c:if test="${landinguser.power==0}">
	        <a  href="javascript:void(0)" onclick="showPanel();"> <img src="${pageContext.request.contextPath }/images/bbs/fatie.png"/></a>
	        </c:if>
        </span>
</div>