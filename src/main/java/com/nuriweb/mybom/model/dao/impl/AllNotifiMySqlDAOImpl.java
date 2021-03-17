package com.nuriweb.mybom.model.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.IAllNotifiDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.ReserveVO;

@Repository
public class AllNotifiMySqlDAOImpl implements IAllNotifiDAO {

	

				private static String SQL_INSERT_AllNotifi = 
					"insert into AllNotifi values(?,?,?,?,?,?,?,?,?,?,?,?)";
				private static String SQL_UPDATE_AllNotifi = 
					"update into AllNotifi values(?,?,?,?,?,?,?,?,?,?,?,?)";
					
				
				//writer 닉네임
				private static String SQL_INSERT_NewFreeBoardNotifi =
				"insert into AllNotifi(mb_id,nickname,category,link)"
				+ " values(?,?,?,?)";
				
				private static String SQL_INSERT_NewQuestionNotifi 
				= "insert into AllNotifi(mb_id,nickname,category,link) values(?,?,?,?)";
				 
				private static String SQL_INSERT_NewPostNotifi 
				= "insert into AllNotifi(mb_id,nickname,category,link) "
						+ "values(?,?,?,?)";
				private static String SQL_INSERT_NewPrivateNotifi 
				= "insert into AllNotifi(mb_id,nickname,category) values(?,?,?)";
				private static String SQL_INSERT_NewMemberNotifi 
				= "insert into AllNotifi(mb_id,nickname,category) values(?,?,?)";

				private static String SQL_INSERT_ReserveNotifi
				= "insert into AllNotifi(mb_id,name,reserve_day,category,link)"
						+ " values(?,?,?,?,?)";
				
				private static String SQL_INSERT_ReviewPost
				= "insert into AllNotifi(mb_id,name,nickname,category,link) "
						+ "values(?,?,?,?,?)";
				private static String SQL_INSERT_LikeCenter
				= "insert into AllNotifi(mb_id,name,nickname,category,link) "
						+ "values(?,?,?,?,?)";
				
				private static String SQL_SELECT_ALL_AllNotifi
					= "select * from AllNotifi order by timestamp_at desc";
				private static String SQL_SELECT_AllNotifiWithNickname = 
						"select * from AllNotifi where nickname = ? order by timestamp_at desc";
				private static String SQL_SELECT_AllNotifiWithmbId = 
						"select * from AllNotifi where mb_id = ? order by timestamp_at desc limit 0,5";
				
		
				
				private static String SQL_SELECT_AllNotifiWithmbId_PG = 
						"select * from AllNotifi where mb_id = ? order by timestamp_at desc"	
									+ " limit ?, ?";
				private static String SQL_DELETE_ONE_Notifi = 
						"delete from AllNotifi where AllNotifi_id = ?";
				private static String SQL_DELTET_ONE_NotifiByNo =
						"delete from AllNotifi where AllNotifi_no = ?";
				
				public static String SQL_SELECT_ALLNOTI_COUNT = 
						"select count(*) from AllNotifi where mb_id = ? ";
				
				private final int commentNo = 0;
				private final int qnaNo =1;
				private final int postNo =2;
				private final int privateNo =3;
				private final int reserveNo =4;
				private final int reviewNo =5;
				private final int likeNo =6;
				
				
	
	@Autowired
	JdbcTemplate jtem;
			
				
	@Override
	public boolean insertNewNotifi(AllNotifiVO no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewNotifi(int no, int bdId, int commentId, int questionId, int reviewId, String name,
			String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	//int no, string array들뻇음
	@Override
	public boolean insertNewFreeBoardNotifi(int mbId, String nickname, int category,String link) {
		try {
			int r = this.jtem.update(SQL_INSERT_NewFreeBoardNotifi,mbId,nickname,category,link);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on SQL_INSERT_NewFreeBoardNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on SQL_INSERT_NewFreeBoardNotifi");
				return false;
			}
	}

	@Override
	public boolean insertNewQuestionNotifi(int mbId,String nickname,
			int category,String link) {
		try {
			int r = this.jtem.update(SQL_INSERT_NewQuestionNotifi,mbId,nickname,category,link);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on SQL_INSERT_NewQuestionNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on SQL_INSERT_NewQuestionNotifi");
				return false;
			}
	}

	@Override
	public boolean insertNewPostNotifi(int mbId, String nickname,int category,String link) {
		try {
			int r = this.jtem.update(SQL_INSERT_NewPostNotifi,mbId,nickname,category,link);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on SQL_INSERT_NewPostNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on SQL_INSERT_NewPostNotifi");
				return false;
			}
	}

	@Override
	public boolean insertNewPrivateNotifi(int mbId, String nickname, int category) {
		try {
			int r = this.jtem.update(SQL_INSERT_NewPrivateNotifi,mbId,nickname,category);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on SQL_INSERT_NewPrivateNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on SQL_INSERT_NewPrivateNotifi");
				return false;
			}
	}

	
	
	@Override
	public boolean insertNewMemberNotifi(int mbId, String nickname, int category) {
		try {
			int r = this.jtem.update(SQL_INSERT_NewMemberNotifi,mbId,nickname,category);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on SQL_INSERT_NewPrivateNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on SQL_INSERT_NewPrivateNotifi");
				return false;
			}
	}



	@Override
	public boolean insertNewReserveNotifi(int mbId,String name,Timestamp reserveDay,int category,String link) {
		try {
			int r = this.jtem.update(SQL_INSERT_ReserveNotifi,mbId,name,reserveDay,category,link);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on insertNewReserveNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on insertNewReserveNotifi");
				return false;
			}
	}

	

	@Override
	public boolean insertNewReviewPostNotifi(int mbId, String name, String nickname, int category,
			String link) {
		
		try {
			int r = this.jtem.update(SQL_INSERT_ReviewPost,mbId,name,nickname,category,link);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on insertNewReserveNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on insertNewReserveNotifi");
				return false;
			}
	}
		
	

	@Override
	public boolean insertLikeCenterNotifi(int mbId, String name, String nickname, int category, String link) {
		try {
			int r = this.jtem.update(SQL_INSERT_LikeCenter,mbId,name,nickname,category,link);
			
			return r ==1;
			
			}catch(EmptyResultDataAccessException e){
				
				System.out.println("EmptyResultDataAccessException on insertNewReserveNotifi");
				return false;
				
			}catch(DataAccessException e){
				
				System.out.println("DataAccessException on insertNewReserveNotifi");
				return false;
			}
	}

	@Override
	public boolean updateNoti(AllNotifiVO no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOneNoti(int no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AllNotifiVO> selectAllNotifi() {
		
		try {
			return
			jtem.query(SQL_SELECT_ALL_AllNotifi, BeanPropertyRowMapper.newInstance(AllNotifiVO.class));
			
			
		}catch(EmptyResultDataAccessException e) {
			System.out.println("Empty error on SQL_SELECT_ALL_AllNotifi ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("DataAccessException on SQL_SELECT_ALL_AllNotifi");
			return null;
		}
	
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiWithName(String nickname) {
		try {
			return
			jtem.query(SQL_SELECT_AllNotifiWithNickname, BeanPropertyRowMapper.newInstance(AllNotifiVO.class),nickname);
			
			
		}catch(EmptyResultDataAccessException e) {
			System.out.println("Empty error on SQL_SELECT_AllNotifiByNickname ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("DataAccessException on SQL_SELECT_AllNotifiByNickname");
			return null;
		}
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiByCategory(int category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AllNotifiVO> selectAllNotifiWithMbId(int mbId) {
		try {
			return
			jtem.query(SQL_SELECT_AllNotifiWithmbId, BeanPropertyRowMapper.newInstance(AllNotifiVO.class),mbId);
			
			
		}catch(EmptyResultDataAccessException e) {
			System.out.println("Empty error on SQL_SELECT_AllNotifiBymbId ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("DataAccessException on SQL_SELECT_AllNotifiBymbId");
			return null;
		}
	}
	
	@Override
	public List<AllNotifiVO> selectAllNotifiWithMbId_PG(int mbId, int offset, int limit) {
		try {
			return
			jtem.query(SQL_SELECT_AllNotifiWithmbId_PG, BeanPropertyRowMapper.newInstance(AllNotifiVO.class),mbId,offset,limit);
			
			
		}catch(EmptyResultDataAccessException e) {
			System.out.println("Empty error on SQL_SELECT_AllNotifiBymbId_PG ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("DataAccessException on SQL_SELECT_AllNotifiBymbId_PG");
			return null;
		}

}

	@Override
	public int checkAllNotifiCount(int mbId) {
		// TODO Auto-generated method stub
		return jtem.queryForObject(SQL_SELECT_ALLNOTI_COUNT,Integer.class,mbId);
	}
	
}
