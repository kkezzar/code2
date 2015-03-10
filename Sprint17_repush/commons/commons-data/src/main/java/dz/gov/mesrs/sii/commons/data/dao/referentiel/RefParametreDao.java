/**
 * [dz.gov.mesrs.sii.referentiel.business.persistence.RefParametreConfigurationDao.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  12:40:52
 */
package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefParametre;

/**
 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:40:52
 */
public interface RefParametreDao {
	
	public  void persist(RefParametre transientInstance);

	public  void remove(RefParametre persistentInstance);

	public  RefParametre merge(RefParametre detachedInstance);

	public  RefParametre findById(int id);
	
	public  RefParametre findByKey(Integer id, String key);
	
	public  RefParametre findByKey(Integer id, String idfEtab, String key);
	
	public String findValueOfKey(String key);
	
	public String findValueOfKey(String idfEtab,String key);
	
	public  List<RefParametre> findByQuery(String query) ;
	
	public  List<RefParametre> findAll();
	
	public List<RefParametre> findGeneric(String value);
	
	public List<RefParametre> findAllNotGeneric();
	
	
}
