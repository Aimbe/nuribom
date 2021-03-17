package com.nuriweb.mybom.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nuriweb.mybom.Materials;
import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.ReviewVO;
import com.nuriweb.mybom.service.inf.IReviewSVC;

@Controller
public class ReviewController {
	
	@Autowired
	IReviewSVC rvSVC;
	@Autowired
	ICenterDAO ctDAO;
 	@RequestMapping(value = "all-review-page.bom",
			method = RequestMethod.GET)
	public ModelAndView allReviewPage(
			@RequestParam(value = "pg",required = false,defaultValue ="1") int page){
		List<ReviewVO> rvList = rvSVC.selectAllReview(page);
		int maxPg = rvSVC.checkMaxPageNumber();
		System.out.println(maxPg);
		if(maxPg==0) {
			maxPg=1;
		}
		if(page>maxPg|| page<=0 ) {
			System.out.println(">> 잘못된 페이지 번호 요청  pg:"+page);
			return new ModelAndView("redirect:all-review-page.bom?pg=1");
		}
		System.out.println("\n"+rvList.size()+"개 리뷰리스트가 검출되었습니다.\n");
		ModelAndView mav = new ModelAndView();
		for (ReviewVO rvv : rvList) {
			CenterVO ct = ctDAO.getOneCenter(rvv.getReviewCenterId());
			rvv.setReviewCenterName(ct.getName());
			rvv.setReviewCenterAddress(ct.getAddressRegion()+" "+ct.getAddressCity()+" "+ct.getAddressDetail());
			rvv.setReviewDay(new SimpleDateFormat("yyyy-MM-dd").format(rvv.getReviewCreatedAt()));
			rvv.setReviewCenterImagePath(ct.getMainImg()); 
			
		}
		mav.setViewName("review/all-review-page");
		mav.addObject("subCategory",Materials.SUB_CATEGORY_ALL_REVIEW);
		mav.addObject("rvList",rvList);
		mav.addObject("rvListSize",rvList.size());
		mav.addObject("pg",page);
		mav.addObject("maxPg",maxPg);
		
		return mav;
	}
 	
 	@RequestMapping(value = "delete_review_{rvId}.bom")
	@ResponseBody
	public String deleteReviewId(
			@PathVariable("rvId") int rvId 
			) {
		System.out.println(rvId);
		boolean b = rvSVC.deleteOneReview(rvId);
		
		
		return b? "success" : "fail";
		
	}
}
