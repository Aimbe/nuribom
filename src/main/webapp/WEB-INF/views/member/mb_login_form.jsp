<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 내가 쓴 리뷰</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-review.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/_header.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/member/login.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="../common/_header.jsp" %>

    
   <div id="main">
        <div class="container main-login">
          <h1>LOGIN</h1> 
          <h2>로그인</h2> 
          
          <form action="member_login.bom" method="post">
            <input type="email" name="email" id="user_id_login" size="40" placeholder="이메일 아이디" required> <br>
            <input type="password" name="password" id="user_pw_login" size="40" placeholder="비밀번호" required> <br>
              <div class="warning"> 
         		<c:if test="${not empty msg}">
         		 <br><font color = "red">아이디와 비밀번호를 확인해주세요.</font>
                </c:if>
              </div>  
         
            <div><button type="submit" id="btn_login">LOGIN</button></div>
            <div class="join"><a href="member_new_form.bom">회원가입</a></div>
            <div class="find"><a href="member_search_pw_form.bom">비밀번호 찾기</a></div>
          </form>
           </div>
        
        


    </div><!--main-->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>