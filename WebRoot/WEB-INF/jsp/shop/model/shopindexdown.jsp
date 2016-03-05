<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--商品展示-->
<div class="max_shopshow">
<div class="shopshow_top"><span><a href="${pageContext.request.contextPath }/shop/getMoreShop?pagetype=2&number=1">更多</a></span><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png"/><b>商品展示</b></div>
<div class="shopshow_down">
<div class="shopshow_down_top"><span><img src="${pageContext.request.contextPath }/images/shop/shoppage/lanmu_07.jpg"/></span><span>共22件商品&nbsp;</span><img src="${pageContext.request.contextPath }/images/shop/shoppage/lanmu_03.jpg" id="top_left_img"/>排序方式：<a href="#">上线时间 &gt;</a><a href="#">积分价格 &gt;</a></div>
<div class="shopshow_down_down">
<div class="down_down_left">
<!---->
<c:forEach items="${indexMap.get('face')}" var="comm" varStatus="index">
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
<!---->
</div>


<div class="down_down_right">
<ul>
<c:forEach items="${indexMap.get('facenew')}" var="comm" varStatus="index">
<li><img src="${pageContext.request.contextPath }/${comm.imgpath }" style="width:85px; height:54px;"/><div><a href="#">${comm.introduce }</a></div></li>
</c:forEach>
</ul>
</div>
</div>
</div>
</div>
