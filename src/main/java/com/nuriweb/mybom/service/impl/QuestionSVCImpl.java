package com.nuriweb.mybom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.IQuestionDAO;
import com.nuriweb.mybom.model.vo.QuestionVO;
import com.nuriweb.mybom.service.inf.IQuestionSVC;

@Service
public class QuestionSVCImpl implements IQuestionSVC {

	
	@Autowired
	IQuestionDAO qnDao;
	
	
	@Override
	public List<QuestionVO> selectAllQuestion(int page, int pgSize) {
		
		int offset = (page-1)*pgSize;
		int limit = pgSize;
		
		List<QuestionVO> qnaList = qnDao.selectAllQuestion(offset, limit);
		
		if(qnaList!=null) {
			
			System.out.println(">> QnASvc, pg: "+page+", QnAList: "+qnaList.size());
			return qnaList;
		
		}else {
			System.out.println(">> qnaList 페이지네이션 전체조회 실패..");
			return null;
		}
	
	}
	
	
	@Override
	public List<QuestionVO> selectAllWaitingQuestion(int page, int pgSize) {

		int offset =(page-1)*pgSize;
		int limit = pgSize;
		
		List<QuestionVO> qnaList = qnDao.selectAllWaitingQuestion(offset, limit);
		
		if(qnaList!=null) {
			
			System.out.println(">> 어드민 QnaSvc, pg: "+page+", QnAList:"+qnaList.size());
			return qnaList;
		}else {
			
			System.out.println(">> 어드민 qnaList 페이지네이션 전체조회 실패..");
			return null;
			
		}
		
	}

	
	

	@Override
	public int checkMaxpageNumber(int pgSize) {
	
		int totalCount = qnDao.checkAllQnACount();
		int maxPg = totalCount/pgSize + (totalCount%pgSize ==0?0:1);
		
		return maxPg;
	}

	
	@Override
	public int checkMaxpageNumberByWaiting(int pgSize) {
	
		int totalCount = qnDao.checkWaitingQnACount();
		int maxPg = totalCount/pgSize + (totalCount%pgSize ==0?0:1);
		return maxPg;
	}
	
	
	@Override
	public List<QuestionVO> selectAllQuestionByUser(int mbId,int page,int pgSize) {
		
		int offset = (page-1)*pgSize;
		int limit = pgSize;
		
		List<QuestionVO> qnaList = qnDao.selectAllQuestionByUser(mbId, offset, limit);
		
		if(qnaList!=null) {
			
			System.out.println(">> QnASvc 요청 mbId: "+mbId+" , pg: "+page+", QnAList: "+qnaList.size());
			return qnaList;
		}else {
			System.out.println(">> qnaList 해당 회원 페이지네이션 전체조회 실패: "+mbId);
			return null;
		}
	
	}

	@Override
	public List<QuestionVO> selectAllQuestionByUser(String userName) {
		
		return qnDao.selectAllQuestionByUser(userName);
	}

	@Override
	public List<QuestionVO> selectAllWaitingQuestion() {

		return qnDao.selectAllWaitingQuestion();
	}

	@Override
	public QuestionVO selectOneQuestion(int id) {

		return qnDao.selectOneQuestion(id);
	}

	@Override
	public boolean insertNewQuestion(QuestionVO qna) {
	
		return qnDao.insertNewQuestion(qna);
	}

	@Override
	public boolean insertNewQuestion(int mbId, String userName, String title, String content, String password,
			int category, boolean secret) {

		return qnDao.insertNewQuestion(mbId, userName, title, content, password, category, secret);
	}

	@Override
	public boolean updateOneQuestion(QuestionVO qna) {
		
		return qnDao.updateOneQuestion(qna);
	}

	@Override
	public boolean updateOneQuestion(int id, String title, String content, String password, int category,
			boolean secret) {
	
		return qnDao.updateOneQuestion(id, title, content, password, category, secret);
	}

	@Override
	public boolean updateAllQuestionByUser(int mbId, String userName) {
		
		return qnDao.updateAllQuestionByUser(mbId, userName);
	}

	@Override
	public boolean updateOneQuestionAnswer(int id) {
		
		return qnDao.updateOneQuestionAnswer(id);
	}

	@Override
	public boolean deleteOneQuestion(int id) {
		
		return qnDao.deleteOneQuestion(id);
	}

	@Override
	public List<QuestionVO> searchAllQuestion(String title) {
		
		return qnDao.searchAllQuestion(title);
	}

	@Override
	public List<QuestionVO> searchAllQuestion(int mbId, String title) {

		return qnDao.searchAllQuestion(mbId, title);
	}

	@Override
	public int checkAllQnACount() {
	
		
		return qnDao.checkAllQnACount();
	}


	@Override
	public int checkAllQnACountByMember(int mbId) {

		return qnDao.checkAllQnACountByMember(mbId);
	}


	@Override
	public int checkAllQnACountByWaitingReply() {
	
		return qnDao.checkWaitingQnACount();
	}

	
	@Override
	public int checkMaxpageNumberByUser(int pgSize, int mbId) {
		
		int totalQnaCnt = qnDao.checkAllQnACountByMember(mbId);
		int maxPg = totalQnaCnt/ PAGE_SIZE + (totalQnaCnt % PAGE_SIZE==0?0:1);
		
		return maxPg;
	}

	//다수개 글아이디 넘어오면 삭제해줘! 
	@Override
	public boolean deleteMyQnA(List<Integer> qnaList) {
		
	if(qnaList!=null) {
			
			for (int i = 0; i < qnaList.size(); i++) {
				
				int qnaId = qnaList.get(i);
				boolean b = qnDao.deleteOneQuestion(qnaId);
				if(b) {
					
					System.out.println(">> 요청 게시글 아이디:"+qnaId+"번 삭제 성공!");
					
				}else {
					System.out.println(">> 요청 게시글 아이디:"+qnaId+"번 삭제 실패!");
					return false;
				}
			}
			return true;
			
		}else {
			
			System.out.println(">> qnaList null..");
			return false;
					
		}
	}

}
