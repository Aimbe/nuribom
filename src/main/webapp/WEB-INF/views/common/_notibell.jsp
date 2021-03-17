<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 



  			  <ul>
     			 <li class="member-bell">
                  <a href="#">
                    <i class="fas fa-bell fa-1x"></i>
                    <span class="bell-circle"><b class="bell-count">${noSize}</b></span>
                  </a>

                  <div class="show-bell">

                    <div class="bell-top">
                      
                      <ul>
                        <li>새로운 알림 ${noSize}개</li>
                        <li><a href="all_notifi.bom">모두보기</a></li>
                      </ul>
                    </div>

                  <div class="bell-content">

                      <ul>
                      <c:if test="${not empty noList}">	 	     		 	
      		 			<c:forEach var="no" items ="${sessionScope.noList}"> 
                        <li>${no.msgDetail}
                  			<div>      
                            <c:choose>
				
					<c:when test="${no.category eq '0'}">
				
						by ${no.nickname}
				
					</c:when>
				
					<c:when test="${no.category eq '1'}">

							by 관리자
						</c:when>
			
						<c:when test="${no.category eq '2'}">
							by 게시글 등록 알리미
						</c:when>

						<c:when test="${no.category eq '3'}">

							 by 개인정보 변경 알리미

						</c:when>

						<c:when test="${no.category eq '4'}">

							 by 회원 가입 알리미
							
						</c:when>
						
						<c:when test="${no.category eq '5'}">

							 by ${no.name}
							
						</c:when>
						
						<c:when test="${no.category eq '6'}">

							 by ${no.name}
							
						</c:when>
						
						<c:when test="${no.category eq '7'}">

							 by ${no.name}
							
						</c:when>
						<c:otherwise>

							by 관리자

					</c:otherwise>

						</c:choose>
						</div>
                          <span class="bell-time">
                       
                          <fmt:formatDate value="${no.timestampAt}" 
						pattern="yyyy/MM/dd"/></span>
                        </li>
                       	</c:forEach> 
     				 		 </c:if>
                       
                      </ul>

                  </div>


                  </div><!--알림창-->

                </li>
                
             