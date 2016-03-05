<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>交易记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath }/css/shop/style_buycar2.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
$(function(){
	if("${number }">=3){
		if("${pagecount }">4){
			if("${number }"<="${pagecount }"-2){
				var page="";
				for(i=-2;i<=2;i++){
					if(i==0){
					page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+eval("${number }"+"+"+i)+"' id='a_back_color'>"+eval("${number }"+"+"+i)+"</a>";
					}else{
					page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+eval("${number }"+"+"+i)+"'>"+eval("${number }"+"+"+i)+"</a>";
					}
				}
				$(".fenye span").html(page);
			}else{
				var page="";
				for(i=4;i>=0;i--){
					if(eval("${pagecount }"-i)=="${number}"){
						page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+eval("${pagecount }"-i)+"' id='a_back_color'>"+eval("${pagecount }"-i)+"</a>";
					}else{
						page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+eval("${pagecount }"-i)+"'>"+eval("${pagecount }"-i)+"</a>";
					}
					
				}
				$(".fenye span").html(page);
			}
		}else{
			var page="";
			for(i=1;i<="${pagecount }";i++){
				if(i=="${number}"){
					page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+i+"' id='a_back_color'>"+i+"</a>";
				}else{
					page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+i+"'>"+i+"</a>";
				}
				
			}
			$(".fenye span").html(page);
		}
	}else{
		var page="";
		for(i=1;i<="${pagecount }";i++){
			
			if(i=="${number}"){
			page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+i+"' id='a_back_color'>"+i+"</a>";
			}
			else{
			page=page+"<a href='${pageContext.request.contextPath }/shop/getAllRecord?number="+i+"'>"+i+"</a>";
			}
			if(i==5){
				break;
			}
		}
		$(".fenye span").html(page);
	}
	$(".up_page").click(function(){
		window.location="'${pageContext.request.contextPath }/shop/getAllRecord?number="+eval("${number }"-1);
	});
	$(".down_page").click(function(){
			window.location="'${pageContext.request.contextPath }/shop/getAllRecord?number="+eval("${number }"+"+"+1);
	});
});
</script>
<%@include file="../../common/bbs/shopcssandscript.jsp" %>
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
        </div>
        
        <div class="BUYCARTOPBOXMAIN_2">
       		 <ul>
            	 <li><span><a href="${pageContext.request.contextPath }/shop/getShopIndexDate"><img src="${pageContext.request.contextPath }/images/shop/shoppage/topback_03.jpg"/></a></span><img src="${pageContext.request.contextPath }/images/shop/shoppage/kuai.jpg" width="4"  height="16" id="BUYCARTOPBOXSPAN_2_2"/>我的订单</li>
             </ul>
    	</div>
         <div class="BUYCARTOPBOXMAIN_3">
       	<table width="720"cellspacing="0" cellpadding="0" align="center">
              <tr id="tableth">
                <th width="100">订单编号<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>              
                <th width="166">商品名称<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>
                <th width="70">商品单价<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>
                <th width="80">商品数量<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>
                <th width="150">购买时间<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th> 
                <th width="72">消耗积分</th>
          </tr>
          <c:forEach items="${oldbuy }" var="indent">
              <tr align="center">
                <td id="td1">${indent.id }</td>
                <td id="td2">${indent.shopname }</td>
                <td id="td3">${indent.shopprice }</td>
                <td id="td4">${indent.shopnum }</td>
                <td id="td5">${indent.buytime }</td>
                <td id="td6">${indent.pricesum }</td>
          </tr>
          </c:forEach>
	</table>
    <div class="fenye_ck">
    <input type="button" value="" class="up_page"/>
 	<span>
    </span>
    <input type="button" value="" class="down_page"/></div>
</div>
    </div>
  <div class="BUYCARTOPBOXBOTTOM"></div>
</div>
<%@ include file="../../common/end.jsp" %>
</body>
</html>