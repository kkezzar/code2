package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.ProcessusI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ProcessusI18nDao {

	public  void persist(ProcessusI18n transientInstance);

	public  void remove(ProcessusI18n persistentInstance);

	public  ProcessusI18n merge(ProcessusI18n detachedInstance);

	public  ProcessusI18n findById(int id);
	
	public  List<ProcessusI18n> findByQuery(String query) ;
	
	public  List<ProcessusI18n> findAll();

}