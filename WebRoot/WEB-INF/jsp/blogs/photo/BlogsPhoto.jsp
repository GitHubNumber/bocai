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
<title>我的相册</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/log.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/photo.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/blogs2.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/blogs/myphoto.css"
	type="text/css"></link>
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
			<span><a class="sendlog"></a>
				<a href="javascript:void(0)" onclick='openDialog("reply");'>添加新相片</a>
				
			</span>
			<h5>我的相册</h5>
		</div>
		<div class="center">
			<div class="center_allgroup">
				<s:iterator var="l" value="list">
					<div class="center_group">
						<div class="center_group2">
							<a
								href="<%=path%>/blogs/photo!selectMyPhoto.action?blogsphoto.id=${l.id}&user.id=${user.id}"><img
								alt="日期: ${l.date} ,group_id: ${l.group_id} photo :${l.photo }
	  	 photoInfo: ${l.photoInfo } user_id : ${l.user_id }"
								src="<%=path %>/images/blogs/userphoto/${l.photo}" width="200px"
								height="200px"> </a> <br />
						</div>
					</div>
				</s:iterator>
			</div>

			<div class="group_roght">
				<img alt="" src="<%=path%>/images/blogs/log/ble.jpg"> <br />
				<span class="photoclass"><b> 相册分类</b></span>
				<ul class="p_fenlei">
					<s:iterator var="lg" value="glist">

						<li><a class="groupA" href="<%=path %>/blogs/photo!selectPhotoByGroupId.action?blogsphotogroup.id=${lg.id}&user.id=${user.id}">
								${lg.photoGroup}</a>
						</li>
					</s:iterator>
				</ul>
			</div>
			<div class="pgleft">
				<div class="feny">
					<ul class="nav">
						<li id="shouye"><a
							href="<%=path %>/blogs/log!getFenYe.action?pageNum=${pageNum-1}&user.id=${user.id}">&#171;上页</a>
						</li>
						<li id="gettosecond"><a
							href="<%=path %>/blogs/log!getFenYe.action?pageNum=${pageNum +1}&user.id=${user.id}">&nbsp;&nbsp;
								下页&raquo;&nbsp; </a>
						</li>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="../../common/end.jsp"></jsp:include>
	<div class="tananswer">
		<span class="close"></span><span id="reply">添加新图片</span>
		<form action="<%=path%>/blogs/upload.action?user.id=${user.id}" method="post" class="center_form"
			enctype="multipart/form-data">
			<input type="hidden" name="blogsphotogroup.id"
				value="${blogsphotogroup.id}">
			<div class="center_box">
				<div
					style="background-color: #cccccc;">
					<h3>上传照片</h3>
				</div>
				<ul>
					<li>
						<h4>文件描述:</h4>
						<input type="text" name="blogsPhotoUpload.info" size="30"
						height="30px">
					</li>
					<li>
						<h4>文 &nbsp;&nbsp;件:</h4>
						<input type="file" name="blogsPhotoUpload.upload" size="20"><br>
					</li>
					<li><input type="submit" class="logpinglun2" value="  提   交  ">
					</li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>
