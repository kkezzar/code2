package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDecisionI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface TypeDecisionI18nDao {

	public  void persist(TypeDecisionI18n transientInstance);

	public  void remove(TypeDecisionI18n persistentInstance);

	public  TypeDecisionI18n merge(TypeDecisionI18n detachedInstance);

	public  TypeDecisionI18n findById(int id);
	
	public  List<TypeDecisionI18n> findByQuery(String query) ;
	
	public  List<TypeDecisionI18n> findAll();

}