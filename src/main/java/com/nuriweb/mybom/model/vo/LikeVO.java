package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;
import java.util.Date;
/*
 ### 좋아요
- LikeVO: likes TBL (mysql; nuribom_db)에 대응
-int id; //좋아요 아이디번호 <<PK>>
-int memberId; // 회원번호  <<FK>> 
-int centerId; // 상담소 아이디번호  <<FK>> 
-Date pushedAt; // 좋아요 눌렀을때 시간 (알람창 위한것) - 클릭:좋아요/다시클릭:좋아요취소
> 해당 상담소(아이디로 구분)를 좋아요 누른 회원아이디를 모두 카운트 = 상담소별 총 “좋아요”수 파악 가능
> 해당 회원아이디로 상담소리스트를 조회= 해당 회원이 좋아요 누른 상담소 파악가능
 */
public class LikeVO {
	
	private int id; //좋아요 아이디번호 <<PK>>
	private int memberId; // 회원번호  <<FK>> 
	private int centerId; // 상담소 아이디번호  <<FK>> 
	private Date pushedAt; //좋아요 누른시점
	
	public LikeVO() {}

	public LikeVO(int memberId, int centerId) {
		super();
		this.memberId = memberId;
		this.centerId = centerId;
	}

	public LikeVO(int id, int memberId, int centerId, Date pushedAt) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.centerId = centerId;
		this.pushedAt = pushedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public Date getPushedAt() {
		return pushedAt;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	@Override
	public String toString() {
		return "LikeVO [id=" + id + ", memberId=" + memberId + ", centerId=" + centerId + ", pushedAt=" + pushedAt
				+ "]";
	}

	
	
	
}
