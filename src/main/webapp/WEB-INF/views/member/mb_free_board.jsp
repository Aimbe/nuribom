<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 나의 마음 공유</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-review.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/myReply.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/myReview.css" rel="stylesheet" type="text/css" />
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
                  <h6 class="user-mini-title">마음공유</h6>
                  
                </div><!--search-box-->

            </div><!--faq-top-->
		 	
		 	<!-- 자유게시판 글 테이블 -->
		 	 <%@include file="../common/member/_my_free_list.jsp" %>
		 	<!-- 글작성 이동 -->
		 	<div class="go-user-board-register">
              <div class="select_all_div">
              <input type="checkbox" class="select_all_btn" id="check_all" onclick="checkAll()"> 전체선택
              </div>
              
              <div class="go-qna-register">
              <button type="button" class="user-board-register delete" onclick="deleteWarning()">삭제</button>
              
              
              </div>
             
            </div>
		 	<!-- 페이지 이동 -->
		 	 <div class="qna-board-page">

              <button type="button" class="page-btn"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
              <button type="button" class="page-btn qna-sel-page"> 1</button>
              <button type="button" class="page-btn"> 2</button>
              <button type="button" class="page-btn"> 3</button>
              <button type="button" class="page-btn"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>

            </div>
		 	

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>