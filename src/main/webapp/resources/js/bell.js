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