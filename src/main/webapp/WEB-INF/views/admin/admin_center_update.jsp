<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html"; charset ="EUC-KR"> 
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
	  <link href="${pageContext.request.contextPath}/resources/admin/css/center_edit.css" rel="stylesheet" type="text/css">

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
                        <h1 class="h3 mb-0 text-gray-800 ">상담소 정보수정</h1>
     
                    </div>

                   
                    <!-- Content Row -->



                    <!-- 여기다가 짜주시면 감사하겠습니다! -->
                        <div id="edit_container">
                             <form method="post" id="center_edit" action="${pageContext.request.contextPath}/center_edit.bom" enctype="multipart/form-data">
                             	<input type="hidden" name="centerId"  value="${centerId}">
                            
                                <div class="btns">
                                    <input type="submit" id="btn_edit" value="수정하기">
                                </div>
                                <div class="edit_basic_info">
                                    <label for="center_name" class="basic_label">상담소명: </label>
                                    <input type="text" size="40" name="center_name" id="center_name" placeholder="이름입력" value="${ct.name}"> <br>

                                     <label for="center_img" class="file_label">상담소 이미지</label> 
                                   
                                   <!-- 메인이미지파일 업로드  -->
                                    <input type="file" name="center_img" id="center_img" size="64"> <br>
                                   

                                    <label for="center_category" class="basic_label">카테고리 선택: </label>
                                    <select name="sel_category" id="center_category"> 
                                        <option value="domestic" selected>가정상담소</option>
                                        <option value="sexualViolence">성폭력상담소</option>
                                        <option value="teenager">청소년상담소</option>
                                        <option value="suicide">자살예방상담소</option>
                                        <option value="multicultural">다문화가족</option>
                                    </select> <br>
                                    
                                    </form>
                                    <label for="center_address" class="basic_label">상세주소: </label>
                                    <input type="text" name="center_address" id="center_address" placeholder="주소입력" size="100"
                                    value="${ct.addressRegion} ${ct.addressCity} ${ct.addressDetail}"> <br>
                                    <label for="center_time_open" class="basic_label">운영시간: </label>
                                    <input type="time" name="center_time_open" id="center_time_open" value="09:00">
                                    <label for="center_time_close" class="basic_label"> </label>  <i class="fas fa-long-arrow-alt-right"></i>
                                    <input type="time" name="center_time_close" id="center_time_close" value="18:00"> <br> 
                                    <label for="center_telNum" class="basic_label">연락처: </label>
                                    <input type="tel" name="center_telNum" id="center_telNum" placeholder="번호입력" maxlength="11"
                                    value="${ct.telephone}"> <br>
                                </div>   

                                <div class="edit_intro_info">
                                    <span>상담소 소개:</span> <br>
                                    <label for="center_intro_1" class="intro_label">1</label>
                                    <textarea name="center_intro_1" id="center_intro_1" cols="50" rows="3" maxlength="500">${ct.firstDesc}</textarea>  
                                    <label for="center_intro_2" class="intro_label"> 2</label>
                                    <textarea name="center_intro_2" id="center_intro_2" cols="50" rows="3" maxlength="500">${ct.secondDesc}</textarea>  
                                    <label for="center_intro_3" class="intro_label">3 </label>
                                    <textarea name="center_intro_3" id="center_intro_3" cols="50" rows="3">${ct.thirdDesc}</textarea>
                                    
                                </div>

                                <div class="edit_progrm_info">
                                    <span>제공 프로그램: </span> <br>
                                 <span>1 </span><label for="center_program_title_1" class="program_label">제목: </label> 
                                    <input type="text" name="center_program_title_1" id="center_program_title1" maxlength="50" size="50" placeholder="${ct.firstProgramTitle}">  
                                      <label for="program_img_1" class="file_label pr_label">프로그램 이미지</label> 
                                    
                                    <!-- 프로그램1 파일 업로드 -->
                                    <input type="file" name="program_img_1" id="program_img_1" >
                                    
                                    <br>
                                   &nbsp; <label for="center_program_desc_1" class="program_label">설명:  </label>
                                    <input type="text" name="center_program_desc_1" id="center_program_desc_1" maxlength="100" size="80" value="${ct.firstProgramDesc}"> <br><br>
                                    
                                 <span>2 </span><label for="center_program_title_2" class="program_label">제목: </label>
                                    <input type="text" name="center_program_title_2" id="center_program_title_2" maxlength="50" size="50" placeholder="${ct.secondProgramTitle}"> 
                                    <label for="program_img_2" class="file_label pr_label">프로그램 이미지</label> 
                                    
                                     <!-- 프로그램2 파일 업로드 -->
                                    <input type="file" name="program_img_2" id="program_img_2" >
                                    <br>
                                   &nbsp; <label for="center_program_desc_2" class="program_label">설명:  </label>
                                    <input type="text" name="center_program_desc_2" id="center_program_desc_2" maxlength="100" size="80" value="${ct.secondProgramDesc}"> <br>

                                </div>

                                <div class="edit_location_info">
                                    <span>위치 정보: </span> <br>
                                    <label for="center_location_latitude " class="location_label">위도:  </label>
                                    <input type="text" name="center_location_latitude" id="center_location_latitude" maxlength="25" size="25"
                                    value="${ct.latitude}"> 
                                    <label for="center_location_longtitude" class="location_label">경도:  </label>
                                    <input type="text" name="center_location_longtitude" id="center_location_longtitude" maxlength="25" size="25"
                                    value="${ct.longitude}"> 
                                    <!-- input type엔 double형이 없기때문에, text로 받이서 double로 캐스팅 해야함-->
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

    <script>
         $('#center_img').on('change',function(){
                var filename = $('#center_img').val();
                $('.upload_file_name').text(filename);
             });

        $('#program_img_1').on('change',function(){
        var filename = $('#program_img_1').val();
        $('.upload_file_name_2').text(filename);
        });
        $('#program_img_2').on('change',function(){
        var filename = $('#program_img_2').val();
        $('.upload_file_name_3').text(filename);
        });
    </script>


</body>

</html>