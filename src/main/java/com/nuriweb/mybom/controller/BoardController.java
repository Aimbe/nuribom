package com.nuriweb.mybom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.nuriweb.mybom.model.dao.inf.IReplyDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.model.vo.QuestionVO;
import com.nuriweb.mybom.model.vo.ReplyVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.IBoardSVC;
import com.nuriweb.mybom.service.inf.IFileManageSVC;
import com.nuriweb.mybom.service.inf.IQuestionSVC;
import com.nuriweb.mybom.service.inf.IReplySVC;


@Controller
public class BoardController {

	@Autowired
	IBoardSVC bdSvc;
	@Autowired
	IFileManageSVC fileSve;
	@Autowired
	IReplySVC rpSvc;
	
	@Autowired
	IAllNotifiSVC noSvc;
	
	//자유게시판 전체 글을 조회할 수 있다.
	//free_board_list.bom // get dao 
	//board_list.bom?pg=x //get pagenation dao 
	@RequestMapping(value = "free_board_list.bom", method =RequestMethod.GET) 
	public ModelAndView freeBoardList(@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber){
											
											//테이블명,페이지마다 뜰 레코드 수 
		int maxPg = bdSvc.checkMaxpageNumber(bdSvc.FREE_BOARD, bdSvc.PAGE_SIZE);
		if(maxPg == 0) {
			maxPg = 1;
		}
		//pageNumber가 범위에 맞지 않게 왔을 때
		if(pageNumber<=0 || pageNumber > maxPg) {
			
			System.out.println(">> 잘못된 페이지번호 요청 pg: "+pageNumber);
			return new ModelAndView("redirect:free_board_list.bom?pg=1");
			
		}
		
		List<BoardVO> bdList = bdSvc.selectAllBoards(bdSvc.FREE_BOARD,pageNumber, bdSvc.PAGE_SIZE);
		int bdListCnt = bdSvc.checkAllBoardCount(bdSvc.FREE_BOARD);
		
		ModelAndView mav = new ModelAndView("board/free_bd_list");
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		if(bdList!=null) {
			
			//덧글개수를 포함하기 위해 사용.
			List<Integer> rpCntList = new ArrayList<Integer>();
			
			for (BoardVO bd : bdList) {
				
				int rpCntForBd = rpSvc.checkReplyCountForBoard(rpSvc.FREE_REPLY, bd.getId());
				rpCntList.add(rpCntForBd);
				
			}
			
			
			mav.addObject("bdAllCnt", bdListCnt);
			mav.addObject("rpCntList", rpCntList);
			mav.addObject("bdList", bdList);
			mav.addObject("bdSize", bdList.size());
			mav.addObject("pn",pageNumber); //요청으로 활성화된 페이지 번호
			mav.addObject("maxPg", maxPg); //최대 페이지 번호
			System.out.println(">>Free_board maxPg: "+maxPg);
			
		}
		
		return mav;
		
	} 
	
	//자유게시판 글을 클릭하여 내용을 조회할 수 있다.
	//board_show_page.bom get dao 
	//board_show.bom?id=x // get proc dao
	@RequestMapping(value = "free_board_show_page.bom", method = RequestMethod.GET) 
	public ModelAndView freeBoardShow(int bdId,
			@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber,
																	RedirectAttributes rdAttr){
		
		BoardVO bd = this.bdSvc.selectOneBoard(bdSvc.FREE_BOARD, bdId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		
		if(bd!=null) {
			
			mav.addObject("board", bd);
			mav.addObject("pn", pageNumber);
			System.out.println(pageNumber);
			mav.setViewName("board/free_bd_show");
			
			
		}else {
			
			rdAttr.addFlashAttribute("noneMsg", "해당 게시글이 없습니다!");
			mav.setViewName("redirect:free_board_list.bom");
			
		}
		
		return mav;
		
	}
	
	
	//-회원은 작성 버튼 클릭으로 글을 쓸 수 있다.  
	//board_new_edit_form.bom //get form 세션 회원 필요
	
	@RequestMapping(value = "free_board_new_edit_form.bom", method = RequestMethod.GET) 
	public ModelAndView freeNeweditForm(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		mav.setViewName("board/free_bd_new_form");
		return mav;
	} 
	
	//board_edit.bom //get proc dao
	@RequestMapping(value = "free_board_edit.bom", method = RequestMethod.POST) 
	public ModelAndView freeNewEditProc(HttpSession ses,String title,String content,String thumbnail,String password,int mbId,String writer){
		
		int mbPKId = (int)ses.getAttribute("mbPKId");
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		
		
		if(title!=null&&content!= null && thumbnail != null &&password!=null &&mbId!= 0 &&writer!=null) {

			if(mbPKId == mbId) {
	
				int returnPk = this.bdSvc.insertNewBoardReturnKeyByThumbnailCategory(bdSvc.FREE_BOARD,new BoardVO(mbId, writer, title, content, password, thumbnail));
		
				if(returnPk >0) {
					//알림도 쏴줘야대!! 
					System.out.println("자유게시판 게시글 등록 성공 id :"+returnPk);
					mav.setViewName("redirect:free_board_show_page.bom?bdId="+returnPk);
					
					
					
					boolean b = noSvc.insertNewPostNotifi(mbId, writer,MyCode.NewPostNotifi,"free_board_show_page.bom?bdId="+returnPk);
					List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
					ses.setAttribute("noList", noList2);
					
					if( b) {
						System.out.println("알림 쏴주기 완료");
					}else {
						
						System.out.println("알림 쏴주기 실패.");
					}
					
					//상세페이지 연동시 거기로 바꿔주기 
					
				}else {
					
					System.out.println("자유게시판 게시글 등록 실패 ");
					mav.addObject("freeMsg", "게시글 등록에 실패하였습니다.");
					mav.setViewName("board/free_bd_new_form"); 
				}
			}
		}else{
			
			System.out.println("자유게시판 게시글 등록 실패 param null..");
			mav.addObject("freeMsg", "게시글 등록에 실패하였습니다.");
			mav.setViewName("board/free_bd_new_form");
	
		}//null 체크
		//파람으로 세팅처리 해보기
		return mav;
	} 
	
	
	//-글을 작성한 회원은 게시글을 수정할 수 있다. (패스워드 확인 폼)
		//board_check_pw_form.bom? id=x //post  세션회원 필요
		//board_update.bom?id = x &check_pw =x //post proc dao 
		@RequestMapping(value = "board_check_pw_form.bom", method = RequestMethod.POST) 
		public ModelAndView freeBoardUpdateCheckByPw(HttpSession ses,
															@ModelAttribute(value="board") BoardVO bd){
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
			
			if(bd!=null) {
				
				System.out.println("요청 파람: " + bd.getId()+" | "+ bd.getMbId()+" | "+bd.getTitle()+" | "+bd.getContent()+" | "+bd.getPassword()+" | "+bd.getCreatedAt());
				mav.setViewName("board/free_bd_check_pw_form");
				
			}
			
			return mav;
		} 

		@RequestMapping(value = "board_update.bom", method = RequestMethod.POST) 
		public ModelAndView freeBoardUpdateCheckPwProc(HttpSession ses,
															@ModelAttribute(value="board") BoardVO bd,String checkPw){
			
			ModelAndView mav = new ModelAndView();
			
			
			if(bd!=null && checkPw != null) {
				
				if(bd.getPassword().equals(checkPw)) {
				
				System.out.println("요청 파람: " + bd.getId()+" | "+ bd.getMbId()+" | "+bd.getTitle()+" | "+bd.getContent()+" | "+bd.getPassword()+" | "+bd.getCreatedAt());
				mav.addObject("board",bd);
				mav.setViewName("board/free_bd_update_form");
				
				}else {
					
					mav.setViewName("board/free_bd_check_pw_form");
					mav.addObject("failPw", "해당 게시글 비밀번호와 입력 비밀번호가 불일치 합니다.");
					System.out.println(">> 수정 비밀번호 비일치 게시글 수정 비밀번호: "+bd.getPassword()+" 회원 입력 비밀번호: "+ checkPw);
				
				}
			}else{
				
				mav.setViewName("board/free_bd_check_pw_form");
				mav.addObject("failPw", "게시글 수정 비밀번호 체크 파람 오류!");
				
			}
			return mav;
		} 
		
		
		
	
	//-글을 작성한 회원은 게시글을 수정할 수 있다.
	//board_update_form.bom? id =x //post form 세션 회원 필요
	//board_update.bom //get proc dao  
	@RequestMapping(value = "free_board_update_form.bom", method = RequestMethod.POST) 
	public ModelAndView freeBoardUpdate(HttpSession ses,
								@ModelAttribute(value="board") BoardVO bd){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		if(bd.getId() == 0) {
			
			mav.setViewName("redirect:free_board_list.bom");
			System.out.println(">> 요청한 아이디를 가진 게시글이 없습니다.");
			return mav;
			
		}
		
		BoardVO updateBd = bdSvc.selectOneBoard(bdSvc.FREE_BOARD, bd.getId());
		int mbPKId = (int)ses.getAttribute("mbPKId");
		
		if(updateBd != null) {
			
			if(mbPKId == updateBd.getMbId()) {
				
				System.out.println(updateBd.getContent()); //내용 잘 담겼나 확인...^^...
				System.out.println("요청한 글의 작성자(id:"+mbPKId+")와 로그인 회원(id:"+updateBd.getMbId()+")가 일치합니다! ");
				mav.addObject("board", updateBd);
				mav.setViewName("board/free_bd_update_form");
				return mav;
			
			}else {
				
				System.out.println(">> 요청한 글의 작성자(id)와 로그인 회원의 id가 일치하지 않습니다.");
				mav.setViewName("redirect:free_board_show_page.bom?bdId="+bd.getId());
				return mav;
			}
		}else {
			//bd null..
			mav.setViewName("redirect:free_board_show_page.bom?bdId="+bd.getId());
			System.out.println(">> 해당 게시글 편집 폼을 준비하지 못했습니다: "+bd.getId());
			return mav;
		}
	} 
	
	
	@RequestMapping(value = "free_board_update_proc.bom", method = RequestMethod.POST) 
	public ModelAndView freeBoardUpdateProc(HttpSession ses,
								@ModelAttribute(value="board") BoardVO bd){
		
		int mbPKId = (int)ses.getAttribute("mbPKId");
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		
		if(mbPKId != bd.getMbId()) {
			
			System.out.println(">> 요청한 글의 작성자(id)와 로그인 회원의 id가 일치하지 않습니다! 게시글 갱신 실패!");
			mav.setViewName("redirect:free_board_show_page.bom?bdId="+bd.getId());
		}
		
		boolean b = bdSvc.updateOneBoard(bdSvc.FREE_BOARD, bd.getId(), bd.getTitle(), bd.getContent(),bd.getPassword());
		
		if(b) {
		
			System.out.println(">> 해당 게시글 업데이트 성공: "+ bd.getId());
			mav.setViewName("redirect:free_board_show_page.bom?bdId="+bd.getId());
		
		}else {
			
			System.out.println(">> 해당 게시글 업데이트 실패: "+ bd.getId());
			mav.setViewName("board/free_bd_update_form");
		}
		
		return mav;
		
	} 
	
//	-글을 작성한 회원은 게시글을 삭제할 수 있다. + 관리자
//	board_delete.bom //get proc dao 세션 회원 필요,관리자 (관리자는 삭제시 알림 푸쉬!)
//	덧글도 같이 삭제해주기!
//	삭제 후 board_list.bom?pg=1 로 이동.
	@RequestMapping(value = "board_delete.bom", method = RequestMethod.GET) 
	public ModelAndView freeBoardDelete(HttpSession ses,int bdId,int mbId){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		
		int mbPKId = (int)ses.getAttribute("mbPKId");
		
		if(bdId != 0 && mbId !=0) {
			
				System.out.println(">> 삭제 요청 게시글 아이디:"+bdId);
				System.out.println(">> mbId: "+ mbId +"  mbPKId: "+mbPKId);
			//로그인 회원의 아이디가 글쓴이 회원의 아이디와 같거나 로그인 중인 회원이 어드민이라면..
			if(mbPKId == mbId || mbPKId == 1) {
				
				List<ReplyVO> rpList = rpSvc.selectAllReply(rpSvc.FREE_REPLY, bdId);
				
				if(rpList!=null) {
					for (ReplyVO rp : rpList) {
						
						rpSvc.deleteOneReply(rpSvc.FREE_REPLY, rp.getId());
						System.out.println(">> 해당 게시글 덧글먼저 삭제:"+ rp.getId());
					}
				}
				
				boolean b = bdSvc.deleteOneBoard(bdSvc.FREE_BOARD, bdId);
				
				if(b) {
					
					System.out.println(">> 해당 게시글 삭제 성공: "+ bdId);
					mav.setViewName("redirect:free_board_list.bom?pg=1");
				
				}else {
					
					System.out.println(">> 해당 게시글 삭제 실패: "+ bdId);
					mav.setViewName("redirect:free_board_show_page.bom?bdId="+bdId);
				}
			}else {
				
				System.out.println(">> 로그인 아이디와 글쓴이 아이디가 일치하지 않습니다.");
				mav.setViewName("redirect:free_board_show_page.bom?bdId="+bdId);
			}
			
		
		}else {
			//bdId, mbId 둘 다 null 일 때..
			System.out.println(">> 파람 오류. bdId MbId null..");
			mav.setViewName("redirect:free_board_list.bom?pg=1");
		}
		
		
		return mav;
	} 	
	
	
	
//	-글을 작성한 회원은 게시글을 삭제할 수 있다. + 관리자 //마이페이지
//	board_delete.bom //get proc dao 세션 회원 필요,관리자 (관리자는 삭제시 알림 푸쉬!)
//	덧글도 같이 삭제해주기!
//	삭제 후 board_list.bom?pg=1 로 이동.
	@RequestMapping(value = "board_delete_My.bom", method = RequestMethod.GET) 
	public ModelAndView freeBoardDeleteMypage(HttpSession ses,
						@RequestParam(value = "bdId")List<Integer> bdList,
								int mbId,
									RedirectAttributes rdAttr){
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		//마이페이지용으로 처리
		
		int mbPKId = (int)ses.getAttribute("mbPKId");
		
		if(bdList!=null) { //체크박스 잘 들어왔나 확인.
			
			for (Integer bd : bdList) {
				System.out.println(">> 넘어온 체크리스트 값: "+bd);
			}
		}
		
		if(bdList!= null && mbId !=0) {
			
			if(mbPKId == mbId || mbPKId == 1) {
				
				
				for (Integer bd : bdList) {
					
				   if(rpSvc.deleteAllReplyByBoard(rpSvc.FREE_REPLY,bd)) {
					   System.out.println(">> 해당 게시글 덧글 다중 삭제 완료: "+ bd+"번");
				   }
				}
				
				
				boolean b = bdSvc.deleteMyBoard(bdList);
				
				if(b) {
					
					System.out.println(">> 해당 게시글 삭제 성공:"+bdList.size()+"개");
					mav.setViewName("redirect:member_free.bom");
					
				}else {
					
					System.out.println(">> 해당 게시글 삭제 실패:"+bdList.size()+"개");
					mav.setViewName("redirect:member_free.bom");
					rdAttr.addFlashAttribute("deleteFailMsg", "게시글 삭제에 실패하셨습니다.");
				}
				
				
			}
		}else {
			//null..
			System.out.println(">> 파람 오류! bdList, mbId 확인 요망");
			rdAttr.addFlashAttribute("deleteFailMsg", "게시글 삭제에 실패하셨습니다. 파람에러");
			mav.setViewName("redirect:member_free.bom");
		}
		

		return mav;
	} 
	
	
	
	
	
	

//-게시판 글을 제목으로 검색하여 조회 할 수 있다. 
//board_search_result.bom //post dao 
//board_search.bom?게시판명_search = x //post proc dao
//show 페이지 복붙해서..
@RequestMapping(value = "free_board_search_proc.bom", method = {RequestMethod.POST,RequestMethod.GET}) 
public ModelAndView freeBoardShearchProc(@RequestParam(value="search")String title,
										@RequestParam(value="pg",defaultValue = "1",required = false)int pg){
	
	
	ModelAndView mav = new ModelAndView();
	mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
	
	
	if(title!=null && !title.isEmpty()) {
		
		System.out.println(">> 들어온 타이틀 검색 키워드: "+ title);
		List<BoardVO> bdList = bdSvc.searchAllBoards(bdSvc.FREE_BOARD, title, pg);	
		
		if(bdList!=null) {
			
			
			int bdSize = bdList.size(); 
			mav.addObject("bdList", bdList);
			mav.addObject("bdSize", bdSize);
			mav.addObject("searchMsg", "게시글 검색 조회: "+bdSize+"개");
			System.out.println(">> 게시글 검색 조회 성공! 제목 키워드: "+title+", 개수"+bdSize+"개");
			mav.addObject("pn",pg);
			Map<String, Integer> rMap = bdSvc.checkMaxPageNumberForSearch(bdSvc.FREE_BOARD, title);
			mav.addObject("maxPg",rMap.get("maxPg"));
			mav.addObject("bdMaxSize",rMap.get("totalBdCnt"));
			mav.setViewName("board/free_bd_search_list");
			
		}else {
			
			System.out.println(">>bdList null 에러..");
			mav.setViewName("redirect:free_board_list.bom");
		}
		
	}else {
		
		System.out.println(">> 타이틀 검색 키워드 null 에러!");
		mav.setViewName("redirect:free_board_list.bom");
	}
	
	
	return mav;
} 
	
//★★★★★★★★★★★★faq★★★★★★★★★★★★★
	
//	- 누리봄의 자주 받는 질문 리스트를 조회할 수 있다.
//	faq_list.bom // get dao 
//	faq_list.bom?pg=x //get proc pagenation dao 
	@RequestMapping(value = "faq_list.bom", method = RequestMethod.GET) 
	public ModelAndView faqBoardList(@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber){
		
		
		int maxPg = bdSvc.checkMaxpageNumber(bdSvc.FAQ_BOARD,bdSvc.PAGE_SIZE);
		if(maxPg == 0) {
			maxPg = 1;
		}
		if(pageNumber<=0 || pageNumber > maxPg) {
			
			System.out.println(">> 잘못된 페이지번호 요청 nuri_pg:"+pageNumber);
			return new ModelAndView("redirect:faq_list.bom?pg=1");
		}
		
		List<BoardVO> bdList = bdSvc.selectAllBoards(bdSvc.FAQ_BOARD, pageNumber,bdSvc.PAGE_SIZE);
		int bdListCnt = bdSvc.checkAllBoardCount(bdSvc.FAQ_BOARD);
		
		
		ModelAndView mav = new ModelAndView("board/faq_bd_list");
		mav.addObject("subCategory",Materials.SUB_CATEGORY_FAQ);
		
		if(bdList!= null) {
			mav.addObject("bdAllcnt",bdListCnt);
			mav.addObject("bdList", bdList);
			mav.addObject("bdSize", bdList.size());
			mav.addObject("pn",pageNumber);
			mav.addObject("maxPg", maxPg);
			System.out.println(">>faq_board maxPg:"+maxPg);
		}
		
		return mav;
	} 
	
	
//	- 게시글을 클릭하여 디테일 페이지로 이동, 게시글의 내용을 조회할 수 있다.
//	faq_show.bom?id=x // get proc dao
//	faq_show_page.bom//post dao 
	@RequestMapping(value = "faq_show_page.bom", method = RequestMethod.GET) 
	public ModelAndView faqBoardshow(int bdId,
									@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber,
											RedirectAttributes rdAttr){
		
		
		
		ModelAndView mav = new ModelAndView();
		if(bdId!=0) {
			
			BoardVO bd = this.bdSvc.selectOneBoard(bdSvc.FAQ_BOARD,bdId);
			mav.addObject("subCategory",Materials.SUB_CATEGORY_FAQ);
						
			if(bd!=null) {
				
				mav.addObject("board", bd);
				mav.addObject("pn", pageNumber);
				System.out.println(">> faq_show pageNumber: "+pageNumber);
				mav.setViewName("board/faq_bd_show");
			
			}else {
				
				rdAttr.addFlashAttribute("faqNoneMsg","해당 게시글이 없습니다.");
				System.out.println(">> FAQ게시판 디테일 페이지 이동 bd null 조회되지 않습니다.");
				mav.setViewName("redirect:faq_list.bom");
			}
		}else {
			
			rdAttr.addFlashAttribute("faqNoneMsg","해당 게시글이 없습니다.");
			System.out.println(">> FAQ게시판 디테일 페이지 이동 bdId null");
			mav.setViewName("redirect:faq_list.bom");
		
		}
		return mav;
	} 
	


//★★★★★★★★★★★★nuri★★★★★★★★★★★★★

//	nuri_board_list.bom // get dao 
//	nuri_board_list.bom?pg=x //get proc pagenation dao 
//	-게시판 글을 조회할 수 있다.
	@RequestMapping(value = "nuri_board_list.bom", method = RequestMethod.GET) 
	public ModelAndView nuriBoardList(@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber){
		
		
		int maxPg = bdSvc.checkMaxpageNumber(bdSvc.NURI_BOARD,bdSvc.NURI_PAGE_SIZE);
		if(maxPg == 0) {
			maxPg = 1;
		}
		if(pageNumber<=0 || pageNumber > maxPg) {
			
			System.out.println(">> 잘못된 페이지번호 요청 nuri_pg:"+pageNumber);
			return new ModelAndView("redirect:nuri_board_list.bom?pg=1");
		}
		
		List<BoardVO> bdList = bdSvc.selectAllBoards(bdSvc.NURI_BOARD, pageNumber,bdSvc.NURI_PAGE_SIZE);
		int bdListCnt = bdSvc.checkAllBoardCount(bdSvc.NURI_BOARD);
		
		ModelAndView mav = new ModelAndView("board/nuri_bd_list");
		mav.addObject("subCategory",Materials.SUB_CATEGORY_ASSISTANCE);
		if(bdList!= null) {
			mav.addObject("bdAllcnt",bdListCnt);
			mav.addObject("bdList", bdList);
			mav.addObject("bdSize", bdList.size());
			mav.addObject("pn",pageNumber);
			mav.addObject("maxPg", maxPg);
			System.out.println(">>nuri_board maxPg:"+maxPg);
		}
		return mav;
	}
	
	

//	nuri_board_show.bom?id=x // get proc dao
//	nuri_board_show_page.bom//get dao  
//	-게시판 글을 클릭하여 내용을 조회할 수 있다.
	@RequestMapping(value = "nuri_board_show.bom", method = RequestMethod.GET) 
	public ModelAndView nuriBoardshow(int bdId,
								@RequestParam(value = "pg",defaultValue = "1",required = false)int pageNumber,
									RedirectAttributes rdAttr){
		
		ModelAndView mav = new ModelAndView();
		
		if(bdId!=0) {
			
			BoardVO bd = this.bdSvc.selectOneBoard(bdSvc.NURI_BOARD,bdId);
			mav.addObject("subCategory",Materials.SUB_CATEGORY_ASSISTANCE);
						
			if(bd!=null) {
				
				mav.addObject("board", bd);
				mav.addObject("pn", pageNumber);
				System.out.println(">> nuri_show pageNumber: "+pageNumber);
				mav.setViewName("board/nuri_bd_show");
			
			}else {
				
				rdAttr.addFlashAttribute("nuriNoneMsg","해당 게시글이 없습니다.");
				System.out.println(">> 누리봄게시판 디테일 페이지 이동 bd null 조회되지 않습니다.");
				mav.setViewName("redirect:nuri_board_list.bom");
			}
		}else {
			
			rdAttr.addFlashAttribute("nuriNoneMsg","해당 게시글이 없습니다.");
			System.out.println(">> 누리봄게시판 디테일 페이지 이동 bdId null");
			mav.setViewName("redirect:nuri_board_list.bom");
		
		}
		return mav;
	}

	
//누리봄스토리 더미페이지  nuri_story.bom 
	@RequestMapping(value = "nuri_story.bom", method = RequestMethod.GET) 
	public ModelAndView nuriBomStory(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_NURISTORY);
		mav.setViewName("board/nuri_story");
		return mav;
	}

	public static void main(String[] args) {
	}
	
	
	
	
	
	
}//class
