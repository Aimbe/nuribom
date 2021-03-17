package com.nuriweb.mybom.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.ILikeDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.LikeVO;
import com.nuriweb.mybom.model.vo.MemberVO;
@Repository("jdbcTemLikeDao")
public class LikeMySqlDAOImpl implements ILikeDAO {

	@Autowired
	private JdbcTemplate jtem;
	
	private static final String SQL_SELECT_ALL_LIKELIST = "select * from likes order by pushed_at desc";//최근 등록된 순으로
	private static final String SQL_SELECT_ALL_LIKELIST_BY_CENTER = "select * from likes where center_id = ?";
	private static final String SQL_SELECT_ALL_LIKELIST_BY_MEMBER = "select * from likes where member_id = ?";
	private static final String SQL_INSERT_NEW_LIKE = "insert into likes values(null,?,?,now())";
	private static final String SQL_DELETE_LIKE = "delete from likes where member_id = ? and center_id = ?";
	private static final String SQL_GET_LIKE_COUNT_OF_CENTER = "select count(*) from likes where center_id = ?";
	private static final String SQL_GET_LIKE_COUNT_OF_MEMBER = "select count(*) from likes where member_id = ?";
	private static final String SQL_SELECT_LIKE_LIST_MB_PG = "select * from likes where member_id = ? order by pushed_at desc limit ?,?;";
	
	@Override
	public boolean likeAdd(int mbId, int ctId) {
		int r = jtem.update(SQL_INSERT_NEW_LIKE, mbId, ctId);
		return r == 1;
	}

	
	
	@Override
	public boolean likeDelete(int mbId, int ctId) {
		int r = jtem.update(SQL_DELETE_LIKE, mbId, ctId);
		return r == 1;
	}


	@Override //단순 좋아요리스트 조회
	public List<LikeVO> selectAllLikeList() {
		return jtem.query(SQL_SELECT_ALL_LIKELIST, BeanPropertyRowMapper.newInstance(LikeVO.class));
	}

	@Override // 한개의 상담소가 지닌 좋아요리스트 조회
	public List<LikeVO> selectAllLikeListOfOneCenter(int ctId) {
		return jtem.query(SQL_SELECT_ALL_LIKELIST_BY_CENTER, BeanPropertyRowMapper.newInstance(LikeVO.class), ctId);
	}

	@Override // 한명의 멤버가 지닌 좋아요리스트 조회
	public List<LikeVO> selectAllLikeListOfOneMember(int mbId) {
		return jtem.query(SQL_SELECT_ALL_LIKELIST_BY_MEMBER, BeanPropertyRowMapper.newInstance(LikeVO.class), mbId);
	}


	@Override
	public int getLikeCountOfOneCenter(int ctId) {
		return jtem.queryForObject(SQL_GET_LIKE_COUNT_OF_CENTER, Integer.class, ctId);
	}

	

	@Override
	public int getLikeCountOfOneMember(int mbId) {
		return jtem.queryForObject(SQL_GET_LIKE_COUNT_OF_MEMBER, Integer.class, mbId);
	}


	@Override
	public boolean isAleadyLiked(int mbId, int ctId) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public int likeAddReturnKey(int mbId, int ctId) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public LikeVO aleadyExistedLike(int mbId, int ctId) {
		// TODO Auto-generated method stub
		return null;
	}


	// 마이페이지
	@Override
	public List<LikeVO> selectAllLikeListOfOneMemberPG(int mbId, int offset, int limit) {
		return jtem.query(SQL_SELECT_LIKE_LIST_MB_PG, BeanPropertyRowMapper.newInstance(LikeVO.class), mbId, offset, limit);
	}
	
	

}
