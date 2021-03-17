<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 도움이 되는 글</title>


<link href="${pageContext.request.contextPath}/resources/css/board/assistance.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
<c:if test="${not empty nuriNoneMsg }">

alert('${nuriNoneMsg}');

</c:if>
</script>
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

            <div class="article-all">

              <div class="article-search">
                <form method="get">
                  <input type="search" class="board-search"placeholder="게시글의 제목을 입력해주세요." name="assistance-search">
                  <button type="button" class="search-ass-btn"><i class="fas fa-search fa-2x search-ass-icon"></i></button>
                </form>
                </div>
			<c:if test="${not empty bdSize}">
              <div class="article-grid-box">
				<c:forEach var="bd" items="${bdList }" varStatus="vs">
                  <div class="article-item">
                    <a href="nuri_board_show.bom?bdId=${bd.id}&pg=${pn}"><img src=".${bd.thumbnail }" width ="400px" height="400px" alt="nuriboard_tumbnail_${vs.index}" class="article-thumbnail"></a>
                    <h3 class="article-title"><a href="nuri_board_show.bom?bdId=${bd.id}&pg=${pn}">${bd.title}</a></h3>
                    <h4 class="article-date"><fmt:formatDate value="${bd.createdAt}" pattern="yyyy.MM.dd"/> </h4>
                  </div>
                 </c:forEach>

              </div>
              </c:if>
              
             <c:if test="${bdSize eq 0}">
			   <h2 class="none-board">작성된 게시글이 없습니다.</h2>
			 </c:if>
			  
              
              <!--페이지 쪽수-->
              <c:if test="${not empty bdSize }">
              <div class="assistance-board-page">

                <button type="button" class="page-btn prev"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
              
              	<c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
              	
              	 <c:if test="${pn eq vs.current }">
                 	<button type="button" class="page-btn assistance-sel-page">${vs.current}</button>
                 </c:if>
                 <c:if test="${pn ne vs.current }">
                 	<button type="button" class="page-btn" onclick="movePage(${vs.current})">${vs.current}</button>
                 </c:if>
                </c:forEach>
                <button type="button" class="page-btn next"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>

              </div>
			  </c:if>
		    </div>
         
   
         

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>


<script type="text/javascript">

$('.prev').click(function(){

	<c:if test="${pn gt 1 }">
		location.href="${pageContext.request.contextPath}/nuri_board_list.bom?pg=${pn-1}";
	</c:if>
});


$('.next').click(function(){

	<c:if test="${pn lt maxPg }">
		location.href="${pageContext.request.contextPath}/nuri_board_list.bom?pg=${pn+1}";
	</c:if>
});

function movePage(e){
	
    location.href ="${pageContext.request.contextPath}/nuri_board_list.bom?pg="+e;

}


</script>


</body>
</html>