package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface EtapeI18nDao {

	public  void persist(EtapeI18n transientInstance);

	public  void remove(EtapeI18n persistentInstance);

	public  EtapeI18n merge(EtapeI18n detachedInstance);

	public  EtapeI18n findById(int id);
	
	public  List<EtapeI18n> findByQuery(String query) ;
	
	public  List<EtapeI18n> findAll();

	public List<EtapeI18n> findListEtapeI18nByEtape(int idEtape);

}