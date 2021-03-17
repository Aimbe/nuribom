package com.nuriweb.mybom.model.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.dao.inf.IReplyDAO;
import com.nuriweb.mybom.model.dao.inf.IReserveDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.ReserveVO;

@Repository
public class ReserveSqlDAOImpl implements IReserveDAO {
	
		// SQL 정의부
		private static String SQL_INSERT_RESERVE = 
			"insert into reserve values(null,?,?,?,?,?,?,0,0)";
		private static String SQL_SELECT_ALL_RESERVE
			= "select * from reserve order by reserve_day";
		private static String SQL_SELECT_ALL_RESERVE_PG
		= "select * from reserve order by reserve_day limit ?, ?";
		private static String SQL_SELECT_ONE_RESERVE = 
				"select * from reserve where reserve_id = ?";
		private static String SQL_SELECT_ALL_RESERVE_OF_ONE_MEMBER = 
				"select * from reserve where reserve_user_id = ?";
		private static String SQL_SELECT_ALL_RESERVE_OF_ONE_MEMBER_PG = 
				"select * from reserve where reserve_user_id = ? order by reserve_day desc limit ?, ?";
		private static String SQL_SELECT_RESERVE_CENTER_TIME = 
				"select * from reserve where reserve_center_id = ? and reserve_day between ? and ?";
		private static String SQL_DELETE_ONE_RESERVE = 
				"delete from reserve  where reserve_id = ?";
		private static String SQL_DELETE_ONE_RESERVE_ =
				"select * from reserve where reserve_user_id = ? order by reserve_day desc";
		private static String SQL_SELECT_MEMBER_NOT_REVIEW_LIST =
				"select * from reserve where reserve_user_id = ? and reserve_review = 0";
		private static String SQL_SELECT_MEMBER_REVIEW_LIST =
				"select * from reserve where reserve_user_id = ? and reserve_review = 1";
		private static String SQL_SELECT_CENTER_LIST =
				"select * from reserve where reserve_center_id = ?";
		private static String SQL_SELECT_CENTER_LIST_NOT_VISIT =
				"select * from reserve where reserve_center_id = ? and reserve_visit = 0";
		private static String SQL_SELECT_CENTER_LIST_VISIT =
				"select * from reserve where reserve_center_id = ? and reserve_visit = 1";
		private static String SQL_SELECT_CENTER_MEMBER_VISIT =
				"SELECT * FROM reserve where reserve_center_id = ? and reserve_user_id = ? and reserve_review=0 and reserve_visit=1 order by reserve_day desc;";
		private static String SQL_UPDATE_REVIEW_CHECK =
				"update reserve set reserve_review = 1 where reserve_id = ?";
		public static String SQL_RESERVE_CHECK_ALL_COUNT = 
				"select count(reserve_id) from reserve";
			public static String SQL_RESERVE_CHECK_MEMBER_COUNT = 
					"select count(reserve_id) from reserve where reserve_user_id = ? ";
				
		
	@Autowired
	private JdbcTemplate jtem;
	
	@Autowired
	ICenterDAO ctDao;
	
	

	
	@Override
	public List<ReserveVO> selectAllReserve(int ofset, int limit) {
		try {
			return jtem.query(SQL_SELECT_ALL_RESERVE_PG,
					BeanPropertyRowMapper.
					newInstance(ReserveVO.class),ofset,limit );
			} catch(EmptyResultDataAccessException e) {
				System.out.println("\nempty -ReserveMySqlDAOImpl selectAllReserve()\r\n");
				return null;
			} catch(DataAccessException e) {
				System.out.println("\n데이터에러 -ReserveMySqlDAOImpl selectAllReserve()\n");
				return null;
			}
	}

	@Override
	public List<ReserveVO> getListSearchReserve(int Id, String Nickname, String CenterName, String day, int time,
			int visit, int review, int ofset, int limit) {
		try {
			if(Id<0&&time<0&&visit==-1&&review==-1) {
				if(Nickname.equals("")||Nickname==null) {
					if(CenterName==null||CenterName.equals("")) {
						if(day==null||day.equals("")) {
							return selectAllReserve(ofset, limit);
					}
				}
			}
			} //아무검색도 안한경우 모든 정보리턴
			if(Id>0) {
				ReserveVO rs = selectOneReserve(Id);
				List<ReserveVO> rsList = new ArrayList<ReserveVO>();
				rsList.add(rs);
				System.out.println("왓냐0");
				return rsList;
			}; 
			int centerId= -1;
			List<CenterVO> searchCenterList = new ArrayList<CenterVO>();
//			List<CenterVO> searchCenterList = ctDao.selectAllCenters();
			if(CenterName != null && !CenterName.equals("")) {
//				searchCenterList = ctDao.selectsearchname(CenterName);
				if(searchCenterList.size()>0) { 
					 centerId = searchCenterList.get(0).getId();
				}
		}
		int whereCount = 0;	
			
		String SQL_SELECT_SEARCH_RESERVE = "select * from reserve where ";
		String nickname = ""; String centerName; String Day; String Time;
		String stVisit; String stReview;
		if(Nickname !=null && !Nickname.equals("")) {
			nickname = "reserve_nickname like concat('%','"+Nickname+"','%')";
			SQL_SELECT_SEARCH_RESERVE+=nickname;
			whereCount++;
		}
		if(centerId!=-1) {
			centerName = "reserve_center_id ="+centerId;
			if(whereCount!=0) {
				SQL_SELECT_SEARCH_RESERVE+=" and ";
				SQL_SELECT_SEARCH_RESERVE+=centerName;
				whereCount++;
			}else {
				SQL_SELECT_SEARCH_RESERVE+=centerName;
				whereCount++;
			}
		}
		if(day !=null&&!day.equals("")) {
			Day = "reserve_day between '"+day+" 00:00:00' and '"+day+" 23:59:00'";
			if(whereCount!=0) {
				SQL_SELECT_SEARCH_RESERVE+=" and ";
				SQL_SELECT_SEARCH_RESERVE+=Day;
				whereCount++;
			}else {
				SQL_SELECT_SEARCH_RESERVE+=Day;
				whereCount++;
			}
		}
		if(time != -1) {
			Time = "reserve_time = "+time;
			if(whereCount!=0) {
				SQL_SELECT_SEARCH_RESERVE+=" and ";
				SQL_SELECT_SEARCH_RESERVE+=Time;
				whereCount++;
			}else {
				SQL_SELECT_SEARCH_RESERVE+=Time;
				whereCount++;
			}
		}
		if(visit != -1) {
			stVisit = "reserve_visit = "+visit;
			if(whereCount!=0) {
				SQL_SELECT_SEARCH_RESERVE+=" and ";
				SQL_SELECT_SEARCH_RESERVE+=stVisit;
				whereCount++;
			}else {
				SQL_SELECT_SEARCH_RESERVE+=stVisit;
				whereCount++;
			}
		}
		if(review != -1) {
			stReview = "reserve_review = "+review;
			if(whereCount!=0) {
				SQL_SELECT_SEARCH_RESERVE+=" and ";
				SQL_SELECT_SEARCH_RESERVE+=stReview;
				whereCount++;
			}else {
				SQL_SELECT_SEARCH_RESERVE+=stReview;
				whereCount++;
			}
		}
		
		SQL_SELECT_SEARCH_RESERVE+= " limit "+ofset+", "+limit+"";
		return jtem.query(SQL_SELECT_SEARCH_RESERVE,
				BeanPropertyRowMapper.newInstance(ReserveVO.class));
		} catch(EmptyResultDataAccessException e) {
			System.out.println("페이지단");
			System.out.println("empty -ReserveMySqlDAOImpl getListSearchReserve");
			return null;
		} catch(DataAccessException e) {
			System.out.println("페이지단");
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getListSearchReserve");
			return null;
		}
	}

	@Override
	public List<ReserveVO> getAllListForMember(int userId, int ofset, int limit) {
		try {		
			return jtem.query(SQL_SELECT_ALL_RESERVE_OF_ONE_MEMBER_PG,
					BeanPropertyRowMapper.
					newInstance(ReserveVO.class) ,userId,ofset,limit);
			} catch(EmptyResultDataAccessException e) {
				System.out.println("empty -ReserveMySqlDAOImpl getAllListForMember");
				return null;
			} catch(DataAccessException e) {
				System.out.println("데이터에러 -ReserveMySqlDAOImpl getallListForMember");
				return null;
			}
	}

	@Override
	public int checkAllReserveCount() {
		
		return jtem.queryForObject(SQL_RESERVE_CHECK_ALL_COUNT,Integer.class);
	}

	@Override
	public int checkAllReserveMemberCount(int mbId) {
		return jtem.queryForObject(SQL_RESERVE_CHECK_MEMBER_COUNT,Integer.class,mbId);
	}

	
	
	
	@Override
	public boolean changeReviewCheck(int id) {
		try {
			System.out.println("안먹은것같은뎅");
		return jtem.update(SQL_UPDATE_REVIEW_CHECK,id)==1;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl insertReserve");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl insertReserve");
			return false;
		}
	}

	@Override
	public ReserveVO thisMemberVisitCenter(int mbId, int ctId) {
		try {
			 List<ReserveVO> rsList = jtem.query(SQL_SELECT_CENTER_MEMBER_VISIT,
						BeanPropertyRowMapper.
						newInstance(ReserveVO.class),ctId,mbId );
			 if(rsList.size()>0) {
				 return rsList.get(0);
				 
			 }
			 else return null;
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl insertReserve");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl insertReserve");
			return null;
		}
		
	}

	@Override
	public boolean insertReserve(ReserveVO rs) {
		try {
			
			int r = this.jtem.update(SQL_INSERT_RESERVE,
					rs.getReserveNickname(), rs.getReserveDay(), 
					rs.getReserveTime(), rs.getReserveUserId(), 
					rs.getReserveCenterId(),rs.getReserveMemo());
			return r == 1;// ? true: false;	
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl insertReserve");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl insertReserve");
			return false;
		}
	}

	@Override
	public int deleteReserve(int reserveId) {
	try {
		int r = this.jtem.update(SQL_DELETE_ONE_RESERVE,reserveId);
		return r ;// ? true: false;	
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl deleteReserve()");
			return 0;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl deleterReserve()");
			return 0;
		}
	}

	@Override
	public ReserveVO selectOneReserve(int reserveId) {
		try {
			ReserveVO rs = jtem.queryForObject(SQL_SELECT_ONE_RESERVE,
				BeanPropertyRowMapper
				.newInstance(ReserveVO.class), reserveId);
			return rs;
		} catch(EmptyResultDataAccessException e) {
			System.out.println(reserveId+"-ReserveMySqlDAOImpl selectOneReserve()");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러-ReserveMySqlDAOImpl selectOneReserve()");
			return null;
		}
	}

	@Override
	public boolean visitCheck(int reserveId) {
	try {
		ReserveVO rs = selectOneReserve(reserveId);
		return rs.getReserveVisit()==1;
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl visitcheck()");
			return false;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl visitCheck()");
			return false;
		}
	}

	@Override
	public List<ReserveVO> selectAllReserve() {
	try {
		return jtem.query(SQL_SELECT_ALL_RESERVE,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) );
		} catch(EmptyResultDataAccessException e) {
			System.out.println("\nempty -ReserveMySqlDAOImpl selectAllReserve()\r\n");
			return null;
		} catch(DataAccessException e) {
			System.out.println("\n데이터에러 -ReserveMySqlDAOImpl selectAllReserve()\n");
			return null;
		}
	}

	@Override
	public List<ReserveVO> getAllListForMember(int userId) {
	try {		
		return jtem.query(SQL_SELECT_ALL_RESERVE_OF_ONE_MEMBER,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,userId);
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getAllListForMember");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getallListForMember");
			return null;
		}
	}

	@Override
	public List<ReserveVO> selectTakesReserve(
			int Id, String Nickname, String CenterName, Timestamp day, int time) {
	try {
		if(Id<0&&Nickname==null&&CenterName==null&&day==null&&time<0) {
			return selectAllReserve();
		} //아무검색도 안한경우 모든 정보리턴
		if(Id>0) {
			ReserveVO rs = selectOneReserve(Id);
			List<ReserveVO> rsList = new ArrayList<ReserveVO>();
			rsList.add(rs);
			
			return rsList;
		}; // pk인 id가 검색에있을경우 1개담긴 리스트 리턴
		// 모든검색없는경우 아이디 검색있는 경우 제외
		// 4개만 조건으로 들어옴 그렇단말은 wher절은 무조건 필수
//		CenterVO center;
		CenterVO center = new CenterVO();
		int centerId = center.getId();
		if(CenterName != null && !CenterName.equals("")) {
		/*
		center = centerdaoimpl.selectOneCenterBycenterName(CenterName);
		if(center==null) { 
			return null;
		}
		*/
	}
	int whereCount = 0;	
		
	String SQL_SELECT_SEARCH_RESERVE = "select * from reserve where ";
	String nickname = ""; String centerName; String Day; String Time;
	if(Nickname !=null && !Nickname.equals("")) {
		nickname = "reserve_nickname = '"+Nickname+"' ";
		SQL_SELECT_SEARCH_RESERVE+=nickname;
		whereCount++;
	}
	
	if(CenterName !=null && !CenterName.equals("")) {
		centerName = "reserve_center_id "+centerId+" ";
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+="and ";
			SQL_SELECT_SEARCH_RESERVE+=centerName;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=centerName;
			whereCount++;
		}
	}
	if(day !=null) {
		Day = "reserve_day = '"+day+"' ";
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+="and ";
			SQL_SELECT_SEARCH_RESERVE+=Day;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=Day;
			whereCount++;
		}
	}
	if(time != -1) {
		Time = "reserve_time = "+time;
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+="and ";
			SQL_SELECT_SEARCH_RESERVE+=Time;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=Time;
			whereCount++;
		}
	}
	
	return jtem.query(SQL_SELECT_SEARCH_RESERVE,
			BeanPropertyRowMapper.newInstance(ReserveVO.class));
	} catch(EmptyResultDataAccessException e) {
		System.out.println("empty");
		return null;
	} catch(DataAccessException e) {
		System.out.println("데이터에러");
		return null;
	}
	}
	@Override
	public List<Integer> getTimeListOfCenter(String day, int centerId) {
	try {
		String startTime = day+" 00:00:00";
		String endtTime = day+" 23:59:00";
		System.out.println(startTime);
		System.out.println(endtTime);
		List<ReserveVO> rsList =  jtem.query(SQL_SELECT_RESERVE_CENTER_TIME,
				BeanPropertyRowMapper.newInstance(ReserveVO.class),
				centerId,startTime,endtTime);
		System.out.println("안오는듯");
		if(rsList!=null&&rsList.size()<1) {
			return null;
		}
		List<Integer> timeList = new ArrayList<Integer>();
		for (ReserveVO rs : rsList) {
			timeList.add(rs.getReserveTime());
		}
		return timeList;
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getTimeListOfCenter");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getTimeListOfCenter");
			return null;
		}
		
	}

	@Override
	public ReserveVO getOneReserveRecently(int userId) {
	try {
		List<ReserveVO> rsList = jtem.query(SQL_DELETE_ONE_RESERVE_,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,userId);
		
		return rsList.get(0);
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getOneReserveRecently");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getOneReserveRecently");
			return null;
		}
	}
	@Override
	public List<ReserveVO> getListReviewCheck(int userId) {
	try {
		return  jtem.query(SQL_SELECT_MEMBER_REVIEW_LIST,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,userId);
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getListReviewCheck");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getListReviewCheck");
			return null;
		}
	}
	@Override
	public List<ReserveVO> getListReviewUnCheck(int userId) {
	try {
		return  jtem.query(SQL_SELECT_MEMBER_NOT_REVIEW_LIST,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,userId);
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getListReviewUnCheck");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getListReviewUnCheck");
			return null;
		}
	}

	@Override
	public List<ReserveVO> getListByCenterAll(int centerId) {
	try {
		return  jtem.query(SQL_SELECT_CENTER_LIST,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,centerId);
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getListByCenterAll");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getListByCenterAll");
			return null;
		}
	}

	@Override
	public List<ReserveVO> getListByCenterNotVisit(int centerId) {
	try {
		return  jtem.query(SQL_SELECT_CENTER_LIST_NOT_VISIT,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,centerId);
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getListByCenterNotVisit");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getListByCenterNotVisit");
			return null;
		}
	}

	@Override
	public List<ReserveVO> getListByCenterVisit(int centerId) {
	try {
		return  jtem.query(SQL_SELECT_CENTER_LIST_VISIT,
				BeanPropertyRowMapper.
				newInstance(ReserveVO.class) ,centerId);
			
		} catch(EmptyResultDataAccessException e) {
			System.out.println("empty -ReserveMySqlDAOImpl getListByCenterVisit");
			return null;
		} catch(DataAccessException e) {
			System.out.println("데이터에러 -ReserveMySqlDAOImpl getListByCenterVisit");
			return null;
		}
	}

	@Override
	public List<ReserveVO> getListSearchReserve(int Id, String Nickname, String CenterName, 
			String day, int time, int visit, int review) {
	try {
		if(Id<0&&time<0&&visit==-1&&review==-1) {
			if(Nickname.equals("")||Nickname==null) {
				if(CenterName==null||CenterName.equals("")) {
					if(day==null||day.equals("")) {
						return selectAllReserve();
				}
			}
		}
		} //아무검색도 안한경우 모든 정보리턴
		if(Id>0) {
			ReserveVO rs = selectOneReserve(Id);
			List<ReserveVO> rsList = new ArrayList<ReserveVO>();
			rsList.add(rs);
			System.out.println("왓냐0");
			return rsList;
		}; 
		int centerId= -1;
		List<CenterVO> searchCenterList = new ArrayList<CenterVO>();
//		List<CenterVO> searchCenterList = ctDao.selectAllCenters();
		if(CenterName != null && !CenterName.equals("")) {
			System.out.println("왓냐1");
			System.out.println("왓냐1");
			System.out.println("왓냐1");
			System.out.println("왓냐1");
//			searchCenterList = ctDao.selectsearchname(CenterName);
			if(searchCenterList.size()>0) { 
				System.out.println("왓냐2");
				 centerId = searchCenterList.get(0).getId();
			}
	}
	int whereCount = 0;	
		
	String SQL_SELECT_SEARCH_RESERVE = "select * from reserve where ";
	String nickname = ""; String centerName; String Day; String Time;
	String stVisit; String stReview;
	if(Nickname !=null && !Nickname.equals("")) {
		nickname = "reserve_nickname like concat('%','"+Nickname+"','%')";
		SQL_SELECT_SEARCH_RESERVE+=nickname;
		whereCount++;
	}
	System.out.println("왓5");
	if(centerId!=-1) {
		System.out.println("왓냐6	");
		System.out.println("여기서 에러날까");
//		centerName = "reserve_center_id = "+centerId+" ";
		centerName = "reserve_center_id ="+centerId;
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+=" and ";
			SQL_SELECT_SEARCH_RESERVE+=centerName;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=centerName;
			whereCount++;
		}
	}
	if(day !=null&&!day.equals("")) {
		Day = "reserve_day between '"+day+" 00:00:00' and '"+day+" 23:59:00'";
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+=" and ";
			SQL_SELECT_SEARCH_RESERVE+=Day;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=Day;
			whereCount++;
		}
	}
	if(time != -1) {
		Time = "reserve_time = "+time;
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+=" and ";
			SQL_SELECT_SEARCH_RESERVE+=Time;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=Time;
			whereCount++;
		}
	}
	if(visit != -1) {
		stVisit = "reserve_visit = "+visit;
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+=" and ";
			SQL_SELECT_SEARCH_RESERVE+=stVisit;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=stVisit;
			whereCount++;
		}
	}
	if(review != -1) {
		stReview = "reserve_review = "+review;
		if(whereCount!=0) {
			SQL_SELECT_SEARCH_RESERVE+=" and ";
			SQL_SELECT_SEARCH_RESERVE+=stReview;
			whereCount++;
		}else {
			SQL_SELECT_SEARCH_RESERVE+=stReview;
			whereCount++;
		}
	}

	return jtem.query(SQL_SELECT_SEARCH_RESERVE,
			BeanPropertyRowMapper.newInstance(ReserveVO.class));
	} catch(EmptyResultDataAccessException e) {
		System.out.println("empty -ReserveMySqlDAOImpl getListSearchReserve");
		return null;
	} catch(DataAccessException e) {
		System.out.println("데이터에러 -ReserveMySqlDAOImpl getListSearchReserve");
		return null;
	}
	
	}
	
	
	
	

}
