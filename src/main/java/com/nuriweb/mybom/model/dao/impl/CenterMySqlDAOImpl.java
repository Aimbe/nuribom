package com.nuriweb.mybom.model.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;
import com.nuriweb.mybom.service.inf.ITagSVC;

@Repository
public class CenterMySqlDAOImpl implements ICenterDAO{
	
	@Autowired
	private JdbcTemplate jtem;
	@Autowired
	private ITagSVC tagSvc;
	
	// SQL 정의
	private static final String SQL_SELECT_ALL_CENTERS= "SELECT * FROM nuribom_db.centers order by created_at desc"; //최신순
	private static final String SQL_SELECT_ALL_CENTERS_OLDEST = "SELECT * FROM nuribom_db.centers"; //오래된순(먼저 등록됨/아이디순)
	private static final String SQL_SELECT_ALL_CENTERS_BY_LIKES= "SELECT * FROM nuribom_db.centers order by likes desc"; //좋아요 많은순
	private static final String SQL_SELECT_ALL_CENTERS_BY_CATEGORY = "SELECT * FROM nuribom_db.centers where category = ?";
	private static final String SQL_SELECT_ALL_CENTERS_BY_ADDR_REGION = "SELECT * FROM nuribom_db.centers where address_region = ?";
	private static final String SQL_SELECT_ALL_CENTERS_BY_ADDR_CITY = "SELECT * FROM nuribom_db.centers where address_city = ?";
	private static final String SQL_GET_COUNT_ALL_CENTERS = "select count(*) from centers";
	private static final String SQL_SELECT_ONE_CENTER = "SELECT * FROM nuribom_db.centers where id = ?";
	
	
	private static final String SQL_SELECT_ALL_CENTERS_PG = "select * from centers order by created_at desc limit ?,?";
	private static final String SQL_SELECT_ALL_CENTERS_BYREGION_PG = "select * from centers where address_region=? order by created_at desc limit ?,?";
	
	
	
	private static final String SQL_UPDATE_CENTER = "UPDATE centers SET name=?, telephone=?, site=?, main_img=?, email=?, category=?, "
			+ "first_desc=?, second_desc=?, third_desc=?, first_program_title=?, first_program_desc=? , first_program_img=?, "
			+ "second_program_title=?, second_program_desc=?, second_program_img=?, address_region=?, address_city=?, address_detail=? where id = ?";
	private static final String SQL_UPDATE_CENTER_OPERTIME = "update centers set open_time=?,close_time=? where id = ?";
	private static final String SQL_UPDATE_CENTER_SITE = "update centers set site=? where id = ?";
	private static final String SQL_UPDATE_CENTER_EMAIL = "update centers set email=? where id = ?";
	private static final String SQL_UPDATE_CENTER_TELNUM = "update centers set telephone=? where id = ?";
	private static final String SQL_UPDATE_CENTER_LIKES = "update centers set likes=? where id = ?";
	private static final String SQL_UPDATE_CENTER_ADDR_REGION = "update centers set address_region=? where id = ?";
	private static final String SQL_UPDATE_CENTER_ADDR_CITY = "update centers set address_city=? where id = ?";
	private static final String SQL_UPDATE_CENTER_ADDR_DETAIL = "update centers set address_detail=? where id = ?";
	private static final String SQL_UPDATE_CENTER_LOCATION = "update centers set longitude=?,latitude=? where id = ?";
	private static final String SQL_UPDATE_CENTER_ALL_IMGS = "update centers set main_img=?,first_program_img=?, second_program_img=? where id = ?";
	private static final String SQL_UPDATE_CENTER_DESCS = "update centers set first_desc=?,second_desc=?, third_desc=?, "
			+ "first_program_title=?,first_program_desc=?, second_program_title=?, second_program_desc=? where id = ?";
	
	private static final String SQL_INSERT_CENTER_SHORTLY = "insert into centers values(null,?, ?, 'http://www.site.com',0,'main.jpg', 'hope@mail.com', '09:00', '18:00', ?,"
			+ " '소개1', '소개2', '소개3', '프로그램1', '프로그램1desc', '프로그램1img', '프로그램2', '프로그램2desc', '프로그램2img', ?, '도시', '세부주소', now(), now(), "
			+ "37.570304515942645, 127.03107052896425)";	//좌표정보는 서울이 디폴트
	private static final String SQL_INSERT_CENTER = "insert into centers values(null,?, ?, ?,0,?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), 37.570304515942645, 127.03107052896425)";
	private static final String SQL_INSERT_CENTER_RETURN_KEY = "insert into centers values(null,?, ?, ?,0,?,' ', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?, ?)";
	private static final String SQL_INSERT_CENTER_FULL = "insert into centers values(null,?, ?, ?,?,?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?, ?)";
	private static final String SQL_DELETE_CENTER = "delete from centers where id = ?";
	
	private static final String SQL_CENTER_SEARCH_NAME = "SELECT * FROM centers where name like concat('%',?,'%')";
	private static final String SQL_CENTER_SEARCH_CATEGORY = "SELECT * FROM centers where category like concat('%',?,'%')";
	private static final String SQL_CENTER_SEARCH_ALL = "SELECT * FROM centers where name like concat('%',?,'%') or category like concat('%',?,'%')";
	private static final String SQL_CENTER_SEARCH_ALL_PG = "SELECT * FROM centers where name like concat('%',?,'%') or category like concat('%',?,'%') order by created_at desc limit ?,?";
	
	private static final String SQL_COUNT_CENTER_SEARCH_ALL_PG = "SELECT count(*) as searchCnt FROM centers where name like concat('%',?,'%') or category like concat('%',?,'%')";
	
	
	@Override
	public List<CenterVO> selectsearchname(String name){
		try {
			System.out.println("여긴왓겟지");
			System.out.println(name);
			return this.jtem.query("select * from centers where name like concat('%','"+name+"','%')"
					, BeanPropertyRowMapper.newInstance(CenterVO.class));
		} catch(EmptyResultDataAccessException e) {
			return null;
		} catch(DataAccessException e) {
			return null;
		}
	}
	
	
	@Override
	public boolean updateEmailOfCenter(String email,int ctId) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_EMAIL, email, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 이메일 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}


	
	
	@Override
	public List<CenterVO> selectAllCenters() {
		return this.jtem.query(SQL_SELECT_ALL_CENTERS, BeanPropertyRowMapper.newInstance(CenterVO.class));
	}

	@Override
	public List<CenterVO> selectAllCentersByOldest() {
		return this.jtem.query(SQL_SELECT_ALL_CENTERS_OLDEST, BeanPropertyRowMapper.newInstance(CenterVO.class));
	}

	@Override
	public List<CenterVO> selectAllCentersByLikesCount() {
		return this.jtem.query(SQL_SELECT_ALL_CENTERS_BY_LIKES, BeanPropertyRowMapper.newInstance(CenterVO.class));
	}

	@Override
	public int getCountOfAllCenters() {
		return this.jtem.queryForObject(SQL_GET_COUNT_ALL_CENTERS, Integer.class);
	}

//	검색된 ct의 개수리턴
	@Override
	public int getAllCenterCount(String target, String keyword) {
		return jtem.queryForObject(SQL_COUNT_CENTER_SEARCH_ALL_PG, Integer.class, keyword, keyword);
	}



	@Override
	public CenterVO getOneCenter(int ctId) {
		try {
			return this.jtem.queryForObject(SQL_SELECT_ONE_CENTER, BeanPropertyRowMapper.newInstance(CenterVO.class), ctId);
		} catch(EmptyResultDataAccessException e) {
			System.out.println("0개 센터 조회");
			return null;
		} catch(DataAccessException e) {
			System.out.println("센터 조회 일반예외 발생.");
			return null;
		}
	}

	@Override
	public List<CenterVO> selectAllCentersByCategory(String category) {
		return this.jtem.query(SQL_SELECT_ALL_CENTERS_BY_CATEGORY, BeanPropertyRowMapper.newInstance(CenterVO.class), category);
	}


	@Override
	public List<CenterVO> selectAllCentersByAddressRegion(String addressRegion) {
		return this.jtem.query(SQL_SELECT_ALL_CENTERS_BY_ADDR_REGION, BeanPropertyRowMapper.newInstance(CenterVO.class), addressRegion);
	}

	@Override
	public List<CenterVO> selectAllCentersByAddressCity(String addressCity) {
		return this.jtem.query(SQL_SELECT_ALL_CENTERS_BY_ADDR_CITY, BeanPropertyRowMapper.newInstance(CenterVO.class), addressCity);
	}

	@Override
	public boolean updateOneCenter(CenterVO ct) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER, ct.getName(), ct.getTelephone(), ct.getSite(), ct.getMainImg(), ct.getEmail(), ct.getCategory(), 
				ct.getFirstDesc(), ct.getSecondDesc(), ct.getThirdDesc(), ct.getFirstProgramTitle(), ct.getFirstProgramDesc(), ct.getFirstProgramImg(), 
				ct.getSecondProgramTitle(), ct.getSecondProgramDesc(), ct.getSecondProgramImg(), ct.getAddressRegion(), ct.getAddressCity(), ct.getAddressDetail(), ct.getId());
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 업데이트 실패 DAE - " + ct);
			return false;
		}
	}

	@Override
	public boolean updateOperatingTimeOfCenter(int ctId, String openTime, String closeTime) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_OPERTIME, openTime, closeTime, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 운영시간 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateSiteOfCenter(int ctId, String site) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_SITE, site, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 사이트 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateTelephoneOfCenter(int ctId, String telephone) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_TELNUM, telephone, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 전화번호 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateLikesOfCenter(int ctId, int likes) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_LIKES, likes, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 좋아요수 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateDescriptionsOfCenter(int ctId, String intro1, String intro2, String intro3, 
					String prTitle1, String prDesc1, String prTitle2, String prDesc2) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_DESCS, intro1, intro2, intro3, prTitle1, prDesc1, prTitle2, prDesc2, ctId);
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 소개문들 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateAddressRegionOfCenter(int ctId, String addressRegion) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_ADDR_REGION, addressRegion, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 region주소 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateAddressCityOfCenter(int ctId, String addressCity) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_ADDR_CITY, addressCity, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 city주소 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean updateAddressDetailOfCenter(int ctId, String addressDetail) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_ADDR_DETAIL, addressDetail, ctId );
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 detail주소 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}


	@Override
	public boolean updateImagesOfCenter(int ctId, String mainImg, String firstPrImg, String secondPrImg) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_ALL_IMGS, mainImg, firstPrImg, secondPrImg, ctId);
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 이미지들 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}
	
	@Override
	public boolean updateLocationOfCenter(int ctId, double latitude, double longitude) {
		try {
			int r = this.jtem.update(SQL_UPDATE_CENTER_LOCATION, latitude, longitude, ctId);
			return r == 1;
		} catch(DataAccessException dae) {
			System.out.println("DAO: 센터 위치 갱신 실패 DAE - ctId: " + ctId);
			return false;
		}
	}

	@Override
	public boolean insertOneCenter(CenterVO ct) {
		int r = this.jtem.update(SQL_INSERT_CENTER, ct.getName(), ct.getTelephone(), ct.getSite(), ct.getMainImg(), ct.getEmail(), ct.getOpenTime(), ct.getCloseTime(), 
				ct.getCategory(), ct.getFirstDesc(), ct.getSecondDesc(), ct.getThirdDesc(), ct.getFirstProgramTitle(), ct.getFirstProgramDesc(), ct.getFirstProgramImg(), 
				ct.getSecondProgramTitle(), ct.getSecondProgramDesc(), ct.getSecondProgramImg(), ct.getAddressRegion(), ct.getAddressCity(), ct.getAddressDetail());
		return r == 1;
	}

	@Override
	public boolean insertOneCenter(String name, String telephone, String category, String addressRegion) {
		int r = this.jtem.update(SQL_INSERT_CENTER_SHORTLY, name, telephone, category, addressRegion);
		return r == 1;
	}

	// img파일경로 없이 추가
	@Override
	public boolean insertOneCenter(String name, String telephone, String site, String email, String category,
			String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle, String firstProgramDesc,
			String secondProgramTitle, String secondProgramDesc, String addressRegion, String addressCity,
			String addressDetail) {
		int r = this.jtem.update(SQL_INSERT_CENTER, name, telephone, site, "main.jpg", email, "09:00", "18:00", category, firstDesc, secondDesc, thirdDesc, 
				firstProgramTitle, firstProgramDesc, "first_pr.jpg", secondProgramTitle, secondProgramDesc, "second_pr.jpg", addressRegion, addressCity, addressDetail);
		return r == 1;
	}
	
	
	@Override
	public boolean insertOneCenter(String name, String telephone, String site, String mainImg, String email,
			String category, String firstDesc, String secondDesc, String thirdDesc, String firstProgramTitle,
			String firstProgramDesc, String firstProgramImg, String secondProgramTitle, String secondProgramDesc,
			String secondProgramImg, String addressRegion, String addressCity, String addressDetail) {
		int r = this.jtem.update(SQL_INSERT_CENTER, name, telephone, site, mainImg, email, "09:00", "18:00", category, firstDesc, secondDesc, thirdDesc, 
				firstProgramTitle, firstProgramDesc, firstProgramImg, secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail);
		return r == 1;
	}

	// full 생성자 필드
	@Override
	public boolean insertOneCenterFull(String name, String telephone, String site, int likes, String mainImg,
			String email, String openTime, String closeTime, String category, String firstDesc, String secondDesc,
			String thirdDesc, String firstProgramTitle, String firstProgramDesc, String firstProgramImg,
			String secondProgramTitle, String secondProgramDesc, String secondProgramImg, String addressRegion,
			String addressCity, String addressDetail, double latitude, double longitude) {
		int r = this.jtem.update(SQL_INSERT_CENTER_FULL, name, telephone, site, likes, mainImg, email, openTime, closeTime, category, firstDesc, secondDesc, thirdDesc, 
				firstProgramTitle, firstProgramDesc, firstProgramImg, secondProgramTitle, secondProgramDesc, secondProgramImg, addressRegion, addressCity, addressDetail, latitude, longitude);
		return r == 1;
	}

	@Override
	public boolean deleteOneCenter(CenterVO ct) {
		int r = this.jtem.update(SQL_DELETE_CENTER, ct.getId());
		return r == 1;
	}

	@Override
	public boolean deleteOneCenter(int ctId) {
		int r = this.jtem.update(SQL_DELETE_CENTER, ctId);
		return r == 1;
	}

	// insert 성공시 pk 키 리턴
	@Override
	public int insertNewCenterReturnKey(CenterVO ct) {
		System.out.println("center Dao: keyHolder... ");
		KeyHolder kh = new GeneratedKeyHolder();
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_CENTER_RETURN_KEY, new String[] {"id"});
						pstmt.setString(1, ct.getName());
						pstmt.setString(2, ct.getTelephone());
						pstmt.setString(3, ct.getSite());
						pstmt.setString(4, ct.getMainImg());
						pstmt.setString(5, ct.getOpenTime());
						pstmt.setString(6, ct.getCloseTime());
						pstmt.setString(7, ct.getCategory());
						pstmt.setString(8, ct.getFirstDesc());
						pstmt.setString(9, ct.getSecondDesc());
						pstmt.setString(10, ct.getThirdDesc());
						pstmt.setString(11, ct.getFirstProgramTitle());
						pstmt.setString(12, ct.getFirstProgramDesc());
						pstmt.setString(13, ct.getFirstProgramImg());
						pstmt.setString(14, ct.getSecondProgramTitle());
						pstmt.setString(15, ct.getSecondProgramDesc());
						pstmt.setString(16, ct.getSecondProgramImg());
						pstmt.setString(17, ct.getAddressRegion());
						pstmt.setString(18, ct.getAddressCity());
						pstmt.setString(19, ct.getAddressDetail());
						pstmt.setDouble(20, ct.getLatitude());
						pstmt.setDouble(21, ct.getLongitude());
					
				return pstmt;
			}
		};
		
		jtem.update(psc, kh);
		Number pk = kh.getKey();
		return pk.intValue();	//centers.id <<PK>> 리턴됨
	}

	@Override
	public List<CenterVO> selectAllCenters(boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> selectAllCenters(int offset, int limit) {
		return jtem.query(SQL_SELECT_ALL_CENTERS_PG, BeanPropertyRowMapper.newInstance(CenterVO.class), offset, limit);
	}

	@Override
	public List<CenterVO> selectAllCentersByRegion(String region, int offset, int limit) {
		return jtem.query(SQL_SELECT_ALL_CENTERS_BYREGION_PG, BeanPropertyRowMapper.newInstance(CenterVO.class),region, offset, limit);
	}
	
	
	@Override
	public List<CenterVO> selectAllCenters(int offset, int limit, boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> searchCenterForCategory(String[] category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> searchCenterForType(String concernType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> searchCenterForTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

//	전체대상 키워드 검색하기 
	@Override
	public List<CenterVO> searchCenteForAll(String keyword) {
		return null;
	}


	@Override
	public List<CenterVO> searchCenter(String keyword, int offset, int limit, boolean order, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> searchCenter(String keyword, int offset, int limit, String column, boolean order,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterVO> searchCenter(String keyword, int offset, int limit, String orderBy, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

//	전체검색 
	@Override
	public List<CenterVO> searchCenter(String keyword, int offset, int limit, String orderBy) {
		return jtem.query(SQL_CENTER_SEARCH_ALL_PG, BeanPropertyRowMapper.newInstance(CenterVO.class), keyword, keyword, offset, limit);
	}

	
	private static final String SQL_SELECT_ALL_CENTER_VIR_LIKES = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C order by sLikes desc limit 0,5";
	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRowLikes() { // 좋아요순 5개..조회 =>메인페이지 위한것..
		return jtem.query(SQL_SELECT_ALL_CENTER_VIR_LIKES, BeanPropertyRowMapper.newInstance(CenterRowVO.class));
	}
	
	

	private static final String SQL_SELECT_ALL_CENTER_VIR = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C order by created_at desc limit 0,10";
	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow() {
		return jtem.query(SQL_SELECT_ALL_CENTER_VIR, BeanPropertyRowMapper.newInstance(CenterRowVO.class));
	}


	private static final String SQL_SELECT_ALL_CENTER_PG_VIR = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C order by created_at desc limit ?,?";

	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow(int offset, int pageSize) {
		//return jtem.query(SQL_SELECT_ALL_CENTER_PG_VIR, BeanPropertyRowMapper.newInstance(CenterRowVO.class)); //, offset, pageSize);
		return jtem.query(SQL_SELECT_ALL_CENTER_PG_VIR, BeanPropertyRowMapper.newInstance(CenterRowVO.class), offset, pageSize);
	}


	public static final String SQL_CENTERS_SEARCH_KEYWORD = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C where name like concat('%',?,'%') order by created_at desc"; // limit ?,?";
	
	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRowKeyword(int offset, int pageSize, String keyword) {
		return jtem.query(SQL_CENTERS_SEARCH_KEYWORD, BeanPropertyRowMapper.newInstance(CenterRowVO.class), keyword); //, offset, pageSize);
	}

	private static final String SQL_CENTERS_SEARCH_REGION = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C where address_region = ? order by created_at desc"; // limit ?,?";

	//public static final String SQL_CENTERS_SEARCH_REGION = "SELECT * FROM centers where address_region like concat('%',?,'%') order by created_at desc limit ?,?";
	
	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRowRegion(int offset, int pageSize, String region) {
		return jtem.query(SQL_CENTERS_SEARCH_REGION, BeanPropertyRowMapper.newInstance(CenterRowVO.class), region);
//				, offset, pageSize);
	}

	
	private static final String SQL_CENTERS_SEARCH_CAT_ORDER = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C where category = ? order by ?"; // limit ?,?";
	
	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow(int offset, int pageSize, String category, String orderBy) {
		return jtem.query(SQL_CENTERS_SEARCH_CAT_ORDER, BeanPropertyRowMapper.newInstance(CenterRowVO.class), category, orderBy);
	}
	
	private static final String SQL_CENTERS_SEARCH_CAT_TAG_ORDER = "select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag  from centers C where category like'%',?,'%' order by created_at desc"; 
	
	@Override
	public List<CenterRowVO> selectAllCentersForVirtualRow(int offset, int pageSize, String category, String tag, String orderBy) {
		return jtem.query(SQL_CENTERS_SEARCH_CAT_TAG_ORDER, BeanPropertyRowMapper.newInstance(CenterRowVO.class), category, orderBy); //tag , offset, pageSize);
	}

	//private static final String SQL_ALL_CENTERS_FULL_SEARCH = "SELECT * FROM centers WHERE name LIKE concat('%',?,'%') and (address_region like ?  ) and (category like ? ) ? limit ?,?";
	private static final String SQL_ALL_CENTERS_FULL_SEARCH = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C where name like concat('%',?,'%') and category = ? order by ? "; // limit ?,?";
	
	
	@Override //태그 미사용중...
	public List<CenterRowVO> selectAllCentersForVirtualRow(int offset, int pageSize, String region, String keyword,
			String category, String tag, String orderBy) {
		return jtem.query(SQL_ALL_CENTERS_FULL_SEARCH, BeanPropertyRowMapper.newInstance(CenterRowVO.class), keyword, category,orderBy); // , offset, pageSize);
	}

	private static final String SQL_GET_LIKE_STATUS = "select C.id as sId, C.name as sName, (select count(*) from likes L where L.center_id = C.id and "
										+ "L.member_id = ? ) sStatus from centers C order by created_at desc;";

	@Override
	public List<CenterRowVO> getLikeStatus(int mbId) {
		return jtem.query(SQL_GET_LIKE_STATUS, BeanPropertyRowMapper.newInstance(CenterRowVO.class), mbId);
	}


	private static final String GET_ONE_VIR_CENTER = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag," + 
			"(select count(*) from likes L where L.center_id = C.id and L.member_id = 1 ) sStatus" + 
			" from centers C where C.id = ? order by created_at desc";

	@Override
	public CenterRowVO getOneCenterVir(int ctId) {
		return jtem.queryForObject(GET_ONE_VIR_CENTER, BeanPropertyRowMapper.newInstance(CenterRowVO.class), ctId);
	}

	
	private String SQL_CENTERS_SEARCH_REGION2 = " select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C where address_region = "; // limit ?,?";
	
	
//	private String SQL_SEARCH_CENTER = "select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
//			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
//			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
//			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
//			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
//			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
//			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
//			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag from centers C where ";
	
	private String SQL_SEARCH_CENTER_ONLY_ORDER = "select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
			" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
			"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
			"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
			"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
			"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
			"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
			"(select group_concat(tag) from tags T where T.center_id = C.id) sTag  from centers C order by";
	@Override
	public List<CenterRowVO> getCenterSearchList(String keyword, String region,  String category, String tag, String orderBy, int offset, int limit) {
		
		try {
			if(keyword == null || keyword.isEmpty()) {
				if(region == null || region.isEmpty()) {
					if(category == null || category.isEmpty() || category.equals("all")) {
						if( tag == null|| tag.isEmpty() || tag.equals("all") ) {
							if( orderBy == null|| orderBy.isEmpty() || orderBy.equals("recent") ) {
								return selectAllCentersForVirtualRow(offset, limit); // 기본조건인 경우 전체리스트리턴
							}
						}
					}
				}
			}
			
			int whereCount = 0;
			int orderCheck = 0;
			int onlyRegion = 0;
			
			String SQL_SEARCH_CENTER = "select C.id as sId, C.name as sName, C.telephone as sTel, C.site as sSite, C.main_img as sMainImg, C.email sEmail," + 
					" C.open_time sOT, C.close_time as sCT, C.first_desc as sFD, C.second_desc as sSD, C.third_desc as sTD, " + 
					"C.first_program_title as sFPT,  C.first_program_desc as sFPD,  C.first_program_img as sFPI, " + 
					"C.second_program_title as sSPT, C.second_program_desc as sSPD, C.second_program_img as sSPI, " + 
					"C.address_region as sAR, C.address_city as sAC,  c.address_detail as sAD, " + 
					"C.created_at as sCreatedAt, C.updated_at as sUpdatedAt, C.latitude as sLatitude, C.longitude as sLongitude, " + 
					"C.category as sCategory, (select count(*) from likes L where L.center_id = C.id) sLikes, " + 
					"(select group_concat(tag) from tags T where T.center_id = C.id) sTag  from centers C where ";
			
			if( keyword != null && !keyword.isEmpty() ) {
				if( keyword.equals("none") ) {
					System.out.println("키워드 none");
					SQL_SEARCH_CENTER += "";
				} else {
					keyword = "name like concat('%','"+keyword+"','%')";
					SQL_SEARCH_CENTER += keyword;
					whereCount++;
					orderCheck++;
				}
			}
			
			if( region != null && !region.isEmpty()  ) {
				if( region.equals("all") ) {
					System.out.println("지역 all");
					SQL_SEARCH_CENTER += "";
				} else {
					region = "address_region = '"+region+"'";
					if( whereCount != 0 ) {
						SQL_SEARCH_CENTER += " and ";
						SQL_SEARCH_CENTER += region;
						whereCount++;
						orderCheck++;
						
					} else {
						SQL_SEARCH_CENTER += region;
						whereCount++;
						orderCheck++;

						onlyRegion++;
						System.out.println("지역만 : " + SQL_SEARCH_CENTER);
						return jtem.query(SQL_SEARCH_CENTER, BeanPropertyRowMapper.newInstance(CenterRowVO.class));

					}
				}
			}
			
			if( category != null && !category.isEmpty() ) {
			
				if( category.equals("all") ) {
					System.out.println("카테고리 all");
					SQL_SEARCH_CENTER += "";
				} else {
					
					category = "category = '"+category+"'";
					if( whereCount != 0 ) {
						System.out.println(SQL_SEARCH_CENTER);
						SQL_SEARCH_CENTER += " and ";
						SQL_SEARCH_CENTER += category;
						whereCount++;
						orderCheck++;
					} else {
						System.out.println(SQL_SEARCH_CENTER);
						SQL_SEARCH_CENTER += category;
						whereCount++;
						orderCheck++;
					}
				}
			}
			
			if( tag != null && !tag.isEmpty() ) {
				if( tag.equals("all") ) {
					SQL_SEARCH_CENTER += "";
				} else {
					//(select group_concat(tag) from tags T where T.center_id = C.id) like concat('%육아%')
					System.out.println("daoimpl바뀌기전tag"+tag);
					tag = "(select group_concat(tag) from tags T where T.center_id = C.id) like concat('%','"+tag+"','%')";
					System.out.println("바뀐tag"+tag);
					if( whereCount != 0 ) {
						SQL_SEARCH_CENTER += " and ";
						SQL_SEARCH_CENTER += tag;
						whereCount++;
						orderCheck++;
					} else {
						SQL_SEARCH_CENTER += tag;
						whereCount++;
						orderCheck++;
					}
				}
			}
			if( orderBy != null && !orderBy.isEmpty() ) {
				if( orderCheck == 0 ) {
					System.out.println("모두 all + 정렬만왔음");
//					SQL_SEARCH_CENTER = SQL_SEARCH_CENTER_ONLY_ORDER +" order by" +orderBy;
//					System.out.println(SQL_SEARCH_CENTER + "정렬");
					System.out.println("모두all+정렬만 쿼리문: "+SQL_SEARCH_CENTER_ONLY_ORDER+orderBy+" limit " + offset + ", "+limit+"");
					return jtem.query(SQL_SEARCH_CENTER_ONLY_ORDER+orderBy+" limit " + offset + ", "+limit+"", BeanPropertyRowMapper.newInstance(CenterRowVO.class));
				} else {
					orderBy = " order by "+orderBy;
					if( whereCount != 0 ) {
						System.out.println(SQL_SEARCH_CENTER);
						System.out.println("어디");
						SQL_SEARCH_CENTER += orderBy;
						whereCount++;
					} else {
						System.out.println(SQL_SEARCH_CENTER);
						System.out.println("왔냐");
						SQL_SEARCH_CENTER += orderBy;
						whereCount++;
					}
				}
			}
			SQL_SEARCH_CENTER+= " limit " + offset + ", "+limit+"";
			System.out.println("성공시 쿼리문: "+SQL_SEARCH_CENTER);
			return jtem.query(SQL_SEARCH_CENTER, BeanPropertyRowMapper.newInstance(CenterRowVO.class));
		} catch(EmptyResultDataAccessException e) {
			System.out.println("ctDao - empty result");
			return null;
		} catch(DataAccessException e) {
//			System.out.println("전체쿼리문: "+SQL_SEARCH_CENTER);
			System.out.println("ctDao - 데이터 접근오류");
			return null;
		}
	} //getSarchCtList


	@Override
	public boolean insertOneCenter(CenterRowVO ct) {
		return false;
	}


	@Override
	public int insertNewCenterReturnKey(CenterRowVO ct) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean deleteOneCenter(CenterRowVO ct) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateOneCenter(CenterRowVO ct) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
