/**
 * Project Name:vr-admin
 * File Name:DateUtil.java
 * Package Name:com.sd.vr.admin.utils
 * Date:2017年3月6日下午1:33:09
 *
 */

package com.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:DateUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月6日 下午1:33:09 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
public class DateUtil extends DateUtils {

	public static final String FMT_DATE_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String FMT_DATE_YYYYMMDD = "yyyyMMdd";

	public static final String FMT_DATE_YYMMDD = "yyMMdd";

	public static final String FMT_DATE_YYYY = "yyyy";

	public static final String FMT_DATE_YYMM = "yyMM";

	public static final String FMT_DATE_YYYYMM = "yyyyMM";

	public static final String FMT_DATE_YYYYMMDDHHmmss = "yyyyMMddHHmmss";

	public static final String FMT_DATE_YYMMDDHHmm = "yyMMddHHmm";

	public static final String FMT_DATE_HHmm = "HHmm";

	private static String defaultDatePattern = "yyyy-MM-dd";

	private static  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static String timePattern = "HH:mm";

	public static String getDatePattern() {
		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return getDatePattern() + " HH:mm:ss";
	}

	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	public static String getTodayTime() {
		Date today = new Date();
		return format(today, getDateTimePattern());
	}

	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
	}

	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}

	public static Date parseRDate(String strDate) throws ParseException {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
		c.add(5, 1);
		c.add(13, -1);

		return c.getTime();
	}

	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, n);
		return cal.getTime();
	}

	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (logger.isDebugEnabled()) {
			logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return date;
	}

	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			logger.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			logger.error("Could not convert '" + strDate + "' to a date, throwing exception", pe);

			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}

	public static String formatDate(Date date, String nFmt) {
		SimpleDateFormat fmtDate = new SimpleDateFormat(nFmt);
		return fmtDate.format(date);
	}

	public static String getCurrentDateTime() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	public static String getCurrentTime() {
		return formatDate(new Date(), "HHmm");
	}

	public static String getCurrentDate() {
		return formatDate(new Date(), "yyyyMMdd");
	}

	public static String getCurrentYYYYMM() {
		return formatDate(new Date(), "yyyyMM");
	}

	public static String getCurrentYYYY() {
		return formatDate(new Date(), "yyyy");
	}

	public static String convert(String dStr, String inFormat, String outFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
		Date d = null;
		try {
			d = sdf.parse(dStr);
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		}

		return dateToString(d, outFormat);
	}

	public static final String dateToString(Date currdate, String strFormat) {
		String returnDate = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			if (currdate == null) {
				return returnDate;
			}
			returnDate = sdf.format(currdate);
		} catch (NullPointerException e) {
		}
		return returnDate;
	}

	public static final boolean checkIsDate(String strDate) {
		Date returnDate = null;
		try {
			if (strDate.length() == 4) {
				returnDate = parse(strDate, "yyyy");
			} else if (strDate.length() == 6) {
				returnDate = parse(strDate, "yyyyMM");
			} else if (strDate.length() == 8) {
				returnDate = parse(strDate, "yyyyMMdd");
			} else if (strDate.length() == 10) {
				returnDate = parse(strDate, "yyyyMMddHH");
			} else if (strDate.length() == 12) {
				returnDate = parse(strDate, "yyyyMMddHHmm");
			}
		} catch (Exception e) {
			return false;
		}
		if (returnDate != null) {
			return true;
		}
		return false;
	}

	public static int getWeekInYear(Date date, int startOfWeek) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.setFirstDayOfWeek(startOfWeek);

		return cld.get(3);
	}

	public static int getDateDiff(Date d1, Date d2) {
		double nd = 86400000.0D;
		Long diff = Long.valueOf(Math.round(Math.abs(d1.getTime() - d2.getTime()) / nd + 0.5D));
		return diff.intValue();
	}

	public static boolean compareNowDateByDay(String dateIn) {
		boolean flag = false;
		try {
			long threeDayMilli = 259200000L;// 差三天的毫秒数
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = sdf.parse(sdf.format(new Date()));
			Date in = sdf.parse(dateIn);
			long l = in.getTime() - now.getTime();
			if (l >= threeDayMilli) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	/**
	 *
	 *
	 * 方法描述: [获取当前月第一天]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月19日-下午7:43:19<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * String
	 *
	 */
	public static String getMouthFirst(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return first+" 00:00:00";
	}

	/**
	 *
	 *
	 * 方法描述: [获取当前月最后一天.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月19日-下午7:43:05<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * String
	 *
	 */
	public static String getMouthLast(){

		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		return last+" 23:59:59";
	}

	/**
	 * 获取当年的第一天
	 * @param year
	 * @return
	 */
	public static String getCurrYearFirst(){
		Calendar currCal=Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return format.format(getYearFirst(currentYear))+" 00:00:00";
	}
	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}


	/**
	 * 获取当年的最后一天
	 * @param year
	 * @return
	 */
	public static String getCurrYearLast(){
		Calendar currCal=Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return format.format(getYearLast(currentYear))+" 23:59:59";
	}

	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}


	public static void main(String[] args) {

		System.out.println(getCurrYearFirst());
	}
}
