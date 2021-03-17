package com.nuriweb.mybom.service.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.LikeVO;
import com.nuriweb.mybom.model.vo.MemberVO;

public interface ILikeSVC {
//	- 멤버가 상담소를 좋아요 할 수 있다.
	boolean likeAdd(int mbId, int ctId);
	int likeAddReturnKey(int mbId, int ctId); //PK return
	
//	- 멤버가 상담소 좋아요를 취소(삭제)할 수 있다.
	boolean likeDelete(int mbId, int ctId);
	
	
//	-해당 멤버가 해당상담소를 좋아요했는지 조회할수 있다
	//=> 데이터가 있으면 좋아요한것/ 다시누르면 좋아요취소(삭제)
	LikeVO aleadyExistedLike(int mbId, int ctId);
	boolean isAleadyLiked(int mbId, int ctId);
	
	
//	-  해당 상담소의 좋아요 갯수를 조회 할 수 있다.
	int getLikeCountOfOneCenter(int ctId);	//svcImpl에서 centerVO_likes 필드에 저장... => 좋아요수 업데이트 가능..
	
	
//	- 멤버가 좋아요 누른 상담소의 갯수를 조회할 수 있다.
	int getLikeCountOfOneMember(int mbId);

	
//	- 전체 좋아요(테이블)리스트를 조회할 수 있다.
	List<LikeVO> selectAllLikeList(); //=> svcImpl에서 centerList조회/MemberList 조회 가능하도록..
	
//	한개의 상담소가 지닌 좋아요리스트를 조회할 수 있다. 
	List<LikeVO> selectAllLikeListOfOneCenter(int ctId);
	
//	한 멤버가 지닌 좋아요리스트를 조회할 수 있다.
	List<LikeVO> selectAllLikeListOfOneMember(int mbId);
	
	
//	svcImpl..
	//- 해당 상담소를 좋아요 한 멤버리스트를 조회할 수 있다.
	List<MemberVO> selectMemberListWhoLikesCenter(int ctId); 
	//- 한 멤버가 좋아요 누른 상담소들을 리스트로 조회할 수 있다.
	List<CenterVO> selectCenterListLikedByOneMember(int mbId);
	List<CenterVO> selectCenterListLikedByOneMember(MemberVO mb);
	
	
	// 마이페이지
		int checkMaxpageNumberByUser(int page, int mbId);
		List<LikeVO> selectAllLikeListOfOneMember(int mbId, int page, int pageSize);
		
}
