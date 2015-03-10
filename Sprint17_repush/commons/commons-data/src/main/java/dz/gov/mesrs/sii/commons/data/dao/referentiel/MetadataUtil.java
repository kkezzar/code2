/**
 * [dz.gov.mesrs.sii.referentiel.business.persistence.MetadataUtil.java] 
 * @author BELDI Jamel on : 13 mars 2014  14:43:25
 */
package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

/**
 * @author BELDI Jamel  on : 13 mars 2014  14:43:25
 */
public interface MetadataUtil {
	
	List findTables();
	List findColonnes(String table);
	

}
