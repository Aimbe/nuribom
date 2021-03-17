package com.nuriweb.mybom.model.dao.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.model.vo.QuestionVO;

public interface IQuestionDAO {


//	1-1.특정 회원의 질문글을 전체조회할 수 있다.
	List<QuestionVO> selectAllQuestionByUser(int mbId,int offset,int limit);

//	1-2.특정 회원의 질문글을 전체조회할 수 있다. <UQ>
	List<QuestionVO> selectAllQuestionByUser(String userName);

	
//  1-3.관리자가 답변예정인 글을 전체조회할 수 있다.
	List<QuestionVO> selectAllWaitingQuestion();
	
	
//	2-1.조회한 글의 제목을 클릭시 드롭다운으로 질문 내용을 볼 수 있다.
//	3.비밀글의 경우 해당 질문 회원만 내용을 조회할 수 있다. 
	QuestionVO selectOneQuestion(int id);
	
//	4.QnA 작성 버튼을 클릭하여 질문을 작성할 수 있다.
	boolean insertNewQuestion(QuestionVO qna);	
	boolean insertNewQuestion(int mbId, String userName, String title,
			String content, String password, int category,boolean secret);	

//	4-1.해당 글 회원은 답변 예정 상태에만 작성 시 적은 비밀번호로 질문을 수정할 수 있다. 
	boolean updateOneQuestion(QuestionVO qna);
	boolean updateOneQuestion(int id,String title,String content,String password,int category,boolean secret);
	boolean updateAllQuestionByUser(int mbId,String userName);

	
//  어드민이 답글을 달았을 경우 답 유무 업데이트 
	boolean updateOneQuestionAnswer(int id);
	
	
//	4-2.해당 글 회원은 자신의 질문을 삭제할 수 있다.
	boolean deleteOneQuestion(int id);

//	5.상단 검색 필드를 사용하여 제목으로 질문글을 검색할 수 있다.
	List<QuestionVO> searchAllQuestion(String title);

//  5-1.상단 검색 필드를 사용하여 해당 회원이 쓴 질문글을 제목으로 검색할 수 있다.
	List<QuestionVO> searchAllQuestion(int mbId,String title);
	
//  페이지네이션이 들어간 전체 질문글 조회 
	List<QuestionVO> selectAllQuestion(int offset,int limit); //pg
	List<QuestionVO> selectAllWaitingQuestion(int offset,int limit);
	
//  전체글 카운트
	int checkAllQnACount();
//  특정 회원용 전체글 카운트
	int checkAllQnACountByMember(int mbId);

//  답변예정글 카운트 
	int checkWaitingQnACount();
	
}
