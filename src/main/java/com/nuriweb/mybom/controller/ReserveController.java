package com.nuriweb.mybom.controller;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.MemberVO;
import com.nuriweb.mybom.model.vo.ReserveVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.IBoardSVC;
import com.nuriweb.mybom.service.inf.ICenterSVC;
import com.nuriweb.mybom.service.inf.IMemberSVC;
import com.nuriweb.mybom.service.inf.IReserveSVC;
import com.nuriweb.mybom.service.inf.IReviewSVC;

@Controller
public class ReserveController {

	@Autowired
	ICenterSVC ctSVC;
	
	@Autowired
	IMemberSVC mbSVC;
	@Autowired
	IReserveSVC rsSVC;
	@Autowired
	IReviewSVC rvSVC;
	
	@Autowired
	IAllNotifiSVC noSvc;
	
	public void normal() {
		// 일반메소드
	}
	
	@RequestMapping(value = "reserve.bom",
			method = RequestMethod.GET)
	public ModelAndView newReserve(HttpServletRequest request,HttpSession ses,
			int ctId){
		ModelAndView mav = new ModelAndView();
		int mbId=-1;
		String a = "";
		if(ses.getAttribute("mbPKId")!=null&&!ses.getAttribute("mbPKId").equals("")) {
			a = String.valueOf(ses.getAttribute("mbPKId"));
			mbId = Integer.parseInt(a);
		}else {
			mav.setViewName("redirect:member_login_form.bom");
			return mav;
		}
		CenterVO ct = ctSVC.getOneCenter(ctId);
		MemberVO mb = mbSVC.selectOneMember(mbId);

		
		mav.addObject("mb",mb);
		mav.addObject("mbId",mbId);
		mav.addObject("ctId",ctId);
		mav.addObject("ct",ct);
		mav.setViewName("reserve/reserve");
		
		return mav;
	}
	
	@RequestMapping(value = "reserve_proc.bom",
			method = RequestMethod.POST)
	public ModelAndView memberNewForm(HttpServletRequest request,HttpSession ses,int ctId) {
		ReserveVO rs = new ReserveVO();
		ModelAndView mav = new ModelAndView();
		String rsTime = request.getParameter("reserveTime");
		String reserveDay = request.getParameter("reserveDay");
		String reserveNickname = request.getParameter("reserveNickname");
		String reserveMemo = request.getParameter("reserveMemo");
		String yoil ="";
		try {
			yoil = getDateDay(reserveDay, "yyyy-MM-dd");
		} catch (Exception e) {
			System.out.println("요일 회수실패");
		}
		int time = -1;
		if(rsTime!=null&&!rsTime.equals("")&&!rsTime.equals("-1")) {
			time = Integer.parseInt(rsTime);
		}else {
			mav.setViewName("redirect:reserve.bom?ctId="+ctId);
			return mav;
		}
		int mbId = -1;
		if((Integer) ses.getAttribute("mbPKId") == null ) {
			return new ModelAndView("member/mb_login_form");
		}else {
			mbId = (Integer) ses.getAttribute("mbPKId");
		}
		
		String link ="reserve.bom?ctId="+ctId;
		
		rs.setReserveUserId(mbId);
		rs.setReserveCenterId(ctId);
		rs.setReserveTime(time);
		
		if(time!=-1&&reserveDay!=null&&!reserveDay.equals("")) {
		Timestamp timestamp_1 = Timestamp.valueOf(reserveDay+" "+time+":00:00.0");
		rs.setReserveDay(timestamp_1);
		}
		rs.setReserveNickname(reserveNickname);
		rs.setReserveMemo(reserveMemo);
		System.out.println(rs.toString());
		CenterVO ct = ctSVC.getOneCenter(ctId);
		String name = ct.getName();
		boolean insertB = rsSVC.insertReserve(rs);
		if(insertB) {
			
			
			Timestamp timestamp_1 = Timestamp.valueOf(reserveDay+" "+time+":00:00.0");
			boolean b = noSvc.insertNewReserveNotifi(mbId,name, timestamp_1, 5,link);
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
			ses.setAttribute("noList", noList2);
				
			if( b) {	
				System.out.println("알림 쏴주기 완료");
			}else {
				
				System.out.println("알림 쏴주기 실패.");
			}
			
			
		}else {
			mav.setViewName("redirect:reserve.bom?ctId="+ctId);
			return mav;
		}
	
		mav.addObject("day",reserveDay);
		mav.addObject("ct",ct);
		mav.addObject("yoil",yoil);
		mav.setViewName("reserve/reserve_proc");
		mav.addObject("rs",rs);
		
		
		return mav;
	}
	
	@RequestMapping(value = "member_reserve.bom", method = RequestMethod.GET) 
	public ModelAndView myReserveList(HttpSession ses,
			@RequestParam(value = "pg",required = false,defaultValue ="1") int page){
		ModelAndView mav = new ModelAndView();
		if((Integer) ses.getAttribute("mbPKId") == null ) {
			return new ModelAndView("member/mb_login_form");
		}
		int mbId = (Integer) ses.getAttribute("mbPKId");		
		int maxPg = rsSVC.checkMaxPageNumberMyReserve(mbId);
		System.out.println("member_reserve이동maxPg:"+maxPg);
		if(maxPg>0) {
			if( page <= 0 || page > maxPg ) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + page);
				return new ModelAndView("redirect:member_reserve.bom?pg=1");
			}
		}
		List<ReserveVO> rsList = rsSVC.getAllListForMember(mbId,page);
		if(rsList!=null&& rsList.size()>0) {
			for (ReserveVO rs : rsList) {
				CenterVO ctone = ctSVC.getOneCenter(rs.getReserveCenterId());
				rs.setReserveCenterName(ctone.getName());
				rs.setReserveCenterAdress(ctone.getAddressRegion()+ctone.getAddressCity()+ctone.getAddressDetail());
			}
		}
		
		mav.addObject("pg",page);
		mav.addObject("maxPg",maxPg);
		mav.addObject("rsList",rsList);
		mav.setViewName("member/mb_reserve");
		mav.addObject("subCategory",8);
		
		return mav;
	} 
	@RequestMapping(value = "delete_reserve_{rsId}.bom")
	@ResponseBody
	public String deleteReserveID(
				@PathVariable("rsId") int rsId 
			) {
		System.out.println(rsId);
		System.out.println("리뷰삭제");
		rvSVC.deleteOneReviewRS(rsId);
		System.out.println("리뷰삭제");
		
		int b =  rsSVC.deleteReserve(rsId);
		
		return b==1? "success" : "fail";
	}
	
	
	
	@RequestMapping(value = "change_time_for_{date}_{ctId}.bom")
	@ResponseBody
	public String changeTime(
			@PathVariable("date") String date, 
			@PathVariable("ctId") int ctId 
			) {
		int[] intarray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		System.out.println(date);
		List<Integer> time = new ArrayList<Integer>();
		for (int i = 9; i < 19; i++) {
			time.add(i);
		}
		System.out.println("준비된시간: "+time.size()+"개");
		System.out.println("ctId = "+ctId);
		List<Integer> timeList = rsSVC.getTimeListOfCenter(date, ctId); // 11 13
		if(timeList!=null && timeList.size()>0) {
			for (Integer tm : timeList) {
				System.out.println(tm+"지우기");
				time.remove((Object)tm);
			}
			for (Integer tm : timeList) {
				intarray[tm] =tm;
			}
		}	
		for (int i = 0; i < intarray.length; i++) {
			System.out.println(intarray[i]);
		}
		String msg = "";
		for (int i = 9; i < 19; i++) {
			if(intarray[i]>0) {msg += "<option value='"+i+"' disabled >"+i+":00</option>";} 
			else { msg += "<option value='"+i+"'>"+i+":00</option>";} 
		}
		
		System.out.println(msg);
		
		Timestamp sd = new Timestamp(System.currentTimeMillis());
		String timegetfor = sd.toString();
		String strTodayTime = timegetfor.substring(11,13);
		int thisTime = Integer.parseInt(strTodayTime);
		
		System.out.println(strTodayTime);                                        //date 2021-21-51
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(timegetfor.toString().substring(0,10));
		System.out.println(date);
		String todayMsg = "";
		String todayMsgLate = "";
		
		if(date.equals(timegetfor.toString().substring(0,10))&&thisTime>=18) {
			todayMsgLate += "<option value='-1'>OVER</option>";
			for (int i = 9; i < 19; i++) {
				todayMsgLate += "<option value='"+i+"' disabled >"+i+":00</option>";
			}
			return todayMsgLate;
		}
		
		
		if(date.equals(timegetfor.toString().substring(0,10))) {
			System.out.println("오늘당일인데 아직 6시는 안넘은곳" +"현재시간: "+thisTime);
			for (int i = 9; i <= thisTime; i++) {
				todayMsg += "<option value='"+i+"' disabled >"+i+":00</option>";
			}
			for (int i = thisTime+1; i < 19; i++) {
				if(intarray[i]>0) {todayMsg += "<option value='"+i+"' disabled >"+i+":00</option>";} 
				else { todayMsg += "<option value='"+i+"'>"+i+":00</option>";} 
			}
			return todayMsg;
		}
		
		
		
		return  msg;
		
	}

	public String getDateDay(String date, String dateType) throws Exception {
	    String day = "" ;
	     
	    SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
	    Date nDate = dateFormat.parse(date) ;
	     
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	     
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    
	    switch(dayNum){
	        case 1:
	            day = "일";
	            break ;
	        case 2:
	            day = "월";
	            break ;
	        case 3:
	            day = "화";
	            break ;
	        case 4:
	            day = "수";
	            break ;
	        case 5:
	            day = "목";
	            break ;
	        case 6:
	            day = "금";
	            break ;
	        case 7:
	            day = "토";
	            break ;
	             
	    }
	    return day ;
	}
	
}