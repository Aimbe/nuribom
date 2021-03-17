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
		var tIcon = '#heart_'+ctId;
		$(target).load(url, function(res) {
			console.log(res);
			
			var msg = $(".likeMsg").text();
			console.log(msg);
			 $(target).click(function () {
				 switch(msg) {
					case '취소':
						$(tIcon).css('color', 'rgb(235, 206, 152)');				
						break;
					case '성공':
						$(tIcon).css('color', 'red');
						break;
					default:
						break;
				}			
			 });
		});
	}
	
	$(document).ready(function () {
	     // 검색창 reset 버튼 
	     $(".btn_reset").click(function () {
	       	location.href = '${pageContext.request.contextPath}/center_list.bom';
	     });
	     
	    
	     $(".btn_reg").click(function() {
			$(this).addClass('selected');
		});

	     
	     //셀렉트박스 옵션값 저장
	     var cat = '${param.sel_category}';
	     console.log(cat);
	     if( cat == 'all' ) {
	    	$('#all').attr('selected','selected');
	     } else if( cat == 'domestic') {
	    	 $('#dm').attr('selected','selected');
	     } else if( cat == 'sexualViolence') {
	    	 $('#sv').attr('selected','selected');
	     } else if( cat == 'teenager') {
	    	 $('#ta').attr('selected','selected');
	     } else if( cat == 'suicide') {
	    	 $('#sc').attr('selected','selected');
	     } else if(cat == 'multicultural'){
	    	 $('#mc').attr('selected','selected');
	     }
	     
		var ord = '${param.sel_filter}';
		console.log(ord);
		 if( ord == 'recent' ) {
	    	$('#recent').attr('selected','selected');
	     } else if( ord == 'old') {
	    	 $('#old').attr('selected','selected');
	     } else if( ord == 'likes') {
	    	 $('#likes').attr('selected','selected');
	     }
	     
	     
	     
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
<%--            <%@ include file="../center/_ct_search_keyword_form.jsp" %> --%>
		
		
 <form action="${pageContext.request.contextPath}/center_search.bom" method="post">
              <div class="row search">
                <div class="keyword">
                  <span>검색어 입력</span>
                  <input type="text" name="keyword" id="search_keyword" class="input_keyword" placeholder="검색어를 입력하세요"
                  value="${param.keyword}"/>
                </div>
              </div>

              <div class="search_btn_wrapper">
                <button type="submit" class="btn_search">
                  검색하기 <i class="fas fa-search fa-1x"></i>
                </button>
              </div>
              
          <!-- 상담소 위치 필터창 -->
          <div class="row filter_area">
            <div class="sel_place">
              <div class="korea">국내</div>
              <ul>
                <li>
                 <a href="${pageContext.request.contextPath}/center_list.bom"> <button type="button" value="all" class="btn_reg selected">전체</button></a> 
                </li>
                <li>
                  <a href="${pageContext.request.contextPath}/center_list.bom?reg=seoul"><button type="button" value="seoul" class="btn_reg" >서울</button></a>
                </li>
                <li>
                  <a href="${pageContext.request.contextPath}/center_list.bom?reg=kyeong"><button type="button" value="kyeong" class="btn_reg">경기</button></a>
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
                    <option value="all" id="all" selected="selected">전체상담소</option>
                    <option value="domestic" id="dm" >가정상담소</option>
                    <option value="sexualViolence" id="sv" > 성폭력상담소</option>
                    <option value="teenager"  id="ta" >청소년상담소</option>
                    <option value="suicide"  id="sc" >자살예방센터</option>
                    <option value="multicultural" id="mc" >다문화가족</option>
                </select>
<!--                 <button type="submit" id="applicate1">적용</button> -->
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
<!--                   <button type="submit" id="applicate1">적용</button> -->
                </div>
                  <!-- 필터 -->
                  <div id="filter_wrapper">
                    <select name="sel_filter" id="center_filter">
                            <option value="recent" id="recent" class = "selected">최신순 </option>
                            <option value="old" id="old" >오래된 순</option>
                            <option value="likes" id="likes">좋아요 많은순</option>
                    </select>
<!--                     <button type="submit" id="applicate1">적용</button> -->
                </div>
          </div> <!-- 검색 옵션 -->
 </form>         

      



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
	        	<%@ include file="../center/_ct_list_box.jsp" %>
	        </c:forEach>
		</c:if>
    </div>
    
    
    
    
    <!-- 페이지네이션 -->
    	<c:if test="${empty reg}">
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
		</c:if>
		
<%-- 		<c:if test="${not empty reg}"> --%>
<%-- 				<c:if test="${pn > 1}"> --%>
<%-- 					<a href="${pageContext.request.contextPath}/center_list.bom?pg=${pn-1}&reg=${reg}"><button type="button" class="page-btn"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button></a> --%>
<%-- 				</c:if> --%>
				
<%--               <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">  --%>
<%--               	<c:if test="${vs.current eq pn}"> --%>
<%--               		<button type="button" class="page-btn user-sel-page">${vs.current}</button> --%>
<%--               	</c:if> --%>
				
<%-- 				<c:if test="${vs.current ne pn}"> --%>
<%-- 					<a href="${pageContext.request.contextPath}/center_list.bom?pg=${vs.current}&reg=${reg}"> --%>
<%-- 					<button type="button" class="page-btn">${vs.current}</button></a> --%>
<%-- 				</c:if>               --%>

<%--               </c:forEach> --%>
              
              
<%-- 	            <c:if test="${pn < maxPg}"> --%>
<%-- 	              	<a href="${pageContext.request.contextPath}/center_list.bom?pg=${pn+1}&reg=${reg}"><button type="button" class="page-btn"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button></a> --%>
<%-- 	            </c:if> --%>
<%-- 		</c:if> --%>
		    
    
      </div> <!-- main-container -->
    </div> <!--main-->
    

    
    
    <%@include file="../common/_footer.jsp" %>
 	<%@include file="../common/_side.jsp" %>
    
  </body>
</html>
