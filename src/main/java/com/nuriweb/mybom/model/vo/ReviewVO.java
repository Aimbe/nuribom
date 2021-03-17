package com.nuriweb.mybom.model.vo;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReviewVO {

	// ReviewVO: review TBL(mysql; nuribom_db)에 대응
	 private int reviewId; 				// review_id  			<<pk>> AI  NN 		리뷰 아이디번호   
	 private int reviewMemberId;		// review_member_id		 <<fk>> 			회원 아이디    
	 private int reviewCenterId; 		// review_center_id 	<<fk>>  			상담소 아이디   
	 private int reviewReserveId;		// review_reserve_id 	<<FK>> 				예약 아이디    
	 private int reviewRate; 			// review_rate			 default 5 평점 1~5             
	 private String reviewUserName;		// review_user_name 	 (varchar 64) NN	글쓴이       
	 private String reviewContent; 		// review_content  		 (varchar 300)NN	내용        
 private String reviewContentTest;        
	 private Timestamp reviewCreatedAt; // review_created_at	 default now() 		작성날짜      
	 private String yyyyMMddhhmm;
	
	 private String reviewCenterName;
	 private String reviewCenterAddress;
	 private String reviewDay;
	 private String reviewCenterImagePath;
	 
	 
	


	public void setReviewContentTest(String reviewContentTest) {
		this.reviewContentTest = reviewContentTest;
	}

	public String getReviewCenterImagePath() {
		return reviewCenterImagePath;
	}

	public void setReviewCenterImagePath(String reviewCenterImagePath) {
		this.reviewCenterImagePath = reviewCenterImagePath;
	}

	public String getReviewCenterName() {
		return reviewCenterName;
	}

	public void setReviewCenterName(String reviewCenterName) {
		this.reviewCenterName = reviewCenterName;
	}

	public String getReviewCenterAddress() {
		return reviewCenterAddress;
	}

	public void setReviewCenterAddress(String reviewCenterAddress) {
		this.reviewCenterAddress = reviewCenterAddress;
	}

	public String getReviewDay() {
		return reviewDay;
	}

	public void setReviewDay(String reviewDay) {
		this.reviewDay = reviewDay;
	}

	public ReviewVO() {
		 
	}
	 
	public ReviewVO(int reviewMemberId, int reviewCenterId, int reviewReserveId, int reviewRate, String reviewUserName,
			String reviewContent) {
		super();
		this.reviewMemberId = reviewMemberId;
		this.reviewCenterId = reviewCenterId;
		this.reviewReserveId = reviewReserveId;
		this.reviewRate = reviewRate;
		this.reviewUserName = reviewUserName;
		this.reviewContent = reviewContent;
	}

	public ReviewVO(int reviewId, int reviewMemberId, int reviewCenterId, int reviewReserveId, int reviewRate,
			String reviewUserName, String reviewContent, Timestamp reviewCreatedAt) {
		super();
		this.reviewId = reviewId;
		this.reviewMemberId = reviewMemberId;
		this.reviewCenterId = reviewCenterId;
		this.reviewReserveId = reviewReserveId;
		this.reviewRate = reviewRate;
		this.reviewUserName = reviewUserName;
		this.reviewContent = reviewContent;
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewMemberId() {
		return reviewMemberId;
	}

	public void setReviewMemberId(int reviewMemberId) {
		this.reviewMemberId = reviewMemberId;
	}

	public int getReviewCenterId() {
		return reviewCenterId;
	}

	public void setReviewCenterId(int reviewCenterId) {
		this.reviewCenterId = reviewCenterId;
	}


	public int getReviewReserveId() {
		return reviewReserveId;
	}

	public void setReviewReserveId(int reviewReserveId) {
		this.reviewReserveId = reviewReserveId;
	}

	public int getReviewRate() {
		return reviewRate;
	}

	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}


	public String getReviewUserName() {
		return reviewUserName;
	}


	public void setReviewUserName(String reviewUserName) {
		this.reviewUserName = reviewUserName;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public Timestamp getReviewCreatedAt() {
		return reviewCreatedAt;
	}

	public void setReviewCreatedAt(Timestamp reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
//		 SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
//		 this.yyyyMMddhhmm = sdf.format(this.reviewCreatedAt);
	}
	


	public String getYyyyMMddhhmm() {
		return yyyyMMddhhmm;
	}

	public void setYyyyMMddhhmm(String yyyyMMddhhmm) {
		this.yyyyMMddhhmm = yyyyMMddhhmm;
	}

	public String getReviewContentTest() {
		return reviewContentTest;
	}

	@Override
	public String toString() {
		return "ReviewVO [reviewId=" + reviewId + ", reviewMemberId=" + reviewMemberId + ", reviewCenterId="
				+ reviewCenterId + ", reviewReserveId=" + reviewReserveId + ", reviewRate=" + reviewRate
				+ ", reviewUserName=" + reviewUserName + ", reviewContent=" + reviewContent + ", reviewCreatedAt="
				+ reviewCreatedAt + "]";
	} 
	
}