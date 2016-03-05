<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="titlematter">
    	<div class="titletop">
        <table class="table1">
            <tr>
              <td width="5%">&nbsp;</td>
              <td width="55%">标题</td>
              <td width="15%">作者</td>
              <td width="10%">回复/查看</td>
              <td width="15%">最后发表</td>
            </tr>
          </table>
        </div>
        <div class="tmatter">
        <table id="table2" width="980" border="0" style="table-layout:fixed;" >
        	<c:if test="${queryResult.totalrecord==0 }">
        		<tr>
        			<td style="text-align: center; color: #333;">暂无发帖</td>
        		</tr>
        	</c:if>
        	<c:forEach var="art" items="${queryResult.resultlist}">
              <tr id="tr1">
                  <td width="5%"><li class="titleimg"><img src="${pageContext.request.contextPath }/images/bbs/pagerank/tl1.gif" width="18px" height="18px" /></li></td>
                  <td width="55%"><h4 id="spill"><a href="${pageContext.request.contextPath }/bbs/get_comment.action?topicTitle.id=${art.topictitleid.id }">${art.topictitleid.topic }</a></h4></td>
                  <td width="15%">
                  <ul>
                  <li><a href="#">${art.topictitleid.userid.nickname }</a></li>
                  <li id="time">${art.topictitleid.releaseTime }</li>
                  </ul>
                  </td>
                  <td width="10%">
                  <ul>
                  <li>${art.topictitleid.answerNum }</li>
                  <li>${art.topictitleid.chickNum }</li>
                  </ul>
                  </td>
                  <td width="15%">
                  <ul>
                  <li><a href="#">${art.userid.nickname }</a></li>
                  <li id="time">${art.answertime }</li>
                  </ul>
                 </td>
              </tr>
              </c:forEach>
           </table>
        </div>
    </div>