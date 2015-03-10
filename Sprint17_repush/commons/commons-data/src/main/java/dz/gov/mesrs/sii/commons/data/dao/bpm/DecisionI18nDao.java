package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.DecisionI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface DecisionI18nDao {

	public  void persist(DecisionI18n transientInstance);

	public  void remove(DecisionI18n persistentInstance);

	public  DecisionI18n merge(DecisionI18n detachedInstance);

	public  DecisionI18n findById(int id);
	
	public  List<DecisionI18n> findByQuery(String query) ;
	
	public  List<DecisionI18n> findAll();

}