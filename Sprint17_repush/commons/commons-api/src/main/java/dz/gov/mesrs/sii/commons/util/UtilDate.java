package dz.gov.mesrs.sii.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
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
	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static DateFormat dayOnlyFormat = new SimpleDateFormat("EEEE");

	/**
	 * Convert String to Calendar
	 * 
	 * @param value
	 * @return the Calendar format
	 * @throws ParseException 
	 */
	public static Calendar convertToDateTime(String value, String timeZone) throws ParseException {
		if (value == null || value.length() == 0) {
			return null;
		}
		Date d = null;
			if (value.contains("T")) {
				dateTime.setTimeZone(TimeZone.getTimeZone(timeZone));
				d = dateTime.parse(value);
			} else {
				dateTimeSansT.setTimeZone(TimeZone.getTimeZone(timeZone));
				d = dateTimeSansT.parse(value);
			}
		
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	/**
	 * Convert Date to String
	 * 
	 * @param date
	 * @return the string date
	 */

	public static String convertToString(Date date) {
		if (date == null) {
			return null;
		}		
			String lDate = dateTimeSansT.format(date);
			return lDate;
		
	}

	public static Calendar convertToDateOnly(String value, String timeZone) throws ParseException {
		if (value == null || value.length() == 0) {
			return null;
		}
		Date d = null;
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		d = dateFormat.parse(value);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	
	public static Date addDays(Date d, Double days){
		Calendar c = Calendar.getInstance();
		c.setTime(d); 
		c.add(Calendar.DATE, days.intValue()); 
		return c.getTime();
		
	}
	
 /**
  * Get the day name
  * @param date
  * @return Day Name
  */
	public static String getDayName(Date date){
		return dayOnlyFormat.format(date);
	}
	
	
	
	
	public static void main(String[] arg) {

		Calendar endDate = null;
		try {
			endDate = UtilDate.convertToDateTime("2099-12-07T23:00:00", "GMT+1");
		
		// Date systemDate = new Date();
		String beginDateString = UtilDate.convertToString(endDate.getTime());
		System.out.println(beginDateString);
		endDate = UtilDate.convertToDateOnly("2099-12-07", "GMT+1");
		System.out.println(getDayName(endDate.getTime()));
		beginDateString = UtilDate.convertToString(endDate.getTime());
		System.out.println(beginDateString);
		System.out.println(Calendar.getInstance().get(Calendar.MONTH));
		System.out.println(addDays(endDate.getTime(), new Double(3.5)));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}

}
