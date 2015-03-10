package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Situation;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface SituationDao {

	public  void persist(Situation transientInstance);

	public  void remove(Situation persistentInstance);

	public  Situation merge(Situation detachedInstance);

	public  Situation findById(int id);
	
	public  List<Situation> findByQuery(String query) ;
	
	public  List<Situation> findAll();

}