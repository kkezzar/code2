package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTache;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefTacheDao {

	public  void persist(RefTache transientInstance);

	public  void remove(RefTache persistentInstance);

	public  RefTache merge(RefTache detachedInstance);

	public  RefTache findById(int id);
	
	public  List<RefTache> findByQuery(String query) ;
	
	public  List<RefTache> findAll();
	public  List<RefTache> findByEvenement(Integer evenementId);

}