<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
    
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/fontawesome.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/solid.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/brands.min.css">
    <link href="/mybom/resources/css/reset.css" rel="stylesheet" type="text/css" />
    <link href=" /mybom/resources/css/main.css?a" rel="stylesheet" type="text/css" />
      <link href=" /mybom/resources/css/main_content.css" rel="stylesheet" type="text/css" />
   <script src="/mybom/resources/js/bell.js"></script>
    
      <link rel="stylesheet" type="text/css" href="/mybom/resources/slick/slick.css"/>
      <link rel="stylesheet" type="text/css" href="/mybom/resources/slick/slick-theme.css"/>
     


     <link href="/mybom/resources/css/board-layout.css" rel="stylesheet" type="text/css" />
  
<head>
<meta charset="UTF-8">
<title>누리봄: 알림창</title>
<link href="${pageContext.request.contextPath}/resources/css/member/noti.css" rel="stylesheet" type="text/css" />

</head>
<body>


<%@include file="../_header.jsp" %>
  
    <div id="main">
	<!-- 레이아웃 비주얼 -->
	 <%@include file="../board/_visual.jsp" %>

	<!-- 카테고리 레이아웃 -->
	 <div class="container submain-content-box">
 	 <%@include file="_my_category.jsp" %>
    
    
            <div class="container contents _contents">
                
              	  <!-- 알림함 -->
           
                
                <div class="alarm_box _noti_box">
                    <h3 class="alarm_title"></h3>
                    
    
    
    
    <div class="container time_line _noti_list">
                        
    
    <div class="daily_box glist">
    
         <h4 class="ico_time">최신 날짜순 정렬</h4>
         
      		 	
      	<c:if test="${not empty noList}">	 	
      		 	
      		 	<c:forEach var="no" items ="${noList}"> 
    
    
    
    <li class="list_content first" style="display:inline" >

           
           
          
          
           
           
            <div class="jbox">&nbsp;&nbsp;&nbsp;
                
            <span class="group1"> 
    
    
    <c:choose>
				
					<c:when test="${no.category eq '0'}">
				
						 <a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" >
  
							    <i class="fas fa-comments fa-4x"></i>
							  </a> 
				
					</c:when>
					
					<c:when test="${no.category eq '1'}">
				
						 <a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" >
  
							    <i class="fas fa-comments fa-4x"></i>
							  </a> 
				
					</c:when>
				
					<c:when test="${no.category eq '2'}">

							<a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" >

							          <i class="fas fa-keyboard fa-4x"></i>
							</a> 

						</c:when>
						
						
						<c:when test="${no.category eq '3'}">

							<a href="" title="연결 페이지로 이동" class="#" target="_blank" >
    
							            <i class="fas fa-user-lock fa-4x"></i>
							    </a> 

						</c:when>
						
						<c:when test="${no.category eq '4'}">

							<a href="" title="연결 페이지로 이동" class="#" target="_blank" >
    						
							            <i class="fas fa-birthday-cake fa-4x"></i>
							    </a> 

						</c:when>
						
						
						<c:when test="${no.category eq '5'}">

							<a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" >
  
									          <i class="fas fa-clinic-medical fa-4x"></i>
									  </a> 

						</c:when>
						
						
						<c:when test="${no.category eq '6'}">

							<a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" >
  
									          <i class="fas fa-pencil-alt fa-4x"></i>
									  </a> 

						</c:when>
						
						<c:when test="${no.category eq '7'}">

							<a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" >
  
									          <i class="fas fa-heart fa-4x"></i>
									  </a> 

						</c:when>
						
					
						<c:otherwise>

							<i class="fas fa-pencil-alt fa-4x"></i>

					</c:otherwise>

			</c:choose>

    
      
            &nbsp;&nbsp;&nbsp;</span>
                
                
                <span class="group1">
                <div><br>
                        
                <div style="font-size: 2.0em">${no.msgTitle}</div>
                
                
                
                
                 <a href="${pageContext.request.contextPath}/${no.link}" title="연결 페이지로 이동" class="#" target="_blank" ><div style="font-size:1.6em">
                 
                   <c:choose>
				
					<c:when test="${no.category eq '0'}">
				
						 ${no.msgDetail}
				
					</c:when>
				
					<c:when test="${no.category eq '1'}">

							 ${no.msgDetail}

						</c:when>
						
						
						<c:when test="${no.category eq '2'}">

							 ${no.msgDetail}

						</c:when>
						
						
						<c:when test="${no.category eq '3'}">

							 ${no.msgDetail}

						</c:when>

						<c:when test="${no.category eq '4'}">

							  ${no.msgDetail}
							
						</c:when>
						
						<c:when test="${no.category eq '5'}">

							<fmt:formatDate value="${no.reserveDay}" 
						pattern="yyyy년 MM월 dd일"/>에&nbsp; ${no.msgDetail}
							
						</c:when>
						
						<c:when test="${no.category eq '6'}">

							  ${no.msgDetail}
							
						</c:when>
						
						
						<c:otherwise>

							<i class="fas fa-pencil-alt fa-4x"></i>

					</c:otherwise>

			</c:choose>
                
     
                   </a></div>
                   
                 <div class ="performer_id">
                 
                  <c:choose>
				
					<c:when test="${no.category eq '0'}">
				
						by ${no.nickname}
				
					</c:when>
				
					<c:when test="${no.category eq '1'}">

							by 관리자

						</c:when>
						
						
						<c:when test="${no.category eq '2'}">

							by 게시글 등록 알리미

						</c:when>
						
						
						<c:when test="${no.category eq '3'}">

							 by 개인정보 변경 알리미

						</c:when>

						<c:when test="${no.category eq '4'}">

							 by 회원 가입 알리미
							
						</c:when>
						
						<c:when test="${no.category eq '5'}">

							 by ${no.name}
							
						</c:when>
						
						<c:when test="${no.category eq '6'}">

							 by ${no.name}
							
						</c:when>
						
						
						<c:otherwise>

							<i class="fas fa-pencil-alt fa-4x"></i>

					</c:otherwise>

			</c:choose>
                 
                 
                 </div><div class="alarm_time"> <fmt:formatDate value="${no.timestampAt}" 
						pattern="yyyy년 MM월 dd일"/></div>
                    
             	
                    
                </div></span>

            

                
              
            </div>
               
        </li>
    
    	</c:forEach> 
    	
      		 	
      		 </c:if>

   
 
    	
      
			</div>
	</div>
	</div>
          
          
            
  <div id="paginate" class ="pgn_btn" style ="margin-left: auto" style ="margin-right :auto">
		<c:if test="${pn > 1}">
			<a href="${pageContext.request
			.contextPath}/all_notifi.bom?pg=${pn-1}"
			><i class="fas fa-chevron-circle-left"></i></a>
		</c:if>
			&nbsp;&nbsp;
		<c:forEach begin="1" end="${maxPg}" step="1" 
			varStatus="vs">
			&nbsp; 
			<c:if test="${vs.current eq pn}">
				<b style="color: blue">${vs.current}</b> <!-- 현재 페이지 -->
			</c:if>	
			<c:if test="${vs.current ne pn}">
			<a href="${pageContext.request
			.contextPath}/all_notifi.bom?pg=${vs.current}"
				>${vs.current}</a>
			</c:if>
			&nbsp; 
			${vs.current eq maxPg ? '' : '|' } 	
		</c:forEach>
			&nbsp;&nbsp;
		<c:if test="${pn < maxPg}">
			<a href="${pageContext.request
			.contextPath}/all_notifi.bom?pg=${pn+1}"
			><i class="fas fa-chevron-circle-right"></i></a>
		</c:if>	
	</div>


           

          </div><!--real-content-->




        </div>

    </div><!--content 영역-->
    
    <%@include file="../_footer.jsp" %>
 <%@include file="../_side.jsp" %>

</body>
</html>