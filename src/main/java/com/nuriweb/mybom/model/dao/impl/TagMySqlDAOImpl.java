package com.nuriweb.mybom.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.ITagDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.TagVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;
@Repository
public class TagMySqlDAOImpl implements ITagDAO {

	@Autowired
	private JdbcTemplate jtem;
	
	private static final String SQL_SELECT_ALL_TAGS = "select * from tags order by registered_at desc"; //최신등록된순
	private static final String SQL_GET_TAGS_COUNT_OF_CENTER = "select count(*) from tags where center_id = ?";
	private static final String SQL_SELECT_ALL_TAGS_OF_CENTER = "select * from tags where center_id = ?";
	private static final String SQL_INSERT_TAG = "insert into tags values(null,?,?,now())";
	private static final String SQL_DELETE_TAG  = "delete from tags where id = ?";
	private static final String SQL_UPDATE_TAG = "update tags set tag = ? where id = ?";
	private static final String SQL_SELECT_TAGSTBL_BY_ONE_TAG = "select * from tags where tag = '?'"; 
	
	@Override //단순 태그리스트 조회
	public List<TagVO> selectAllTagList() {
		return this.jtem.query(SQL_SELECT_ALL_TAGS, BeanPropertyRowMapper.newInstance(TagVO.class));
	}

	@Override //한개의 상담소가 지닌 태그리스트 조회
	public List<TagVO> selectAllTagListOfOneCenter(CenterVO ct) {
		return jtem.query(SQL_SELECT_ALL_TAGS_OF_CENTER, BeanPropertyRowMapper.newInstance(TagVO.class), ct.getId());
	}

	@Override  //한개의 상담소가 지닌 태그리스트 조회
	public List<TagVO> selectAllTagListOfOneCenter(int ctId) {
		return jtem.query(SQL_SELECT_ALL_TAGS_OF_CENTER, BeanPropertyRowMapper.newInstance(TagVO.class), ctId);
	}
	
	@Override	//해당 태그를 가지고 있는 태그테이블 리스트로 조회 => ctId를 뽑아서 ctList조회하도록 할수 있음(svcImpl..)
	public List<TagVO> selectAllCentersByTag(String tag) {
		return jtem.query(SQL_SELECT_TAGSTBL_BY_ONE_TAG, BeanPropertyRowMapper.newInstance(TagVO.class), tag);
	}
	
	@Override
	public int getTagCountOfCenter(CenterVO ct) {
		return jtem.queryForObject(SQL_GET_TAGS_COUNT_OF_CENTER, Integer.class, ct.getId());
	}
	@Override
	public int getTagCountOfCenter(int ctId) {
		return jtem.queryForObject(SQL_GET_TAGS_COUNT_OF_CENTER, Integer.class, ctId);
	}

	@Override
	public boolean insertTag(String tag, CenterVO ct) {
		int r = jtem.update(SQL_INSERT_TAG, tag, ct.getId());
		return r == 1;
	}
	@Override
	public boolean insertTag(String tag, int ctId) {
		int r = jtem.update(SQL_INSERT_TAG, tag, ctId);
		return r == 1;
	}
	
	@Override
	public boolean updateTagOfCenter(String tag, int tagId) {
		int r = jtem.update(SQL_UPDATE_TAG, tagId);
		return r == 1;
	}

	@Override
	public boolean deleteTagOfCenter(String tag, CenterVO ct) {
		int r = jtem.update(SQL_DELETE_TAG, tag, ct.getId());
		return r == 1;
	}
	@Override
	public boolean deleteTagOfCenter(String tag, int ctId) {
		int r = jtem.update(SQL_DELETE_TAG, tag, ctId);
		return r == 1;
	}

	@Override
	public List<TagVO> selectAllTagListOfOneCenter(CenterRowVO ct) {
		return jtem.query(SQL_SELECT_ALL_TAGS_OF_CENTER, BeanPropertyRowMapper.newInstance(TagVO.class), ct.getsId());
	}

	
	private String SQL_DELETE_ALL_TAGS = "delete from tags where center_id = ?";
	@Override
	public boolean deleteAllTagsOfCenter(int ctId) {
		int r = jtem.update(SQL_DELETE_ALL_TAGS, ctId);
		return r == 1;
	}







}
