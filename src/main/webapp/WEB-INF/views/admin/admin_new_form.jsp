<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>

     <meta http-equiv="Content-Type" content="text/html" charset ="UTF-8"> 
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
    
    
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">   
    <link href="${pageContext.request.contextPath}/resources/admin/css/sb-admin-2 copy.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/admin/css/admin-board-edit.css" rel="stylesheet">


	<script type="text/javascript">
	
	<c:if test="${not empty failMsg}">
		alert('${failMsg}');
	</c:if>
	
	</script>

    <script>
      
        $(document).ready(function() {

           
            //$('#board-category option:selected').val();
            $('#board-category').change(function(){

                var category =$('#board-category option:selected').attr('value'); 
                
                if(category =='faq'){
                    $('.upload-box').css('display','none');
                
                }else{
                
                    $('.upload-box').css('display','block');
                }

             

            });


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
                     <!--<h1 class="h3 mb-0 text-gray-800">????????? Q&A ?????????</h1>-->
                     <h4 class="page-title">????????? ??? ??????</h4>
                    </div>

                   
                    <!-- ???????????? ???????????? ?????????????????????! -->

                  

                    <div id="standalone-container">
          
                        <form method="post" action="${pageContext.request.contextPath}/admin_add_proc.bom" id="admin-edit" enctype="multipart/form-data" >

                            <select name="category" id="board-category" class="board-category">
                                <option value="faq">?????? ?????? ?????? </option>
                                <option value="nuri">????????? ?????? ??? </option>
                              </select>
						  <input type="hidden" name ="mbId" value="1">
						  <input type="hidden" name ="writer" value="?????????">
						  
                          <input type="text" name="title" maxlength="64" class="user-title" placeholder="????????? ??????????????????." required>
                          <div id="editor"></div>
                          <input type="hidden" name="content" id="user-board-content" required>
                         
                          <div class="upload-box">
                            <label for="user-board-thumbnale" class="file-label">?????????</label> 
                            <input type="file" name="upfile" id="user-board-thumbnale" accept=".jpg,.png">
                              
                            <div class="upload">
                              <p class="upload-file-name">400*400 ????????? ????????? jpg png??? ???????????????.</p>
                            </div>
                          </div>
                          <div class="pw-box">
                            <label for="user-board-pw" class="pw-label">????????????</label>
                            <input type="password" id ="user-board-pw" name="password" maxlength="6"  placeholder="???????????? 6???" required>
                            <span class="pw-length">???????????? 6????????? ?????? ??????????????????.</span>
                          </div>
          
                          <div class="buttom-wrap">
                            <button id="saveDelta" disabled>??????</button>
                          </div>
                       </form>
                        </div>
                        
                     
                      </div>
          

                    
                   
                        

                   </div>
                            
        </div>
    </div>

  

    <script>

        var toolbarOptions = [
       
       
        [{'header':[1,2,3,false]}],
        ['bold','italic','underline'],
        ['image'],
        [{'color':[]},{'background':[]}]
    
        
          
    
        ];
    
        var quill = new Quill('#editor', {
             modules: {
            
            toolbar: toolbarOptions
          },
          placeholder: '???????????? ??????????????????',
          theme: 'snow'
    });
    
    
    
    $('#saveDelta').click(function(){
    
    
      /*window.delta = quill.getContents(); //quill.setContents(window.delta);??? ??????! 
                                          //???????????? ?????? ?????? ?????????! => quill.enable(false)
      console.log(window.delta);
    */
  
      var content = quill.root.innerHTML;
      $('#user-board-content').val(content);
      //var c = $('#user-board-content').val();
      //alert(c);
      $('#user-edit').submit();
      
    
    });
    
  
    $('#user-board-thumbnale').on('change',function(){
  
        var filename = $('#user-board-thumbnale').val();
        $('.upload-file-name').text(filename);
  
  
    });
  
  
    $('#user-board-pw').keyup(function(){

    	if($('#user-board-pw').val().length != 6){
    		$('.pw-length').css('display','block');
    		$('#saveDelta').attr('disabled', true);
    	}else{
    		$('.pw-length').css('display','none');
    		$('#saveDelta').attr('disabled', false);
    	}

    });
  
    </script>
    

</body>

</html>