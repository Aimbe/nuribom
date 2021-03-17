package com.nuriweb.mybom.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.ILikeDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.LikeVO;
import com.nuriweb.mybom.model.vo.MemberVO;

@Repository("mybatisLikeDao")
public class LikeMySqlMyBatisDAOImpl implements ILikeDAO {

	@Autowired
	private SqlSessionTemplate sstem;
	
	@Override
	public boolean likeAdd(int mbId, int ctId) {
		LikeVO like = new LikeVO(mbId, ctId);
		int r = sstem.insert("ILikeDAO.likeAdd", like);
		return r == 1;
	}

	@Override
	public int likeAddReturnKey(int mbId, int ctId) {
		LikeVO like = new LikeVO(mbId, ctId);
		int r = sstem.insert("ILikeDAO.likeAddReturnKey", like);
		if( r == 1 ) {
			System.out.println("mybatis like 등록성공 pk => " + like.getId()); 
			return like.getId(); //등록된 like키 리턴
		} else {
			return 0;
		}
	}

	@Override
	public boolean likeDelete(int mbId, int ctId) {
		LikeVO like = new LikeVO(mbId, ctId);
		int r = sstem.delete("ILikeDAO.likeDelete", like);
		return r == 1;
		
	}

	@Override
	public LikeVO aleadyExistedLike(int mbId, int ctId) { //null이 아니면 이미 좋아요 한것임
		LikeVO like = new LikeVO(mbId, ctId);
		return sstem.selectOne("ILikeDAO.aleadyExistedLike", like);
	}

	// svc...
	@Override
	public boolean isAleadyLiked(int mbId, int ctId) {
//		return sstem.selectList("ILikeDAO.selectAllLikeList");
		return false;
	}

	@Override
	public int getLikeCountOfOneCenter(int ctId) {	//한 상담소의 좋아요 개수
		return sstem.selectOne("ILikeDAO.getLikeCountOfOneCenter", ctId);
	}

	@Override
	public int getLikeCountOfOneMember(int mbId) { // 멤버가 좋아요한 상담소 개수
		return sstem.selectOne("ILikeDAO.getLikeCountOfOneMember", mbId);
	}

	@Override
	public List<LikeVO> selectAllLikeList() {
		return sstem.selectList("ILikeDAO.selectAllLikeList");
	}

	@Override
	public List<LikeVO> selectAllLikeListOfOneCenter(int ctId) {
		return sstem.selectList("ILikeDAO.selectAllLikeListOfOneCenter", ctId);
	}

	@Override
	public List<LikeVO> selectAllLikeListOfOneMember(int mbId) {
		return sstem.selectList("ILikeDAO.selectAllLikeListOfOneMember", mbId);
	}
	

	@Override
	public List<LikeVO> selectAllLikeListOfOneMemberPG(int mbId, int offset, int limit) {
		RowBounds row = new RowBounds(offset, limit);
		return sstem.selectList("ILikeDAO.selectAllLikeListOfOneMemberPG", mbId, row);
	}



}
