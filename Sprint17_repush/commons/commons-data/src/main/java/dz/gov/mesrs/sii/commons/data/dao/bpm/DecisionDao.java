package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Decision;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface DecisionDao {

	public  void persist(Decision transientInstance);

	public  void remove(Decision persistentInstance);

	public  Decision merge(Decision detachedInstance);

	public  Decision findById(int id);
	
	public  List<Decision> findByQuery(String query) ;
	
	public  List<Decision> findAll();

}