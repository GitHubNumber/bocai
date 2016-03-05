<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 顶部登陆模块 -->
<style>
.siteChose{color:#666; background-color:#f7f7f7; height:25px; border-bottom:1px solid #CCCCCC; text-align:center;margin-bottom:10px;}
.siteChose1 span {float:right; margin-top:5px;}
.siteChose1{ width:980px; margin:auto;}
</style>
<div id="fade" class="tb-shade"></div>
<DIV id=Panel style="DISPLAY: none;">
 		<div class="topnav">
        <A style="DISPLAY: block; FLOAT: right" onclick=hidePanel(); href="#"><img src="${pageContext.request.contextPath }/images/bbs/close.png" border="0" width="14" height="13" /></A>
         <h4>登录</h4>
        </div>
<IFRAME id=frame style=" overflow:hidden;" src="about:blank" frameborder="0" width="500px" height="400px" scrolling="no"></IFRAME>
</DIV>
<div class="siteChose">
	<div class="siteChose1"><a href="${pageContext.request.contextPath }" style="float:left; margin-top: 5px">Mango论坛</a>
	<span>[<c:if test="${landinguser.power!=0}" >
	欢迎你：${landinguser.nickname} | <a href="${pageContext.request.contextPath }/user/operate!loginOut.action">注销</a>
	</c:if>
	<c:if test="${landinguser.power==0}">
	<a href = "javascript:void(0)" onclick="showPanel();">论坛登录</a> | <a href="${pageContext.request.contextPath }/user/getregister.action">论坛注册</a>
	</c:if>] | <a href="${pageContext.request.contextPath }/shop/getShopIndexDate">Mango商城</a> | <a href="${pageContext.request.contextPath }">返回论坛</a></span>
    </div>
</div>