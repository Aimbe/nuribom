package com.nuriweb.mybom.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;
import com.nuriweb.mybom.service.inf.ICenterSVC;
import com.nuriweb.mybom.service.inf.ITagSVC;

@Service
public class CenterSVCImpl implements ICenterSVC {

	@Autowired
	ICenterDAO ctDao;
	@Autowired
	ITagSVC tagSvc;
	
	
	@Override
	public List<CenterVO> selectAllCentersBySearch(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> selectAllCenters() {
		return ctDao.selectAllCenters();
	}

	@Override
	public List<CenterVO> selectAllCentersByOldest() {
		return ctDao.selectAllCentersByOldest();
	}

	@Override
	public List<CenterVO> selectAllCentersByLikesCount() {
		return ctDao.selectAllCentersByLikesCount();
	}

	// page 받아서 조회...
	@Override
	public List<CenterVO> selectAllCenters(int page, int pageSize) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPg = ctDao.selectAllCenters(offset, limit);
		System.out.println("ctSvc, page="+page+", ctList=" +ctListPg.size());
		
		return ctListPg;
	}
	
	// 최대 페이지 체크...
	@Override
	public int checkMaxPageNumber(int pageSize) {
		int totalCtCnt = ctDao.getCountOfAllCenters();
		int maxPage = totalCtCnt / pageSize + (totalCtCnt % pageSize == 0? 0:1);
		return maxPage;
	}

	
	@Override
	public List<CenterVO> selectAllCenters(boolean order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int getCountOfAllCenters() {
		return ctDao.getCountOfAllCenters();
	}

	@Override
	public CenterVO getOneCenter(int ctId) {
		return ctDao.getOneCenter(ctId);
	}

	@Override
	public List<CenterVO> selectAllCentersByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> selectAllCentersByAddressRegion(String addressRegion) {
		return ctDao.selectAllCentersByAddressRegion(addressRegion);
	}

	@Override
	public List<CenterVO> selectAllCentersByAddressCity(String addressCity) {
		return ctDao.selectAllCentersByAddressRegion(addressCity);
	}

	@Override
	public boolean updateOneCenter(CenterVO ct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOperatingTimeOfCenter(int ctId, String openTime, String closeTime) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSiteOfCenter(int ctId, String site) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTelephoneOfCenter(int ctId, String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLikesOfCenter(int ctId, int likes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDescriptionsOfCenter(int ctId, String intro1, String intro2, String intro3, String prTitle1,
			String prDesc1, String prTitle2, String prDesc2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAddressRegionOfCenter(int ctId, String addressRegion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAddressCityOfCenter(int ctId, String addressCity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAddressDetailOfCenter(int ctId, String addressDetail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateImagesOfCenter(int ctId, String mainImg, String firstPrImg, String secondPrImg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLocationOfCenter(int ctId, double latitude, double longitude) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOneCenter(CenterVO ct) {
		return ctDao.insertOneCenter(ct);
	}

	@Override
	public boolean insertOneCenter(String name, String telephone, String category, String addressRegion) {
		return ctDao.insertOneCenter(name, telephone, category, addressRegion);
	}

	@Override
	public boolean insertOneCenter(String name, String telephone, String site, String email, String category,
			String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle, String firstProgramDesc,
			String secondProgramTitle, String secondProgramDesc, String addressRegion, String addressCity,
			String addressDetail) {
		return ctDao.insertOneCenter(name, telephone, site, email, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, secondProgramTitle, secondProgramDesc, 
				addressRegion, addressCity, addressDetail);
	}

	@Override
	public boolean insertOneCenter(String name, String telephone, String site, String mainImg, String email,
			String category, String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle,
			String firstProgramDesc, String firstProgramImg, String secondProgramTitle, String secondProgramDesc,
			String secondProgramImg, String addressRegion, String addressCity, String addressDetail) {
		return ctDao.insertOneCenter(name, telephone, site, mainImg, email, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, firstProgramImg, 
				secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail);
	}

	@Override
	public boolean insertOneCenterFull(String name, String telephone, String site, int likes, String mainImg,
			String email, String openTime, String closeTime, String category, String firstDesc, String secondDesc,
			String thirdDesc, String firstProgramTitle, String firstProgramDesc, String firstProgramImg,
			String secondProgramTitle, String secondProgramDesc, String secondProgramImg, String addressRegion,
			String addressCity, String addressDetail, double latitude, double longitude) {
		return ctDao.insertOneCenterFull(name, telephone, site, likes, mainImg, email, openTime, closeTime, category, firstDesc, secondDesc, thirdDesc, 
				firstProgramTitle, firstProgramDesc, firstProgramImg, secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail, latitude, longitude);
		
	}

	@Override
	public boolean deleteOneCenter(CenterVO ct) {
		return ctDao.deleteOneCenter(ct);
	}

	@Override
	public boolean deleteOneCenter(int ctId) {
		return ctDao.deleteOneCenter(ctId);
	}

	@Override
	public int insertNewCenterReturnKey(CenterVO ct) {
		return ctDao.insertNewCenterReturnKey(ct);
	}

	@Override
	public List<CenterVO> searchCenter(String keyword, String target) {
		// TODO Auto-generated method stub
		return null;
	}

//	All 타켓 한정...
	@Override
	public List<CenterVO> searchCenter(String keyword, String target, int pg) {
		int offset = (pg-1)*BASIC_CT_PAGE_SIZE;
		String orderBy = "order by created_at desc";
		return ctDao.searchCenter(keyword, offset, BASIC_CT_PAGE_SIZE, orderBy);
	}

//	검색시 최대 페이지 수
	@Override
	public Map<String, Integer> checkMaxPageNumber(String target, String keyword) {
		int totalCtCnt = ctDao.getAllCenterCount(target, keyword);
		int maxPg = totalCtCnt/ BASIC_CT_PAGE_SIZE + (totalCtCnt % BASIC_CT_PAGE_SIZE == 0? 0:1);
		Map<String, Integer> rMap = new HashMap<>();
		rMap.put("maxPg", maxPg); //최대 검색페이지수
		rMap.putIfAbsent("totlaCtCnt", totalCtCnt);
		return rMap;
		
	}
	

	@Override
	public List<CenterVO> searchCenter(String keyword, String target, int pg, String orderColumn, boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> searchCenter(String keyword, String target, int pg, String orderColumn, boolean order,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmailOfCenter(String email, int ctId) {
		return ctDao.updateEmailOfCenter(email, ctId);
	}

	@Override
	public List<CenterVO> selectAllCenters(int page, int pageSize, String searchRegion) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPgReg = ctDao.selectAllCentersByRegion(searchRegion, offset, limit);
		return ctListPgReg;
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow() {
		
		return ctDao.selectAllCentersForVirtualRow();
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow(int page, int pageSize) {
		
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPg = ctDao.selectAllCenters(offset, limit);
		System.out.println("ctSvc 일반, page="+offset+", ctList=" +ctListPg.size());
		
		return ctDao.selectAllCentersForVirtualRow(offset, pageSize);
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRowKeyword(int page, int pageSize, String keyword) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPg = ctDao.selectAllCenters(offset, limit);
		System.out.println("ctSvc 키워드, page="+offset+", ctList=" +ctListPg.size());
		return ctDao.selectAllCentersForVirtualRowKeyword(offset, pageSize, keyword);
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRowRegion(int page, int pageSize, String region) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPg = ctDao.selectAllCenters(offset, limit);
		System.out.println("ctSvc 지역, page="+offset+", ctList=" +ctListPg.size());
		return ctDao.selectAllCentersForVirtualRowRegion(offset, pageSize, region);
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow(int page, int pageSize, String category, String tag,	String orderBy) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPg = ctDao.selectAllCenters(offset, limit);
		System.out.println("ctSvc 다중..., page="+offset+", ctList=" +ctListPg.size());
		return ctDao.selectAllCentersForVirtualRow(offset, pageSize, category, orderBy);
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow(int page, int pageSize, String region, String keyword,
			String category, String tag, String orderBy) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		List<CenterVO> ctListPg = ctDao.selectAllCenters(offset, limit);
		System.out.println("ctSvc 풀 옵션..., page="+offset+", ctList=" +ctListPg.size());
		return ctDao.selectAllCentersForVirtualRow(offset, pageSize, region, keyword, category, tag, orderBy);
	}

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRowLikes() {
		return ctDao.selectAllCentersForVirtualRowLikes();
	}

	@Override
	public List<CenterRowVO> getLikeStatus(int mbId) {
		return ctDao.getLikeStatus(mbId);
	}

	@Override
	public CenterRowVO getOneCenterVir(int ctId) {
		return ctDao.getOneCenterVir(ctId);
	}

	@Override
	public List<CenterRowVO> getCenterSearchList(String keyword, String region, String category, String tag,
			String orderBy, int page, int pageSize) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		
		return ctDao.getCenterSearchList(keyword, region, category, tag, orderBy, offset, limit);
	}



	

}
