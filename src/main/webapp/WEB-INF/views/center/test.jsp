<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center add new form</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/center_add.bom" method="post" >
	
<%-- 		<input type="hidden" name="memberId"  value="${mbPKId}"> --%>
	
		<table border="1">
			<tr> <td>제목:</td> 
				<td><input type="text" name="name" size="64" placeholder="제목 입력" required> </td> 
			</tr>
		
			<tr> <td>소개1:</td> 
				<td>
					<textarea rows="" cols="64" name="firstDesc" placeholder="게시글 내용 입력">
					</textarea>
				</td> 
			</tr>
			<tr> <td>소개2:</td> 
				<td>
					<textarea rows="" cols="64" name="secondDesc" placeholder="게시글 내용 입력">
					</textarea>
				</td> 
			</tr>
			<tr> <td>소개3:</td> 
				<td>
					<textarea rows="" cols="64" name="thirdDesc" placeholder="게시글 내용 입력">
					</textarea>
				</td> 
			</tr>
	
			<tr> 
				<td><input type="reset" value="리셋"> </td> 
				<td><input type="submit" value="새글등록"> </td> 
			</tr>
		</table>
	</form>


</body>
</html>