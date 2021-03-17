<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./common/_common.jsp"%>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>혼자 앓지 마세요,누리봄</title>

<script type="text/javascript">
	var CTX = '${pageContext.request.contextPath}';
	$(document).ready(function() {
		//main_content.css 변경함! 
		var nuriBoardUrl = CTX + "/nuri_board_limit.bom";
		$('.today-board').load(nuriBoardUrl);

	});
</script>

</head>
<body>

	<%@include file="./common/_header.jsp"%>
	<div id="main">

		<%@include file="./common/main/_visual.jsp"%>


		<div class="container recommend-box">

			<h2>
				많은 분들이 찾아주시는 <br> <span class="recommend-highlight">누리봄
					추천 상담소</span>
			</h2>

			<!-- 슬릭 이미지(좋아요 많은순) -->
			<div class="center-container-box">
				<div class="recommend-slick">

					<c:forEach var="ct" items="${ctList}">
						<a
							href="${pageContext.request.contextPath}/center_detail.bom?ctId=${ct.sId}">
							<div class="center-item">
								<img
									src="${pageContext.request.contextPath}/resources/images/center/${ct.sMainImg}"
									alt="center_${ct.sId}" class="center-img">
								<h4 class="center-title">${ct.sName}</h4>
								<div class="review-heart">
									<i class="fas fa-heart fa-2x"></i> <i
										class="fas fa-heart fa-2x"></i> <i class="fas fa-heart fa-2x"></i>
									<i class="fas fa-heart fa-2x"></i> <i
										class="fas fa-heart fa-2x"></i>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>

				<div class="slick-arrow-left arrow slick-arrow prev">
					<i class="fas fa-chevron-circle-left fa-2x"></i>
				</div>

				<div class="slick-arrow-right arrow slick-arrow next">
					<i class="fas fa-chevron-circle-right fa-2x"></i>
				</div>

			</div>


		</div>
		<!--상담소 슬릭 더미 추천-->

		<%@include file="./common/main/_nuristorty.jsp"%>

		<!-- board -->
		<div class="container main-bottom-board">

			<div class="board-grid-box">

				<div class="today-board">


				</div>


				<div class="question-board">


					<a href="${pageContext.request.contextPath}/faq_list.bom"><img
						src="${pageContext.request.contextPath}/resources/images/question.png"
						alt="question"></a>
					<h3>자주 묻는 질문</h3>
					<h4>
						누리봄에게 <b>궁금한 점</b>이 있다면?
					</h4>


				</div>

				<div class="call-board">


					<a href="${pageContext.request.contextPath}/center_contacts.bom"><img
						src="${pageContext.request.contextPath}/resources/images/call.png"
						alt="center-call"></a>
					<h3>국가상담센터 연결</h3>
					<h4>
						혼자라고 느껴질 땐 <b>도움</b>을 요청해주세요.
					</h4>


				</div>
			</div>


		</div>
		<!--board 영역-->




	</div>
	<!--main-->

	<%@include file="./common/_footer.jsp"%>
	<%@include file="./common/_side.jsp"%>
	<%@include file="./common/main/_slick.jsp"%>

</body>
</html>