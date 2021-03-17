package com.nuriweb.mybom.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.style.DefaultValueStyler;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuriweb.mybom.Materials;
import com.nuriweb.mybom.MyCode;
import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.dao.inf.IReviewDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.LikeVO;
import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.model.vo.ReviewVO;
import com.nuriweb.mybom.model.vo.TagVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.ICenterSVC;
import com.nuriweb.mybom.service.inf.IFileManageSVC;
import com.nuriweb.mybom.service.inf.ILikeSVC;
import com.nuriweb.mybom.service.inf.IReserveSVC;
import com.nuriweb.mybom.service.inf.IReviewSVC;
import com.nuriweb.mybom.service.inf.ITagSVC;

@Controller
public class CenterController {

	@Autowired
	ICenterSVC ctSvc;
	@Autowired 
	IReviewSVC rvSvc;
	@Autowired 
	IReviewDAO rvDao;
	@Autowired
	ITagSVC tgSvc;
	@Autowired
	IFileManageSVC fileSvc;
	@Autowired 
	IReserveSVC rsSvc;
	@Autowired
	ILikeSVC likeSvc;
	@Autowired
	IAllNotifiSVC noSvc;

//	전체 상담소 리스트를 조회할 수 있다.
//	center_list.bom
	@RequestMapping(value = "center_list.bom", method = RequestMethod.GET)
	public ModelAndView showCenterList(HttpServletRequest req, HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "key", required = false, defaultValue = "none") String keyword,
			@RequestParam(value = "reg", required = false, defaultValue = "all") String region,
			@RequestParam(value = "sel_category", required = false, defaultValue = "all") String category,
			@RequestParam(value = "sel_tag", required = false, defaultValue = "all") String tag,
			@RequestParam(value = "sel_filter", required = false, defaultValue = "recent") String orderBy) {

		ModelAndView mav = new ModelAndView("center/ct_list");
//		List<CenterRowVO> ctList = ctSvc.getCenterSearchList(keyword, region, category, tag, orderBy, pageNumber, ctSvc.BASIC_CT_PAGE_SIZE);

		if (keyword.equals("none")) {
			keyword = "";
		} else {
			keyword = req.getParameter("key");
		}

		switch (region) {
		case "all":
			region = "all";
//				ctList = ctSvc.selectAllCentersForVirtualRow(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE);
			break;
		case "seoul":
			region = "서울특별시";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "서울특별시");
			break;
		case "kyeong":
			region = "경기도";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "경기도");
			break;
		default:
			break;
		}

		switch (category) {
		case "all":
			category = "all";
			break;
		case "domestic":
			category = "가정상담소";
			break;
		case "sexualViolence":
			category = "성폭력상담소";
			break;
		case "teenager":
			category = "청소년상담소";
			break;
		case "suicide":
			category = "자살예방센터";
			break;
		case "multicultural":
			category = "다문화가족";
			break;
		default:
			break;
		}

		switch (tag) {
		case "all":
			tag = "all";
			break;
		case "friend":
			tag = "친구";
			break;
		case "married":
			tag = "부부";
			break;
		case "socialRelationship":
			tag = "대외관계";
			break;
		case "couple":
			tag = "연인";
			break;
		case "family":
			tag = "가족";
			break;
		case "study":
			tag = "학업";
			break;
		case "company":
			tag = "직장";
			break;
		case "career":
			tag = "진로";
			break;
		case "job":
			tag = "취업";
			break;
		case "childcare":
			tag = "육아";
			break;
		case "overseas":
			tag = "해외생활";
			break;
		case "addicted":
			tag = "중독";
			break;
		case "eatingDisorder":
			tag = "섭식장애";
			break;
		case "sexLife":
			tag = "성생활";
			break;
		case "sexualMinorities":
			tag = "성소수자";
			break;
		case "emotionalControl":
			tag = "감정조절";
			break;
		case "selfHarm":
			tag = "자해";
			break;
		case "suicide":
			tag = "자살";
			break;
		}

		switch (orderBy) {
		case "recent":
			orderBy = ICenterDAO.ORDER_RECENT;
			break;
		case "old":
			orderBy = ICenterDAO.ORDER_OLD;
			break;
		case "likes":
			orderBy = ICenterDAO.ORDER_LIKES;
			break;
		default:
			break;
		}

		List<CenterRowVO> ctList = ctSvc.getCenterSearchList(keyword, region, category, tag, orderBy, pageNumber,
				ctSvc.BASIC_CT_PAGE_SIZE);
		// ctList = ctSvc.getCenterSearchList(keyword, region, category, tag, orderBy,
		// pageNumber, ctSvc.BASIC_CT_PAGE_SIZE);

		int maxPage = ctSvc.checkMaxPageNumber(ctSvc.BASIC_CT_PAGE_SIZE);
		if (pageNumber <= 0 || pageNumber > maxPage) {
			System.out.println(">> 잘못된 페이지번호 요청 pg: " + pageNumber);
			mav.setViewName("center_list.bom?pg=1");
		}

		Map<Integer, List<String>> tagMap = new HashMap<>();
		List<String> tagList = null;

		if (ctList != null) {

			for (CenterRowVO ct : ctList) {
				String[] tags = ct.getsTag().split(",");
				tagList = Arrays.asList(tags);
				tagMap.put(ct.getsId(), tagList);

				if (ses.getAttribute("mbPKId") != null) {
					boolean r = likeSvc.isAleadyLiked((int) ses.getAttribute("mbPKId"), ct.getsId());
					if (r)
						ct.setsStatus(1);
					else
						ct.setsStatus(0);
				}
				int likes = likeSvc.getLikeCountOfOneCenter(ct.getsLikes());
				ct.setsLikes(likes); // 좋아요수 전달
			}

			int ctSize = ctList.size();
			System.out.println("pg 상담소 전체리스트 조회성공: " + ctSize + "개");
			mav.addObject("ctList", ctList);
			mav.addObject("ctSize", ctSize);
			mav.addObject("pn", pageNumber); // 클릭한 페이지 번호
			mav.addObject("tagList", tagList);
			mav.addObject("tagMap", tagMap); // 태그 출력 위한것
			mav.addObject("reg", req.getParameter("reg"));

			System.out.println("ctList controll: maxPage = " + maxPage);
			mav.addObject("maxPg", maxPage);
		} else {
		}
		return mav;
	}

	
//	상담소 상세보기
//	center_detail.bom?ceId=x 
	@RequestMapping(value = "center_detail.bom", method = RequestMethod.GET)
	public ModelAndView showCenterDetail(HttpServletRequest req,HttpSession ses,
			@RequestParam(value ="pg",required = false,defaultValue ="1") int page ){
		ModelAndView mav = new ModelAndView();
		int ctId = Integer.parseInt(req.getParameter("ctId"));
		CenterVO ct = ctSvc.getOneCenter(ctId);
		int likes =likeSvc.getLikeCountOfOneCenter(ctId);
		ct.setLikes(likes); //좋아요수 전달
		List<TagVO> tagList = tgSvc.selectAllTagListOfOneCenter(ctId);
		
		System.out.println(ses.getAttribute("mbPKId"));
		int mbid=-1;
		String a = "";
		if(ses.getAttribute("mbPKId")!=null&&!ses.getAttribute("mbPKId").equals("")) {
			a = String.valueOf(ses.getAttribute("mbPKId"));
		}
		if(a!=null && !a.equals("")) {
			mbid = Integer.parseInt(a);
			mav.addObject("mbId",mbid);
		}
		ReserveVO reser = rsSvc.thisMemberVisitCenter(mbid, ctId);
		if(reser!=null && mbid>0) {
			System.out.println(reser.toString());
			
			mav.addObject("reserve",reser);
		}else {
			mav.addObject("reserveR",1);
		}
	
		if( ct != null ) {
			mav.addObject("msg", "상담소 조회 성공");
			mav.addObject("center", ct);
			mav.setViewName("center/ct_detail");
			
			int maxPg = rvSvc.checkMaxPageNumberCenter(ctId);
			System.out.println(maxPg);
			if(maxPg>0) {
			if(page<=0||page>maxPg){
				System.out.println(">> 잘못된 페이지 번호 요청  pg:"+page);
				return new ModelAndView("redirect:center_detail.bom?ctId="+ctId);
			}
			}
			List<ReviewVO> rvList = rvSvc.selectCenterReviewPage(ctId, page);
			List<ReviewVO> AllrvList = rvSvc.selectAllReviewByCenter(ctId);
			
			float listSize = AllrvList.size();
			float score = 0;
			for (ReviewVO rv : AllrvList) {
				score += rv.getReviewRate();
			}
			for (ReviewVO rvv : rvList) {
				rvv.setReviewDay(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(rvv.getReviewCreatedAt()));
			}
			
			float AverageL = 0.0f;
			if(score!=0)AverageL= score/listSize;
			float Average = 0.0f;
			if(score!=0)Average= (float)(Math.round(AverageL*100)/100.0); 
			mav.addObject("rvList",rvList);
			mav.addObject("rvListSize",rvDao.checkCenterReviewCount(ctId));
			mav.addObject("Average",Average);
			mav.addObject("tagList", tagList);
			mav.addObject("pg",page);
			mav.addObject("maxPg",maxPg);
			mav.addObject("ctId",ctId);
			
		} else {
			mav.addObject("msg", "상담소 조회 실패");
			mav.setViewName("redirect:center_list.bom");
		}
		return mav;
	}
	@RequestMapping(value="add_review.bom",method=RequestMethod.GET)
	public ModelAndView addReview(HttpServletRequest req,HttpSession ses,
			@RequestParam(value = "selectRate",required = false, defaultValue="0") int rate,
			@RequestParam(value = "review_write",required = false, defaultValue="") String Write) {
ModelAndView mav = new ModelAndView();

		int ctId = Integer.parseInt(req.getParameter("ctId"));
		System.out.println(rate+"");
		CenterVO ct = ctSvc.getOneCenter(ctId);
		String a = String.valueOf(ses.getAttribute("mbPKId"));
		int mbid = Integer.parseInt(a);
		ReserveVO reser = rsSvc.thisMemberVisitCenter(mbid, ctId);
		if(reser==null) {
			mav.setViewName("redirect:center_detail.bom?ctId="+ctId);
			return mav;
		}
		if(rate>0&&!Write.equals("")&&Write!=null&&reser!=null) {
			System.out.println(mbid);
			System.out.println(ctId);
			System.out.println(reser.getReserveId());
			System.out.println(rate+"");
			System.out.println((String)ses.getAttribute("mbnickName"));
			System.out.println(Write);
			boolean insert = rvSvc.insertOneReview(mbid,ctId,reser.getReserveId(),
					rate,(String)ses.getAttribute("mbnickName"),Write);
			
			boolean bd = noSvc.insertNewReviewPostNotifi(mbid, ct.getName(), "" ,MyCode.ReviewPostNotifi, "");
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbid);
			ses.setAttribute("noList", noList2);
			if(insert) {
			rsSvc.changeReviewCheck(reser.getReserveId());
			mav.setViewName("redirect:center_detail.bom?ctId="+ctId);
			return mav;
			}
		}
		mav.setViewName("redirect:center_detail.bom?ctId="+ctId);
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "center_detail_edit.bom", method = RequestMethod.GET)
	public ModelAndView showCenterDetail(HttpServletRequest req,HttpSession ses,
			@RequestParam(value ="pg",required = false,defaultValue ="1") int page,
		@RequestParam(value = "rvId",required = false, defaultValue="0") int rvId) {
		ModelAndView mav = new ModelAndView();
		ReviewVO editRv = rvSvc.selectOneReview(rvId);
		mav.addObject("editRv",editRv);
		mav.addObject("rvId",rvId);
		System.out.println(editRv.getReviewCenterId());
		int ctId = editRv.getReviewCenterId();
		CenterVO ct = ctSvc.getOneCenter(ctId);
		int likes =likeSvc.getLikeCountOfOneCenter(ctId);
		ct.setLikes(likes); //좋아요수 전달
		List<TagVO> tagList = tgSvc.selectAllTagListOfOneCenter(ctId);
		
		System.out.println(ses.getAttribute("mbPKId"));
		int mbid=-1;
		String a = "";
		if(ses.getAttribute("mbPKId")!=null&&!ses.getAttribute("mbPKId").equals("")) {
			a = String.valueOf(ses.getAttribute("mbPKId"));
		}
		if(a!=null && !a.equals("")) {
			mbid = Integer.parseInt(a);
			mav.addObject("mbId",mbid);
		}
		if(mbid != editRv.getReviewMemberId()) {
			System.out.println("로그인한 계정과 리뷰의 아이디가 맞지않음");
			mav.setViewName("redirect:center_detail.bom?ctId="+ctId);
			return mav;
		}
		if( ct != null ) {
			mav.addObject("msg", "상담소 조회 성공");
			mav.addObject("center", ct);
			mav.setViewName("center/ct_detail_edit");
			int maxPg = rvSvc.checkMaxPageNumberCenter(ctId);
			System.out.println(maxPg);
			if(maxPg>0) {
			if(page<=0||page>maxPg){
				System.out.println(">> 잘못된 페이지 번호 요청  pg:"+page);
				return new ModelAndView("redirect:center_detail.bom?ctId="+ctId);
			}
			}
			List<ReviewVO> rvList = rvSvc.selectCenterReviewPage(ctId, page);
			List<ReviewVO> AllrvList = rvSvc.selectAllReviewByCenter(ctId);
			
			float listSize = AllrvList.size();
			float score = 0;
			for (ReviewVO rv : AllrvList) {
				score += rv.getReviewRate();
			}
			for (ReviewVO rvv : rvList) {
				rvv.setReviewDay(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(rvv.getReviewCreatedAt()));
			}
			
			float AverageL = 0.0f;
			if(score!=0)AverageL= score/listSize;
			float Average = 0.0f;
			if(score!=0)Average= (float)(Math.round(AverageL*100)/100.0); 
			mav.addObject("rvList",rvList);
			mav.addObject("rvListSize",rvDao.checkCenterReviewCount(ctId));
			mav.addObject("Average",Average);
			mav.addObject("pg",page);
			mav.addObject("maxPg",maxPg);
			mav.addObject("ctId",ctId);
			mav.addObject("tagList", tagList);
		} else {
			mav.addObject("msg", "상담소 조회 실패");
			mav.setViewName("redirect:center_list.bom");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="center_detail_rv_edit.bom",method=RequestMethod.GET)
	public ModelAndView editReview(HttpServletRequest req,HttpSession ses,
			@RequestParam(value = "selectRate",required = false, defaultValue="0") int rate,
			@RequestParam(value = "review_write",required = false, defaultValue="") String Write,
			@RequestParam(value = "rvId") int rvId) {
		ModelAndView mav = new ModelAndView();
		System.out.println(rvId);
		
		ReviewVO editRv = rvSvc.selectOneReview(rvId);
		int ctId = editRv.getReviewCenterId();
		System.out.println(rate+"");
		CenterVO ct = ctSvc.getOneCenter(ctId);
		String a = String.valueOf(ses.getAttribute("mbPKId"));
		int mbid = Integer.parseInt(a);
		
		if(rate>0&&!Write.equals("")&&Write!=null) {
			boolean edit = rvSvc.updateOneReview(rvId, rate, Write);
			
			if(edit) {
			System.out.println("ct컨트롤러 실패로인해 못들어옴");	
			mav.setViewName("redirect:center_detail.bom?ctId="+ctId+"#main_reviews");
			return mav;
			}else {
				System.out.println("수정실패함 리뷰아이디:"+rvId);
			}
		}
		mav.setViewName("redirect:center_detail.bom?ctId="+ctId+"#main_reviews");
		return mav;
	}
	


	
	
	
	
	
	
	
//	회원이 상담소를 좋아요/좋아요 취소할 수 있다.
//	@ResponseBody
	@RequestMapping(value = "center_like.bom", method = RequestMethod.GET)
	public String centerLike(HttpSession ses, Model model, @RequestParam("mbId") int mbId,
			@RequestParam("ctId") int ctId, RedirectAttributes rdAttr) {

		boolean r = likeSvc.isAleadyLiked(mbId, ctId);
		List<LikeVO> likeList = likeSvc.selectAllLikeListOfOneMember(mbId);

		// 상담소 좋아요 수 조회

		// ses 저장 멤버아이디 mbPKId
		CenterRowVO ct = ctSvc.getOneCenterVir(ctId);

		if (r) { // true 좋아요되어있음(삭제)
			likeSvc.likeDelete(mbId, ctId);

			model.addAttribute("likeList", likeList);

			System.out.println(mbId + "번 회원이 " + ctId + "번 상담소 좋아요 취소함.");
			model.addAttribute("msg", "취소");
			System.out.println(ctId + "번 상담소 좋아요수: " + likeSvc.getLikeCountOfOneCenter(ctId) + "(좋아요취소)");
			model.addAttribute("status", "delete");
			model.addAttribute("likeCnt", ct.getsLikes() - 1);
			model.addAttribute("selStatus", null);
//			return "delete";
			ct.setsStatus(0);
			model.addAttribute("ct", ct);

		} else { // false 좋아요추가
			likeSvc.likeAdd(mbId, ctId);

			model.addAttribute("likeList", likeList);

			System.out.println(mbId + "번 회원이 " + ctId + "번 상담소 좋아요함.");
			model.addAttribute("msg", "성공");
			// model.addAttribute("likeCnt", likeSvc.getLikeCountOfOneCenter(ctId)+1);
			System.out.println(ctId + "번 상담소 좋아요수: " + likeSvc.getLikeCountOfOneCenter(ctId) + "(좋아요성공)");
			model.addAttribute("status", "like");
			model.addAttribute("likeCnt", ct.getsLikes() + 1);

			
			noSvc.insertLikeCenterNotifi(mbId, ct.getsName(), "", MyCode.LikeCenterNotifi, "");
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
			ses.setAttribute("noList", noList2);
			
			model.addAttribute("selStatus", "selected");
			ct.setsStatus(1);
			model.addAttribute("ct", ct);
//			return "like";

			// 좋아요 성공한 시점 => 알림전달 필요!!

		}
		int likeCnt = likeSvc.getLikeCountOfOneCenter(ctId);
		ct.setsLikes(likeCnt);
		model.addAttribute("likeCnt", likeSvc.getLikeCountOfOneCenter(ctId));

		return "center/_ct_like";
	}

//	상담소를 조회/검색할 수 있다.
	@RequestMapping(value = "center_search.bom", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView centerSearch(HttpSession ses, HttpServletRequest req,
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "reg", required = false, defaultValue = "all") String region,
			@RequestParam(value = "sel_category", required = false, defaultValue = "all") String category,
			@RequestParam(value = "sel_tag", required = false, defaultValue = "all") String tag,
			@RequestParam(value = "sel_filter", required = false, defaultValue = "recent") String orderBy,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {

		System.out.println(">> 검색 키워드: " + keyword);
		// List<CenterRowVO> ctList =
		// ctSvc.selectAllCentersForVirtualRowKeyword(pageNumber,
		// ctSvc.BASIC_CT_PAGE_SIZE, keyword);
		System.out.println(">> 카테고리: " + category);
		System.out.println(">> 태그: " + tag);
		System.out.println(">> 정렬방식: " + orderBy);

		ModelAndView mav = new ModelAndView("center/ct_list");

		if (keyword.equals("none")) {
			keyword = "";
		} else {
			keyword = req.getParameter("keyword");
		}

		switch (region) {
		case "all":
			region = "all";
			break;
		case "seoul":
			region = "서울특별시";
			break;
		case "0":
			region = "서울특별시";
			break;
		case "kyeong":
			region = "경기도";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "경기도");
			break;
		case "1":
			region = "경기도";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "경기도");
			break;
		default:
			break;
		}

		switch (category) {
		case "all":
			category = "all";
			break;
		case "domestic":
			category = "가정상담소";
			break;
		case "sexualViolence":
			category = "성폭력상담소";
			break;
		case "teenager":
			category = "청소년상담소";
			break;
		case "suicide":
			category = "자살예방센터";
			break;
		case "multicultural":
			category = "다문화가족";
			break;
		default:
			break;
		}

		switch (tag) {
		case "all":
			tag = "all";
			break;
		case "friend":
			tag = "친구";
			break;
		case "married":
			tag = "부부";
			break;
		case "socialRelationship":
			tag = "대외관계";
			break;
		case "couple":
			tag = "연인";
			break;
		case "family":
			tag = "가족";
			break;
		case "study":
			tag = "학업";
			break;
		case "company":
			tag = "직장";
			break;
		case "career":
			tag = "진로";
			break;
		case "job":
			tag = "취업";
			break;
		case "childcare":
			tag = "육아";
			break;
		case "overseas":
			tag = "해외생활";
			break;
		case "addicted":
			tag = "중독";
			break;
		case "eatingDisorder":
			tag = "섭식장애";
			break;
		case "sexLife":
			tag = "성생활";
			break;
		case "sexualMinorities":
			tag = "성소수자";
			break;
		case "emotionalControl":
			tag = "감정조절";
			break;
		case "selfHarm":
			tag = "자해";
			break;
		case "suicide":
			tag = "자살";
			break;
		case "0":
			tag = "친구";
			break;
		case "1":
			tag = "부부";
			break;
		case "2":
			tag = "대외관계";
			break;
		case "3":
			tag = "연인";
			break;
		case "4":
			tag = "가족";
			break;
		case "5":
			tag = "학업";
			break;
		case "6":
			tag = "직장";
			break;
		case "7":
			tag = "진로";
			break;
		case "8":
			tag = "취업";
			break;
		case "9":
			tag = "육아";
			break;
		case "10":
			tag = "해외생활";
			break;
		case "11":
			tag = "중독";
			break;
		case "12":
			tag = "섭식장애";
			break;
		case "13":
			tag = "성생활";
			break;
		case "14":
			tag = "성소수자";
			break;
		case "15":
			tag = "감정조절";
			break;
		case "16":
			tag = "자해";
			break;
		case "17":
			tag = "자살";
			break;
		}

		switch (orderBy) {
		case "recent":
			orderBy = ICenterDAO.ORDER_RECENT;
			break;
		case "old":
			orderBy = ICenterDAO.ORDER_OLD;
			break;
		case "likes":
			orderBy = ICenterDAO.ORDER_LIKES;
			break;
		default:
			break;
		}
		List<CenterRowVO> ctList = ctSvc.getCenterSearchList(keyword, region, category, tag, orderBy, pageNumber,
				ctSvc.BASIC_CT_PAGE_SIZE);
		// ctList = ctSvc.getCenterSearchList(keyword, region, category, tag, orderBy,
		// pageNumber, ctSvc.BASIC_CT_PAGE_SIZE);

		int maxPage = ctSvc.checkMaxPageNumber(ctSvc.BASIC_CT_PAGE_SIZE);
		if (pageNumber <= 0 || pageNumber > maxPage) {
			System.out.println(">> 잘못된 페이지번호 요청 pg: " + pageNumber);
			mav.setViewName("center_list.bom?pg=1");
		}

		// ctList = ctSvc.getCenterSearchList(keyword, region, category, tag, orderBy,
		// pageNumber, ctSvc.BASIC_CT_PAGE_SIZE);

//		ModelAndView mav = new ModelAndView();
		Map<Integer, List<String>> tagMap = new HashMap<>();
		List<String> tagList = null;

		if (ctList != null) {

			for (CenterRowVO ct : ctList) {
				String[] tags = ct.getsTag().split(",");
				tagList = Arrays.asList(tags);
				tagMap.put(ct.getsId(), tagList);

				if (ses.getAttribute("mbPKId") != null) {
					boolean r = likeSvc.isAleadyLiked((int) ses.getAttribute("mbPKId"), ct.getsId());
					if (r)
						ct.setsStatus(1);
					else
						ct.setsStatus(0);
				}
				int likes = likeSvc.getLikeCountOfOneCenter(ct.getsLikes());
				ct.setsLikes(likes); // 좋아요수 전달
			}

			int ctSize = ctList.size();
			System.out.println("pg 상담소 전체리스트 조회성공: " + ctSize + "개");
			mav.addObject("ctList", ctList);
			mav.addObject("ctSize", ctSize);
			mav.addObject("pn", pageNumber); // 클릭한 페이지 번호
			mav.addObject("tagList", tagList);
			mav.addObject("tagMap", tagMap); // 태그 출력 위한것
			mav.addObject("reg", region);

			System.out.println("ctList controll: maxPage = " + maxPage);
			mav.addObject("maxPg", maxPage);
		} else {
			System.out.println("center 검색 실패");
		}
		return mav;
	}

//	 상담소 리스트를 조회할 수 있다.(어드민용)
	@RequestMapping(value = "admin_center.bom", method = RequestMethod.GET)
	public ModelAndView AdminCenter(@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {

		ModelAndView mav = new ModelAndView("admin/admin_centerlist");

		int maxPage = ctSvc.checkMaxPageNumber(ctSvc.ADMIN_CT_PAGE_SIZE);
		if (pageNumber <= 0 || pageNumber > maxPage) {
			System.out.println(">> 잘못된 페이지번호 요청 pg: " + pageNumber);
			mav.setViewName("admin_center.bom?pg=1");
		}

		List<CenterVO> ctList = ctSvc.selectAllCenters(pageNumber, ctSvc.ADMIN_CT_PAGE_SIZE);

		if (ctList != null) {
			int ctSize = ctSvc.getCountOfAllCenters();
			mav.addObject("msg", "pg 상담소 전체리스트 조회성공: " + ctSize + "개");
			mav.addObject("ctList", ctList);
			mav.addObject("ctSize", ctSize);
			System.out.println("ctSize = " + ctSize);
			mav.addObject("pn", pageNumber); // 클릭한 페이지 번호
			mav.addObject("maxPg", maxPage);

			System.out.println("admin ctList controll: maxPage = " + maxPage);

		} else {
		}
		return mav;
	}

// 상담소를 등록할 수 있다.(어드민)
	@RequestMapping(value = "center_new_form.bom", method = RequestMethod.GET)
	public ModelAndView centerNewForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/admin_center_add");
		return mav;
	}

	// 등록 proc
	@RequestMapping(value = "center_add.bom", method = RequestMethod.POST)
	public ModelAndView addNewCenterProc(HttpServletRequest req, MultipartFile center_img, MultipartFile program_img_1,
			MultipartFile program_img_2) {
		System.out.println("addNewCenterProc() 싪행..");
		HttpSession ses = req.getSession();

		// 카테고리 & realPath 구분..
		String selCat = req.getParameter("sel_category");
		String category = "";
		// category 구분
		switch (selCat) {
		case "domestic":
			category = "가정상담소";
			break;
		case "sexualViolence":
			category = "성폭력상담소";
			break;
		case "teenager":
			category = "청소년상담소";
			break;
		case "suicide":
			category = "자살예방센터";
			break;
		case "multicultural":
			category = "다문화가족";
			break;
		default:
			System.out.println("카테고리 선택 오류");
			break;
		}

		// 파일 처리
		String mainRealPath = ses.getServletContext().getRealPath(IFileManageSVC.DEF_CT_DEST + "/");
		String proRealPath = ses.getServletContext().getRealPath(IFileManageSVC.DEF_CT_PRO_DEST + "/");
		System.out.println(">> add center mainRealPath: " + mainRealPath);
		System.out.println(">> add center proRealPath: " + proRealPath);

		String mainFilePath = fileSvc.writeCenterFile(center_img, mainRealPath, IFileManageSVC.DEF_CT_DEST);
		String pr1FilePath = fileSvc.writeCenterFile(program_img_1, proRealPath, IFileManageSVC.DEF_CT_PRO_DEST);
		String pr2FilePath = fileSvc.writeCenterFile(program_img_2, proRealPath, IFileManageSVC.DEF_CT_PRO_DEST);
		System.out.println("mainFilePath: " + mainFilePath);
		System.out.println("pr1FilePath: " + pr1FilePath);
		System.out.println("pr2FilePath: " + pr2FilePath);

		String main = (mainFilePath.substring(mainFilePath.lastIndexOf("/"))).substring(1); // 원래 오리지널 파일
		String pr1 = (pr1FilePath.substring(pr1FilePath.lastIndexOf("/"))).substring(1);
		String pr2 = (pr2FilePath.substring(pr2FilePath.lastIndexOf("/"))).substring(1);

		// center 생성
		String name = req.getParameter("center_name");
		String telephone = req.getParameter("center_telNum");
		String site = req.getParameter("center_site");
		String openTime = req.getParameter("center_time_open");
		String closeTime = req.getParameter("center_time_close");
		String addressRegion = req.getParameter("center_address").split(" ")[0];
		String addressCity = req.getParameter("center_address").split(" ")[1];
		String addressDetail = req.getParameter("center_address").split(" ")[2];
		String firstDesc = req.getParameter("center_intro_1");
		String secondDesc = req.getParameter("center_intro_2");
		String thirdDesc = req.getParameter("center_intro_3");
		String firstProgramTitle = req.getParameter("center_program_title_1");
		String firstProgramDesc = req.getParameter("center_program_desc_1");
		String secondProgramTitle = req.getParameter("center_program_title_2");
		String secondProgramDesc = req.getParameter("center_program_desc_2");
		double latitude = Double.parseDouble(req.getParameter("center_location_latitude"));
		double longitude = Double.parseDouble(req.getParameter("center_location_longtitude"));

		
//		CenterRowVO ct = new CenterRowVO(name, telephone, site, category, main, openTime, closeTime,
//				firstDesc, secondDesc, thirdDesc, firstProgramTitle, firstProgramDesc, pr1, secondProgramTitle, secondProgramDesc, pr2,
//				addressRegion, addressCity, addressDetail, latitude, longitude);
		
		CenterVO ct = new CenterVO(name, telephone, site, main, openTime, closeTime, category, firstDesc, secondDesc,
				thirdDesc, firstProgramTitle, firstProgramDesc, pr1, secondProgramTitle, secondProgramDesc, pr2,
				addressRegion, addressCity, addressDetail, latitude, longitude);

		ModelAndView mav = new ModelAndView();

		int ctRetKey = ctSvc.insertNewCenterReturnKey(ct); // 새로 등록한 ct Id리턴

		if (ctRetKey > 0) { // center PK 존재
			mav.addObject("centerId", ctRetKey);
			mav.addObject("center", ct);
			System.out.println("센터 추가 성공 " + ctRetKey);
			mav.setViewName("admin/_ct_add_tag"); // ?ctId="+ctRetKey);

		} else {
			mav.setViewName("redirect:center_new_form.bom");
			System.out.println("센터 추가 실패" + ct);
		}
		return mav;
	}

//	 상담소 tag를 등록할 수 있다. (어드민) proc
	@RequestMapping(value = "center_add_tag.bom", method = RequestMethod.POST)
	public String centerAddTagProc(HttpServletRequest req, @RequestParam(value = "centerId", required = true) int ctId,
			Model model) {

		// tag 생성
		System.out.println();
		List<String> tagList = new ArrayList<String>();
		String[] tags = req.getParameterValues("sel_tag[]");
		// tag value 한글로 판별하여 변경
		for (String tag : tags) {
			switch (tag) {
			case "check_freind":
				tagList.add("친구");
				break;
			case "check_married":
				tagList.add("부부");
				break;
			case "check_socialRelationship":
				tagList.add("대외관계");
				break;
			case "check_couple":
				tagList.add("연인");
				break;
			case "check_family":
				tagList.add("가족");
				break;
			case "check_study":
				tagList.add("학업");
				break;
			case "check_company":
				tagList.add("직장");
				break;
			case "check_career":
				tagList.add("진로");
				break;
			case "check_job":
				tagList.add("취업");
				break;
			case "check_childcare":
				tagList.add("육아");
				break;
			case "check_overseas":
				tagList.add("해외생활");
				break;
			case "check_addicted":
				tagList.add("중독");
				break;
			case "check_eatingDisorder":
				tagList.add("섭식장애");
				break;
			case "check_sexLife":
				tagList.add("성생활");
				break;
			case "check_sexualMinorities":
				tagList.add("성소수자");
				break;
			case "check_emotionalControl":
				tagList.add("감정조절");
				break;
			case "check_selfHarm":
				tagList.add("자해");
				break;
			case "check_suicide":
				tagList.add("자살");
				break;
			}
			System.out.println(tag);
		}

		for (String tag : tagList) {
			System.out.println("한글버전: " + tag);
		}

		boolean b = tgSvc.insertTags(tagList.toArray(new String[tagList.size()]), ctId);
		if (b) {
			System.out.println("상담소 태그등록 성공.");
			return "redirect:admin_center.bom";
		} else {
			System.out.println("상담소 태그등록 실패.");
			return "admin/_ct_add_tag";
		}
	}

	
	// 상담소를 업데이트할 수 있다.(어드민)
		@RequestMapping(value = "center_edit_form.bom", method = RequestMethod.GET)
		public ModelAndView centerEditForm(HttpServletRequest req, @RequestParam(value = "ctId", required = true) int ctId,
				Model model) {
			
			ModelAndView mav = new ModelAndView();
			
			CenterVO ct = ctSvc.getOneCenter(ctId);
			if( ct != null ) {
				mav.addObject("ct", ct);
				mav.setViewName("admin/admin_center_update");
			} else {
				System.out.println("편집할 센터 조회되지 않음");
				mav.setViewName("redirect:admin_center.bom");
			}
			return mav;
		}
		
		// 센터 업데이트 proc(어드민)
		@RequestMapping(value = "center_edit.bom", method = RequestMethod.POST)
		public ModelAndView centerEditProc(HttpServletRequest req, MultipartFile center_img, MultipartFile program_img_1, MultipartFile program_img_2) {
			System.out.println("centerEditProc() 실행..");
			HttpSession ses = req.getSession();

			// 카테고리 & realPath 구분..
			String selCat = req.getParameter("sel_category");
			String category = "";
			// category 구분
			switch (selCat) {
			case "domestic":
				category = "가정상담소";
				break;
			case "sexualViolence":
				category = "성폭력상담소";
				break;
			case "teenager":
				category = "청소년상담소";
				break;
			case "suicide":
				category = "자살예방센터";
				break;
			case "multicultural":
				category = "다문화가족";
				break;
			default:
				System.out.println("카테고리 선택 오류");
				break;
			}

			// 파일 처리
			String mainRealPath = ses.getServletContext().getRealPath(IFileManageSVC.DEF_CT_DEST + "/");
			String proRealPath = ses.getServletContext().getRealPath(IFileManageSVC.DEF_CT_PRO_DEST + "/");
			System.out.println(">> add center mainRealPath: " + mainRealPath);
			System.out.println(">> add center proRealPath: " + proRealPath);

			String mainFilePath = fileSvc.writeCenterFile(center_img, mainRealPath, IFileManageSVC.DEF_CT_DEST);
			String pr1FilePath = fileSvc.writeCenterFile(program_img_1, proRealPath, IFileManageSVC.DEF_CT_PRO_DEST);
			String pr2FilePath = fileSvc.writeCenterFile(program_img_2, proRealPath, IFileManageSVC.DEF_CT_PRO_DEST);
			System.out.println("mainFilePath: " + mainFilePath);
			System.out.println("pr1FilePath: " + pr1FilePath);
			System.out.println("pr2FilePath: " + pr2FilePath);

			String main = (mainFilePath.substring(mainFilePath.lastIndexOf("/"))).substring(1); // 원래 오리지널 파일
			String pr1 = (pr1FilePath.substring(pr1FilePath.lastIndexOf("/"))).substring(1);
			String pr2 = (pr2FilePath.substring(pr2FilePath.lastIndexOf("/"))).substring(1);

			// center 생성
			String name = req.getParameter("center_name");
			String telephone = req.getParameter("center_telNum");
			String site = req.getParameter("center_site");
			String openTime = req.getParameter("center_time_open");
			String closeTime = req.getParameter("center_time_close");
			String addressRegion = req.getParameter("center_address").split(" ")[0];
			String addressCity = req.getParameter("center_address").split(" ")[1];
			String addressDetail = req.getParameter("center_address").split(" ")[2];
			String firstDesc = req.getParameter("center_intro_1");
			String secondDesc = req.getParameter("center_intro_2");
			String thirdDesc = req.getParameter("center_intro_3");
			String firstProgramTitle = req.getParameter("center_program_title_1");
			String firstProgramDesc = req.getParameter("center_program_desc_1");
			String secondProgramTitle = req.getParameter("center_program_title_2");
			String secondProgramDesc = req.getParameter("center_program_desc_2");
			double latitude = Double.parseDouble(req.getParameter("center_location_latitude"));
			double longitude = Double.parseDouble(req.getParameter("center_location_longtitude"));

			CenterVO ct = new CenterVO(name, telephone, site, main, openTime, closeTime, category, firstDesc, secondDesc,
					thirdDesc, firstProgramTitle, firstProgramDesc, pr1, secondProgramTitle, secondProgramDesc, pr2,
					addressRegion, addressCity, addressDetail, latitude, longitude);

			ModelAndView mav = new ModelAndView();

			boolean r = ctSvc.updateOneCenter(ct); // 새로 등록한 ct Id리턴

			if ( r) { // center PK 존재
				mav.addObject("centerId", ct.getId());
				mav.addObject("center", ct);
				System.out.println("센터 수정 성공 " + ct.getId());
				mav.setViewName("admin/admin_centerlist"); // ?ctId="+ctRetKey);

			} else {
				mav.setViewName("redirect:center_edit_form.bom");
				System.out.println("센터 수정 실패" + ct);
			}

			return mav;
		}
		
		
// 상담소를 삭제할 수 있다.(어드민)
		@RequestMapping(value = "delete_center_{ctId}.bom")
		@ResponseBody
		public String deleteReserveID(@PathVariable("ctId") int ctId) {
			System.out.println(ctId);
			System.out.println("센터삭제");
			
			//태그먼저 삭제..
			boolean tDel = tgSvc.deleteAllTagsOfCenter(ctId);
			if(tDel) System.out.println("태그삭제" + ctId);

			CenterVO ct = ctSvc.getOneCenter(ctId);
			boolean b = ctSvc.deleteOneCenter(ct);
			if(b) System.out.println("센터삭제성공" + ctId);
			
			System.out.println(tDel);
			System.out.println(b);
			return b ? "success" : "fail";

		}
		
		
	@RequestMapping(value = "center_delete.bom", method = RequestMethod.POST)
	public String centerDeleteProc(HttpServletRequest req, @RequestParam(value = "ctId", required = true) int ctId, Model model) {
		
		boolean r = ctSvc.deleteOneCenter(ctId);
		
		if( r ) {
			System.out.println(ctId + "번 센터 삭제 성공.");
			return "redirect:admin_center.bom";
		} else {
			System.out.println(ctId + "번 센터 삭제 실패.");
			return "";
		}
	}
		
		
//	메인홈 슬릭이미지 좋아요 많은 순서대로...
	@RequestMapping(value = "home.bom", method = RequestMethod.GET)
	public ModelAndView showMainImg() {

		List<CenterRowVO> ctList = ctSvc.selectAllCentersForVirtualRowLikes();

		ModelAndView mav = new ModelAndView();
		mav.addObject("ctList", ctList);
		mav.setViewName("home");
		return mav;
	}


//	 국가상담센터페이지 연결.
	@RequestMapping(value = "center_contacts.bom", method = RequestMethod.GET)
	public ModelAndView CenterContacts() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_CALL);
		mav.setViewName("board/center_contacts");
		return mav;
	}
}
