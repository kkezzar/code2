/**
 * [dz.gov.mesrs.sii.referentiel.business.persistence.RefParametreEtabDao.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:15:31
 */
package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefParametreEtab;

/**
 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:15:31
 */
public interface RefParametreEtabDao {
	
	public  void persist(RefParametreEtab transientInstance);

	public  void remove(RefParametreEtab persistentInstance);

	public  RefParametreEtab merge(RefParametreEtab detachedInstance);

	public  RefParametreEtab findById(int id);
	
	public  RefParametreEtab findByKey(Integer id, String key);
	
	public  RefParametreEtab findByKey(Integer id, String idfEtab, String key);
	
	public  RefParametreEtab findByKeyEtab(String idfEtab, String key);
	
	public String findValueOfKey(String key);
	
	public String findValueOfKey(Integer idEtab,String key);
	
	public  List<RefParametreEtab> findByQuery(String query) ;
	
	public  List<RefParametreEtab> findAll();
	
	public  List<RefParametreEtab> findAll(int idEtab);
	
	public  List<RefParametreEtab> findAllNotGeneric();
	
	public List<RefParametreEtab> findGeneric(Integer idEtab, String value);

}
