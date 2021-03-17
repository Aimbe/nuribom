package com.nuriweb.mybom.model.vo;

import java.sql.Timestamp;
import java.util.Date;

public class AdminVO {


private int versionId; //버전 아이디 NN PK AI
private Timestamp currentTime; //현재시간  default now
private String versionInfo; //(varchar 24)  
private int dailyPages; // 페이지 뷰 (하루동안) 
private int dailyVisitors ; //방문자 수 (하루동안) 
private int totalPages; // 총 페이지뷰
private int totalVisitors; //총 방문자 수
private int totalMembers; //총  가입 멤버 수 
private int newMembers; //신규 가입 회원 수


public AdminVO() {
	super();
}


public AdminVO(String versionInfo, int totalMembers, int newMembers) {
	super();
	this.versionInfo = versionInfo;
	this.totalMembers = totalMembers;
	this.newMembers = newMembers;
}






public AdminVO(int versionId, Timestamp currentTime, String versionInfo, int dailyPages, int dailyVisitors,
		int totalPages, int totalVisitors, int totalMembers, int newMembers) {
	super();
	this.versionId = versionId;
	this.currentTime = currentTime;
	this.versionInfo = versionInfo;
	this.dailyPages = dailyPages;
	this.dailyVisitors = dailyVisitors;
	this.totalPages = totalPages;
	this.totalVisitors = totalVisitors;
	this.totalMembers = totalMembers;
	this.newMembers = newMembers;
}




public int getVersionId() {
	return versionId;
}


public void setVersionId(int versionId) {
	this.versionId = versionId;
}


public int getDailyPages() {
	return dailyPages;
}


public void setDailyPages(int dailyPages) {
	this.dailyPages = dailyPages;
}


public Timestamp getCurrentTime() {
	return currentTime;
}


public void setCurrentTime(Timestamp currentTime) {
	this.currentTime = currentTime;
}


public String getVersionInfo() {
	return versionInfo;
}


public void setVersionInfo(String versionInfo) {
	this.versionInfo = versionInfo;
}


public int getDailyVisitors() {
	return dailyVisitors;
}


public void setDailyVisitors(int dailyVisitors) {
	this.dailyVisitors = dailyVisitors;
}


public int getTotalPages() {
	return totalPages;
}


public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}


public int getTotalVisitors() {
	return totalVisitors;
}


public void setTotalVisitors(int totalVisitors) {
	this.totalVisitors = totalVisitors;
}


public int gettotalMembers() {
	return totalMembers;
}


public void settotalMembers(int totalMembers) {
	this.totalMembers = totalMembers;
}


public int getNewMembers() {
	return newMembers;
}


public void setNewMembers(int newMembers) {
	this.newMembers = newMembers;
}


@Override
public String toString() {
	return "AdminVO [versionId=" + versionId + ", currentTime=" + currentTime + ", versionInfo=" + versionInfo
			+ ", dailyPages=" + dailyPages + ", dailyVisitors=" + dailyVisitors + ", totalPages=" + totalPages
			+ ", totalVisitors=" + totalVisitors + ", totalMembers=" + totalMembers + ", newMembers=" + newMembers + "]";
}





}