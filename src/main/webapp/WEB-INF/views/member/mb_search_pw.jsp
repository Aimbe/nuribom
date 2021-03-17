<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/_common.jsp" %>

<head>
<meta charset="UTF-8">
<title>누리봄: 비밀번호 찾기</title>
 <script src="${pageContext.request.contextPath}/resources/js/my-review.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/_header.js"></script>
<%-- <link href="${pageContext.request.contextPath}/resources/css/user-board.css" rel="stylesheet" type="text/css" /> --%>
<link href=" ${pageContext.request.contextPath}/resources/css/member/searchPw.css?aa" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/resources/css/member/pwSendSuccess.css?aa" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="../common/_header.jsp" %>

    
   <div id="main">

        <div class="container searchPw">
            <h1>FIND PW</h1>
            <h2>비밀번호 찾기</h2>
        </div>
        <div class="container searchPw-form" id="loadBox">
        <form method="get">
          <div class="tit">가입 당시 입력하셨던 이메일을 알려주세요</div>
          <div class="input">
            <input type="email" id="email-input" name="email" placeholder="이메일을 입력하세요." value="" required>
          </div>
          <p class="warning" id="email-warning">형식에 맞는 이메일을 입력해주세요</p>
          <p class="warning" id="email-dup-NO">일치하는 이메일이 없습니다.</p>
          <script>
            // 이메일 체크
            $('#email-input').focusout(function(){
              var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
              if( regExp.test(document.getElementById("email-input").value)){
                $('#email-warning').css('display','none');
                $('#sendPW').removeAttr('disabled'); 
              } else {
                $('#email-warning').css('display','block');
                $('#sendPW').prop('disabled', 'disabled');
                
              }

            });
            
            $('#email-input').keyup(function(){
		    	var CTX = '${pageContext.request.contextPath}';
		    	var email = $('#email-input').val();
		    	var targetUrl = CTX+"/emailCheck.bom";
		    	$.ajax({
					type: 'get',
					url: targetUrl,
					data: "email="+email,
					success: function(data) {
						if(data == "success"){
							
		                      $("#email-dup-NO").css('display','block');
		                      $('#sendPW').prop('disabled', 'disabled');							
							
		                 }else if(data == "fail"){
		                	 $("#email-dup-NO").css('display','none');
		                	 $('#sendPW').removeAttr('disabled'); 
		                     
		                 }
					}
		    	   }); 
		    }); 
            
          </script>
             <div class="input_box">
              <div class="tit">비밀번호 확인 질문</div>
              <div class="input">
                <select class="pwQuestion" name="pwQuestion">
                 	<option value="1">기억에 남는 추억의 장소는?</option>
                    <option value="2">자신의 인생 좌우명은?</option>
                    <option value="3">자신의 보물 1호는?</option>
                    <option value="4">가장 기억에 남는 선생님 성함은?</option>
                    <option value="5">내가 좋아하는 캐릭터는?</option>
                    <option value="6">다시 태어나면 되고싶은 것은?</option>
                    <option value="7">기억나는 짝꿍이름은?</option>
                    <option value="8">자신이 두번째로 존경하는 인물은?</option>
                    <option value="9">받았던 선물 중 기억나는 선물은?</option>
              </select>
              </div>
              <div class="input"><input type="text" id="pwAnswer" name="pwAnswer" placeholder="답변을 입력해주세요" value="" required>
              </div>    
              <p class="warning" id="pwAnswer-warning">3자 이상 20자 이하로 입력해주세요</p>
                  <script>
                    // 비밀번호 확인 질문
                    $('#pwAnswer').keyup(function(){
                      if(document.getElementById("pwAnswer").value.length < 3 || 
                      document.getElementById("pwAnswer").value.length > 20 ){
                        $('#pwAnswer-warning').css('display','block'); 
                        $('#sendPW').prop('disabled', 'disabled');	
                        // btn_off();
                      } else{
                        $('#pwAnswer-warning').css('display','none'); 
                        $('#sendPW').removeAttr('disabled'); 
             
                      }
                    });
                    
                    
                    
                    
                  </script>  
              <div class="send_btns"><button type="button" id="sendPW" 
              onclick="beforeCheck()" class="btn_bk" disabled>입력한 이메일로 비밀번호 받기</button></div>
              </div>
              </form>
              <script>
              
              function beforeCheck(){
            		 // 이메일
            		if($('#email-warning').css('display') == 'block' ){
            			alert("형식에 맞는 이메일을 입력해주세요");
            			$('#email-input').focus();
            			
            		}
            		if($('#email-dup-NO').css('display') == 'block' ){
            			alert("일치하는 이메일이 없습니다.");
            			$('#email-input').focus();	
            			
            		}
            		if( $('#email-input').val().length == 0 ){
            			alert("이메일을 입력해주세요.");
            			$('#email-input').focus();	
            			
            		}
            		// 비번 확인 질문 형식
            		
            		if($('#pwAnswer-warning').css('display') == 'block' ){
             			alert("답변은 3자 이상 20자 이하로 입력해주세요");
             			$('#pwAnswer').focus();
             			
             		} 
            		
           		   if($('#pwAnswer').val().length == 0 ){
             			alert("비밀번호 질문의 답변을 입력해주세요.");
             			$('#pwAnswer').focus();
        
             		} 
            		
           		var CTX = '${pageContext.request.contextPath}';
           		var email = $('#email-input').val();
              	var pwQuestion = $("select[name=pwQuestion]").val();
              	var pwAnswer = $('#pwAnswer').val();
              	
              	console.log(email);
              	console.log(pwQuestion);
              	console.log(pwAnswer);
              	
  			    	$.ajax({	
  			        	async: true,
  			            type : 'GET',
  			            contentType: "application/json; charset=UTF-8",
  			        	url :CTX + "/pwQaCheck.bom?email="+email+"&pwQuestion="+pwQuestion+"&pwAnswer="+pwAnswer,
  			        	success : function(data){
  			        	  if(data == "success"){
  			        		  alert("입력하신 메일주소로 비밀번호를 발송했습니다. 잠시 뒤 화면이 전환됩니다.");
  			        		  console.log(data);
  			        		  $('#loadBox').load(CTX+"/password_send_email.bom?email="+email);
  						
  		                 }else if(data == "fail"){
  		                	 alert("비밀번호 확인 질문과 답변이 일치하지 않습니다.");
  		                	
  		                	 
  		                 }
  					}
             		 })
            		
            		
            		
              };
              
              </script>
            </div>
       </div>
            
        </div>  

    </div><!--main-->
 
 
 <%@include file="../common/_footer.jsp" %>
 <%@include file="../common/_side.jsp" %>

</body>
</html>