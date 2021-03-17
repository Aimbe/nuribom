package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;

public class ReplyVO {

	//ReplyVO: free_reply TBL, nuri_reply TBL, qna_reply TBL(mysql; nuribom_db)에 대응
	private int id; //덧글 아이디 <<pk>> AI NN
	private int bdId; //게시판 아이디 <<fk>>
	private int mbId; //회원 아이디<<fk>>
	private String userName; //작성자이름 (varchar64) not null
	private String content; //덧글 내용 (varchar 200)  not null
	private Timestamp createdAt; //작성일 (datetime) default now()

	
	public ReplyVO() {}

	
	
	public ReplyVO(int bdId, int mbId, String userName, String content) {
		super();
		this.bdId = bdId;
		this.mbId = mbId;
		this.userName = userName;
		this.content = content;
	}

	public ReplyVO(int id, int bdId, int mbId, String userName, String content, Timestamp createdAt) {
		super();
		this.id = id;
		this.bdId = bdId;
		this.mbId = mbId;
		this.userName = userName;
		this.content = content;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getBdId() {
		return bdId;
	}



	public void setBdId(int bdId) {
		this.bdId = bdId;
	}



	public int getMbId() {
		return mbId;
	}



	public void setMbId(int mbId) {
		this.mbId = mbId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Timestamp getcreatedAt() {
		return createdAt;
	}



	public void setcreatedAt(Timestamp createdAt) {
		createdAt = createdAt;
	}



	@Override
	public String toString() {
		return "ReplyVO [id=" + id + ", bdId=" + bdId + ", mbId=" + mbId + ", userName=" + userName + ", content="
				+ content + ", createdAt=" + createdAt + "]";
	}
}
