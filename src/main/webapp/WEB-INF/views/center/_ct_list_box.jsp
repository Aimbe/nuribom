<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<div class="clist_box">

	<c:if test="${not empty mbLoginName}">
		<div id="ctlike_${mbPKId}_${ct.sId}" class="like_wrapper" >
            <button  type="button" class="btn_like" 
            onclick="likeCenter(${mbPKId},${ct.sId})" >
              <span title="관심상담소 저장">
              	<i class="fas fa-heart fa-2x btn_like_icon"
              	style="color: ${ct.sStatus eq 1 ? ' palevioletred' : ' rgb(235, 206, 152)'}" ></i>
              </span>
            </button>
        </div>
	</c:if>


           <a href="center_detail.bom?ctId=${ct.sId}"> 
           <div class="cname clist_info_top">
              <h1 id="cname">${ct.sName}</h1>
			
			<c:forEach var="tagMap" items="${tagMap}"> 
					<c:if test="${ct.sId eq tagMap.key}">
						<c:forEach var="tags" items="${tagMap.value}">	
	              			<span class="center_tag">${tags}</span> &nbsp;
	              		</c:forEach>
            		</c:if>
			</c:forEach>

		

            </div>
			</a>
            
            <div class="info_bottom_wrapper">
              <div class="clist_info_bottom">
               <a href="center_detail.bom?ctId=${ct.sId}"> 
                <p class="info_detail">
                  <span>${ct.sAR} ${ct.sAC} </span> <br />
                  <span>${ct.sOT} ~ ${ct.sCT}</span> <br />
                  <span>Tel. ${ct.sTel}</span>
                </p>
              </a>
                  <span class="btn_reserve" onclick="location.href='${pageContext.request.contextPath}/reserve.bom?ctId=${ctId}'">예약하기</span>
              </div>

			<a href="center_detail.bom?ctId=${ct.sId}"> 
              <div class="clist_img">
                <img src="./resources/images/center/${ct.sMainImg}" >
              </div>
            </a>
            </div>
</div>



















