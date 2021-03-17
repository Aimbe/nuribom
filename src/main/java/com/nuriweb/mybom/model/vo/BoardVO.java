package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;


public class BoardVO {
	//기본 썸네일 이미지 준비
	public static final String DEF_THUMBNAIL = "default.png";
	
	
	//BoardVO: freeboard TBL,nuriboard TBL,faqboard TBL(mysql; nuribom_db)에 대응
	private int id; //게시판 아이디번호 <<pk>> AI NN
	private int mbId; //회원 아이디번호<<fk>>
	private String writer; //글쓴이 (varchar64)not null 
	private String title; //글제목 (varchar24)not null
	private String content; //(text)글내용 not null
	private String password; //(varchar 6) not null 글 수정시 사용할 비밀번호 
	private int view; //조회수 default 0 
	private Timestamp createdAt; //작성 날짜 (datetime) default now()
	private String thumbnail; //게시판 썸네일 이미지 경로<uq> <varchar800> default (null 시 넣을 기본 이미지 준비.. 그 url) //null 이거나 비거나 .png .jpg 확장자명 안맞을 때 모두 default 값 처리

	public BoardVO() {}

	//freeboard, faqboard
	public BoardVO(int mbId, String writer, String title, String content, String password) {
		super();
		this.mbId = mbId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.password = password;
	}
	
	//nuriboard
	public BoardVO(int mbId, String writer, String title, String content, String password, String thumbnail) {
		super();
		this.mbId = mbId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.password = password;
		this.thumbnail = thumbnail;
	}



	public BoardVO(int id, int mbId, String writer, String title, String content, String password, int view,
			Timestamp createdAt, String thumbnail) {
		super();
		this.id = id;
		this.mbId = mbId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.password = password;
		this.view = view;
		this.createdAt = createdAt;
		this.thumbnail = thumbnail;
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

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public static String getDefThumbnail() {
		return DEF_THUMBNAIL;
	}

	
}//class
