<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
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
			
			$(function(){
				//选项切换
					$(".option_type").change(function(){
					var type=$(".option_type").val();
					if(type==10){
					$(".edit").css("display","block");
					$(".ck_voteoption").css("display","none");
					}
					if(type==11){
					$(".edit").css("display","none");
					$(".ck_voteoption").css("display","block");
					}
					});
					
				//搜索空白
					$(".seachma").focus(function(){
					var comment=$(".seachma").val();
					if(comment=="请输入搜索内容"){
					$(".seachma").val("");
					}
					});
					
					$(".seachma").blur(function(){
					var comment=$(".seachma").val();
					if(comment==""){
					$(".seachma").val("请输入搜索内容");
					}
					});
					$(".add_option a").click(function(){
						$(".add_option").before("<li><label></label><input type='text' name='voteoption'/></li>");
					});
					$(".ck_voteoption_right a").click(function(){
							if($(".ck_text_li").css("display")=="none"){
								$(".ck_voteoption_right a").text("-取消投票说明");
								
								$(".ck_text_li").css("display","block");
							}else{
								$(".ck_voteoption_right a").text("+添加投票说明");
								$(".ck_text_text").text("");
								$(".ck_text_li").css("display","none");
							};
					});
				
				});
</script>
<!-- 编辑器-->

<div class="SendMetteredit">
   		
   		<form action="${pageContext.request.contextPath }/bbs/bulidTopic.action" method="post" >
    	<s:token/>
    	<div class="SendTop">
        <p>还可以输入80个字符</p>
        <select name="topicTitle.topicRank" class="option_type">
        <option value="10">普通新帖</option>
        <option value="11">发布投票</option>
        <option value="12">悬赏发帖</option>
        </select>
        <input name="topicTitle.topic"  type="text" />
        </div>
        <div class="edit">
            <ul><li><textarea id="content1" name="topicComment.commentContent" style="width:978px;height:450px;visibility:hidden;"></textarea></li></ul>
        </div>
           <!-- 发起投票 -->
       <div class="ck_voteoption" style="display: none;">
       <!--left-->
	       <div class="ck_voteoption_left">
		       <ul>
		       <li><label>投票选项：</label><input type="text" name="voteoption"/></li>
		       <li><label></label><input type="text" name="voteoption"/></li>
		       <li><label></label><input type="text" name="voteoption"/></li>
		       <li><label></label><input type="text" name="voteoption"/></li>
		       <li class="add_option"><label></label><a style="cursor: pointer;">+添加更多选项</a></li>
		       <li><label>投票方式：</label><input  type="radio" name="optiontype" id="ck_olnyone" checked="checked"/>单选<input type="radio" name="optiontype" id="ck_olnyone"/>多选</li>
		       </ul>
	       </div>
       <!--right-->
	       <div class="ck_voteoption_right">
		       <ul>
		       <li><a style="cursor: pointer;">+添加投票说明</a></li>
		       <li class="ck_text_li" style="display: none;"><textarea name="" class="ck_text_text"></textarea></li>
		       </ul>
	       </div>
       </div>
      
        <!-- 按钮 -->
	    <div class="sendbutton">
		    <ul>
		       	<li><input type="submit" value="发&nbsp;表" /></li>
		       	<li><input id="cancel" type="button" value="返&nbsp;回" /></li>
		    </ul>
	    </div>
	     </form>
       
</div>
 