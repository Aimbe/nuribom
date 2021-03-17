<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link href="${pageContext.request.contextPath}/resources/css/search/basichome_center_category_view.css" rel="stylesheet" type="text/css" />
    <div class="containerr jogak" id="sub"  onmouseover="hongjae()" onmouseout="hongjae2()">
    <div class="containerr CCV_top">
        <p class="CCV_top_title fts5">어떤 고민이 있으신가요?</p>
        <br>
        <hr class = "CCV_top_hr">
    </div>
    
    <div class = "containerr CCV_search">
         <form action="center_search.bom" method="get" id="searching">
             <div class = "CCV_serch_text">
           
                 <span class="searchicon"><i class="fas fa-search fa-2x"></i></span>
        <span class="searchtext_input">   
            <input type="text" name="keyword" class="search_box_category" placeholder="원하는 상담소/고민을 입력해주세요">
                 </span>
        </div> 
             <!-- 검색창-->
             <div class ="containerr CCV_category" style="margin-left:15px">
                 <span class="CCV_catebtn CCV_cate2">친구</span>
                 <span class="CCV_catebtn CCV_cate3">부부</span>
                 <span class="CCV_catebtn CCV_cate1">대인관계</span>
                 <span class="CCV_catebtn CCV_cate4">연인</span>
                 <span class="CCV_catebtn CCV_cate5">가족</span>
                 <span class="CCV_catebtn CCV_cate6">학업</span>
                 <span class="CCV_catebtn CCV_cate7">직장</span>
                 <span class="CCV_catebtn CCV_cate8">진로</span>
                 <span class="CCV_catebtn CCV_cate9">취업</span>
                 <span class="CCV_catebtn CCV_cate10">육아</span>
                 <span class="CCV_catebtn CCV_cate11">해외생활</span>
                 <span class="CCV_catebtn CCV_cate12">중독</span>
                 <span class="CCV_catebtn CCV_cate13">섭식장애</span>
                 <span class="CCV_catebtn CCV_cate15">성생활</span>
                 <span class="CCV_catebtn CCV_cate16">성소수자</span>
                 <span class="CCV_catebtn CCV_cate17">감정조절</span>
                 <span class="CCV_catebtn CCV_cate18">자해</span>
                 <span class="CCV_catebtn CCV_cate19">자살</span>
             </div>
              <hr class = "CCV_top_hr">
            <input type="hidden" id="cate" name="sel_tag" value="all" />
           	<input type ="submit" class="search_category_center_btn" value="상담소 찾기" >
            <script>
               let arrOn = new Array();
                
                 $(".CCV_catebtn").click(function(e){
                     let obj = $(e.target);
                     obj.toggleClass("on");
               		$(".search_category_center_btn").addClass("showbutton");
                                 
                 });
                    $(".search_category_center_btn").click(function(){
                       $.each($('.CCV_catebtn'),function(index,item){
                         if($(item).hasClass("on") === true){
                             arrOn.push(index);
                         } 
                     }); 
                     $("#cate").val(arrOn);
                     $("#cate").val(arrOn[0]);
                     
                });
                    
                    
                  
                       
             </script>
        </form>
    </div>
    </div>