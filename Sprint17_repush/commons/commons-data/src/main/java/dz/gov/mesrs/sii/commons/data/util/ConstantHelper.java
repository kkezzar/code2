/**
 * [dz.gov.mesrs.sii.fve.business.util.ConstantHelper.java] 
 * @author rlaib on : 20 mars 2014  11:11:14
 */
package dz.gov.mesrs.sii.commons.data.util;

/**
 * @author rlaib  on : 20 mars 2014  11:11:14
 */
public class ConstantHelper {
	
	public static final String WHERE_SQL_STR = "where";
	public static final String OR_SQL_STR = "or";
	public static final String AND_SQL_STR = "and";
	
	
	/**
	 * [ConstantHelper.LikeContain] Method 
	 * @author BELDI Jamel  on : 22 avr. 2014  18:36:50
	 * @param param
	 * @return
	 */
	public static String LikeContain(Object param) {
		String result;
		result = "'%" + param + "%'";
		return result;
	}

	
	/**
	 * [ConstantHelper.quotedString] Method 
	 * @author BELDI Jamel  on : 22 avr. 2014  18:36:42
	 * @param value
	 * @return
	 */
	public static String quotedString(String value) {
		value = "'" + value + "'";
		return value;
	}
	
	
	/**
	 * [ConstantHelper.startWith] Method 
	 * @author BELDI Jamel  on : 22 avr. 2014  18:36:34
	 * @param param
	 * @return
	 */
	public static String startWith(Object param) {
		String result;
		result = "'" + param + "%'";
		return result;
	}
	
	
	/**
	 * [ConstantHelper.strToInt] Method 
	 * @author BELDI Jamel  on : 22 avr. 2014  18:36:28
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		try {
			return Integer.parseInt(str);
			
		} catch (NumberFormatException e) {
			return -1;
		}
	}

}
