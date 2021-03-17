package com.nuriweb.mybom.service.inf;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;

public interface ICenterSVC {
	
	public static final int BASIC_CT_PAGE_SIZE = 10;
	public static final int ADMIN_CT_PAGE_SIZE = 15;
	
//	- 상담소를 이름으로 검색할 수 있다.
	List<CenterVO> selectAllCentersBySearch(String keyword);
	List<CenterVO> searchCenter(String keyword, String target);
	List<CenterVO> searchCenter(String keyword, String target, int pg);
	List<CenterVO> searchCenter(String keyword, String target, int pg, String orderColumn, boolean order);
	List<CenterVO> searchCenter(String keyword, String target, int pg, String orderColumn, boolean order,  Date startDate, Date endDate);
	
	Map<String, Integer> checkMaxPageNumber(String target, String keyword); 	// 최대페이지 수 - 검색한 한정된 게시글 개수 기준..
	
	int checkMaxPageNumber(int pageSize); 	// 최대페이지 수 리턴
	// 상담소
//  - 상담소 전체리스트를 열람할 수 있다.(최신순, 오래된순, 좋아요많은순,... 등)
	List<CenterVO> selectAllCenters(); //최신순
	List<CenterVO> selectAllCentersByOldest();
	List<CenterVO> selectAllCentersByLikesCount();

	// 상담소 조회 - 페이지네이션, 정렬, 태깅...
	List<CenterVO> selectAllCenters(int page, int pageSize);
	List<CenterVO> selectAllCenters(int page, int pageSize, String searchRegion);
	List<CenterVO> selectAllCenters(boolean order);
	
	
	
	
	// 가상 tbl에 맵핑...
		List<CenterRowVO> selectAllCentersForVirtualRow();
		List<CenterRowVO> selectAllCentersForVirtualRow(int page, int pageSize);
		List<CenterRowVO> selectAllCentersForVirtualRowKeyword(int page, int pageSize, String keyword);
		List<CenterRowVO> selectAllCentersForVirtualRowRegion(int page, int pageSize, String region);
		List<CenterRowVO> selectAllCentersForVirtualRow(int page, int pageSize, String category, String tag, String orderBy);
		List<CenterRowVO> selectAllCentersForVirtualRow(int page, int pageSize, String region, String keyword,String category, String tag, String orderBy);
	
	
		List<CenterRowVO> selectAllCentersForVirtualRowLikes();
	
	
		// 좋아요목록 출력위한것...
		List<CenterRowVO> getLikeStatus(int mbId);
		
	
		CenterRowVO getOneCenterVir(int ctId);
	
		// 상담소 검색목록..
		List<CenterRowVO> getCenterSearchList(String keyword, String region, String category, String tag, String orderBy, int offset, int limit);
	
	
	
	
//	상담소의 이메일을 업데이트할 수 있다. => 태그 컬럼으로 사용할 것임..
	boolean updateEmailOfCenter(String email, int ctId);
	
//	- 상담소의 전체 갯수를 조회할 수 있다.
	int getCountOfAllCenters();
	
//	- 상담소 정보를 상세보기 할 수 있다. (아이디)
	CenterVO getOneCenter(int ctId);
	
//	- 상담소의 카테고리로 상담소를 분류하여 조회할 수 있다.
	List<CenterVO> selectAllCentersByCategory(String category);
	
//	- 상담소의 태그로 상담소를 분류하여 조회할 수 있다. (태그는 여러개 소유가능) 	tagDao에서 실행
//	List<CenterVO> selectAllCentersByTag(String tag);
//	List<CenterVO> selectAllCentersByTag(String[] tags);
	
//	- 지역(서울,경기 등)에 따라 상담소를 분류하여 조회할 수 있다.
	List<CenterVO> selectAllCentersByAddressRegion(String addressRegion);
	List<CenterVO> selectAllCentersByAddressCity(String addressCity);

	
// 	- 상담소의 정보를 편집/갱신할 수 있다. (위치, 내용,...)
	boolean updateOneCenter(CenterVO ct); //전체필드수정가능

// 	- 상담소의 운영시간을 업데이트할 수 있다.
	boolean updateOperatingTimeOfCenter(int ctId, String openTime, String closeTime);
	
//	- 상담소의 사이트를 업데이트할 수 있다.
	boolean updateSiteOfCenter(int ctId, String site);
	
//	- 상담소의 전화번호를 업데이트할 수 있다.
	boolean updateTelephoneOfCenter(int ctId, String telephone);
	
//	- 상담소의 좋아요 수를 업데이트할 수 있다.
	boolean updateLikesOfCenter(int ctId, int likes);
	
//	- 상담소의 소개문들을 업데이트할 수 있다.
	boolean updateDescriptionsOfCenter(int ctId, String intro1, String intro2, String intro3, 
			String prTitle1, String prDesc1, String prTitle2, String prDesc2);
	
//	- 상담소의 주소를 업데이트할 수 있다.
	boolean updateAddressRegionOfCenter(int ctId, String addressRegion);
	boolean updateAddressCityOfCenter(int ctId, String addressCity);
	boolean updateAddressDetailOfCenter(int ctId, String addressDetail);
	
//	- 상담소의 이미지들을 업데이트할 수 있다.
	boolean updateImagesOfCenter(int ctId, String mainImg, String firstPrImg, String secondPrImg);
	
// 	- 상담소의 위치를 업데이트할 수 있다. 
	boolean updateLocationOfCenter(int ctId, double latitude, double longitude);
	
//  - 새로운 상담소를 등록할 수 있다.	
	int insertNewCenterReturnKey(CenterVO ct); // db insert 성공시 ctpk키 리턴
	
	boolean insertOneCenter(CenterVO ct);
	boolean insertOneCenter(String name, String telephone, String category, String addressRegion);//가장 기본
	
	//img없이
	boolean insertOneCenter(String name, String telephone, String site, String email, String category, 
			String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle,
			String firstProgramDesc, String secondProgramTitle, String secondProgramDesc, String addressRegion,
			String addressCity, String addressDetail); 
	
	boolean insertOneCenter(String name, String telephone, String site, String mainImg, String email, String category,
			String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle, String firstProgramDesc,
			String firstProgramImg, String secondProgramTitle, String secondProgramDesc, String secondProgramImg,
			String addressRegion, String addressCity, String addressDetail); 
	// full
	boolean insertOneCenterFull(String name, String telephone, String site, int likes, String mainImg, String email,
			String openTime, String closeTime, String category, String firstDesc, String secondDesc, String thirdDesc,
			String firstProgramTitle, String firstProgramDesc, String firstProgramImg, String secondProgramTitle,
			String secondProgramDesc, String secondProgramImg, String addressRegion, String addressCity,
			String addressDetail, double latitude, double longitude); 
	
//  - 상담소를 삭제할 수 있다.
	boolean deleteOneCenter(CenterVO ct);
	boolean deleteOneCenter(int ctId);
	
}
