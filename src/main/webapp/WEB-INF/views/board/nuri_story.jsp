<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 자주 묻는 질문</title>


<link href="${pageContext.request.contextPath}/resources/css/board/nuristory.css" rel="stylesheet" type="text/css" />
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

            
                <img src="${pageContext.request.contextPath}/resources/images/nuristory.png" alt="누리봄 스토리" class="nuri-img">

              <p class="nuristory-ment">
              
			                여러분, 살면서 가끔 우울할 때가 있죠. 혹은 자주거나, 매일이거나요.<br>
			                우울한 마음이 들 땐 모두 그렇게 살겠지 하며 애써 넘기셨나요?<br>
			                대한민국 성인 4명 중 1명이상은 살아가면서 한 번 이상 우울,<br> 불안과 같은 정신 건강 문제를 경험합니다. <br><br>
			
			                내 가족, 내 친구 그리고 그 누구보다 소중한 나 자신에게도 <br>그런 힘들 날들이 찾아올 수 있습니다.<br><br>
			                 하지만 관련 서비스를 받는 사람은 캐나다, 미국, 뉴질랜드의 반 정도 밖에 되지 않죠.<br>
			                  친구 또는 가족에게 털어 놓기에는 상대의 시선이 부담스럽고, 정신과 의사 혹은 심리상담사를<br> 찾아 나서기에는
			                  사회적 편견이 우리 발목을 붙잡습니다.<br><br>
			                
			                저희 누리봄은 심리상담의 사회적 편견에서 벗어나 자신의 고민을 말하는 데 부담이 없고<br> 심리상담의 허들을 낮추고자 시작했습니다.<br><br>
			                 누리봄은 나와 가까운 곳에 있는 상담소, 또는 내가 가진 고민들을 <br>전문으로 상담해주는 상담소를 한 눈에 찾을 수 있습니다.<br><br>
			                  내 문제를 자신의 문제처럼 함께 고민해주는 사람들이 모인 '마음공유'에서<br> 서로의 마음을 공유하고 공감하고 연대감을 느낄 수 있어요.<br><br>
			                
			                마음이 다치면, 세상도 닫힙니다. <br>누구에게나 힘든 시간은 오고 어떤 사람에게는 그 시간이 금방 지나가지만<br> 어떤 사람은 오래 머뭅니다.<br><br>
			                 누리봄은 더 많은 사람이 힘든 시간보다 행복한 시간을 누리는<br> 인생을 살 수 있게 되는 세상을 꿈꿉니다.

              </p>
          
   
         

          </div><!-- 실질적 content 영역 -->

		</div><!-- container -->

    </div><!--큰 content 영역-->
  </div><!-- main -->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>