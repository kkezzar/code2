package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface SituationEntiteOccurrenceDao {

	public  void persist(SituationEntiteOccurrence transientInstance);

	public  void remove(SituationEntiteOccurrence persistentInstance);

	public  SituationEntiteOccurrence merge(SituationEntiteOccurrence detachedInstance);

	public  SituationEntiteOccurrence findById(int id);
	
	public  List<SituationEntiteOccurrence> findByQuery(String query) ;
	
	public  List<SituationEntiteOccurrence> findAll();
	
	public  List<SituationEntiteOccurrence> getEntityOccurrenceHistory(String entityTypeCode, Integer entityOccurrenceId);

}