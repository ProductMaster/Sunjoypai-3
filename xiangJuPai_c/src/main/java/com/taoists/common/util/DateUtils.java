package com.taoists.common.util;

import java.util.Calendar;
import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;








import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import com.taoists.common.Const;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class DateUtils {
	
	public static String getDateShort(long ctime){
		if(ctime==-99){
			return "-";
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date(ctime)).toString();
	}
	public static String getDate(long ctime){
		if(ctime==-99){
			return "-";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(ctime)).toString();
	}
	
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

	public static DateTime toDateTime(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return null;
		}
		return DateTimeFormat.forPattern(PATTERN_DATETIME).parseDateTime(dateTime);
	}

	public static String toString(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATETIME);
	}

	public static String toString(String dateTime, String pattern) {
		return new DateTime(dateTime).toString(pattern);
	}

	public static String toString(LocalDate localDate, String pattern) {
		if (localDate == null) {
			return null;
		}
		return localDate.toString(pattern);
	}

	public static String today2String() {
		return new LocalDate().toString(PATTERN_DATE);
	}

	// Date类型转换为字符串 && 将字符串转换为Date类型
	public static String datetime2String(Date date){
		SimpleDateFormat sdf= new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(date);
	}
	public static String date2String(Date date){
		SimpleDateFormat sdf= new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(date);
	}
	public static Date string2Datetime(String date) {
		Date retValue = null ;
		SimpleDateFormat sdf= new SimpleDateFormat(PATTERN_DATETIME);
		try{
			retValue = sdf.parse(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retValue;
	}
	public static Date string2Date(String date) {
		Date retValue = null ;
		SimpleDateFormat sdf= new SimpleDateFormat(PATTERN_DATE);
		try{
			retValue = sdf.parse(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retValue;
	}	
	
	
	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return返回字符串格式 yyyy-MM-dd
	 */
	public static String long2ShortString(long date){
		if(date<=0){
			return "-";
		}
		SimpleDateFormat sdf =  new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String long2LongString(long date){
		if(date == -99){
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(new Date(date));
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	 public final static long String2Long(String sDate){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 ParsePosition pos = new ParsePosition(0);
    	return formatter.parse(sDate,pos).getTime();
    }	
	 public final static long String2Long(String sDate,String fomat){
		 SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		 ParsePosition pos = new ParsePosition(0);
    	return formatter.parse(sDate,pos).getTime();
    }
	
	
	/**
	 * 获取指定日期一年前时间
	 * 
	 * @return返回long格式 
	 */
	public static long getChainYear(long date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.YEAR,-1);   
		return cal.getTime().getTime();
	}

	
	public static String todayToString() {
		return new LocalDate().toString("yyyyMMdd");
	}
	
	/** 获取测试的年月日字串 **/
/*	public static String getTestDateShortString(){
		return "2013-3-1";		
	}*/
	
	/**
     * 传入天数得到毫秒数


     * @param day
     * @return
     */
    public static long getMillsForDay(int day){
    	return 86400000 * day;
    }
    
    /**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	 /**
     * 获得某天一天起始时间的long数值
     * @param sDate
     * @return
     */ 
     public final static long String2LongStart(String sDate){
         sDate+=" 00:00:00";
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         ParsePosition pos = new ParsePosition(0);
        return formatter.parse(sDate,pos).getTime();
    }
	   
	/**
	 * 获得某天一天结束时间的long数值
	 * @param sDate
	 * @return
	 */ 
	 public final static long String2LongEnd(String sDate){
		 sDate+=" 23:59:59";
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 ParsePosition pos = new ParsePosition(0);
    	return formatter.parse(sDate,pos).getTime();
    }
}
