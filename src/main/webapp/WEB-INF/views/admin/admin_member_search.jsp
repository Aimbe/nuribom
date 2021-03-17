<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html" charset ="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <meta name="description" content="">
    <meta name="author" content="">

    <title>NOORIBOM ADMIN PAGE</title>

    <!-- Custom fonts for this template-->
    
    
    
    <link href="${pageContext.request.contextPath}/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
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

<script src="${pageContext.request.contextPath}/resources/admin/admin_mb.js"></script>
<link href="${pageContext.request.contextPath}/resources/admin/css/memberMng.css?v" rel="stylesheet">  
</head>


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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">회원관리</h1>
     
                    </div>

                   
                   
                    

                    <div>


                    <!-- 여기다가 짜주시면 감사하겠습니다! -->


                    <div class="searchDiv">    
                    <form method="post" action="${pageContext.request.contextPath}/member_search.bom">
                        <select id="search-word" name="target">
                                <option value="nickname" ${param['target'] eq 'nickname' ? ' selected': '' }>닉네임</option>
                                <option value="email"  ${param['target'] eq 'email' ? ' selected': '' } >이메일</option>
                        </select>
                        <input type="search" class="user-search" placeholder="" name="keyword" value="${param.keyword}">
                        <button type="submit" class="search-user-btn"><i class="fas fa-search fa search-user-icon"></i></button>
                      </form>
                    </div>
                    <div>
                        <!-- <button type="button" class="" -->

                    </div>

                        <div>
                        <table class="type10" id="mb_list">
                            <thead>
                            <tr>
                              <th scope="cols"> </th>
                              <th scope="cols">ID</th>
                              <th scope="cols">이메일</th>
                              <th scope="cols">닉네임</th>
                              <th scope="cols">비밀번호</th>
                              <th scope="cols">가입날짜</th>
                              <th scope="cols">갱신날짜</th>
                              <th scope="cols">질문</th>
                              <th scope="cols">답변</th>
                              <th scope="cols"> </th>

                            </tr>
                            </thead>
                            <tbody>
                            
                            <c:forEach var="mb" items="${mbList}">
                            <tr>
                              <th scope="row"><input type="checkbox" name="checkedbox" value="${mb.id}"></th>
                              <td>${mb.id}</td>
                              <td>${mb.email}</td>
                              <td>${mb.nickname}</td>
                              <td>${mb.password}</td>
                              <td><fmt:formatDate value="${mb.joinedAt}" pattern="yyyy년 MM월 dd일  - HH시!"/></td>
                              <td><fmt:formatDate value="${mb.updatedAt}" 
							pattern="yyyy년 MM월 dd일  - HH시!"/></td>
                              <td>${mb.pwQuestion}</td>
                              <td>${mb.pwAnswer}</td>
                              <td><button type="button" id="editBtn" 
                              onclick="location.href='admin_member_update_form.bom?mbId=${mb.id}'">
                              <a>수정</a></button></td>
                          
                            </tr>
                            </c:forEach>

                            </tbody>
                          </table>
                         </div>
                          <div class="go-user-board-register">
                            <div class="select_all_div">
                            <input type="checkbox" class="select_all_btn" id="check_all" onclick="checkAll()"> 전체선택
                            </div>
                            <script>
                            
                              function checkAll(){
                                if(document.getElementById('check_all').checked==true){  //id 를 사용하여 하나의 객체만을 호출
                                      $('input[type=checkbox]').prop("checked", true);  //name 을 사용하여 배열 형태로 담아 호출
                                    } else $('input[type=checkbox]').prop("checked", false);
                                    
                                    
                              };
                            </script>
                            <div class="delete-right-div">
                            <button type="button" class="user-board-register delete" >삭제</button>
                            
                            </div>
                            <script>
                            $('.delete').click(function(){
                            	// console.log("11");
                                 var check_count = document.getElementsByName("checkedbox").length;
                                 var checkDelete = [];
                                 var delVal = confirm('회원을 삭제하시겠습니까?');
                                 if(delVal == true){
                                	
                                 	for (var i=0; i< check_count; i++) {
                                    	 if (document.getElementsByName("checkedbox")[i].checked == true) {
                                     	  var ctId = document.getElementsByName("checkedbox")[i].value;
                                     	  checkDelete.push(ctId);
                                     	 }
                                	 }
                                 	
                                 	 location.href=
                                 		 "${pageContext.request.contextPath}/admin_member_delete.bom?mbId="+checkDelete;
//                                  	for (var i=0; i< checkDelete.length; i++) {
//                                  	console.log(checkDelete[i]);
//                                  	}
                                 }else{
                            			alert('게시글 삭제를 취소합니다.');
                            		}
                            		
                            		
                            }); 
                            </script>
                           
                   </div>
              <div class="user-board-page">

              <div id="paginate">
		<c:if test="${pn > 1}">
			<button type="button" class="page-btn" onclick = "location.href = '${pageContext.request
			.contextPath}/admin_member.bom?pg=${pn-1}'" >
			 <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
		</c:if>
			
		<c:forEach begin="1" end="${maxPg}" step="1" 
			varStatus="vs">
			
			<c:if test="${pn eq vs.current}">
			<button type="button" class="page-btn user-sel-page">${vs.current}</button> <!-- 현재 페이지 -->
			</c:if>	
			<c:if test="${pn ne vs.current}">
			<button type="button" class="page-btn"
				onclick = "location.href = '${pageContext.request
			.contextPath}/admin_member.bom?pg=${vs.current}'">${vs.current}</button>
			</c:if>
			
		
		</c:forEach>
		
		<c:if test="${pn < maxPg}">
			<button type="button" class="page-btn"
			onclick = "location.href = '${pageContext.request
			.contextPath}/admin_member.bom?pg=${pn+1}'"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
		</c:if>	
            </div>	

<!--                           <div class="user-board-page"> -->

<!--                             <button type="button" class="page-btn"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button> -->
<!--                             <button type="button" class="page-btn user-sel-page"> 1</button> -->
<!--                             <button type="button" class="page-btn"> 2</button> -->
<!--                             <button type="button" class="page-btn"> 3</button> -->
<!--                             <button type="button" class="page-btn"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button> -->
<!--                           </div> -->


                          

                        </div>
                    </div>

                       
                            
        </div>
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