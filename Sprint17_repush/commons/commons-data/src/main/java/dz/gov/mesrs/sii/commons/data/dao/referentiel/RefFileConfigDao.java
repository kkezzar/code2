package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFileConfig;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefFileConfigDao {

	public  void persist(RefFileConfig transientInstance);

	public  void remove(RefFileConfig persistentInstance);

	public  RefFileConfig merge(RefFileConfig detachedInstance);

	public  RefFileConfig findById(int id);
	
	public  List<RefFileConfig> findByQuery(String query) ;
	
	public  List<RefFileConfig> findAll();
	
	public List<RefFileConfig> findByEntity(Integer idDomain,
			Integer idEntity, Integer idLanguage); 
	
	public List<RefFileConfig> findByConfiguration(Integer idDomain,
			Integer idConfiguration);
		
	

}