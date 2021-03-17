package com.nuriweb.mybom.model.vo;

import java.util.Arrays;
import java.util.Date;

/*
###상담소태그(Tag) : - 1상담소 당 여러개 가능   				         담당: 김은영
 ex) 우울증, 부부, 연인, 학업, 직장...
-TagVO: tags TBL (mysql; nuribom_db)에 대응
-int id; //태그 아이디번호 <<PK>>
-String tag; // 해당태그입력(varchar 48) NN
-int centerId; // 상담소아이디번호 <<FK>> (PK) 
-Date registeredAt; // 태그등록시점
> 상담소아이디에 해당하는 태그 여러개 저장 => 상담소아이디로 모든 태그를 조회가능 
 */
public class TagVO {

	//상담소 태그들  - 현재 18개
	public static final String[] CENTER_TAGS = {
		"친구", "부부", "대외관계", "연인", "가족", 
		"학업", "직장", "진로", "취업", "육아", 
		"해외생활", "중독", "섭식장애", "성생활", "성소수자", 
		"감정조절", "자해", "자살", 
	};
	public static final int TAG_FRIEND = 0;
	public static final int TAG_MARRIED= 1;
	public static final int TAG_SOCIAL_RELATION= 2;
	public static final int TAG_COUPLE = 3;
	public static final int TAG_FAMILY = 4;
	public static final int TAG_STUDY = 5;
	public static final int TAG_COMPANY = 6;
	public static final int TAG_CAREER = 7;
	public static final int TAG_JOB = 8;
	public static final int TAG_CHILDCARE= 9;
	public static final int TAG_OVERSEAS = 10;
	public static final int TAG_ADDICTED = 11;
	public static final int TAG_EATING_DISORDER = 12;
	public static final int TAG_SEX_LIFE = 13;
	public static final int TAG_SEXUAL_MINOR = 14;
	public static final int TAG_EMOTION_CONTROL = 15;
	public static final int TAG_SELFHARM = 16;
	public static final int TAG_SUICIDE = 17;
	
	
	private int id; //태그 아이디번호 <<PK>>
	private String tag; // 해당태그입력(varchar 48) NN
	private int centerId; // 상담소아이디번호 <<FK>> (PK) 
	private Date registeredAt;

	public TagVO() {}

	public TagVO(String tag, int centerId) {
		this(0, tag, centerId, null);
	}
	
	public TagVO(int id, String tag, int centerId, Date registeredAt) {
		super();
		this.id = id;
		this.centerId = centerId;
		this.tag = tag;
		this.registeredAt = registeredAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	@Override
	public String toString() {
		return "TagVO [id=" + id + ", tag=" + tag + ", centerId=" + centerId + ", registeredAt=" + registeredAt + "]";
	}

	
	
	
}
