<%@page import="java.util.List"%>
<%@page import="com.nuriweb.mybom.model.vo.ReviewVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자유게시판</title>
<link href="${pageContext.request.contextPath}/resources/css/board/user-detial.css" rel="stylesheet" type="text/css" />
 <link href=" ${pageContext.request.contextPath}/resources/css/review/all-review.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="../common/_header.jsp" %>


	<div id="main">
	<!-- 레이아웃 비주얼 -->
	 <%@include file="../common/board/_visual.jsp" %>

	<!-- 카테고리 레이아웃 -->
	 <div class="container submain-content-box">
 	 <%@include file="../common/board/_mind_board_category.jsp" %>

          <div class="real-content">
  <c:forEach var="rv" items="${rvList}">
 
		          <div class="review-item-box">

                <div class="review-item-img">                
                 
                    <img src=" ${pageContext.request.contextPath}/resources/images/center/${rv.reviewCenterImagePath}" 
                      width=322px height="290px" 
                    alt="center-item-img1">
                </div>

                <div class="review-item-content-box">

                  <div class="review-item-info">
                   
                    <div class="review-info-left">
                      <h3 class="review-center-title">
                        ${rv.reviewCenterName }
                      </h3>
                      <i class="fas fa-map-marker-alt fa-2x center-map-icon"></i>
                      <h4 class="review-center-address">
                  ${rv.reviewCenterAddress}
                      </h4>
                    </div>

                    <div class="review-user-like">

                    </div>

                  </div><!--info-->

                  <div class="review-user-rate-box">
                    <div class="review-user-top">
                      <div class="user-rate-star">
                      
                      <c:forEach var="i" begin="1" end="${rv.reviewRate}">
                           <i class="fas fa-star fa-2x"></i>
						</c:forEach>
                        <h4 class="avg-rate">${rv.reviewRate}</h4>
                    </div>

<%
// List<ReviewVO> rev = (List<ReviewVO>)pageContext.findAttribute("rvList");
// String s = new SimpleDateFormat("yyyy-MM-dd").format(rev.get(0).getReviewCreatedAt());  
%>
                      <h4 class="review-user-info">
                        ${rv.reviewUserName} &#47; ${rv.reviewDay} 
                      </h4>
                    </div><!--user-top-->

                    <div class="review-user-comment">

                      <p>${rv.reviewContent}</p>

                    </div><!--user-comment-->

                      <h3 class="more-center-h3">
                        <a href="center_detail.bom?ctId=${rv.reviewCenterId}"  class="move-center-page">
                        이 상담소 더보기 
                        <i class="fas fa-long-arrow-alt-right fa-1x center-move-icon"></i>
                        </a>
                    
                      </h3>

                  </div><!--rate-box-->
               </div><!--content-box-->
             </div><!--all-review-page-box-->
 </c:forEach>
 <script>
 	var page = <c:out value='${pg}'/>;			
	var finalPage = <c:out value='${maxPg}'/>; 	  	 
	var siteLink = 	"${pageContext.request.contextPath}/all-review-page.bom?pg=";										 
		function showFinalPage(){
			location.href=siteLink+finalPage;
		}
		function firstPage(){
			location.href=siteLink+1;
		}
		function prevPage(){
			location.href=siteLink+(page-1);
		}
		
		function prevPage2(){
			location.href=siteLink+(page-2);
		}
		function prevPage3(){
			location.href=siteLink+(page-3);
		}
		function prevPage4(){
			location.href=siteLink+(page-4);
		}
		
		function nextPage(){
				location.href=siteLink+(page+1);
		}
			
		function nextPage2(){
				location.href=siteLink+(page+2);
		}
		function nextPage3(){
			location.href=siteLink+(page+3);
		}	
		function nextPage4(){
			location.href=siteLink+(page+4);
		}	
			
		</script>
		
<div class="all-review-board-page">
<!-- disabled  -->
<c:if test ="${maxPg>=5 }">
<c:if test ="${pg == 1}">  	
<button type="button" class="page-btn btndisable" disabled>  <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn btndisable" disabled> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btns all-review-sel-page"> 1</button>
  <button type="button" class="page-btn" onclick="nextPage()" > 2</button>
  <button type="button" class="page-btn" onclick="nextPage2()"> 3</button>
  <button type="button" class="page-btn" onclick="nextPage3()"> 4</button>
  <button type="button" class="page-btn" onclick="nextPage4()"> 5</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage();"><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
 </c:if>
  
 <c:if test ="${pg == 2}">  
<button type="button" class="page-btn" onclick="firstPage()"> <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"> 1</button>
  <button type="button" class="page-btns all-review-sel-page"> 2</button>
  <button type="button" class="page-btn" onclick="nextPage()"> 3</button>
  <button type="button" class="page-btn" onclick="nextPage2()"> 4</button>
  <button type="button" class="page-btn" onclick="nextPage3()"> 5</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage();"><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  </c:if>
  
	<c:if test ="${pg>2 && pg<maxPg-1}">  
	
  <button type="button" class="page-btn"  onclick="firstPage()">  <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn " onclick="prevPage2()">${pg-2}</button>
  <button type="button" class="page-btn " onclick="prevPage()">${pg-1}</button>
  <button type="button" class="page-btns all-review-sel-page">${pg}</button>
  <button type="button" class="page-btn" onclick="nextPage()">${pg+1}</button>
  <button type="button" class="page-btn" onclick="nextPage2()">${pg+2}</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  <button type="button" class="page-btn "  onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
   </c:if>
   
   <c:if test ="${pg == (maxPg-1)}">  
  <button type="button" class="page-btn"  onclick="firstPage()">  <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn " onclick="prevPage3()">${pg-3}</button>
  <button type="button" class="page-btn " onclick="prevPage2()">${pg-2}</button>
  <button type="button" class="page-btn " onclick="prevPage()">${pg-1}</button>
  <button type="button" class="page-btns all-review-sel-page">${pg}</button>
  <button type="button" class="page-btn" onclick="nextPage()">${pg+1}</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  <button type="button" class="page-btn "  onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
   </c:if>
   
   <c:if test ="${pg == maxPg}">  
  <button type="button" class="page-btn" onclick="firstPage()"> <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()">  <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
  <button type="button" class="page-btn " onclick="prevPage4()">${pg-4}</button>
  <button type="button" class="page-btn " onclick="prevPage3()">${pg-3}</button>
  <button type="button" class="page-btn " onclick="prevPage2()">${pg-2}</button>
  <button type="button" class="page-btn " onclick="prevPage()">${pg-1}</button>
  <button type="button" class="page-btns all-review-sel-page">${pg}</button>
  <button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  <button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  </c:if>
 </c:if>
 
 
 <c:if test ="${maxPg<5}">
 
 <c:if test ="${pg==1 }">
 	<button type="button" class="page-btn btndisable " disabled>  <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
 	<button type="button" class="page-btn btndisable" disabled > <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
 </c:if>
 <c:if test ="${pg!=1 }">
 	<button type="button" class="page-btn "  onclick="firstPage()">  <i class="fas fa-chevron-left fa-1x page-icon"></i><i class="fas fa-chevron-left fa-1x page-icon"></i></button>
 	<button type="button" class="page-btn" onclick="prevPage()"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
 </c:if>
 <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	<c:if test="${vs.current eq pg }">
			  <button type="button" class="page-btns all-review-sel-page"> ${vs.current} </button>
	</c:if>
		<c:if test="${vs.current ne pg}">
  	<button type="button" class="page-btn " onclick="location.href='${pageContext.request.contextPath}/all-review-page.bom?pg=${vs.current}'">${vs.current}</button>
			</c:if>
		</c:forEach>
		<c:if test="${pg!=maxPg }">
		 <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage();"><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
		</c:if>
		<c:if test="${pg==maxPg }">
		 <button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon"></i></button>
  			<button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon"></i> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
		</c:if>
		
 </c:if>
 
 
 
 
</div>
		

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main --> 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

<script type="text/javascript">

$('.sel-update').click(function(){


    $('.free-user-reply').css('display','none');
    $('.reply-update-form').css('display','block');
  
    event.preventDefault ? event.preventDefault() : (event.returnValue = false);

  });
</script>
</body>
</html>