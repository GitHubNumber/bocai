<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>   
    <title>所有商品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="${pageContext.request.contextPath }/css/shop/style_buycar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$("#down_ul_li3 input").click(function(num){
		window.location="${pageContext.request.contextPath }/shop/addCommodity?comm.id="+$(this).attr("name");
	});
	if("${number }">=3){
		if("${pagecount }">4){
			if("${number }"<="${pagecount }"-2){
				var page="";
				for(i=-2;i<=2;i++){
					if(i==0){
					page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+eval("${number }"+"+"+i)+"&pagetype="+"${pagetype}"+"' id='a_back_color'>"+eval("${number }"+"+"+i)+"</a>";
					}else{
					page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+eval("${number }"+"+"+i)+"&pagetype="+"${pagetype}"+"'>"+eval("${number }"+"+"+i)+"</a>";
					}
				}
				$(".BUYCARTOPBOXBOTTOM span").html(page);
			}else{
				var page="";
				for(i=4;i>=0;i--){
					if(eval("${pagecount }"-i)=="${number}"){
						page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+eval("${pagecount }"-i)+"&pagetype="+"${pagetype}"+"' id='a_back_color'>"+eval("${pagecount }"-i)+"</a>";
					}else{
						page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+eval("${pagecount }"-i)+"&pagetype="+"${pagetype}"+"'>"+eval("${pagecount }"-i)+"</a>";
					}
					
				}
				$(".BUYCARTOPBOXBOTTOM span").html(page);
			}
		}else{
			var page="";
			for(i=1;i<="${pagecount }";i++){
				alert(2);
				if(i=="${number}"){
					page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+i+"&pagetype="+"${pagetype}"+"' id='a_back_color'>"+i+"</a>";
				}else{
					page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+i+"&pagetype="+"${pagetype}"+"'>"+i+"</a>";
				}
				
			}
			$(".BUYCARTOPBOXBOTTOM span").html(page);
		}
	}else{
		var page="";
		for(i=1;i<="${pagecount }";i++){
			
			if(i=="${number}"){
			page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+i+"&pagetype="+"${pagetype}"+"' id='a_back_color'>"+i+"</a>";
			}
			else{
			page=page+"<a href='${pageContext.request.contextPath }/shop/getMoreShop?number="+i+"&pagetype="+"${pagetype}"+"'>"+i+"</a>";
			}
			if(i==5){
				break;
			}
		}
		$(".BUYCARTOPBOXBOTTOM span").html(page);
	}
	if("${pagetype }"==2){
		$("a.all").attr("id","a_back_color");
		}
		if("${pagetype }"==0){
		$("a.face").attr("id","a_back_color");
		}
		if("${pagetype }"==1){
		$("a.flower").attr("id","a_back_color");
		}
	$(".up_page").click(function(){
		window.location="${pageContext.request.contextPath }/shop/getMoreShop?number="+eval("${number }"-1)+"&pagetype="+"${pagetype}";
	});
	$(".down_page").click(function(){
			window.location="${pageContext.request.contextPath }/shop/getMoreShop?number="+eval("${number }"+"+"+1)+"&pagetype="+"${pagetype}";
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
<%@include file="../../common/bbs/cssandscript.jsp" %>
<body>
<%@ include file="../../common/shoptop.jsp" %>
 <div class="BUYCARTOPBOX">
	<div class="BUYCARTOPBOXTOP"></div>
    
    <div class="BUYCARTOPBOXMAIN">
    	<div class="BUYCARTOPBOXMAIN_1">
        	<ul id="BUYCARTOPBOXMAIN_1"><li><img src="${pageContext.request.contextPath }/images/shop/shoppage/yonghu_03.jpg" /></li></ul>
			<ul id="BUYCARTOPBOXMAIN_2">
            	<li>${landinguser.power=="0" ? '游客' : landinguser.nickname }
<c:if test="${landinguser.power=='0'}"><a href="${pageContext.request.contextPath }/user/get_login">注册</a></c:if>
<c:if test="${landinguser.power!='0'}"><a href="${pageContext.request.contextPath }/user/Logout">退出</a></c:if></li>
            	<li><img src="${pageContext.request.contextPath }/images/shop/shoppage/tubiao.jpg" /><span id="BUYCARTOPBOXSPAN_1">积分：</span>${landinguser.power=="0" ? 0 : landinguser.integral}<span id="BUYCARTOPBOXSPAN_1"></span></li>
            </ul>
            
            <ul id="BUYCARTOPBOXMAINUL">
            	<li id="BUYCARTOPBOXMAINLI"><img src="${pageContext.request.contextPath }/images/shop/shoppage/01_03.jpg" /><a href="${pageContext.request.contextPath }/shop/selAllCommodity">我的购物车</a></li>
                <li id="BUYCARTOPBOXMAINLI"><img src="${pageContext.request.contextPath }/images/shop/shoppage/02_03.jpg" /><a href="${pageContext.request.contextPath }/shop/getAllRecord">我的交易记录</a></li>
                <li id="BUYCARTOPBOXMAINLI"><img src="${pageContext.request.contextPath }/images/shop/shoppage/03_03.jpg" /><a href="${pageContext.request.contextPath }/shop/getMoreShop">浏览全部商品</a></li>
                <li><img src="${pageContext.request.contextPath }/images/shop/shoppage/04_03.jpg" />我的积分详情</li>                  
            </ul>
            
            <div id="img_2_img"><img src="${pageContext.request.contextPath }/images/shop/shoppage/2_img_03.jpg" /></div>
            <div class="down_down_right">
            <ul>
             <c:forEach items="${indexMap.get('maxgood') }" var="comm">
            <li><img src="${pageContext.request.contextPath }/${comm.imgpath }" style="width:85px; height:54px;"/><div><a href="#">${comm.introduce }</a></div></li>
            </c:forEach>
            </ul>
            </div>  
        </div>
        <div class="BUYCARTOPBOXMAIN_2">
       	<div class="BUYCARTOPBOXMAIN_2_TOP"><a href="${pageContext.request.contextPath }/shop/getShopIndexDate">返回首页</a></div>
        <div class="BUYCARTOPBOXMAIN_2_CENTER"><span><a href="${pageContext.request.contextPath }/shop/getMoreShop?pagetype=2&number=1" class="all">全部</a><a href="${pageContext.request.contextPath }/shop/getMoreShop?pagetype=0&number=1" class="face">头像</a><a href="${pageContext.request.contextPath }/shop/getMoreShop?pagetype=1&number=1" class="flower">鲜花</a></span></div>
        <div class="BUYCARTOPBOXMAIN_2_DOWN">
         <div id="all_shop_font">全部商品：</div>
         <div class="down_down2">
         <!------>
         <c:forEach items="${indexMap.get('allshop') }" var="comm">
         <div>
            <div id="shop_img" class="shop_img2" style="background: url(${pageContext.request.contextPath }/${comm.imgpath })">
            <div class="shop_inter"><a href="#">${comm.introduce }</a></div>
            </div>
            <ul class="interest_down_ul">
            <li id="down_ul_li1">${comm.name }</li>
            <li id="down_ul_li2">
            <ul>
            <li id="li2_li1">${comm.price}</li>
            <li id="li2_li2">1981</li>
            </ul>
            </li>
            <li id="down_ul_li3"><input type="button" value="购买" name="${comm.id }"/></li>
            
            </ul>
         </div>
         </c:forEach>
         <!------>
     
         </div>
        </div>
    	</div>
    </div>
  <div class="BUYCARTOPBOXBOTTOM">
 	<input type="button" value="" class="up_page"/>
 	<span>
    </span>
    <input type="button" value="" class="down_page"/>
  </div>
</div> 
<%@ include file="../../common/end.jsp" %>
</body>
</html>
