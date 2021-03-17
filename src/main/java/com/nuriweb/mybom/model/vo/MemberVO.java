package com.nuriweb.mybom.model.vo;

import java.util.Date;

public class MemberVO {

//	### 회원(Member)  								         담당: 유현경
//	MemberVO: members TBL (mysql; nuribom_db)에 대응
	private int id; //  관리번호 <pk> AI  0번 admin
	private String email; // 이메일 (UQ) NN
	private String nickname; //  (varchar 64) 닉네임 (UQ) NN
	private String password; // (varchar 64) - 비밀번호 NN
	private Date joinedAt; //  (TIMESTAMP) - 가입 날짜 default CURRENT_TIMESTAMP
	private Date updatedAt; //(TIMESTAMP) - 정보 수정 날짜 default CURRENT_TIMESTAMP
	private int pwQuestion; // 비밀번호 질문 (카테고리로 구분) NN
	private String pwAnswer; // (varchar 64) 비밀번호 답변 NN
	
	public MemberVO() {
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", email=" + email + ", nickname=" + nickname + ", password=" + password
				+ ", joinedAt=" + joinedAt + ", updatedAt=" + updatedAt + ", pwQuestion=" + pwQuestion + ", pwAnswer="
				+ pwAnswer + "]";
	}


	public MemberVO(String email, String nickname, String password, int pwQuestion, String pwAnswer) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
	}


	public MemberVO(int id, String email, String nickname, String password, Date joinedAt, Date updatedAt,
			int pwQuestion, String pwAnswer) {
		super();
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.joinedAt = joinedAt;
		this.updatedAt = updatedAt;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Date joinedAt) {
		this.joinedAt = joinedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getPwQuestion() {
		return pwQuestion;
	}

	public void setPwQuestion(int pwQuestion) {
		this.pwQuestion = pwQuestion;
	}

	public String getPwAnswer() {
		return pwAnswer;
	}

	public void setPwAnswer(String pwAnswer) {
		this.pwAnswer = pwAnswer;
	}
	
	
	

}
