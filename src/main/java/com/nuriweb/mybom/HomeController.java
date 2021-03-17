package com.nuriweb.mybom;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.service.inf.IBoardSVC;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	IBoardSVC bdSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "home.bom", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}

	//누리봄 게시글 리스트 메인홈 조회..
		@RequestMapping(value = "nuri_board_limit.bom", method = RequestMethod.GET)
		public ModelAndView nuriBoardlimit() {
			
			ModelAndView mav = new ModelAndView();
			
			List<BoardVO> nuriList = bdSvc.selectAllBoardsByMain(bdSvc.NURI_BOARD,bdSvc.NURI_MAIN_PAGE_SIZE);
			
			if(nuriList!=null) {
				
				mav.addObject("nrList", nuriList);
				mav.addObject("nrSize", nuriList.size());
				System.out.println(">> 누리봄 리스트 최근순 6개 조회: "+nuriList.size());
				mav.setViewName("main/_nuri_list");
				
			}else {
				
				mav.setViewName("redirect:home.bom");
			}
			
			
			return mav;
		}
		
	
}
