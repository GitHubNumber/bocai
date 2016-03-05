<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Mango_EcShop首页</title>
	</meta>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/shop/style.css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$("#down_ul_li3 input").click(function(num){
		window.location="${pageContext.request.contextPath }/shop/addCommodity?comm.id="+$(this).attr("name");
	});
	$(".shop_inter").css("opacity","0.6");
	$(".shop_inter").hide();
	$(".shop_img2").mouseover(function(){
		$(this).children(".shop_inter").show();
		$(this).children(".shop_inter").animate({height:"112px",marginTop:"0px"},{ queue: false, duration: 90 });
	}).mouseout(function(){
		$(this).children(".shop_inter").hide();
		$(this).children(".shop_inter").animate({height:"0px",marginTop:"110px"},{ queue: false, duration: 90 });
	});
});
</script>
<body>
<%@include file="../../common/bbs/cssandscript.jsp" %>
	<%@include file="../../common/shoptop.jsp" %>
<div class="allpage" style="width:980px; margin:0 auto; ">
	
    <%@include file="../model/shopindextop.jsp" %>
    <%@include file="../model/shopindexcenter.jsp" %>
    <%@include file="../model/shopindexdown.jsp" %>
    
</div>
<%@include file="../../common/end.jsp" %>
</body>
</html>