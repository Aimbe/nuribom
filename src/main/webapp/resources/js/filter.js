  $(document).ready(function() {
 
        // 검색창 reset 버튼 
        $(".btn_reset").click(function () {
          
        });
        
        // 검색어 입력창 클릭시 다른 카테고리창 열려있다면 닫힘
        $("#search_keyword").focus(function() {
            $(".show_category").css("display", "none");
            $(".show_theme").css("display", "none");
            $(".show_filter").css("display", "none");
        });

        // 적용하기 버튼 눌렸을때 열려있던 창닫기
        $(".btn_search_apply").click(function() {
            $(".show_category").css("display", "none");
            $(".show_theme").css("display", "none");
            $(".show_filter").css("display", "none");
        });

        // 검색하기 눌렀을때 열려있는 창닫기
        $(".btn_search").click(function() {
            $(".show_category").css("display", "none");
            $(".show_theme").css("display", "none");
            $(".show_filter").css("display", "none");
        });

        // 필터창 선택시 옵션기억 
        var filterCheck = "fnew"; //기본 최신순
        $(".filter_new").click(function() {
           filterCheck = "fnew";
          if(filterCheck == "fnew") {
             $(".filter_new").css("background-color", "rgb(161, 172, 89)");
             $(".filter_new").css("color", "white");
             $(".filter_rate").css("background-color", "white");
             $(".filter_rate").css("color", "black");
             $(".filter_review").css("background-color", "white");
             $(".filter_review").css("color", "black");
          } 
        } );
        
        $(".filter_rate").click(function() {
          filterCheck = "frate";
          if(filterCheck == "frate") {
             $(".filter_rate").css("background-color", "rgb(161, 172, 89)");
             $(".filter_rate").css("color", "white");
             $(".filter_new").css("background-color", "white");
             $(".filter_new").css("color", "black");
             $(".filter_review").css("background-color", "white");
             $(".filter_review").css("color", "black");
          } 
        });
        $(".filter_review").click(function() {
          filterCheck = "freview";
          if(filterCheck == "freview") {
             $(".filter_review").css("background-color", "rgb(161, 172, 89)");
             $(".filter_review").css("color", "white");
             $(".filter_new").css("background-color", "white");
             $(".filter_new").css("color", "black");
             $(".filter_rate").css("background-color", "white");
             $(".filter_rate").css("color", "black");
          }
        });
   
        // 카테고리 선택
        $(".btn_category").click(function () {
          if (cNum == "hide") {
            $(".show_category").css("display", "block");
            $(".show_theme").css("display", "none");
            $(".show_filter").css("display", "none");
            cNum = "show";
          } else {
            $(".show_category").css("display", "none");
            cNum = "hide";
          }
        });

        // 고민분야 선택
        $(".btn_theme").click(function () {
          if (cNum == "hide") {
            $(".show_theme").css("display", "block");
            $(".show_category").css("display", "none");
            $(".show_filter").css("display", "none");
            cNum = "show";
          } else {
            $(".show_theme").css("display", "none");
            cNum = "hide";
          }
        });

        // 필터 선택
        $(".btn_filter").click(function () {
          if (cNum == "hide") {
            $(".show_filter").css("display", "block");
            $(".show_category").css("display", "none");
            $(".show_theme").css("display", "none");
            cNum = "show";
          } else {
            $(".show_filter").css("display", "none");
            cNum = "hide";
          }
        });

        // 닫기버튼 클릭해도 사라져야함
        $(".btn_close_category").click(function () {
          $(".show_category").css("display", "none");
        });

        $(".btn_close_theme").click(function () {
          $(".show_theme").css("display", "none");
        });

        $(".btn_close_filter").click(function () {
          $(".show_filter").css("display", "none");
        });

          // 좋아요 클릭
        var check = "unselected";
        $(".fa-heart").click(function () {
          if (check == "unselected") {
            $(this).css("color", "palevioletred");
            check = "selected";
          } else {
            $(this).css("color", "rgb(235, 206, 152)");
            check = "unselected";
          }
        });   


  });

        //checkbox 반복생성
        // 친구 부부 대외관계 연인 가족 학업 직장 진로 취업 육아 해외생활 중독 섭식장애 성생활 성소수자 감정조절 자해 자살 
//        var themes = ["친구", "부부", "대외관계", "연인", "가족", "학업", "직장", "진로", "취업", "육아", "해외생활", "중독", 
//                      "섭식장애", "성생활", "성소수자", "감정조절", "자해", "자살"];
//          var themesEn = ["friend", "married", "socialRelationship", "couple", "family", "study", "company", "career", "job", "childcare", "overseas", "addicted", 
//                      "eatingDisorder", "sexLife", "sexualMinorities", "emotionalControl", "selfHarm", "suicide"];   
//        function createThemeCheckboxes() {
//          for (var i = 0; i < themes.length; i++) {
////            var text = "<li><label for="check_" + themesEn[i] + "">" + themes[i] + "<input type="checkbox" id="check_" + themesEn[i] + ""/></label></li>";
//            // $(".theme_check").append(text);
//            document.write(text);
//          }         
//        }
	                 

        
   
