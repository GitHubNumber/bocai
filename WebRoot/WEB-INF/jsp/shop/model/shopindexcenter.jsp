<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!--感兴趣-->
<div class="max_interest">
<div class="interest_top"><span><a href="#">换一换</a></span><img src="${pageContext.request.contextPath }/images/shop/shoppage/love.png"/><b>您可能感兴趣的商品</b></div>
<div class="interest_down">
<c:forEach items="${indexMap.get('interest')}" var="comm" varStatus="indexs">
<div id="${indexs.count==4?'last_div':'' }">
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
</div>
</div>
