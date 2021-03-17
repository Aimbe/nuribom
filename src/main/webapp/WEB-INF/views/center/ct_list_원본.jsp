<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>누리봄</title>
    
     <link href=" ${pageContext.request.contextPath}/resources/css/center/center_list.css" rel="stylesheet" type="text/css" />
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

          <div class="row filter_options" id="filter_options">
                <!--카테고리 -->
              <div id="category_wrapper">
                <select name="sel_category" id="center_category">
                    <option value="all" class = "selected">전체상담소</option>
                    <option value="domestic" >가정상담소</option>
                    <option value="sexualViolence">성폭력상담소</option>
                    <option value="teenager">청소년상담소</option>
                    <option value="suicide">자살예방센터</option>
                    <option value="multicultural">다문화가족</option>
                </select>
              </div>
                <!-- 태그 -->
                <div id="tag_wrapper">
                  <select name="sel_tag" id="center_tag">
                    <option value="all" class = "selected">모든고민</option>
                    <option value="friend" >친구</option>
                    <option value="married" >부부</option>
                    <option value="socialRelationship" >대외관계</option>
                    <option value="couple" >연인</option>
                    <option value="family" >가족</option>
                    <option value="study" >학업</option>
                    <option value="company" >직장</option>
                    <option value="career" >진로</option>
                    <option value="job" >취업</option>
                    <option value="childcare" >육아</option>
                    <option value="overseas" >해외생활</option>
                    <option value="addicted" >중독</option>
                    <option value="eatingDisorder" >섭식장애</option>
                    <option value="sexLife" >성생활</option>
                    <option value="sexualMinorities" >성소수자</option>
                    <option value="emotionalControl" >감정조절</option>
                    <option value="selfHarm" >자해</option>
                    <option value="suicide" >자살</option>
                  </select>
                </div>
                  <!-- 필터 -->
                  <div id="filter_wrapper">
                    <select name="sel_filter" id="center_filter">
                            <option value="all" class = "selected">최신순 </option>
                            <option value="domestic" >오래된 순</option>
                            <option value="sexualViolence">리뷰 많은순</option>
                    </select>
                </div>
          </div> <!-- 검색 옵션 -->
          

      



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
