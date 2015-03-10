package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Tache;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface TacheDao {

	public  void persist(Tache transientInstance);

	public  void remove(Tache persistentInstance);

	public  Tache merge(Tache detachedInstance);

	public  Tache findById(int id);
	
	public  List<Tache> findByQuery(String query) ;
	
	public  List<Tache> findAll();

	public List<Tache> findCurrentTacheByEtapeId(int id);
	
	public List<Tache> findFinishTacheByEntity(String entityCode,
			int entityInstanceId);
	

}