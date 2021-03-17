<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 	<td> -->
<!-- 		<label class="checkbox-inline" for="check_centers_id"> -->
<%-- 			<input type="checkbox" name="check_centers_id" id="check_centers_id" value="${ct.id}" onclick="check_centers(this)"> --%>
<!-- 		</label> -->
<!-- 	</td> -->
<tr id="ct_list" class="center_log">
	<c:set var="num" value="${ctSize -((pn-1)*15)}"/>
		<td class="center_list_number" >
			<c:out value="${num-vs.index}"/>
		</td>
	    <td scope="row" class="center_id">${ct.id}</td>
	    <td class="center_category">${ct.category}</td>
	    <td class="center_name">${ct.name} </td>
	    <td class="center_address">${ct.addressRegion} ${ct.addressCity}</td>
	    <td class="center_tel_num">${ct.telephone}</td>
	    <td class="center_site"><a href="${ct.site}">${ct.site}</a></td>
	    <td class="center_createdAt">
	    	<fmt:formatDate value = "${ct.createdAt}" pattern = "yyyy.MM.dd"/></td>
		<td class="admin_btns">
			 <a href="${pageContext.request.contextPath}/center_detail.bom?ctId=${ct.id}"><input type="button" id="btn_detail" value="상세"/></a>
			 <a href="${pageContext.request.contextPath}/center_edit_form.bom?ctId=${ct.id}"><input type="button" id="btn_edit" value="수정"/></a>
			 <input type="button" id="btn_delete" class="btn_delete" value="삭제" onclick="DeleteCenterAdmin(${ct.id})"/>
		</td>	    	
</tr>

<script>

  
</script>
