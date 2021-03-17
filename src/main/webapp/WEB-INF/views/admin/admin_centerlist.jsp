<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html"; charset ="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
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
    
    <!-- 각자의 css파일이나 js 파일 링크는 여기에! -->
     <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/fontawesome.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/solid.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/brands.min.css">
    
     <link href="${pageContext.request.contextPath}/resources/admin/css/center_admin.css" rel="stylesheet" type="text/css">
     
<!-- 전체 admin 기본설정때문에 css 적용안되는게 너무 많아서 내부스타일 따로 설정함    -->
 
</head>
  <script type="text/javascript">
    function DeleteCenterAdmin(id){
    	if ( confirm("이 상담소를 정말로 삭제하시겠습니까?") == true){    //확인
    	
    		var CTX = '${pageContext.request.contextPath}';
    				$.ajax({
    					type: 'get',
    					url: CTX + "/delete_center_"+id+".bom",
    					
    				
    					dataType: 'text', // pcard2 명시적
    					success: function(res) { // 비동기 요청을 서버에서 성공처리 시 2xx
    						console.log(res); 
    						if( res == "success") {
    							$("#"+id).closest('.center_log').remove();
    							 location.href = "admin_center.bom";
    						}else{
    						 alert('삭제에 실패했습니다.');
    						}
    						
    					}, 
    					error: function(xhr,status) { // 비동기 요청을 서버에서 실패처리 시 3xx,4xx,5xx
    						console.log("ERROR: " + status);
    						console.log("ERROR: " + xhr.status);
    						 alert('삭제에 실패했습니다.');
    					},
    					complete: function() { // 성공실패 무관하게 무조건 끝에 실행..
    						console.log("completed.."); // 끝에 실행 - 모래시계 끄자...
    					}
    				});
    	}else{   //취소
    	    return;
    	}
    }

    
    
     //체크박스 선택된 상담소 아이디번호 추출
    function value_check() {
        var check_count = document.getElementsByName("check_centers").length;
        for (var i=0; i<check_count; i++) {
            if (document.getElementsByName("check_centers")[i].checked == true) {
              var ctId = document.getElementsByName("check_centers")[i].value; //상담소 아이디번호(input value값에 저장되어 있어야함)
              alert(ctId + " 상담소번호 추출 테스트용..");
            }
        }
    }
   
    </script>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                   
                </div>
                <div class="sidebar-brand-text mx-3">NOORIBOM Admin</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.html">
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
                    <span>사이트 관리</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">종류</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_member.bom">회원 관리</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_reservation.bom">예약 관리 </a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_center.bom">상담소 관리</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>관리자 글 관리</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">메뉴</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_qna_none_reply.bom">Q&A 답변</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin_add_new_board.bom">관리자 글쓰기</a>
                      
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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 ct_title">
                        <h1 class="h3 mb-0 text-gray-800 ">상담소 관리</h1>
     
                    </div>
                   
                    <!-- Content Row -->
                   
                    <div>

                    <!-- 여기다가 짜주시면 감사하겠습니다! -->

                        <div class="keyword">
                          <input type="text" id="search_keyword" placeholder="검색어 입력"/>
                          <input type="button" id="btn_search" value="검색">
                        </div>

                    <div id="btns">
<!--                         <button id="btn_edit" onclick="value_check()"><a href="상담소 관리_편집.html">수정하기</a></button> -->
<!--                         <button id="btn_delete" onclick="value_check()"><a>삭제하기</a></button> -->
                        <button id="btn_register"><a href="${pageContext.request.contextPath}/center_new_form.bom">새로등록</a></button>
                    </div>   

                        <table class="type10">
                             <thead>
                            <tr>
                              <!-- <th scope="cols">선택</th> -->
                              <th scope="cols" id="no" >No.</th>
                              <th scope="cols" id="id" >ID.</th>
                              <th scope="cols" id="category" >카테고리.</th>
                              <th scope="cols" id="name" >상담소 이름.</th>
                              <th scope="cols" id="region" >지역.</th>
                              <th scope="cols" id="tel" >전화번호.</th>
                              <th scope="cols" id="site" >사이트.</th>
                              <th scope="cols" id="created_at" >등록일.</th>
                              <th scope="cols" id="btn_col" > </th>
                            </tr>
                            </thead>
                          
                            <form id="center_list" method="GET">
                                <tbody style="font-size: 15px">
	                                <c:forEach var="ct" items="${ctList}" varStatus="vs">
	                                		<%@ include file="../admin/_ct_list_td.jsp" %>
	                                </c:forEach>
                                </tbody>
                                
                            </form>
                          </table>


                    </div>

                    <!-- 페이지네이션 -->
		 	<div class="admin-center-list-page" id="paginate">

				<c:if test="${pn > 1}">
					<a href="${pageContext.request.contextPath}/admin_center.bom?pg=${pn-1}">
					<button type="button" class="page-btn"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button></a>
				</c:if>
				
              <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs"> 
              	<c:if test="${vs.current eq pn}">
              		<button type="button" class="page-btn admin-sel-page">${vs.current}</button>
              	</c:if>
				
				<c:if test="${vs.current ne pn}">
					<a href="${pageContext.request.contextPath}/admin_center.bom?pg=${vs.current}">
					<button type="button" class="page-btn">${vs.current}</button></a>
				</c:if>              

              </c:forEach>
              
              
	            <c:if test="${pn < maxPg}">
	              	<a href="${pageContext.request.contextPath}/admin_center.bom?pg=${pn+1}">
	              	<button type="button" class="page-btn"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button></a>
	            </c:if>
            
            
           </div>
    
                   
                            
        </div> <!-- container -->
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

  

</body>

</html>