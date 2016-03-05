<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>mango注册</title>
	<link href="${pageContext.request.contextPath }/css/user/register.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		var params;
		var urlaction;
		var op;
		var oop;
		var right="<img src='${pageContext.request.contextPath }/images/common/12.gif' />";
		var wrong="<img src='${pageContext.request.contextPath }/images/common/13.gif' />";
		var nicknamechick=/[\u4e00-\u9fa5]+/;
		$(".chick").click(function(){
			$(this).attr("src","${pageContext.request.contextPath }/user/operate!getvalidate?a="+Math.random());
		});
		
		$(".validate").blur(function() {
			op=$(this);
			if(op.attr("id")=="mailbox"){
				params= {
						"user.mailbox" : op.val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/AjaxValue!checkmailbox";
			}
			if(op.attr("id")=="checking"){
				params= {
						"user.checking" : op.val()
					};
				urlaction="${pageContext.request.contextPath }/Chick/AjaxValue!checkChecking";
			}
			
				Chickajax(op,params,urlaction);
			});
		//本地检测
		$(".locvalidate").blur(function(){
			op=$(this);
			if(op.attr("id")=="password"){
				oop=op.nextAll("div .vall").empty();
				var password=$("#password").val();
				if(password.replace(/\s+/g,'') == ''){
					 oop.html("<font color='#FF0000'>密码不能为空</font>");
				}else if(password.length>8&&password.length<16){
					 oop.html(right);
				}else{
					 oop.html("<font color='#FF0000'>密码必须大于8位小于16位</font>");
				};
			}
			if(op.attr("id")=="againpassword"){
				oop=op.nextAll("div .vall").empty();
				var password=$("#password").val();
				var againpassword=$("#againpassword").val();
				if(againpassword.replace(/\s+/g,'') == ''){ 
					oop.html(wrong);
				}else if(password == againpassword){
					oop.html(right);
				}else{
				oop.html("<font color='#FF0000'>两次密码输入不同</font>");
				};
			}
			if(op.attr("id")=="nickname"){
				oop=op.nextAll("div .vall").empty();
				var nickname=$("#nickname").val();
				if(nickname.replace(/\s+/g,'') == ''){
					
					 oop.html("<font color='#FF0000'>昵称不能为空</font>");
				}else if(nickname.length<2||nickname.length>8){
					
					oop.html("<font color='#FF0000'>昵称必须大于2位小于10</font>");	
				}else if(nicknamechick.test(nickname)){
				oop.html(right);
				}
			}
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
				$(op).nextAll("div .vall").empty().html("<img src='${pageContext.request.contextPath }/images/common/"+obj.promptResult.imagename+".gif' />"+obj.promptResult.message).show(100);
			}, 
			error : function(json) {
				alert("json=" + json);
				return false;
			}
		});
	}

</script>
	
</head>
<body>
<div class="allpage">
    <div class="maintop">
    
        	<div><img alt="" src="${pageContext.request.contextPath }/images/user/reg_logo.png" /><br /></div>
        	<div class="top">
        	<span><a href="${pageContext.request.contextPath }/bbs/index!topicTitle">返回首页</a></span>
            <h3>用户注册</h3>
            <div/>
    </div>
	<div class="middle">
   			<div class="left">
            	<form action="${pageContext.request.contextPath }/user/register.action" method="post">
            	<s:token/>
            	<ul>
                	<li><label>邮箱：</label><input id="mailbox" class="validate"  type="text" name="user.mailbox"/><div class="vall">[什么是Mango通行证]</div></li>
                    
                    <li><label>密码：</label><input id="password" class="locvalidate" type="password" name="user.password"/><div class="vall">4-12个字符</div></li>
                    
                    <li><label>确认密码：</label><input id="againpassword" class="locvalidate" type="password" name="user.againpassword"/><div class="vall"></div></li>
                    
                    <li><label>昵称：</label><input  id="nickname" class="locvalidate" type="text" name="user.nickname"/><div class="vall"></div></li>
                    
                    <li><label>验证码：</label><input id="checking" class="validate" type="text" name="user.checking"/><img class="chick" src="${pageContext.request.contextPath }/user/operate!getvalidate" title="点击更换"/><div class="vall"><div/></li>
                    <li class="butt"><label>&nbsp;</label><input type="submit" value="注册"></input><input type="button" value="取消" id="but"></input></li>
                </ul>
             </form>
            </div>
            <div class="right">
            	<ul>
                	<li>在Mango，你可以</li>
                    <li><span>·</span>自由地分享你的精彩视频</li>
                    <li><span>·</span>和好友七嘴八舌的扮演"影评者"</li>
                    <li><span>·</span>交更多的朋友</li>
                    <li><span>·</span>享受更精彩的宽频乐趣</li>
                </ul>
            </div>
	</div>
</div>
<%@include file="../common/end.jsp" %>
  </body>
</html>
