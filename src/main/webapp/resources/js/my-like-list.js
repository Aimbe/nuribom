var like = 'like';

          $('.center-like-icon').click(function(){

            if(like == 'like'){
              $(this).css('color','#f9ed6c');
              like ='unLike';

            }else{
              $(this).css('color','#d0cece');
              like ='like';
            }


          });

  
        
        //좋아요