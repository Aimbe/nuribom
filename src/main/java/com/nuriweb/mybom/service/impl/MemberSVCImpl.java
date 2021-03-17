package com.nuriweb.mybom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.nuriweb.mybom.MyCode;
import com.nuriweb.mybom.model.dao.inf.IMemberDAO;
import com.nuriweb.mybom.model.vo.MemberVO;
import com.nuriweb.mybom.service.inf.IMemberSVC;

 
@Service
public class MemberSVCImpl implements IMemberSVC{
	@Autowired
	IMemberDAO mbDao;
	
	@Override
	public boolean isDuplicatedEmail(String email) {
		return mbDao.isDuplicatedEmail(email);
	}

	@Override
	public boolean insertNewMember(MemberVO mb) {
		return mbDao.insertNewMember(mb);
	}

	@Override
	public boolean insertNewMember(String email, String nickname, String password, int pwQuestion, String pwAnswer) {
		return mbDao.insertNewMember(email, nickname, password, pwQuestion, pwAnswer);
	}

	@Override
	public boolean insertNewMemberWithCrypto(MemberVO mb) {
		return mbDao.insertNewMemberWithCrypto(mb);
	}

	@Override
	public boolean isDuplicatedNick(String nickname) {
		return mbDao.isDuplicatedNick(nickname);
	}

	@Override
	public String selectPwByEmail(String email) {
		return mbDao.selectPwByEmail(email);
	}

	@Override
	public String decryptPassword(String email) {
		return mbDao.decryptPassword(email);
	}
	// 로그인 인증 
		@Override
		public int loginProcess(String email, String pw) {
			System.out.println("SVC: loginProcess()");
			
			// 파람 체크
			if( email == null || email.isEmpty() ) 
				
				return MyCode.MBLOGIN_NULLEMTPY;
			if( pw == null || pw.isEmpty() ) 
				return MyCode.MBLOGIN_PW_NULLEMPTY;
			
			// 가입 체크
			MemberVO mb = mbDao.selectOneMemberbyEmail(email);
			if( mb == null ) {
				System.out.println(email + " 회원이 없습니다");
				return MyCode.MBLOGIN_NOT_FOUND;
			}			
			int mbId = mb.getId(); 
			System.out.println("email" + email);
			// 패스 인증
			//String dbPW = this.mbDao.decryptPassword(mbId, login);
			String dbPW = this.mbDao.decryptPassword(email);
			if( dbPW.equals(pw) ) {
				// 인증 성공!
				return MyCode.MBLOGIN_AUTH;
			} else {
				return MyCode.MBLOGIN_PW_MISMATCH;
			}
		}


	@Override
	public List<MemberVO> selectAllMember() {
		return mbDao.selectAllMember();
	}

	@Override
	public MemberVO selectOneMember(int id) {
		return mbDao.selectOneMember(id);
	}

	@Override
	public MemberVO selectOneMemberbyNick(String nickname) {
		return mbDao.selectOneMemberbyNick(nickname);
	}
	@Override
	public MemberVO selectOneMemberbyEmail(String nickname) {
		return mbDao.selectOneMemberbyEmail(nickname);
	}


	@Override
	public boolean updateOneMember(MemberVO mb) {
		return mbDao.updateOneMember(mb);
	}

	@Override
	public boolean updatePw(String email, String pw) {
		return mbDao.updatePw(email, pw);
	}

	@Override
	public boolean deleteOneMember(int id) {
		return mbDao.deleteOneMember(id);
	}

	@Override
	public boolean deleteOneMember(String login) {
		return mbDao.deleteOneMember(login);
	}

	@Override
	public int checkMaxpageNumber() {
		int totalCount = mbDao.checkAllMemberCount();
		int maxPg = totalCount/PAGE_SIZE + (totalCount%PAGE_SIZE ==0?0:1);
		return maxPg;
	}

	@Override
	public List<MemberVO> selectAllMember(int page) {
		int offset = (page-1) * PAGE_SIZE;
		// 0, PAGE_SIZE, PAGE_SIZE*2, PAGE_SIZE*3,... 
	int limit = PAGE_SIZE;
	List<MemberVO> mbListPg
		= mbDao.selectAllArticles(offset, limit);
	System.out.println("atsvc, pg="+ page + 
			" , atList= " + mbListPg.size());
	return mbListPg;
	}

	@Override
	public boolean deleteMember(List<Integer> mbList) {
		if(mbList!=null) {
			
			for (int i = 0; i < mbList.size(); i++) {
				
				int bdId = mbList.get(i);
				boolean b = mbDao.deleteOneMember(bdId);
				if(b) {
					
					System.out.println(">> 요청 게시글 아이디:"+bdId+"번 삭제 성공!");
					
				}else {
					System.out.println(">> 요청 게시글 아이디:"+bdId+"번 삭제 실패!");
					return false;
				}
			}
			return true;
			
		}else {
			
			System.out.println(">> mbList null..");
			return false;
					
		}
	}

	@Override
	public List<MemberVO> searchMember(String keyword, String target, int pg) {
		String orderBy = " order by id desc ";
		int offset = (pg-1) * SEARCH_PAGE_SIZE;
		return mbDao.searchMember(keyword, offset, SEARCH_PAGE_SIZE, orderBy);
		//return null;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String target, String keyword) {
		int totalAtCnt = mbDao.checkAllArticleCount(target, keyword);
		int maxPg = totalAtCnt / SEARCH_PAGE_SIZE + 
				(totalAtCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		Map<String,Integer> rMap = new HashMap<>();
		rMap.put("totalAtCnt", totalAtCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	@Override
	   public int countNewMember() {
	      // TODO Auto-generated method stub
	      return mbDao.countNewMember();
	   }

	   @Override
	   public int countTotalMember() {
	      // TODO Auto-generated method stub
	      return mbDao.countTotalMember();
	   }
	

}
