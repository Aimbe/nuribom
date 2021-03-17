<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <form action="${pageContext.request.contextPath}/center_list.bom" method="post">
              <div class="row search">
                <div class="keyword">
                  <span>검색어 입력</span>
                  <input type="text" id="search_keyword" class="input_keyword" placeholder="검색어를 입력하세요"
                  value="${param.keyword}"/>
                </div>
              </div>

              <div class="search_btn_wrapper">
                <button type="submit" class="btn_search">
                  검색하기 <i class="fas fa-search fa-1x"></i>
                </button>
              </div>
</form>