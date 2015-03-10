package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefConfiguration;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefConfigurationDao {

	public  void persist(RefConfiguration transientInstance);

	public  void remove(RefConfiguration persistentInstance);

	public  RefConfiguration merge(RefConfiguration detachedInstance);

	public  RefConfiguration findById(int id);
	
	public  List<RefConfiguration> findByQuery(String query) ;
	
	public  List<RefConfiguration> findAll();
	
	public  List<RefConfiguration> findByDomain(Integer idDomain);

}