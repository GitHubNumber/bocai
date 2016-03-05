<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath }/css/shop/style_buycar3.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$("#del_shop").click(function(){
		$("#myform").attr("action","${pageContext.request.contextPath }/shop/delCommodity");
		$("#myform").submit();
		return false;
	});
	$("#all_change").click(function(){
		if($(this).attr("checked")=="checked"){
		$("#td6 input").attr("checked",$(this).attr("checked"));
		}
		else{
			$("#td6 input").removeAttr("checked");	
		}
	});
	var pricesum1=0;
	$(".pricesum").each(function(){
		pricesum1=eval($(this).text()+"+"+pricesum1);
	});
	$("div.fenye b").text(pricesum1);
	
	$("#td4 a").click(function(){
		var num=0;var sum=0;
		if($(this).text()=="-"){
			if($(this).next().val()>1){
			num=eval($(this).next().val()+"-"+1);
			$(this).next().val(num);
			}else{
			num=1;
			$(this).next().val(num);
			}
			
		}
		if($(this).text()=="+"){
			num=eval($(this).prev().val()+"+"+1);
			$(this).prev().val(num);
		}
		$(this).parent().next().text(eval($(this).parent().prev().text()*num)+"");
		$(".pricesum").each(function(){
			sum=eval($(this).text()+"+"+sum);
		});
		$("div.fenye b").text(sum);
		return false;
	});
	if("${flag }"==1){
		alert("您的积分不足以支付！");
	}
	$("#sure_give_monney_ck").click(function(){
		if("${allid }"!=""){
		if(confirm("确认支付？")){
			$("#myform").submit();
		}
		}else{
			alert("请先购买商品!");
		}
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
            	 <li><span><a href="${pageContext.request.contextPath }/shop/getShopIndexDate"><img src="${pageContext.request.contextPath }/images/shop/shoppage/topback_03.jpg"/></a></span><img src="${pageContext.request.contextPath }/images/shop/shoppage/kuai.jpg" width="4"  height="16" id="BUYCARTOPBOXSPAN_2_2"/>购物车</li>
             </ul>
    	</div>
        <form action="${pageContext.request.contextPath }/shop/addBuyRecord" method="post" id="myform">
         <div class="BUYCARTOPBOXMAIN_3">
       	<table width="720"cellspacing="0" cellpadding="0" align="center">
              <tr id="tableth">
                <th width="100">商品编号<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>              
                <th width="166">商品名称<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>
                <th width="100">商品单价<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>
                <th width="100">商品个数<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th>
                <th width="100">消耗积分<img src="${pageContext.request.contextPath }/images/shop/shoppage/tabledot.jpg" height="30"/></th> 
                <th width="72">操作</th>
          </tr>
          <c:forEach var="comm" items="${shocar }">
              <tr align="center">
                <td id="td1">${comm.id }</td>
                <td id="td2">${comm.name }<input type="hidden" name="shopname" value="${comm.name }"/></td>
                <td id="td3">${comm.price }<input type="hidden" name="price" value="${comm.price }"/></td>
                <td id="td4"><a href="#">-</a><input type="text" name="shopnum" value="1" maxlength="4" size="1"/><a href="#">+</a></td>
                <td id="td5" class="pricesum">${comm.price }</td>
                <td id="td6"><input type="checkbox" name="shop_id" value="${comm.id }"/></td>
          </tr>
          </c:forEach>
	</table>
    <div class="fenye_ck"><span>总价：<b></b>分</span><input type="checkbox" id="all_change"/>全选<a href="#" id="del_shop">删除所选商品</a></div>
<input type="button" value="确认支付" id="sure_give_monney_ck" style="border:1px solid #00000;"/>
</div>
</form>
    </div>
  <div class="BUYCARTOPBOXBOTTOM"></div>
</div>
<%@ include file="../../common/end.jsp" %>
</body>
</html>