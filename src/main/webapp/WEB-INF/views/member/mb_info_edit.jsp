<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 관심상담소</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-info-edit.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />

<link href=" ${pageContext.request.contextPath}/resources/css/member/myInfoEdit.css" rel="stylesheet" type="text/css" />
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
		 	 <%@include file="../common/member/_my_info_edit.jsp" %>
		 	<!-- 글작성 이동 -->
		 	
		 	<!-- 페이지 이동 -->
		 	 
		 	

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

 </div>
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>