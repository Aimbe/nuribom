package com.nuriweb.mybom.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IAllNotifiDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;

@Service
public class AllNotifiSVCImpl implements IAllNotifiSVC{

	@Autowired
	IAllNotifiDAO noDao;
	
	@Override
	public boolean insertNewNotifi(AllNotifiVO no) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewNotifi(no);
	}

	@Override
	public boolean insertNewNotifi(int no, int bdId, int commentId, int questiontId, int reviewId, String name,
			String nickname) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewNotifi(no,bdId, commentId, questiontId, reviewId, name, nickname);
	}

	
	@Override
	public boolean insertNewFreeBoardNotifi(int mbId, String nickname, int category, String link) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewFreeBoardNotifi(mbId, nickname, category, link);
	}

	@Override
	public boolean insertNewQuestionNotifi(int mbId, String nickname, int category, String link) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewQuestionNotifi(mbId, nickname, category, link);
	}

	@Override
	public boolean insertNewPostNotifi(int mbId, String nickname, int category, String link) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewPostNotifi(mbId, nickname, category, link);
	}

	@Override
	public boolean insertNewPrivateNotifi(int mbId, String nickname, int category) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewPrivateNotifi(mbId, nickname, category);
	}

	@Override
	public boolean insertNewMemberNotifi(int mbId, String nickname, int category) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewMemberNotifi(mbId, nickname, category);
	}

	@Override
	public boolean insertNewReserveNotifi(int mbId, String name, Timestamp reserveDay, int category, String link) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewReserveNotifi(mbId, name, reserveDay, category, link);
	}

	@Override
	public boolean insertNewReviewPostNotifi(int mbId, String name, String nickname, int category, String link) {
		// TODO Auto-generated method stub
		return this.noDao.insertNewReviewPostNotifi(mbId, name, nickname, category, link);
	}

	@Override
	public boolean insertLikeCenterNotifi(int mbId, String name, String nickname, int category, String link) {
		// TODO Auto-generated method stub
		return this.noDao.insertLikeCenterNotifi(mbId, name, nickname, category, link);
	}

	@Override
	public boolean updateNoti(AllNotifiVO no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOneNoti(int no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AllNotifiVO> selectAllNotifi() {
		// TODO Auto-generated method stub
		return this.noDao.selectAllNotifi();
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiWithName(String nickname) {
		// TODO Auto-generated method stub
		return this.noDao.selectAllNotifiWithName(nickname);
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiWithMbId(int mbId) {
		// TODO Auto-generated method stub
		return this.noDao.selectAllNotifiWithMbId(mbId);
	}
	

	
	
	@Override
	public List<AllNotifiVO> selectAllNotifiWithMbId_PG(int mbId, int offset, int limit) {
		
		return this.noDao.selectAllNotifiWithMbId_PG(mbId, offset, limit);
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiByCategory(int category) {
		// TODO Auto-generated method stub
		return this.noDao.selectAllNotifiByCategory(category);
	}

	@Override
	public int checkAllNotifiCount(int mbId) {
		// TODO Auto-generated method stub
		return this.noDao.checkAllNotifiCount(mbId);
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiWithMbId(int mbId, int page) {
		
		int offset = (page-1) *PAGE_SIZE_MY;
		int limit = PAGE_SIZE_MY;
		return noDao.selectAllNotifiWithMbId_PG(mbId, offset, limit);
	}
	
	public static final int PAGE_SIZE_MY = 10;

	@Override
	public int checkMaxPageNumberAllNotifi(int mbId) {
		int totalnotiCount = noDao.checkAllNotifiCount(mbId);
		int maxPg = totalnotiCount / PAGE_SIZE_MY+ 
				(totalnotiCount % PAGE_SIZE_MY == 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg;
	}
	
	//날짜별로 리스트 조회/
	//selectAllNotifiWithtimestamp; where timestamp = ???
	
	
	
}
