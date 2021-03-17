<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_onlyCtag.jsp" %>

<style>

.none_nuribom{

 font-size:1.9rem;
 color:#7f7872;
 position: absolute;
 top:165px;
 margin: 180px;
}


</style>

 <h3>누리봄이 전하는 글</h3>
<h4>너무 감동적이여서 심금을 울릴만한 그런 멘트</h4>
<span class="today-line"></span>

<c:choose>
<c:when test="${nrSize gt 0}"> 

	<ul class="today-board-list">
	 <c:forEach var="nr" items="${nrList}" varStatus="vs">
	 
	 <c:if test='${ vs.index lt 4}'><c:set var="pg" value="1"/></c:if>
	 <c:if test='${ vs.index ge 4}'><c:set var="pg" value="2"/></c:if>
	 
      <li>
        <a href="nuri_board_show.bom?bdId=${nr.id}&pg=${pg}">${nr.title}</a>
        <span class="today-board-date">  <fmt:formatDate value="${nr.createdAt}" pattern="yyyy.MM.dd"/></span>
      </li>
      </c:forEach>
    </ul>

</c:when>
<c:when test="${nrSize eq 0}">

	<h6 class="none_nuribom">등록된 게시글이 없습니다.</h6>

</c:when>


</c:choose>
  <a href="${pageContext.request.contextPath}/nuri_board_list.bom" class="today-board-all-view">전체보기+</a>
