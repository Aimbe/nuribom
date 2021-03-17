package com.nuriweb.mybom.service.inf;

import java.util.List;
import java.util.Map;

import com.nuriweb.mybom.model.vo.BoardVO;

public interface IBoardSVC {

	//테이블명
	String FREE_BOARD ="free_board";
	String NURI_BOARD ="nuri_board";
	String FAQ_BOARD ="faq_board";
	
	String category_nuri ="nuri";
	String category_faq ="faq";
	
	
	//페이지당 표시 최대 글 수
	int PAGE_SIZE = 10;
	int NURI_PAGE_SIZE =4;
	int NURI_MAIN_PAGE_SIZE =6;

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



// 회원닉네임 수정 시 업데이트
	boolean updateAllBoardByUserName(String table,int mbId,String UserName);
	
	
	
//	4.글을 작성한 회원은 게시글을 삭제할 수 있다.
	boolean deleteOneBoard(String table,int id);

	
//	5.게시판 글을 제목으로 검색하여 조회 할 수 있다. 
	List<BoardVO> searchAllBoards(String table,String title,int pg);
	
//  5-1.상단 검색 필드를 사용하여 해당 회원이 쓴 글을 제목으로 검색할 수 있다.
	List<BoardVO> searchAllBoards(String table,int mbId,String title,int pg);
	
	
//	6.회원은 작성 버튼 클릭으로 글을 쓸 수 있다.
	boolean insertNewBoard(String table,BoardVO bd);
//	boolean insertNewBoard(String table,int mbId, String UserName, String title, String content, String password);
	boolean insertNewBoard(String table,int mbId, String UserName, String title, String content, String password, String thumbnail);
	

//	int insertNewBoardReturnKeyByNoneThumbnailCategory(String table,BoardVO bd); 
	int insertNewBoardReturnKeyByThumbnailCategory(String table,BoardVO bd); 
	
	int checkMaxpageNumber(String table,int pgSize);
	List<BoardVO> selectAllBoards(String table,int page,int pgSize); //pg
	
	//메인홈 누리봄용 
	List<BoardVO> selectAllBoardsByMain(String table,int limit); 
	
	
	int checkAllBoardCount(String table);
	Map<String, Integer> checkMaxPageNumberForSearch(String table,String keyword);
	//메인홈 연결할 누리봄 도움주는 글 페이지 limit 6 
	
	int checkMaxPageNumberMyBoard(String table, int pgSize, int mbId);
	List<BoardVO> selectAllBoardsByUser(String table, int page, int pgSize, int mbId); //pg
	
	//다수개 삭제 처리용 
	boolean deleteMyBoard(List<Integer> bdList);
	
}
