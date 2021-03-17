<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자주 묻는 질문</title>

<link href="${pageContext.request.contextPath}/resources/css/board/content.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/board/faq-detial.css" rel="stylesheet" type="text/css" />
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

            
              <div class="user-detail-top">

              <h2>${board.title }</h2>
             <div class="user-detail-info">
               
                <ul>
                  <li >by ${board.writer}</li>
                  <li >조회수 ${board.view}</li>
                 <li ><fmt:formatDate value="${board.createdAt}" 
						pattern="yyyy.MM.dd" /></li>
                </ul>

             </div>
            </div>

            <div class="detail-content">

           			
					${board.content}

            </div>
	
	    <div class="free-detail-list">
		   <button type="button" class="free-allList-page-go">목록</button>
        </div>
   
   
   
         

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>
 
 <script type="text/javascript">
$('.free-allList-page-go').click(function(){
	
	location.href="${pageContext.request.contextPath}/faq_list.bom?pg=${pn}";
    
}); 
</script>

</body>
</html>