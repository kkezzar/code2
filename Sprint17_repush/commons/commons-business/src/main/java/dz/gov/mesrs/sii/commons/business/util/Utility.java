/**
 * [dz.gov.mesrs.sii.commons.business.util.Utility.java] 
 * @author MAKERRI Sofiane on : 4 janv. 2015  08:29:22
 */
package dz.gov.mesrs.sii.commons.business.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

/**
 * @author MAKERRI Sofiane  on : 4 janv. 2015  08:29:22
 */

public class Utility implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 4 janv. 2015  08:29:27
	 */
	private static final long serialVersionUID = 1L;
	
	public Utility() {
		
	}
	
	/**
	 * [Utility.map] Method 
	 * @author MAKERRI Sofiane  on : 4 janv. 2015  08:31:12
	 * @param mapper
	 * @param source
	 * @param destType
	 * @return
	 */
	public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

		if (mapper == null || source == null) {
			return null;
		}
	    final List<U> dest = new ArrayList<>();

	    for (T element : source) {
	        dest.add(mapper.map(element, destType));
	    }

	    return dest;
	}

}
