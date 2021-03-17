package com.nuriweb.mybom.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.IBoardDAO;
import com.nuriweb.mybom.model.vo.BoardVO;

@Repository
public class BoardMySqlDAOImpl implements IBoardDAO {

	
	@Autowired
	private JdbcTemplate jtem;
	
	
//	1.게시판 글을 최신 작성날자순으로 전체조회할 수 있다.
	@Override
	public List<BoardVO> selectAllBoards(String table) {
		
		String sql_select_all_boards = "SELECT * FROM "+table+" order by created_at desc";
		
		try {
		return jtem.query(sql_select_all_boards, BeanPropertyRowMapper.newInstance(BoardVO.class));
		}catch (DataAccessException dea) {
			
			System.out.println("board 전체조회 실패");
			return null;
		}
	}

//  1-1.특정 회원의 글을 전체조회할 수 있다.	
	@Override
	public List<BoardVO> selectAllBoardsByUser(String table, int mbId) {
		
		String sql_select_all_boards_by_User ="SELECT * FROM "+table+" where mb_id= ? order by created_at desc";
		try {
		return jtem.query(sql_select_all_boards_by_User, BeanPropertyRowMapper.newInstance(BoardVO.class),mbId);
		}catch (DataAccessException dea) {
			
			System.out.println("board dao: 회원 아이디 글 조회 실패:" + mbId);
			return null;
		}
	}
	
//  1-2.특정 회원의 글을 전체조회할 수 있다. <UQ>
	@Override
	public List<BoardVO> selectAllBoardsByUser(String table, String UserName) {
		
		String sql_select_all_boards_by_UserName ="SELECT * FROM "+table+" where writer = ? order by created_at desc";
		try {
		return jtem.query(sql_select_all_boards_by_UserName, BeanPropertyRowMapper.newInstance(BoardVO.class),UserName);	
	
		}catch (DataAccessException dea) {
		
			System.out.println("board dao: 회원 아이디 닉네임 조회 실패:"+UserName);
			return null;
		}
	}
	
//	2.게시판 글을 클릭하여 내용을 조회할 수 있다.
	@Override
	public BoardVO selectOneBoard(String table, int id) {

		String sql_select_one_board_by_id = "SELECT * FROM "+table+" where id = ?";
		try {
		return jtem.queryForObject(sql_select_one_board_by_id, BeanPropertyRowMapper.newInstance(BoardVO.class),id);
		
		}catch (EmptyResultDataAccessException dea) {
		
			
			System.out.println("board dao: 0개 게시글 조회");
			return null;
			
		}catch (DataAccessException dea) {
			
			System.out.println("board dao: 해당 글 조회 실패: " + id);
			return null;
		}
		
	}
	
//	3.글을 작성한 회원은 게시글을 수정할 수 있다.
	@Override
	public boolean updateOneBoard(String table, BoardVO bd) {
		
		String sql_update_one_board ="update "+table+" set  writer= ? , title= ? , content= ? ,password= ?,view = ?,thumbnail= ? where id= ?";
		
		try {
		int r = jtem.update(sql_update_one_board,bd.getWriter(),bd.getTitle(),bd.getContent(),bd.getPassword(),bd.getView(),bd.getThumbnail(),bd.getId());
		return r==1;
		}catch (DataAccessException dae) {

			System.out.println("board dao 글 수정 실패: "+bd);
			return false;
		}
		
	}

	@Override
	public boolean updateOneBoard(String table, int id, String title, String content, String password,
			String thumbnail) {
		
		String sql_update_one_board="update "+table+" set title= ? , content= ? ,password= ?,thumbnail= ? where id= ?";
		
		try {

			int r = jtem.update(sql_update_one_board,title,content,password,thumbnail,id);
			return r == 1;
			
		}catch (DataAccessException dea) {
			
			System.out.println("board dao 글 수정 실패: "+ id);
			return false;
		}
		
	}

	@Override
	public boolean updateOneBoard(String table, int id, String title, String content, String password) {
		
		String sql_update_one_board="update "+table+" set title= ? , content= ? ,password= ? where id= ?";
		
		try {
			
			int r = jtem.update(sql_update_one_board,title,content,password,id);
			return r == 1;
			
		}catch (DataAccessException dea) {
			
			System.out.println("board dao 글 수정 실패: "+ id);
			return false;
		}
	}


	
// 회원닉네임 수정 시 업데이트
	@Override
	public boolean updateAllBoardByUserName(String table, int mbId, String UserName) {
		
		//워크벤치 edit preferences sql editor safe updates 체크 해제하기 
		String sql_update_all_board_UserName = "update "+table+" set writer=? where mb_id= ?";
		
		try {

			int r = jtem.update(sql_update_all_board_UserName,UserName,mbId);
			return r == 1;
			
		}catch (DataAccessException dea) {
		
			System.out.println("board dao 글쓴이 회원 닉네임 수정 실패: " + mbId);
			return false;
			
		}
		
	}

//	4.글을 작성한 회원은 게시글을 삭제할 수 있다.
	@Override
	public boolean deleteOneBoard(String table, int id) {
		
		String sql_delete_one_board = "delete from "+table+" where id = ?";
		
		try {
			int r =jtem.update(sql_delete_one_board,id);
			return r==1;
		}catch (DataAccessException dea) {
			
			System.out.println("board dao 글 삭제 실패: " + id);
			return false;
		}
		
	}

//	5.게시판 글을 제목으로 검색하여 조회 할 수 있다.
	@Override
	public List<BoardVO> searchAllBoards(String table, String title,int offset,int limit) {
		
		String sql_search_all_board_for_title= "select * from "+table+" where title like concat('%',?,'%') order by created_at desc limit ?,?";
		
		try {
			
			return jtem.query(sql_search_all_board_for_title,BeanPropertyRowMapper.newInstance(BoardVO.class),title,offset,limit);
			
		} catch (DataAccessException dae) {
		
			System.out.println(">>"+table+" board 검색 실패: "+ title);
			return null;
		}
		

	}

	
//  5-1.상단 검색 필드를 사용하여 해당 회원이 쓴 글을 제목으로 검색할 수 있다.	
	@Override
	public List<BoardVO> searchAllBoards(String table, int mbId, String title,int offset,int limit) {
		//TODO::select * from freeboard where title like '%춤%' and mb_id = 1;
		String sql_search_all_board_for_title= "select * from "+table+" where title like concat('%',?,'%') and mb_id=? order by created_at desc limit?,?";
		
		try {
			
			return jtem.query(sql_search_all_board_for_title,BeanPropertyRowMapper.newInstance(BoardVO.class),title,mbId,offset,limit);
			
		} catch (DataAccessException dae) {
		
			System.out.println(">>"+table+" board 검색 실패: "+ title+" ,검색 회원 아이디: "+mbId);
			return null;
		}
	}
	
	
//	6.회원은 작성 버튼 클릭으로 글을 쓸 수 있다.
	@Override
	public boolean insertNewBoard(String table, BoardVO bd) {
		
		String sql_insert_new_board = "insert into "+table+" value(null,?,?,?,?,?,0,now(),?)";
		
		try {
			
			int r = jtem.update(sql_insert_new_board,bd.getMbId(),bd.getWriter(),bd.getTitle(),bd.getContent(),bd.getPassword(),bd.getThumbnail());
			return r == 1;
			
		}catch (DataAccessException dae) {
	
			System.out.println("board dao 글 작성 실패: "+ bd);
			return false;
		}
	
	}

//	@Override
//	public boolean insertNewBoard(String table, int mbId, String UserName, String title, String content,
//			String password) {
//		
//		String sql_insert_new_board ="insert into "+table+" value(null,?,?,?,?,?,0,now(),'default.png')";
//		
//		try {
//		
//			int r = jtem.update(sql_insert_new_board,mbId,UserName,title,content,password);
//			return r == 1;
//			
//		}catch (DataAccessException dea) {
//		
//			System.out.println("board dao 글 작성 실패: " + mbId +"|" +UserName +"|" +title +"|" +content+"|"+password);
//			return false;
//		}
//		
//		
//	}

	@Override
	public boolean insertNewBoard(String table, int mbId, String UserName, String title, String content,
			String password, String thumbnail) {
		
		String sql_insert_new_board ="insert into "+table+" value(null,?,?,?,?,?,0,now(),?)";
		
		try {
			
			int r = jtem.update(sql_insert_new_board,mbId,UserName,title,content,password,thumbnail);
			return r == 1;
			
		}catch (DataAccessException dea) {
		
			System.out.println("board dao 글 작성 실패: " + mbId +"|" +UserName +"|" +title +"|" +content+"|"+password+"|"+thumbnail);
			return false;
		}
		
	}

	// <<pk>> 키를 리턴해주는 insert문. 게시글을 등록하고 바로 보러 이동할 때 사용.
	// faq,free board 사용
//	@Override
//	public int insertNewBoardReturnKeyByNoneThumbnailCategory(String table, BoardVO bd) {
//		
//		String sql_insert_one_board ="insert into "+table+" value(null,?,?,?,?,?,0,now(),'default.png')";
//		KeyHolder kh = new GeneratedKeyHolder();
//		
//		PreparedStatementCreator psc = new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				
//				PreparedStatement psmt = con.prepareStatement(sql_insert_one_board, new String[] {"id"});
//				psmt.setInt(1, bd.getMbId());
//				psmt.setString(2, bd.getWriter());
//				psmt.setString(3, bd.getTitle());
//				psmt.setString(4, bd.getContent());
//				psmt.setString(5, bd.getPassword());
//				
//				return psmt;
//			}
//		};
//		
//		jtem.update(psc, kh);
//		Number pk = kh.getKey();
//		
//		return pk.intValue();
//	}

	// nuribom board 사용
	@Override
	public int insertNewBoardReturnKeyByThumbnailCategory(String table, BoardVO bd) {
		
		String sql_insert_one_board ="insert into "+table+" value(null,?,?,?,?,?,0,now(),?)";
		KeyHolder kh = new GeneratedKeyHolder();
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement psmt = con.prepareStatement(sql_insert_one_board, new String[] {"id"});
				psmt.setInt(1, bd.getMbId());
				psmt.setString(2, bd.getWriter());
				psmt.setString(3, bd.getTitle());
				psmt.setString(4, bd.getContent());
				psmt.setString(5, bd.getPassword());
				psmt.setString(6, bd.getThumbnail());
				
				return psmt;
			}
		};
		
		jtem.update(psc, kh);
		Number pk = kh.getKey();
		
		return pk.intValue();
	}
	
	//"select * from articles order by created_at desc limit ?,?"; //오프셋 위치, 페이지 당 레코드 수 

	//페이지네이션이 일어난 전체글 조회
	@Override
	public List<BoardVO> selectAllBoards(String table,int offset, int limit) {
		
		String sql_select_all_boards_pg = "select * from "+table+" order by created_at desc limit ?,?";
		
			try {
			
			return jtem.query(sql_select_all_boards_pg,BeanPropertyRowMapper.newInstance(BoardVO.class),offset,limit);
	
			}catch (DataAccessException dae) {
				
				System.out.println("게시글 페이지네이션 전체 조회 실패");
				return null;
			}
	}

	//조회수 증가
	@Override
	public boolean increseView(String table, int id) {
	
		String sql_increse_view ="update "+table+" set view = view + 1 where id = ?";
		
		int r = this.jtem.update(sql_increse_view, id);
		
		return r == 1;
	}

	@Override
	public int checkAllBoardCount(String table) {
		
		String sql_check_all_count = "select count(id) from "+table;
		return jtem.queryForObject(sql_check_all_count,Integer.class);
	}

	@Override
	public int checkAllBoardCount(String table, String keyword) {
		
		String sql_check_all_search_board_count ="SELECT count(*) FROM "+table+" where title like concat('%',?,'%')";
	
			try {
			return jtem.queryForObject(sql_check_all_search_board_count,Integer.class,keyword);
	
			}catch (EmptyResultDataAccessException e) {
				System.out.println(">> 검색 게시글 0개 조회..");
				return 0;
			}catch (DataAccessException dae) {
				System.out.println(">> 검색 키워드 결과 카운트 sql문 에러..");
				return 0;
			}
		}

	@Override
	public int checkAllBoardCount(String table, int mbId) {
		String sql_check_all_member_board_count = "select count(id) from "+table+" where mb_Id=?";
		try {
			return jtem.queryForObject(sql_check_all_member_board_count,Integer.class, mbId);
	
			}catch (EmptyResultDataAccessException e) {
				System.out.println(">> 검색 게시글 0개 조회..");
				return 0;
			}catch (DataAccessException dae) {
				System.out.println(">> 검색 키워드 결과 카운트 sql문 에러..");
				return 0;
			}
	}

	//페이지네이션이 일어난 특정 회원 글  조회
		@Override
		public List<BoardVO> selectAllBoardsByMbId(String table,int offset, int limit, int mbId) {
			
			String sql_select_mb_boards_pg = "select * from "+table+" where mb_id = ? order by created_at desc limit ?, ?";
			
				try {
				
				return jtem.query(sql_select_mb_boards_pg,BeanPropertyRowMapper.newInstance(BoardVO.class), mbId, offset,limit);
		
				}catch (DataAccessException dae) {
					
					System.out.println("게시글 페이지네이션 전체 조회 실패");
					return null;
				}
		}


}
