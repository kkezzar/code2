/**
 * [dz.gov.mesrs.sii.fve.business.persistence.bac.DossierBachelierDao.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:10:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.CorrespondanceDomaine;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:10:13
 */
public interface CorrespondanceDomaineDao {

	
	public  List<CorrespondanceDomaine> findAll();
	public  List<CorrespondanceDomaine> findAdvanced(Map<String, String> attributesDto);
	public  List<CorrespondanceDomaine> getFilieres();
	public  List<CorrespondanceDomaine> getFilieresByEtab(String codeEtab, String year);
	
	
}
