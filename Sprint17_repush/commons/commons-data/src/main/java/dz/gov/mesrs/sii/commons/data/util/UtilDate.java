package dz.gov.mesrs.sii.commons.data.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <b>File :</b> UtilDate.java<br>
 * <b>Creation date :</b> 18 March 2013<br>
 * <b>Description :</b><br>
 * Processing formats dates
 * 
 * @author j.beldi
 * 
 */

public class UtilDate {

	
	private static DateFormat dateTime = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss");
	private static DateFormat dateTimeSansT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**
	 * Convert String to Calendar
	 * @param value
	 * @return the Calendar format
	 */
	public static Calendar convertToDateTime(String value,String timeZone) {
		if (value == null || value.length() == 0) {
			return null;
		}
		Date d = null;

		try {
			dateTime.setTimeZone(TimeZone.getTimeZone(timeZone));
			d = dateTime.parse(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	/**
	 * Convert Date to String
	 * @param date
	 * @return the string date
	 */

	public static String convertToString(Date date) {
		if (date == null) {
			return null;
		}
		try {
			String lDate = dateTimeSansT.format(date);
			return lDate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * [UtilDate.getYeafOfDate] Method 
	 * @author MAKERRI Sofiane  on : 17 févr. 2015  15:25:52
	 * @param date
	 * @return
	 */
	public static int getYeafOfDate(Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			return year;
		}
		return 0;
	}
	
	public static void main (String [] arg){
		
		Calendar endDate = null;
		endDate = UtilDate
				.convertToDateTime("2099-12-07 23:00:00","GMT+1");
		//Date systemDate = new Date();
		String beginDateString = UtilDate
				.convertToString(endDate.getTime());
		System.out.println(beginDateString);
	}
	
}
