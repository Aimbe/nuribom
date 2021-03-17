package com.nuriweb.mybom.service.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.model.vo.ReviewVO;

public interface IReviewSVC {
	
	//전체 리뷰를 조회할 수 있다.
	List<ReviewVO> selectAllReview();
	List<ReviewVO> selectAllReview(int page);
	
	//상담소디테일 페이지에서 해당 상담소의 리뷰를 조회할 수 있다.
	List<ReviewVO> selectAllReviewByCenter(int ctId);
	List<ReviewVO> selectCenterReviewPage(int ctId,int page);
	
	//리뷰 수정,삭제 개별 선택
	// 리뷰 id로 해당리뷰정보를 받을수있다.
	ReviewVO selectOneReview(int id);
	
	//상담소디테일 페이지에서 예약을 했던 회원이 상담소 리뷰를 작성할 수 있다.
	boolean insertOneReview(ReviewVO rv);
	boolean insertOneReview(int mbId, int ctId, int rsId, int rate, String userName, String content);
	
	//해당 리뷰를 작성한 회원은 마이페이지에서 리뷰를 수정할 수 있다.
	boolean updateOneReview(ReviewVO rv);
	boolean updateOneReview(int id,int rate,String content);
	boolean updateOneReviewByUserName(int mbId,String userName);
	
	//해당 리뷰를 작성한 회원은 마이페이지에서 리뷰를 삭제할 수 있다.
	boolean deleteOneReview(int id);
	boolean deleteOneReviewRS(int rsId) ;
	// 여러개 삭제할수있다
	boolean deleteListReview(List<ReviewVO> rvList);
	
	//한 회원의 리뷰리스트를 조회할수있다.
	List<ReviewVO> getListByMember(int mbId);
	List<ReviewVO> getListByMember(int mbId, int page);
		
	public static final int PAGE_SIZE_MAIN = 3;
	public static final int PAGE_SIZE_CENTER = 6;
	public static final int PAGE_SIZE_MY = 5;
	int checkMaxPageNumber();
	int checkMaxPageNumberCenter(int ctId);
	int checkMaxPageNumberMyReview(int mbId);
	
}
