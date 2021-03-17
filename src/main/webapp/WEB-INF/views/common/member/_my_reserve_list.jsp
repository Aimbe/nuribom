<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <div class="real-content">
  		 <script>
                    function cancelConfirm(id){
                    	if ( confirm("이 예약을 정말로 취소하시겠습니까?") == true){    //확인
						
                    		var CTX = '${pageContext.request.contextPath}';
                    			
                    				$.ajax({
                    					type: 'get', 
                    					url: CTX + "/delete_reserve_"+id+".bom", // 명령문 이 컨트롤러를 실행하고 돌아와라-
                    					success: function(res) { 
                    						console.log(res); 
                    						if( res == "success") {
                    							$("#"+id).closest('.reserve-div').remove();
                    						}else{
                    						
                    						}                    						
                    					}, 
                    					error: function(xhr,status) { // 비동기 요청을 서버에서 실패처리 시 3xx,4xx,5xx
                    						console.log("ERROR: " + status);
                    						console.log("ERROR: " + xhr.status);
                    					},
                    					complete: function() { // 성공실패 무관하게 무조건 끝에 실행..
                    						console.log("completed.."); // 끝에 실행 - 모래시계 끄자...
                    					}
                    				});
                    			
                    	
                    	}else{   //취소

                    	    return;

                    	}
                    }
                  </script>
                 
 <c:forEach var="rs" items="${rsList}">
      <div class="reserve-div">
      <c:if test="${rs.reserveVisit == 1 }">
              <span class="is-done">상담완료</span>
      </c:if>
      <c:if test="${rs.reserveVisit !=1 }">
              <span class="is-done">상담예정</span>
      </c:if>
              <div class="center-title">
                <h3 class="center-title h3">${rs.reserveCenterName }</h3>
                <div class="reserve-cancel">
               	<c:if test="${rs.reserveVisit !=1 }">
                  <button onclick="cancelConfirm(${rs.reserveId})" id="${rs.reserveId }">예약 취소</button>
			      </c:if>
                </div>
              </div>
              <div class="center-info">
                	
                    <h4 class="reserve-info">
                      ${rs.reserveCenterAdress }
                    </h4>
                    <h4 class="reserve-info">
                    ${rs.reserveDay} 
                    </h4>
                    <h4 class="reserve-info">
                      예약번호: ${rs.reserveId}
                    </h4>
              </div>
              <div class="bottom-btn">
                <button class="go-center-pg" onclick="location.href='${pageContext.request.contextPath}/center_detail.bom?ctId=${rs.reserveCenterId}'">상담소 페이지로 가기</button>
                <br>
                <c:if test="${rs.reserveReview == 0 }">
                	 <c:if test="${rs.reserveVisit == 0 }">
                <button class="go-review" onclick="location.href='${pageContext.request.contextPath}/center_detail.bom?ctId=${rs.reserveCenterId}&rsId=${rs.reserveId}#main_reviews'" disabled>상담미완</button>
                	 </c:if>
                	  <c:if test="${rs.reserveVisit == 1 }">
                <button class="go-review go-review-on" onclick="location.href='${pageContext.request.contextPath}/center_detail.bom?ctId=${rs.reserveCenterId}&rsId=${rs.reserveId}#main_reviews'" >후기 작성하기</button>
                	 </c:if>
                
                </c:if>
                 <c:if test="${rs.reserveReview == 1}">
                <button class="go-review " disabled>후기 작성완료</button>
                </c:if>
                
              </div>
    </div>
  </c:forEach>            
 </div>
            