package com.nuriweb.mybom.model.dao.inf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.nuriweb.mybom.model.vo.VisitorVO;

public interface IVisitorsDAO {

	//session이 돌때마다 방문자 수 date로 컬럼에 등록
	public boolean insertTotalVistors();
	
	//yymmdd 날짜 별로 총 count 수 구하기
	public int selectDailyVisitors();
	
	//총 컬럼의 count 수 구하기 
	public int selectToatalVistors();
	
	//-1일 값 리턴
	public int selectOneDayBefore();
	
	//-2일 값 리턴
	public int selectTwoDayBefore();
	//-3일 값 리턴
	public int selectThreeDayBefore();
	//-4일 값 리턴
	public int selectFourDayBefore();
	//-5일 값 리턴
	public int selectFiveDayBefore();
	//-6일 값 리턴
	public int selectSixDayBefore();
	
	
}
