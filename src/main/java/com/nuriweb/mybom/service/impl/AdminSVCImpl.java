package com.nuriweb.mybom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IAdminDAO;
import com.nuriweb.mybom.model.vo.AdminVO;
import com.nuriweb.mybom.service.inf.IAdminSVC;

@Service
public class AdminSVCImpl implements IAdminSVC {

	@Autowired
	IAdminDAO adDao;
	
	@Override
	public boolean insertNewVersionInfo(AdminVO ad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewAdminInfo(String versionInfo) {
		// TODO Auto-generated method stub
		return this.adDao.insertNewAdminInfo(versionInfo);
	}

	@Override
	public boolean updateVersionInfo(String versionInfo) {
		// TODO Auto-generated method stub
		return this.adDao.updateVersionInfo(versionInfo);
	}

	@Override
	public boolean updateNewMembers(int newMembers) {
		// TODO Auto-generated method stub
		return this.adDao.updateNewMembers(newMembers);
	}

	@Override
	public boolean updatetotalMembers(int totalMembers) {
		// TODO Auto-generated method stub
		return this.adDao.updatetotalMembers(totalMembers);
	}

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
		// TODO Auto-generated method stub
		return this.adDao.selectAllAdminInfo();
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
