<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_onlyCtag.jsp" %>


<!-- ctag만 모아놓은 파일 -->
<!-- include로 들어오는 영역이 아니니 태그들 넣어줘! -->
	<c:choose>
	<c:when test="${rpSize gt 0}"> 
  <h4 class="user-reply-title">댓글  <sapn class="user-reply-size">${rpSize}</sapn></h4>
	  <c:forEach var="rp" items="${rpList}">
		<div class="free-user-reply" id="reply-box">

			   <h5 class="free-reply-username">
	          	   ${rp.userName}
	           </h5>
	           
	           <h5 class="free-reply-userdate">
	            <fmt:formatDate value="${rp.createdAt}" pattern="yyyy.MM.dd HH:mm"/>
	           </h5>
	
	           
	            <ul class="user-reply-update writer-show">
	           <!--회원 세션으로 writer-show 여부 판정-->
	            <c:if test="${mbPKId eq rp.mbId}"> 
				  <li>
	                <a href="#" class="sel-update" id="update-${rp.id}-move">수정</a>
	              </li>
	              
	              <script type="text/javascript">
	              
		            $(document).ready(function() {
		              
		              $('#update-${rp.id}-move').on('click',function(){
		            	  
		            	var id = '<c:out value="${rp.id}"/>'
		            	var url = CTX+"/free_reply_update.bom";
		            	
		            	$.ajax({
		            		
		            		type:'post',
		            		url: url,
		            		data:"id="+id,
		            		success: function(res,sc,xhr) {
		            			
		            			console.log(res);
								var reply = res;
								console.log(res.id);
								console.log(res.content);
								console.log(sc);
								console.log(xhr.status);
								var content = res.content.replace(/<br>/g, "\n");
								
								
								var result =
									
								'<h5 class="free-reply-update-username">'+
						    	  res.userName+
						      	'</h5>'+
						      	'<form method="post" action="free_reply_update_froc.bom" id="up-form">'+
						      	'<input type="hidden" name="id" value="'+res.id+'" class="go-update-rpId">'+
						      	'<input type="hidden" name="bdId" value="'+res.bdId+'" class="go-update-bdId">'+
						      	'<textarea name="content" id="free-user-reply-textarea" class="setting-value" maxlength="200">'+content+'</textarea>'
						        +'<button type="submit" class="free-reply-update">수정</button>'+'</form>'

						     
								if(xhr.status ==200){
									
									$('.reply-update-form').html(result);

								}
							},
		            		or: function(xhr,status) {
		            			
		            			console.log('ERROR:'+status);
		            			
		            		}
		            	});
		             });
		            });
	              </script>
	             
	              <li>
	                <a href="#" class="delete-my-reply-${rp.id}">삭제</a>
	              </li>
	             </c:if>
	             
	             <c:if test="${mbPKId eq 1 }">
	               <li>
	                <a href="#" class="delete-my-reply-${rp.id}">삭제</a>
	              </li>
	             </c:if>
	             
	              <script type="text/javascript">
	              
		           $(document).ready(function() {
		             
		        	 $('.delete-my-reply-${rp.id}').on('click',function(){
		        		 
		        		 var delVal = confirm('덧글을 삭제하시겠습니까?');
		        		 
		        		 if(delVal == false){
		        			 
		        			 alert('덧글 삭제를 취소합니다.');
		        			 event.preventDefault ? event.preventDefault() : (event.returnValue = false);     
		        		 }else{
		        		 
		        		 var id =${rp.id};
		        		 var bdId = ${rp.bdId};
		        		 var url = CTX+"/free_reply_delete.bom";
		        		 
		        		 $.ajax({
		        			 
		        			type:'post',
		        			url: url,
		        			data:"id="+id+"&bdId="+bdId,
		        			success: function(res) { 
		        			
		        				$('#test').load(res);
		        				location.reload();
		        			}
		        		 });
		        		}//삭제할게!
		        	 });
		        	});
	              
	              </script>
	             
				</ul>
				
			<p class="free-reply-content">
	             ${rp.content}
            </p>
          
	   </div>
	 </c:forEach>
 	</c:when>
	<c:when test="${rpSize eq 0}">
	<h4 class="user-reply-title">댓글  <sapn class="user-reply-size">0</sapn></h4>
		<div class="free-user-reply" id="reply-box">
			<h4 class="none_reply">등록된 덧글이 없습니다.</h4>
		</div>
	
	</c:when>

  </c:choose>   
  
  
	<div class="reply-update-form">
    
	</div>
	
	
  
  
<script type="text/javascript">   

<c:if test="${not empty rpMsg}">
  alert('${rpMsg}');
</c:if>



$('.sel-update').click(function(){


    $('.free-user-reply').css('display','none');
    $('.reply-update-form').css('display','block');
  
    event.preventDefault ? event.preventDefault() : (event.returnValue = false);

  });
</script>