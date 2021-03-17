<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- subcategory를 받아서 오게됨. -->
   
       <div class="subcategory-box">

          <div class="intro">

			   <h4>INTRODUCE</h4>
	            <h4>마음공유</h4>
		
          </div>

          <div class="sub-category-list">

            <ul class="sub-category-ul">
            
              <li> <!--  class="current-category" -->
                <a href="free_board_list.bom">자유게시판</a>
                <i class="fas fa-chevron-circle-right fa-2x "></i> <!-- sel-category -->

              </li>
              <li>
                <a href="all-review-page.bom">리뷰 모음</a>
                <i class="fas fa-chevron-circle-right fa-2x"></i>
              </li>
              
         
              
              
              
            </ul>

          </div>


        </div>
        <div class="main-content">

          <div class="content-category-box">

              <h2 class="content-category-left">마음공유</h2>
              <div class="content-catefory-light">

                <!--우측 카테고리 경로-->
                  <ul>
                    <li><i class="fas fa-home fa-1x"></i></li>
                    <li> 마음공유</li>
                    <li>
                    <c:choose>
                    	<c:when test="${subCategory eq 1 }">
                    		자유게시판
                    	</c:when>
                    	<c:when test="${subCategory eq 2 }">
                    		리뷰모음
                    	</c:when>
                    
                    </c:choose>
                    </li>
                  </ul>


              </div> 
		</div>
		
	  <script>
   
   <c:if test="${subCategory eq 1 }">
		
   $('.sub-category-ul li:nth-child(1)').addClass('current-category');
   $('.sub-category-ul li:nth-child(1)>i').addClass('sel-category');

	

//    alert('${subCategory}');
   
   </c:if>   
   
   <c:if test="${ subCategory ne 1 }">
	
   $('.sub-category-ul li:nth-child(1)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(1)>i').removeClass('sel-category');
   
   </c:if>   
      
   <c:if test="${subCategory eq 2 }">
	
   $('.sub-category-ul li:nth-child(2)').addClass('current-category');
   $('.sub-category-ul li:nth-child(2)>i').addClass('sel-category');

   </c:if>   
   
   <c:if test="${subCategory ne 2 }">
	
   $('.sub-category-ul li:nth-child(2)').removeClass('current-category');
   $('.sub-category-ul li:nth-child(2)>i').removeClass('sel-category');
   
   </c:if>   
      
    
   
   
   </script>	
		