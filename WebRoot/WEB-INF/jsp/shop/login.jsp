<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
</head>
<link type="text/css" href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath }/js/share.js"></script>
<script>
	$(document).ready(function(){
		var params;
		var urlaction;
		var op;
		$("#ver").click(function(){
			$(this).attr("src","getvalidate?a="+Math.random());
		});
		$("#chack").click(function(){if($("#chack").is(":checked")){alert("感谢你的支持！你可以继续操作")}});
		$(".validate").blur(function() {
			op=$(this);
			if(op.attr("id")=="mailbox"){
				params= {
						"user.mailbox" : op.val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/CheckEmail";
			}
			if(op.attr("id")=="password"){
				params= {
						"user.password" : op.val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/CheckPassword";
			}
			if(op.attr("id")=="agpassword"){
				params= {
						"user.againpassword" : op.val(),
						"user.password":$("#password").val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/CheckagainPassword";
			}
			if(op.attr("id")=="nickname"){
				params= {
						"user.nickname" : op.val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/CheckNickname";
			}
			if(op.attr("id")=="verification1"){
				params= {
						"user.checking" : op.val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/checking";
			}
				Chickajax(op,params,urlaction);
			});
	});
	
	function Chickajax(op,params,urlaction){
		$.ajax({
			type : "POST",
			url : urlaction,
			data : params,
			dataType : "text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
			success : function(json) {
				var obj = $.parseJSON(json); //使用这个方法解析json
				var state_value = obj.result; //result是和action中定义的result变量的get方法对应的
				$(op).nextAll("div .vall").html("<img src='${pageContext.request.contextPath }/images/system/system"+obj.imagename+".jpg' />"+obj.message).show(1000);
			}, 
			error : function(json) {
				alert("json=" + json);
				return false;
			}
		});
	}

</script>
<body>
	<div style="width:100%px; background:#ffffff; margin: 0 auto; text-align: right; text-indent: 12em;border-bottom: 1px solid #cccccc; overflow: hidden; height: 25px; line-height: 25px; ">
	<a href="${pageContext.request.contextPath }/getIndexCommodity" style="float: left;font-size: 12px; color:blue;text-decoration: none" >返回首页</a>
	</div>
	<div>
		<div class="mainnav">
			<div style=" height:400px; width:490px; float:left;">
				<div class="login">
					<span></span>
					<div class="spanleft"></div>
					<div class="main">
						<div class="indiv">
							<h4>用户登陆</h4>
							<form action="Login" method="post">
								<s:token />
								<ul class="loginul">
									<li><label>邮&nbsp;&nbsp;箱：</label><input type="text"
										name="user.mailbox" /></li>
									<li><label>密&nbsp;&nbsp;码：</label><input type="password"
										name="user.password" /></li>
									<li><label>&nbsp;</label><input type="submit" id="alogin"
										value="" />&nbsp;<a href="user/ser_serve0">忘记密码？</a></li>
								</ul>
							</form>
						</div>
						<div class="share-con">
							<span class="tit">分享到：</span> <a title="复制链接"
								href="javascript:void(0)" onclick="copyLink()"><span
								class="copylink"></span>
							</a> <a title="分享到腾讯微博" href="javascript:void(0)"
								onclick="postToWb();"><span class="tengxunweibo"></span>
							</a> <a title="分享到新浪微博" href="javascript:void(0);"><span
								class="xinlang"></span>
							</a> <a title="分享到搜狐微博"
								href="javascript:void((function(s,d,e,r,l,p,t,z,c){var f='http://t.sohu.com/third/post.jsp?',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=660,height=470,left=',(s.width-660)/2,',top=',(s.height-470)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','','','utf-8'));"><span
								class="sohu"></span>
							</a> <a title="分享到网易微博" href="javascript:void(0);"><span
								class="wangyi"></span>
							</a> <a title="分享到Qzone空间" href="javascript:void(0);"><span
								class="qqzone"></span>
							</a> <a title="分享到朋友社区" href="javascript:void(0);"><span
								class="qqpengyou"></span>
							</a> <a title="分享到人人网" href="javascript:void(0);"><span
								class="renren"></span>
							</a> <a title="分享到开心网" href="javascript:void(0);"><span
								class="kaixin"></span>
							</a> <a title="分享到淘江湖" href="javascript:void(0);"><span
								class="taojianghu"></span>
							</a> <a title="分享到豆瓣" href="javascript:void(0);"><span
								class="douban"></span>
							</a> <a title="分享到百度收藏" href="javascript:void(0);"><span
								class="baidusoucang"></span>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div style=" height:400px; width:490px; float:left;">
				<div class="login">
					<span></span>
					<div class="spanleft"></div>
					<div class="main">
						<div class="indiv">
							<h4>注册新用户</h4>
							<form id="reg" action="Register" method="post">
							<s:token />
								<ul>
									<li><label>Email地址：</label><input id="mailbox" class="validate" type="text"
										name="user.mailbox" title="请输入有用的邮箱" /><div class="vall"></div>
									</li>
									<li><label>设定登陆密码：</label><input id="password" class="validate"
										type="password" name="user.password" title="密码必须大于5位小于12位" /><div class="vall"></div>
									</li>
									<li><label>再次输入密码：</label><input id="agpassword" class="validate"
										type="password" name="user.againpassword" title="请重复输入密码" /><div class="vall"></div>
									</li>
									<li><label>昵称：</label><input id="nickname" type="text" class="validate"
										name="user.nickname" title="昵称不能大于3个汉字" /><div class="vall"></div>
									</li>
									<li><label>验证码：</label><input id="verification1"
										class="validate" type="text" name="user.checking"
										style=" width:100px;" />&nbsp;<img id="ver" title="点击更换"
										src="${pageContext.request.contextPath }/user/getvalidate" /><div class="vall"></div>
									</li>
									<li id="treaty"><label></label><input type="checkbox" id="chack" />同意并接受注册协议</li>
									<li><label>&nbsp;</label><input type="submit"
										id="aregister" value="" />
									</li>
								</ul>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
