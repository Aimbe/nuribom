package com.nuriweb.mybom.service.inf;
import java.sql.Timestamp;
import java.util.List;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.ReviewVO;
public interface IAllNotifiSVC {


	
	//새 알림을 받을 수 있다. 
	boolean insertNewNotifi(AllNotifiVO no);
	boolean insertNewNotifi(int no,int bdId, int comment_id, int question_id, int review_id, String name, String nickname);
	
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
	
	//pg추가
	List<AllNotifiVO> selectAllNotifiWithMbId(int mbId, int page);
	
	//알림을 mbid로 조회 한뒤 페이지네이션 할 수 있다. 
	List<AllNotifiVO> selectAllNotifiWithMbId_PG(int mbId,int offset, int limit);
	
	//알림을 종류 별로 조회 할 수 있다. 
	List<AllNotifiVO> selectAllNotifiByCategory(int category);
	
	//총 카운트 조회
	int checkAllNotifiCount(int mbId);
	
	//max pg조회
	int checkMaxPageNumberAllNotifi(int mbId);
	
	
}
