var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
          
          function beforeCheck(){
            if($('#change-pw').val() != $('#change-pw-check').val()){
              $('#pw-check-warning').css('display','block');
              $('#change-pw-check').focus();
              return false;
            } else if(!regExp.test($('#change-pw').val())){
              $('#pw-warning').css('display','block');
              // $('#pw-input').focus();
              alert("비밀번호를 확인해주세요");
              return false;
            }
          };

       
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

          

          function checkWarning(){
            confirm("정보를 수정 하시겠습니까?");
          }

          function deleteWarning(){
            confirm("누리봄을 탈퇴하시겠습니까?");
          }
    
          
  
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

  
        
        
        