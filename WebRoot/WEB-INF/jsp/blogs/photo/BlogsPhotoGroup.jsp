<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional.dtd//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>相册分组</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/photo.css"
	type="text/css"></link>
	<link rel="stylesheet" href="<%=path%>/css/blogs/log.css"
	rel="stylesheet" type="text/css"></link>
	<link rel="stylesheet" href="<%=path%>/css/blogs/blogs2.css"
	rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.js"></script>
<script>
	(function($) {
		function getParent(obj, cnt) {
			for ( var i = 0; i < cnt; i++) {
				obj = obj.parent();
			}
			return obj;
		}

		$.fn.TDragDrop = function(set) {
			var defaults = {
				parent : 0, // 拖动对象，向上n父节点，parent为0时，拖动本身
				opacity : 0.3
			// 拖动时节点透明度，默认为0.3
			};
			var setting = $.extend(defaults, set);

			this
					.each(function() {
						var ele = getParent($(this), setting.parent);
						ele.css('position', 'absolute').css('top',
								ele.offset().top)
								.css('left', ele.offset().left);
						$(this)
								.bind(
										'mousedown',
										function(event) {
											event = event || window.event;
											var self = getParent($(this),
													setting.parent);
											self
													.attr('flag', true)
													.attr('oldZIndex',
															self.css('z-index'))
													.attr(
															'startX',
															event.clientX
																	- self
																			.offset().left)
													.attr(
															'startY',
															event.clientY
																	- self
																			.offset().top);

											self.css('cursor', 'move').css(
													'z-index', 99999).css(
													'opacity', setting.opacity);
											document.onselectstart = function() {
												return false;
											};
										})
								.bind(
										'mouseup mouseout',
										function() {
											var self = getParent($(this),
													setting.parent);
											self.css('cursor', 'auto').css(
													'z-index',
													self.attr('oldZIndex'))
													.css('opacity', '1');
											self.attr('flag', false);
											document.onselectstart = function() {
												return true;
											};
										})
								.bind(
										'mousemove',
										function(event) {
											event = event || window.event;
											var self = getParent($(this),
													setting.parent);
											if (self.attr('flag') === 'true') {
												self
														.offset({
															'left' : event.clientX
																	- self
																			.attr('startX'),
															'top' : event.clientY
																	- self
																			.attr('startY')
														});
												if (self.offset().left < 0) {
													self.offset({
														'left' : 0
													});
												} else if (self.offset().left > $(
														window).width()
														- self.width() - 2) {
													self.offset({
														'left' : $(window)
																.width()
																- self.width()
																- 2
													});
												}
												if (self.offset().top < 0) {
													self.offset({
														'top' : 0
													});
												} else if (self.offset().top > $(
														window).height()
														- self.height() - 2) {
													self.offset({
														'top' : $(window)
																.height()
																- self.height()
																- 2
													});
												}
											}
										});

					});
		};
	})(jQuery);

	$(function() {
		// 调用
		var obj = $("#reply");
		obj.TDragDrop({
			'parent' : 1
		});

		$('.close,.cancel').click(function() {
			$(this).parent().fadeOut('normal');
		});

		openDialog('reply');
		$('.tananswer').css("display", "none");
	});

	function openDialog(id) {
		// 居中
		var box = $("#" + id).parent();
		box.fadeIn('normal');
		box.offset({
			'top' : ($(window).height() - box.height()) / 2,
			'left' : ($(window).width() - box.width()) / 2
		});
		$("textarea").val('');
	}
</script>
</head>
<body>
	<!-- 网页top -->
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="blogs_main">
		<!-- 网页heard -->
		<jsp:include page="../heard.jsp"></jsp:include>
		<div class="log2 log1 margintop">
		 <span><a class="sendlog"></a><a href="javascript:void(0)" onclick='openDialog("reply");'>添加相册</a></span>
                <h5>相册</h5>
         </div>
		<div class="center">
			<div class="center_allgroup">
			<div class="center_context">
			
				<s:iterator var="l" value="#request.list">
					<div class="center_group">
						<div class="center_group2">
							<a
								href="<%=path %>/blogs/photo!selectPhotoByGroupId.action?blogsphotogroup.id=${l.id}&user.id=${user.id}"><img
								alt="" src="<%=path%>/images/blogs/userphoto/${l.cover}"> </a> <br />
						</div>
						<span class="group">分组: ${l.photoGroup} ,赞: (${l.user_id})</span>
					</div>
				</s:iterator>
				</div>
			<div class="pgleft">
			<div class="feny">
       			 <ul class="nav">
       			<li id="shouye"><a href="<%=path %>/blogs/photogroup!getFenYe.action?pageNum=${pageNum-1}&user.id=${user.id}">&#171;上页</a></li>
      		  	<li id="gettosecond"><a href="<%=path %>/blogs/photogroup!getFenYe.action?pageNum=${pageNum +1}&user.id=${user.id}">&nbsp;&nbsp; 下页&raquo;&nbsp;　</a></li>
      		  	</ul>
			</div>
			</div>
			</div>
			
			
			<div class="right">
				<img alt="" src="<%=path%>/images/blogs/1.jpg"> <br />
				<iframe ID='ifm2' width='189' height='190' align='center'
					marginwidth='0' marginheight='0' hspace='0' vspace='0'
					frameborder='0' scrolling='no'
					src='http://weather.qq.com/inc/ss258.htm'></iframe>
			</div>
			<div class="bottom">
				<ul class="myul">
					<li class="myli"></li>
					<li class="myli"><a href="javascript:void(0)" onclick='openDialog("reply");'><img src="<%=path %>/images/blogs/diy.png"/><br/><b>个性化相册</b></a></li>
					<li class="myli"><a href="javascript:void(0)"><img src="<%=path %>/images/blogs/add.png"/><br/><b>添加照片</b></a></li>
					<li></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tananswer">
		<span class="close"></span><span id="reply">添加新图片</span>
		 <form action="<%=path %>/blogs/uploadGroup.action?user.id=${user.id}" method="post" enctype="multipart/form-data">
  		相冊名:<input type="text" name="blogsPhotoUpload.info" ><br/>
  		点击添加封面:<input type="file" name="blogsPhotoUpload.upload"><br>
  		<input type="submit" value="提交">
  		</form>
	</div>
	<jsp:include page="../../common/end.jsp"/>
</body>
</html>