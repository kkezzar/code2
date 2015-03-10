package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface SituationEntiteDao {

	public  void persist(SituationEntite transientInstance);

	public  void remove(SituationEntite persistentInstance);

	public  SituationEntite merge(SituationEntite detachedInstance);

	public  SituationEntite findById(int id);
	
	public  List<SituationEntite> findByQuery(String query) ;
	
	public  List<SituationEntite> findAll();
	
	public SituationEntite findByCodeSituationByCodeEntite(String codeEntite, String codeSituation);

	public List<SituationEntite> findSituationsByEntiteCode(String codeEntite);

}
