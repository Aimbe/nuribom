package com.nuriweb.mybom.model.dao.impl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nuriweb.mybom.model.dao.inf.IVisitorsDAO;
import com.nuriweb.mybom.model.vo.VisitorVO;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.jdbc.datasource.DataSourceUtils;
//@Repository
public class VisitorMySqlDAOImpl implements IVisitorsDAO {

	
	public static String insertTotalVistors ="insert into Visitor(visit_time)"
			+ "values(now())";
	
	public static String selectDailyVisitors ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -1 day) AND date_add(now(), interval 0 day)";
	
	
	public static String selectToatalVistors ="SELECT COUNT(*) AS totalVisitors from Visitor";
	
	public static String selectDayOfWeekWithNumber ="SELECT visit_date, SUBSTR( _UTF8'일월화수목금토', DAYOFWEEK(date), 1) AS week FROM visitors";

	
	public static String selectDayOfWeekWithNumber2 ="SELECT visit_date, DAYOFWEEK(visit_date) AS week_n," + 
			"CASE DAYOFWEEK(date)" + "WHEN '1' THEN 'sun'" + "WHEN '2' THEN 'mon'" + "WHEN '3' THEN 'Tue'" + 
			"WHEN '4' THEN 'Wed'" +"WHEN '5' THEN 'Thur'" + "WHEN '6' THEN 'Fri'" + 
			"WHEN '7' THEN 'Sat'" + "END AS week FROM visitor"; 
	
	
	public static String seletTodayWithNumber = "SELECT COUNT(*) as dailyvisitor-1 from Visitor" + 
			"WHERE FORMAT(visit_time, 'YYYY-MM-DD') = FORMAT(now(), 'YYYY-MM-DD')";
	
	
	public static String selectAllstacks ="SELECT COUNT(*) as dailyvisitor from Visitor where visit_time >= date_add(now(), interval -2 day)";
	
	
	public static String selectPeriod = "SELECT * FROM visitor WHERE date_format(date_time, '%Y-%m-%d') BETWEEN '2020-01-11' AND '2020-01-18'";

	


	public static String slectOneBf ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -2 day) AND date_add(now(), interval -1 day)";
	public static String slectTwoBf ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -3 day) AND date_add(now(), interval -2 day)";
	public static String slectThreeBf ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -4 day) AND date_add(now(), interval -3 day)";
	public static String slectFourBf ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -5 day) AND date_add(now(), interval -4 day)";
	public static String slectFiveBf ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -6 day) AND date_add(now(), interval -5 day)";
	public static String slectSixBf ="SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -7 day) AND date_add(now(), interval -6 day)";

	
	
	//SELECT count(*) as oneweek FROM visitor WHERE visit_time BETWEEN date_add(now(), interval -14 day) AND date_add(now(), interval -7 day)
	
	JdbcTemplate jtem;
	private DataSource ds; 
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		this.jtem = new JdbcTemplate(ds);
		//jtem.getDataSource();
	
	}
	
	
	public String getDateDay(String date, String dateType) throws Exception {
	    String day = "" ;
	     
	    SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
	    Date nDate = dateFormat.parse(date) ;
	     
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	     
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    
	    switch(dayNum){
	        case 1: day = "Sun";
	            break ;
	        case 2: day = "Mon";
	            break ;
	        case 3: day = "Tue";
	            break ;
	        case 4: day = "Wed";
	            break ;
	        case 5: day = "Thur";
	            break ;
	        case 6:  day = "Fri";
	            break ;
	        case 7: day = "Sun";
	            break ;
	             
	    }
	    return day ;
	}
	

	
	
	
	
	@Override
	public boolean insertTotalVistors() {
		
		int r = this.jtem.update(insertTotalVistors);
		
		return r ==1;
	}

	@Override
	public int selectDailyVisitors() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(selectDailyVisitors, Integer.class);

	}

	@Override
	public int selectToatalVistors() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(selectToatalVistors,Integer.class);
	}


	@Override
	public int selectOneDayBefore() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(slectOneBf,Integer.class);
	}


	@Override
	public int selectTwoDayBefore() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(slectTwoBf,Integer.class);
	}


	@Override
	public int selectThreeDayBefore() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(slectThreeBf,Integer.class);
	}


	@Override
	public int selectFourDayBefore() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(slectFourBf,Integer.class);
	}


	@Override
	public int selectFiveDayBefore() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(slectFiveBf,Integer.class);
	}


	@Override
	public int selectSixDayBefore() {
		// TODO Auto-generated method stub
		return jtem.queryForObject(slectSixBf,Integer.class);
	}



	
	
	

}
