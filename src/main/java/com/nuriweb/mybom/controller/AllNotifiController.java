package com.nuriweb.mybom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nuriweb.mybom.model.dao.inf.IAllNotifiDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.ReviewVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;

@Controller
public class AllNotifiController {

		@Autowired
		IAllNotifiSVC noSvc;
	
//	  더미 테스트 확인용
//		@RequestMapping(value ="all_notifi.bom", method =RequestMethod.GET)
//		public ModelAndView allnotifiListProc() {
//			
//			
//			
//			List<AllNotifiVO> noList =noSvc.selectAllNotifi();
//			//List<AllNotifiVO> anList =noSvc.selectAllNotifiWithMbId(mbId);
//			
//			//페이지네이션 ver
//			//List<AllNotifiVO> anList =noSvc.selectAllNotifiWithMbId_PG(mbId, offset, limit);
//			
//			
//			//알림 개수
//			int noCount = noList.size();
//			ModelAndView mav = new ModelAndView("common/member/_my_allnotifi_list");
////			mav.addObject("noList",noList);
//			
//			if( noList != null) {
//				mav.addObject("noList",noList);
//				mav.addObject("noCount",noCount);
//				
//				//mav.addObject("msg","회원님의 알림 개수 " +noCount);
//				
//			}else {
//				
//				mav.addObject("msg","알림 없음");
//			}
			
	//List<AllNotifiVO> noList = noSvc.
		
		// 내가 받은 알림들 비동기 조각페이지. (mbId 값 받았을떄만 가능 )
				@RequestMapping(value = "noticard@{login}.my", 
						method = RequestMethod.GET) 
				public ModelAndView AllNotiCard(HttpSession ses){
						
					
					//여긴 로그인 안되면 hidden으로 처리되서 굳이 필요 없음
					if((Integer) ses.getAttribute("mbPKId") == null ) {
						return new ModelAndView("member/mb_login_form");
					} else {
					int mbId = (Integer) ses.getAttribute("mbPKId");				
					
					List<AllNotifiVO> noList = noSvc.selectAllNotifiWithMbId(mbId);
					System.out.println(noList.size());
					ModelAndView mav = new ModelAndView("common/_notibell");
					
					
					if( noList != null ) {
						int noSize = noList.size();
						
						ses.setAttribute("noSize", noSize);
						ses.setAttribute("noList", noList);
						
//						mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + noSize +"개");
//						mav.addObject("noList", noList); //알림 리스트
//						mav.addObject("noSize", noSize); //총 개수
//						mav.addObject("mbId", mbId);
//				
					} 	
					return mav;
					}
				} 
				
		
		
		
		// 내가 받은 알림들을 확인 할 수 있다. (페이지네이션,mbId 값 받았을떄만 가능 )
		@RequestMapping(value = "all_notifi.bom", 
				method = RequestMethod.GET) 
		public ModelAndView myAllNotiListProc(HttpSession ses,
				@RequestParam(value = "pg",required = false,defaultValue ="1") int page){
				
			if((Integer) ses.getAttribute("mbPKId") == null ) {
				return new ModelAndView("member/mb_login_form");
			} else {
			int mbId = (Integer) ses.getAttribute("mbPKId");				
			int maxPg = noSvc.checkMaxPageNumberAllNotifi(mbId);
			if(maxPg == 0) {
				maxPg = 1;
			}
			if( page <= 0 || page > maxPg ) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + page);
				return new ModelAndView("redirect:all_notifi.bom?pg=1");
			}
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
			List<AllNotifiVO> noList = noSvc.selectAllNotifiWithMbId(mbId, page);
			System.out.println(noList.size());
			ModelAndView mav = new ModelAndView("common/member/_my_allnotifi_list");
			if( noList != null ) {
				int noSize = noList.size();
				mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + noSize +"개");
				mav.addObject("noList", noList); //알림 리스트
				mav.addObject("noSize", noSize); //총 개수
				mav.addObject("mbId", mbId);
			
				
				
				
				
				ses.setAttribute("noSize", noSize);
				ses.setAttribute("noList", noList2);
				
				
				mav.addObject("pn", page); // 현재 페이지 번호
				mav.addObject("maxPg", maxPg); // 최대 페이지 번호
				
				System.out.println("maxPg :" + maxPg );
			} 	
			return mav;
			}
		} 
		
		@RequestMapping(value = "all_term.bom", method = RequestMethod.GET) 
		public String myAllNotiListProc(HttpServletRequest req, HttpSession ses){
			return "common/main/private_term";
		}
	
		
		
}












