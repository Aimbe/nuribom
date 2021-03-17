package com.nuriweb.mybom.service.inf;

import java.sql.Timestamp;
import java.util.List;

import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.model.vo.ReviewVO;

public interface IReserveSVC {

	//-예약
	
	// 원하는 상담소에서 상담일정을  예약할 수 있다.
	boolean insertReserve(ReserveVO rs);
	// 예약번호로 상담예약해놓은  일정을 삭제할 수 있다
	int deleteReserve(int reserveId);
	// 예약번호로 예약정보를 찾을 수 있다.
	ReserveVO selectOneReserve(int reserveId);
	// 방문했는지 알수있다 ( 예약시간이 지금시간보다 빠른지 ) 
	boolean visitCheck(int reserveId);
	// 모든 예약정보를 조회할 수 있다.
	List<ReserveVO> selectAllReserve();
	
	

	
	// 5가지 동시검색 리스트 리턴 //관리자
	List<ReserveVO> selectTakesReserve(int Id, String Nickname,
			String CenterName, Timestamp day, int time);
	
	// 특정 상담소의 예약정보를 조회할 수 있다.
	List<ReserveVO> getListByCenterAll(int centerId);
	// 특정 상담소의 예약정보중 아직 방문하지 않은 정보만 조회할 수 있다.
	List<ReserveVO> getListByCenterNotVisit(int centerId);
	// 특정 상담소의 예약정보중 아직 방문한 정보만 조회할 수 있다.
	List<ReserveVO> getListByCenterVisit(int centerId);

	// 특정 상담소의 예약시간정보를 받아올수있다
	List<Integer> getTimeListOfCenter(String day, int centerId);
	

	ReserveVO thisMemberVisitCenter(int mbId, int ctId);
	
	// 한 멤버의 예약리스트를 가져올수 있다.
	List<ReserveVO> getAllListForMember(int userId);
	
	// 한 멤버가 가장 최근에한 예약정보를 리턴한다
	ReserveVO getOneReserveRecently(int userId);
	
	// 한 멤버의 예약정보중 리뷰작성을한 예약정보만 리스트로 리턴
	List<ReserveVO> getListReviewCheck(int userId);
	
	// 한 멤버의 예약정보중 리뷰작성을'안'한 예약정보만 리스트로 리턴 
	List<ReserveVO> getListReviewUnCheck(int userId);
	
	// 7가지 동시검색 리스트 리턴 //관리자
	List<ReserveVO> getListSearchReserve(int Id, String Nickname,
			String CenterName, String day, int time, int visit, int review);
	
	boolean changeReviewCheck(int id);
	
	
	
	
	// 모든 예약정보를 조회할 수 있다. - 어드민 15개
	List<ReserveVO> selectAllReserve(int page);
	// 7가지 동시검색 리스트 리턴  //관리자 - 15개
	List<ReserveVO> getListSearchReserve(int Id, String Nickname,
			String CenterName, String day, int time, int visit, int review, int page);
	// 한 멤버의 예약리스트를 가져올수 있다. -3개
		List<ReserveVO> getAllListForMember(int userId , int page);
	
	
	
	
	
	public static final int PAGE_SIZE_ADMIN = 15;
	public static final int PAGE_SIZE_MY = 3;
	
	int checkMaxPageNumber();
	int checkMaxPageNumberMyReserve(int mbId);
	
	
	
}
