package com.nuriweb.mybom.model.dao.inf;

import java.sql.Timestamp;
import java.util.List;

import com.nuriweb.mybom.model.vo.AllNotifiVO;

public interface IAllNotifiDAO {

	
	//댓글이 업데이트 될 시에 댓글정보를 선택해서 알림 리스트로 가져올 수 있다. (scheduler 사용)
	//예약을 했을시 그 정보를 알림 리스트로 받아 올 수 있다.
	//예약 일시가 지났을시에 리뷰 알림을 보낼 수 있다. 
	//개인 정보를 업데이트 할 시에 알림을 받아 올 수 있다. 
	
	//새 알림을 받을 수 있다. 
	boolean insertNewNotifi(AllNotifiVO no);
	boolean insertNewNotifi(int no,int bdId, int commentId, int questiontId, int reviewId, String name, String nickname);
	
	//자유게시판 댓글 알림을 받을수 있다.
//	
	boolean insertNewFreeBoardNotifi(int mbId, String nickname, int category,String link);
	
	//관리자에게 f&Q 댓글 알림을 받을 수 있다.
	
	boolean insertNewQuestionNotifi(int mbId,String nickname, int category,String link
		);
	
	//게시글 등록 알림을 받을 수 있다. 
	
	boolean insertNewPostNotifi(int mbId, String nickname, int category,String link);
	
	//개인정보 변경 알림을 받을 수 있다.
	boolean insertNewPrivateNotifi(int mbId, String nickname, int category);
	
	//가입 축하 알림을 받을 수 있다.
	boolean insertNewMemberNotifi(int mbId, String nickname, int category);
	//예약 알림을 받을 수 있다. 
	boolean insertNewReserveNotifi(int mbId,String name,Timestamp reserveDay,int category,String link);
	
	//리뷰 등록 알림을 받을수 있다.
	boolean insertNewReviewPostNotifi(int mbId,String name, String nickname,
			int category,String link);
	
	//상담소 좋아요 알림을 받을 수 있다.
	boolean insertLikeCenterNotifi(int mbId,String name, String nickname,
			int category,String link);
	
	
	//알림을 편집할 수 있다.
	boolean updateNoti(AllNotifiVO no);
	
	//알림을 삭제 할 수 있다.
	boolean deleteOneNoti(int no);
	
	//알림을 전체 조회 할 수 있다. 
	List<AllNotifiVO> selectAllNotifi(); 
	
	//알림을 닉네임으로 조회 할 수 있다. 
	List<AllNotifiVO> selectAllNotifiWithName(String nickname);
	
	//알림을 mbid로 조회 할 수 있다
	List<AllNotifiVO> selectAllNotifiWithMbId(int mbId);
	
	//알림을 mbid로 조회 한뒤 페이지네이션 할 수 있다. 
	List<AllNotifiVO> selectAllNotifiWithMbId_PG(int mbId,int offset, int limit);
	
	//알림을 종류 별로 조회 할 수 있다. 
	List<AllNotifiVO> selectAllNotifiByCategory(int category);
	
	int checkAllNotifiCount(int mbId);
	
	
}
