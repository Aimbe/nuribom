package com.nuriweb.mybom.model.dao.inf;
import java.util.List;

import com.nuriweb.mybom.model.vo.AdminVO;


public interface IAdminDAO {

	
boolean insertNewVersionInfo(AdminVO ad);
	
	//버전 정보,신규 회원자 수,좋아요 수를  입력할 수 있다.default 0
	boolean insertNewAdminInfo(String versionInfo);
	
	//버전 정보를 업데이트 할 수 있다.
	boolean updateVersionInfo(String versionInfo);
	
	//신규 회원 수를 갱신 할 수 있다.
	boolean updateNewMembers(int newMembers);
		
	//총 멤버 수를 갱신 할 수 있다.
	boolean updatetotalMembers(int totalMembers);
	//하루 방문자 갱신
	boolean updateDailyVisitors(int dailyVisitors);
	//하루 페이지수 갱신
	boolean updateDailyPage(int dailyPage);
	//총 페이지수 갱신
	boolean updateTotalPages(int totalPages);
	
	//총 방문자 수 갱신
	boolean updateTotalVisitors(int totalVisitors);
	
	//모든 정보 출력
	List<AdminVO> selectAllAdminInfo();	
	
	//주별 페이지뷰
	List<AdminVO> selectDailyPagesWithWeek();
	
	//주별 방문자수
	List<AdminVO> selectDailyVisitorsWithWeek();
	//월별 페이지뷰
	List<AdminVO> selectDailyPagesWithMonth();
	//월별 방문자수
	List<AdminVO> selectDailyVisitorsWithMonth();
	//버전으로 삭제
	boolean deleteAllAdminInfo(String version);
	
	
}
