/**
 * [dz.gov.mesrs.sii.fve.business.persistence.bac.DossierBachelierDao.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:10:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.Generation;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:10:13
 */
public interface GenerationDao {

	public  void persist(Generation transientInstance);
	
	public  Generation merge(Generation detachedInstance);

	public  Generation findById(int id);
	
	public  List<Generation> findAll();
	
	public  List<Generation> findByEtab(String codeEtab);
	
	public  List<Generation> findByYear(String codeEtablissement, String year);
	
	public  List<String> getAllYears();
	
	public void flushAndClear();

	
}
