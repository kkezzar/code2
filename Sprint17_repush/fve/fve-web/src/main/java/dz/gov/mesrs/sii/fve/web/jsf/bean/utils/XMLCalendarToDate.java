package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Java program to convert XMLGregorianCalendar to Date and inverse i.e.
 * java.util.Date to XMLGregorianCalendar. If you are using XJC to create Java
 * classes from XML Schema or XSD file, By default JAXB map XSD data types
 * xs:date, xs:time and xs:dateTime to XMLGregorianCalendar in Java.
 * 
 * @author Javin Paul
 */
public class XMLCalendarToDate {

	/**
	 * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
	 * [XMLCalendarToDate.toXMLGregorianCalendar] Method 
	 * @author yselmane  on : 6 mai 2014  09:25:24
	 * @param date
	 * @return XMLGregorianCalendar date
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
			ex.printStackTrace();
		}
		return xmlCalendar;
	}

	/**
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 * [XMLCalendarToDate.toDate] Method 
	 * @author yselmane  on : 6 mai 2014  09:25:58
	 * @param calendar
	 * @return Java Date
	 */
	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

}
