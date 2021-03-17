package com.nuriweb.mybom.model.dao.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.MemberVO;

public interface IMemberDAO {
	
//	회원가입 시 이메일 중복체크 할 수 있다.
	boolean isDuplicatedEmail(String email);
//	회원가입 할 수 있다. (id 중복체크; Sequence Diagram) 패스워드 암호화
	boolean insertNewMember(MemberVO mb);
	boolean insertNewMember(String email, String nickname, String password, int pwQuestion, String pwAnswer);
	//(패스워드 암호화 저장)
	boolean insertNewMemberWithCrypto(MemberVO mb);
//	닉네임 중복체크 할 수 있다.
	boolean isDuplicatedNick(String nickname);
//	가입한 이메일로 비밀번호를 전달받을수있다 (비밀번호찾기)
	// 이메일로 비번찾기
	String selectPwByEmail(String email);
	
//	회원가입시 이메일을 인증할 수 있다.  (이메일인증)
	 
//	가입된 계정으로 로그인 할 수 있다. (세션, 암호화 인증)
	String decryptPassword(String email);

//	로그아웃 할 수 있다. (서비스)
	
//  전체 회원을 조회할 수 있다.	
	List<MemberVO> selectAllMember();
//	내 정보를 수정할 수 있다. (닉네임 중복체크)
	//회원 정보 상세보기
	MemberVO selectOneMember(int id); // <<PK>>
// 닉네임, 비번질문, 비번답변을 업데이트 할 수 있다.
	boolean updateOneMember(MemberVO mb);
	
	
//	내 비밀번호를 변경 할 수 있다.
	boolean updatePw(String email, String pw);
//	탈퇴할 수 있다. (정보수정페이지에서)
	boolean deleteOneMember(int id);
	boolean deleteOneMember(String login);
	MemberVO selectOneMemberbyNick(String nickname);
	MemberVO selectOneMemberbyEmail(String nickname);
	int checkAllMemberCount();
	List<MemberVO> selectAllArticles(int offset, int limit);
	List<MemberVO> searchMember(String keyword, int offset, int searchPageSize, String orderBy);
	int checkAllArticleCount(String target, String keyword);
	
	   int countNewMember();
	   //오늘 가입 멤버수 보여줌
	   int countTotalMember();
	   //총 가입한 회원수 보여줌


}
