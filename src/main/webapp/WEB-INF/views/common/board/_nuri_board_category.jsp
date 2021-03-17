<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- subcategory를 받아서 오게됨. -->
   
       <div class="subcategory-box">

          <div class="intro">

            <h4>INTRODUCE</h4>
            <h4>누리봄 살펴보기</h4>

          </div>

          <div class="sub-category-list">

            <ul class="sub-category-ul">
              <li >
                <a href="${pageContext.request.contextPath}/nuri_story.bom">누리봄 스토리</a>
                <i class="fas fa-chevron-circle-right fa-2x "></i>

              </li>
              <li>
                <a href="${pageContext.request.contextPath}/nuri_board_list.bom">도움이 되는 글</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/faq_list.bom">자주 묻는 질문</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/center_contacts.bom">국가 센터 상담 연결</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/question_list.bom">누리봄 QnA</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
              
            </ul>

          </div>

		</div>
		
		   <div class="main-content">

          <div class="content-category-box">

              <h2 class="content-category-left">누리봄 살펴보기</h2>
              <div class="content-catefory-light">

                <!--우측 카테고리 경로-->
                  <ul>
                    <li><i class="fas fa-home fa-1x"></i></li>
                    <li> 누리봄 살펴보기</li>
                    <li>
                    <c:choose>
                    	<c:when test="${subCategory eq 3 }">
                    		누리봄 스토리
                    	</c:when>
                    	<c:when test="${subCategory eq 4 }">
                    		도움이 되는 글
                    	</c:when>
                    	<c:when test="${subCategory eq 5 }">
                    		자주 묻는 질문
                    	</c:when>
                    	<c:when test="${subCategory eq 6 }">
                    		국가 센터 상담 연결
                    	</c:when>
                   		<c:when test="${subCategory eq 7 }">
                    		누리봄 QnA
                    	</c:when>
                    </c:choose>
                    </li>
                  </ul>


              </div> 
		</div>
		
	  <script>
   
   <c:if test="${subCategory eq 3 }">
		
   $('.sub-category-ul li:nth-child(1)').addClass('current-category');
   $('.sub-category-ul li:nth-child(1)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${ subCategory ne 3 }">
	
   $('.sub-category-ul li:nth-child(1)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(1)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 4 }">
	
   $('.sub-category-ul li:nth-child(2)').addClass('current-category');
   $('.sub-category-ul li:nth-child(2)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 4 }">
	
   $('.sub-category-ul li:nth-child(2)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(2)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 5 }">
	
   $('.sub-category-ul li:nth-child(3)').addClass('current-category');
   $('.sub-category-ul li:nth-child(3)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 5 }">
	
   $('.sub-category-ul li:nth-child(3)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(3)>i').removeClass('sel-category');
   
   </c:if>   
      
   
   <c:if test="${subCategory eq 6 }">
	
   $('.sub-category-ul li:nth-child(4)').addClass('current-category');
   $('.sub-category-ul li:nth-child(4)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 6 }">
	
   $('.sub-category-ul li:nth-child(4)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(4)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 7 }">
	
   $('.sub-category-ul li:nth-child(5)').addClass('current-category');
   $('.sub-category-ul li:nth-child(5)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 7 }">
	
   $('.sub-category-ul li:nth-child(5)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(5)>i').removeClass('sel-category');
   
   </c:if>   
      
   
   </script>	
		