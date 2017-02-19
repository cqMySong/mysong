package com.cq.mysong.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *------------------☆重庆MySong☆------------------------
 * @author ：mySong		@Date : 2017-2-10 
 * @version ：  V1.0
 * @Description :   
 *	 日期操作工具类
 *------------------☆重庆MySong☆------------------------
 */
public class DateUtil {
	public static String DATEFORMT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static String DATEFORMT_YMD = "yyyy-MM-dd";
	private static int DAY = 24;
	private static int HOUR = 60;
	private static int MINUTE = 60;
	private static int SECOND = 1000;

	/**
	 * 日期大小判断
	 */
	public static boolean isBeforeDate(Date begDate, Date endDate) {
		return begDate.before(endDate);
	}

	/**
	 * 按照默认格式 格式化日期为文本字符
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, DATEFORMT_YMD);
	}

	/**
	 * 按照自定义格式 格式化日期为文本字符
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 按照自定义格式文本日期
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat(format);
			date = (Date) df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, DATEFORMT_YMD);
	}
	/**
	 *返回年 
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 *返回月
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}
	
	/**
	 *返回日
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 *返回小时 
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}
	/**
	 *返回分钟 
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 *返回秒
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}
	/**
	 *返回日期的毫秒数
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	/**
	 *添加天 可以为负数表示当前日期减去的天数
	 */
	public static Date addDay(Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * DAY * HOUR*MINUTE * SECOND);
		return c.getTime();
	}
	/**
	 * 当前日期加年
	 */
	public static Date addYear(Date date, int year){
		java.util.Calendar c = java.util.Calendar.getInstance();
		int monthtep = getMonth(date)-1;
		int yeartep = getYear(date)+year;
		int daytep = getDay(date);
		c.set(yeartep,monthtep ,daytep);
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		c1.set(yeartep, monthtep,1);
		if(daytep>c1.getActualMaximum(Calendar.DAY_OF_MONTH))
			daytep = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(yeartep, monthtep,daytep);
		return c.getTime();
	}
	/**
	 * 当前日期加月
	 */
	public static Date addMonth(Date date, int month){
		java.util.Calendar c = java.util.Calendar.getInstance();
		int monthtep = getMonth(date)+month-1;
		int yeartep = getYear(date);
		int daytep = getDay(date);
		c.set(yeartep, monthtep,daytep);
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		c1.set(yeartep, monthtep,1);
		if(daytep>c1.getActualMaximum(Calendar.DAY_OF_MONTH))
			daytep = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(yeartep, monthtep,daytep);
		return c.getTime();
	}
	
	/**
	 * 两个日期相差天数
	 */
	public static int betweenDays(Date bdate, Date edate){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		if(bdate.before(edate)){
			c1.setTime(bdate);
			c2.setTime(edate);
		}else{
			c2.setTime(bdate);
			c1.setTime(edate);
		}
		return (int) ((c2.getTimeInMillis()-c1.getTimeInMillis())/(DAY * HOUR*MINUTE * SECOND));
	}
	/**
	 * 当前日期月份对应的第一天日期
	 */
	public static Date getMonthFirstDay(Date date){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		return c1.getTime();
	}
	/**
	 * 返回两个日期相差的月数
	 */
	public static int betweenMonths(Date bdate, Date edate,boolean hasDay){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		if(BaseUtil.isEmpty(bdate)||BaseUtil.isEmpty(edate)) 
			return 0;
		if(bdate.before(edate)){
			c1.setTime(bdate);
			c2.setTime(edate);
		}else{
			c1.setTime(edate);
			c2.setTime(bdate);
		}
		int months = (c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))*12+(c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH));
		if(hasDay){
			if(c2.get(Calendar.DAY_OF_MONTH)+1<c1.get(Calendar.DAY_OF_MONTH)){
				months = months-1;
			}
		}
		return months;
	}
	
	
	/**
	 * 返回当期月的下一个月1号
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getNextFirstDate(int curYear,int curMonth){
		Calendar c = Calendar.getInstance();
		c.set(curYear, curMonth, 1);
		Date curDate = c.getTime();
		return curDate;
	}
	
	/**
	 * 获得上一个月1号
	 * @param curYear
	 * @param curMonth
	 * @return
	 */
	public static Date getPreFirstDate(int curYear,int curMonth){
		Calendar c = Calendar.getInstance();
		c.set(curYear, curMonth, 1);
		Date curDate = c.getTime();
		return addMonth(curDate, -1);
	}
	/**
	 * 获取当前日期的总天数
	 * @param curDate
	 * @return
	 */
	public static int getCurMonthDays(Date curDate){
		Calendar c = Calendar.getInstance();
		c.set(getYear(curDate), getMonth(curDate), 1);
		Date curMm = addDay(c.getTime(), -1);
		return getDay(curMm);
	}
	
	/**
	 * 得到指定月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
}
