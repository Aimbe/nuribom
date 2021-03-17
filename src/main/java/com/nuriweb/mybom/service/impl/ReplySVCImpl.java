package com.nuriweb.mybom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IReplyDAO;
import com.nuriweb.mybom.model.vo.ReplyVO;
import com.nuriweb.mybom.service.inf.IReplySVC;

@Service
public class ReplySVCImpl implements IReplySVC {

	
	
	
	
	@Autowired
	IReplyDAO rpDao;
	
	@Override
	public boolean insertNewReply(String table, ReplyVO rp) {
		
		return rpDao.insertNewReply(table, rp);
	}

	@Override
	public boolean insertNewReply(String table, int bdId, int mbId, String userName, String content) {
		// TODO Auto-generated method stub
		return rpDao.insertNewReply(table, bdId, mbId, userName, content);
	}

	//리턴키! 
	@Override
	public int insertNewReplyReturnKey(String table, ReplyVO rp) {
		
		return rpDao.insertNewReplyReturnKey(table, rp);
	}
	
	
	@Override
	public List<ReplyVO> selectAllReply(String table, int bdId) {
		
		return rpDao.selectAllReply(table, bdId);
	}

	@Override
	public ReplyVO selectOneReply(String table, int id) {

		return rpDao.selectOneReply(table, id);
	}

	@Override
	public List<ReplyVO> selectAllReplyByUser(String table, int mbId) {
	
		return rpDao.selectAllReplyByUser(table, mbId);
	}

	@Override
	public List<ReplyVO> selectAllReplyByUser(String table, String UserName) {
	
		return rpDao.selectAllReplyByUser(table, UserName);
	}

	@Override
	public boolean updateOneReply(String table, ReplyVO rp) {
		
		return rpDao.updateOneReply(table, rp);
	}

	@Override
	public boolean updateOneReply(String table, int id, String content) {
		
		return rpDao.updateOneReply(table, id, content);
	}

	@Override
	public boolean updateAllReplyByUser(String table, int mbId, String userName) {
		
		return rpDao.updateAllReplyByUser(table, mbId, userName);
	}

	@Override
	public boolean deleteOneReply(String table, int id) {
		
		return rpDao.deleteOneReply(table, id);
	}

	@Override
	public boolean deleteAllReplyByBoard(String table, int bdId) {
		
		return rpDao.deleteAllReplyByBoard(table, bdId);
	}

	
	
	@Override
	public List<ReplyVO> searchAllReply(String table, int mbId, String content) {
		
		return rpDao.searchAllReply(table, mbId, content);
	}

	@Override
	public int checkReplyCountForBoard(String table, int bdId) {
		
		return rpDao.checkReplyCountForBoard(table, bdId);
	}

	@Override
	public ReplyVO selectOneReplyByQnA(int bdId) {
		
		return rpDao.selectOneReplyByQnA(bdId);
	}

}
