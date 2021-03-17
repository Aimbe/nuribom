<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


   

         <div class="subcategory-box">

          <div class="intro">

            <h4>MY PAGE</h4>
            <h4>마이페이지</h4>

          </div>

          <div class="sub-category-list">

            <ul class ="sub-category-ul">
              <li>
                <a href="${pageContext.request.contextPath}/member_reserve.bom" class="mySubTi">나의 예약</a>
                <i class="fas fa-chevron-circle-right fa-2x " ></i>

              </li>
              <li>
                
                <a href="${pageContext.request.contextPath}/member_review.bom" class="mySubTi">내가 쓴 후기</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
               
              </li>
              <li>
                
                <a href="${pageContext.request.contextPath}/member_free.bom" class="mySubTi">나의 마음공유</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              
              </li>
              <li>
               
                <a href="#" class="mySubTi">내가 쓴 댓글</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
             
              </li>
              <li>
               
                <a href="${pageContext.request.contextPath}/member_qna.bom"  class="mySubTi">나의 문의내역</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
             
              </li>
              <li>
               
                <a href="${pageContext.request.contextPath}/all_notifi.bom" class="mySubTi">알림 모아보기</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
             
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/member_like.bom"  class="mySubTi">관심 상담소</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/member_info_edit.bom" class="mySubTi">회원 정보 수정</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
            </ul>

          </div>


        </div>
         <div class="main-content">

          <div class="content-category-box">

              <h2 class="content-category-left"></h2>
              <div class="content-catefory-light">

                <!--우측 카테고리 경로-->
                  <ul>
                    <li><i class="fas fa-home fa-1x"></i></li>
                    <li>마이페이지</li>
                    <li id="mySubTitle">
    					 <c:choose>
                    	<c:when test="${subCategory eq 8 }">
                    		나의 예약
                    	</c:when>
                    	<c:when test="${subCategory eq 9 }">
                    		내가 쓴 후기
                    	</c:when>
                    	<c:when test="${subCategory eq 10 }">
                    		나의 마음 공유
                    	</c:when>
                    	
                   		<c:when test="${subCategory eq 11 }">
                    		나의 문의 내역
                    	</c:when>
                    	<c:when test="${subCategory eq 12 }">
                    		알림 모아보기
                    	</c:when>
                    	<c:when test="${subCategory eq 13 }">
                    		관심 상담소
                    	</c:when>
                    	<c:when test="${subCategory eq 14 }">
                    		회원 정보 수정
                    	</c:when>
                    </c:choose>                
	                    
                    </li>
                  </ul>


              </div> 



          </div>
          
  <script>
   
   <c:if test="${subCategory eq 8 }">
		
   $('.sub-category-ul li:nth-child(1)').addClass('current-category');
   $('.sub-category-ul li:nth-child(1)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${ subCategory ne 8 }">
	
   $('.sub-category-ul li:nth-child(1)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(1)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 9 }">
	
   $('.sub-category-ul li:nth-child(2)').addClass('current-category');
   $('.sub-category-ul li:nth-child(2)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 9 }">
	
   $('.sub-category-ul li:nth-child(2)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(2)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 10 }">
	
   $('.sub-category-ul li:nth-child(3)').addClass('current-category');
   $('.sub-category-ul li:nth-child(3)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 10 }">
	
   $('.sub-category-ul li:nth-child(3)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(3)>i').removeClass('sel-category');
   
   </c:if>   
      
   
   <c:if test="${subCategory eq 11 }">
	
   $('.sub-category-ul li:nth-child(5)').addClass('current-category');
   $('.sub-category-ul li:nth-child(5)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 11 }">
	
   $('.sub-category-ul li:nth-child(5)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(5)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 12 }">
	
   $('.sub-category-ul li:nth-child(6)').addClass('current-category');
   $('.sub-category-ul li:nth-child(6)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 12 }">
	
   $('.sub-category-ul li:nth-child(6)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(6)>i').removeClass('sel-category');
   
   </c:if>   
   
   <c:if test="${subCategory eq 13 }">
	
   $('.sub-category-ul li:nth-child(7)').addClass('current-category');
   $('.sub-category-ul li:nth-child(7)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 13 }">
	
   $('.sub-category-ul li:nth-child(7)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(7)>i').removeClass('sel-category');
   
   </c:if>   
   
   <c:if test="${subCategory eq 14 }">
	
   $('.sub-category-ul li:nth-child(8)').addClass('current-category');
   $('.sub-category-ul li:nth-child(8)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 14 }">
	
   $('.sub-category-ul li:nth-child(8)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(8)>i').removeClass('sel-category');
   
   </c:if>   
      
   
   </script>	
		
          
        