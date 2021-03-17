package com.nuriweb.mybom.model.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.IReviewDAO;
import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.model.vo.ReviewVO;

@Repository
public class ReviewMySqlDAOImpl implements IReviewDAO {
	// review_id  			<<pk>> AI  NN 		리뷰 아이디번호 
	// review_member_id		 <<fk>> 			회원 아이디
	// review_center_id 	<<fk>>  			상담소 아이디
	// review_reserve_id 	<<FK>> 				예약 아이디
	// review_rate			 default 5 평점 1~5
	// review_user_name 	 (varchar 64) NN	글쓴이
	// review_content  		 (varchar 300)NN	내용
	// review_created_at	 default now() 		작성날짜 
	
	private static String SQL_INSERT_REVIEW = 
		"insert into review (review_id,review_member_id,review_center_id,review_reserve_id,review_rate,review_user_name,"
		+ " review_content) values(null,?,?,?,?,?,?)";
	private static String SQL_SELECT_CENTER_ALL_REVIEW =
		"select * from review where review_center_id = ?";
	private static String SQL_SELECT_MEMBER_ALL_REVIEW =
			"select * from review where review_member_id = ?";
	private static String SQL_SELECT_ONE_REVIEW_REVIEW_ID = 
		"select * from review where review_id = ?";
	private static String SQL_SELECT_ALL_REVIEW = 
			"select * from review order by review_created_at";
	private static String SQL_UPDATE_REVIEW = 
	"update review set review_rate = ?, review_user_name = ?,"
	+ " review_content = ? where review_id = ?";
	private static String SQL_UPDATE_REVIEW2 = 
			"update review set review_rate = ?, review_created_at = now(),"
					+ " review_content = ? where review_id = ?";
	private static String SQL_UPDATE_REVIEW3 = 
			"update review set  review_user_name = ? where review_id = ?";
	private static String SQL_DELETE_ONE_REVIEW_BY_ID = 
		"delete from review  where review_id = ?";
	private static String SQL_DELETE_ONE_REVIEW_BY_RS_ID = 
			"delete from review  where review_reserve_id = ?";
	private static String SQL_SELECT_ALL_REVIEW_PG
	= "select * from review order by review_created_at desc limit ?, ?"; 
	private static String SQL_DELETE_ONE_REVIEW_BY_ID_PG = 
			"select * from review where review_member_id = ? order by review_created_at desc limit ?, ?";
	private static String SQL_SELECT_CENTER_REVIEW_PG
	= "select * from review where review_center_id = ? order by review_created_at desc limit ?, ?";
	
	public static String SQL_REVIEW_CHECK_ALL_COUNT = 
	"select count(review_id) from review";
	public static String SQL_REVIEW_CHECK_MEMBER_COUNT = 
			"select count(review_id) from review where review_member_id = ? ";
	
	public static String SQL_REVIEW_CHECK_CENTER_COUNT = 
			"select count(review_id) from review where review_center_id = ?";
	
@Autowired
private JdbcTemplate jtem;

@Override
public int checkAllReviewCount() {
	return jtem.queryForObject(SQL_REVIEW_CHECK_ALL_COUNT,Integer.class);
}

@Override
public int checkAllMemberCount(int mbId) {
	return jtem.queryForObject(SQL_REVIEW_CHECK_MEMBER_COUNT,Integer.class,mbId);
}

@Override
public int checkCenterReviewCount(int ctId) {
	return jtem.queryForObject(SQL_REVIEW_CHECK_CENTER_COUNT,Integer.class,ctId);
}

	@Override
public List<ReviewVO> selectAllReviewPage(int ofset, int limit) {
		try {
	return jtem.query(SQL_SELECT_ALL_REVIEW_PG, BeanPropertyRowMapper.
			newInstance(ReviewVO.class),ofset,limit);
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return null;
		}
}
	
@Override
public List<ReviewVO> selectCenterReviewPage(int ctId, int ofset, int limit) {
	try {
		 List<ReviewVO> rs = jtem.query(SQL_SELECT_CENTER_REVIEW_PG, BeanPropertyRowMapper.
				newInstance(ReviewVO.class),ctId,ofset,limit);
		 System.out.println(rs.size());
	return jtem.query(SQL_SELECT_CENTER_REVIEW_PG, BeanPropertyRowMapper.
				newInstance(ReviewVO.class),ctId,ofset,limit);
	} catch(EmptyResultDataAccessException e) {
		System.out.println("empty - ReviewMySqlDAOImpl ");
		return null;
	} catch(DataAccessException e) {
		System.out.println("데이터에러-ReviewMySqlDAOImpl ");
		return null;
	}
}

	@Override
	public List<ReviewVO> selectAllReview() {
		try {
			System.out.println("\n\n안햇냐?\n\n");
			return jtem.query(SQL_SELECT_ALL_REVIEW,
					BeanPropertyRowMapper.
					newInstance(ReviewVO.class));
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return null;
		}
	}

	@Override
	public List<ReviewVO> selectAllReviewByCenter(int ctId) {
		try {
			return jtem.query(SQL_SELECT_CENTER_ALL_REVIEW,
				BeanPropertyRowMapper.
				newInstance(ReviewVO.class),ctId);
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return null;
		}
	}

	@Override
	public ReviewVO selectOneReview(int id) {
		try {
			
			ReviewVO rv = jtem.queryForObject(SQL_SELECT_ONE_REVIEW_REVIEW_ID,
					BeanPropertyRowMapper
					.newInstance(ReviewVO.class), id);
					
			
			return rv;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return null;
		}
	}
	
//	ReviewVO(int reviewMemberId, int reviewCenterId, int reviewReserveId,
//				int reviewRate, String reviewUserName,String reviewContent 

	@Override
	public boolean insertOneReview(ReviewVO rv) {
		try {
			int r = this.jtem.update(SQL_UPDATE_REVIEW,
				rv.getReviewMemberId(),rv.getReviewCenterId(),rv.getReviewReserveId(),
				rv.getReviewRate(),rv.getReviewUserName(),rv.getReviewContent());
			return r==1;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}

	@Override
	public boolean insertOneReview(int mbId, int ctId,int rsId, int rate, String userName, String content) {
		try {
			int r = this.jtem.update(SQL_INSERT_REVIEW,
					mbId,ctId,rsId,rate,userName,content);
			
			return r==1;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}
/*	private static String SQL_UPDATE_REVIEW = 
	"update review set review_rate = ?, review_user_name = ?,"
	+ " review_content = ? where review_id = ?";
	*/
	@Override
	public boolean updateOneReview(ReviewVO rv) {
		try {
			int r = this.jtem.update(SQL_UPDATE_REVIEW,
					rv.getReviewRate(),rv.getReviewUserName(),rv.getReviewContent(),rv.getReviewId());
					return r==1;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}

	@Override
	public boolean updateOneReview(int id, int rate, String content) {
		try {
			int r = this.jtem.update(SQL_UPDATE_REVIEW2,
					rate,content,id);
					return r==1;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}

	@Override
	public boolean updateOneReviewByUserName(int mbId, String userName) {
		try {
			int r = this.jtem.update(SQL_UPDATE_REVIEW3,
					userName,mbId);
					return r==1;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}

	@Override
	public boolean deleteOneReview(int id) {
		try {
			int r = this.jtem.update(SQL_DELETE_ONE_REVIEW_BY_ID,id);
			return r == 1;// ? true: false;	
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}
	
	@Override
	public boolean deleteOneReviewRS(int rsId) {
		try {
			int r = this.jtem.update(SQL_DELETE_ONE_REVIEW_BY_RS_ID,rsId);
			return r == 1;// ? true: false;	
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return false;
		}
	}
	

	@Override
	public List<ReviewVO> getListByMember(int mbId) {
		try {
			return jtem.query(SQL_SELECT_MEMBER_ALL_REVIEW,
				BeanPropertyRowMapper.
				newInstance(ReviewVO.class),mbId);
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return null;
		}
	
	}
	@Override
	public List<ReviewVO> getListByMemberPage(int mbId, int ofset, int limit) {
		try {
			return jtem.query(SQL_DELETE_ONE_REVIEW_BY_ID_PG,
				BeanPropertyRowMapper.
				newInstance(ReviewVO.class),mbId, ofset, limit);
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty - ReviewMySqlDAOImpl ");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReviewMySqlDAOImpl ");
			return null;
		}
	}
	
	
}
