package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.TacheAction;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface TacheActionDao {

	public  void persist(TacheAction transientInstance);

	public  void remove(TacheAction persistentInstance);

	public  TacheAction merge(TacheAction detachedInstance);

	public  TacheAction findById(int id);
	
	public  List<TacheAction> findByQuery(String query) ;
	
	public  List<TacheAction> findAll();
	
	public  List<TacheAction> findTacheActionByTache(int idTache);

}