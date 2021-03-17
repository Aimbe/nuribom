<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <div class="qna-all-list">


			
			
              <table class="qna-list-table">
				<c:forEach var="at" items="${atList}">
				<tr>
				
                <tr onclick="goCenterDetail('${at.reviewCenterId}')">
                  <td onclick="event.cancelBubble=true">
                  <input type="checkbox" name="check_at" value="${at.reviewId}"></td>
                  <td>

                   
                    
                    <h6 class="qna-category">
                    	[<c:out value="${at.reviewCenterName }" default="0"/>]
                    </h6>
                    
                    <h6 class="qna-title">                   
                      <p><c:out value="${at.reviewContent}" default="없음"/>
                    </p>
                    </h6>
                    
                    

                  </td>
                  <td onclick="event.cancelBubble=true">  

                   
                    <p class="qna-date"><fmt:formatDate value="${at.reviewCreatedAt}" 
						pattern="yyyy년 MM월 dd일" /></p>
                    <p class="qna-edit-btn"><button type="button" onclick="editReview(${at.reviewId})" class="qna-edit">수정</button></p>
                       <script>
                    function editReview(id){
                    	console.log('마이페이지 리뷰수정 클릭');
                		if ( confirm("수정하시겠습니까?") == true){ 
                			location.href="${pageContext.request.contextPath}/center_detail_edit.bom?rvId="+id;
                		}
                    }
                    </script>
                  </td>
                  <td>

                    <h4 class="review-star">
                      <div class="user-rate-star">
                       <c:forEach var="i" begin="1" end="${at.reviewRate}">
                           <i class="fas fa-star fa-2x"></i>
						</c:forEach>
                        <h4 class="avg-rate"><c:out value="${at.reviewRate}" default="0"/></h4>
                        
                    </div>
                    </h4>
                    

                  </td>
                </tr> 
				</c:forEach>

               
              </table>
			

            </div><!--qna-all-list-->
            
<script>
	function goCenterDetail(ctId) {
		//window.location.href = URL;
		location.href = '${pageContext.request.contextPath}/center_detail.bom?ctId='+ctId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
	
	

    
  

</script>    
                        