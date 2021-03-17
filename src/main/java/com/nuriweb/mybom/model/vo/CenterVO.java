package com.nuriweb.mybom.model.vo;

import java.util.Date;

/*
- CenterVO: centers TBL (mysql; nuribom_db)에 대응
- int id; // 상담소아이디번호 <<PK>> AI NN
- String name; // 상담소이름 (varchar 48) <<UQ>>
- String telephone; // 상담소 전화번호 (varchar 20)
- String site; // 상담소 사이트주소(varchar 248)
- int likes; // 상담소 좋아요 수 (likes TBL로 파악 가능) integer default 0
	   // ctId로 조회되는 모든 mb의 size
- String mainImg; // 상담소 대표이미지(varchar 496 url) 
- String email; // 상담소 이메일(varchar 60)
- String openTime; // 오픈시간(varchar 20) 			ex)09:00
- String closeTime; // 마감시간(varchar 20)			ex)18:00
- String category; // 상담소 카테고리 (5개 중 하나에 해당) (varchar 48;)
- String firstDesc; // 상담소 소개정보1 (text 1000)
- String secondDesc; // 상담소 소개정보2 (text 1000)
- String thirdDesc; // 상담소 소개정보3 (text 1000)
-String firstProgramTitle; // 상담소 제공 프로그램 제목1 (varchar 48)
-String firstProgramDesc; // 상담소 제공 프로그램 설명1 (varchar 256) 
-String secondProgramTitle; // 상담소 제공 프로그램 제목2 (varchar 48)
-String secondProgramDesc; // 상담소 제공 프로그램 설명2 (varchar 256) 
-String addressRegion; // 상담소 주소1(서울시, 경기도,OOO,..) (varchar 20)
-String addressCity; // 상담소 주소2(광진구,용인시 ,OOO,..)  (varchar 20)
-String addressDetail; // 상담소 주소3(그외 세부주소 전체)  (varchar 100)
-Date createdAt; // 생성일
-Date updatedAt; //등록일 
   -상담소위치(Location)   합쳤어요..!					   
-double latitude; // 위도  double
-double longitude; // 경도 double
 */
public class CenterVO {
	// 상담소 카테고리 5개
	public static final String[] CENTER_CATEGORYS = {
		"가정상담소", "성폭력상담소", "청소년상담소", "자살예방센터", "다문화가족"
	};
	
	private int id; 
	private String name; 
	private String telephone; 
	private String site; 
	private int likes; 
	private String mainImg; 
	private String email; 
	private String openTime;
	private String closeTime;
	private String category; 
	private String firstDesc;
	private String secondDesc;
	private String thirdDesc; 
	private String firstProgramTitle; 
	private String firstProgramDesc; 
	private String firstProgramImg; 
	private String secondProgramTitle;
	private String secondProgramDesc; 
	private String secondProgramImg;
	private String addressRegion; 
	private String addressCity; 
	private String addressDetail;
	private Date createdAt;
	private Date updatedAt;
	private double latitude; 
	private double longitude;
	
	public CenterVO() {}

	public CenterVO(String name, String telephone, String category, String addressRegion) {
		this(name, telephone, category, addressRegion, null);
	}
		
	public CenterVO(String name, String telephone, String category, String addressRegion, String addressCity) {
		this(name, telephone, null, null, category, addressRegion, addressCity);
	}

	public CenterVO(String name, String telephone, String site, String email, String category, String addressRegion,
			String addressCity) {
		this(name, telephone, site, email, category, null, null, null, addressRegion, addressCity, null);
	}

	//id, likes, openTime, closeTime, createdAt, updatedAt, latitude, longitude  + img 필드 모두, desc/program 하나씩만
	public CenterVO(String name, String telephone, String site, String email, String category, String firstDesc,
			String firstProgramTitle, String secondProgramTitle, String addressRegion, String addressCity,
			String addressDetail) {
		this(name, telephone, site, email, category, firstDesc, null, null, firstProgramTitle, null, secondProgramTitle, null, addressRegion, addressCity, addressDetail);
	}


	//id, likes, openTime, closeTime, createdAt, updatedAt, latitude, longitude  + img 필드 모두 제외
	public CenterVO(String name, String telephone, String site, String email, String category, 
			String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle,
			String firstProgramDesc, String secondProgramTitle, String secondProgramDesc, String addressRegion,
			String addressCity, String addressDetail) {
		this(name, telephone, site, null, email, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, null, 
			secondProgramTitle, secondProgramDesc, null, addressRegion, addressCity, addressDetail);
	}


	//id, likes, openTime, closeTime, createdAt, updatedAt, latitude, longitude 제외
	public CenterVO(String name, String telephone, String site, String mainImg, String email, String category,
			String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle, String firstProgramDesc,
			String firstProgramImg, String secondProgramTitle, String secondProgramDesc, String secondProgramImg,
			String addressRegion, String addressCity, String addressDetail) {
		this(name, telephone, site, mainImg, email, null, null, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, firstProgramImg, 
			secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail);
	}

	//id, likes, createdAt, updatedAt, latitude, longitude 제외
	public CenterVO(String name, String telephone, String site, String mainImg, String email, String openTime,
			String closeTime, String category, String firstDesc, String secondDesc, String thirdDesc,
			String firstProgramTitle, String firstProgramDesc, String firstProgramImg, String secondProgramTitle,
			String secondProgramDesc, String secondProgramImg, String addressRegion, String addressCity,
			String addressDetail) {
		this(name, telephone, site, 0, mainImg, email, openTime, closeTime, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, firstProgramImg, 
		secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail, 0.0, 0.0);
	}

	
	// id, likes, email, createdAt, updatedAt 제외
	public CenterVO(String name, String telephone, String site, String mainImg, String openTime, String closeTime,
			String category, String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle,
			String firstProgramDesc, String firstProgramImg, String secondProgramTitle, String secondProgramDesc,
			String secondProgramImg, String addressRegion, String addressCity, String addressDetail, double latitude,
			double longitude) {
		this(0, name, telephone, site, 0, mainImg, "", openTime, closeTime, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, firstProgramImg, 
				secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail, null, null, latitude, longitude);
	}
	
	// id, likes, createdAt, updatedAt 제외
	public CenterVO(String name, String telephone, String site, String mainImg, String email, String openTime,
			String closeTime, String category, String firstDesc, String secondDesc, String thirdDesc,
			String firstProgramTitle, String firstProgramDesc, String firstProgramImg, String secondProgramTitle,
			String secondProgramDesc, String secondProgramImg, String addressRegion, String addressCity,
			String addressDetail, double latitude, double longitude) {
		this(0, name, telephone, site, 0, mainImg, email, openTime, closeTime, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, firstProgramImg, 
				secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail, null, null, latitude, longitude);
	}

	//id, createdAt, updatedAt 제외
	public CenterVO(String name, String telephone, String site, int likes, String mainImg, String email,
			String openTime, String closeTime, String category, String firstDesc, String secondDesc, String thirdDesc,
			String firstProgramTitle, String firstProgramDesc, String firstProgramImg, String secondProgramTitle,
			String secondProgramDesc, String secondProgramImg, String addressRegion, String addressCity,
			String addressDetail, double latitude, double longitude) {
		this(0, name, telephone, site, likes, mainImg, email, openTime, closeTime, category, firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, firstProgramImg, 
				secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail, null, null, latitude, longitude);
	}

	//Full 생성자
	public CenterVO(int id, String name, String telephone, String site, int likes, String mainImg, String email,
			String openTime, String closeTime, String category, String firstDesc, String secondDesc, String thirdDesc,
			String firstProgramTitle, String firstProgramDesc, String firstProgramImg, String secondProgramTitle,
			String secondProgramDesc, String secondProgramImg, String addressRegion, String addressCity,
			String addressDetail, Date createdAt, Date updatedAt, double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.site = site;
		this.likes = likes;
		this.mainImg = mainImg;
		this.email = email;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.category = category;
		this.firstDesc = firstDesc;
		this.secondDesc = secondDesc;
		this.thirdDesc = thirdDesc;
		this.firstProgramTitle = firstProgramTitle;
		this.firstProgramDesc = firstProgramDesc;
		this.firstProgramImg = firstProgramImg;
		this.secondProgramTitle = secondProgramTitle;
		this.secondProgramDesc = secondProgramDesc;
		this.secondProgramImg = secondProgramImg;
		this.addressRegion = addressRegion;
		this.addressCity = addressCity;
		this.addressDetail = addressDetail;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFirstDesc() {
		return firstDesc;
	}

	public void setFirstDesc(String firstDesc) {
		this.firstDesc = firstDesc;
	}

	public String getSecondDesc() {
		return secondDesc;
	}

	public void setSecondDesc(String secondDesc) {
		this.secondDesc = secondDesc;
	}

	public String getThirdDesc() {
		return thirdDesc;
	}

	public void setThirdDesc(String thirdDesc) {
		this.thirdDesc = thirdDesc;
	}

	public String getFirstProgramTitle() {
		return firstProgramTitle;
	}

	public void setFirstProgramTitle(String firstProgramTitle) {
		this.firstProgramTitle = firstProgramTitle;
	}

	public String getFirstProgramDesc() {
		return firstProgramDesc;
	}

	public void setFirstProgramDesc(String firstProgramDesc) {
		this.firstProgramDesc = firstProgramDesc;
	}

	public String getFirstProgramImg() {
		return firstProgramImg;
	}

	public void setFirstProgramImg(String firstProgramImg) {
		this.firstProgramImg = firstProgramImg;
	}

	public String getSecondProgramTitle() {
		return secondProgramTitle;
	}

	public void setSecondProgramTitle(String secondProgramTitle) {
		this.secondProgramTitle = secondProgramTitle;
	}

	public String getSecondProgramDesc() {
		return secondProgramDesc;
	}

	public void setSecondProgramDesc(String secondProgramDesc) {
		this.secondProgramDesc = secondProgramDesc;
	}

	public String getSecondProgramImg() {
		return secondProgramImg;
	}

	public void setSecondProgramImg(String secondProgramImg) {
		this.secondProgramImg = secondProgramImg;
	}

	public String getAddressRegion() {
		return addressRegion;
	}

	public void setAddressRegion(String addressRegion) {
		this.addressRegion = addressRegion;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "CenterVO [id=" + id + ", name=" + name + ", telephone=" + telephone + ", site=" + site + ", likes="
				+ likes + ", mainImg=" + mainImg + ", email=" + email + ", openTime=" + openTime + ", closeTime="
				+ closeTime + ", category=" + category + ", firstDesc=" + firstDesc + ", secondDesc=" + secondDesc
				+ ", thirdDesc=" + thirdDesc + ", firstProgramTitle=" + firstProgramTitle + ", firstProgramDesc="
				+ firstProgramDesc + ", firstProgramImg=" + firstProgramImg + ", secondProgramTitle="
				+ secondProgramTitle + ", secondProgramDesc=" + secondProgramDesc + ", secondProgramImg="
				+ secondProgramImg + ", addressRegion=" + addressRegion + ", addressCity=" + addressCity
				+ ", addressDetail=" + addressDetail + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
	
	
	
}





























