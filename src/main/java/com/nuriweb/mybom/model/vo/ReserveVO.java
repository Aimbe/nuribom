package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;

public class ReserveVO {
	
	int reserveId;  		 // 예약고유번호 		<<PK>> AI NN
	String reserveNickname;  // 예약자 닉네임 		(varchar 20) NN
	Timestamp reserveDay;    // 상담소  예약일 		(TIMESTAMP) NN
	int reserveTime; 		 // 예약시간 			(int) 24:00 표기법 ex)16:00 
	int reserveUserId; 		 // 유저고유번호		(int) <FK> NN
	int reserveCenterId;	 // 상담소 고유번호		(int) <FK> NN	
	String reserveMemo; 	 // 특이사항 메모 		(varchar 48)
	int reserveVisit; 	  	 // 0==방문미완료 1==방문완료    0==false 1==true  Null기본값 = 0
	int reserveReview; 		 // 0==리뷰미작성 1==리뷰작성    0==false 1==true  Null기본값 = 0
	String reserveCenterName;
	String reserveCenterAdress;
	
	
	
	public ReserveVO() {
		 
	}
	
	public ReserveVO(String reserveNickname, Timestamp reserveDay, int reserveTime, int reserveUserId,
			int reserveCenterId, String reserveMemo) {
		super();
		this.reserveNickname = reserveNickname;
		this.reserveDay = reserveDay;
		this.reserveTime = reserveTime;
		this.reserveUserId = reserveUserId;
		this.reserveCenterId = reserveCenterId;
		this.reserveMemo = reserveMemo;
	}

	


	public String getReserveCenterAdress() {
		return reserveCenterAdress;
	}

	public void setReserveCenterAdress(String reserveCenterAdress) {
		this.reserveCenterAdress = reserveCenterAdress;
	}

	public ReserveVO(int reserveId, String reserveNickname, Timestamp reserveDay, int reserveTime, int reserveUserId,
			int reserveCenterId, String reserveMemo, int reserveVisit,int reserveReview) {
		super();
		this.reserveId = reserveId;
		this.reserveNickname = reserveNickname;
		this.reserveDay = reserveDay;
		this.reserveTime = reserveTime;
		this.reserveUserId = reserveUserId;
		this.reserveCenterId = reserveCenterId;
		this.reserveMemo = reserveMemo;
		this.reserveVisit = reserveVisit;
		this.reserveReview = reserveReview;
		
	}



	public String getReserveCenterName() {
		return reserveCenterName;
	}

	public void setReserveCenterName(String reserveCenterName) {
		this.reserveCenterName = reserveCenterName;
	}

	public int getReserveId() {
		return reserveId;
	}




	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}




	public String getReserveNickname() {
		return reserveNickname;
	}




	public void setReserveNickname(String reserveNickname) {
		this.reserveNickname = reserveNickname;
	}




	public Timestamp getReserveDay() {
		return reserveDay;
	}




	public void setReserveDay(Timestamp reserveDay) {
		this.reserveDay = reserveDay;
	}




	public int getReserveTime() {
		return reserveTime;
	}




	public void setReserveTime(int reserveTime) {
		this.reserveTime = reserveTime;
	}




	public int getReserveUserId() {
		return reserveUserId;
	}




	public void setReserveUserId(int reserveUserId) {
		this.reserveUserId = reserveUserId;
	}




	public int getReserveCenterId() {
		return reserveCenterId;
	}




	public void setReserveCenterId(int reserveCenterId) {
		this.reserveCenterId = reserveCenterId;
	}




	public String getReserveMemo() {
		return reserveMemo;
	}




	public void setReserveMemo(String reserveMemo) {
		this.reserveMemo = reserveMemo;
	}

	public int getReserveVisit() {
		return reserveVisit;
	}

	public void setReserveVisit(int reserveVisit) {
		this.reserveVisit = reserveVisit;
	}

	public int getReserveReview() {
		return reserveReview;
	}

	public void setReserveReview(int reserveReview) {
		this.reserveReview = reserveReview;
	}

	@Override
	public String toString() {
		return "ReserveVO [reserveId=" + reserveId + ", reserveNickname=" + reserveNickname + ", reserveDay="
				+ reserveDay + ", reserveTime=" + reserveTime + ", reserveUserId=" + reserveUserId
				+ ", reserveCenterId=" + reserveCenterId + ", reserveMemo=" + reserveMemo + ", reserveVisit="
				+ reserveVisit + ", reserveReview=" + reserveReview + "]";
	}


}
