<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="real-content">


          <div class="container join-member-form">
            <form method="GET" action="member_info_edit_proc.bom" onsubmit="beforeCheck()">
            <div class="container contents">
              <div class="login_wrap">
                <div class="form_wrap">
                  <div class="input_box">
                  <input type="hidden" name="id" id="id" value="${member.id}" readonly>
                    <div class="tit">이메일</div>
                    <div class="input">
                    <input type="hidden" id="pw-input"  maxlength="20" minlength="8" name="password" 
                                    placeholder="비밀번호를 입력하세요." value="${member.password}"  size="27" required readonly> 
                     <input type="text" name="email" id="email" value="${member.email}" readonly>
                    </div>
                   
                  </div>
                  
                  </div>
                  <!-- <div class="login_btns"><button type="button" class="btn_bk btn_sm">인증번호 발송</button>
                 </div> -->
                
                
                  <div class="input_box">
                    <div class="tit">닉네임</div>
                    <div class="input">
                      <input type="text" id="nickname" minlength="3" maxlength="20" 
                      name="name" value="${member.nickname}" placeholder="닉네임을 입력하세요." required>
                    </div>
                    <p class="warning" id="nickDup">이미 사용중인 닉네임입니다.</p>
                    <p class="warning" id="nickOK">사용가능한 닉네임입니다.</p>
                  <p class="warning" id="nick-warning">3자 이상 20자 이하로 입력해주세요</p>
                
                </div>
<!--                    <div class="dup_btns"><button type="button" class="btn_bk btn_sm" onclick="nickCheck()">중복확인</button> -->
                  </div>
                
                <div class="input_box">
                  <div class="tit">비밀번호 확인 질문</div>
                  <div class="input">
                    <select class="pwQuestion" name="pwQuestion" id="pwQuestion">
                      <option value="1" <c:if test="${member.pwQuestion == 1}"> selected </c:if>>기억에 남는 추억의 장소는?</option>
                      <option value="2" <c:if test="${member.pwQuestion == 2}"> selected </c:if>>자신의 인생 좌우명은?</option>
                      <option value="3" <c:if test="${member.pwQuestion == 3}"> selected </c:if>>자신의 보물 1호는?</option>
                      <option value="4" <c:if test="${member.pwQuestion == 4}"> selected </c:if>>가장 기억에 남는 선생님 성함은?</option>
                      <option value="5" <c:if test="${member.pwQuestion == 5}"> selected </c:if>>내가 좋아하는 캐릭터는?</option>
                      <option value="6" <c:if test="${member.pwQuestion == 6}"> selected </c:if>>다시 태어나면 되고싶은 것은?</option>
                      <option value="7" <c:if test="${member.pwQuestion == 7}"> selected </c:if>>기억나는 짝꿍이름은?</option>
                      <option value="8" <c:if test="${member.pwQuestion == 8}"> selected </c:if>>자신이 두번째로 존경하는 인물은?</option>
                      <option value="9" <c:if test="${member.pwQuestion == 9}"> selected </c:if>>받았던 선물 중 기억나는 선물은?</option>
                  </select>
                  </div>
                  <div class="input">
                 <input type="text" id="pwAnswer" maxlength="20" minlength="3"  name="pwAnswer" 
                                        placeholder="답변을 입력해주세요" value="${member.pwAnswer}" size="27"  required>
                  </div>
                  <p class="warning" id="pwAnswer-warning">3자 이상 20자 이하로 입력해주세요</p>
                  <script>
                    // 비밀번호 확인 질문
                    $('#pwAnswer').focusout(function(){
                      if(document.getElementById("pwAnswer").value.length < 3 || 
                      document.getElementById("pwAnswer").value.length > 20 ){
                        $('#pwAnswer-warning').css('display','block'); 
                        // btn_off();
                      } else{
                        $('#pwAnswer-warning').css('display','none'); 
    
                      }
                    });
                  </script>
                </div>
              </div>
              <br>
              <br>
              <div class="save_btns"><button type="button" class="btn_bk" id="editInfoSave">저장하기</button></div>
            </form>
             <script>
            
             $('#nickname').keyup(function(){
            
                 var idStr = $('#nickname').val();
                 
                 $.ajax({
                 	async: true,
                     type : 'GET',
                     
                     contentType: "application/json; charset=UTF-8",
                 	url : "nickCheck.bom?nickname="+idStr,
                     success : function(data){
                          if(data == "success"){
                               $("#nickOK").css('color','blue');
                               $("#nickOK").css('display','block');
                               
                               $("#nickDup").css('display','none');
                          }else if(data == "fail"){
                               $("#nickDup").css('display','block');
                               $("#nickOK").css('display','none');
                          }
                     }
                 });
            });
            
               // 닉네임 길이 제한
               $('#nickname').focusout(function(){
                 if(document.getElementById("nickname").value.length > 20 ||
                    document.getElementById("nickname").value.length < 3 ){
                      $('#nick-warning').css('display','block'); 
                     //  btn_off();
                     } else{
                       $('#nick-warning').css('display','none'); 
                       // !$('.warning').css('display','none') ? null : btn_on();
                     };
               });
            
             
             $('#editInfoSave').click(function(){
            		// console.log("11");
            		
            		var id = $('#id').val();
            		var email = $('#email').val();
            		var nickname = $('#nickname').val();
            		var password = $('#pw-input').val();
            		var pwAnswer = $('#pwAnswer').val();
            		var pwQuestion = $('#pwQuestion option:selected').val();
            		var form = { "id":id,
            				"email":email,
            				"nickname":nickname,
            				"password":password,
            				"pwAnswer":pwAnswer,
            				"pwQuestion":pwQuestion };
					
            		
            	     var delVal = confirm('정보를 수정하시겠습니까?');
            	     if(delVal == true){
            	    	 
            	 		 $.ajax({
                         	async: true,
                            type : "POST",
                           
                            data : JSON.stringify(form),
//                         	datatype : "json",
                            traditional : true, 
                            contentType : "application/json; charset=UTF-8",
                         	 url : "member_info_edit_proc.bom",
                             success : function(data){
                                  if(data == "success"){	
                                      alert('정보를 수정했습니다.');
                                      location.href ="${pageContext.request.contextPath}/member_info_edit.bom";
                                  }else if(data == "fail"){
                                	  alert('정보수정에 실패했습니다.');
                                      location.href ="${pageContext.request.contextPath}/member_info_edit.bom";
                                  }
                             }
                         });
            	     	
            	     }else{
            				alert('정보 수정을 취소합니다.');
            			}
            			
            			
            	}); 
            
              </script>
              
              <div class="input_box">
<!--                 <form onsubmit="return beforeCheck()"> -->
                  <div class="tit">비밀번호(8자 이상 20자 이하)</div>
                  <div class="input">
                    <input type="password" id="now-pw-input"  maxlength="20" minlength="8"
                         name="password" placeholder="현재 비밀번호를 입력하세요." value=""> 
                  </div>
                  <p class="warning" id="now-pw-check-warning">현재 비밀번호가 일치하지 않습니다.</p>
                  <div class="input">
                    <input type="password" name="password" id="change-pw"
                         placeholder="변경할 비밀번호를 입력하세요." value=""> 
                  </div>
                  <div class="input">
                    <input type="password" id="change-pw-check" 
                        placeholder="변경할 비밀번호를 확인해 주세요." value="">
                  </div>
                  <p class="warning" id="pw-check-warning">비밀번호가 일치하지 않습니다</p>
                  <p class="warning" id="pw-warning">영문, 숫자를 포함한 8-20자를 입력해주세요.</p>
                </div>
                <div class="change_btns"><button type="button" id="changeBtn" class="btn_bk btn_sm" disabled='disabled'>비밀번호 변경</button>
<!--                 </form> -->
              </div>
              <script>
                $('#change-pw').blur(function(){
                var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
                return regExp.test($('#change-pw').val()) ? 
                      // console.log('1') : console.log($('#pw-input').val());
                      $('#pw-warning').css('display','none'):
                      $('#pw-warning').css('display','block');  
                        // 형식에 맞는 경우 true 리턴	 
              });

              $('#change-pw-check').keyup(function(){
                
                return $('#change-pw').val() != $('#change-pw-check').val() ? 
                // console.log($('#change-pw-check').val()) : console.log('1');
                      $('#pw-check-warning').css('display','block'):
                      $('#pw-check-warning').css('display','none');  
                        // 형식에 맞는 경우 true 리턴	 
              });

              
              // 현재 비번 체크
              $('#now-pw-input').keyup(function(){
                  
                  var password = $('#now-pw-input').val();
                  
                  $.ajax({
                  	async: true,
                     type : 'post',       
                     data : JSON.stringify(password),
                     contentType: "application/json; charset=UTF-8",
                    	url : "nowPwCheck.bom",
                      success : function(data){
                           if(data == "success"){
                                $("#now-pw-check-warning").css('display','none');
                        	    $('#changeBtn').removeAttr('disabled')              
                           }else if(data == "fail"){
                               $("#now-pw-check-warning").css('display','block');
                        	   $('#changeBtn').prop('disabled', 'disabled');

                           }
                      }
                  });	
             });
              
              $('#changeBtn').click(function() {
 				 var password = $('#change-pw').val();
                   
                   $.ajax({
                   	async: true,
                      type : 'post',       
                      data : JSON.stringify(password),
                      contentType: "application/json; charset=UTF-8",
                     	url : "member_update_pw.bom",
                       success : function(data){
                            if(data == "success"){
                               alert("비밀번호 변경이 완료 되었습니다."); 
                               location.href ="${pageContext.request.contextPath}/member_info_edit.bom";
                            }else if(data == "fail"){
                         	  alert("비밀번호 변경을 실패했습니다.");  
                         	  location.href ="${pageContext.request.contextPath}/member_info_edit.bom";

                            }
                       }
                   });	
 			});
              

                 function deleteWarning(){
                   
                   var delVal = confirm("누리봄을 정말로... 탈퇴하시겠습니까...?ㅠㅠ");
                   var id = $('#id').val();
                   if(delVal == true){
          	    	 
          	 		 $.ajax({
                       	async: true,
                          type : "POST",
                          data : id,
//                       	datatype : "json",
                          traditional : true, 
                          contentType : "application/json; charset=UTF-8",
                      	  url : "member_out.bom",
                           success : function(data){
                                if(data == "success"){	
                                    alert('회원탈퇴를 성공했습니다.');
                                    location.href ="${pageContext.request.contextPath}/";
                                }else if(data == "fail"){
                              	  alert('회원탈퇴를 실패했습니다.');
                                    location.href ="${pageContext.request.contextPath}/member_info_edit.bom";
                                }
                           }
                       });
          	     	
          	     }else{
          				alert('회원탈퇴를 취소합니다.');
          			}
                   
                   
                 }
              
              </script>
              <div class="logout_btn"><button type="button" class="btn_bk" onclick="deleteWarning()">회원탈퇴</button></div>
              
          </div>
            

           

            
          </div><!--real-content-->