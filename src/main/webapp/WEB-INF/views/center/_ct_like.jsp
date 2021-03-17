    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <button type="button" class="btn_like" onclick="likeCenter(${param.mbId},${param.ctId})">
      <span title="관심상담소 저장">  
      	<i class="fas fa-heart fa-2x btn_like_icon "  id="heart_${param.ctId}"
      		style="color: ${ct.sStatus eq 1 ? 'palevioletred' : ' rgb(235, 206, 152)'}"></i>
	  </span>
    </button>
  