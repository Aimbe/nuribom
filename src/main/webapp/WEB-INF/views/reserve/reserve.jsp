<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file ="../common/_common.jsp" %>
    <link href=" ${pageContext.request.contextPath}/resources/css/reserve/reserve.css" rel="stylesheet" type="text/css" />

  

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
 
  <body>
    <%@ include file ="../common/_header.jsp" %>
  
        
<div id="main">
<br><br><br><br>
	<div class="container reserve">
		<p class="fts7 bottomspace"> 예약하기 </p>
        <p class="fts5">예약내용을 확인 후 확정해 주세요</p>
	</div>
    <br><hr class="container reserve_top_hr"><br>
    
	
<div class="container img-container">
    <br>
    <div class="reserve_center_name">${ct.name}</div>
    <br>
    <img src=" ${pageContext.request.contextPath}/resources/images/center/${ct.mainImg}"
     width=500px height="350px" class="reserve_center_image">
    
</div>
    <br><br>    <br><br>
    <br>
        	
			
		<div class="container reserve_wrap">
			<div class="reserve_form_div">
				<form action="reserve_proc.bom" method="post" id="reserve_form">
				<table border="1" class="reserve_form_table">
                    <colgroup>
                        <col style="width:45%"/>
                        <col style="width:55%"/>
                    </colgroup>
                    <tbody>
					<tr>
						<td class="td_reserve_menual">예약일 </td>
						<td class="td_reserve_input">
				<input type="date" name="reserveDay" id="reserve_day" requried>				
				<script>
					  var day = new Date();
					  var nextDay = new Date(day);
					  nextDay.setDate(day.getDate()+10); 
// 					  nextDay.setDate(22);
					  document.getElementById('reserve_day').value = day.toISOString().substring(0, 10);
					  document.getElementById('reserve_day').min = day.toISOString().substring(0, 10);
					  document.getElementById('reserve_day').max = nextDay.toISOString().substring(0, 10);
					</script>

										 
				<script>
				$(document).ready(function(){
					var ctId= <c:out value='${ctId}'/>;		
            		var CTX = '${pageContext.request.contextPath}';
            		var date = $("#reserve_day").val();
            			
            				$.ajax({
            					type: 'get',
            					url: CTX + "/change_time_for_"+date+"_"+ctId+".bom",
            					dataType: 'text', 
            					success: function(res) { 
            						console.log(res); 
            						$("#reserveTime").html(res);
            					}, 
            					error: function(xhr,status) { 
            						console.log("ERROR: " + status);
            						console.log("ERROR: " + xhr.status);
            					},
            					complete: function() { 
            						console.log("completed.."); 
            					}
            				});
				});
				
				$("#reserve_day").change(function(){
					var ctId= <c:out value='${ctId}'/>;		
            		var CTX = '${pageContext.request.contextPath}';
            			var date = $("#reserve_day").val();
            			
            				$.ajax({
            					type: 'get',
            					url: CTX + "/change_time_for_"+date+"_"+ctId+".bom",
            					dataType: 'text', 
            					success: function(res) { 
            						console.log(res); 
            						$("#reserveTime").html(res);
            					}, 
            					error: function(xhr,status) { 
            						console.log("ERROR: " + status);
            						console.log("ERROR: " + xhr.status);
            					},
            					complete: function() { 
            						console.log("completed.."); 
            					}
            				});
            			
					     });
				</script>
						</td>
					
					</tr>
					<tr>
						<td class="td_reserve_menual">예약시간선택 </td>
						<td class="td_reserve_input">
                             <select name="reserveTime" class="reserve_time_combo" form="reserve_form"  id="reserveTime">
                                <option value="" >시간선택</option>
                                <option value="8">08:00</option>
                                <option value="9">09:00</option>
                                <option value="10">10:00</option>
                                <option value="11">11:00</option>
                                <option value="12">12:00</option>
                                <option value="13">13:00</option>
                                <option value="14">14:00</option>
                                <option value="15">15:00</option>
                                <option value="16">16:00</option>
                                <option value="17">17:00</option>
                                <option value="18">18:00</option>
                              </select>
<!--                            <input type="text" name="reserve_time" required>-->
						</td>
					</tr>
					<tr>
						<td class="td_reserve_menual" >이름(닉네임) </td>
						<td class="td_reserve_input"><input type="text" id="reserveNickname" name="reserveNickname"
                              					placeholder="닉네임    " required></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td class="td_reserve_menual">이메일 </td> -->
<!-- 						<td class="td_reserve_input"><input type="email" name="reservez_email" placeholder="이메일 입력" required></td> -->
<!-- 					</tr> -->
					<tr>
						<td class="td_reserve_menual td_reserve_menual_plus">상담하고 싶은 내용</td>
						 <td>
                             
<!--                             <input type="text" name="reserve_text" placeholder="상담하고 싶은 내용 입력해주세요" style="height: 100px">-->
                        <textarea class = "reserve_form_textarea" name="reserveMemo" form="reserve_form" 
                                  cols="28" rows="6" placeholder="상담하고 싶은 내용을 입력해주세요"
                                   style="resize: none;" ></textarea></td> 
<!--						<td class="td_reserve_input"><textarea>상담하고 싶은 내용</textarea></td>-->
					</tr>
                        
                    </tbody>
				</table>
				<input type="hidden" name="ctId" value="${ctId}">
				<input type="hidden" name="mbId" value="${mbId}">
<!-- 					<input type ="button" id="reserveAction" value="예약하기" -->
<!--                            onclick="btnsub()"> -->
					<input type ="submit" id="reserveAction" value="예약하기">
<!--                            onclick="alert('안녕')"> -->
                           <script>
                           function btnsub(){
                        	   $('#reserve_form').submit();
                           }
   
                           </script>
<!--					<input type="submit" value="예약하기" >-->
				</form>	
			</div>
		</div>

</div> <!--  main -->
<br><br><br>  

    <%@ include file ="../common/_footer.jsp" %>
  </body>
</html>
