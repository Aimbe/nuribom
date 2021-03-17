package com.nuriweb.mybom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuriweb.mybom.Materials;
import com.nuriweb.mybom.MyCode;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.QuestionVO;
import com.nuriweb.mybom.model.vo.ReplyVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.IQuestionSVC;
import com.nuriweb.mybom.service.inf.IReplySVC;

@Controller
public class QuestionController {

	
	@Autowired
	IQuestionSVC qnaSvc;
	
	@Autowired
	IReplySVC rpSvc;
	
	@Autowired
	IAllNotifiSVC noSvc;
	
//	-전체 질문글 리스트를 조회할 수 있다. (비밀글 경우 해당 회원만 확인가능!)
//	question_list.bom // get dao 
//	question_list.bom?pg=x //get proc pagenation dao 
	@RequestMapping(value = "question_list.bom", method = RequestMethod.GET) 
	public ModelAndView qnaBoardList(@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber){
		
		int maxPg = qnaSvc.checkMaxpageNumber(qnaSvc.PAGE_SIZE);
		if(maxPg == 0) {
			maxPg = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_QNA);
		
		if(pageNumber<=0 || pageNumber > maxPg) {
			
			System.out.println(">> 잘못된 페이지번호 요청 pg: "+pageNumber);
			mav.setViewName("redirect:question_list.bom?pg=1");
		}
		
		List<QuestionVO> qnaList = qnaSvc.selectAllQuestion(pageNumber, qnaSvc.PAGE_SIZE);
		int qnaListCnt = qnaSvc.checkAllQnACount();
		List<ReplyVO> rpList = new ArrayList<ReplyVO>();
		if(qnaList!=null) {
			
			
			for (QuestionVO qna : qnaList) {
				
				
				ReplyVO rp = rpSvc.selectOneReplyByQnA(qna.getId());
				System.out.println(">>질문게시판 덧글 조회 rp: "+ rp);
				rpList.add(rp);
				
			}
			
			
			mav.addObject("rpList", rpList);
			mav.addObject("qnaAllCnt", qnaListCnt);
			mav.addObject("qnaList",qnaList);
			mav.addObject("qnaSize",qnaList.size());
			mav.addObject("pn", pageNumber);
			mav.addObject("maxPg", maxPg);
			mav.setViewName("board/qna_bd_list");
			System.out.println(">>QnA_board maxPg: "+maxPg+ ",페이지 요청 넘버: "+pageNumber);
		
		}else {
			
			System.out.println(">> qnaList 페이지네이션 게시글 리스트 조회 실패 null 에러..");
			mav.setViewName("board/qna_bd_list");
			
		}
		
		return mav;
	} 
	
//	-QnA 작성 버튼을 클릭하여 질문을 작성할 수 있다.
//	question_new_edit_form.bom //get form 세션 회원 필요
//	question_edit.bom //get proc dao 
	@RequestMapping(value = "question_new_edit_form.bom", method = RequestMethod.GET) 
	public ModelAndView qnaBoardNewForm(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_QNA);
		mav.setViewName("board/qna_edit_form");
		return mav;
	} 
	
	
	@RequestMapping(value = "question_edit.bom", method = RequestMethod.GET) 
	public ModelAndView qnaBoardNewFormProc(HttpSession ses, String title,String content,
												int category,String password,int mbId,String writer,
														@RequestParam(value ="secret",defaultValue ="1")int secret){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_QNA);
		
		int mbPKId = (int)ses.getAttribute("mbPKId");
		
		if(title!=null && category!=0 && password != null && mbId != 0 && writer!=null && content!=null) {
			
			if(mbPKId == mbId) {
				
				boolean b = this.qnaSvc.insertNewQuestion(mbId, writer, title, content, password, category, secret==1?true:false);
				
				if(b) {
					
					System.out.println(">> 질문게시판 질문 등록 성공!");
					mav.setViewName("redirect:question_list.bom");
					
					boolean c = noSvc.insertNewPostNotifi(mbId, "질문 게시판",MyCode.NewPostNotifi,"board/question_list.bom");
					List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
					ses.setAttribute("noList", noList2);
					
					if(c) {
						System.out.println("알림 쏴주기 완료");
					}else {
						
						System.out.println("알림 쏴주기 실패.");
					}
					
				}else {
					
					System.out.println(">> 질문게시판 질문 등록 실패");
					mav.addObject("qnaFailMsg", "질문 등록에 실패하였습니다");
					mav.setViewName("board/qna_edit_form");
				}
			}
		}else {
			//하나라도 null 이라면..
			System.out.println(">> 질문게시판 질문 등록 실패");
			mav.addObject("qnaFailMsg", "질문 등록에 실패하였습니다");
			mav.setViewName("board/qna_edit_form");
		}
		
		
		return mav;
	} 
	
	
	
	
	
//	-해당 글 회원은 답변 예정 상태에만 작성 시 적은 비밀번호로 질문을 수정할 수 있다.
//	question_check_pw_form.bom? id=x //post form 세션회원 필요
//	question_check_proc.bom?id = x &check_pw =x //post proc dao 
	@RequestMapping(value = "question_check_pw_form.bom", method = RequestMethod.POST) 
	public ModelAndView qnaCheckPw(HttpSession ses,@ModelAttribute(value="question") QuestionVO qna){
		
		ModelAndView mav = new ModelAndView();
		// 마이 페이지용..mav.addObject("subCategory",Materials.SUB_CATEGORY_QNA);
		
		int mbid = (int)ses.getAttribute("mbPKId");
		
		
		if(qna!=null) {
			
			System.out.println("qna: "+ qna.toString());
			mav.setViewName("board/qna_check_pw");
			
		}else {
			
			mav.setViewName("redirect:member_qna.bom?mbId"+mbid);
			
		}
		return mav;
	} 

	@RequestMapping(value = "question_check_proc.bom", method = RequestMethod.POST) 
	public ModelAndView qnaCheckPwProc(HttpSession ses,@ModelAttribute(value="question") QuestionVO qna,String checkPw){
		
		ModelAndView mav = new ModelAndView();
		
		
		if(qna!=null && checkPw != null) {
			
			if(qna.getPassword().equals(checkPw)) {
			
			System.out.println("요청 파람: " + qna.toString() );
			mav.addObject("question",qna);
			mav.setViewName("board/qna_update_form");
			
			}else {
				
				mav.setViewName("board/qna_check_pw");
				mav.addObject("failPw", "해당 게시글 비밀번호와 입력 비밀번호가 불일치 합니다.");
				System.out.println(">> 수정 비밀번호 비일치 게시글 수정 비밀번호: "+qna.getPassword()+" 회원 입력 비밀번호: "+ checkPw);
			
			}
		}else{
			
			mav.setViewName("board/qna_check_pw");
			mav.addObject("failPw", "게시글 수정 비밀번호 체크 파람 오류!");
			
		}
		return mav;
	} 	
	
	
	
	
//	question_update_form.bom? id =x //get form 세션 회원 필요
//	question_update.bom //post proc dao  세션 회원 필요
//	마이페이지 수정 후 해당 회원 마이페이지 질문게시판 이동.
	@RequestMapping(value = "question_update_form.bom", method = RequestMethod.GET) 
	public ModelAndView qnaUpdateForm(HttpSession ses,
				@ModelAttribute(value="question") QuestionVO qna){
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("subCategory",Materials.SUB_CATEGORY_QNA);
		//마이페이지용..
		int mbid = (int)ses.getAttribute("mbPKId");
		System.out.println(">> 파람 넘어온 qna: "+qna);
		if(qna.getId()==0) {
			
			mav.setViewName("redirect:member_qna.bom?mbId"+mbid);
			System.out.println(">> 요청한 아이디를 가진 게시글이 없습니다.");
			return mav;
		}
		
		if(qna!=null) {
		
			QuestionVO updateQna = qnaSvc.selectOneQuestion(qna.getId());
			
			if(updateQna!=null) {
				
				if(mbid == updateQna.getMbId()) {
					
					System.out.println("요청한 글의 작성자(id:"+mbid+")와 로그인 회원(id:"+updateQna.getMbId()+")가 일치합니다! ");
					mav.addObject("question",updateQna);
					mav.setViewName("board/qna_update_form");
					return mav;
				}else {
					//아이디 같지않을 때..
					System.out.println(">> 요청한 글의 작성자(id)와 로그인 회원의 id가 일치하지 않습니다.");
					mav.setViewName("redirect:member_qna.bom?mbId"+mbid);
				}
				
			}else {
			  //하나의 질문뽑기 실패했을 때.
				mav.setViewName("redirect:member_qna.bom?mbId"+mbid);
				System.out.println(">> 해당 질문 편집 폼을 준비하지 못했습니다: "+qna.getId());

				return mav;
				}
		}
			//qna가 제대로 안들어왔을 때.
			System.out.println(">> 업데이트 폼 준비 실패 :: 파람 에러");
			mav.setViewName("redirect:member_qna.bom?mbId"+mbid);

		
		return mav;
	} 
	
	
	@RequestMapping(value = "question_update.bom", method = RequestMethod.POST) 
	public ModelAndView qnaUpdateProc(HttpSession ses,int mbId,int id,String title,
											int category,String content,String password,
												@RequestParam(value ="secret",defaultValue ="1")int secret){
		
		int mbPKId = (int)ses.getAttribute("mbPKId");
		ModelAndView mav = new ModelAndView();
		//mav.addObject("subCategory",Materials.SUB_CATEGORY_QNA);
		//마이페이지용..
		if(mbPKId != mbId) {
			
			System.out.println(">> 요청한 글의 작성자(id)와 로그인 회원의 id가 일치하지 않습니다! 질문 갱신 실패!");
			mav.setViewName("redirect:member_qna.bom?mbId"+mbPKId);
			
		}
		if(id != 0 && title!=null && category!=0 && content!=null && password!=null) {
		
			System.out.println(">> 업데이트 들어감!" );
			boolean b = qnaSvc.updateOneQuestion(id, title, content, password, category, secret==1?true:false);
		
			if(b) {
				
				System.out.println(">> 해당 질문 업데이트 성공: "+ id);
				mav.setViewName("redirect:member_qna.bom?mbId"+mbPKId);
			
			}else {
				
				System.out.println(">> 해당 질문 업데이트 실패: "+ id);
				mav.setViewName("board/qna_update_form");
				mav.addObject("updateMsg","질문 수정에 실패하였습니다.");
			}
			
			
		}else {
		System.out.println("id:"+id+"  title: "+title+" category:"+category+" content:"+content+" password:"+password+" secret:"+secret);
		System.out.println(">>업데이트 파람 null 에러..");
		mav.setViewName("board/qna_update_form");
		mav.addObject("updateMsg","파람에러:: 질문 수정 실패 관리자에게 문의해주세요.");
		}
		return mav;
	} 
	
	
	
	
//	-해당 글 회원은 자신의 질문을 삭제할 수 있다. 마이페이지!
//	question_delete.bom//get proc dao 세션 회원 필요
//	마이페이지 내 질문게시판에서 삭제 후 해당 마이페이지 질문게시판으로 이동.
	@RequestMapping(value = "question_delete.bom", method = RequestMethod.GET) 
	public ModelAndView qnaDelete(HttpSession ses,
							@RequestParam(value = "qnaId")List<Integer> qnaList,
									int mbId){
		
		ModelAndView mav = new ModelAndView();
		int mbPKId = (int)ses.getAttribute("mbPKId");
		
		
		if(qnaList!=null) {
			
			for (Integer qna : qnaList) {
				System.out.println(">> 넘어온 체크리스트 값: "+qna);
			}
			
		}
		if(qnaList!= null && mbId !=0) {
			
			if(mbPKId == mbId) {
				
				for (Integer qnaId : qnaList) {
					
					if(rpSvc.deleteAllReplyByBoard(rpSvc.QNA_REPLY, qnaId)) {
						System.out.println(" >> 해당 질문 덧글 삭제 완료");
					}
				}
				
				
				
				boolean b = qnaSvc.deleteMyQnA(qnaList);
				if(b) {
					
					System.out.println(">> 해당 게시글 삭제 성공:"+qnaList.size()+"개");
					mav.setViewName("redirect:member_qna.bom");
					
				}else {
					
					System.out.println(">> 해당 게시글 삭제 실패:"+qnaList.size()+"개");
					mav.setViewName("redirect:member_qna.bom");
				}
				
				
			}
		}else {
			//null..
			System.out.println(">> 파람 오류! qnaList, mbId 확인 요망");
			mav.setViewName("redirect:member_qna.bom");
		}
		
		return mav;
	} 
	
	
	



}//class
