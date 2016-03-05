<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主题</title>
</head>

<%@include file="../common/bbs/cssandscript.jsp" %>
<%@include file="../common/bbs/fromtop.jsp" %>
<script>
$(document).ready(function(){
		var now=${queryResult.nowpage };
		var alltitle=${queryResult.totalrecord };
		var allpage=Math.floor( alltitle%10!=0? (alltitle/10+1):(alltitle/10));
		var sendallpage=0;
		var first=0;
		var end=0;
		var over=0;//上一页
		var second=0;//下一页
			if(now>allpage||now <1){
				now=1;
			}
			if(now+3>=allpage){
				sendallpage=allpage;
			}else if(now<7&&allpage>=7){
				sendallpage=7;
			}else if(now<=7&&allpage<=7){
				sendallpage=allpage;
			}else{
				sendallpage=now+3;
			}
			if(sendallpage<=7){	
				first=1;
			}else{
				first=sendallpage-6;
			}
			if(first+7>=sendallpage){
				end=sendallpage;
			}else{
				end=first+7;
			}
			if(now==end){
				second=end;
			}else{
				second=now+1;
			}
			if(now==first){
				over=first;
			}else{
				over=now-1;
			}
		var pageing="";
		for(var i=first;i<end+1;i++){
			if(now==i){
				pageing=pageing+"<li class='nowpagenav'><a href='#'>"+i+"</a></li>";
			}else{
				pageing=pageing+"<li><a href='${pageContext.request.contextPath }/bbs/get_comment.action?topicTitle.id=${queryResult.commentid }&queryResult.nowpage="+i+"'>"+i+"</a></li>";
			};
		}
		$(" #shouye").after(pageing);
$(".mouse_img").mouseover(function(){
	var thisobj=$(this);
	var userid=$(this).attr("id");
	if(thisobj.parent().prev().text()==""){
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath }/Chick/AjaxValue!ajaxUser",
			data : {"user.id" : userid},
			dataType : "text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
			success : function(json) {
				var obj = $.parseJSON(json); //使用这个方法解析json
				thisobj.parent().prev().html("<div id='tanchu1' class='tanchu1'><div class='tanchuhaead'><img src='${pageContext.request.contextPath }/images/user/userpic.png' width='130' height='130'/></div><div class='tanchuinfo'><ul><li><h4>"+obj.user.nickname+"</h4></li><li>&nbsp;</li><li>等级："+obj.user.grade+"</li><li>注册时间："+obj.user.registertime.substring(0,obj.user.registertime.length-9)+"</li><li>积分："+obj.user.integral+"</li><li>精华："+obj.user.contribute+"</li><li>贡献度："+obj.user.contribute+"</li><li>帖子："+obj.user.contribute+"</li><li id='tanchuimg'><a id='bolgs' title='博客' />&nbsp<a id='messageicon' title='留言' />&nbsp<a id='charmicon' title='魅力' /></li><li>&nbsp;</li></ul></div></div>");
				thisobj.parent().prev().css("display","block");
			}, 
			error : function(json) {
				return false;
			}
		});
	}else{
		$(this).parent().prev().css("display","block");
	}
});
$(" #show_user_info").mouseleave(function(){$(this).css("display","none");});
});
(function($){
	function getParent(obj,cnt){
		for(var i=0;i<cnt;i++){
			obj = obj.parent();
		}
		return obj;
	}
	
	$.fn.TDragDrop = function(set){
		var defaults = {
			parent:0,		// 拖动对象，向上n父节点，parent为0时，拖动本身
			opacity:0.3	// 拖动时节点透明度，默认为0.3
		};
		var setting = $.extend(defaults, set);

		this.each(function(){
			var ele = getParent($(this),setting.parent);
			ele.css('position','absolute')
			   .css('top',ele.offset().top)
			   .css('left',ele.offset().left);
			$(this).bind('mousedown',function(event){
				event = event || window.event;
				var self = getParent($(this),setting.parent);
				self.attr('flag',true)
					.attr('oldZIndex',self.css('z-index'))
					.attr('startX',event.clientX - self.offset().left)
					.attr('startY',event.clientY - self.offset().top);
					
				self.css('cursor','move')
					.css('z-index',99999)
					.css('opacity',setting.opacity);
      document.onselectstart=function(){return false};
			}).bind('mouseup mouseout',function(){
				var self = getParent($(this),setting.parent);
				self.css('cursor','auto')
					.css('z-index',self.attr('oldZIndex'))
					.css('opacity','1');
				self.attr('flag',false);
				document.onselectstart=function(){return true};
			}).bind('mousemove',function(event){
				event = event || window.event;
				var self = getParent($(this),setting.parent);
				if(self.attr('flag') === 'true'){
					self.offset({'left':event.clientX - self.attr('startX'),
								 'top':event.clientY - self.attr('startY')});
					if(self.offset().left < 0){
						self.offset({'left':0});
					}else if(self.offset().left > $(window).width() - self.width() - 2){
						self.offset({'left':$(window).width() - self.width() - 2});
					}
					if(self.offset().top < 0){
						self.offset({'top':0});
					}else if(self.offset().top > $(window).height() - self.height() - 2){
						self.offset({'top':$(window).height() - self.height() - 2});
					}
					    		
				}
			});
			
		});
	}
})(jQuery);

$(function(){
// 调用

var obj = $("#reply");
	obj.TDragDrop({'parent':1});

$('.close,.cancel').click(function(){
  $(this).parent().fadeOut('normal');
})
  

});

function openDialog(id){
// 居中
var box = $("#"+id).parent();
box.fadeIn('normal');
box.offset({'top':($(window).height()-box.height())/2,
            'left':($(window).width()-box.width())/2});
$("textarea").val('');
}
</script>
  <body>
  	<form action="${pageContext.request.contextPath }/bbs/addcomment" method="post">
	<div class="tananswer">
			<input type="hidden" name="topicTitle.id" value="${queryResult.resultlist.get(0).topictitleid.id }">
            <s:token/>
      		<span class="close"></span>
			<span id="reply">评论${queryResult.resultlist.get(0).topictitleid.topic }</span>
			<span class="tip">请输入内容：</span>
			<textarea name="topicComment.commentContent"></textarea>
			<button type="submit" >发表评论</button>
      		<a href="javascript:void(0)" class='cancel'>取消</a>
	</div>
	</form>
	<%@include file="../common/top.jsp" %>
<div class="allpage">
    <%@include file="../common/bbs/userinfo.jsp" %>
    <%@include file="../common/bbs/nav.jsp" %>
    <%@include file="../common/bbs/pagenav.jsp" %>
    <div class="asearch">
	<ul>
		<span class="asearchrigeht"><li><input type="text"
				class="seachma" value="请输入搜索内容" />
		</li>
			<li><input type="submit" name="searchsubmit" class="sousuo"
				value="搜索"></input>
		</li> </span>
		<li><img
			src="${pageContext.request.contextPath }/images/bbs/icon11.png"
			id="zhuye" />
		</li>
		<li id="zhexian"><a href="#">所有版面</a>
		</li>
		<li><a href="#">精彩活动</a>
		</li>
		<li id="zhexian2"><a href="#">查看新帖</a>
		</li>
	</ul>
</div>
    <%@include file="../common/bbs/pagingmetter.jsp" %>
    <%@include file="../common/bbs/comment.jsp" %>
    <%@include file="../common/bbs/pagingmetter.jsp" %>
    <%@include file="../common/bbs/commenteditor.jsp" %>
    <%@include file="../common/end.jsp" %>
</div>
  </body>
</html>
