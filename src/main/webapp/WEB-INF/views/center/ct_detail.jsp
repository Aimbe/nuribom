<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>
    
  <head>

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>누리봄</title>
  <link href=" ${pageContext.request.contextPath}/resources/css/center/center_detail.css" rel="stylesheet" type="text/css" />
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAjPS3hZ8oiTu0YA8bDTDewLM2kMNXPRPw&callback=initMap&libraries=&v=weekly"
      defer
    ></script>
    <!-- google Map Api -->


<script type="text/javascript">

      $(document).ready(function () {
    	  
        var currentPosition = parseInt($("#side-fix-btn").css("top"));
        $(window).scroll(function () {
          var position = $(window).scrollTop();
          $("#side-fix-btn")
            .stop()
            .animate({ top: position + currentPosition + "px" }, 800);
        });

        // 디테일페이지 사이드바
        var detailCurrentPosition = parseInt(
          $("#detail-side-fix-btn").css("top")
        );
        $(window).scroll(function () {
          var detailPosition = $(window).scrollTop();
          $("#detail-side-fix-btn")
            .stop()
            .animate(
              { top: detailPosition + detailCurrentPosition + "px" },
              1000
            );
        });

        //리뷰 평점클릭시 별점아이콘 처리
        $("#select_rate").change(function () {
          var rate = $(this).val();
          switch (rate) {
            case "1":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(92, 86, 86)");
              $("#star_3").css("color", " rgb(92, 86, 86)");
              $("#star_4").css("color", " rgb(92, 86, 86)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "2":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(92, 86, 86)");
              $("#star_4").css("color", " rgb(92, 86, 86)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "3":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(184, 184, 55)");
              $("#star_4").css("color", " rgb(92, 86, 86)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "4":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(184, 184, 55)");
              $("#star_4").css("color", " rgb(184, 184, 55)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "5":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(184, 184, 55)");
              $("#star_4").css("color", " rgb(184, 184, 55)");
              $("#star_5").css("color", " rgb(184, 184, 55)");
              break;
          }
        });
      });

      // 구글맵
      var marker;
      var map;
      function initMap() {
//     	  var customerId = [[${center.id}]];


 		var latVal = ${center.latitude}; // 위도값을 가져옴
        var lngVal = ${center.longitude}; // 경도값을 가져옴
        console.log(latVal);
        console.log(lngVal);

		 var myLatLng = {lat: latVal, lng: lngVal}; // 위도, 경도를 가지는 객체를 생성

        map = new google.maps.Map(document.getElementById("map"), {
          center: myLatLng,
          zoom: 16, //자세한 정도
        });

        marker = new google.maps.Marker({
          map: map,
          draggable: true,
          animation: google.maps.Animation.BOUNCE,
          position: myLatLng,
          title: "", //말풍선
        });
        marker.addListener("click", toggleBounce);
      }
      // 마커 애니메이션
      function toggleBounce() {
        if (marker.getAnimation() !== null) {
          marker.setAnimation(null);
        } else {
          marker.setAnimation(google.maps.Animation.BOUNCE);
        }
      }
      google.maps.event.addListener(marker, "click", function () {
        var infowindow = new google.maps.InfoWindow({
          content: "",
          size: new google.maps.Size(100, 100),
        });
        infowindow.open(map, marker);
      });
</script>
</head>
<body>
   <%@include file="../common/_header.jsp" %>  

    <!-- 메인  -->
    <div id="main">
 <%@include file="../common/board/_visual.jsp" %><!--비주얼-->

      <div class="container main-container">
        <div class="detail_info_top">
          <div class="top_img">
            <img src="./resources/images/center/${center.mainImg}">  
          </div>
          <div class="top_info_wrap">
            <div class="top_info">
              <div class="center_name">
                <h1>${center.name}</h1>
                 <div class="tags">
                 <c:forEach var="tags" items="${tagList}">
                  	<span class="center_tag tag_num_1">${tags.tag}상담</span> &nbsp;
                  </c:forEach>
                </div>
              </div>
              <div class="center_intro">
                <span class="info_address">${center.addressRegion} ${center.addressCity}</span>
                <span class="info_tel">Tel. ${center.telephone}</span>
                <span class="info_time">운영시간. ${center.openTime} ~ ${center.closeTime}(평일)</span>
            	<span class="info_site" id="check_site">Site. <a href="${center.site}">${center.site}</a></span>
              </div>
              <div class="top_btns">
              
              
              		<!-- 좋아요 -->
		              <div id="ctlike_${mbPKId}_${center.id}" class="like_wrapper">
		              
		                <button type="button" class="btn_like" >
	                 		  <span title="관심상담소 저장">
				                 		<i class="fas fa-heart fa-2x btn_like_icon" style="font-size: 20px; color: rgb(173, 151, 110);"></i>
					                   <span class="like_count">${center.likes}<small>명이 좋아합니다</small></span>
		                  		</span>
		                </button>
		                
		              </div>
	
	                <div class="btn_reserve_wrap">
	                  <button type="button" class="btn_reserve" onclick="location.href='${pageContext.request.contextPath}/reserve.bom?ctId=${ctId}'">예약하기</button>
	                </div>
	                
              </div>
              
            </div>
          </div>
        </div>

        <div class="info_main_wrapper">
          <div class="info_main">
            <div id="main_intro">
              <div class="num_check">01</div>
              <div class="tit">상담소 소개</div>
              <div class="deco_box"></div>
              <div class="deco_tit_first">자세히</div>
              <div class="deco_tit_second">알아보기</div>

              <div class="center_description_wrap">
                <p class="center_description first">
                  ${center.firstDesc}
                </p>

                <p class="center_description second">
                ${center.secondDesc}
                </p>

                <p class="center_description third">
               ${center.thirdDesc}
                </p>
              </div>
            </div>
          </div>

          <div id="main_programs">
            <div class="num_check">02</div>
            <div class="tit">제공 프로그램</div>

            <div class="first_program">
              <div class="program_title">
                <span>${center.firstProgramTitle}</span>
              </div>
               <div class="program_img" style="background-image:
              	 url('./resources/images/center/programs/${center.firstProgramImg}')"></div>
              <div class="program_description">
                <span>${center.firstProgramDesc} </span>
              </div>
            </div>

            <div class="second_program">
              <div class="program_img" style="background-image:
              	 url('./resources/images/center/programs/${center.secondProgramImg}')"></div>
              <div class="program_title">
                <span>${center.secondProgramTitle}</span>
              </div>
              <div class="program_description">
                <span>${center.secondProgramDesc}</span>
              </div>
            </div>
          </div>

          <div id="main_location">
            <div class="num_check">03</div>
            <div class="tit">찾아오시는 길</div>
            <div id="map"></div>
            <span>주소</span>
            <span id="total_address">
              : ${center.addressRegion} ${center.addressCity} ${center.addressDetail}</span>
          </div>

          <div id="main_reviews">
            <div class="num_check">04</div>
            <div class="tit">리뷰 게시판</div>
            <div id="go_total_review">
              <a href="${pageContext.request.contextPath}/all-review-page.bom"
                >리뷰모음 &nbsp;<i class="fas fa-arrow-circle-right fa-1x"></i
              ></a>
            </div>

            <div class="review_board_top">
              <div class="review_info">
                <i class="fas fa-star fa-2x" id="star"></i>
                <span id="avg_rate"> ${Average} </span>
                <span id="total_review_count">(${rvListSize}개 리뷰)</span>
              </div>

			
              <div class="reviews_wrapper">
              
               <c:forEach var="rv" items="${rvList}">
     
                 <div class="review_box">
                  <div class="write_info"> 
                  
                <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    
                    <span class="writer">${rv.reviewUserName}</span>
                    <span class="written_at">${rv.reviewDay}</span>
                    <c:if test="${!empty mbId }">
                    <c:if test="${rv.reviewMemberId == mbId }">
                    <input type="button" onclick="editReview(${rv.reviewId})" value="수정" id="review_change${rv.reviewId}" class="review_change" />
                    <input type="button" onclick="DeleteReview(${rv.reviewId})" value="삭제" id="review_delete${rv.reviewId}" class="review_delete" />
                    </c:if>
                    </c:if>
                    
                    <script>
                    function editReview(id){
                		if ( confirm("수정하시겠습니까?") == true){ 
                			location.href="center_detail_edit.bom?rvId="+id;
                		}
                    }
                    </script>
                   <script>
                   function DeleteReview(id){
                		if ( confirm("정말 삭제하시겠습니까? 재작성하실수없습니다.") == true){    //확인
                		
                			var CTX = '${pageContext.request.contextPath}';
                					$.ajax({
                						type: 'get',
                						url: CTX + "/delete_review_"+id+".bom",
                					
                						dataType: 'text', // pcard2 명시적
                						success: function(res) { // 비동기 요청을 서버에서 성공처리 시 2xx
                							console.log(res); 
                							if( res == "success") {
                								$("#review_delete"+id).closest('.review_box').remove();
                							}else{
                							 alert('ajax 삭제에 실패했습니다.');
                							}
                							
                						}, 
                						error: function(xhr,status) { // 비동기 요청을 서버에서 실패처리 시 3xx,4xx,5xx
                							console.log("ERROR: " + status);
                							console.log("ERROR: " + xhr.status);
                							 alert('삭제에 실패했습니다.');
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
                    
                  </div>
                  <div class="comment">
                    <p> 
                    ${rv.reviewContent} <!-- 이것이문제여 -->
                    </p>
                  </div>
                  
                  
                </div>
                
            </c:forEach>
              
              </div>
              <!--reviews_wrapper-->

              <div class="paging">
                  <c:if test="${pg != 1 && maxPg>0 }">
                <a href="center_detail.bom?ctId=${ctId}&pg=${pg-1}#main_reviews"><i class="fas fa-chevron-circle-left fa-2x"></i></a>
                </c:if>
                <c:if test="${pg != maxPg  && maxPg>0}">
                <a href="center_detail.bom?ctId=${ctId}&pg=${pg+1}#main_reviews"
                  ><i class="fas fa-chevron-circle-right fa-2x"></i
                ></a>
                </c:if>
              </div>
            </div>
            <!--review_board_top-->

            <div id="review_board_bottom">
              <span id="question">직접 상담 받으니 어떠셨나요?</span>
              <span id="recommend">다른 분들을 위해 후기를 남겨주세요!</span>
              
 		<form action="add_review.bom#main_reviews" id="review_form" method="get">
 			<input type="hidden" name="ctId" value ="${ctId}">
 				<input type="hidden" name="pg" value ="${pg}">
              <div id="rate">
                <span id="tit">만족도</span> &nbsp;&nbsp;
                <select id="select_rate" name="selectRate" onchange="setRateStars()" form="review_form">
                  <option value="1">1.0</option>
                  <option value="2">2.0</option>
                  <option value="3" selected >3.0</option>
                  <option value="4">4.0</option>
                  <option value="5" >5.0</option>
                </select>
                
                <span class="stars">
                  <i class="fas fa-star" id="star_1"></i>
                  <i class="fas fa-star" id="star_2"></i>
                  <i class="fas fa-star" id="star_3"></i>
                  <i class="fas fa-star" id="star_4"></i>
                  <i class="fas fa-star" id="star_5"></i>
                </span>
              </div>
              <div class="board">
                  <textarea
                    name="review_write"
                    id="review_write"
                    cols="30"
                    rows="10"
                    placeholder="리뷰를 작성 해주세요."
             		form="review_form"
                  ></textarea>
             	 <c:if test="${reserveR != 1 }">
             	  <c:if test="${!empty mbPKId }">
                	  <input type="submit" class="reviewSubmit" value="작성하기" id="register_review" />
                  	</c:if>
                  </c:if>
                  
                    <c:if test="${reserveR == 1 }">
                  <script>
                  	$('#review_write').prop('readonly',true);
                  	 $('#review_write').attr('placeholder','상담소 상담 후 이용해주세요' );
                  </script>
                  </c:if>
                  
                   <c:if test="${empty mbPKId }">
                  <script>
                  	$('#review_write').prop('readonly',true);
                  	 $('#review_write').attr('placeholder','로그인 후 이용해주세요' );
                  </script>
                  </c:if>
                  
                </form>
              </div>
            </div> <!--review_board_bottom-->
            
          </div> <!--main_review-->
          
        </div> <!-- info_main_wrapper -->
        
      </div> <!-- main_container -->
    </div> <!--main-->


	<%@include file="../center/_ct_detail_side.jsp" %>
	<%@include file="../common/_footer.jsp" %>
	<%@include file="../common/_side.jsp" %>
      <style type="text/css">
  .fa-2x {	
  	font-size: 1.3em;
  }
  </style>
  </body>
</html>
