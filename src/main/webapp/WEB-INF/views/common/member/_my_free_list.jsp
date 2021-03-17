<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <table class="user-all-list">
                <tr>
                  <th></th>
                  <th>No.</th>
                  <th>제목</th>
                  
                  <th>조회수</th>
                  <th>작성일</th>
                  
                  
                </tr>
                
		<c:forEach var="at" items="${atList}">
				
               <tr onclick="showDetailPage('${at.id}')">
                  <td onclick="event.cancelBubble=true">
                  
                  <input type="checkbox" name="check_at" value="${at.id}">
                  
                  </td>
                  <td class="user-list-number"><c:out value="${at.id}" default="0"/></td>
                  <td><a href="#"><c:out value="${at.title}" default="0"/></a>
                  
                  </td>
                  
                  <td><c:out value="${at.view}" default="0"/></td>
                  <td><c:out value="${at.createdAt}" default="0"/></td>
                  
                  
                </tr>
				</c:forEach>
                

            </table>
            
<script>
	function showDetailPage(bdId) {
		//window.location.href = URL;
		location.href = '${pageContext.request.contextPath}/free_board_show_page.bom?bdId='+bdId+'&pg=${pn}';
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
	
	

    
  

</script>    
            