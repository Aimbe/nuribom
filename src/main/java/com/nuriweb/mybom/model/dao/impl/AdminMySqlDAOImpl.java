package com.nuriweb.mybom.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.IAdminDAO;
import com.nuriweb.mybom.model.vo.AdminVO;

@Repository
public class AdminMySqlDAOImpl implements IAdminDAO {

	
	
	private static String SQL_INSERT_AdminInfo = 
			"insert into admin(version_info) values(?)";
		private static String SQL_UPDATE_VersionInfo = 
			"update admin set version_info= ? where no = ?";
			
		private static String SQL_UPDATE_NewMembers =
				"update admin set new_members=? where version_id= ?";
		private static String SQL_UPDATE_totalMembers = 
				"update admin set total_likes=? where version_id = ?";
		private static String SQL_UPDATE_DailyVisitors = 
				"update admin set daily_visitors=? where version_id = ?";
		
		private static String SQL_UPDATE_DailyPages = 
				"update admin set daily_pages=? where version_id = ?";
		
		private static String SQL_UPDATE_TotalVisitors = 
				"update admin set total_visitors=? where version_id = ?";
		
		private static String SQL_UPDATE_TotalPages = 
				"update admin set total_visitors=? where version_id = ?";
		
		private static String SQL_SELECT_ALLAdmin
			= "select * from Admin";

		private static String SQL_DELETE_AllAdmin = 
				"delete from Admin where version_id= ?";
		
		//totalmembers sql 작성
		public static String selectNewMember ="SELECT COUNT(*) as newmembers from Member" + 
				"WHERE FORMAT(joined_at, 'YYYY-MM-DD') = FORMAT(now(), 'YYYY-MM-DD')"
				+ "values(now())";
	
		public static String selectTotalMember ="SELECT COUNT(*) as newmembers from Member"; 
		

@Autowired
JdbcTemplate jtem;
	
	@Override
	public boolean insertNewVersionInfo(AdminVO ad) {
		
		return false;
	}

	@Override
	public boolean insertNewAdminInfo(String versionInfo) {
		
		try {
		int r = this.jtem.update(SQL_INSERT_AdminInfo,versionInfo);
		
			return r==1;
		}catch(EmptyResultDataAccessException e) {
			System.out.println("empty date acces  error on NewAdminInfo");
			
			return false;
		}
	}

	@Override
	public boolean updateVersionInfo(String version_info) {

		int r = this.jtem.update(SQL_UPDATE_VersionInfo,version_info);
				return r ==1;
		
	}

	@Override
	public boolean updateNewMembers(int newMembers) {
		
		try {
		int r = this.jtem.update(SQL_UPDATE_NewMembers,newMembers);
				return r ==1;
		}catch(EmptyResultDataAccessException e) {
			System.out.println("empty result error on UpdateNewMember");
			return false;
		}
		
		}

	@Override
	public boolean updatetotalMembers(int totalMembers) {
		try {
			int r = this.jtem.update(SQL_UPDATE_NewMembers,totalMembers);
					return r ==1;
			}catch(EmptyResultDataAccessException e) {
				System.out.println("empty result error on UpdateNewMember");
				return false;
			}
			
	}

	
	//VistorDao로 이동
	
	@Override
	public boolean updateDailyVisitors(int dailyVisitors) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDailyPage(int dailyPage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTotalPages(int totalPages) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTotalVisitors(int totalVisitors) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AdminVO> selectAllAdminInfo() {
		return jtem.query(SQL_SELECT_ALLAdmin,BeanPropertyRowMapper.newInstance(AdminVO.class));
	}

	@Override
	public List<AdminVO> selectDailyPagesWithWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminVO> selectDailyVisitorsWithWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminVO> selectDailyPagesWithMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminVO> selectDailyVisitorsWithMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllAdminInfo(String version) {
		// TODO Auto-generated method stub
		return false;
	}

}
