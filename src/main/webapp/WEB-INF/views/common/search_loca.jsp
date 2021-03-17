<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link href="${pageContext.request.contextPath}/resources/css/search/basichome_center_location_view.css" rel="stylesheet" type="text/css" />
    <div class="containerr jogak_loc"  id ="sub_loc" onmouseover="focusinloc()" onmouseout="focusoutloc()" >
    <div class="containerr CLV_top">
        <p class="CLV_top_title fts5"> 어떤 고민이 있으신가요?</p>
        <br>
        <hr class = "CLV_top_hr">
    </div>
    
    <div class = "containerr CLV_search">
         <form action="center_search.bom" method="get" id="searching">
            <input type="hidden" id="loc" name="reg" />
             <div class = "CLV_serch_text">
           
                 <span class="searchicon"><i class="fas fa-search fa-2x"></i></span>
        <span class="searchtext_input">   
            <input type="text" name="keyword" class= "search_box_location" placeholder="원하는 상담소/고민을 입력해주세요">
                 </span>
        </div> 
             <!-- 검색창-->
             <div class ="containerr CLV_location">
                 <span class="CLV_locbtn CLV_loc1">서울</span>
                 <span class="CLV_locbtn CLV_loc2">경기</span>
                 <span class="CLV_locbtn CLV_loc3">인천</span>
                 <span class="CLV_locbtn CLV_loc4">강원</span>
                 <span class="CLV_locbtn CLV_loc5">충북</span>
                 <span class="CLV_locbtn CLV_loc6">충남</span>
                 <span class="CLV_locbtn CLV_loc7">대전</span>
                 <span class="CLV_locbtn CLV_loc8">전북</span>
                 <span class="CLV_locbtn CLV_loc9">전남</span>
                 <span class="CLV_locbtn CLV_loc10">경북</span>
                 <span class="CLV_locbtn CLV_loc11">경남</span>
                 <span class="CLV_locbtn CLV_loc12">대구</span>
                 <span class="CLV_locbtn CLV_loc13">제주</span>
                 <span class="CLV_locbtn CLV_loc14">독도</span>
                 <span class="CLV_locbtn CLV_loc15">울릉도</span>
                 <span class="CLV_locbtn CLV_loc16">북한</span>
                 <span class="CLV_locbtn CLV_loc17">마라도</span>
                 <span class="CLV_locbtn CLV_loc18">대마도</span>
                 <span class="CLV_locbtn CLV_loc19">니혼</span>
             </div>
              <hr class = "CLV_top_hr">
           	<input type ="submit" class="search_locgory_center_btn" value="상담소 찾기" >
            <script>
                 $(".CLV_locbtn").click(function(e){
                	 $('.CLV_locbtn').removeClass("onLoc");	 
                     let locSpan = $(e.target);
                     locSpan.toggleClass("onLoc");
                 });
                    $(".search_locgory_center_btn").click(function(){
                       $.each($('.CLV_locbtn'),function(index,item){
                         if($(item).hasClass("onLoc") === true){
                        	 $('#loc').val(index);
                         } 
                     }); 
                });
             </script>
        </form>
    </div>
    </div>
   