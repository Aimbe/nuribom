<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<!DOCTYPE html>
<html lang="ko">

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
    
    <!-- 각자의 css파일이나 js 파일 링크는 여기에! -->
	 <link href="${pageContext.request.contextPath}/resources/admin/css/admin-qna-board.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/fontawesome.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/solid.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/brands.min.css">
     
 <script>
      
        $(document).ready(function() {
   
          $('.qna-title').click(function(){
  
            $(this).next().slideToggle();
           
           });  
  
          <c:if test="${not empty qnaReilyMsg}">
          	alert('${qnaReilyMsg}');
          </c:if>
          
        });    
    
    </script>
</head>



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
                                    src="${pageContext.request.contextPath}/resources/admin/img/undraw_profile.svg">
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
                    <!--<h1 class="h3 mb-0 text-gray-800">답변 미정 질문 목록</h1>-->
     
                    </div>

                   

                    <div class="question-layoutbox">

                        <h4 class="page-title">답변 대기 질문 목록</h4>
                        
                        <div class="qna-all-list">
						 <c:if test="${not empty qnaSize}">
                            <table class="qna-list-table">
              				 <c:forEach var="qna" items="${qnaList}">
                              <tr>
                                <td>
              
                                  <h6 class="qna-category">
                                    <c:if test="${qna.category eq 1}">
				                      	[로그인문의]
				                      </c:if>
				                      <c:if test="${qna.category eq 2}">
				                      	[상담문의]
				                      </c:if>
				                      <c:if test="${qna.category eq 3}">
				                      	[기능문의]
				                      </c:if>
                                  </h6>
                                  <h6 class="qna-title">
                                	  	 ${qna.title}
                                  </h6>
              
                                  <div class="answer">
              
                                    <h6 class="qna-content">
										${qna.content}              
                                    </h6>
              
                                    <p class="answer-title">
              
                                 		     누리봄 관리자
              
                                    </p>
                                    <div class="assistance-detail-reply">
                  
                                      <form method="post" action="${pageContext.request.contextPath}/admin_qna_reply_proc.bom">
                                   		<input type="hidden" name="qnaId" value="${qna.id}">
                                   		<input type="hidden" name="mbId" value="1">
                                   		<textarea name="content" id="assistance-reply-textarea-${qna.id}" class="assistance-reply-textarea" placeholder="사용자의 마음을 헤아리는 누리봄이 됩시다." maxlength="200"></textarea>
                                        <button type="submit" class="ass-reply-send">작성</button>
                                      </form>
                                    </div>
                                    
                                  </div>
                               </td>
                                <td>
              
                                  <p class="qna-user-name">${qna.writer}</p>
                                  <p class="qna-date"><fmt:formatDate value="${qna.createdAt}" pattern="yyyy.MM.dd"/></p>
                                </td>
                                
                              </tr> 
                             </c:forEach>
							</table>
              			   </c:if>
			               <c:if test="${qnaSize eq 0 }">
							<h2 class="none-qna">답변 예정 질문이 없습니다.</h2>
						   </c:if>
                          </div><!--qna-all-list-->

						 <c:if test="${not empty qnaSize}">
                          <div class="qna-board-page">

                            <button type="button" class="page-btn prev"> <i class="fas fa-chevron-left fa-1x page-icon"></i></button>
                            
                            <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
                            <c:if test="${pn eq vs.current }">
                            <button type="button" class="page-btn qna-sel-page">${vs.current}</button>
                            </c:if>
                            <c:if test="${pn ne vs.current }">
                            <button type="button" class="page-btn" onclick="movePage(${vs.current})">${vs.current}</button>
                            </c:if>
                          
                            </c:forEach>
                            
                            <button type="button" class="page-btn next"> <i class="fas fa-chevron-right fa-1x page-icon"></i></button>
              
                          </div>
						</c:if>

                    </div><!--qna-layoutbox-->
				   </div>
                            
        </div>
    </div>

<script type="text/javascript">

$('.prev').click(function(){

	<c:if test="${pn gt 1 }">
		location.href="${pageContext.request.contextPath}/admin_qna_none_reply.bom?pg=${pn-1}";
	</c:if>
});   
   
   
$('.next').click(function(){

	<c:if test="${pn lt maxPg }">
		location.href="${pageContext.request.contextPath}/admin_qna_none_reply.bom?pg=${pn+1}";
	</c:if>
});  
   
   
function movePage(e){
	
    location.href ="${pageContext.request.contextPath}/admin_qna_none_reply.bom?pg="+e;

}  


</script>

</body>

</html>