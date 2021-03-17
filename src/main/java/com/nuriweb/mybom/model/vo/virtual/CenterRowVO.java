package com.nuriweb.mybom.model.vo.virtual;

import java.util.Date;
/*
 select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail,
 C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, 
C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, 
C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, 
C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, 
C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, 
C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, 
(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C order by created_at desc limit 0,10;
 */

public class CenterRowVO {
	private int sId; 
	private String sName; 
	private String sTel; 
	private String sSite; 
	
	private int sLikes; 
	private String sTag;
	private String sCategory; 
	
	
	private String sMainImg; 
	private String sEmail; 
	private String sOT;
	private String sCT;
	
	private String sFD;
	private String sSD;
	private String sTD; 
	
	private String sFPT; 
	private String sFPD; 
	private String sFPI; 
	
	private String sSPT;
	private String sSPD; 
	private String sSPI;
	
	private String sAR; 
	private String sAC; 
	private String sAD;
	
	private Date sCreatedAt;
	private Date sUpdatedAt;
	private double sLatitude; 
	private double sLongitude;
	
	private int sStatus;
	
	public CenterRowVO() {}

	public CenterRowVO(int sId, String sName, String sTel, String sSite, int sLikes, String sTag, String sCategory,
			String sMainImg, String sEmail, String sOT, String sCT, String sFD, String sSD, String sTD, String sFPT,
			String sFPD, String sFPI, String sSPT, String sSPD, String sSPI, String sAR, String sAC, String sAD,
			Date sCreatedAt, Date sUpdatedAt, double sLatitude, double sLongitude) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sTel = sTel;
		this.sSite = sSite;
		this.sLikes = sLikes;
		this.sTag = sTag;
		this.sCategory = sCategory;
		this.sMainImg = sMainImg;
		this.sEmail = sEmail;
		this.sOT = sOT;
		this.sCT = sCT;
		this.sFD = sFD;
		this.sSD = sSD;
		this.sTD = sTD;
		this.sFPT = sFPT;
		this.sFPD = sFPD;
		this.sFPI = sFPI;
		this.sSPT = sSPT;
		this.sSPD = sSPD;
		this.sSPI = sSPI;
		this.sAR = sAR;
		this.sAC = sAC;
		this.sAD = sAD;
		this.sCreatedAt = sCreatedAt;
		this.sUpdatedAt = sUpdatedAt;
		this.sLatitude = sLatitude;
		this.sLongitude = sLongitude;
	}
	
	

	public CenterRowVO(int sId, String sName, String sTel, String sSite, int sLikes, String sTag, String sCategory,
			String sMainImg, String sEmail, String sOT, String sCT, String sFD, String sSD, String sTD, String sFPT,
			String sFPD, String sFPI, String sSPT, String sSPD, String sSPI, String sAR, String sAC, String sAD,
			Date sCreatedAt, Date sUpdatedAt, double sLatitude, double sLongitude, int sStatus) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sTel = sTel;
		this.sSite = sSite;
		this.sLikes = sLikes;
		this.sTag = sTag;
		this.sCategory = sCategory;
		this.sMainImg = sMainImg;
		this.sEmail = sEmail;
		this.sOT = sOT;
		this.sCT = sCT;
		this.sFD = sFD;
		this.sSD = sSD;
		this.sTD = sTD;
		this.sFPT = sFPT;
		this.sFPD = sFPD;
		this.sFPI = sFPI;
		this.sSPT = sSPT;
		this.sSPD = sSPD;
		this.sSPI = sSPI;
		this.sAR = sAR;
		this.sAC = sAC;
		this.sAD = sAD;
		this.sCreatedAt = sCreatedAt;
		this.sUpdatedAt = sUpdatedAt;
		this.sLatitude = sLatitude;
		this.sLongitude = sLongitude;
		this.sStatus = sStatus;
	}

	
	
	
	public int getsStatus() {
		return sStatus;
	}

	public void setsStatus(int sStatus) {
		this.sStatus = sStatus;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsTel() {
		return sTel;
	}

	public void setsTel(String sTel) {
		this.sTel = sTel;
	}

	public String getsSite() {
		return sSite;
	}

	public void setsSite(String sSite) {
		this.sSite = sSite;
	}

	public int getsLikes() {
		return sLikes;
	}

	public void setsLikes(int sLikes) {
		this.sLikes = sLikes;
	}

	public String getsTag() {
		return sTag;
	}

	public void setsTag(String sTag) {
		this.sTag = sTag;
	}

	public String getsCategory() {
		return sCategory;
	}

	public void setsCategory(String sCategory) {
		this.sCategory = sCategory;
	}

	public String getsMainImg() {
		return sMainImg;
	}

	public void setsMainImg(String sMainImg) {
		this.sMainImg = sMainImg;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsOT() {
		return sOT;
	}

	public void setsOT(String sOT) {
		this.sOT = sOT;
	}

	public String getsCT() {
		return sCT;
	}

	public void setsCT(String sCT) {
		this.sCT = sCT;
	}

	public String getsFD() {
		return sFD;
	}

	public void setsFD(String sFD) {
		this.sFD = sFD;
	}

	public String getsSD() {
		return sSD;
	}

	public void setsSD(String sSD) {
		this.sSD = sSD;
	}

	public String getsTD() {
		return sTD;
	}

	public void setsTD(String sTD) {
		this.sTD = sTD;
	}

	public String getsFPT() {
		return sFPT;
	}

	public void setsFPT(String sFPT) {
		this.sFPT = sFPT;
	}

	public String getsFPD() {
		return sFPD;
	}

	public void setsFPD(String sFPD) {
		this.sFPD = sFPD;
	}

	public String getsFPI() {
		return sFPI;
	}

	public void setsFPI(String sFPI) {
		this.sFPI = sFPI;
	}

	public String getsSPT() {
		return sSPT;
	}

	public void setsSPT(String sSPT) {
		this.sSPT = sSPT;
	}

	public String getsSPD() {
		return sSPD;
	}

	public void setsSPD(String sSPD) {
		this.sSPD = sSPD;
	}

	public String getsSPI() {
		return sSPI;
	}

	public void setsSPI(String sSPI) {
		this.sSPI = sSPI;
	}

	public String getsAR() {
		return sAR;
	}

	public void setsAR(String sAR) {
		this.sAR = sAR;
	}

	public String getsAC() {
		return sAC;
	}

	public void setsAC(String sAC) {
		this.sAC = sAC;
	}

	public String getsAD() {
		return sAD;
	}

	public void setsAD(String sAD) {
		this.sAD = sAD;
	}

	public Date getsCreatedAt() {
		return sCreatedAt;
	}

	public void setsCreatedAt(Date sCreatedAt) {
		this.sCreatedAt = sCreatedAt;
	}

	public Date getsUpdatedAt() {
		return sUpdatedAt;
	}

	public void setsUpdatedAt(Date sUpdatedAt) {
		this.sUpdatedAt = sUpdatedAt;
	}

	public double getsLatitude() {
		return sLatitude;
	}

	public void setsLatitude(double sLatitude) {
		this.sLatitude = sLatitude;
	}

	public double getsLongitude() {
		return sLongitude;
	}

	public void setsLongitude(double sLongitude) {
		this.sLongitude = sLongitude;
	}

	@Override
	public String toString() {
		return "CenterRowVO [sId=" + sId + ", sName=" + sName + ", sTel=" + sTel + ", sSite=" + sSite + ", sLikes="
				+ sLikes + ", sTag=" + sTag + ", sCategory=" + sCategory + ", sMainImg=" + sMainImg + ", sEmail="
				+ sEmail + ", sOT=" + sOT + ", sCT=" + sCT + ", sFD=" + sFD + ", sSD=" + sSD + ", sTD=" + sTD
				+ ", sFPT=" + sFPT + ", sFPD=" + sFPD + ", sFPI=" + sFPI + ", sSPT=" + sSPT + ", sSPD=" + sSPD
				+ ", sSPI=" + sSPI + ", sAR=" + sAR + ", sAC=" + sAC + ", sAD=" + sAD + ", sCreatedAt=" + sCreatedAt
				+ ", sUpdatedAt=" + sUpdatedAt + ", sLatitude=" + sLatitude + ", sLongitude=" + sLongitude
				+ ", sStatus=" + sStatus + "]";
	}


	
	
	
}
