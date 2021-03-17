package com.nuriweb.mybom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IReserveDAO;
import com.nuriweb.mybom.model.dao.inf.IReviewDAO;
import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.model.vo.ReviewVO;
import com.nuriweb.mybom.service.inf.IReviewSVC;

@Service
public class ReviewSVCImpl implements IReviewSVC {

	@Autowired
	IReviewDAO rvDao;
	
	
	@Override
	public List<ReviewVO> selectAllReview(int page) {
		int ofset = (page-1) *PAGE_SIZE_MAIN;
		int limit = PAGE_SIZE_MAIN;
		return rvDao.selectAllReviewPage(ofset, limit);
	}
	

	@Override
	public List<ReviewVO> selectCenterReviewPage(int ctId, int page) {
		int ofset = (page-1) *PAGE_SIZE_CENTER;
		int limit = PAGE_SIZE_CENTER;
		return rvDao.selectCenterReviewPage(ctId, ofset, limit);
	}


	@Override
	public List<ReviewVO> selectAllReview() {
		return this.rvDao.selectAllReview();
	}

	@Override
	public List<ReviewVO> selectAllReviewByCenter(int ctId) {
		return this.rvDao.selectAllReviewByCenter(ctId);
	}

	@Override
	public ReviewVO selectOneReview(int id) {
		// TODO Auto-generated method stub
		return this.rvDao.selectOneReview(id);
	}

	@Override
	public boolean insertOneReview(ReviewVO rv) {
		return this.rvDao.insertOneReview(rv);
	}

	@Override
	public boolean insertOneReview(int mbId, int ctId, int rsId, int rate, String userName, String content) {
		return this.rvDao.insertOneReview(mbId, ctId, rsId, rate, userName, content);
	}

	@Override
	public boolean updateOneReview(ReviewVO rv) {
		return this.rvDao.updateOneReview(rv);
	}

	@Override
	public boolean updateOneReview(int id, int rate, String content) {
		return this.rvDao.updateOneReview(id, rate, content);
	}

	@Override
	public boolean deleteOneReviewRS(int rsId) {
		return this.rvDao.deleteOneReviewRS(rsId);
	}
	@Override
	public boolean updateOneReviewByUserName(int mbId, String userName) {
		return this.rvDao.updateOneReviewByUserName(mbId, userName);
	}

	@Override
	public boolean deleteOneReview(int id) {
		return this.rvDao.deleteOneReview(id);
	}
	@Override
	public int checkMaxPageNumber() {
		int totalAtCnt = rvDao.checkAllReviewCount();
		int maxPg = totalAtCnt / PAGE_SIZE_MAIN + 
				(totalAtCnt % PAGE_SIZE_MAIN == 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg;
	}
	
	@Override
	public List<ReviewVO> getListByMember(int mbId) {
		return this.rvDao.getListByMember(mbId);
	}
	@Override
	public int checkMaxPageNumberCenter(int ctId) {
		int totalAtCnt = rvDao.checkCenterReviewCount(ctId);
		int maxPg = totalAtCnt / PAGE_SIZE_CENTER + 
				(totalAtCnt % PAGE_SIZE_CENTER== 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg;
	}




	@Override
	public List<ReviewVO> getListByMember(int mbId, int page) {
		int ofset = (page-1) *PAGE_SIZE_MY;
		int limit = PAGE_SIZE_MY;
		return rvDao.getListByMemberPage(mbId, ofset, limit);
	}
	@Override
	public int checkMaxPageNumberMyReview(int mbId) {
		int totalAtCnt = rvDao.checkAllMemberCount(mbId);
		int maxPg = totalAtCnt / PAGE_SIZE_MY + 
				(totalAtCnt % PAGE_SIZE_MY == 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg;
	}


	@Override
	public boolean deleteListReview(List<ReviewVO> rvList) {
		int b = 0;
		for (ReviewVO rv : rvList) {
			if(rvDao.deleteOneReview(rv.getReviewId())) {
				b+=1;
			}
		}
		return b==rvList.size();
	}
	
	
}
