package com.nuriweb.mybom.model.dao.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.BoardVO;

public interface IBoardDAO {

	
	
//	1.게시판 글을 최신 작성날자순으로 전체조회할 수 있다.
	List<BoardVO> selectAllBoards(String table); 
	
//  1-1.특정 회원의 글을 전체조회할 수 있다.
	List<BoardVO> selectAllBoardsByUser(String table,int mbId); 

//  1-2.특정 회원의 글을 전체조회할 수 있다. <UQ>
	List<BoardVO> selectAllBoardsByUser(String table,String UserName);  
	
	
//	2.게시판 글을 클릭하여 내용을 조회할 수 있다.
	BoardVO selectOneBoard(String table,int id);
	
	
//	3.글을 작성한 회원은 게시글을 수정할 수 있다.
	boolean updateOneBoard(String table,BoardVO bd);
	boolean updateOneBoard(String table,int id,String title,String content,String password,String thumbnail);
	boolean updateOneBoard(String table,int id,String title,String content,String password);

//  조회수 업데이트
	boolean increseView(String table,int id); //조회수 증가 
	
// 회원닉네임 수정 시 업데이트
	boolean updateAllBoardByUserName(String table,int mbId,String UserName);
	
	
	
//	4.글을 작성한 회원은 게시글을 삭제할 수 있다.
	boolean deleteOneBoard(String table,int id);

	
//	5.게시판 글을 제목으로 검색하여 조회 할 수 있다. 
	List<BoardVO> searchAllBoards(String table,String title,int offset,int limit);

//  5-1.상단 검색 필드를 사용하여 해당 회원이 쓴 글을 제목으로 검색할 수 있다.
	List<BoardVO> searchAllBoards(String table,int mbId,String title,int offset,int limit);	
	
//	6.회원은 작성 버튼 클릭으로 글을 쓸 수 있다.
	boolean insertNewBoard(String table,BoardVO bd);
//	boolean insertNewBoard(String table,int mbId, String UserName, String title, String content, String password);
	boolean insertNewBoard(String table,int mbId, String UserName, String title, String content, String password, String thumbnail);
	
//	int insertNewBoardReturnKeyByNoneThumbnailCategory(String table,BoardVO bd); 
	int insertNewBoardReturnKeyByThumbnailCategory(String table,BoardVO bd); 
	
	List<BoardVO> selectAllBoards(String table,int offset,int limit); //pg
	
	
	
	int checkAllBoardCount(String table);
	int checkAllBoardCount(String table,String keyword);
	int checkAllBoardCount(String table,int mbId);

	List<BoardVO> selectAllBoardsByMbId(String table, int offset, int limit, int mbId);
	

	
}
