<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="qna-all-list">

              <table class="qna-list-table">
				<c:forEach var="qna" items="${qnaList}">
                <tr>
                  <td><input type="checkbox" name="check_at" value="${qna.id}"></td>
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
	                     	 <p> 관리자 덧글 찍기....</p>
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
                     	  <p> 관리자 덧글 찍기..</p>
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
                    	  	
                    	  	<form method="post" action="${pageContext.request.contextPath}/question_check_pw_form.bom">
                    	  		
                    	  		<input type="hidden" name="id" value="${qna.id }">
                    	  		<input type="hidden" name="mbId" value="${qna.mbId }">
                    	  		<input type="hidden" name="writer" value="${qna.writer }">
                    	  		<input type="hidden" name="title" value="${qna.title }">
                    	  		<input type="hidden" name="content" value="${qna.content}">
                    	  		<input type="hidden" name="password" value="${qna.password }">
                    	  		<input type="hidden" name="category" value="${qna.category}">
                    	  		<input type="hidden" name="secret" value="${qna.secret }">
                    	  		<input type="hidden" name="createdAt" value="${qna.createdAt}">
                    	  		<input type="submit" value="수정" class="update-btn">
                    	  	</form>
                    	  	
                    	  	
                    	  </c:if>
                    	  <c:if test="${(qna.reply==true?1:2) eq 2}">
                    	  	답변완료
                    	  </c:if>
                    </h4>

                  </td>
                </tr> 

			   </c:forEach>

              </table>


            </div><!--qna-all-list-->
            
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
   

 
</script>