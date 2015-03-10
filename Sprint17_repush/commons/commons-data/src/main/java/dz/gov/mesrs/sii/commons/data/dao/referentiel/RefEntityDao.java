package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntity;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefEntityDao {

	public  void persist(RefEntity transientInstance);

	public  void remove(RefEntity persistentInstance);

	public  RefEntity merge(RefEntity detachedInstance);

	public  RefEntity findById(int id);
	
	public  List<RefEntity> findByQuery(String query) ;
	
	public  List<RefEntity> findAll();
	
	public  List<RefEntity> findByDomain(Integer idDomain);

}