package com.nuriweb.mybom;


import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nuriweb.mybom.model.dao.impl.VisitorMySqlDAOImpl;
import com.nuriweb.mybom.model.dao.inf.IVisitorsDAO;
import com.nuriweb.mybom.model.vo.AdminVO;

@WebListener
public class SessionListener implements HttpSessionListener {

//	@Autowired
	IVisitorsDAO viDao;
	
	public void sessionCreated(HttpSessionEvent arg0) {
		
//      this.viDao = new VisitorMySqlDAOImpl();
		
		HttpSession ses = arg0.getSession();
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ses.getServletContext());
	  	
		
		viDao = ctx.getBean(VisitorMySqlDAOImpl.class);
		
		System.out.println(viDao + "tt");
      //
      
       int dailyVisitors = 0;
       int totalVisitors = 0;
       
       try {
    	   viDao.insertTotalVistors();
    	   
    	   		//오늘 visitor 수
    	  // dailyVisitors = viDao.selectDailyVisitors();
                        // total visitor 수
    	   totalVisitors = viDao.selectToatalVistors();
    	   
    	   System.out.println("ses - " + dailyVisitors);
    	   
       } catch (Exception e) {
              e.printStackTrace();
              System.out.println("ERROR ON SESSION liST");
        }

        int today = viDao.selectDailyVisitors();
		int todaymone =  viDao.selectOneDayBefore();
		int todaymtwo =  viDao.selectTwoDayBefore();
		int todaymthree =  viDao.selectThreeDayBefore();
		int todaymfour =  viDao.selectFourDayBefore();
		int todaymfive =  viDao.selectFiveDayBefore();
		int todaymsix =  viDao.selectSixDayBefore();
       HttpSession sess = arg0.getSession();
       
       System.out.println(">> sess: todaymone " + todaymone);
       
       sess.setAttribute("totalVisitors", totalVisitors); // 전체 방문자 수
       sess.setAttribute("today", today); // 오늘 방문자 수
       sess.setAttribute("todaymone",todaymone);
       sess.setAttribute("todaymtwo",todaymtwo);
       sess.setAttribute("todaymthree",todaymthree);
       sess.setAttribute("todaymfour",todaymfour);
       sess.setAttribute("todaymfive",todaymfive);
       sess.setAttribute("todaymsix",todaymsix);
       
   }


    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
     // TODO Auto-generated method stub
    }
}