<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 내가 쓴 리뷰</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-review.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/_header.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/member/join_success.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="../common/_header.jsp" %>


   <div id="main">
        <div class="container main-join-success">
            <h1>반갑습니다!</h1> 
            <h2>누리봄 가입에 성공했습니다.</h2> 
            
            <div class="go_login">
            <input type="button" id="go_to_login" value="로그인하러 가기" onclick="location.href='${pageContext.request.contextPath}/member_login_form.bom'"></div>
            
            <div class="go_home">
            <input type="button" id="go_to_home" value="홈화면으로"onclick="location.href='${pageContext.request.contextPath}/'">
            </div>
           
           </div>
        
        
        


    </div><!--main-->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>