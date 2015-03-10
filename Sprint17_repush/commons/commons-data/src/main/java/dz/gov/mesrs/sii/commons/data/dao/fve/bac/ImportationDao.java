/**
 * [dz.gov.mesrs.sii.fve.business.persistence.bac.DossierBachelierDao.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:10:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.Importation;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:10:13
 */
public interface ImportationDao {

	public  void persist(Importation transientInstance);

	public  void remove(Importation persistentInstance);

	public  Importation merge(Importation detachedInstance);

	public  Importation findById(int id);
	
	public  List<Importation> findAll();
	
	public  List<Importation> findByTypeByYear(String typeCode, String year);
	
	public  List<String> getAllYears();
	
	public  List<Importation> findByYear(String year);
	
	public void flushAndClear();

}
