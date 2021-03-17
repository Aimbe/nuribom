<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>누리봄</title>
    
     <link href=" ${pageContext.request.contextPath}/resources/css/center/center_list.css?after" rel="stylesheet" type="text/css" />
<%--      <script src="${pageContext.request.contextPath}/resources/js/filter.js"></script> --%>

<%-- 	<script src="<c:url value="/resources/js/filter.js" />"></script> --%>
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

<script type="text/javascript">
$(document).ready(function () {
     // 검색창 reset 버튼 
     $(".btn_reset").click(function () {
       	location.href = '${pageContext.request.contextPath}/center_list.bom';
     });
     
     // 검색어 입력창 클릭시 다른 카테고리창 열려있다면 닫힘
     $("#search_keyword").focus(function() {
         $(".show_category").css("display", "none");
         $(".show_theme").css("display", "none");
         $(".show_filter").css("display", "none");
     });

     // 적용하기 버튼 눌렸을때 열려있던 창닫기
     $(".btn_search_apply").click(function() {
         $(".show_category").css("display", "none");
         $(".show_theme").css("display", "none");
         $(".show_filter").css("display", "none");
     });

     // 검색하기 눌렀을때 열려있는 창닫기
     $(".btn_search").click(function() {
         $(".show_category").css("display", "none");
         $(".show_theme").css("display", "none");
         $(".show_filter").css("display", "none");
     });

     // 필터창 선택시 옵션기억 
     var filterCheck = "fnew"; //기본 최신순
     $(".filter_new").click(function() {
        filterCheck = "fnew";
       if(filterCheck == "fnew") {
          $(".filter_new").css("background-color", "rgb(161, 172, 89)");
          $(".filter_new").css("color", "white");
          $(".filter_rate").css("background-color", "white");
          $(".filter_rate").css("color", "black");
          $(".filter_review").css("background-color", "white");
          $(".filter_review").css("color", "black");
       } 
     } );
     
     $(".filter_rate").click(function() {
       filterCheck = "frate";
       if(filterCheck == "frate") {
          $(".filter_rate").css("background-color", "rgb(161, 172, 89)");
          $(".filter_rate").css("color", "white");
          $(".filter_new").css("background-color", "white");
          $(".filter_new").css("color", "black");
          $(".filter_review").css("background-color", "white");
          $(".filter_review").css("color", "black");
       } 
     });
     $(".filter_review").click(function() {
       filterCheck = "freview";
       if(filterCheck == "freview") {
          $(".filter_review").css("background-color", "rgb(161, 172, 89)");
          $(".filter_review").css("color", "white");
          $(".filter_new").css("background-color", "white");
          $(".filter_new").css("color", "black");
          $(".filter_rate").css("background-color", "white");
          $(".filter_rate").css("color", "black");
       }
     });

     // 카테고리 선택
     $(".btn_category").click(function () {
       if (cNum == "hide") {
         $(".show_category").css("display", "block");
         $(".show_theme").css("display", "none");
         $(".show_filter").css("display", "none");
         cNum = "show";
       } else {
         $(".show_category").css("display", "none");
         cNum = "hide";
       }
     });

     // 고민분야 선택
     $(".btn_theme").click(function () {
       if (cNum == "hide") {
         $(".show_theme").css("display", "block");
         $(".show_category").css("display", "none");
         $(".show_filter").css("display", "none");
         cNum = "show";
       } else {
         $(".show_theme").css("display", "none");
         cNum = "hide";
       }
     });

     // 필터 선택
     $(".btn_filter").click(function () {
       if (cNum == "hide") {
         $(".show_filter").css("display", "block");
         $(".show_category").css("display", "none");
         $(".show_theme").css("display", "none");
         cNum = "show";
       } else {
         $(".show_filter").css("display", "none");
         cNum = "hide";
       }
     });

     // 닫기버튼 클릭해도 사라져야함
     $(".btn_close_category").click(function () {
       $(".show_category").css("display", "none");
     });

     $(".btn_close_theme").click(function () {
       $(".show_theme").css("display", "none");
     });

     $(".btn_close_filter").click(function () {
       $(".show_filter").css("display", "none");
     });

 

              

});
</script>

  </head>
  <body>
  <%@include file="../common/_header.jsp" %>

    <!-- 메인  -->
    <div id="main">
 <%@include file="../common/board/_visual.jsp" %><!--비주얼-->



      <div class="container main-container">
        <div class="clist_sorting">
          <!-- 상단메뉴바(검색, 카테고리별 체크, 정렬 여러기준으로 등등...) -->

           <!-- 검색어입력창 -->
           <%@ include file="../center/_ct_search_keyword_form.jsp" %>



          <!-- 상담소 위치 필터창 -->
          <div class="row filter_area">
            <div class="sel_place">
              <div class="korea">국내</div>
              <ul>
                <li>
                  <button type="button" class="selected">전체</button> 
                </li>
                <li>
                  <a href=""><button type="button" class="" >서울</button></a>
                </li>
                <li>
                  <a href=""><button type="button" class="">경기</button></a>
                </li>
                <li>
                  <button type="button" class="">부산</button>
                </li>
                <li>
                  <button type="button" class="">강원</button>
                </li>
                <li>
                  <button type="button" class="">경상</button>
                </li>
                <li>
                  <button type="button" class="">전라</button>
                </li>
                <li>
                  <button type="button" class="">대전</button>
                </li>
                <li>
                  <button type="button" class="">대구</button>
                </li>
                <li>
                  <button type="button" class="">제주</button>
                </li>

              </ul>
            </div>
          </div>

            <button type="button" class="btn_reset"><i class="fas fa-sync-alt fa-2x"></i></button>
          <!-- 상담카테고리 & 태그 필터창 -->

          <div class="row filter_options">
            <div class="btn_layer btn_category">
              <button type="button" class="btn_select">
                카테고리
                <i class="fas fa-chevron-down"></i>
              </button>
            </div>
            <div class="btn_layer btn_theme">
              <button type="button" class="btn_select">
                고민분야
                <i class="fas fa-chevron-down"></i>
              </button>
            </div>
            <div class="btn_layer btn_filter">
              <button type="button" class="btn_select">
                최신순
                <i class="fas fa-chevron-down"></i>
              </button>
            </div>
          </div>

          <!-- 카테고리 -->
          <div class="select_box_wrapper show_select_box show_category">
            <div id="selectCategory" class="select_box category">
              <!--  클릭했을때 class에 open추가 -->
              <button type="button" class="btn_close_category">
                <i class="fas fa-times fa-2x fa"></i>
              </button>
              <div class="title">카테고리</div>
              <div class="btn_wrapper">
                <button type="button" class="btn_search_apply">적용하기</button>
              </div>
            </div>

            <ul class="check_list">
              <li>
                <label for="check_all" class="selected"> 전체
                  <input type="checkbox" name="check_cate" value="all"  id="check_all" >
                </label>
              </li>
              <script>
                //  체크박스 전체 선택
                $(".check_list").on("click", "#check_all", function () {
                    $(this).parents(".check_list").find('input').prop("checked", $(this).is(":checked"));
                });
              </script>
              <li>
                <label for="check_domestic">가정상담소
                  <input type="checkbox" id="check_domestic" />
                </label>
              </li>
              <li>
                <label for="check_sexualViolence">성폭력상담소
                  <input type="checkbox" id="check_sexualViolence" />
                </label>
              </li>
              <li>
                <label for="check_teenager">청소년상담소
                 <input type="checkbox" id="check_teenager" />
              </label>
              </li>
              <li>
                <label for="check_suicide">자살예방센터
                 <input type="checkbox" id="check_suicide" />
                </label>
              </li>
              <li>
                <label for="check_multicultural">다문화가족
                  <input type="checkbox" id="check_multicultural" />
                </label>
              </li>
            </ul>
          </div>

          <!-- 고민분야 -->
          <div class="select_box_wrapper show_select_box show_theme" >
            <div id="selectTheme" class="select_box theme">
              <!--  클릭했을때 class에 open추가 -->
              <button type="button" class="btn_close_theme">
                <i class="fas fa-times fa-2x fa"></i>
              </button>
              <div class="title">고민분야</div>
              <div class="btn_wrapper">
                <button type="button" class="btn_search_apply">적용하기</button>
              </div>
            </div>

            <form id="checked_tags" method="GET">
              <ul class="check_list">
                  <!-- var themes = ['친구', '부부', '대외관계', '연인', '가족', '학업', '직장', '진로', '취업', '육아', '해외생활', '중독', 
                                    '섭식장애', '성생활', '성소수자', '감정조절', '자해', '자살'];
                      var themesEn = ['friend', 'married', 'socialRelationship', 'couple', 'family', 'study', 'company', 'career', 'job', 'childcare', 'overseas', 'addicted', 
                        'eatingDisorder', 'sexLife', 'sexualMinorities', 'emotionalControl', 'selfHarm', 'suicide'];    -->
                <li>
                  <label for="check_all" class="selected"> 전체
                    <input type="checkbox" name="check_tags" value="all"  id="check_all" >
                  </label>
                </li>
                <script>
                  //  체크박스 전체 선택
                  // $(".check_list").on("click", "#check_all", function () {
                  //     $(this).parents(".check_list").find('input').prop("checked", $(this).is(":checked"));
                  // });
                  // // 체크박스 개별 선택
                  // $(".check_list").on("click", ".normal", function() {
                  //     var is_checked = true;
                  //     // $(this).parents(".check_list").find('input').prop("checked",false);
                  //     $(".check_list .normal").each(function(){
                  //         is_checked = is_checked && $(this).is(":checked");
                  //     });

                  //     $("#check_all").prop("checked", is_checked);
                  // });

                  $("input[type=checkbox]").on('click', function(){
              if($(this).attr("id") === "check_all"){  //전체선택 클릭시                
                  if($(this).prop("checked")){
                      $(this).closest(".check_list").find("input[type=checkbox]").prop("checked",true);
                  }else{
                      $(this).closest(".check_list").find("input[type=checkbox]").prop("checked",false);
                  }
              } else{ //개별 체크박스 클릭시
                  $(this).closest(".check_list").find("input[name=inputForAll]").prop("checked",false);
              }
          });











                </script>
                <li>
                  <label for="check_freind">친구
                    <input type="checkbox" name="check_tags" value="freind" id="check_freind" class="normal"/>
                </label>
                </li>
                <li>
                  <label for="check_married">부부
                    <input type="checkbox" name="check_tags" value="married" id="check_married"  class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_socialRelationship">대외관계
                    <input type="checkbox" name="check_tags" value="socialRelationship" id="check_socialRelationship"  class="normal"/>
                </label>
                </li>
                <li>                  
                  <label for="check_couple">연인
                    <input type="checkbox" name="check_tags" value="couple" id="check_couple"  class="normal"/>
                </label>
                </li>
                <li>
                  <label for="check_family">가족
                    <input type="checkbox" name="check_tags" value="family" id="check_family"  class="normal"/>
                </label>
                </li>
                <li>
                  <label for="check_study">학업
                    <input type="checkbox" name="check_tags" value="study" id="check_study" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_company">직장
                    <input type="checkbox" name="check_tags" value="company" id="check_company"  class="normal"/>
                </label>
                </li>
                <li>
                  <label for="check_career">진로
                    <input type="checkbox" name="check_tags" value="career" id="check_career" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_job">취업
                    <input type="checkbox" name="check_tags" value="job" id="check_job" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_childcare">육아          
                    <input type="checkbox" name="check_tags" value="childcare" id="check_childcare" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_overseas">해외생활
                    <input type="checkbox" name="check_tags" value="overseas" id="check_overseas" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_addicted">중독
                    <input type="checkbox" name="check_tags" value="addicted" id="check_addicted" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_eatingDisorder">섭식장애
                    <input type="checkbox" name="check_tags" value="eatingDisorder" id="check_eatingDisorder" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_sexLife">성생활
                    <input type="checkbox" name="check_tags" value="sexLife" id="check_sexLife"  class="normal"/>
                </label>
                </li>
                <li>
                  <label for="check_sexualMinorities">성소수자
                    <input type="checkbox" name="check_tags" value="sexualMinorities" id="check_sexualMinorities" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_emotionalControl">감정조절
                    <input type="checkbox" name="check_tags" value="emotionalControl" id="check_emotionalControl" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_selfHarm">자해
                    <input type="checkbox" name="check_tags" value="selfHarm" id="check_selfHarm" class="normal" />
                </label>
                </li>
                <li>
                  <label for="check_suicide">자살
                    <input type="checkbox" name="check_tags" value="suicide" id="check_suicide" class="normal" />
                </label>
                </li>
          
              </ul>
            </form>
          </div>

          <!-- 필터 -->
          <div class="select_box_wrapper show_select_box show_filter">
            <div id="selectCategory" class="select_box filter">
              <!--  클릭했을때 class에 open추가 -->
              <button type="button" class="btn_close_filter">
                <i class="fas fa-times fa-2x fa"></i>
              </button>
              <div class="title">필터</div>
              
              <ul class="check_list">
                <li class="filter_items selected filter_new"> 최신순</li>
                <li class="filter_items filter_rate"> 오래된 순</li>
                <li class="filter_items filter_review"> 좋아요 많은순</li>
              </ul>

          </div>

        </div>

      



	<c:if test="${empty ctList}">
		<h4>상담소가 하나도 없네요!</h4>
	</c:if>
<%-- 	<c:if test="${not empty ctList}"> --%>
<!-- 		<div id="msg" style="border: 1px solid red"> -->
<%-- 			${ctSize} --%>
<!-- 		</div> -->
<%-- 	</c:if> --%>



	<div class="clist_wrap">
        <!-- 상담소 리스트 출력 -->
        <c:if test="${not empty ctList}">
	        <c:forEach var="ct" items="${ctList}" > 
<%-- 	        begin="1" end="10" var="at" varStatus="">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           --%>
	        	<%@ include file="../center/_ct_list_box.jsp" %>
	        </c:forEach>
		</c:if>
    </div>
    
    
    
    
    <!-- 페이지네이션 -->
		 	<div class="user-center-list-page" id="paginate">

				<c:if test="${pn > 1}">
					<a href="${pageContext.request.contextPath}/center_list.bom?pg=${pn-1}"><button type="button" class="page-btn"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button></a>
				</c:if>
				
              <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs"> 
              	<c:if test="${vs.current eq pn}">
              		<button type="button" class="page-btn user-sel-page">${vs.current}</button>
              	</c:if>
				
				<c:if test="${vs.current ne pn}">
					<a href="${pageContext.request.contextPath}/center_list.bom?pg=${vs.current}">
					<button type="button" class="page-btn">${vs.current}</button></a>
				</c:if>              

              </c:forEach>
              
              
	            <c:if test="${pn < maxPg}">
	              	<a href="${pageContext.request.contextPath}/center_list.bom?pg=${pn+1}"><button type="button" class="page-btn"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button></a>
	            </c:if>
            
            
           </div>
    
    
      </div> <!-- main-container -->
    </div> <!--main-->
    

    
    
    <%@include file="../common/_footer.jsp" %>
 	<%@include file="../common/_side.jsp" %>
    
  </body>
</html>
