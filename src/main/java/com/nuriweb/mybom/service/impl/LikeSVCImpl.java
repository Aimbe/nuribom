package com.nuriweb.mybom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.dao.inf.ILikeDAO;
import com.nuriweb.mybom.model.dao.inf.IMemberDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.LikeVO;
import com.nuriweb.mybom.model.vo.MemberVO;
import com.nuriweb.mybom.service.inf.ILikeSVC;

@Service
public class LikeSVCImpl implements ILikeSVC {

	@Autowired
	@Qualifier("mybatisLikeDao")
	ILikeDAO lkDao;
	
	@Autowired
	IMemberDAO mbDao;
	
	@Autowired
	ICenterDAO ctDao;
	
	@Override
	public boolean likeAdd(int mbId, int ctId) {
		return lkDao.likeAdd(mbId, ctId);
	}

	
	@Override
	public List<LikeVO> selectAllLikeList() {
		return lkDao.selectAllLikeList();
	}

	@Override
	public List<LikeVO> selectAllLikeListOfOneCenter(int ctId) {
		return lkDao.selectAllLikeListOfOneCenter(ctId);
	}

	@Override
	public List<LikeVO> selectAllLikeListOfOneMember(int mbId) {
		return lkDao.selectAllLikeListOfOneMember(mbId);
	}

//	svc에서만 처리..
	@Override
	public List<MemberVO> selectMemberListWhoLikesCenter(int ctId) {
		List<MemberVO> mbList = new ArrayList<>();
		List<LikeVO> likeList = lkDao.selectAllLikeListOfOneCenter(ctId);	//해당 상담소 아이디로 조회한 좋아요리스트
		for (LikeVO like : likeList) {
			mbList.add(mbDao.selectOneMember(like.getMemberId())); //상담소 좋아요한 멤버아이디로 멤버를 뽑아내서 리스트로 저장
		}
		if( mbList != null ) {
			System.out.println("ctId: " + ctId +"를 좋아요 누른 멤버리스트 조회성공");
			return mbList;
		}
		return null;
	}

	@Override
	public List<CenterVO> selectCenterListLikedByOneMember(int mbId) {
		List<CenterVO> ctList = new ArrayList<>();
		List<LikeVO> likeList = lkDao.selectAllLikeListOfOneMember(mbId); //멤버로 조회한 좋아요리스트
		for (LikeVO like : likeList) {
			ctList.add(ctDao.getOneCenter(like.getCenterId())); //한멤버의 상담소좋아요리스트
		}
		if( ctList != null ) {
			System.out.println("mbId: " + mbId +"회원의 좋아요 누른 센터리스트 조회성공");
			return ctList;
		}
		return null;
	}

	@Override
	public List<CenterVO> selectCenterListLikedByOneMember(MemberVO mb) {
		List<CenterVO> ctList = new ArrayList<>();
		List<LikeVO> likeList = lkDao.selectAllLikeListOfOneMember(mb.getId()); //멤버로 조회한 좋아요리스트
		for (LikeVO like : likeList) {
			ctList.add(ctDao.getOneCenter(like.getCenterId())); //한멤버의 상담소좋아요리스트
		}
		if( ctList != null ) {
			System.out.println("mbId: " + mb.getId() +"회원의 좋아요 누른 센터리스트 조회성공");
			return ctList;
		}
		return null;
	}

	@Override
	public LikeVO aleadyExistedLike(int mbId, int ctId) {
		return lkDao.aleadyExistedLike(mbId, ctId);
	}

	@Override
	public boolean isAleadyLiked(int mbId, int ctId) {
		
		LikeVO like = lkDao.aleadyExistedLike(mbId, ctId);
		if( like != null ) {
//			System.out.println("like svc 이미 좋아요 했음. => 취소가능"); //true일때 다시 누르면 => likeDelete..
			return true;
		} else {
//			System.out.println("like svc 좋아요 안했음. => 좋아요가능"); 
			return false; //결과가 false일대만 좋아요를 누를 수 있다.
		}
	}


	@Override
	public int likeAddReturnKey(int mbId, int ctId) {
		return lkDao.likeAddReturnKey(mbId, ctId);
	}

// svc...
	@Override
	public boolean likeDelete(int mbId, int ctId) {
		return lkDao.likeDelete(mbId, ctId);
	}


	@Override
	public int getLikeCountOfOneCenter(int ctId) {
		return lkDao.getLikeCountOfOneCenter(ctId);
	}


	@Override
	public int getLikeCountOfOneMember(int mbId) {
		return lkDao.getLikeCountOfOneMember(mbId);
	}
	
	// 마이페이지
	@Override
	public int checkMaxpageNumberByUser(int pageSize, int mbId) {
			int totalCtCnt = lkDao.getLikeCountOfOneMember(mbId);
			int maxPage = totalCtCnt / 3 + (totalCtCnt % 3 == 0? 0:1);
			return maxPage;
		
	}
	
	// 마이페이지
	@Override
	public List<LikeVO> selectAllLikeListOfOneMember(int mbId, int page, int pageSize) {
		int offset = (page-1) * pageSize;
		int limit = pageSize;
		
		List<LikeVO> likeListPg = lkDao.selectAllLikeListOfOneMemberPG(mbId, offset, limit);
		return likeListPg;
		
	}


}
