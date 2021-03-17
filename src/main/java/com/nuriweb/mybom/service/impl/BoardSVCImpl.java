package com.nuriweb.mybom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IBoardDAO;
import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.service.inf.IBoardSVC;

@Service
public class BoardSVCImpl implements IBoardSVC {

	
	@Autowired
	IBoardDAO bdDao;
	
	@Override
	public List<BoardVO> selectAllBoards(String table) {
		
		return bdDao.selectAllBoards(table);
	}

	@Override
	public List<BoardVO> selectAllBoardsByUser(String table, int mbId) {
		
		return bdDao.selectAllBoardsByUser(table, mbId);
	}

	@Override
	public List<BoardVO> selectAllBoardsByUser(String table, String UserName) {
		
		return bdDao.selectAllBoardsByUser(table, UserName);
	}

	@Override //조회수 증가
	public BoardVO selectOneBoard(String table, int id) {
	
		
		BoardVO bd = bdDao.selectOneBoard(table, id);
		
		if(bd!=null) {
		
			if(bdDao.increseView(table, id)) {
				return bd;
			
			}else { //조회수 증가에 실패했다면..
				
				System.out.println("svc 게시글 상세 조회: 조회수 증가 실패..");
				return null;
			}
		}else {
			
			return null;
		}
	}

	@Override
	public boolean updateOneBoard(String table, BoardVO bd) {
		
		return bdDao.updateOneBoard(table, bd);
	}

	@Override
	public boolean updateOneBoard(String table, int id, String title, String content, String password,
			String thumbnail) {
		
		return bdDao.updateOneBoard(table, id, title, content, password, thumbnail);
	}

	@Override
	public boolean updateOneBoard(String table, int id, String title, String content, String password) {
		
		return bdDao.updateOneBoard(table, id, title, content, password);
	}

	@Override
	public boolean updateAllBoardByUserName(String table, int mbId, String UserName) {
	
		return bdDao.updateAllBoardByUserName(table, mbId, UserName);
	}

	@Override
	public boolean deleteOneBoard(String table, int id) {
		
		return bdDao.deleteOneBoard(table, id);
	}

	@Override
	public List<BoardVO> searchAllBoards(String table, String title,int pg) {
		
		int offset = (pg-1)* PAGE_SIZE;
		return bdDao.searchAllBoards(table, title, offset, PAGE_SIZE);
		
	}


	@Override
	public List<BoardVO> searchAllBoards(String table, int mbId, String title,int pg) {
	
		int offset = (pg-1)*PAGE_SIZE;
		return bdDao.searchAllBoards(table, mbId, title, offset, PAGE_SIZE);
	}

	
	@Override
	public boolean insertNewBoard(String table, BoardVO bd) {
		
		return bdDao.insertNewBoard(table, bd);
	}

//	@Override
//	public boolean insertNewBoard(String table, int mbId, String UserName, String title, String content,
//			String password) {
//		// TODO Auto-generated method stub
//		return bdDao.insertNewBoard(table, mbId, UserName, title, content, password);
//	}

	@Override
	public boolean insertNewBoard(String table, int mbId, String UserName, String title, String content,
			String password, String thumbnail) {

		return bdDao.insertNewBoard(table, mbId, UserName, title, content, password, thumbnail);
	}

//	@Override
//	public int insertNewBoardReturnKeyByNoneThumbnailCategory(String table, BoardVO bd) {
//	
//		return bdDao.insertNewBoardReturnKeyByNoneThumbnailCategory(table, bd);
//	}

	@Override
	public int insertNewBoardReturnKeyByThumbnailCategory(String table, BoardVO bd) {
	
		return bdDao.insertNewBoardReturnKeyByThumbnailCategory(table, bd);
	}

	@Override
	public int checkMaxpageNumber(String table,int pgSize) {
		
		//남는 레코드들도 한 페이지로 다해서 처리..
		int totalCount = bdDao.checkAllBoardCount(table);
		int maxPg = totalCount/pgSize + (totalCount%pgSize ==0?0:1);
		return maxPg;
	}
	
	@Override
	public List<BoardVO> selectAllBoards(String table, int page,int pgSize) {
		
		int offset = (page-1)*pgSize;
		int limit = pgSize;
		List<BoardVO> bdListPg = bdDao.selectAllBoards(table, offset, limit);
		if(bdListPg!=null) {
			
			System.out.println(">> bdSvc, pg: "+ page+"bdList: "+ bdListPg.size());
			return bdListPg;
			
		}else {
			System.out.println(">> bdList 페이지네이션 전체조회 실패..");
			return null;
		}
	}//

	@Override
	public List<BoardVO> selectAllBoardsByMain(String table, int limit) {
	
		List<BoardVO> bdList = bdDao.selectAllBoards(table, 0,limit);
		if(bdList!=null) {
			
			System.out.println(">> bdSvc, 메인홈용: "+bdList+"개");
			return bdList;
		}else {
			
			System.out.println(">> bdList 최신순 6개 조회 실패..");
			return null;
		}
	}
	
	
	
	@Override
	public int checkAllBoardCount(String table) {
		
		return bdDao.checkAllBoardCount(table);
	}

	@Override
	public Map<String, Integer> checkMaxPageNumberForSearch(String table, String keyword) {
	
		
		int totalBdCnt = bdDao.checkAllBoardCount(table, keyword);
		int maxPg = totalBdCnt/PAGE_SIZE + (totalBdCnt % PAGE_SIZE ==0?0:1);
		
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("maxPg",maxPg);
		rMap.put("totalBdCnt", totalBdCnt);
		return rMap;
	}

	@Override
	public int checkMaxPageNumberMyBoard(String table, int pgSize, int mbId) {
		int totalAtCnt = bdDao.checkAllBoardCount(table, mbId);
		int maxPg = totalAtCnt / PAGE_SIZE + 
				(totalAtCnt % PAGE_SIZE == 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		
		return maxPg;
	}
	
	@Override
	public List<BoardVO> selectAllBoardsByUser(String table, int page,int pgSize, int mbId) {
		
		int offset = (page-1)*pgSize;
		int limit = pgSize;
		List<BoardVO> bdListPg = bdDao.selectAllBoardsByMbId(table, offset, limit, mbId);
		if(bdListPg!=null) {
			
			System.out.println(">> bdSvc, pg: "+ page+"bdList: "+ bdListPg.size());
			return bdListPg;
			
		}else {
			System.out.println(">> bdList 페이지네이션 전체조회 실패..");
			return null;
		}
	}//

	
	//다수개 글아이디 넘어오면 삭제해주는..
	@Override
	public boolean deleteMyBoard(List<Integer> bdList) {
		
		if(bdList!=null) {
			
			for (int i = 0; i < bdList.size(); i++) {
				
				int bdId = bdList.get(i);
				boolean b = bdDao.deleteOneBoard(FREE_BOARD, bdId);
				if(b) {
					
					System.out.println(">> 요청 게시글 아이디:"+bdId+"번 삭제 성공!");
					
				}else {
					System.out.println(">> 요청 게시글 아이디:"+bdId+"번 삭제 실패!");
					return false;
				}
			}
			return true;
			
		}else {
			
			System.out.println(">> bdList null..");
			return false;
					
		}
		
	}
}
