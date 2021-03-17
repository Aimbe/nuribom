package com.nuriweb.mybom;

public class MyCode {
	// 로그인 상수
	public static final int MBLOGIN_ERROR = 0; 
	public static final int MBLOGIN_AUTH = 1; // authenticate
	public static final int MBLOGIN_NOT_FOUND = 2;
	public static final int MBLOGIN_NULLEMTPY = 3;
	public static final int MBLOGIN_PW_NULLEMPTY = 4;
	public static final int MBLOGIN_PW_MISMATCH = 5;
	
	public static final String[] LOGIN_MSGS = {
		"로그인 에러", "로그인 성공!", "로그인 회원 없음", 
		"로그인 파람 에러", "암호 파람 에러", "암호 불일치"
	};
	
	// 게시글 상수
		public static final int PASSWORD_LENGTH =6;
		
		
		//알림 상수
		public static final int NewFreeBoardNotifi = 0; 
		public static final int NewQuestionNotifi = 1; 
		public static final int NewPostNotifi = 2; 
		public static final int NewPrivateNotifi = 3;
		public static final int NewMemberNotifi = 4;
		public static final int ReserveNotifi = 5; 
		public static final int ReviewPostNotifi = 6; 
		public static final int LikeCenterNotifi = 7; 
}
