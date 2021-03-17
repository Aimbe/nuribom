package com.nuriweb.mybom.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuriweb.mybom.Materials;
import com.nuriweb.mybom.MyCode;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.model.vo.ReplyVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.IReplySVC;

@Controller
public class ReplyController {


	@Autowired
	IReplySVC rpSvc;
	@Autowired
	IAllNotifiSVC noSvc;
	
//	-게시판 (자유 게시판) 에서 회원이 덧글을 작성할 수 있다. ==>알림쏘기 !!!! 
//	  free_reply_proc.bom //post froc dao
@RequestMapping(value = "free_reply_proc.bom", method = RequestMethod.POST) 

public String freeNeweditForm(HttpSession ses,
									 @RequestParam(value="bdId")int bdId,
									 	@RequestParam(value="mbId")int mbId,
									 		@RequestParam(value="writer")String writer,
									 			@RequestParam(value="content")String content,
									 									Model model){
	int mbPKId = (int)ses.getAttribute("mbPKId");
	
	if(bdId!=0 && mbId!=0 && writer!=null && content !=null) {
	
			
			content = content.replaceAll(" ","&nbsp;");//"","&nbsp;"
			content = content.replaceAll("\n","<br>");
			content = content.replaceAll("\t","");
			content = content.replaceAll("\r","");
			System.out.println("content::"+content);
			
		if(mbPKId == mbId) {
			
			
			if(rpSvc.insertNewReply(rpSvc.FREE_REPLY,new ReplyVO(bdId, mbId, writer, content))) {
				
				List<ReplyVO> rpList = rpSvc.selectAllReply(rpSvc.FREE_REPLY, bdId);
				model.addAttribute("rpList",rpList);
				model.addAttribute("rpSize", rpList.size());
				
				boolean b = noSvc.insertNewFreeBoardNotifi(mbId, writer, MyCode.NewFreeBoardNotifi,"free_board_show_page.bom?bdId="+bdId);
				List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
				ses.setAttribute("noList", noList2);
				
				if( b) {
					System.out.println("자유게시판 댓글 알림 쏴주기 완료");
				}else {
					
					System.out.println("자유게시판 댓글 알림 쏴주기 실패.");
				}
				
			
			}else {
				
				model.addAttribute("rpMsg","덧글 등록에 실패했습니다. 관리자에게 문의해주세요!");
				System.out.println(">> replyVO 덧글등록 실패 :: sql문에러 ");
				
			}
		
		}
		
	}else {
		
		model.addAttribute("rpMsg","덧글 등록에 실패했습니다. 관리자에게 문의해주세요!");
		System.out.println(">> replyVO 덧글등록 실패 :: 파람에러 ");
	}
	return "reply/_show_reply";
} 



//	 -게시판 디테일페이지에서 해당 글에 작성된 덧글을 조회할 수 있다.
//	   reply_list.jsp?bdId = x  // dao
@RequestMapping(value = "free_reply_list.bom", method = RequestMethod.GET) 
public ModelAndView freeReplyShow(
							@RequestParam(value="bdId")int bdId){
	
		ModelAndView mav = new ModelAndView();
	if(bdId!=0) {
		
		System.out.println(">>bdId: "+ bdId+" 덧글 조회 성공!");
		List<ReplyVO> rpList = rpSvc.selectAllReply(rpSvc.FREE_REPLY, bdId);
		
		if(rpList!=null) {
			
			for (ReplyVO rp : rpList) {
				System.out.println(rp.toString());
			} //확인용..
		
		int rpSize = rpList.size();
		mav.addObject("rpList", rpList);
		mav.addObject("rpSize", rpSize);
		System.out.println("rpSize: "+ rpSize);
		
		}else {
			
			mav.addObject("rpMsg", "덧글 조회 실패했습니다. 관리자에게 문의해주세요!");
			
		}    
	}else {
		mav.addObject("rpMsg", "덧글 조회 실패했습니다. 관리자에게 문의해주세요!");
	}

	mav.setViewName("reply/_show_reply");
return mav;

} 





//-해당 덧글을 작성한 회원이라면 덧글을 수정할 수 있다.
// reply_update.jsp?bdId =x& mbId =x …  //get proc dao 
// 수정 후 다시 조회
@RequestMapping(value = "free_reply_update.bom", method = RequestMethod.POST)
@ResponseBody()
public ReplyVO freeReplyUpdate(
							@RequestParam(value="id")int id){
	
	
	ReplyVO rp = this.rpSvc.selectOneReply(rpSvc.FREE_REPLY, id);
	
	if(rp!=null) {
		
		return rp;
	}
	return null;
} 

@RequestMapping(value = "free_reply_update_froc.bom", method = RequestMethod.POST)
public ModelAndView freeReplyUpdateProc(
							@RequestParam(value="id")int id,
								@RequestParam(value="content")String content,
								   @RequestParam(value="bdId")int bdId,
								   		RedirectAttributes rdAttr){
	
	ModelAndView mav = new ModelAndView();
	
	if(id!=0 && content!=null&&bdId!=0) {
		
		content = content.replaceAll("\n", "<br>");
		boolean b = rpSvc.updateOneReply(rpSvc.FREE_REPLY, id, content);
		
		if(b) {
			
			mav.setViewName("redirect:free_board_show_page.bom?bdId="+bdId);
			System.out.println(">> 덧글 수정완료 덧글 아이디: "+id);
			
		}else {
			
			mav.setViewName("redirect:free_board_show_page.bom?bdId="+bdId);
			System.out.println(">> 덧글 수정실패 덧글 아이디: "+id);
			rdAttr.addFlashAttribute("rpUpdateMsg", "덧글 업데이트에 실패했습니다. 관리자에게 문의해주세요.");
		}
		
		
	}
	return mav;
} 




//	-해당 덧글을 작성한 회원이라면 덧글을 삭제할 수 있다. + 관리자
//	 · 삭제 버튼 클릭시 confirm 창으로 한 번 더 물어보기!
//	    confirm 창에서 yes를 선택시 ..
//	 reply_delete.jsp? bdId=x & id = x  //get proc dao 
//	  삭제 후 다시 조회 
@RequestMapping(value = "free_reply_delete.bom", method = RequestMethod.POST)
public ModelAndView freeReplyDelete(
							@RequestParam(value="id")int id,
							   @RequestParam(value="id")int bdId,
							   			RedirectAttributes rdAttr){
	
	ModelAndView mav = new ModelAndView();
	
	if(id!=0 && bdId!=0) {
		
		boolean r = rpSvc.deleteOneReply(rpSvc.FREE_REPLY, id);
		
		if(r) {
		
		  System.out.println(">>덧글삭제 완료: "+id);
		  List<ReplyVO> rpList = rpSvc.selectAllReply(rpSvc.FREE_REPLY, bdId);
		  if(rpList!=null) {
			  
			    int rpSize = rpList.size();
				mav.addObject("rpList", rpList);
				mav.addObject("rpSize", rpSize);
		  }
			
		}else {
			
			mav.setViewName("redirect:free_board_show_page.bom?bdId="+bdId);
			rdAttr.addFlashAttribute("rpUpdateMsg", "덧글 삭제를 실패했습니다. 관리자에게 문의해주세요.");
			return mav;
		}
	}
	mav.setViewName("reply/_show_reply");
	return mav;
} 

}
