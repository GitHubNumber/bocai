<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
*{margin:0;padding:0;}
.clearfix{zoom:1;}
ul,li{list-style:none;}
img{border:0;}
.wrapper{width:575px;margin:0 auto;padding-bottom:50px;}
/* focus */
#focus{width:575px;height:280px;overflow:hidden;position:relative;}
#focus ul{height:380px;position:absolute;}
#focus ul li{float:left;width:575px;height:280px;overflow:hidden;position:relative;background:#000;}
#focus ul li div{position:absolute;overflow:hidden;}
#focus .btnBg{position:absolute;575px;height:20px;left:0;bottom:0;background:#000;}
#focus .btn{position:absolute;width:555px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
#focus .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
#focus .btn span.on{background:#fff;}
#focus .preNext{width:45px;height:100px;position:absolute;top:90px;background:url(${pageContext.request.contextPath }/images/shop/shoppage/img/sprite.png) no-repeat 0 0;cursor:pointer;}
#focus .pre{left:0;}
#focus .next{right:0;background-position:right top;}
</style>
<script type="text/javascript">
$(function() {
	var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;
	
	//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
	var btn = "<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
	$("#focus").append(btn);
	$("#focus .btnBg").css("opacity",0.5);

	//为小按钮添加鼠标滑入事件，以显示相应的内容
	$("#focus .btn span").css("opacity",0.4).mouseover(function() {
		index = $("#focus .btn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseover");

	//上一页、下一页按钮透明度处理
	$("#focus .preNext").css("opacity",0.2).hover(function() {
		$(this).stop(true,false).animate({"opacity":"0.5"},300);
	},function() {
		$(this).stop(true,false).animate({"opacity":"0.2"},300);
	});

	//上一页按钮
	$("#focus .pre").click(function() {
		index -= 1;
		if(index == -1) {index = len - 1;}
		showPics(index);
	});

	//下一页按钮
	$("#focus .next").click(function() {
		index += 1;
		if(index == len) {index = 0;}
		showPics(index);
	});

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},4000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
		$("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的
	}
});

</script>

<!--顶部-->
<div class="head_div">
<div class="head_div_left">
    <div class="head_div_left_top">
    	<div class="head_div_left_top_div"><a href="#">积分抽奖火爆开启，多款豪礼大放送，赶快来拼人品吧~</a></div>
    </div>
    <div class="head_div_left_down">
        <div class="head_div_left_down_div">
        <label>全部商品分类</label>
        	<ul>
                <li><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png">我的购物车</li>
               	<li><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png">我的交易记录</li>
				<li><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png">浏览全部商品</li>
               	<li id="last_li_ck"><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png">我的积分详情</li>
            </ul>
        </div>
    </div>
</div>
<div class="head_div_center_js">

<div class="wrapper">
		<div id="focus">
			<ul>
				<li><a href="http://www.jsfoot.com/" target="_blank"><img src="${pageContext.request.contextPath }/images/shop/shoppage/img/01.jpg" alt="QQ商城焦点图效果下载" /></a></li>
					<li><a href="http://www.jsfoot.com/" target="_blank"><img src="${pageContext.request.contextPath }/images/shop/shoppage/img/02.jpg" alt="QQ商城焦点图效果教程" /></a></li>
				<li><a href="http://www.jsfoot.com/" target="_blank"><img src="${pageContext.request.contextPath }/images/shop/shoppage/img/03.jpg" alt="jquery商城焦点图效果" /></a></li>
				<li><a href="http://www.jsfoot.com/" target="_blank"><img src="${pageContext.request.contextPath }/images/shop/shoppage/img/04.jpg" alt="jquery商城焦点图代码" /></a></li>
				<li><a href="http://www.jsfoot.com/" target="_blank"><img src="${pageContext.request.contextPath }/images/shop/shoppage/img/05.jpg" alt="jquery商城焦点图源码" /></a></li>
		</ul>
		</div><!--focus end-->
	
	</div><!-- wrapper end -->
</div>
<div class="head_div_down">
	<div class="head_div_down_top">
    <div class="head_div_down_top_img"><img src="${pageContext.request.contextPath }/images/shop/shoppage/user_info_03.jpg"></div>
    <div class="head_div_down_top_info">
    <ul>
   <li>${landinguser.power==0 ? '游客' : landinguser.nickname }<c:if test="${landinguser.power==0 }"><a href="${pageContext.request.contextPath }/user/getregister.action">[注册]</a></c:if><c:if test="${landinguser.power!=0 }"><a href="${pageContext.request.contextPath }/user/operate!loginOut.action">[注销]</a></c:if></li>
    <li><img src="${pageContext.request.contextPath }/images/shop/shoppage/user_info_07.jpg"/>积分：${landinguser.power==0 ? 0 : landinguser.integral }</li>
    <li><img src="${pageContext.request.contextPath }/images/shop/shoppage/tubiao.jpg"/>等级：${landinguser.power==0 ? 0 : landinguser.grade }</li>
    </ul>
    </div>
    </div>
    <div class="head_div_down_center"><input type="button"/></div>
    <div class="head_div_down_down">
    		<ul>
               <li><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png"><a href="${pageContext.request.contextPath }/shop/selAllCommodity">我的购物车</a></li>
               	<li><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png"><a href="${pageContext.request.contextPath }/shop/getAllRecord">我的交易记录</a></li>
				<li><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png"><a href="${pageContext.request.contextPath }/shop/getMoreShop">浏览全部商品</a></li>
               	<li id="last_li_ck"><img src="${pageContext.request.contextPath }/images/shop/shoppage/fsda.png"><a href="#">我的积分详情</a></li>
            </ul>
    </div>
</div>
</div>