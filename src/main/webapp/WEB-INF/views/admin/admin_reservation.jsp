<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<head>

    <meta http-equiv="Content-Type" content="text/html"; charset ="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="UTF-8">
    
    <meta name="description" content="">
    <meta name="author" content="">

    <title>NOORIBOM ADMIN PAGE</title>

    <!-- Custom fonts for this template-->
    
    
    
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/resources/admin/css/sb-admin-2 copy.css" rel="stylesheet" type ="text/css"/>

   <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/resources/admin/js/sb-admin-2.min.js"></script>
    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/chart.js/Chart.min.js"></script>
    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/admin/js/demo/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath}/resources/admin/js/demo/chart-pie-demo.js"></script>
    
    <!-- ????????? css???????????? js ?????? ????????? ?????????! -->
   
 <link href="${pageContext.request.contextPath}/resources/admin/css/admin_reserve.css" rel="stylesheet" type="text/css">
 <link href=" ${pageContext.request.contextPath}/resources/css/review/all-review.css" rel="stylesheet" type="text/css" />


</head>

<script>
function DeleteReserveAdmin(id){
	if ( confirm("??? ????????? ????????? ?????????????????????????") == true){    //??????
	
		var CTX = '${pageContext.request.contextPath}';
				$.ajax({
					type: 'get',
					url: CTX + "/delete_reserve_"+id+".bom",
				
					dataType: 'text', // pcard2 ?????????
					success: function(res) { // ????????? ????????? ???????????? ???????????? ??? 2xx
						console.log(res); 
						if( res == "success") {
							$("#"+id).closest('.reserve_log').remove();
						}else{
						 alert('????????? ??????????????????.');
						}
						
					}, 
					error: function(xhr,status) { // ????????? ????????? ???????????? ???????????? ??? 3xx,4xx,5xx
						console.log("ERROR: " + status);
						console.log("ERROR: " + xhr.status);
						 alert('????????? ??????????????????.');
					},
					complete: function() { // ???????????? ???????????? ????????? ?????? ??????..
						console.log("completed.."); // ?????? ?????? - ???????????? ??????...
					}
				});
			
	
	}else{   //??????

	    return;

	}
}

</script>


<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/admin_dashboard.bom">
                <div class="sidebar-brand-icon rotate-n-15">
                   
                </div>
                <div class="sidebar-brand-text mx-3">NOORIBOM Admin</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin_dashboard.bom">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
             <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>????????? ??????</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">??????</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_member.bom">?????? ??????</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_reservation.bom">?????? ?????? </a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_center.bom">????????? ??????</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>????????? ??? ??????</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">??????</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_qna_none_reply.bom">Q&A ??????</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_add_new_board.bom">????????? ?????????</a>
                      
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="conatainer d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                   

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        
                     

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">ADMIN</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                              
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->

                
                
               
                <div class="container">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 ">
                       <span class="homebackclick"><h1 class="h3 mb-0 text-gray-800">?????? ??????</h1></span>
                    </div>
                    <script>
                    	$('.homebackclick').click(function(){
                    		location.href="admin_reservation.bom";
                    	});
                    </script>
                    
                    <div class="admin_reserve_search">
                        
                        
                    <form action="admin_reservation_search.bom" method="get" id="fdas">
                        ????????????:
                        <input type="number" name="search_reserve_code">
                        ????????????:
                        <input type="text" name="search_reserve_member_name">
                        ?????????:
                        <input type="text" name="search_reserve_center_name" class="search_reserve_center_name">
<!--
                        ????????????:<select name="search_center_cate"                                        class="search_center_cate" multiple>
                                <option value="">--??????--</option>
                                <option value="1">????????????</option>
                                <option value="2">??????</option>
                                <option value="3">??????</option>
                                <option value="4">??????</option>
                                <option value="5">??????</option>
                                <option value="6">??????</option>
                                <option value="7">??????</option>
                                <option value="8">??????</option>
                                <option value="9">??????</option>
                                <option value="10">??????</option>
                                <option value="11">????????????</option>
                                <option value="12">??????</option>
                                <option value="13">??????</option>
                                <option value="14">??????</option>
                                <option value="15">?????????</option>
                                <option value="16">????????????</option>
                                <option value="17">????????????</option>
                                <option value="18">??????</option>
                                <option value="19">??????</option>
                              </select>
-->
                        ?????????:<input type="date" name="search_reserve_day" id="search_reserve_day">
                        ????????????:
                        <select name="search_reserve_time" class="search_reserve_time">
                                <option value="" >????????????</option>
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
           &nbsp;&nbsp;??????:&nbsp;??????<input type="radio" name="visit_checkbox" value="1">&nbsp;?????????<input type="radio" name="visit_checkbox" value="0">
         &nbsp;&nbsp;????????????: ??????<input type="radio" name="review_checkbox" value="1">&nbsp;?????????<input type="radio" name="review_checkbox" value="0">
                        <input type="submit" value="??????">
                        
                        </form>
                      
                        
                    </div>
                    
                    
                    
                    <div class="reserve_header">
      <span class="reserve_header_text reserve_header_id">????????????</span>
                        
         <span class="reserve_header_text reserve_header_member_name">?????????</span>
       <span class="reserve_header_text reserve_header_center_name">?????????</span>
<!--        <span class="reserve_header_text reserve_header_center_cate"></span>-->
       <span class="reserve_header_text reserve_header_reserve_day">?????????</span>
      <span class="reserve_header_text reserve_header_reserve_time">????????????</span>
      <span class="reserve_header_text reserve_header_reserve_visit">????????????</span>
      <span class="reserve_header_text reserve_header_reserve_review">????????????</span>
                        
                 </div>
        <c:forEach var="rs" items="${rsList}">
                  <div class="reserve_log">
      		<span class="reserve_log_text reserve_log_id">${rs.reserveId}</span>
      	 	<span class="reserve_log_text reserve_log_member_name">${rs.reserveNickname}</span>
     	  	<span class="reserve_log_text reserve_log_center_name">${rs.reserveCenterName}</span>
       		<span class="reserve_log_text reserve_log_reserve_day">${rs.reserveDay}</span>
      		<span class="reserve_log_text reserve_log_reserve_time">${rs.reserveTime}:00</span>
      		<span class="reserve_log_text reserve_log_reserve_visit">
      <c:if test="${rs.reserveVisit == 0}">
    	<c:out value="??????" />
    	</c:if>
    	 <c:if test="${rs.reserveVisit == 1}">
    	<c:out value="??????" />
    	</c:if>
 
      		</span>
      		<span class="reserve_log_text reserve_log_reserve_review">
      		 <c:if test="${rs.reserveReview == 0}">
    				<c:out value="??????" />
    		</c:if>
    	 <c:if test="${rs.reserveReview == 1}">
    	<c:out value="??????" />
    	</c:if>

      		</span>
      		<button type="button" class="deleteButton" id="${rs.reserveId}" onclick="DeleteReserveAdmin(${rs.reserveId})">X</button>
               <hr class="reserve_log_hr">
         </div> 
		</c:forEach>
		
		
<!--             <div class="reserve_log"> -->
<!--       <span class="reserve_log_text reserve_log_id">121321</span> -->
<!--         <span class="reserve_log_text reserve_log_member_name">?????????</span> -->
<!--        <span class="reserve_log_text reserve_log_center_name">??????????????????</span> -->
<!--        <span class="reserve_log_text reserve_log_center_cate"></span> --> 
<!--        <span class="reserve_log_text reserve_log_reserve_day">2020-12-31</span> -->
<!--       <span class="reserve_log_text reserve_log_reserve_time">16:00</span> -->
<!--       <button type="button" class="deleteButton" id onclick="dfs()">X</button> -->
<!--                 <hr class="reserve_log_hr"> -->
<!--          </div>  -->
         
        <script>
 	var page = <c:out value='${pg}'/>;			
	var finalPage = <c:out value='${maxPg}'/>; 	  	 
	var siteLink = 	"admin_reservation.bom?pg=";	
	var searchCheck  = <c:out value='${search}'/>;
	if(searchCheck>0){
	siteLink="admin_reservation_search.bom?search_reserve_code=&search_reserve_member_name=${Nickname}&search_reserve_center_name=${CenterName}&search_reserve_day=${day}&search_reserve_time=${stTime}&pg="
	}	
		function showFinalPage(){
			location.href=siteLink+finalPage;
		}
		function firstPage(){
			location.href=siteLink+1;
		}
		function prevPage(){
			location.href=siteLink+(page-1);
		}
		
		function prevPage2(){
			location.href=siteLink+(page-2);
		}
		function prevPage3(){
			location.href=siteLink+(page-3);
		}
		function prevPage4(){
			location.href=siteLink+(page-4);
		}
		
		function nextPage(){
				location.href=siteLink+(page+1);
		}
			
		function nextPage2(){
				location.href=siteLink+(page+2);
		}
		function nextPage3(){
			location.href=siteLink+(page+3);
		}	
		function nextPage4(){
			location.href=siteLink+(page+4);
		}	
			
		</script>	
                    
    </div>
    
<div class="all-review-board-page">
<!-- disabled  -->
<c:if test ="${maxPg>=5 }">
<c:if test ="${pg == 1}">  	
<button type="button" class="page-btn btndisable" disabled>  <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn btndisable" disabled> <i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn all-review-sel-page"> 1</button>
  <button type="button" class="page-btn" onclick="nextPage()" > 2</button>
  <button type="button" class="page-btn" onclick="nextPage2()"> 3</button>
  <button type="button" class="page-btn" onclick="nextPage3()"> 4</button>
  <button type="button" class="page-btn" onclick="nextPage4()"> 5</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
 </c:if>
  
 <c:if test ="${pg == 2}">  
<button type="button" class="page-btn" onclick="firstPage()"> <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"> 1</button>
  <button type="button" class="page-btn all-review-sel-page"> 2</button>
  <button type="button" class="page-btn" onclick="nextPage()"> 3</button>
  <button type="button" class="page-btn" onclick="nextPage2()"> 4</button>
  <button type="button" class="page-btn" onclick="nextPage3()"> 5</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  </c:if>
  
	<c:if test ="${pg>2 && pg<maxPg-1}">  
	
  <button type="button" class="page-btn"  onclick="firstPage()">  <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"> <i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn " onclick="prevPage2()">${pg-2}</button>
  <button type="button" class="page-btn " onclick="prevPage()">${pg-1}</button>
  <button type="button" class="page-btn all-review-sel-page">${pg}</button>
  <button type="button" class="page-btn" onclick="nextPage()">${pg+1}</button>
  <button type="button" class="page-btn" onclick="nextPage2()">${pg+2}</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
   </c:if>
   
   <c:if test ="${pg == (maxPg-1)}">  
  <button type="button" class="page-btn"  onclick="firstPage()">  <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()"> <i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn " onclick="prevPage3()">${pg-3}</button>
  <button type="button" class="page-btn " onclick="prevPage2()">${pg-2}</button>
  <button type="button" class="page-btn " onclick="prevPage()">${pg-1}</button>
  <button type="button" class="page-btn all-review-sel-page">${pg}</button>
  <button type="button" class="page-btn" onclick="nextPage()">${pg+1}</button>
  <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn "  onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
   </c:if>
   
   <c:if test ="${pg == maxPg}">  
  <button type="button" class="page-btn" onclick="firstPage()"> <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="prevPage()">  <i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn " onclick="prevPage4()">${pg-4}</button>
  <button type="button" class="page-btn " onclick="prevPage3()">${pg-3}</button>
  <button type="button" class="page-btn " onclick="prevPage2()">${pg-2}</button>
  <button type="button" class="page-btn " onclick="prevPage()">${pg-1}</button>
  <button type="button" class="page-btn all-review-sel-page">${pg}</button>
  <button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small "></i></button>
  </c:if>
 </c:if>
 
 
 <c:if test ="${maxPg>0 && maxPg<5}">
 
 <c:if test ="${pg==1 }">
 	<button type="button" class="page-btn btndisable " disabled >  <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
 	<button type="button" class="page-btn btndisable" disabled > <i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
 </c:if>
 <c:if test ="${pg!=1 }">
 	<button type="button" class="page-btn "  onclick="firstPage()">  <i class="fas fa-chevron-left fa-1x page-icon size_small"></i><i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
 	<button type="button" class="page-btn" onclick="prevPage()"> <i class="fas fa-chevron-left fa-1x page-icon size_small"></i></button>
 </c:if>
 <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	<c:if test="${vs.current eq pg }">
			  <button type="button" class="page-btn all-review-sel-page"> ${vs.current} </button>
	</c:if>
		<c:if test="${vs.current ne pg}">
		<c:if test="${search>0}">
			<button type="button" class="page-btn " onclick="location.href='admin_reservation_search.bom?search_reserve_code=&search_reserve_member_name=${Nickname}&search_reserve_center_name=${CenterName}&search_reserve_day=${day}&search_reserve_time=${stTime}&pg=${vs.current}'">${vs.current}</button>
			</c:if>
		<c:if test="${search==0}">
  	<button type="button" class="page-btn " onclick="location.href='admin_reservation.bom?pg=${vs.current}'">${vs.current}</button>
  	</c:if>
			</c:if>
		</c:forEach>
		
		<c:if test="${pg!=maxPg }">
		 <button type="button" class="page-btn" onclick="nextPage()"> <i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
  <button type="button" class="page-btn" onclick="showFinalPage()"><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
		</c:if>
		<c:if test="${pg==maxPg }">
		 <button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon size_small "></i></button>
  			<button type="button" class="page-btn btndisable" disabled><i class="fas fa-chevron-right fa-1x page-icon size_small"></i><i class="fas fa-chevron-right fa-1x page-icon size_small"></i></button>
		</c:if>
		
 </c:if>
 
 
 
 
 
</div>
<div style="height:200px"></div>
   

</body>

</html>