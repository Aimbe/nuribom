
                function checkAll(){
                  if(document.getElementById('check_all').checked==true){  //id 를 사용하여 하나의 객체만을 호출
                        $('input[type=checkbox]').prop("checked", true);  //name 을 사용하여 배열 형태로 담아 호출
                      } else $('input[type=checkbox]').prop("checked", false);
                      
                      
                };
             
           
                function deleteWarning(){
                  
                  if($('input[type=checkbox]:checked').length != 0 ){
                  confirm("선택하신 리뷰를 정말로 삭제하시겠습니까?");
                  } else {
                  alert("선택하신 리뷰가 없습니다.")
                  }
                }
         