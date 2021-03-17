package com.nuriweb.mybom.service.inf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.vo.MemberVO;


public interface IMemberSVC {
	
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
	
	int loginProcess(String email, String password);
	
//	로그아웃 할 수 있다. (서비스)
	
//  전체 회원을 조회할 수 있다.	
	List<MemberVO> selectAllMember();
	List<MemberVO> selectAllMember(int page);
//	내 정보를 수정할 수 있다. (닉네임 중복체크)
	//회원 정보 상세보기
	MemberVO selectOneMember(int id); // <<PK>>
	MemberVO selectOneMemberbyNick(String nickname);
	MemberVO selectOneMemberbyEmail(String nickname);
	
	boolean updateOneMember(MemberVO mb);
//	내 비밀번호를 변경 할 수 있다.
	boolean updatePw(String email, String pw);
//	탈퇴할 수 있다. (정보수정페이지에서)
	boolean deleteOneMember(int id);
	boolean deleteOneMember(String login);
	int checkMaxpageNumber();
	public static final int SEARCH_PAGE_SIZE = 10;
	public static final int PAGE_SIZE = 10;
	boolean deleteMember(List<Integer> mbList);
	List<MemberVO> searchMember(String k, String target, int pg);
	Map<String, Integer> checkMaxPageNumber(String target, String keyword); 
	
	   int countNewMember();
	   //오늘 가입 멤버수 보여줌
	   int countTotalMember();
	   //총 가입한 회원수 보여줌
	   
}
