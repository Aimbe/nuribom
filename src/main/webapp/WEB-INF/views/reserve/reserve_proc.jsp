<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file ="../common/_common.jsp" %>
    <link href="${pageContext.request.contextPath}/resources/css/reserve/reserve_proc.css" rel="stylesheet" type="text/css" />

    <script>

      $(document).ready(function() {
 

        var currentPosition = parseInt($("#side-fix-btn").css("top"));
         $(window).scroll(function() { 
           var position = $(window).scrollTop();
            $("#side-fix-btn").stop().animate({"top":position+currentPosition+"px"},800);
          
          });




          var cNum = 'hide';
          
        $('.fa-bell').click(function(){

            

                if(cNum =='hide'){

                $('.show-bell').css('display','block');

                 /*$('.show-bell').fadeIn(600);*/
                  cNum ='show';
                 
                }else{

                  $('.show-bell').css('display','none');
                  /*$('.show-bell').fadeOut(600);*/
                  cNum ='hide';
                }


          });
          
        });    
      

    

    </script>
    
    
  
  </head>
  <body>
      <%@ include file ="../common/_header.jsp" %>
  
        
<div id="main">
<br><br><br><br>
	<div class="container reserve_proc_title">
		<p class="reserve_proc_title_msg"> 예약완료 </p>
        <p class="reserve_proc_title_submsg"> 상담예약이 완료되었습니다</p>
	</div><br><br>
<div class="container">
    <hr class="reserve_proc_hr">
    </div>
    
	
<div class="container reserve_proc_span">
    <span class="reserve_center reserve_proc_center_image"> 
    
    <img src= "${pageContext.request.contextPath}/resources/images/center/${ct.mainImg}" width=400px height="300px" class="reserve_proc_center_image_picture">
        </span>
  <span class="reserve_center reserve_proce_center_text">
        <div class="reserve_proc_span_name">${ct.name }</div>
        <div class="reserve_proc_span_address">${ct.addressRegion}&nbsp;${ct.addressCity }</div>
        <div class="reserve_proc_span_day">상담 일자: ${day}(${yoil})</div>
        <div class="reserve_proc_span_time">
        <c:if test="${rs.reserveTime > 12}">
    	<c:out value="상담시간: PM ${rs.reserveTime - 12 }:00" />
    	</c:if>
        <c:if test="${rs.reserveTime < 12}">
    	<c:out value="상담시간: AM ${rs.reserveTime}:00" />
    	</c:if>
    	<c:if test="${rs.reserveTime == 12}">
    	<c:out value="상담시간: PM ${rs.reserveTime}:00" />
    	</c:if>
        
        
        </div>
        <div class="reserve_proc_span_tell">tel)${ct.telephone }</div>
    	<input type="button" onclick="location.href='${pageContext.request.contextPath}/member_reserve.bom'" class="goMyReserve" value="나의 예약관리">
    </span>
</div>
    <br> <br> <br> <br><br>
    <div class="container">
    <hr class="reserve_proc_hr">
    </div>
    <div class="container">
        <input type ="button" id="search_category_center_btn" value="메인홈으로" 
         onclick="location.href='${pageContext.request.contextPath}/home.bom'"
       >
<!--         onclick="goMainHome()"> -->
    </div>
    <script>
    	function goMainHome(){
    	location.href("");	
    	}
    </script>
    
    <br><br>    <br><br>

    <br>
      

 <%@ include file ="../common/_footer.jsp" %>
 
  </body>
</html>
