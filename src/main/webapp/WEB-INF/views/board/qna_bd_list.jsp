<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 질문게시판</title>

<link href="${pageContext.request.contextPath}/resources/css/board/qna.css" rel="stylesheet" type="text/css" />
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

                <div class="qna-search-box">

              <h6 class="qna-mini-title">누리봄 QnA</h6>
              <div class="qna-search-inner-box">
                <form method="get">
                  <input type="search" class="qna-search"placeholder="search" name="qna-search">
                  <button type="button" class="search-qna-btn"><i class="fas fa-search fa-2x search-qna-icon"></i></button>
                </form>

              </div>
            </div><!--search-box-->

            <div class="qna-all-list">
		<c:if test="${not empty qnaSize }">
           <table class="qna-list-table">
			<c:forEach var="qna" items="${qnaList}" varStatus="vs">
                <tr>
                  <td>

                    <h6 class="qna-category">
                      <c:if test="${qna.category eq 1}">
                      	[로그인문의]
                      </c:if>
                      <c:if test="${qna.category eq 2}">
                      	[상담문의]
                      </c:if>
                      <c:if test="${qna.category eq 3}">
                      	[기능문의]
                      </c:if>
                    </h6>
                    <c:if test="${(qna.secret== true?1:2) eq 2}">
	                   <c:if test="${mbPKId ne qna.mbId}">
	                    <i class="fas fa-lock sel-lock"></i>
	                    <h6 class="qna-title">
	                   		비밀글 입니다.
	                    </h6>
	                   </c:if> 
                       <c:if test="${mbPKId eq qna.mbId}">
	                    <h6 class="qna-title">
	                   		${qna.title }
	                    </h6>
	                   </c:if> 
                    
                    </c:if>
                    <c:if test="${(qna.secret== true?1:2) eq 1}">
                    <h6 class="qna-title">
                     	 ${qna.title}
                    </h6>
                    </c:if>
                    <c:if test="${(qna.secret== true?1:2) eq 1}">
	                  <div class="answer">
	                     
	                     <c:if test="${(qna.reply==true?1:2) eq 1}">
	                       <h6 class="qna-content">
							 ${qna.content}
                          </h6>
                     	  <p class="answer-title">누리봄 관리자</p>
                     	  <p> 관리자의 답변이 아직 등록되지 않았습니다.</p>
	                     </c:if>
	                     <c:if test="${(qna.reply==true?1:2) eq 2}">
	                      <h6 class="qna-content">
							 ${qna.content}
                          </h6>
                     	  <p class="answer-title">누리봄 관리자</p>
	                     	 <p> ${rpList[vs.index].content}</p>
	                     </c:if>
	                     
	                  </div>
                    </c:if>
                      <c:if test="${(qna.secret== true?1:2) eq 2}">
	                  <div class="answer">
	                     
	                     <c:if test="${(qna.reply==true?1:2) eq 1 and mbPKId ne qna.mbId}">
	                     	
	                     	 <h6 class="qna-content">
								작성자만 확인가능한 질문입니다.
                          	</h6>
	                     </c:if>
	                     <c:if test="${(qna.reply==true?1:2) eq 1 and mbPKId eq qna.mbId}">
	                      <h6 class="qna-content">
							 ${qna.content}
                          </h6>
                     	  <p class="answer-title">누리봄 관리자</p>
                     	  <p> 관리자의 답변이 아직 등록되지 않았습니다.</p>
	                     </c:if>
	                      <c:if test="${(qna.reply==true?1:2) eq 2 and mbPKId eq qna.mbId}">
	                      <h6 class="qna-content">
							 ${qna.content}
                          </h6>
                     	  <p class="answer-title">누리봄 관리자</p>
                     	  <p> ${rpList[vs.index].content}</p>
	                     </c:if>
	                      <c:if test="${(qna.reply==true?1:2) eq 2 and mbPKId ne qna.mbId}">
	                      
	                     	 <h6 class="qna-content">
								작성자만 확인가능한 질문입니다.
                          	</h6>
                     	 
	                     </c:if>
	                   </div>
                    </c:if>
                    
                     
                  </td>
                  <td>

                    <p class="qna-user-name">${qna.writer}</p>
                    <p class="qna-date"><fmt:formatDate value="${qna.createdAt}" pattern="yyyy.MM.dd"/></p>
                  </td>
                  <td>

                    <h4 class="is-qna-reply">
                    	  <c:if test="${(qna.reply==true?1:2) eq 1}">
                    	  	답변예정
                    	  </c:if>
                    	  <c:if test="${(qna.reply==true?1:2) eq 2}">
                    	  	답변완료
                    	  </c:if>
                    </h4>

                  </td>
                </tr> 

			   </c:forEach>
             </table>
		</c:if>
		
		<c:if test="${qnaSize eq 0 }">
			<h2 class="none-qna">등록된 질문이 없습니다.</h2>
		</c:if>

            </div><!--qna-all-list-->
            <div class="go-qna-register">
              <button type="button" class="qna-register">QnA 작성</button>
            </div>

		   	<c:if test="${not empty qnaSize}">
             <div class="qna-board-page">

              <button type="button" class="page-btn prev"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
              
             <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
               <c:if test="${pn eq vs.current }">
              	<button type="button" class="page-btn qna-sel-page">${vs.current }</button>
               </c:if>
                <c:if test="${pn ne vs.current }">
              	<button type="button" class="page-btn" onclick="movePage(${vs.current})">${vs.current }</button>
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

$('.qna-title').click(function(){

    $(this).next().slideToggle();
   
   });  
   
   
$('.qna-register').click(function(){

	<c:if test="${not empty mbPKId}">
    	location.href="${pageContext.request.contextPath}/question_new_edit_form.bom";
    </c:if>

	<c:if test="${empty mbPKId}">
		alert('로그인 후 사용이 가능합니다.');
    </c:if>

  });
   
$('.prev').click(function(){

	<c:if test="${pn gt 1 }">
		location.href="${pageContext.request.contextPath}/question_list.bom?pg=${pn-1}";
	</c:if>
});   
   
   
$('.next').click(function(){

	<c:if test="${pn lt maxPg }">
		location.href="${pageContext.request.contextPath}/question_list.bom?pg=${pn+1}";
	</c:if>
});  
   
   
function movePage(e){
	
    location.href ="${pageContext.request.contextPath}/question_list.bom?pg="+e;

}  

</script>

</body>
</html>