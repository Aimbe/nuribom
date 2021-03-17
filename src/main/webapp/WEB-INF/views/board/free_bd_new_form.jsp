<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자유게시판 새 글 작성하기</title>

<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/board/user-edit.css" rel="stylesheet" type="text/css" />
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
		 
		 
		 	  <div id="standalone-container">
          
              <form action="${pageContext.request.contextPath}/free_board_edit.bom" method="post" id="user-edit">

				<input type="hidden" name ="mbId" value="${mbPKId}">
           		<input type="hidden" name ="writer" value="${mbnickName}">

                <input type="text" name="title" maxlength="64" class="user-title" placeholder="제목을 입력해주세요." required>
                <div id="editor"></div>
                <input type="hidden" name="content" id="user-board-content" required>
				<input type="hidden" name= "thumbnail" value="">

                <div class="pw-box">
                  <label for="user-board-pw" class="pw-label">비밀번호</label>
                  <input type="password" id ="user-board-pw" name="password" maxlength="6"  placeholder="비밀번호 6자" required>
               	  <span class="pw-length">비밀번호 6글자를 모두 입력해주세요.</span>
               	<c:if test="${not empty freeMsg}">
                	<span class="free-msg">${freeMsg }</span>
                </c:if>
                </div>
                <div class="buttom-wrap">
                  <button id="saveDelta" disabled>발행</button>
                </div>
              </form>
              </div><!-- standalone -->
		 		 	

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

<script type="text/javascript">


var toolbarOptions = [
    
    
   
    [{'header':[false,1,2,3]}],
    ['bold','italic','underline'],
    ['image'],
    [{'color':[]},{'background':[]}]

    ];

    var quill = new Quill('#editor', {
         modules: {
        
        toolbar: toolbarOptions
      },
      placeholder: '게시물을 작성해주세요',
      theme: 'snow'
});



$('#saveDelta').click(function(){


  /*window.delta = quill.getContents(); //quill.setContents(window.delta);로 셋팅! 
                                      //불러와서 다시 이어 못쓰게! => quill.enable(false)
  console.log(window.delta);
*/

  var content = quill.root.innerHTML;
  $('#user-board-content').val(content);
  $('#user-edit').submit();
  

  
});

// $('#user-board-pw').focusin(function() {

// 	if($('#user-board-pw').val().length != 6){
// 		$('.pw-length').css('display','block');
// 		$('#saveDelta').attr('disabled', true);
// 	}else{
// 		$('.pw-length').css('display','none');
// 		$('#saveDelta').attr('disabled', false);
// 	}
// })

$('#user-board-pw').keyup(function(){

	if($('#user-board-pw').val().length != 6){
		$('.pw-length').css('display','block');
		$('#saveDelta').attr('disabled', true);
	}else{
		$('.pw-length').css('display','none');
		$('#saveDelta').attr('disabled', false);
	}

});


</script>


</body>
</html>