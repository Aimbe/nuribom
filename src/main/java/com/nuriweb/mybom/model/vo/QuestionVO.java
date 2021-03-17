package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;

public class QuestionVO {

	//카테고리 상수
	public static final int CATEGORY_LOGIN = 1;
	public static final int CATEGORY_CENTER = 2;
	public static final int CATEGORY_FUNCTION = 3;
	//답변
	public static final int ADMIN_REPLY_WAIT = 1;
	public static final int ADMIN_REPLY_COMPLETION = 2;
	//공개 유무
	public static final int QNA_SHOW = 1;
	public static final int QNA_HIDE = 2;
	
	private int id; //질문 아이디번호 <<pk>> AI  NN
	private int mbId; //회원 아이디번호<<FK>>
	private String writer; //글쓴이 (varchar64) not null
	private String title; //글제목 (varchar 64)not null
	private String content; //글내용(varchar 500) 글내용 not null
	private String password; //수정비번 (varchar 6) not null
	private int category; //질문 카테고리 (int) 1.로그인문의 2.상담문의 3.기능문의 default 1 
	private boolean secret; //공개유무 (int) 1.공개 2.비공개 default 1
	private boolean reply; //답 유무 (int) 1.답변예정 2.답변완료  default 1
	private Timestamp createdAt; //작성날짜 (datetime) default now()

	public QuestionVO() {}

	public QuestionVO(int mbId, String writer, String title, String content, String password, int category,
			boolean secret) {
		super();
		this.mbId = mbId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.password = password;
		this.category = category;
		this.secret = secret;
	}

	
	
	public QuestionVO(int mbId, String writer, String title, String content, String password, int category,
			boolean secret, boolean reply) {
		super();
		this.mbId = mbId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.password = password;
		this.category = category;
		this.secret = secret;
		this.reply = reply;
	}

	public QuestionVO(int id, int mbId, String writer, String title, String content, String password, int category,
			boolean secret, boolean reply, Timestamp createdAt) {
		super();
		this.id = id;
		this.mbId = mbId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.password = password;
		this.category = category;
		this.secret = secret;
		this.reply = reply;
		this.createdAt = createdAt;
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMbId() {
		return mbId;
	}

	public void setMbId(int mbId) {
		this.mbId = mbId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	public boolean isReply() {
		return reply;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "QuestionVO [id=" + id + ", mbId=" + mbId + ", writer=" + writer + ", title=" + title + ", content="
				+ content + ", password=" + password + ", category=" + category + ", secret=" + secret + ", reply="
				+ reply + ", createdAt=" + createdAt + "]";
	}
	
	
	
	
	
}
