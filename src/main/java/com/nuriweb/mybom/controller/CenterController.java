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

//	?????? ????????? ???????????? ????????? ??? ??????.
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
			region = "???????????????";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "???????????????");
			break;
		case "kyeong":
			region = "?????????";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "?????????");
			break;
		default:
			break;
		}

		switch (category) {
		case "all":
			category = "all";
			break;
		case "domestic":
			category = "???????????????";
			break;
		case "sexualViolence":
			category = "??????????????????";
			break;
		case "teenager":
			category = "??????????????????";
			break;
		case "suicide":
			category = "??????????????????";
			break;
		case "multicultural":
			category = "???????????????";
			break;
		default:
			break;
		}

		switch (tag) {
		case "all":
			tag = "all";
			break;
		case "friend":
			tag = "??????";
			break;
		case "married":
			tag = "??????";
			break;
		case "socialRelationship":
			tag = "????????????";
			break;
		case "couple":
			tag = "??????";
			break;
		case "family":
			tag = "??????";
			break;
		case "study":
			tag = "??????";
			break;
		case "company":
			tag = "??????";
			break;
		case "career":
			tag = "??????";
			break;
		case "job":
			tag = "??????";
			break;
		case "childcare":
			tag = "??????";
			break;
		case "overseas":
			tag = "????????????";
			break;
		case "addicted":
			tag = "??????";
			break;
		case "eatingDisorder":
			tag = "????????????";
			break;
		case "sexLife":
			tag = "?????????";
			break;
		case "sexualMinorities":
			tag = "????????????";
			break;
		case "emotionalControl":
			tag = "????????????";
			break;
		case "selfHarm":
			tag = "??????";
			break;
		case "suicide":
			tag = "??????";
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
			System.out.println(">> ????????? ??????????????? ?????? pg: " + pageNumber);
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
				ct.setsLikes(likes); // ???????????? ??????
			}

			int ctSize = ctList.size();
			System.out.println("pg ????????? ??????????????? ????????????: " + ctSize + "???");
			mav.addObject("ctList", ctList);
			mav.addObject("ctSize", ctSize);
			mav.addObject("pn", pageNumber); // ????????? ????????? ??????
			mav.addObject("tagList", tagList);
			mav.addObject("tagMap", tagMap); // ?????? ?????? ?????????
			mav.addObject("reg", req.getParameter("reg"));

			System.out.println("ctList controll: maxPage = " + maxPage);
			mav.addObject("maxPg", maxPage);
		} else {
		}
		return mav;
	}

	
//	????????? ????????????
//	center_detail.bom?ceId=x 
	@RequestMapping(value = "center_detail.bom", method = RequestMethod.GET)
	public ModelAndView showCenterDetail(HttpServletRequest req,HttpSession ses,
			@RequestParam(value ="pg",required = false,defaultValue ="1") int page ){
		ModelAndView mav = new ModelAndView();
		int ctId = Integer.parseInt(req.getParameter("ctId"));
		CenterVO ct = ctSvc.getOneCenter(ctId);
		int likes =likeSvc.getLikeCountOfOneCenter(ctId);
		ct.setLikes(likes); //???????????? ??????
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
			mav.addObject("msg", "????????? ?????? ??????");
			mav.addObject("center", ct);
			mav.setViewName("center/ct_detail");
			
			int maxPg = rvSvc.checkMaxPageNumberCenter(ctId);
			System.out.println(maxPg);
			if(maxPg>0) {
			if(page<=0||page>maxPg){
				System.out.println(">> ????????? ????????? ?????? ??????  pg:"+page);
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
			mav.addObject("msg", "????????? ?????? ??????");
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
		ct.setLikes(likes); //???????????? ??????
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
			System.out.println("???????????? ????????? ????????? ???????????? ????????????");
			mav.setViewName("redirect:center_detail.bom?ctId="+ctId);
			return mav;
		}
		if( ct != null ) {
			mav.addObject("msg", "????????? ?????? ??????");
			mav.addObject("center", ct);
			mav.setViewName("center/ct_detail_edit");
			int maxPg = rvSvc.checkMaxPageNumberCenter(ctId);
			System.out.println(maxPg);
			if(maxPg>0) {
			if(page<=0||page>maxPg){
				System.out.println(">> ????????? ????????? ?????? ??????  pg:"+page);
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
			mav.addObject("msg", "????????? ?????? ??????");
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
			System.out.println("ct???????????? ??????????????? ????????????");	
			mav.setViewName("redirect:center_detail.bom?ctId="+ctId+"#main_reviews");
			return mav;
			}else {
				System.out.println("??????????????? ???????????????:"+rvId);
			}
		}
		mav.setViewName("redirect:center_detail.bom?ctId="+ctId+"#main_reviews");
		return mav;
	}
	


	
	
	
	
	
	
	
//	????????? ???????????? ?????????/????????? ????????? ??? ??????.
//	@ResponseBody
	@RequestMapping(value = "center_like.bom", method = RequestMethod.GET)
	public String centerLike(HttpSession ses, Model model, @RequestParam("mbId") int mbId,
			@RequestParam("ctId") int ctId, RedirectAttributes rdAttr) {

		boolean r = likeSvc.isAleadyLiked(mbId, ctId);
		List<LikeVO> likeList = likeSvc.selectAllLikeListOfOneMember(mbId);

		// ????????? ????????? ??? ??????

		// ses ?????? ??????????????? mbPKId
		CenterRowVO ct = ctSvc.getOneCenterVir(ctId);

		if (r) { // true ?????????????????????(??????)
			likeSvc.likeDelete(mbId, ctId);

			model.addAttribute("likeList", likeList);

			System.out.println(mbId + "??? ????????? " + ctId + "??? ????????? ????????? ?????????.");
			model.addAttribute("msg", "??????");
			System.out.println(ctId + "??? ????????? ????????????: " + likeSvc.getLikeCountOfOneCenter(ctId) + "(???????????????)");
			model.addAttribute("status", "delete");
			model.addAttribute("likeCnt", ct.getsLikes() - 1);
			model.addAttribute("selStatus", null);
//			return "delete";
			ct.setsStatus(0);
			model.addAttribute("ct", ct);

		} else { // false ???????????????
			likeSvc.likeAdd(mbId, ctId);

			model.addAttribute("likeList", likeList);

			System.out.println(mbId + "??? ????????? " + ctId + "??? ????????? ????????????.");
			model.addAttribute("msg", "??????");
			// model.addAttribute("likeCnt", likeSvc.getLikeCountOfOneCenter(ctId)+1);
			System.out.println(ctId + "??? ????????? ????????????: " + likeSvc.getLikeCountOfOneCenter(ctId) + "(???????????????)");
			model.addAttribute("status", "like");
			model.addAttribute("likeCnt", ct.getsLikes() + 1);

			
			noSvc.insertLikeCenterNotifi(mbId, ct.getsName(), "", MyCode.LikeCenterNotifi, "");
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
			ses.setAttribute("noList", noList2);
			
			model.addAttribute("selStatus", "selected");
			ct.setsStatus(1);
			model.addAttribute("ct", ct);
//			return "like";

			// ????????? ????????? ?????? => ???????????? ??????!!

		}
		int likeCnt = likeSvc.getLikeCountOfOneCenter(ctId);
		ct.setsLikes(likeCnt);
		model.addAttribute("likeCnt", likeSvc.getLikeCountOfOneCenter(ctId));

		return "center/_ct_like";
	}

//	???????????? ??????/????????? ??? ??????.
	@RequestMapping(value = "center_search.bom", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView centerSearch(HttpSession ses, HttpServletRequest req,
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "reg", required = false, defaultValue = "all") String region,
			@RequestParam(value = "sel_category", required = false, defaultValue = "all") String category,
			@RequestParam(value = "sel_tag", required = false, defaultValue = "all") String tag,
			@RequestParam(value = "sel_filter", required = false, defaultValue = "recent") String orderBy,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {

		System.out.println(">> ?????? ?????????: " + keyword);
		// List<CenterRowVO> ctList =
		// ctSvc.selectAllCentersForVirtualRowKeyword(pageNumber,
		// ctSvc.BASIC_CT_PAGE_SIZE, keyword);
		System.out.println(">> ????????????: " + category);
		System.out.println(">> ??????: " + tag);
		System.out.println(">> ????????????: " + orderBy);

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
			region = "???????????????";
			break;
		case "0":
			region = "???????????????";
			break;
		case "kyeong":
			region = "?????????";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "?????????");
			break;
		case "1":
			region = "?????????";
//				ctList = ctSvc.selectAllCentersForVirtualRowRegion(pageNumber, ctSvc.BASIC_CT_PAGE_SIZE, "?????????");
			break;
		default:
			break;
		}

		switch (category) {
		case "all":
			category = "all";
			break;
		case "domestic":
			category = "???????????????";
			break;
		case "sexualViolence":
			category = "??????????????????";
			break;
		case "teenager":
			category = "??????????????????";
			break;
		case "suicide":
			category = "??????????????????";
			break;
		case "multicultural":
			category = "???????????????";
			break;
		default:
			break;
		}

		switch (tag) {
		case "all":
			tag = "all";
			break;
		case "friend":
			tag = "??????";
			break;
		case "married":
			tag = "??????";
			break;
		case "socialRelationship":
			tag = "????????????";
			break;
		case "couple":
			tag = "??????";
			break;
		case "family":
			tag = "??????";
			break;
		case "study":
			tag = "??????";
			break;
		case "company":
			tag = "??????";
			break;
		case "career":
			tag = "??????";
			break;
		case "job":
			tag = "??????";
			break;
		case "childcare":
			tag = "??????";
			break;
		case "overseas":
			tag = "????????????";
			break;
		case "addicted":
			tag = "??????";
			break;
		case "eatingDisorder":
			tag = "????????????";
			break;
		case "sexLife":
			tag = "?????????";
			break;
		case "sexualMinorities":
			tag = "????????????";
			break;
		case "emotionalControl":
			tag = "????????????";
			break;
		case "selfHarm":
			tag = "??????";
			break;
		case "suicide":
			tag = "??????";
			break;
		case "0":
			tag = "??????";
			break;
		case "1":
			tag = "??????";
			break;
		case "2":
			tag = "????????????";
			break;
		case "3":
			tag = "??????";
			break;
		case "4":
			tag = "??????";
			break;
		case "5":
			tag = "??????";
			break;
		case "6":
			tag = "??????";
			break;
		case "7":
			tag = "??????";
			break;
		case "8":
			tag = "??????";
			break;
		case "9":
			tag = "??????";
			break;
		case "10":
			tag = "????????????";
			break;
		case "11":
			tag = "??????";
			break;
		case "12":
			tag = "????????????";
			break;
		case "13":
			tag = "?????????";
			break;
		case "14":
			tag = "????????????";
			break;
		case "15":
			tag = "????????????";
			break;
		case "16":
			tag = "??????";
			break;
		case "17":
			tag = "??????";
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
			System.out.println(">> ????????? ??????????????? ?????? pg: " + pageNumber);
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
				ct.setsLikes(likes); // ???????????? ??????
			}

			int ctSize = ctList.size();
			System.out.println("pg ????????? ??????????????? ????????????: " + ctSize + "???");
			mav.addObject("ctList", ctList);
			mav.addObject("ctSize", ctSize);
			mav.addObject("pn", pageNumber); // ????????? ????????? ??????
			mav.addObject("tagList", tagList);
			mav.addObject("tagMap", tagMap); // ?????? ?????? ?????????
			mav.addObject("reg", region);

			System.out.println("ctList controll: maxPage = " + maxPage);
			mav.addObject("maxPg", maxPage);
		} else {
			System.out.println("center ?????? ??????");
		}
		return mav;
	}

//	 ????????? ???????????? ????????? ??? ??????.(????????????)
	@RequestMapping(value = "admin_center.bom", method = RequestMethod.GET)
	public ModelAndView AdminCenter(@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {

		ModelAndView mav = new ModelAndView("admin/admin_centerlist");

		int maxPage = ctSvc.checkMaxPageNumber(ctSvc.ADMIN_CT_PAGE_SIZE);
		if (pageNumber <= 0 || pageNumber > maxPage) {
			System.out.println(">> ????????? ??????????????? ?????? pg: " + pageNumber);
			mav.setViewName("admin_center.bom?pg=1");
		}

		List<CenterVO> ctList = ctSvc.selectAllCenters(pageNumber, ctSvc.ADMIN_CT_PAGE_SIZE);

		if (ctList != null) {
			int ctSize = ctSvc.getCountOfAllCenters();
			mav.addObject("msg", "pg ????????? ??????????????? ????????????: " + ctSize + "???");
			mav.addObject("ctList", ctList);
			mav.addObject("ctSize", ctSize);
			System.out.println("ctSize = " + ctSize);
			mav.addObject("pn", pageNumber); // ????????? ????????? ??????
			mav.addObject("maxPg", maxPage);

			System.out.println("admin ctList controll: maxPage = " + maxPage);

		} else {
		}
		return mav;
	}

// ???????????? ????????? ??? ??????.(?????????)
	@RequestMapping(value = "center_new_form.bom", method = RequestMethod.GET)
	public ModelAndView centerNewForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/admin_center_add");
		return mav;
	}

	// ?????? proc
	@RequestMapping(value = "center_add.bom", method = RequestMethod.POST)
	public ModelAndView addNewCenterProc(HttpServletRequest req, MultipartFile center_img, MultipartFile program_img_1,
			MultipartFile program_img_2) {
		System.out.println("addNewCenterProc() ??????..");
		HttpSession ses = req.getSession();

		// ???????????? & realPath ??????..
		String selCat = req.getParameter("sel_category");
		String category = "";
		// category ??????
		switch (selCat) {
		case "domestic":
			category = "???????????????";
			break;
		case "sexualViolence":
			category = "??????????????????";
			break;
		case "teenager":
			category = "??????????????????";
			break;
		case "suicide":
			category = "??????????????????";
			break;
		case "multicultural":
			category = "???????????????";
			break;
		default:
			System.out.println("???????????? ?????? ??????");
			break;
		}

		// ?????? ??????
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

		String main = (mainFilePath.substring(mainFilePath.lastIndexOf("/"))).substring(1); // ?????? ???????????? ??????
		String pr1 = (pr1FilePath.substring(pr1FilePath.lastIndexOf("/"))).substring(1);
		String pr2 = (pr2FilePath.substring(pr2FilePath.lastIndexOf("/"))).substring(1);

		// center ??????
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

		int ctRetKey = ctSvc.insertNewCenterReturnKey(ct); // ?????? ????????? ct Id??????

		if (ctRetKey > 0) { // center PK ??????
			mav.addObject("centerId", ctRetKey);
			mav.addObject("center", ct);
			System.out.println("?????? ?????? ?????? " + ctRetKey);
			mav.setViewName("admin/_ct_add_tag"); // ?ctId="+ctRetKey);

		} else {
			mav.setViewName("redirect:center_new_form.bom");
			System.out.println("?????? ?????? ??????" + ct);
		}
		return mav;
	}

//	 ????????? tag??? ????????? ??? ??????. (?????????) proc
	@RequestMapping(value = "center_add_tag.bom", method = RequestMethod.POST)
	public String centerAddTagProc(HttpServletRequest req, @RequestParam(value = "centerId", required = true) int ctId,
			Model model) {

		// tag ??????
		System.out.println();
		List<String> tagList = new ArrayList<String>();
		String[] tags = req.getParameterValues("sel_tag[]");
		// tag value ????????? ???????????? ??????
		for (String tag : tags) {
			switch (tag) {
			case "check_freind":
				tagList.add("??????");
				break;
			case "check_married":
				tagList.add("??????");
				break;
			case "check_socialRelationship":
				tagList.add("????????????");
				break;
			case "check_couple":
				tagList.add("??????");
				break;
			case "check_family":
				tagList.add("??????");
				break;
			case "check_study":
				tagList.add("??????");
				break;
			case "check_company":
				tagList.add("??????");
				break;
			case "check_career":
				tagList.add("??????");
				break;
			case "check_job":
				tagList.add("??????");
				break;
			case "check_childcare":
				tagList.add("??????");
				break;
			case "check_overseas":
				tagList.add("????????????");
				break;
			case "check_addicted":
				tagList.add("??????");
				break;
			case "check_eatingDisorder":
				tagList.add("????????????");
				break;
			case "check_sexLife":
				tagList.add("?????????");
				break;
			case "check_sexualMinorities":
				tagList.add("????????????");
				break;
			case "check_emotionalControl":
				tagList.add("????????????");
				break;
			case "check_selfHarm":
				tagList.add("??????");
				break;
			case "check_suicide":
				tagList.add("??????");
				break;
			}
			System.out.println(tag);
		}

		for (String tag : tagList) {
			System.out.println("????????????: " + tag);
		}

		boolean b = tgSvc.insertTags(tagList.toArray(new String[tagList.size()]), ctId);
		if (b) {
			System.out.println("????????? ???????????? ??????.");
			return "redirect:admin_center.bom";
		} else {
			System.out.println("????????? ???????????? ??????.");
			return "admin/_ct_add_tag";
		}
	}

	
	// ???????????? ??????????????? ??? ??????.(?????????)
		@RequestMapping(value = "center_edit_form.bom", method = RequestMethod.GET)
		public ModelAndView centerEditForm(HttpServletRequest req, @RequestParam(value = "ctId", required = true) int ctId,
				Model model) {
			
			ModelAndView mav = new ModelAndView();
			
			CenterVO ct = ctSvc.getOneCenter(ctId);
			if( ct != null ) {
				mav.addObject("ct", ct);
				mav.setViewName("admin/admin_center_update");
			} else {
				System.out.println("????????? ?????? ???????????? ??????");
				mav.setViewName("redirect:admin_center.bom");
			}
			return mav;
		}
		
		// ?????? ???????????? proc(?????????)
		@RequestMapping(value = "center_edit.bom", method = RequestMethod.POST)
		public ModelAndView centerEditProc(HttpServletRequest req, MultipartFile center_img, MultipartFile program_img_1, MultipartFile program_img_2) {
			System.out.println("centerEditProc() ??????..");
			HttpSession ses = req.getSession();

			// ???????????? & realPath ??????..
			String selCat = req.getParameter("sel_category");
			String category = "";
			// category ??????
			switch (selCat) {
			case "domestic":
				category = "???????????????";
				break;
			case "sexualViolence":
				category = "??????????????????";
				break;
			case "teenager":
				category = "??????????????????";
				break;
			case "suicide":
				category = "??????????????????";
				break;
			case "multicultural":
				category = "???????????????";
				break;
			default:
				System.out.println("???????????? ?????? ??????");
				break;
			}

			// ?????? ??????
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

			String main = (mainFilePath.substring(mainFilePath.lastIndexOf("/"))).substring(1); // ?????? ???????????? ??????
			String pr1 = (pr1FilePath.substring(pr1FilePath.lastIndexOf("/"))).substring(1);
			String pr2 = (pr2FilePath.substring(pr2FilePath.lastIndexOf("/"))).substring(1);

			// center ??????
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

			boolean r = ctSvc.updateOneCenter(ct); // ?????? ????????? ct Id??????

			if ( r) { // center PK ??????
				mav.addObject("centerId", ct.getId());
				mav.addObject("center", ct);
				System.out.println("?????? ?????? ?????? " + ct.getId());
				mav.setViewName("admin/admin_centerlist"); // ?ctId="+ctRetKey);

			} else {
				mav.setViewName("redirect:center_edit_form.bom");
				System.out.println("?????? ?????? ??????" + ct);
			}

			return mav;
		}
		
		
// ???????????? ????????? ??? ??????.(?????????)
		@RequestMapping(value = "delete_center_{ctId}.bom")
		@ResponseBody
		public String deleteReserveID(@PathVariable("ctId") int ctId) {
			System.out.println(ctId);
			System.out.println("????????????");
			
			//???????????? ??????..
			boolean tDel = tgSvc.deleteAllTagsOfCenter(ctId);
			if(tDel) System.out.println("????????????" + ctId);

			CenterVO ct = ctSvc.getOneCenter(ctId);
			boolean b = ctSvc.deleteOneCenter(ct);
			if(b) System.out.println("??????????????????" + ctId);
			
			System.out.println(tDel);
			System.out.println(b);
			return b ? "success" : "fail";

		}
		
		
	@RequestMapping(value = "center_delete.bom", method = RequestMethod.POST)
	public String centerDeleteProc(HttpServletRequest req, @RequestParam(value = "ctId", required = true) int ctId, Model model) {
		
		boolean r = ctSvc.deleteOneCenter(ctId);
		
		if( r ) {
			System.out.println(ctId + "??? ?????? ?????? ??????.");
			return "redirect:admin_center.bom";
		} else {
			System.out.println(ctId + "??? ?????? ?????? ??????.");
			return "";
		}
	}
		
		
//	????????? ??????????????? ????????? ?????? ????????????...
	@RequestMapping(value = "home.bom", method = RequestMethod.GET)
	public ModelAndView showMainImg() {

		List<CenterRowVO> ctList = ctSvc.selectAllCentersForVirtualRowLikes();

		ModelAndView mav = new ModelAndView();
		mav.addObject("ctList", ctList);
		mav.setViewName("home");
		return mav;
	}


//	 ??????????????????????????? ??????.
	@RequestMapping(value = "center_contacts.bom", method = RequestMethod.GET)
	public ModelAndView CenterContacts() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory",Materials.SUB_CATEGORY_CALL);
		mav.setViewName("board/center_contacts");
		return mav;
	}
}
