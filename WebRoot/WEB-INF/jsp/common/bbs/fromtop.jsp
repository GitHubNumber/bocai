<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<a name="top"> </a>
<div class="totop">
<c:if test="${landinguser.power!=0}">
<a href="${pageContext.request.contextPath }/bbs/get_sendmetter" class="cw">发帖子</a>
</c:if>
<c:if test="${landinguser.power==0}">
<a  href="javascript:void(0)" onclick="showPanel();"> 发帖子</a>
</c:if>
<a href="#top">回顶部</a>
</div>
