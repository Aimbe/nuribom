<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<c:if test="${not empty bdSize}">
	<c:set var="num" value="${bdMaxSize -((pn-1)*10)}"/>
	 <table class="user-all-list">

                <tr>
                  <th>No.</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>조회수</th>
                  <th>작성일</th>
                  
                </tr>
				 <c:forEach var="bd" items="${bdList}" varStatus="vs">
				 	<tr onclick="showDetailPage('${bd.id}')">
	                  <td class="user-list-number">
	                	<c:out value="${num-vs.index }"/>  	
	                  </td>
	                  <td><a href="#">${bd.title}</a></td>
	                  <td>${bd.writer}</td>
	                  <td>${bd.view}</td>
	                 <td><fmt:formatDate value="${bd.createdAt}" pattern="yyyy.MM.dd"/> </td>
	                  
	                </tr>
				 </c:forEach>
               

     </table>
</c:if>      

<c:if test="${bdSize eq 0}">
   <h2 class="none-board">검색 키워드와 일치하는 게시글이 없습니다.</h2>
</c:if>
<script>
	function showDetailPage(bdId) {
		//window.location.href = URL;
		location.href = 'free_board_show_page.bom?bdId='+bdId+'&pg=${pn}';
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
</script>    
            
            
            