<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자유게시판</title>

<link href="${pageContext.request.contextPath}/resources/css/board/user-board.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
<c:if test="${not empty noneMsg }">

alert('${noneMsg}');

</c:if>
</script>
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
		 
		 	<div class="user-top">
              <i class="fas fa-comment-dots fa-5x"></i></i>
              <h2 class="user-top-title">
				  마음 공유<br>
		                함께 소통하는 공간
			  </h2>
		
		      <h3>
		                함께 마음을 나누는 곳입니다.<br>
		                타인을 향한 비방 및 욕설을 주의해주세요.
			 </h3>

                <div class="user-search-box">

                  <h6 class="user-mini-title">자유게시판</h6>
                  <div class="user-search-inner-box">
                    <form method="post" action="${pageContext.request.contextPath}/free_board_search_proc.bom">
                      <input type="search" class="user-search"placeholder="게시글의 제목을 입력해주세요." name="search" required oninvalid="this.setCustomValidity('최소 한 글자 이상 검색 키워드를 입력해주세요.')"
                      oninput = "setCustomValidity('')">
                      <button type="submit" class="search-user-btn"><i class="fas fa-search fa-2x search-user-icon"></i></button>
                    </form>

                  </div>
                </div><!--search-box-->

            </div><!--free-top-->
		 	
		 	<!-- 자유게시판 글 테이블 -->
		 	 <%@include file="_free_board_list.jsp" %>
		 	<!-- 글작성 이동 -->
		 	<div class="go-user-board-register">
              <button type="button" class="user-board-register">글쓰기</button>
            </div>
            
		 	<!-- 페이지 이동 -->
		 	<c:if test="${not empty bdSize}">
			 	<div class="user-board-page">
	
				  
	              <button type="button" class="page-btn prev"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
	              
	              <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	              	
	              	<c:if test="${pn eq vs.current}">
	             	 	<button type="button" class="page-btn  user-sel-page">${vs.current }</button>
	             	</c:if>
	             	<c:if test="${pn ne vs.current}">
	             	 	<button type="button" class="page-btn" onclick="movePage(${vs.current})">${vs.current}</button>
	             	</c:if>
	             	
	             
	             </c:forEach>
	              
	              
	              <button type="button" class="page-btn next"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
	            
	            </div>
		 	</c:if>
		 	
          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

<script type="text/javascript">

$('.user-board-register').click(function(){

	<c:if test="${not empty mbPKId}">
    	location.href="${pageContext.request.contextPath}/free_board_new_edit_form.bom";
    </c:if>

    <c:if test="${empty mbPKId}">
		alert('로그인 후 사용이 가능합니다.');
    </c:if>

  });


$('.prev').click(function(){

	<c:if test="${pn gt 1 }">
		location.href="${pageContext.request.contextPath}/free_board_list.bom?pg=${pn-1}";
	</c:if>
});


$('.next').click(function(){

	<c:if test="${pn lt maxPg }">
		location.href="${pageContext.request.contextPath}/free_board_list.bom?pg=${pn+1}";
	</c:if>
});

$('.number').click(function(){

	
		location.href="${pageContext.request.contextPath}/free_board_list.bom?pg=${pn+1}";

});



function movePage(e){
	
    location.href ="${pageContext.request.contextPath}/free_board_list.bom?pg="+e;

}


</script>


</body>
</html>