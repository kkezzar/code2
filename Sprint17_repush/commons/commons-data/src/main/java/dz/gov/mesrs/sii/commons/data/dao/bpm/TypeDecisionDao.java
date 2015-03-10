package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDecision;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface TypeDecisionDao {

	public  void persist(TypeDecision transientInstance);

	public  void remove(TypeDecision persistentInstance);

	public  TypeDecision merge(TypeDecision detachedInstance);

	public  TypeDecision findById(int id);
	
	public  List<TypeDecision> findByQuery(String query) ;
	
	public  List<TypeDecision> findAll();

}