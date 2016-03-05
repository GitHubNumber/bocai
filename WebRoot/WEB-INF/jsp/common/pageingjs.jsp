<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
		var now=${queryResult.nowpage };
		var alltitle=${queryResult.totalrecord };
		var allpage=Math.floor( alltitle%25!=0? (alltitle/25+1):(alltitle/25));
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
				pageing=pageing+"<li><a href='${pageContext.request.contextPath }/bbs/index!topicTitle.action?queryResult.nowpage="+i+"'>"+i+"</a></li>";
			};
		}
		$(" #shouye").after(pageing);
		$(" #gettosecond").children("a").attr("href","${pageContext.request.contextPath }/bbs/index!topicTitle.action?pageNum="+second);
		
		$(" #time").html($("#time").text().substring($("#time").text(),$("#time").text().length-2));
	});
</script>