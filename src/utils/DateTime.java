package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
	
	public String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH);   
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND);    
		
		String currentDate = year + "-" + month + "-" + date + " " +hour + ":" +minute + ":" + second;
		
		return currentDate; 
	}
	
	public int getPassedDays(String beginDate) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
        Date preDate = format.parse(beginDate);
        long curDate = new Date().getTime();

        int days = (int) ((curDate - preDate.getTime()) / (1000*3600*24));
		return days;
	}
}
