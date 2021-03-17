<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>	
<meta charset="UTF-8">
<title>누리봄: 내가 쓴 리뷰</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-review.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css?aa" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/member/myReply.css?aa" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/member/myReview.css?aqa" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="../common/_header.jsp" %>


	<div id="main">
	<!-- 레이아웃 비주얼 -->
	 <%@include file="../common/board/_visual.jsp" %>

	<!-- 카테고리 레이아웃 -->
	 <div class="container submain-content-box">
 	 <%@include file="../common/member/_my_category.jsp" %>

   
          <div class="real-content">

            
            <div class="user-top">
              

                <div class="user-search-box">
                  <h6 class="user-mini-title">리뷰게시판</h6>
                  
                </div><!--search-box-->

            </div><!--faq-top-->
		 	
		 	<!-- 자유게시판 글 테이블 -->
		 	<c:if test="${empty atSize || atSize == 0}">
		 	<div class="noBoard">
			<h4> 작성하신 리뷰가 없습니다.</h4>
			</div>
			</c:if>
			<c:if test="${atSize > 0}">
		 	 <%@include file="../common/member/_my_review_list.jsp" %>
		 	<!-- 글작성 이동 -->
		 	<div class="go-user-board-register">
              <div class="select_all_div">
              <input type="checkbox" class="select_all_btn" id="check_all" onclick="checkAll()"> 전체선택
              </div>
              
              <div class="go-qna-register">
              <button type="button" class="user-board-register delete">삭제</button>
              
              
              </div>
             
            </div>
		 	<!-- 페이지 이동 -->
		 	 <div class="qna-board-page">

			<div id="paginate">
		<c:if test="${pn > 1}">
			<button type="button" class="page-btn" onclick = "location.href = '${pageContext.request
			.contextPath}/member_review.bom?pg=${pn-1}'" >
			 <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
		</c:if>
			
		<c:forEach begin="1" end="${maxPg}" step="1" 
			varStatus="vs">
			
			<c:if test="${vs.current eq pn}">
			<button type="button" class="page-btn qna-sel-page">${vs.current}</button> <!-- 현재 페이지 -->
			</c:if>	
			<c:if test="${vs.current ne pn}">
			<button type="button" class="page-btn"
				onclick = "location.href = '${pageContext.request
			.contextPath}/member_review.bom?pg=${vs.current}'">${vs.current}</button>
			</c:if>
			
		
		</c:forEach>
		
		<c:if test="${pn < maxPg}">
			<button type="button" class="page-btn"
			onclick = "location.href = '${pageContext.request
			.contextPath}/member_review.bom?pg=${pn+1}'"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
		</c:if>	
	</div>
				
<!--               <button type="button" class="page-btn"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button> -->
<!--               <button type="button" class="page-btn qna-sel-page"> 1</button> -->
<!--               <button type="button" class="page-btn"> 2</button> -->
<!--               <button type="button" class="page-btn"> 3</button> -->
<!--               <button type="button" class="page-btn"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button> -->

            </div>
		 	
		</c:if>

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
<script>

 
//  $('.write').click(function(){

// 	    	location.href="${pageContext.request.contextPath}/free_board_new_edit_form.bom";
	

// 	  });

$('.delete').click(function(){
	// console.log("11");
     var check_count = document.getElementsByName("check_at").length;
     var checkDelete = [];
     var delVal = confirm('게시글을 삭제하시겠습니까?');
     if(delVal == true){
    	
     	for (var i=0; i< check_count; i++) {
        	 if (document.getElementsByName("check_at")[i].checked == true) {
         	  var ctId = document.getElementsByName("check_at")[i].value;
         	  checkDelete.push(ctId);
         	 }
    	 }
     	
     	 location.href=
     		 "${pageContext.request.contextPath}/delete_my_review.bom?bdId="+checkDelete+"&mbId="+'${mbId}';
//      	for (var i=0; i< checkDelete.length; i++) {
//      	console.log(checkDelete[i]);
//      	}
     }else{
			alert('게시글 삭제를 취소합니다.');
		}
		
		
}); 
 </script>
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>