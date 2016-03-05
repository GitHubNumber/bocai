<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional.dtd//EN">
<html>
<head>
<title>log</title>
<script charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/addLog.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/log.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogs/blogs2.css" type="text/css"></link>
<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[id="content1"]', {
					resizeType : 0,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|','bold', 'italic', 'underline','forecolor', 
						 '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons']
				});
			});
</script>
</head>
<body>
<!-- 网页top -->
	<jsp:include page="top.jsp"></jsp:include>
	<div class="blogs_main">
		<!-- 网页heard -->
		<jsp:include page="heard.jsp"></jsp:include>
		<div class="log2 log1 margintop">
                <h5>发表日志</h5>
         </div>
	<div class="SendMetteredit">
   		<form action="${pageContext.request.contextPath }/blogs/log!addLog.action" method="post" style="float: left;">
    	<s:token/>
    	<div class="SendTop">
        <p>还可以输入80个字符</p>
        <select name="topicTitle.topicRank">
        <option value="10">个人日记</option>
        <option value="11">网络吐槽</option>
        <option value="12">怀念回忆</option>
        </select>
        <input name="blogsLog.log_title" class="middle"  type="text" />
        </div>
        <div class="edit">
            <li><textarea id="content1" name="blogsLog.log" style="width:978px;height:400px;visibility:hidden;"></textarea></li>
            <li class="sendbutton">
            	<ul>
                	<li><input type="submit" value="发&nbsp;表" /></li>
                </ul>
            </li>
		<input type="hidden" name ="blogsLog.user_id" value="1" > 
		</div>
	</form>
    </div>
	</div>
	<!-- 网页底部 -->
	<jsp:include page="../common/end.jsp"/>
</body>
</html>
