<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 나의예약</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-like-list.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />

<link href=" ${pageContext.request.contextPath}/resources/css/member/myReserve.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/review/all-review.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="../common/_header.jsp" %>


	<div id="main">
	<!-- 레이아웃 비주얼 -->
	 <%@include file="../common/board/_visual.jsp" %>

	<!-- 카테고리 레이아웃 -->
	 <div class="container submain-content-box">
 	 <%@include file="../common/member/_my_category.jsp" %>

   
		 	
		 	<!-- 자유게시판 글 테이블 -->
		 	 <%@include file="../common/member/_my_reserve_list.jsp" %>
		 	<!-- 글작성 이동 -->
		 	
		 	<!-- 페이지 이동 -->
		 	 <div class="qna-board-page">
 	<script>
 	var page = <c:out value='${pg}'/>;			
	var finalPage = <c:out value='${maxPg}'/>; 	  	 
	var siteLink = "member_reserve.bom?pg=";										 
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
  	<button type="button" class="page-btn " onclick="location.href='member_reserve.bom?pg=${vs.current}'">${vs.current}</button>
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

            </div>
		 	

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>