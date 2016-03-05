<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/bbs/login.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/common/share.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/share.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	if($("#text").text()==1){
		parent.document.getElementById("Panel").style.display = 'none';
	 	parent.document.getElementById("fade").style.display='none';
	 	parent.location.reload();
	}
	$(".zhanghao").focus(function(){
		$("#prompt").empty();
	});
});
</script>
</head>
<body>
		<c:if test="${landinguser.power!=0}">
        <div style="display:none;" id="text">1</div>
        </c:if>
        <c:if test="${landinguser.power==0}">
        <div style="display:none;" id="text">0</div>
        </c:if>
    <div class="login_main">
    	<div class="cennav">
       <table width="384" border="0" height="150">
       <form action="<s:url action="/user/login.action"></s:url>" id="form1" method="post">
       <s:token/>
       	  <tr>
            <td width="80"></td>
            <td width="300"><div id="prompt">${promptresult.message }</div></td>
          </tr>
          <tr>
            <td>账号</td>
            <td><input name="user.mailbox" type="text" class="zhanghao" /></td>
          </tr>
          <tr>
            <td>密码</td>
            <td><input name="user.password" type="password" class="zhanghao" /></td>
         </tr>
          <tr>
            <td colspan="2"><li class="zhuangtai"><input id="check" type="checkbox" value="" />记住我的登陆状态</li></td>
          </tr>
          <tr>
            <td colspan="2"><span><a href="#"> 忘记密码？</a></span><a href="javascript:void(0)" onclick="document.getElementById('form1').submit()" id="denglu">登 录</a></td>
         </tr>
         <form>
        </table>
      </div>
        <div class="endnav">
        <li>还没有本论坛账号？<a href="#">立即注册</a></li>
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
</body> 
</html>