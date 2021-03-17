package com.nuriweb.mybom.service.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.model.vo.ReplyVO;

public interface IReplySVC {

	
	public static final String FREE_REPLY ="free_reply";
	public static final String QNA_REPLY ="qna_reply";
	public static final String NURINAME ="누리봄 관리자";
	
	//게시판 (누리봄 전하는 글,자유 게시판) 에서 회원이 덧글을 작성할 수 있다.
	//게시판(QnA)에 어드민 관리자가 어드민페이지에서 덧글을 작성할 수 있다. (질문)
	boolean insertNewReply(String table,ReplyVO rp);
	boolean insertNewReply(String table,int bdId, int mbId, String userName, String content);
	int insertNewReplyReturnKey(String table,ReplyVO rp); 
	
	//게시판 디테일페이지에서 해당 글에 작성된 덧글을 조회할 수 있다.
	List<ReplyVO> selectAllReply(String table,int bdId);
	
	//수정,삭제를 위한 개별 조회
	ReplyVO selectOneReply(String table,int id);
	
	
	
	//특정 회원의 덧글을 조회할 수 있다.
	List<ReplyVO> selectAllReplyByUser(String table,int mbId);
	
	List<ReplyVO> selectAllReplyByUser(String table,String UserName);

	//질문용 
	ReplyVO selectOneReplyByQnA(int bdId);
		
	
	//해당 덧글을 작성한 회원이라면 덧글을 수정할 수 있다.
	boolean updateOneReply(String table,ReplyVO rp);
	boolean updateOneReply(String table,int id,String content);
	
	//회원의 닉네임이 수정될 시 같이 수정되야하므로..
	boolean updateAllReplyByUser(String table,int mbId,String userName);
	
	//해당 덧글을 작성한 회원이라면 덧글을 삭제할 수 있다.
	boolean deleteOneReply(String table,int id);
	
	//게시글 다중 삭제 
	boolean deleteAllReplyByBoard(String table,int bdId);

	
	//마이페이지에서 내가 쓴 댓글을 검색 할 수 있다.
	List<ReplyVO> searchAllReply(String table,int mbId,String content);
	
	//게시글별 덧글 개수를 조회할 수 있다.
	int checkReplyCountForBoard(String table,int bdId);
}
