package com.nuriweb.mybom.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.IQuestionDAO;
import com.nuriweb.mybom.model.vo.QuestionVO;


@Repository
public class QuestionMySqlDAOImpl implements IQuestionDAO {

	
	
	private class QuestionRowMapper implements RowMapper<QuestionVO>{

		@Override
		public QuestionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			
			return new QuestionVO(rs.getInt("id"),rs.getInt("mb_id"), rs.getString("writer"), rs.getString("title"),rs.getString("content"),rs.getString("password"),rs.getInt("category"),rs.getInt("secret")==1?true:false,rs.getInt("reply")==1?true:false,rs.getTimestamp("created_at"));
		}
	}//RowMapper
	
	private QuestionRowMapper qnaRowMapper;
	
	public QuestionMySqlDAOImpl() {
		this.qnaRowMapper = new QuestionRowMapper();
	}
	
	
	//sql
	private static final String SQL_SELECT_ALL_QUESTION_PG ="SELECT * FROM question order by created_at desc limit ?,?";
	private static final String SQL_SELECT_ALL_QUESTION_BY_MBID_PG = "SELECT * FROM question where mb_id =? order by created_at desc limit ?,?";
	private static final String SQL_SELECT_ALL_QUESTION_BY_WRITER = "SELECT * FROM question where writer = ? order by created_at desc";
	private static final String SQL_SELECT_ALL_WAITING_QUESTION = "SELECT * FROM question where reply = 1 order by created_at desc";
	private static final String SQL_SELECT_ALL_WAITING_QUESTION_PG = "SELECT * FROM question where reply = 1 order by created_at desc limit ?,?";

	
	private static final String SQL_SELECT_ONE_QUESTION_BY_ID = "SELECT * FROM question where id = ?";
	private static final String SQL_INSERT_ONE_QUESTION = "insert into question value(null,?,?,?,?,?,?,?,1,now())";
	private static final String SQL_UPDATE_ONE_QUESTION = "update question set title=?, content=?,password=?,category=?,secret =? where id= ?";
	private static final String SQL_UPDATE_ALL_QUESTION_BY_USERNAME = "update question set writer=? where mb_id= ?";
	private static final String SQL_UPDATE_ONE_QUESTION_ANSWER ="update question set reply = 2 where id= ?";
	private static final String SQL_DELETE_ONE_QUESTION = "delete from question where id = ?";
	private static final String SQL_SEARCH_FOR_TITLE ="select * from question where title like ?";
	private static final String SQL_SEARCH_FOR_TITLE_BY_MEMBER = "select * from question where title like ? and mb_id = ?";
	private static final String SQL_CHECK_ALL_QNA_COUNT = "select count(id) from question";
	private static final String SQL_CHECK_ALL_WAITING_QNA_COUNT = "select count(id) from question where reply =1";
	private static final String SQL_CHECK_ALL_QNA_COUNT_MEMBER = "select count(id) from question where mb_id =?";
	

	@Autowired
	private JdbcTemplate jtem;
	
	
	
	@Override
	public List<QuestionVO> selectAllQuestion(int offset,int limit) {
	
		
		try {
			
			return jtem.query(SQL_SELECT_ALL_QUESTION_PG,this.qnaRowMapper,offset,limit);
			
			
		} catch (DataAccessException dae) {
			
			System.out.println("Question ???????????? ??????");
			return null;
		}
		
	}

	

	@Override
	public List<QuestionVO> selectAllWaitingQuestion(int offset, int limit) {
		
		try {
		
			return jtem.query(SQL_SELECT_ALL_WAITING_QUESTION_PG,this.qnaRowMapper,offset,limit);
			
			
		} catch (DataAccessException dae) {
		
			System.out.println("Question ????????? ???????????? ???????????? ??????");
			return null;
		
		}
	}

	
	
	
	
	@Override
	public List<QuestionVO> selectAllQuestionByUser(int mbId,int offset,int limit) {
		

		try {
			
			return jtem.query(SQL_SELECT_ALL_QUESTION_BY_MBID_PG,this.qnaRowMapper,mbId,offset,limit);
			
			
		} catch (DataAccessException dae) {
		
			System.out.println("Question ?????? ?????? ???????????? ??????: " + mbId);
			return null;
			
		}

	}

	@Override
	public List<QuestionVO> selectAllQuestionByUser(String userName) {
	
		try {
			
			return jtem.query(SQL_SELECT_ALL_QUESTION_BY_WRITER,this.qnaRowMapper,userName);
			
		} catch (DataAccessException dae) {
	
			System.out.println("Question ?????? ?????? ???????????? ??????: " + userName);
			return null;
		}
	}

	@Override
	public List<QuestionVO> selectAllWaitingQuestion() {
		
		try {
			
			return jtem.query(SQL_SELECT_ALL_WAITING_QUESTION,this.qnaRowMapper);
			
		} catch (DataAccessException dae) {
		
			System.out.println("Question ?????? ?????? ?????? ???????????? ??????");
			return null;
			
			
		}
		
		
	}

	@Override
	public QuestionVO selectOneQuestion(int id) {
	
		try {
			
			return jtem.queryForObject(SQL_SELECT_ONE_QUESTION_BY_ID,this.qnaRowMapper,id);
			
			
		}catch (EmptyResultDataAccessException dea) {
		
			System.out.println("Question dao: 0??? ?????? ??????");
			return null;	
			
		} catch (DataAccessException dea) {
			
			System.out.println("Question ?????? ?????? ??????: "+id);
			return null;
			
		}
		
	}

	
//	4.QnA ?????? ????????? ???????????? ????????? ????????? ??? ??????.
	
	@Override
	public boolean insertNewQuestion(QuestionVO qna) {
		
		try {
			
			int r = jtem.update(SQL_INSERT_ONE_QUESTION,qna.getMbId(),qna.getWriter(),qna.getTitle(),qna.getContent(),qna.getPassword(),qna.getCategory(),qna.isSecret()?1:2);
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("Question ?????? ?????? ??????: "+qna);
			return false;
			
		}
	
	}

	@Override
	public boolean insertNewQuestion(int mbId, String userName, String title, String content, String password,
			int category, boolean secret) {
		
		try {
			
			int r = jtem.update(SQL_INSERT_ONE_QUESTION,mbId,userName,title,content,password,category,secret?1:2);
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("Question ?????? ?????? ??????: " + userName+ "|" + title+ "|" + content+ "|" + password+ "|" + category);
			return false;
			
		}
	}

	
	
	@Override
	public boolean updateOneQuestion(QuestionVO qna) {
		
		try {
			
			int r = jtem.update(SQL_UPDATE_ONE_QUESTION,qna.getTitle(),qna.getContent(),qna.getPassword(),qna.getCategory(),qna.isSecret()?1:2,qna.getId());
			return r == 1;
			
		} catch (DataAccessException dae) {
		
			System.out.println("Question dao ???????????? ??????: "+qna);
			return false;
		}
		
	
	}

	@Override
	public boolean updateOneQuestion(int id, String title, String content, String password, int category,
			boolean secret) {
		
		try {
				int r = jtem.update(SQL_UPDATE_ONE_QUESTION,title,content,password,category,secret?1:2,id);
				return r == 1;
				
			} catch (DataAccessException dae) {
			
				System.out.println("Question dao ???????????? ??????: "+ id);
				return false;
			}
		
	}

	@Override
	public boolean updateAllQuestionByUser(int mbId, String userName) {
		
		
		try {
			
			int r = jtem.update(SQL_UPDATE_ALL_QUESTION_BY_USERNAME,userName,mbId);
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("Question dao ????????? ?????? ????????? ???????????? ??????: "+ mbId);
			return false;
		}
	}

	@Override
	public boolean updateOneQuestionAnswer(int id) {
	
		try {
			
			int r = jtem.update(SQL_UPDATE_ONE_QUESTION_ANSWER,id);
			return r == 1;
			
		} catch (DataAccessException dae) {
			
			System.out.println("Question ????????? ?????? ?????? ?????? ??????: " + id);
			return false;
		}
		
	}

	@Override
	public boolean deleteOneQuestion(int id) {
	
		try {
			
			int r = jtem.update(SQL_DELETE_ONE_QUESTION,id);
			return r == 1;
			
		} catch (DataAccessException dae) {
		
			System.out.println("question dao ??? ?????? ??????: " + id);
			return false;
		}
		
	
	}

	@Override
	public List<QuestionVO> searchAllQuestion(String title) {
		// TODO mysql ???????????? ????????? ????????????
		//select * from members where name like '%???%'=> ???????????? proc?????? trim ????????? ????????????
		return null;
	}

	@Override
	public List<QuestionVO> searchAllQuestion(int mbId, String title) {
		// TODO mysql ???????????? ????????? ???????????? => ???????????? proc?????? trim ????????? ???????????? 
		//select * from question where title like '%????????????%' and mb_id = 2;
		return null;
	}

	@Override
	public int checkAllQnACount() {
		
		try {
		return jtem.queryForObject(SQL_CHECK_ALL_QNA_COUNT, Integer.class);
		
		}catch (EmptyResultDataAccessException e) {
		
			System.out.println(">> QnA ????????? 0???..");
			return 0;
		}catch (DataAccessException e) {
		
			System.out.println(">> QnA ????????? ????????? sql ??????..");
			return 0;
		}
	}

	
	@Override
	public int checkWaitingQnACount() {
	
		try {
			
			return jtem.queryForObject(SQL_CHECK_ALL_WAITING_QNA_COUNT, Integer.class);
			
		}catch (EmptyResultDataAccessException e) {
			
			System.out.println(">> ????????? ?????? ?????? QnA ????????? 0???..");
			return 0;
		}catch (DataAccessException e) {
		
			System.out.println(">> ????????? ?????? ?????? QnA ????????? ????????? sql ??????..");
			return 0;
		}
		
	}
	
	
	@Override
	public int checkAllQnACountByMember(int mbId) {
	
		try {
			
			return jtem.queryForObject(SQL_CHECK_ALL_QNA_COUNT_MEMBER, Integer.class,mbId);
			
		} catch (EmptyResultDataAccessException e) {
			
			System.out.println(">> ?????? ????????? 0??? ??????..");
			return 0;
		}catch (DataAccessException dae) {
		
			System.out.println(">> ?????? ????????? ????????? sql??? ??????!");
			return 0;
		}
	
	}
}
