<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="real-content">

           <c:forEach var="ct" items="${likeCtList}"> 
            <div class="review-item-box">
			
              <div class="review-item-img">
                   <img src="./resources/images/center/${ct.mainImg}" >
              </div>

              <div class="review-item-content-box">

                <div class="review-item-info">
                 
                  <div class="review-info-left">
                    <h3 class="review-center-title">
                     ${ct.name}
                    </h3>
                    <i class="fas fa-map-marker-alt fa-2x center-map-icon"></i>
                    <h4 class="review-center-address">
                      ${ct.addressRegion} ${ct.addressCity} 
                    </h4>
                  </div>
                  <div class="review-user-like">
					<div id="ctlike_${mbPKId}_${ct.id}" class="like_wrapper" >
			            <button  type="button" class="btn_like" 
			            onclick="likeCenter(${mbPKId},${ct.id})" >
			              <span title="관심상담소 저장">
<%-- 			              	<i class="fas fa-heart fa-2x ${selStatus eq null ? '' : 'selected'}"></i> --%>
			              </span>
			            </button>
			        </div>
                  </div>	
                </div><!--info-->

                  <div class="center-info">
                    <p>${ct.openTime} ~ ${ct.closeTime}</p>
                    <p>공휴일 휴무</p>
                    <p>Tel. ${ct.telephone}</p>
                    <p><a href="${ct.site}">${ct.site}</a></p>
                    <p></p>
                   
                  </div>
                  
                  <div class="go-book-center">
                    <button type="button" class="go-book"
                    onclick="location.href='reserve.bom?ctId=${ct.id}'">예약하기</button>
                    <button type="button" class="go-center"
                    onclick="location.href='center_detail.bom?ctId=${ct.id}'">이 상담소 더보기</button>
                    </div>
                    

                
             </div><!--content-box-->
           </div><!--all-review-page-box-->
           </c:forEach>
           
   			 <script type="text/javascript">

					var CTX = '${pageContext.request.contextPath}';
					// 관심상담소 담기/취소(좋아요)
					function likeCenter(mbId,ctId) {
						var url = CTX + "/center_like.bom";
						var params = "?mbId="+mbId + "&ctId="+ctId;
				
						url = url + params;
				
						//비동기..
						var target = '#ctlike_'+ mbId+"_"+ctId;
						$(target).load(url, function(res) {
						console.log(res);
						});
						
					}
					
				</script>       
           