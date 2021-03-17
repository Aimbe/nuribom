<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 관심상담소</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-like-list.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />

<link href=" ${pageContext.request.contextPath}/resources/css/member/myLike.css?sasaas" rel="stylesheet" type="text/css" />
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
		 	<c:if test="${empty likeSize || likeSize == 0}">
		 		<div class="noBoard">
				<h4> 좋아요 한 상담소가 없습니다.</h4>
				
			</div>
			</c:if>
			<c:if test="${likeSize > 0}">
		 	 <%@include file="../common/member/_my_like_list.jsp" %>
		 	<!-- 글작성 이동 -->
		 	
		 	<!-- 페이지 이동 -->
		 	 <div class="user-board-page">

              <div id="paginate">
		<c:if test="${pn > 1}">
			<button type="button" class="page-btn" onclick = "location.href = '${pageContext.request
			.contextPath}/member_qna.bom?pg=${pn-1}'" >
			 <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
		</c:if>
			
		<c:forEach begin="1" end="${maxPg}" step="1" 
			varStatus="vs">
			
			<c:if test="${pn eq vs.current}">
			<button type="button" class="page-btn qna-sel-page">${vs.current}</button> <!-- 현재 페이지 -->
			</c:if>	
			<c:if test="${pn ne vs.current}">
			<button type="button" class="page-btn"
				onclick = "location.href = '${pageContext.request
			.contextPath}/member_like.bom?pg=${vs.current}'">${vs.current}</button>
			</c:if>
			
		
		</c:forEach>
		
		<c:if test="${pn < maxPg}">
			<button type="button" class="page-btn"
			onclick = "location.href = '${pageContext.request
			.contextPath}/member_like.bom?pg=${pn+1}'"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
		</c:if>	
            </div>	
		 	
			</c:if>
          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>