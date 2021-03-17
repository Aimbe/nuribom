<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트 전체 조회</title>
</head>
<body>

	<h3 style="color:blue">${msg}</h3>
	

	<ul>
	
	<c:forEach var="qn" items="${qnlist}"> 
	
		<li>${qn.mbId}</li>
		<li>${qn.writer}</li>
		<li>${qn.title}</li>
		<li>${qn.content}</li>
		<li><c:out value="${qn.secret?'공개':'비공개'}"/></li>
		
	</c:forEach>
	
	
	
<%-- 		<li>${bd.id}</li> --%>
<%-- 		<li>${bd.writer}</li> --%>
<%-- 		<li>${bd.title}</li> --%>
<%-- 		<li>${bd.id}</li> --%>
	
	
<%-- 		<c:forEach var="bd" items="${bdList}"> --%>
			
<!-- 			<li> -->
<%-- 				${bd.id}  --%>
<!-- 			</li> -->
<!-- 			<li> -->
<%-- 				${bd.writer}  --%>
<!-- 			</li> -->
<!-- 			<li> -->
<%-- 				${bd.title}  --%>
<!-- 			</li> -->
<!-- 			<li> -->
<%-- 				${bd.content}  --%>
<!-- 			</li> -->
		
<!-- 			<li> -->
<%-- 				<c:out value="${bd.thumbnail}" default="default.png"/>  --%>
<!-- 			</li> -->
		
		
<%-- 		</c:forEach> --%>
	</ul>



</body>
</html>