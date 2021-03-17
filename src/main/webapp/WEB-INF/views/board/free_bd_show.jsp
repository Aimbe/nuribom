<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자유게시판</title>
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
<link rel="stylesheet" href="https://cdn.quilljs.com/1.3.6/quill.core.css">
<link href="${pageContext.request.contextPath}/resources/css/board/content.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/board/user-detial.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">

var CTX ='${pageContext.request.contextPath}';
$(document).ready(function() {

	var bdId = '<c:out value="${board.id}"/>'
	var url = CTX+"/free_reply_list.bom";
	var param ="?bdId="+bdId;
	console.log(url+param);
	$('#test').load(url+param);
	
$('.free-reply-send').on('click',function(){

	<c:if test="${not empty mbPKId}">
		var bdId = $('input[name=bdId]').val();
		var mbId = $('input[name=mbId]').val();
		var writer = $('input[name=writer]').val();
		var content = $('textarea[name=content]:visible').val();
		content = encodeURIComponent(content);


		console.log(bdId+','+mbId+','+writer+','+content);
		var url = CTX+"/free_reply_proc.bom";


	$.ajax({
		
		type:'post',
		url: url,
		data:"bdId="+bdId+"&mbId="+mbId+"&writer="+writer+"&content="+content,
		success: function(res) { 
			
		  result = decodeURIComponent(res);

			$('#test').load(result);
			location.reload();
		}
	});


	</c:if>
	
});
	
	
});
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
		 
		 
		     <div class="user-detail-top">

              <h2>${board.title}</h2>
             <div class="user-detail-info">
               
                <ul>
                  <li>by ${board.writer}</li>
                  <li>조회수 ${board.view}</li>
                 <li>
                 <fmt:formatDate value="${board.createdAt}" 
						pattern="yyyy.MM.dd" />
                 </li>
                </ul>

             </div>
            </div>
		
            <div class="detail-content">
				
				${board.content}
					
            </div>

            <div class="update-btn-box">
             <!--write-user-sel-btn 글을 쓴 유저에게만 보이는 버튼-->
          
	              <div class="go-update-register write-user-sel-btn">
	                <c:if test="${mbPKId eq board.mbId }">
	              	<c:set var="content" value="${board.content}"/>
	              	<form action="${pageContext.request.contextPath}/board_check_pw_form.bom" method="post" class="update-form">
	              		<input type="hidden" name ="id" value="${board.id}" >
	              		<input type="hidden" name ="mbId" value="${board.mbId}">
	              		<input type="hidden" name ="writer" value="${board.writer}">
	              		<input type="hidden" name ="title" value="${board.title}">
	              		<input type="hidden" name ="content" value="<c:out value="${content}"/>">
			    		<input type="hidden" name ="password" value="${board.password}">
	              		<input type="hidden" name ="view" value="${board.view}">
	              		<input type="hidden" name ="createdAt" value="${board.createdAt}">
	              		<input type="hidden" name ="thumbnail" value="${board.thumbnail}">
	              		
	              		<button type="submit" class="update-register">수정</button>
	                </form>
	                <button type="button" class="delete-btn">삭제</button>
	               </c:if>
	                <c:if test="${mbPKId eq 1}">
			 	 <button type="button" class="delete-btn">삭제</button>
					</c:if>
	              </div>
			 </div><!--수정영역-->

            <div class="user-detail-bottom">
			 <div id="test">
				
              </div>

                <div class="user-detail-reply">

<%--                   <form method="post" action="${pageContext.request.contextPath}/free_reply_proc.bom" id="reply-form"> --%>
                  	<input type="hidden" name="bdId" value="${board.id}">
                  	<input type="hidden" name="mbId" value="${mbPKId}">
                  	<input type="hidden" name="writer" value="${mbnickName}">
                  	<textarea name="content" id="free-user-reply-textarea" maxlength="200"></textarea>
                    <button type="button" class="free-reply-send">작성</button>
<!--                   </form> -->
                </div>

            </div>

            <div class="free-detail-list">

            

              <button type="button" class="free-allList-page-go">목록</button>
            </div>
		 		 	

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

<script type="text/javascript">

// $('.sel-update').click(function(){


//     $('.free-user-reply').css('display','none');
//     $('.reply-update-form').css('display','block');
  
//     event.preventDefault ? event.preventDefault() : (event.returnValue = false);

//   });
  
$('.free-allList-page-go').click(function(){
	
	location.href="${pageContext.request.contextPath}/free_board_list.bom?pg=${pn}";
    
  }); 

$('.delete-btn').click(function(){
	
	
	var delVal = confirm('게시글을 삭제하시겠습니까?');
	
	if(delVal ==true){
	
		location.href="${pageContext.request.contextPath}/board_delete.bom?bdId="+${board.id}+"&mbId="+${board.mbId};
	    
		
	}else{
		
		alert('게시글 삭제를 취소합니다.');
		
	}
	
	
  }); 
  
$('.free-reply-send').click(function(){
	
	<c:if test="${empty mbPKId}">
		alert('로그인 후 사용이 가능합니다.');
		return false;
		//$('#reply-form').attr('onsubmit','return false');
	</c:if>
// 	<c:if test="${not empty mbPKId}">
// 	//$('#reply-form').attr('onsubmit','return false');
// 	</c:if>
	
});


<c:if test="${empty mbPKId}">

$('#free-user-reply-textarea').attr('placeholder','로그인 후 사용해주세요.');

</c:if>

<c:if test="${not empty mbPKId}">

$('#free-user-reply-textarea').attr('placeholder','타인을 향한 비방 및 욕설 시 강한 제재에 들어갑니다.');

</c:if>


<c:if test="${not empty rpUpdateMsg}">
alert('${rpUpdateMsg}');
</c:if>



// $('#free-user-reply-textarea').keyup(function(){

//     if($(this).val().length >= 10){
    
//       $('.free-reply-send').attr('disabled', true);
//     }else{
   
//       $('.free-reply-send').attr('disabled', false);
     
//     }

// });


  

</script>


</body>
</html>