package com.nuriweb.mybom.controller;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.SortControl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuriweb.mybom.MyCode;
import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.dao.inf.IReserveDAO;
import com.nuriweb.mybom.model.dao.inf.IVisitorsDAO;
import com.nuriweb.mybom.model.vo.AdminVO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.model.vo.MemberVO;
import com.nuriweb.mybom.model.vo.QuestionVO;
import com.nuriweb.mybom.model.vo.ReplyVO;
import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.service.inf.IAdminSVC;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.IBoardSVC;
import com.nuriweb.mybom.service.inf.ICenterSVC;
import com.nuriweb.mybom.service.inf.IFileManageSVC;
import com.nuriweb.mybom.service.inf.IMemberSVC;
import com.nuriweb.mybom.service.inf.IQuestionSVC;
import com.nuriweb.mybom.service.inf.IReplySVC;
import com.nuriweb.mybom.service.inf.IReserveSVC;

@Controller
public class AdminController {
	
	@Autowired
	IReserveSVC rsSvc;
	@Autowired
	ICenterSVC ctSvc;
	
	@Autowired
	IBoardSVC bdSvc;
	
	@Autowired
	IFileManageSVC fileSvc;
	
	@Autowired
	IQuestionSVC qnaSvc;
	
	@Autowired
	IReplySVC rpSvc;
	
	
	
	
	@Autowired 
   IMemberSVC mbSvc;

	@Autowired
	IAdminSVC adSvc;
	
	@Autowired
	IVisitorsDAO viDao;

	@Autowired
	IAllNotifiSVC noSvc;
	
	
	@RequestMapping(value ="admin_dashboard.bom" ,method = RequestMethod.GET)
	public ModelAndView adminDashboard() {
		
		System.out.println("Launching Dashboard controller");
		
		List<AdminVO> adList =adSvc.selectAllAdminInfo();
		//List<Integer> yoilList = viDao.
		
		
		System.out.println(viDao+"msgsgsgsgs");
		
//		int today = viDao.selectDailyVisitors();
//		int todaymone =  viDao.selectOneDayBefore();
//		int todaymtwo =  viDao.selectTwoDayBefore();
//		int todaymthree =  viDao.selectThreeDayBefore();
//		int todaymfour =  viDao.selectFourDayBefore();
//		int todaymfive =  viDao.selectFiveDayBefore();
//		int todaymsix =  viDao.selectSixDayBefore();
		int newMember = mbSvc.countNewMember();
		int TotalMember =mbSvc.countTotalMember();
		//int newMembers = adList.get(0).getDailyPages();
		ModelAndView mav = new ModelAndView("admin/admin_dashboard");
		if(adList != null) {
			
			mav.addObject("msg", "어드민 정보 읽어오기 성공");
			mav.addObject("adList", adList);
			mav.addObject("newMember", newMember);
			mav.addObject("totalMember", TotalMember);
//			mav.addObject("today",today);
//			mav.addObject("todaymone",todaymone);
//			mav.addObject("todaymtwo",todaymtwo);
//			mav.addObject("todaymthree",todaymthree);
//			mav.addObject("todaymfour",todaymfour);
//			mav.addObject("todaymfive",todaymfive);
//			mav.addObject("todaymsix",todaymsix);
//			
			
			
		}else {
			
			mav.addObject("msg", "어드민 정보 읽어오기 실패");
		}
		
		return mav;
		
	}



	
//   //test
//      @RequestMapping(value = "admin_member.bom", method = RequestMethod.GET) 
//      public ModelAndView testPage(){
//         
//         ModelAndView mav = new ModelAndView();
//         mav.setViewName("admin/admin_member");
//         return mav;
//      } 
     
//		- 전체 회원 조회  할 수  있다. (어드민)
//		admin_member.bom ; get proc dao 어드민
		@RequestMapping(value = "admin_member.bom", 
				method = RequestMethod.GET)
		public ModelAndView memberListProc(HttpServletRequest request,
				@RequestParam(value = "pg",required = false,defaultValue ="1") int pageNumber){
			System.out.println("MemberListController");
			// no param, session?
			int maxPg = mbSvc.checkMaxpageNumber();
			if( pageNumber <= 0 || pageNumber > maxPg ) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + pageNumber);
				return new ModelAndView("redirect:admin_member.bom?pg=1");
			}
			List<MemberVO> mbList = mbSvc.selectAllMember(pageNumber);
			
			ModelAndView mav = new ModelAndView("admin/admin_member");
			if( mbList != null ) {
				// member_list.my => mb_list.jsp?
				int mbSize = mbList.size();
				mav.addObject("msg", "회원 리스트 조회 성공!! " 
							+ mbSize + "명");			
				mav.addObject("mbList", mbList);
				mav.addObject("mbSize", mbSize);
				mav.addObject("pn", pageNumber);
				mav.addObject("maxPg", maxPg);
			} else {
				mav.addObject("msg", "ERROR: 회원 리스트 조회 실패!!");
				
			
			}
			return mav;
			
		}
//		- 회원 정보를 편집/갱신 할 수 있다.
//		admin_member_update.bom?id=x; get proc dao 세션, 인증, 회원
@RequestMapping(value = "admin_member_update_form.bom", method = RequestMethod.GET)
public ModelAndView memberEditForm(HttpServletRequest request) {
	int id = Integer.parseInt(request.getParameter("mbId")); //3		
	// 원래.. 인증 세션 처리 권한 처리 등. 필요...
	
	// DAO/Model
	MemberVO mb = mbSvc.selectOneMember(id);
	
	// 분기 처리
	ModelAndView mav = new ModelAndView();
	if( mb != null ) {
		
		mav.setViewName("admin/admin_member_update");
		mav.addObject("member", mb);
		mav.addObject("msg", "회원 편집 준비 성공!!");
	} else {
		
		mav.setViewName("redirect:admin_member.bom");
		//mav.setViewName("redirect:member_show.my?mbId="+id);
		mav.addObject("msg", "회원 편집 준비 실패 !! + " + id);
	}
	
	return mav;
}
//			member_update.my ; post proc dao 세션, 인증, 회원
@RequestMapping(value = "admin_member_update.bom", method = RequestMethod.POST)
public ModelAndView memberUpdateProc(HttpServletRequest request) {
	// submit post 파람
	int id = Integer.parseInt(request.getParameter("id")); 		
	// 원래.. 인증 세션 처리 권한 처리 등. 필요...
	String email = request.getParameter("email");
	String nickname = request.getParameter("nickname");
	String password = request.getParameter("password");
	
	String[] pwQuestions= request.getParameterValues("pwQuestion");
	int pwQuestion = Integer.parseInt(pwQuestions[0]);
	String pwAnswer = request.getParameter("pwAnswer");			
	
	MemberVO mb = new MemberVO(id, email, nickname, password, null, null, pwQuestion, pwAnswer);
	System.out.println(mb.toString());
	boolean b = mbSvc.updateOneMember(mb);
	ModelAndView mav = new ModelAndView();
	if( b ) {
		System.out.println(b);
		mav.setViewName("redirect:admin_member.bom");
	} else {
		System.out.println(b);
		mav.addObject("msg", "dao: 회원 정보 갱신 실패!! + " + id);
		mav.setViewName("admin/admin_member_update"); //fw
	}	
	return mav;
}
		
//-회원 관리 화면에서 회원을 탈퇴 시킬 수 있다.
//admin_member_delete.bom?id=x // get proc dao 
@RequestMapping(value = "admin_member_delete.bom", method = RequestMethod.GET) 
public ModelAndView memberDelete(HttpSession ses,
					@RequestParam(value = "mbId")List<Integer> mbList){
	
	ModelAndView mav = new ModelAndView();
	int mbPKId = (int)ses.getAttribute("mbPKId");
	if(mbList!=null) { //체크박스 잘 들어왔나 확인.
				
				for (Integer bd : mbList) {
					System.out.println(">> 넘어온 체크리스트 값: "+bd);
				}
			}
	if(mbList != null ) {
		
		if( mbPKId == 1) {
			
			boolean b = mbSvc.deleteMember(mbList);
			
			if(b) {
				
				System.out.println(">> 해당 게시글 삭제 성공:"+mbList.size()+"개");
				mav.setViewName("redirect:/admin_member.bom");
				
			}else {
				
				System.out.println(">> 해당 게시글 삭제 실패:"+mbList.size()+"개");
				mav.setViewName("redirect:/admin_member.bom");
				mav.addObject("deleteFailMsg", "게시글 삭제에 실패하셨습니다.");
			}
			
		}
	}
	return mav;
}

//-회원 관리 화면에서 검색창을 통해 회원 nickname, email 을 통해서 회원을 검색 할 수 있다.
//admin_member_search.bom?nickname = x //get proc dao  
@RequestMapping(value = "member_search.bom",			
		method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView memberSearchProc(
		@RequestParam(value = "keyword", 
		defaultValue = "test") String k, 
		@RequestParam(value = "target", required = false,
		defaultValue = "nickname") String target,
		@RequestParam(value = "pg", defaultValue = "1", 
		required = false) int pg
		) {
	List<MemberVO> mbList = mbSvc.searchMember(k, target, pg);
	ModelAndView mav = new ModelAndView();
	if( mbList != null ) {
		int mbSize = mbList.size(); 
		mav.addObject("msg", "회원 검색 성공: " + mbSize +"개");
		mav.addObject("mbList", mbList);
		mav.addObject("mbSize", mbSize); // 0개 이상
		// ${pn}, ${param.pg}
		mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
		Map<String,Integer> rMap = mbSvc.checkMaxPageNumber(target,k);
		//System.out.println("ctrl: maxPg = " + maxPg );
		mav.addObject("maxPg", rMap.get("maxPg")); // 최대 페이지 번호
		mav.addObject("mbMaxSize", rMap.get("totalAtCnt")); // 총 검색일치 레코드 개수
		mav.addObject("searchMsg", "게시글 검색 조회: "+mbSize+"개");
		System.out.println("검색 성공 " + mbSize);
		mav.setViewName("admin/admin_member_search");
	} else {
		mav.addObject("msg", "게시글 검색 실패"); // null 
		mav.setViewName("redirect:admin_member.bom");
		System.out.println("검색실패");
	}		
	return mav;
}


	
	
	
	
	
public void normal() {
		
	}
	@RequestMapping(value = "admin_reservation.bom",
			method = RequestMethod.GET)
	public ModelAndView adminReserveList(HttpServletRequest request,
			@RequestParam(value = "pg",required = false,defaultValue ="1") int page) {
		List<ReserveVO> rsList = rsSvc.selectAllReserve(page);
		int maxPg = rsSvc.checkMaxPageNumber();
		System.out.println("\n"+rsList.size()+"\n");
		ModelAndView mav = new ModelAndView();
		for (ReserveVO rs : rsList) {
			rs.setReserveCenterName(ctSvc.getOneCenter(rs.getReserveCenterId()).getName());
		}
		mav.addObject("rsList",rsList);
		mav.addObject("pg",page);
		mav.addObject("search",0);
		mav.addObject("maxPg",maxPg);
		
		mav.setViewName("admin/admin_reservation");
		return mav;
	}
	@RequestMapping(value = "admin_reservation_search.bom",
			method = RequestMethod.GET)
	public ModelAndView adminSearchList(HttpServletRequest request,
			@RequestParam(value = "pg",required = false,defaultValue ="1") int page,
			@RequestParam(value = "visit_checkbox",required = false,defaultValue ="-1") int visit,
			@RequestParam(value = "review_checkbox",required = false,defaultValue ="-1") int review
			) {
		String stId = request.getParameter("search_reserve_code");
		String Nickname = request.getParameter("search_reserve_member_name");
		String CenterName = request.getParameter("search_reserve_center_name");
		String day = request.getParameter("search_reserve_day");
		String stTime = request.getParameter("search_reserve_time");
		int Id =-1;
		int time = -1;
		if(!stId.equals("")&&stId!=null) {
			Id = Integer.parseInt(stId);
		}
		if(!stTime.equals("")&&stTime!=null) {
		time = Integer.parseInt(stTime);
		}
		System.out.println("\n"+Nickname+"\n");
				List<ReserveVO> rsList = rsSvc.getListSearchReserve(
						Id, Nickname, CenterName, day, time, visit, review,page);
				List<ReserveVO> mxrsList = rsSvc.getListSearchReserve(
						Id, Nickname, CenterName, day, time, visit, review);
				int maxPg=0;
				if(mxrsList!=null) {
				if(mxrsList.size()<=0&&mxrsList!=null) {
					maxPg= mxrsList.size()/15;
					if(mxrsList.size()%15 >0) maxPg+=1;
				}
				}
				System.out.println("관리자 maxPg"+maxPg+"리스트사이즈:"+mxrsList.size());
//				List<ReserveVO> rsList = rsSvc.selectAllReserve();
				if(rsList.size()>0)
				System.out.println("12\n"+rsList.size()+"\n");
				ModelAndView mav = new ModelAndView();
				for (ReserveVO rs : rsList) {
					rs.setReserveCenterName(ctSvc.getOneCenter(rs.getReserveCenterId()).getName());
				}
				mav.addObject("rsList",rsList);
				mav.addObject("Nickname",Nickname);
				mav.addObject("CenterName",CenterName);
				mav.addObject("day",day);
				mav.addObject("stTime",stTime);
				mav.addObject("pg",page);
				mav.addObject("maxPg",maxPg);
				mav.addObject("search",1);
				mav.setViewName("admin/admin_reservation");
				return mav;
	}
	
	
	/////보드 
	
	
	//관리자가 누리봄/FAQ 글을 등록할 수 있다.
	//admin_add_new_board.bom post form
		@RequestMapping(value = "admin_add_new_board.bom",method = RequestMethod.GET)
		public ModelAndView adminNewForm() {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/admin_new_form");
			
			
			return mav;
		}

		
	//admin_add_proc.bom post proc dao
		@RequestMapping(value = "admin_add_proc.bom",method = RequestMethod.POST)
		public ModelAndView adminNewFormProc(HttpSession ses,int mbId,String writer,String category,
													String title,String content,MultipartFile upfile,String password) {
			
			ModelAndView mav = new ModelAndView();

			if(mbId!=0 && writer!=null && category!=null && title!=null && content !=null&& password!=null) {
				
				if(mbId==1 && category.equals(bdSvc.category_faq)) {
					
					int returnPk = this.bdSvc.insertNewBoardReturnKeyByThumbnailCategory(bdSvc.FAQ_BOARD, new BoardVO(mbId, writer, title, content, password, ""));
					
					if(returnPk>0) {
						//추가완료
						System.out.println(">> faq게시판 게시글 등록 성공 id: "+returnPk);
						//faq list,show 구현 후 등록시 returnPK 받아 해당 show 페이지로 이동..
						mav.setViewName("redirect:faq_list.bom");
					}else {
						System.out.println(">> faq게시판 게시글 등록 실패");
						mav.addObject("failMsg", "게시글 등록에 실패하였습니다.");
						mav.setViewName("admin/admin_new_form");
					}
					
				}else if(mbId == 1 && category.equals(bdSvc.category_nuri)) {
					
					if(upfile!=null &&!upfile.isEmpty()) {
					
						String realPath = ses.getServletContext().getRealPath(IFileManageSVC.DEF_BD_DEST+"/");
						System.out.println(">> realPath: "+ realPath);
						
						String filePath = fileSvc.writeUploadFile(upfile, realPath,fileSvc.DEF_BD_DEST);
						System.out.println(">> filePath:"+ filePath);
						
						int returnKey = this.bdSvc.insertNewBoardReturnKeyByThumbnailCategory(bdSvc.NURI_BOARD, new BoardVO(mbId, writer, title, content, password, filePath));
						
						if(returnKey >0) {
							System.out.println(">>누리봄 게시판 유저 지정 썸네일 파일 등록 성공 id:"+ returnKey);
							mav.setViewName("redirect:nuri_board_list.bom");
							//썸네일 등록 잘 된 거 보여줄래.. list로 이동.
						}else {
							System.out.println(">>누리봄 게시판 유저 지정 썸네일 파일 등록 실패..");
							mav.addObject("failMsg", "게시글 등록에 실패하였습니다.");
							mav.setViewName("admin/admin_new_form");
						}
					}else {
						//upfile이 null이거나 isEmpty..
						//filePath 경로를 봐서... default img로 썸네일 넣기..
						String defThumbnail = "/resources/images/board/default.png"; //file경로 지정한 default 이미지.
						int returnKey = this.bdSvc.insertNewBoardReturnKeyByThumbnailCategory(bdSvc.NURI_BOARD, new BoardVO(mbId, writer, title, content, password,defThumbnail));
						
						if(returnKey>0) {
							
							System.out.println(">>누리봄 게시판 썸네일 기본 등록 성공 id:"+ returnKey);
							mav.setViewName("redirect:nuri_board_list.bom");
							
						}else {
							
							System.out.println(">>누리봄 게시판 썸네일 기본 등록 실패..");
							mav.addObject("failMsg", "게시글 등록에 실패하였습니다.");
							mav.setViewName("admin/admin_new_form");
						}
					}
				}else {
					
					mav.addObject("failMsg", "없는 카테고리! 게시글 등록에 실패하였습니다.");
					mav.setViewName("admin/admin_new_form");
				}
			}else {
				
				System.out.println(">> 어드민 게시글 등록실패 :: 파람 에러!");
				mav.addObject("failMsg", "게시글 등록에 실패하였습니다.");
				mav.setViewName("admin/admin_new_form");
			}
			return mav;
		}

		
		//페이지 네이션 5개 
		@RequestMapping(value = "admin_qna_none_reply.bom",method = RequestMethod.GET)
		public ModelAndView adminQnAreplyWaiting(@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber) {
			
			
			int maxPg = qnaSvc.checkMaxpageNumberByWaiting(qnaSvc.PAGE_SIZE);
			ModelAndView mav = new ModelAndView();
			
			if(pageNumber<=0 || pageNumber > maxPg) {
				
				System.out.println(">> 어드민 질문 조회 잘못된 페이지번호 요청pg: "+pageNumber);
				mav.setViewName("redirect:admin_qna_none_reply.bom?pg=1");
				
			}
			
			List<QuestionVO> qnaList = qnaSvc.selectAllWaitingQuestion(pageNumber, qnaSvc.PAGE_SIZE);
			int qnaListCnt = qnaSvc.checkAllQnACountByWaitingReply();
			
			if(qnaList!=null) {
				
				mav.addObject("qnaAllCnt", qnaListCnt);
				mav.addObject("qnaList",qnaList);
				mav.addObject("qnaSize",qnaList.size());
				mav.addObject("pn", pageNumber);
				mav.addObject("maxPg", maxPg);
				mav.setViewName("admin/admin_qna_nonreply");
				System.out.println(">>어드민 답변예정 QnA_board maxPg: "+maxPg+ ",페이지 요청 넘버: "+pageNumber);
				
			}else {
				
				System.out.println(">> 어드민 답변예정 qnaList 페이지네이션 게시글 리스트 조회 실패 null 에러..");
			}
			return mav;
		}
		
		//어드민이 질문 답을 입력할 수 있다.  
			@RequestMapping(value = "admin_qna_reply_proc.bom",method = RequestMethod.POST)
			public ModelAndView adminQnAReplyProc(@RequestParam(value = "qnaId")int qnaId,
													@RequestParam(value = "mbId", defaultValue = "1" )int mbId,
																		@RequestParam(value = "content")String content,
																							RedirectAttributes rdAttr) {
				ModelAndView mav = new ModelAndView();
				
				if(qnaId!=0 && mbId!=0 && content!=null) {
					
					if(mbId==1) {
						
						QuestionVO qn = qnaSvc.selectOneQuestion(qnaId);
						
						if(qn!=null) {
							
							if(rpSvc.insertNewReply(rpSvc.QNA_REPLY, new ReplyVO(qn.getId(), mbId,rpSvc.NURINAME, content))) {
								
								if(qnaSvc.updateOneQuestionAnswer(qn.getId())) {
									System.out.println(">> 관리자 덧글 등록 성공:: 회원 질문 상태 성공적으로 업데이트!");
									
									
									boolean b = noSvc.insertNewQuestionNotifi(mbId, "관리자", MyCode.NewQuestionNotifi, "");
									
									if( b) {	
										System.out.println("관리자 답글 알림 쏴주기 완료");
									}else {
										
										System.out.println("관리자 답글 알림 쏴주기 실패.");
									}
									
									
								}else {
									System.out.println(">> 관리자 덧글 등록 성공:: 회원 질문 상태 업데이트 실패..");
								}
							}else {
								System.out.println(">> 관리자 덧글 등록 실패.. 질문 아이디:"+ qn.getId());
								rdAttr.addFlashAttribute("qnaReilyMsg","덧글 등록에 실패했습니다.");
								
							}	
						}else {
							System.out.println(">> 해당 질문 조회 실패 질문 아이디: "+ qnaId);
							rdAttr.addFlashAttribute("qnaReilyMsg","해당 질문을 찾을 수 없습니다.");
						}
					}
				}else {
					
					System.out.println(">> 파람 오류 에러..");
					rdAttr.addFlashAttribute("qnaReilyMsg","파람에러 당장 에러 수정!");
				}
				
				mav.setViewName("redirect:admin_qna_none_reply.bom");
			return mav;
			}
			
			
			
			// 

			
					
			
				
				
		
	
}
