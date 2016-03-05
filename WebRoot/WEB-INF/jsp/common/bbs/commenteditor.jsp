<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<script charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script>
	$(document).ready(function(){
	if($("#text1").text()==1){
		$(".commentrightdis").css("display","none");
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[id="content1"]', {
					resizeType : 0,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'bold', 'italic', 'underline','forecolor', 
						 '|','emoticons']
				});
			});
	}
});
</script>
<div class="commentedit">
    	<ul>
        <li class="commentleft">
        	<img src="${pageContext.request.contextPath }/images/user/userpic.png" width="130" height="130" />
        </li>
        <li class="commentright">
        	<c:if test="${landinguser.power!=0}">
	        <div style="display:none;" id="text1">1</div>
	        </c:if>
	        <c:if test="${landinguser.power==0}">
	        <div style="display:none;" id="text1">0</div>
	        </c:if>
        	<div class="commentrightdis">
            	<h3>您需要登录后才可以回帖&nbsp;<a href="javascript:void(0)" onclick="showPanel();" style="font-weight: bold;">登陆</a>|<a href="${pageContext.request.contextPath }/user/getregister.action">注册</a></h3>
            </div>
            <ul>
            	<form action="${pageContext.request.contextPath }/bbs/addcomment" method="post">
            	<input type="hidden" name="topicTitle.id" value="${queryResult.resultlist.get(0).topictitleid.id }">
            	<s:token/>
            	<li class="commentright1"><textarea id="content1" name="topicComment.commentContent" style="width:750px;height:150px; border:0px;visibility:hidden;z-index:11;"></textarea>
                </li>
            	<li class="commentright2">
               	<input type="submit" value=" " />
                </li>
                </from>
            </ul>
        </li>
        </ul>
</div>
