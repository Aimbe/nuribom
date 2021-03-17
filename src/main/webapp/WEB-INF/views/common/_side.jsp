<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <div id="side-fix-btn">

      <div class="side-inner-btn">
        <a href="${pageContext.request.contextPath}/home.bom">
          <i class="fas fa-home fa-2x home-btn"></i>
        </a>
      </div>
      <div class="side-inner-btn">
        <a href="${pageContext.request.contextPath}/center_list.bom">
         <i class="fas fa-search fa-2x search-btn"></i>
        </a>
      </div>
      <div class="side-inner-btn">
        <a href="${pageContext.request.contextPath}/all-review-page.bom">
         <i class="fas fa-heart fa-2x like-btn"></i>
        </a>
      </div>
      <div class="side-inner-btn">
        <a href="${pageContext.request.contextPath}/home.bom">
          <i class="fas fa-angle-up fa-2x scroll-up-btn"></i>
        </a>
      </div>

    </div>
    
    <script>
    
    $( '.scroll-up-btn' ).click( function() {
		$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
		return false;
	  } );


    
    </script>