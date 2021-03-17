<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <script src="${pageContext.request.contextPath}/resources/js/_header.js"></script>
<div id="header">
      <div class="container header-container">
        <div class="header-container-left">
          <h1 class="logo">
             <a href="${pageContext.request.contextPath}/home.bom">누리봄</a> 
          </h1>

          <ul>
              <li><a href="${pageContext.request.contextPath}/center_list.bom">상담소</a></li>
              <li><a href="${pageContext.request.contextPath}/nuri_story.bom">살펴보기</a></li>
              <li><a href="${pageContext.request.contextPath}/free_board_list.bom?pg=1">마음공유</a></li>
          </ul>
    
        </div>

       <div class="header-container-center">

                <ul>
      <li class="center-recommend" ><a onmouseover="hongjae()" onmouseout="hongjae2()"  class="showcate" ><i class="fas fa-question-circle fa-1x select"></i>무엇이 고민이세요?</a></li>
      <li class="center-recommend"><a  onmouseover="focusinloc()" onmouseout="focusoutloc()" class="showloca" ><i class="fas fa-map-marker-alt fa-1x select"></i>어디서 만날까요?</a></li>

                </ul>
          <%@include file="./search_cate.jsp" %>
          <%@include file="./search_loca.jsp" %>
       <script>
	         function  hongjae(){
                $("#sub").stop().fadeIn(700);
                $("#sub").addClass("show");
                
                
	         }
	         
	         function hongjae2(){
                $("#sub").stop().fadeOut(300);
                $("#sub").removeClass("show");
	         }
        </script>
         <script>
	         function focusinloc(){
                $("#sub_loc").stop().fadeIn(700);
                $("#sub_loc").addClass("show_loc");
	         }
	         
	         function focusoutloc(){
                $("#sub_loc").stop().fadeOut(300);
                $("#sub_loc").removeClass("show_loc");
	         }
        </script>

                

        </div>
        <div class="header-container-right">


			<%@include file="_notibell.jsp" %>

               
                
                <c:if test="${ empty mbPKId}">
                <li><a href="${pageContext.request.contextPath}/member_login_form.bom" id="loginBtn">로그인</a></li>
                </c:if>
                
               	<c:if test="${not empty mbPKId}">
                <li><a href="${pageContext.request.contextPath}/member_reserve.bom" class="member-page">마이페이지</a></li>
                <li><a href="${pageContext.request.contextPath}/member_logout.bom">로그아웃</a></li>
              	</c:if>
                

                
            </ul>
        </div>
     </div>
    </div><!--header-->