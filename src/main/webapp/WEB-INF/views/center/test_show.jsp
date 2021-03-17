<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center add new form</title>
</head>
<body>
<div id="at_show">
		<table border="1">
			<tr>
				<th>번호:</th> 
				<td>${center.id}</td>
			</tr>
			<tr>
				<th>제목:</th> 
				<td><c:out value="${center.name}" default="제목없음"/> </td>
			</tr>
			
			<tr>
				<th>내용:</th> 
				<td><textarea rows="10" cols="64" readonly><c:out value="${center.firstDesc}" default="내용없음"/></textarea> </td>
			</tr>
				<tr>
				<th>내용:</th> 
				<td><textarea rows="10" cols="64" readonly><c:out value="${center.secondDesc}" default="내용없음"/></textarea> </td>
			</tr>
				<tr>
				<th>내용:</th> 
				<td><textarea rows="10" cols="64" readonly><c:out value="${center.thirdDesc}" default="내용없음"/></textarea> </td>
			</tr>
	
		
		</table>
		
	<!-- 게시글 편집, 삭제, 좋아요 메뉴.. -->
			
	</div>


</body>
</html>