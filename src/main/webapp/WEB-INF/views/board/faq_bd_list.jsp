<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자주 묻는 질문</title>

<link href="${pageContext.request.contextPath}/resources/css/board/user-board.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/board/faq.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
<c:if test="${not empty faqNoneMsg }">

alert('${faqNoneMsg}');

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

            
            <div class="faq-top">
              <i class="fas fa-question-circle fa-5x"></i>
              <h2 class="faq-top-title">

                누리봄 FAQ<br>
                자주 묻는 질문들

              </h2>

              <h3>이용자분들께서 자주 질문해 주시는 내용을 담았습니다.<br>
                참고하셔서 건강한 마음으로 행복한 삶을 만드세요!</h3>

                <div class="faq-search-box">

                  <h6 class="faq-mini-title">누리봄 FAQ</h6>
                  <div class="faq-search-inner-box">
                    <form method="get">
                      <input type="search" class="faq-search"placeholder="게시글의 제목을 입력해주세요." name="faq-search">
                      <button type="button" class="search-faq-btn"><i class="fas fa-search fa-2x search-faq-icon"></i></button>
                    </form>

                  </div>
                </div><!--search-box-->

            </div><!--faq-top-->
		<c:if test="${not empty bdSize}">
			<table class="faq-all-list">

                <tr>
                  <th>No.</th>
                  <th>제목</th>
                </tr>
			<c:set var="num" value="${bdAllcnt-((pn-1)*10)}"/>
            <c:forEach var="bd" items="${bdList}" varStatus="vs">
                <tr>
                  <td class="faq-list-number"><c:out value="${num-vs.index}"/></td>
                  <td><a href="faq_show_page.bom?bdId=${bd.id}&pg=${pn}">${bd.title}</a></td>
                </tr>
			</c:forEach>
               
            </table>
		</c:if>
		  <c:if test="${not empty bdSize}">
            <div class="faq-board-page">

              <button type="button" class="page-btn prev"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
             
             
               <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
             
	             <c:if test="${pn eq vs.current}">
	              <button type="button" class="page-btn faq-sel-page">${vs.current}</button>
	           	 </c:if>
                  
                 <c:if test="${pn ne vs.current}">
                  <button type="button" class="page-btn" onclick="movePage(${vs.current})">${vs.current}</button>
                 </c:if>
               </c:forEach>
              
              
              <button type="button" class="page-btn next"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
            </div>
   		  </c:if>
   
   
   
         

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>
 
 <script type="text/javascript">
 
$('.prev').click(function(){

		<c:if test="${pn gt 1 }">
			location.href="${pageContext.request.contextPath}/faq_list.bom?pg=${pn-1}";
		</c:if>
	});


$('.next').click(function(){

	<c:if test="${pn lt maxPg }">
		location.href="${pageContext.request.contextPath}/faq_list.bom?pg=${pn+1}";
	</c:if>
});


function movePage(e){
	
    location.href ="${pageContext.request.contextPath}/faq_list.bom?pg="+e;

}

	
 
 </script>
</body>
</html>