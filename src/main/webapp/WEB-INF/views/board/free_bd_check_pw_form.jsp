<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자유게시판 게시글 수정 비밀번호 확인</title>
<link href="${pageContext.request.contextPath}/resources/css/board/board-check.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	<c:if test="${not empty failPw }">
		alert('${failPw}');	
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


		     <div class="pw-box">
		              <form action="${pageContext.request.contextPath}/board_update.bom" method="post"> 
							<c:set var="content" value="${board.content}"/>
		                <h3 class="label">
		                    작성시 사용한 비밀번호 6글자를 입력해주세요.
		                </h3>
		                <div class="check-input-box">
		                  <input type="hidden" name ="id" value="${board.id}">
	              		  <input type="hidden" name ="mbId" value="${board.mbId}">
	              		  <input type="hidden" name ="writer" value="${board.writer}">
	              		  <input type="hidden" name ="title" value="${board.title}">
	              		  <input type="hidden" name ="content" value="<c:out value="${content}"/>">
	              		  <input type="hidden" name ="view" value="${board.view}">
	              		  <input type="hidden" name ="password" value="${board.password}">
	              		  <input type="password" id="check_pw" name="checkPw" maxlength="6"  placeholder="비밀번호 6자" required>
		                  <input type="submit" value="확인" class="submit-btn" disabled>
		                </div>
		                <!--input hidden으로 나머지도 처리..-->
		              </form>
		              
		          </div><!--pw-box-->

			 </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>


<script type="text/javascript">

$('#check_pw').keyup(function(){

	if($('#check_pw').val().length != 6){
		//$('.pw-length').css('display','block');
		$('.submit-btn').attr('disabled', true);
	}else{
		//$('.pw-length').css('display','none');
		$('.submit-btn').attr('disabled', false);
	}

});


</script>

</body>
</html>