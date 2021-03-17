<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 질문 등록</title>

<link href="${pageContext.request.contextPath}/resources/css/board/qna-register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
<c:if test="${not empty qnaFailMsg}">
	alert('${qnaFailMsg}');
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
 	 <%@include file="../common/board/_nuri_board_category.jsp" %>

   		
   		
   		<div class="real-content">

                 <div class="qna-register-box">

                <form method="get" action="${pageContext.request.contextPath}/question_edit.bom">

                  <table>
                    <tr>
                      <td>
                       	 제목
                      </td>
                      <td>
                         <input type="hidden" name ="mbId" value="${mbPKId}">
                         <input type="hidden" name ="writer" value="${mbnickName}">
                        <input type="text" name="title" placeholder="질문 제목을 입력해주세요." required maxlength="64">
                      </td>
                    </tr>
                    <tr>
                      <td>
                       	 문의 유형
                      </td>
                      <td>

                        <select name="category" >
                          <option value="1">로그인 문의</option>
                          <option value="2">상담 문의</option>
                          <option value="3">기능 문의</option>
                        </select>
                      </td>
                    </tr>
                    
                    <tr>
                      <td colspan="2" class="qna-content-textarea">
                        <textarea name="content" cols="20" rows="10" placeholder="내용을 입력해주세요." maxlength="500" required></textarea>
                      </td>
                      
                    </tr>
                    <tr class="pw-td">
                      <td >
                       	 비밀번호
                      </td>
                      <td >
                        <input type="password" id="check-password" name="password" maxlength="6"  placeholder="비밀번호 6자" required>
                        <h4 class="none-pw"> 비밀번호 6글자를 정확히 입력해주세요!</h4>
                      </td>
                    </tr>

                    <tr class="qna-lock-box">
                      
                      <td colspan="2" class="qna-lock-td">
                        <label for="qna-lock" class="qna-lock-label">비밀글</label>
                        <input type="checkbox" id="qna-lock" name="secret" >
                       
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" >
                       
                        <input type="submit" value="질문 등록" class="qna-submit-btn" disabled>
                      </td>
                      
                    </tr>
                  </table>

                </form>

              </div>
           
         

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

<script type="text/javascript">
	
	$('#check-password').keyup(function(){

	    if($('#check-password').val().length != 6){
	      $('.none-pw').css('display','inline');
	      $('.qna-submit-btn').attr('disabled', true);
	    }else{
	      $('.none-pw').css('display','none');
	      $('.qna-submit-btn').attr('disabled', false);
	    }

    });

if(document.getElementById("qna-lock").checked == true){
	
	document.getElementById("qna-lock").value =1;
	
}else{
	document.getElementById("qna-lock").value =2;
	
}
	
</script>




</body>
</html>