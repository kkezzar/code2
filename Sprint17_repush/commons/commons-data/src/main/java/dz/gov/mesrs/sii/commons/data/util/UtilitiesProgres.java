package dz.gov.mesrs.sii.commons.data.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;

/**
 * 
 * @author salem
 *
 */
public class UtilitiesProgres {

	public UtilitiesProgres() {
		
	}

	
	/**
	 * recuperer la date du jour
	 */
	public static Date getCurrentDate(){
		DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		Date date= new Date();
		Date now =null;
		try{
			
			now=dateFormat.parse(dateFormat.format(date));
			
		}catch(ParseException ex){
			ex.printStackTrace();
		}
		return now;
	}
}
