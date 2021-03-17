<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="${pageContext.request.contextPath}/resources/admin/css/memberMng.css" rel="stylesheet">  
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
                        <h1 class="h3 mb-0 text-gray-800">회원 정보 수정</h1>
     
                    </div>

                   
                
                   
                    

                        
                    <!-- 여기다가 짜주시면 감사하겠습니다! -->

                    <div class="editDiv">   
                        
                       
                     <form method="POST" action="admin_member_update.bom" onsubmit="beforeCheck()">
<%--                         <input type="hidden" name="id" value="${member.id}"> <!-- PK -->	 --%>
                        <table id="editTbl">
                            <tr><td>ID</td> 
                            <td><input type="text" name="id" value="${member.id}" readonly></td></tr>				
                            
                            <tr><td>이메일</td> 
                                <td><input type="text" name="email" value="${member.email}" readonly></td></tr>
                            <tr><td>닉네임</td> 
                                <td> <input type="text" id="nickname" minlength="3" maxlength="20" name="nickname"
                                     placeholder="닉네임을 입력하세요." value="${member.nickname}" size="27" required></td>
                                    <td> <p class="warning">은/는 사용할 수 있/없는 닉네임입니다.</p>
                                        <p class="warning" id="nick-warning">3자 이상 20자 이하로 입력해주세요</p></td>
                                </tr>
                            <tr><td>비밀번호</td> 
                                <td>
                                    <input type="password" id="pw-input"  maxlength="20" minlength="8" name="password" 
                                    placeholder="비밀번호를 입력하세요." value="${member.password}"  size="27" required readonly> 
                                </td>
                                <td> <p class="warning" id="pw-warning">영문, 숫자를 포함한 8-20자를 입력해주세요.</p></td>
                            </tr>
                            <tr><td>비밀번호확인</td> 
                                <td>
                                    <input type="password" id="pw-check" maxlength="20" minlength="8" 
                                    placeholder="비밀번호를 확인해 주세요." value="${member.password}"  size="27" required readonly>
                                </td>
                                <td><p class="warning" id="pw-check-warning">비밀번호가 일치하지 않습니다</p></td>
                            </tr>
                           <tr>
                                <td>가입일</td> 
                                <td> <fmt:formatDate value="${member.joinedAt}" 
                                        pattern="yyyy년 MM월 dd일" />   </td>
                            </tr>
                            <tr> 
                                <td>갱신일</td> 
                                <td><fmt:formatDate value="${member.updatedAt}" 
                                        pattern="yyyy년 MM월 dd일" />	</td> 
                            </tr>
                            <tr><td>비밀번호 확인 질문</td> 
                                <td><select class="pwQuestion" name="pwQuestion">
                                    <option value="1" <c:if test="${member.pwQuestion == 1}"> selected </c:if>>기억에 남는 추억의 장소는?</option>
                                    <option value="2" <c:if test="${member.pwQuestion == 2}"> selected </c:if>>자신의 인생 좌우명은?</option>
                                    <option value="3" <c:if test="${member.pwQuestion == 3}"> selected </c:if>>자신의 보물 1호는?</option>
                                    <option value="4" <c:if test="${member.pwQuestion == 4}"> selected </c:if>>가장 기억에 남는 선생님 성함은?</option>
                                    <option value="5" <c:if test="${member.pwQuestion == 5}"> selected </c:if>>내가 좋아하는 캐릭터는?</option>
                                    <option value="6" <c:if test="${member.pwQuestion == 6}"> selected </c:if>>다시 태어나면 되고싶은 것은?</option>
                                    <option value="7" <c:if test="${member.pwQuestion == 7}"> selected </c:if>>기억나는 짝꿍이름은?</option>
                                    <option value="8" <c:if test="${member.pwQuestion == 8}"> selected </c:if>>자신이 두번째로 존경하는 인물은?</option>
                                    <option value="9" <c:if test="${member.pwQuestion == 9}"> selected </c:if>>받았던 선물 중 기억나는 선물은?</option>
                                </select></td></tr> 
                                <tr><td>비밀번호 확인 답변</td> 
                                    <td><div class="input"><input type="text" id="pwAnswer" maxlength="20" minlength="3"  name="pwAnswer" 
                                        placeholder="답변을 입력해주세요" value="${member.pwAnswer}" size="27"  required></div></td>
                                    <td><p class="warning" id="pwAnswer-warning">3자 이상 20자 이하로 입력해주세요</p></td></tr>
                            
                           
                                
                            
                        </table>
                        
                        <div class="resetSubmitBtn">
                        <input type="reset" value="리셋">
                        <input type="submit" value="갱신">
                        </div>
                        </form>
                        </div>
                    
                    

                    </div>
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
 <script>
                    // submit 전에 비밀번호 다시 체크
                    var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
                            
                            function beforeCheck(){
                                if($('#pw-input').val() != $('#pw-check').val()){
                                $('#pw-check-warning').css('display','block');
                                $('#pw-check').focus();
                                return false;
                                } else if(!regExp.test($('#pw-input').val())){
                                $('#pw-warning').css('display','block');
                                $('#pw-input').focus();
                                // alert("비밀번호를 확인해주세요");
                                return false;
                                }
                            };
                            // 닉네임 길이 제한
                            $('#nickname').focusout(function(){
                                if(document.getElementById("nickname").value.length > 20 ||
                                    document.getElementById("nickname").value.length < 3 ){
                                    $('#nick-warning').css('display','block'); 
                                      
                                    } else{
                                    $('#nick-warning').css('display','none'); 
                                   
                            
                                    };
                            });

                            $('#pw-input').focusout(function(){
               
                                // 비번체크
                            var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
                            if(regExp.test($('#pw-input').val())){
                                $('#pw-warning').css('display','none');
                            } else{
                                $('#pw-warning').css('display','block');
                            };



                            
                            });

                            $('#pw-check').keyup(function(){
                                if($('#pw-input').val() != $('#pw-check').val()){
                                $('#pw-check-warning').css('display','block');
                                } else {
                                $('#pw-check-warning').css('display','none');
                                }
                            });

                             // 비밀번호 확인 질문
                            $('#pwAnswer').focusout(function(){
                                if(document.getElementById("pwAnswer").value.length < 3 || 
                                document.getElementById("pwAnswer").value.length > 20 ){
                                $('#pwAnswer-warning').css('display','block'); 
                            
                                } else{
                                $('#pwAnswer-warning').css('display','none'); 
                                
                                }
                            });
                            </script>

</html>