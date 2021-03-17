package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;
import java.util.Date;

public class AllNotifiVO {

	
	public static final String[] msgTitleArray = {"댓글알림","Q&A 답글 알림","게시글 등록 알림","개인정보 변경 알림","누리봄 가입 알림","예약 알림","리뷰등록 알림","좋아요 알림","비밀번호 변경 알림","게시글 수정 알림","게시글 삭제 알림"};
	public static final String[] msgDetailArray = {"내글에 댓글이 달렸습니다.","작성하신 Q&A에 답글이 달렸습니다.","작성하신 게시글이 등록되었습니다.","개인정보 수정이 완료되었습니다",
			"누리봄의 회원이 되신걸 진심으로 환영합니다!","상담소 예약이 정상적으로 완료되었습니다.","리뷰 작성이 완료되었습니다. 소중한 후기 감사합니다!","회원님이 상담소에 좋아요를 눌렀습니다.","비밀번호가 변경되었습니다.","게시글이 수정되었습니다.","게시글이 삭제되었습니다."};
	
	
	private int no; //알람 아이디번호<<pk>> AI 
	private int bdId; //게시판 아이디  <<FK>>
	private int commentId; //댓글 아이디번호  <<FK>>
	private int questiontId; //질문 아이디 번호 <<FK>>
	private int reviewId; //리뷰 아이디 번호 <<FK>>
	private int mbId; //회원 아이디 <<FK>>
	private int ctId; //상담소 아이디 <<FK>>
	private int reserveId; //예약 번호 <<fk>>
	private boolean reply; //질문글 답변 알림
	private String name; // 상담소이름 (varchar 48)<<FK>>  
	private String nickname; //유저 닉네임 <UQ><<FK>> 
	private Timestamp reserveDay; // 예약날짜
	private int category;//알림 종류(1~5){댓글,게시글,개인정보,예약,리뷰요청} 
	private Timestamp timestampAt; //default now() 그시간
	private int reserveVisit; //미방문 ==1 방문 ==2 
	private String msgTitle; //{댓글알림,게시글 등록 알림,개인정보 변경 알림,예약 알림, 리뷰요청 알림}
	private String msgDetail; //{00님이 내글에 댓글을 달았습니다,작성하신 게시글이 등록되었습니다,개인정보 수정이 완료되었습니다,yyyyMMdd 00상담소 예약이 완료되었습니다,00상담소 방문은 만족하셨나요?더 나은 환경을 위해 후기를 남겨주세요!}
	private int readCheck; //{안읽음1 읽음2}
	private String link; //링크
	
	
	
	public AllNotifiVO() {
		super();
	}

	//종류별로 생성자 만들기 (알림 별로) 
	
	//자유게시판 댓글 알림
	
	public AllNotifiVO(int bdId, int commentId, int mbId, String nickname, int category,String link) {
		super();
		this.no = no;
		this.bdId = bdId;
		this.commentId = commentId;
		this.mbId = mbId;
		this.nickname = nickname;
		this.category = category;
		this.link = link;
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
	}
	
	

	//관리자 fnq댓글 알림 
		
	public AllNotifiVO(int no,int questiontId, int mbId, boolean reply, String nickname, int category,
			 String msgTitle, String msgDetail) {
		super();
		this.no =no;
		this.questiontId = questiontId;
		this.mbId = mbId;
		this.reply = reply;
		this.nickname = nickname;
		this.category = category;
		
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
	}

	
	//게시글 등록 알림
	
	public AllNotifiVO(int no,int bdId, int questiontId, int reviewId, int mbId, String nickname, int category,
			 String msgTitle, String msgDetail) {
		super();
		this.no =no;
		this.bdId = bdId;
		this.questiontId = questiontId;
		this.reviewId = reviewId;
		this.mbId = mbId;
		this.nickname = nickname;
		this.category = category;
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
	}
	
	
	//개인정보 변경알림
	public AllNotifiVO(int no,int mbId, String nickname, int category, String msgTitle,
			String msgDetail) {
		super();
		this.no =no;
		this.mbId = mbId;
		this.nickname = nickname;
		this.category = category;
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
	}

	
	
	//예약 알림
	
	public AllNotifiVO(int no ,int mbId, int ctId, int reserveId, String name, String nickname, Timestamp reserveDay,
			int category, String msgTitle, String msgDetail) {
		super();
		this.no =no;
		this.mbId = mbId;
		this.ctId = ctId;
		this.reserveId = reserveId;
		this.name = name;
		this.nickname = nickname;
		this.reserveDay = reserveDay;
		this.category = category;
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
	}

	
	//리뷰 요청 알림

	public AllNotifiVO(int no,int mbId, int ctId, int reserveId, String name, String nickname, Timestamp reserveDay,
			int category, int reserveVisit, String msgTitle, String msgDetail) {
		super();
		this.no =no;
		this.mbId = mbId;
		this.ctId = ctId;
		this.reserveId = reserveId;
		this.name = name;
		this.nickname = nickname;
		this.reserveDay = reserveDay;
		this.category = category;
		this.reserveVisit = reserveVisit;
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
	}
	
	
	//전체 생성자
	
	public AllNotifiVO(int no, int bdId, int commentId, int questiontId, int reviewId, int mbId, int ctId,
			int reserveId, boolean reply, String name, String nickname, Timestamp reserveDay, int category,
			Timestamp timestampAt, int reserveVisit, String msgTitle, String msgDetail, int readCheck,String link) {
		super();
		this.no = no;
		this.bdId = bdId;
		this.commentId = commentId;
		this.questiontId = questiontId;
		this.reviewId = reviewId;
		this.mbId = mbId;
		this.ctId = ctId;
		this.reserveId = reserveId;
		this.reply = reply;
		this.name = name;
		this.nickname = nickname;
		this.reserveDay = reserveDay;
		this.category = category;
		this.timestampAt = timestampAt;
		this.reserveVisit = reserveVisit;
		this.msgTitle = msgTitleArray[category];
		this.msgDetail = msgDetailArray[category];
		this.readCheck = readCheck;
		this.link =link;
	}
	

	

	

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getBdId() {
		return bdId;
	}

	

	public void setBdId(int bdId) {
		this.bdId = bdId;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public AllNotifiVO(boolean reply) {
		super();
		this.reply = reply;
	}

	public int getReserveId() {
		return reserveId;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public Timestamp getReserveDay() {
		return reserveDay;
	}

	public void setReserveDay(Timestamp reserveDay) {
		this.reserveDay = reserveDay;
	}

	public Timestamp gettimestampAt() {
		return timestampAt;
	}

	public void settimestampAt(Timestamp timestampAt) {
		this.timestampAt = timestampAt;
	}

	public int getcommentId() {
		return commentId;
	}



	public void setcommentId(int commentId) {
		this.commentId = commentId;
	}



	public int getquestiontId() {
		return questiontId;
	}



	public void setquestiontId(int questiontId) {
		this.questiontId = questiontId;
	}



	public int getreviewId() {
		return reviewId;
	}



	public void setreviewId(int reviewId) {
		this.reviewId = reviewId;
	}



	public int getMbId() {
		return mbId;
	}



	public void setMbId(int mbId) {
		this.mbId = mbId;
	}



	public int getCtId() {
		return ctId;
	}



	public void setCtId(int ctId) {
		this.ctId = ctId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getCategory() {
		return category;
	}



	public void setCategory(int category) {
		this.category = category;
	}



	public String getmsgTitle() {
		return msgTitle;
	}



	public void setmsgTitle(String msgTitle) {
		this.msgTitle = msgTitleArray[category];
	}



	public boolean isReply() {
		return reply;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	public int getreserveVisit() {
		return reserveVisit;
	}

	public void setreserveVisit(int reserveVisit) {
		this.reserveVisit = reserveVisit;
	}

	public String getmsgDetail() {
		return msgDetail;
	}



	public void setmsgDetail(String msgDetail) {
		this.msgDetail = msgDetailArray[category];
	}

	public int getReadCheck() {
		return readCheck;
	}

	public void setReadCheck(int readCheck) {
		this.readCheck = readCheck;
	}

	@Override
	public String toString() {
		return "AllNotifiVO [no=" + no + ", bdId=" + bdId + ", commentId=" + commentId + ", questiontId=" + questiontId
				+ ", reviewId=" + reviewId + ", mbId=" + mbId + ", ctId=" + ctId + ", reserveId=" + reserveId
				+ ", reply=" + reply + ", name=" + name + ", nickname=" + nickname + ", reserveDay=" + reserveDay
				+ ", category=" + category + ", timestampAt=" + timestampAt + ", reserveVisit=" + reserveVisit
				+ ", msgTitle=" + msgTitle + ", msgDetail=" + msgDetail + ", readCheck=" + readCheck + ", link=" + link
				+ "]";
	}


	
	


	
}

