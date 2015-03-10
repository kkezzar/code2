package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.ActionI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ActionI18nDao {

	public  void persist(ActionI18n transientInstance);

	public  void remove(ActionI18n persistentInstance);

	public  ActionI18n merge(ActionI18n detachedInstance);

	public  ActionI18n findById(int id);
	
	public  List<ActionI18n> findByQuery(String query) ;
	
	public  List<ActionI18n> findAll();
	
	public List<ActionI18n> findListActionI18nByAction(int idAction);
	
	public List<ActionI18n> findListActionI18nByCodeEtape(String codeEtape);


}