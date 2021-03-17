package com.nuriweb.mybom.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.nuriweb.mybom.model.dao.inf.IReplyDAO;
import com.nuriweb.mybom.model.vo.ReplyVO;

@Repository
public class ReplyMySqlDAOImpl implements IReplyDAO {

	
	
	private class ReplyRowMapper implements RowMapper<ReplyVO>{

		@Override
		public ReplyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return new ReplyVO(rs.getInt("id"),rs.getInt("bd_id"),rs.getInt("mb_id"),rs.getString("user_name"),rs.getString("content"),rs.getTimestamp("created_at"));
		}
	}
	
	
	private ReplyRowMapper rpRowMapper;
	
	public ReplyMySqlDAOImpl() {
	
		this.rpRowMapper = new ReplyRowMapper();
	}
	
	
	@Autowired
	private JdbcTemplate jtem;
	
	
	//게시판 (누리봄 전하는 글,자유 게시판) 에서 회원이 덧글을 작성할 수 있다.
	//게시판(QnA)에 어드민 관리자가 어드민페이지에서 덧글을 작성할 수 있다. (질문)
	@Override
	public boolean insertNewReply(String table, ReplyVO rp) {
		
		String sql_insert_new_Reply = "insert into "+table+" value(null,?,?,?,?,now())";
		//insert into free_reply value(null,1,1,"힙합처돌이","재밌네요!!",now());
		
		try {
			
			int r = jtem.update(sql_insert_new_Reply,rp.getBdId(),rp.getMbId(),rp.getUserName(),rp.getContent());
			return r == 1;
			
		}catch (DataAccessException dea) {
			
			System.out.println("reply dao 덧글 작성 실패: "+ rp);
			return false;
			
		}
	}

	@Override
	public boolean insertNewReply(String table, int bdId, int mbId, String userName, String content) {
		
		String sql_insert_new_Reply = "insert into "+table+" value(null,?,?,?,?,now())";
		
		try {
			
			int r = jtem.update(sql_insert_new_Reply,bdId,mbId,userName,content);
			return r == 1;
			
		}catch (DataAccessException dea) {
		
			System.out.println("reply dao 덧글 작성 실패: "+ bdId +"|"+ mbId+"|"+userName+"|"+content);
			return false;
		}
	}
	
	

	//게시판 디테일페이지에서 해당 글에 작성된 덧글을 조회할 수 있다.
	@Override
	public List<ReplyVO> selectAllReply(String table, int bdId) {
		
		String sql_select_all_reply_by_bdId = "SELECT * FROM "+table+" where bd_id = ? ";
		
		try {
			
			return jtem.query(sql_select_all_reply_by_bdId,this.rpRowMapper,bdId);
			
		}catch (DataAccessException dea) {
			
			System.out.println("reply 해당 게시글에 달린 댓글 전체조회 실패: "+bdId);
			return null;
		}
		
	}

	
	//수정,삭제를 위한 개별 조회
	@Override
	public ReplyVO selectOneReply(String table, int id) {
	
		String sql_select_one_reply = "SELECT * FROM "+table+" where id = ?";
		try {
			
			return jtem.queryForObject(sql_select_one_reply,this.rpRowMapper,id);
		
		}catch (EmptyResultDataAccessException dea) {
		
			System.out.println("reply dao: 0개 덧글 조회");
			return null;	
			
		}catch (DataAccessException dae) {
		
			System.out.println("reply dao: 해당 댓글 조회 실패: " + id);
			return null;
		}
	}
	
	
	//질문용
		@Override
		public ReplyVO selectOneReplyByQnA(int bdId) {
			
			
			String sql_select_reply_by_bdId ="select * from qna_reply where bd_id = ?";
			
			try {
				
				return jtem.queryForObject(sql_select_reply_by_bdId, this.rpRowMapper,bdId);
				
			} catch (DataAccessException dea) {
				
				System.out.println("reply dao: 해당 질문 덧글  조회 실패: "+bdId);
				return null;
			}	
			
			
		}
	
	
	//특정 회원의 덧글을 조회할 수 있다.
	@Override
	public List<ReplyVO> selectAllReplyByUser(String table, int mbId) {
		
		String sql_select_all_reply_by_user = "SELECT * FROM "+table+" where mb_id = ?";
		
		try {
			return jtem.query(sql_select_all_reply_by_user,BeanPropertyRowMapper.newInstance(ReplyVO.class),mbId);
			
		}catch (DataAccessException dea) {

			System.out.println("reply dao: 해당 회원 댓글 조회 실패: " + mbId);
			return null;
			
		}
	}

	@Override
	public List<ReplyVO> selectAllReplyByUser(String table, String UserName) {
		
		String sql_select_all_reply_by_userName = "SELECT * FROM "+table+" where user_name = ?";
		
		try {
			return jtem.query(sql_select_all_reply_by_userName,BeanPropertyRowMapper.newInstance(ReplyVO.class),UserName);
			
		}catch (DataAccessException dea) {

			System.out.println("reply dao: 해당 회원 댓글 조회 실패: " + UserName);
			return null;
			
		}
	}

	
	//해당 덧글을 작성한 회원이라면 덧글을 수정할 수 있다.
	@Override
	public boolean updateOneReply(String table, ReplyVO rp) {
		
		String sql_update_one_reply ="update "+table+" set user_name = ? , content= ? where id= ?";
		
		try {
			
			int r = jtem.update(sql_update_one_reply,rp.getUserName(),rp.getContent(),rp.getId());
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("reply dao 댓글 수정 실패: "+ rp);
			return false;
		}
		
		
	}

	@Override
	public boolean updateOneReply(String table, int id, String content) {
		
		String sql_update_one_reply ="update "+table+" set content= ? where id= ?";
	
		try {
			
			int r = jtem.update(sql_update_one_reply,content,id);
			return r ==1;
			
		} catch (DataAccessException dae) {

			System.out.println("reply dao 댓글 수정 실패: "+ id);
			return false;
		}

	}

	//회원의 닉네임이 수정될 시 같이 수정되야하므로..
	@Override
	public boolean updateAllReplyByUser(String table, int mbId, String userName) {
	
		String sql_update_all_reply_by_userName ="update "+table+" set user_name=? where mb_id= ?";
		
		try {
			
			int r = jtem.update(sql_update_all_reply_by_userName,userName,mbId);
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("reply dao 글쓴이 닉네임 수정 실패!: "+ mbId);
			return false;
		}
		
	}
	
	
	//해당 덧글을 작성한 회원이라면 덧글을 삭제할 수 있다.
	@Override
	public boolean deleteOneReply(String table, int id) {
		
		String sql_delete_one_reply = "delete from "+table+" where id = ?";
		
		try {
			
			int r = jtem.update(sql_delete_one_reply,id);
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("reply dao 댓글 삭제 실패: " + id);
			return false;
		}
		
		
	}
	
	@Override
	public boolean deleteAllReplyByBoard(String table, int bdId) {

		String sql_delete_all_reply_by_board = "delete from "+table+" where bd_id = ?";
		
		try {
			
			int r = jtem.update(sql_delete_all_reply_by_board,bdId);
			return r ==1;
			
		} catch (DataAccessException dae) {
		
			System.out.println("reply dao 게시글 댓글 전체 삭제 실패: "+bdId);
			return false;
			
		}
		
		
	}

	

	
	
	@Override
	public List<ReplyVO> searchAllReply(String table, int mbId, String content) {
		// TODO Auto-generated method stub
		return null;
	}
	

	//리턴키 
	@Override
	public int insertNewReplyReturnKey(String table, ReplyVO rp) {
		
		String sql_insert_one_board ="insert into "+table+" value(null,?,?,?,?,now())";
		KeyHolder kh = new GeneratedKeyHolder();
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement psmt = con.prepareStatement(sql_insert_one_board,new String[] {"id"});
				psmt.setInt(1, rp.getBdId());
				psmt.setInt(2, rp.getMbId());
				psmt.setString(3, rp.getUserName());
				psmt.setString(4, rp.getContent());
				return psmt;
			}
		};
		
		jtem.update(psc,kh);
		Number pk = kh.getKey();
		return pk.intValue();
	}

	@Override
	public int checkReplyCountForBoard(String table, int bdId) {
		
		String sql_check_reply_count_for_board = "SELECT count(*) from "+table+" where bd_id = ?";

		try {
			System.out.println(">> 해당 테이블:"+table+" 게시글 아이디 덧글 개수 조회 성공! "+bdId);
			return jtem.queryForObject(sql_check_reply_count_for_board,Integer.class,bdId);

		} catch (DataAccessException dae) {
			
			System.out.println(">> 해당 테이블:"+table+" 게시글 아이디 덧글 개수 조회 실패 "+bdId);
			return -1;
		}
	}
}
