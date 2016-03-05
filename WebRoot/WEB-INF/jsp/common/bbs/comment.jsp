<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--内容-->
	
   <div class="metternav">
    	<ul>
        <li class="titleli">查看: ${queryResult.resultlist.get(0).topictitleid.chickNum }|回复: ${queryResult.resultlist.get(0).topictitleid.answerNum }</li>
        <li class="titleli1"><a>${queryResult.resultlist.get(0).topictitleid.topic }</a></li>
        </ul>
    </div>
    <div class="metter">
    		<c:forEach var="result" items="${queryResult.resultlist }" varStatus="nowpage">
    		<div class="metter1">
                <div class="li1">
                <div class="show_user_info" style="display:none"></div>
                	<ul>
                    	<li class="li1first"><h3><a href="${pageContext.request.contextPath }/blogs/log?user.id=${result.userid.id}">${result.userid.nickname }</a></h3></li>
                    	<div id="show_user_info" style="display:none;"></div>
                        <li class="headpic">
                        <img src="${pageContext.request.contextPath }/images/user/userpic.png" id="${result.userid.id }" class="mouse_img" width="130" height="130" />
                        </li>
                        <li class="userinfo">
                        <div>
                        	<ul>
                            <li>高级玩家</li>
                            <li><a class="rank"></a></li>
                            <li><label>贡献度</label>30</li>
                            <li><label>精华</label>30</li>
                            <li><label>帖子</label>30</li>
                            <li><label>积分</label>30</li>
                            <li><label>贡献度</label>30</li>
                            <li>
                            <label><a class="gettoblogs"></a>串个门</label><a class="addfriend"></a>加好友
                            </li>
                            <li>
                            <label><a class="call"></a>打招呼</label><a class="send"></a>发信息
                            </li>
                            </ul>
                        </div>
                        </li>
                    </ul>
                </div>
                <div class="li2">
                	<ul>
                    	<li class="li2first"><span>#${ (queryResult.nowpage-1)*10+nowpage.count}</span><a class="img"></a>&nbsp;发表：${result.answertime }</li>
                        <li class="li2second">
                        <div class="mettermain">
                        	<div class="li2third">
                            ${result.commentContent }
                            </div>
                            <div class="li24">
                            <ul>
                            <li><a class="answer"></a>回复</li>
                            <li><a class="praise"></a>赞一个</li>
                            <li><a class="bad"></a>踩一个</li>
                            </ul>
                            </div>
                        </div>
                        </li>
                    </ul>
                </div>
            </div>
            </c:forEach>
    </div>