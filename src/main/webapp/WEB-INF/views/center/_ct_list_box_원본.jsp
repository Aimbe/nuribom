<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="clist_box">

	<c:if test="${not empty mbLoginName}">
		<div id="ctlike_${mbPKId}_${ct.id}" class="like_wrapper" >
            <button  type="button" class="btn_like" 
            onclick="likeCenter(${mbPKId},${ct.id})" >
              <span title="관심상담소 저장">
              	<i class="fas fa-heart fa-2x ${selStatus eq null ? '' : ' selected'}"></i>
              </span>
            </button>
        </div>
	</c:if>


           <a href="center_detail.bom?ctId=${ct.id}"> 
           <div class="cname clist_info_top">
              <h1 id="cname">${ct.name}</h1>
			
			<c:forEach var="tagMap" items="${tagMap}"> 
					<c:if test="${ct.id eq tagMap.key}">
						<c:forEach var="tags" items="${tagMap.value}">	
	              			<span class="center_tag">${tags}</span> &nbsp;
	              		</c:forEach>
            		</c:if>
			</c:forEach>

            </div>
			</a>
            
            <div class="info_bottom_wrapper">
              <div class="clist_info_bottom">
               <a href="center_detail.bom?ctId=${ct.id}"> 
                <p class="info_detail">
                  <span>${ct.addressRegion} ${ct.addressCity} </span> <br />
                  <span>${ct.openTime} ~ ${ct.closeTime}</span> <br />
                  <span>Tel. ${ct.telephone}</span>
                </p>
              </a>
                  <span class="btn_reserve">예약하기</span>
              </div>

			<a href="center_detail.bom?ctId=${ct.id}"> 
              <div class="clist_img">
                <img src="./resources/images/center/${ct.mainImg}" >
              </div>
            </a>
            </div>
</div>



















