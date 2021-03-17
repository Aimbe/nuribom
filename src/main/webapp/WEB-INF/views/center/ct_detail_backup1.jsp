<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>
    
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>누리봄</title>
  <link href=" ${pageContext.request.contextPath}/resources/css/center/center_detail.css?after" rel="stylesheet" type="text/css" />
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAjPS3hZ8oiTu0YA8bDTDewLM2kMNXPRPw&callback=initMap&libraries=&v=weekly"
      defer
    ></script>
    <!-- google Map Api -->

<script type="text/javascript">
var CTX = '${pageContext.request.contextPath}';
	$(document).ready(function() {

		var mbId = '<c:out value="${mbPKId}"/>'; //${mbPKId};
		var ctId = '<c:out value="${ct.id}"/>'; //${ct.id};
		var targetUrl = CTX + "/center_like.bom";
		var params =  "?mbId="+mbId + "&ctId="+ctId;
// 		var targetUrl = url + params;

		var target = '#ctlike_'+ mbId+"_"+ctId;

		$(target).on("click", function() {
			console.log(mbId);
			console.log(ctId);
			console.log(target);
			
		$ajax({
			type: 'get',
			url: targetUrl,
			data: "mbId="+mbId+"&ctId="+ctId,
			success: function(res) {
				console.log(res);
				$(target).removeClass('like');
				$(target).removeClass('delete');
			
				
				//msg
				var msg = '';
				switch(res) {
					case 'like':
						msg = '관심상담소에 저장했습니다';
					case 'delete':
						msg = '관심상담소에 저장했습니다';
					default: 
						msg = 'ERROR';
				}
				
				$(target).addClass(res);
				$(target).html(msg);
		
			}
	
		});
			
	}); //on

</script>


<%-- // 	var CTX = '${pageContext.request.contextPath}'; --%>
<!-- // 	// 관심상담소 담기/취소(좋아요) likeCenterInDetailPg -->
<!-- // 	function likeCenter(mbId,ctId) { -->
<!-- // 		var url = CTX + "/center_like.bom"; -->
<!-- // 		var params = "?mbId="+mbId + "&ctId="+ctId; -->
<!-- // 		url = url + params; -->

<!-- // 		//비동기.. -->
<!-- // 		var target = '#ctlike_'+ mbId+"_"+ctId; -->
<!-- // 		$(target).load(url, function(res) { -->
<!-- // 			console.log(res); -->
<!-- // 		}); -->
		
<!-- // 	} -->
	


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

        // 좋아요 클릭
//         var check = "unselected";
//         $(".btn_like").click(function () {
//           if (check == "unselected") {
//             $(".btn_like_icon").css("color", "palevioletred");
//             check = "selected";
//           } else {
//             $(".btn_like_icon").css("color", "rgb(235, 206, 152)");
//             check = "unselected";
//           }
//         });

        //리뷰 평점클릭시 별점아이콘 처리
        $("#select_rate").change(function () {
          var rate = $(this).val();
          switch (rate) {
            case "1.0":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(92, 86, 86)");
              $("#star_3").css("color", " rgb(92, 86, 86)");
              $("#star_4").css("color", " rgb(92, 86, 86)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "2.0":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(92, 86, 86)");
              $("#star_4").css("color", " rgb(92, 86, 86)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "3.0":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(184, 184, 55)");
              $("#star_4").css("color", " rgb(92, 86, 86)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "4.0":
              $("#star_1").css("color", " rgb(184, 184, 55)");
              $("#star_2").css("color", " rgb(184, 184, 55)");
              $("#star_3").css("color", " rgb(184, 184, 55)");
              $("#star_4").css("color", " rgb(184, 184, 55)");
              $("#star_5").css("color", " rgb(92, 86, 86)");
              break;
            case "5.0":
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
                <span class="info_time">운영시간 ${center.openTime} ~ ${center.closeTime}(평일)</span>
            	<span class="info_site" id="check_site">Site. <a href="${center.site}">${center.site}</a></span>
              </div>
              <div class="top_btns">
              
		              <div id="ctlike_${mbPKId}_${center.id}" class="like_wrapper" >
		              
		                <button type="button" class="btn_like"> 
<%-- 		                onclick="likeCenter(${mbPKId},${center.id})" > --%>
	                 		  <span title="관심상담소 저장">
				                 	 <i class="fas fa-heart fa-2x btn_like_icon ${selStatus eq null ? '' : ' selectedCt'}""></i>
					                  <span class="like_count">${center.likes}</span>
		                  		</span>
		                </button>
		                
		              </div>
	
	                <div class="btn_reserve_wrap">
	                  <button type="button" class="btn_reserve">예약하기</button>
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
              <a href=""
                >리뷰모음 &nbsp;<i class="fas fa-arrow-circle-right fa-1x"></i
              ></a>
            </div>

            <div class="review_board_top">
              <div class="review_info">
                <i class="fas fa-star fa-2x" id="star"></i>
                <span id="avg_rate"> 4.91점 </span>
                <span id="total_review_count">(11개 리뷰)</span>
              </div>

              <div class="reviews_wrapper">
                <div class="review_box">
                  <div class="write_info">
                    <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    <span class="writer">jinni***</span>
                    <span class="written_at">2020.11.20 15:52</span>
                    <input type="button" value="수정" id="review_change" />
                    &nbsp;
                    <input type="button" value="삭제" id="review_delete" />
                  </div>
                  <div class="comment">
                    <p>
                      이야기를 차분하게 들어주시면서도 잘 이끌어 나가 주십니다.
                      덕분에 마음에 쌓아둔 것이 많이 해소됐어요. 처음이라면
                      고민하지말고 상담받아 보세요!
                    </p>
                  </div>
                </div>

                <div class="review_box">
                  <div class="write_info">
                    <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    <span class="writer">jinni***</span>
                    <span class="written_at">2020.11.20 15:52</span>
                  </div>
                  <div class="comment">
                    <p>
                      이야기를 차분하게 들어주시면서도 잘 이끌어 나가 주십니다.
                      덕분에 마음에 쌓아둔 것이 많이 해소됐어요. 처음이라면
                      고민하지말고 상담받아 보세요!
                    </p>
                  </div>
                </div>

                <div class="review_box">
                  <div class="write_info">
                    <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    <span class="writer">jinni***</span>
                    <span class="written_at">2020.11.20 15:52</span>
                  </div>
                  <div class="comment">
                    <p>
                      이야기를 차분하게 들어주시면서도 잘 이끌어 나가 주십니다.
                      덕분에 마음에 쌓아둔 것이 많이 해소됐어요. 처음이라면
                      고민하지말고 상담받아 보세요!이야기를 차분하게
                      들어주시면서도 잘 이끌어 나가 주십니다. 덕분에 마음에
                      쌓아둔 것이 많이 해소됐어요. 처음이라면 고민하지말고
                      상담받아 보세요!
                    </p>
                  </div>
                </div>

                <div class="review_box">
                  <div class="write_info">
                    <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    <span class="writer">jinni***</span>
                    <span class="written_at">2020.11.20 15:52</span>
                  </div>
                  <div class="comment">
                    <p>
                      이야기를 차분하게 들어주시면서도 잘 이끌어 나가 주십니다.
                      덕분에 마음에 쌓아둔 것이 많이 해소됐어요. 처음이라면
                      고민하지말고 상담받아 보세요!이야기를 차분하게
                      들어주시면서도 잘 이끌어 나가 주십니다. 덕분에 마음에
                      쌓아둔 것이 많이 해소됐어요.
                    </p>
                  </div>
                </div>

                <div class="review_box">
                  <div class="write_info">
                    <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    <span class="writer">jinni***</span>
                    <span class="written_at">2020.11.20 15:52</span>
                  </div>
                  <div class="comment">
                    <p>
                      이야기를 차분하게 들어주시면서도 잘 이끌어 나가 주십니다.
                      덕분에 마음에 쌓아둔 것이 많이 해소됐어요. 처음이라면
                      고민하지말고 상담받아 보세요!
                    </p>
                  </div>
                </div>

                <div class="review_box">
                  <div class="write_info">
                    <div class="user"><i class="fas fa-user fa-3x"></i></div>
                    <span class="writer">jinni***</span>
                    <span class="written_at">2020.11.20 15:52</span>
                  </div>
                  <div class="comment">
                    <p>
                      이야기를 차분하게 들어주시면서도 잘 이끌어 나가 주십니다.
                      덕분에 마음에 쌓아둔 것이 많이 해소됐어요. 처음이라면
                      고민하지말고 상담받아 보세요!
                    </p>
                  </div>
                </div>
              </div>
              <!--reviews_wrapper-->

              <div class="paging">
                <a href="#"><i class="fas fa-chevron-circle-left fa-2x"></i></a>
                <a href="#"
                  ><i class="fas fa-chevron-circle-right fa-2x"></i
                ></a>
              </div>
            </div>
            <!--review_board_top-->

            <div id="review_board_bottom">
              <span id="question">직접 상담 받으니 어떠셨나요?</span>
              <span id="recommend">다른 분들을 위해 후기를 남겨주세요!</span>

              <div id="rate">
                <span id="tit">만족도</span> &nbsp;&nbsp;
                <select id="select_rate" onchange="setRateStars()">
                  <option>1.0</option>
                  <option>2.0</option>
                  <option selected>3.0</option>
                  <option>4.0</option>
                  <option>5.0</option>
                </select>
                <span class="stars">
                  <i class="fas fa-star" id="star_1"></i>
                  <i class="fas fa-star" id="star_2"></i>
                  <i class="fas fa-star" id="star_3"></i>
                  <i class="fas fa-star" id="star_4"></i>
                  <i class="fas fa-star" id="star_5"></i>
                </span>
              </div>

              <script></script>

              <div class="board">
                <form action="" id="review_write" method="GET">
                  <textarea
                    name="review_write"
                    id="review_write"
                    cols="30"
                    rows="10"
                    placeholder="이용완료한 회원만 작성할 수 있습니다."
                  >
                  </textarea>
                  <input type="submit" value="작성하기" id="register_review" />
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
    
  </body>
</html>
