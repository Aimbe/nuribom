package com.nuriweb.mybom.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IReserveDAO;
import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.service.inf.IReserveSVC;

@Service
public class ReserveSVCImpl implements IReserveSVC {

	@Autowired
	IReserveDAO rsDao;
	
	
	
	
	@Override
	public List<ReserveVO> selectAllReserve(int page) {
		int ofset = (page-1) * PAGE_SIZE_ADMIN;
		int limit = PAGE_SIZE_ADMIN;
		return this.rsDao.selectAllReserve(ofset, limit);
	}

	@Override
	public List<ReserveVO> getListSearchReserve(int Id, String Nickname, String CenterName, String day, int time,
			int visit, int review, int page) {
		int ofset = (page-1) * PAGE_SIZE_ADMIN;
		int limit = PAGE_SIZE_ADMIN;
		return this.rsDao.getListSearchReserve(Id, Nickname, CenterName, day, time, visit, review, ofset, limit);
	}

	@Override
	public List<ReserveVO> getAllListForMember(int userId, int page) {
		int ofset = (page-1) * PAGE_SIZE_MY;
		int limit = PAGE_SIZE_MY;
		return this.rsDao.getAllListForMember(userId, ofset, limit);
	}

	@Override
	public int checkMaxPageNumber() {
		int totalAtCnt = this.rsDao.checkAllReserveCount();
		int maxPg = totalAtCnt / PAGE_SIZE_ADMIN + 
				(totalAtCnt % PAGE_SIZE_ADMIN== 0 ? 0 : 1);
		return maxPg;
	}

	@Override
	public int checkMaxPageNumberMyReserve(int mbId) {
		int totalAtCnt = this.rsDao.checkAllReserveMemberCount(mbId);
		int maxPg = totalAtCnt / PAGE_SIZE_MY + 
				(totalAtCnt % PAGE_SIZE_MY== 0 ? 0 : 1);
		return maxPg;
	}

	
	
	@Override
	public boolean insertReserve(ReserveVO rs) {
		return this.rsDao.insertReserve(rs);
	}

	@Override
	public int deleteReserve(int reserveId) {
		return this.rsDao.deleteReserve(reserveId);
	}

	@Override
	public ReserveVO selectOneReserve(int reserveId) {
		return this.rsDao.selectOneReserve(reserveId);
	}

	@Override
	public boolean visitCheck(int reserveId) {
		return this.rsDao.visitCheck(reserveId);

	}

	@Override
	public ReserveVO thisMemberVisitCenter(int mbId, int ctId) {
		return this.rsDao.thisMemberVisitCenter(mbId, ctId);
	}

	@Override
	public List<ReserveVO> selectAllReserve() {
		return this.rsDao.selectAllReserve();
	}

	@Override
	public List<ReserveVO> selectTakesReserve(int Id, String Nickname, String CenterName, Timestamp day, int time) {
		return this.rsDao.selectTakesReserve(Id, Nickname, CenterName, day, time);

	}

	@Override
	public List<ReserveVO> getListByCenterAll(int centerId) {
		return this.rsDao.getListByCenterAll(centerId);

	}

	@Override
	public List<ReserveVO> getListByCenterNotVisit(int centerId) {
		return this.rsDao.getListByCenterNotVisit(centerId);

	}

	@Override
	public List<ReserveVO> getListByCenterVisit(int centerId) {
		return this.rsDao.getListByCenterVisit(centerId);

	}

	@Override
	public List<Integer> getTimeListOfCenter(String day, int centerId) {
		return this.rsDao.getTimeListOfCenter(day,centerId);

	}

	@Override
	public List<ReserveVO> getAllListForMember(int userId) {
		return this.rsDao.getAllListForMember(userId);

	}

	@Override
	public ReserveVO getOneReserveRecently(int userId) {
		return this.rsDao.getOneReserveRecently(userId);

	}

	@Override
	public List<ReserveVO> getListReviewCheck(int userId) {
		return this.rsDao.getListReviewCheck(userId);

	}

	@Override
	public List<ReserveVO> getListReviewUnCheck(int userId) {
		return this.rsDao.getListReviewUnCheck(userId);

	}

	@Override
	public List<ReserveVO> getListSearchReserve(int Id, String Nickname, String CenterName, String day, int time,
			int visit, int review) {
		return this.rsDao.getListSearchReserve(Id, Nickname, CenterName, day, time, visit, review);

	}

	@Override
	public boolean changeReviewCheck(int id) {
		return this.rsDao.changeReviewCheck(id);
	}

	
}
