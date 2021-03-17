<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

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
	
 <link href="${pageContext.request.contextPath}/resources/admin/css/center_register.css?after" rel="stylesheet" type="text/css">
 
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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 ct_title">
                        <h1 class="h3 mb-0 text-gray-800 ">상담소 등록</h1>
     
                    </div>

                   
                    <!-- Content Row -->


                    <!-- 여기다가 짜주시면 감사하겠습니다! -->
                        <div id="register_container">
                          <form method="post" id="center_register" action="${pageContext.request.contextPath}/center_add_tag.bom" enctype="multipart/form-data">
								<input type="hidden" name="centerId"  value="${centerId}">
                             	
                                <div class="btns">
                                  <input type="submit" id="btn_register" value="등록하기">
                                </div>
                               
                                    <label for="center_tag" class="basic_label">등록할 상담소의 상담분야 <strong>태그</strong>를 선택해 주세요<small>(최소 2개이상)</small> : </label> <br>
                                  
                                          <div id="check_tags">
                                        <label for="check_freind" class="sel_tag">친구
                                            <input type="checkbox" value="check_freind" name="sel_tag[]" />
                                          </label> 
                                    
                                        <label for="check_married" class="sel_tag">부부
                                            <input type="checkbox" value="check_married" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_socialRelationship" class="sel_tag">대외관계
                                            <input type="checkbox" value="check_socialRelationship" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_couple" class="sel_tag">연인
                                            <input type="checkbox" value="check_couple" name="sel_tag[]"/>
                                          </label> <br>
                                    
                                        <label for="check_family" class="sel_tag">가족
                                            <input type="checkbox" value="check_family" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_study" class="sel_tag">학업
                                            <input type="checkbox" value="check_study" name="sel_tag[]"/>
                                          </label> 
                                        <label for="check_company" class="sel_tag">직장
                                            <input type="checkbox" value="check_company" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_career" class="sel_tag">진로
                                            <input type="checkbox" value="check_career" name="sel_tag[]"/>
                                          </label> <br>
                                    
                                        <label for="check_job" class="sel_tag">취업
                                            <input type="checkbox" value="check_job" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_childcare" class="sel_tag">육아          
                                            <input type="checkbox" value="check_childcare" name="sel_tag[]" />
                                          </label> 
                                    
                                        <label for="check_overseas" class="sel_tag">해외생활
                                            <input type="checkbox" value="check_overseas" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_addicted" class="sel_tag">중독
                                            <input type="checkbox" value="check_addicted" name="sel_tag[]"/>
                                          </label> <br>
                                    
                                        <label for="check_eatingDisorder" class="sel_tag">섭식장애
                                            <input type="checkbox" value="check_eatingDisorder" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_sexLife" class="sel_tag">성생활
                                            <input type="checkbox" value="check_sexLife" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_sexualMinorities" class="sel_tag">성소수자
                                            <input type="checkbox" value="check_sexualMinorities" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_emotionalControl" class="sel_tag">감정조절
                                            <input type="checkbox" value="check_emotionalControl" name="sel_tag[]"/>
                                          </label> <br>
                                    
                                        <label for="check_selfHarm" class="sel_tag">자해
                                            <input type="checkbox" value="check_selfHarm" name="sel_tag[]"/>
                                          </label> 
                                    
                                        <label for="check_suicide" class="sel_tag">자살
                                            <input type="checkbox" value="check_suicide" name="sel_tag[]"/>
                                          </label> 
                                   </div>


                            </form>
                           
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
